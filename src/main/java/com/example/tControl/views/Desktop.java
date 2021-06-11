package com.example.tControl.views;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.example.tControl.component.MessageTemperatureComponent;
import com.example.tControl.component.PushVerticalLayout;
import com.example.tControl.myObject.MessageTemperatureInformation;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;


@Route(value = "desktop", layout = MainView.class)
@PageTitle("Desktop")
@CssImport("./styles/views/desktop/desktop.css")
@RouteAlias(value = "", layout = MainView.class)
@Tag(value = "d1")


public class Desktop extends VerticalLayout {
	private static HorizontalLayout extendedInformation;
	private static H6 fioExtendedInformation; 
	private static H6 divisionExtendedInformation; 
	private static H6 positionExtendedInformation; 
	private static H6 idCardExtendedInformation; 
	private static H6 tCExtendedInformation; 
	private static H6 timeExtendedInformation; 
	private static Image photoExtendedInformation;
	private static LocalDateTime lastedDateTime;

	//
	private static PushVerticalLayout<MessageTemperatureComponent> listBoxLayout;
	//private static List<MessageTemperatureComponent> itemsList = new ArrayList<MessageTemperatureComponent>();
	private static List<MessageTemperatureInformation> itemsListTemperatureInformation = new ArrayList<MessageTemperatureInformation>();
	private static List<MessageTemperatureComponent> itemsListTemperatureComponent = new ArrayList<MessageTemperatureComponent>();
	//
	private static Button expandButton;
	
	public Desktop() {
		
		extendedInformation = new HorizontalLayout();
		extendedInformation.setEnabled(false);
		extendedInformation.setVisible(false);
		//extendedInformation.getStyle().set("display","block");
		//extendedInformation.setSizeUndefined();

		VerticalLayout labelExtendedInformation = new VerticalLayout();
		fioExtendedInformation = new H6("Фролов Максим Викторович");
		divisionExtendedInformation = new H6("Отдел продаж");
		positionExtendedInformation = new H6("Бухгалтер");
		idCardExtendedInformation = new H6("132456258");
		tCExtendedInformation = new H6("37.8");
		timeExtendedInformation = new H6("20:30");
		labelExtendedInformation.add(fioExtendedInformation, divisionExtendedInformation,positionExtendedInformation ,idCardExtendedInformation,tCExtendedInformation,timeExtendedInformation);
		
		photoExtendedInformation = new Image("images/noPhoto.png", "noPhoto");
		photoExtendedInformation.setWidth("220px");
		photoExtendedInformation.setHeight("220px");
		
		expandButton = new Button(new Icon(VaadinIcon.ARROW_DOWN), event -> {
			extendedInformation.setEnabled(true);
			extendedInformation.setVisible(true);
			//
			expandButton.setEnabled(false);
			expandButton.setVisible(false);
			listBoxLayout.setHeight("650px");
		
			
		});
		expandButton.setEnabled(true);
		expandButton.setVisible(true);
		Button rollUpButton = new Button(new Icon(VaadinIcon.ARROW_UP), event -> {
			extendedInformation.setEnabled(false);
			extendedInformation.setVisible(false);
			listBoxLayout.setHeight("650px");
			expandButton.setEnabled(true);
			expandButton.setVisible(true);

		});
		
		extendedInformation.add(labelExtendedInformation, photoExtendedInformation,rollUpButton,expandButton);
		
		itemsListTemperatureComponent = new ArrayList<MessageTemperatureComponent>();
			fillInItemsListTemperatureComponent();
			System.out.println("size =" + itemsListTemperatureComponent.size());
				listBoxLayout =  new PushVerticalLayout<MessageTemperatureComponent>(itemsListTemperatureComponent);
					addingMessageTemperatureComponents();
				
		ListBox<Object> listBox = new ListBox<>();

		listBox.addValueChangeListener(event -> {
			extendedInformation.setEnabled(true);
			extendedInformation.setVisible(true);
			//
			expandButton.setEnabled(false);
			expandButton.setVisible(false);
		});

		listBoxLayout.getStyle().set("overflow", "auto");
		listBoxLayout.setHeight("400px");

		//itemsList = new ArrayList<MessageTemperatureComponent>();
		
		
		
		listBox.setRenderer(new ComponentRenderer(item -> {

		    return item;
		}));
		
		
		listBoxLayout.setSpacing(true);
		add(extendedInformation,expandButton,listBoxLayout);
		
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
//		System.out.println("onAttach_Desktop");
//		itemsListTemperatureComponent = new ArrayList<MessageTemperatureComponent>();
//		fillInItemsListTemperatureComponent();
//			listBoxLayout =  new PushVerticalLayout<MessageTemperatureComponent>(itemsListTemperatureComponent);
		
	}

	
	@Override
	protected void onDetach(DetachEvent detachEvent) {
		// TODO Auto-generated method stub
		super.onDetach(detachEvent);
	}
	private void fillInItemsListTemperatureComponent() {
		if(itemsListTemperatureInformation.size() > 0) {
			for (MessageTemperatureInformation imti : itemsListTemperatureInformation) {
				MessageTemperatureComponent temperatureComponent = new MessageTemperatureComponent(imti);
				itemsListTemperatureComponent.add(temperatureComponent);
			}
		}
	}
	private void addingMessageTemperatureComponents() {
		if(itemsListTemperatureComponent.size() > 0) {
			for (MessageTemperatureComponent imtc : itemsListTemperatureComponent) {
				listBoxLayout.add(imtc);
			}
		}
	}
    public static ComponentEventListener<ClickEvent<HorizontalLayout>> getExtendedInformationListener(String fio, String division, String position, String idCard, String time, StreamResource imageResource) {
    	ComponentEventListener<ClickEvent<HorizontalLayout>> listener = new ComponentEventListener<ClickEvent<HorizontalLayout>>() {
			
			@Override
			public void onComponentEvent(ClickEvent<HorizontalLayout> event) {
				extendedInformation.setEnabled(true);
				extendedInformation.setVisible(true);
				expandButton.setEnabled(false);
				expandButton.setVisible(false);
				//
				Desktop.fioExtendedInformation.setText(fio);
				Desktop.divisionExtendedInformation.setText(division);
				Desktop.positionExtendedInformation.setText(position);
				Desktop.idCardExtendedInformation.setText(idCard);
				Desktop.timeExtendedInformation.setText(time);
				
		    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		    	String filename = "myfilename-" + df.format(new Date()) + ".png";
		    	if(imageResource != null) Desktop.photoExtendedInformation.getElement().setAttribute("src", imageResource);
			}
		};
		return listener;
    }
    

	public static HorizontalLayout getExtendedInformation() {
		return extendedInformation;
	}
	public static H6 getFioExtendedInformation() {
		return fioExtendedInformation;
	}
	public static void setFioExtendedInformation(H6 fioExtendedInformation) {
		Desktop.fioExtendedInformation = fioExtendedInformation;
	}
	public static H6 getDivisionExtendedInformation() {
		return divisionExtendedInformation;
	}
	public static void setDivisionExtendedInformation(H6 divisionExtendedInformation) {
		Desktop.divisionExtendedInformation = divisionExtendedInformation;
	}
	public static H6 getPositionExtendedInformation() {
		return positionExtendedInformation;
	}
	public static void setPositionExtendedInformation(H6 positionExtendedInformation) {
		Desktop.positionExtendedInformation = positionExtendedInformation;
	}
	public static H6 getIdCardExtendedInformation() {
		return idCardExtendedInformation;
	}
	public static void setIdCardExtendedInformation(H6 idCardExtendedInformation) {
		Desktop.idCardExtendedInformation = idCardExtendedInformation;
	}
	public static H6 gettCExtendedInformation() {
		return tCExtendedInformation;
	}
	public static void settCExtendedInformation(H6 tCExtendedInformation) {
		Desktop.tCExtendedInformation = tCExtendedInformation;
	}
	public static H6 getTimeExtendedInformation() {
		return timeExtendedInformation;
	}
	public static void setTimeExtendedInformation(H6 timeExtendedInformation) {
		Desktop.timeExtendedInformation = timeExtendedInformation;
	}
	public static Image getPhotoExtendedInformation() {
		return photoExtendedInformation;
	}
	public static void setPhotoExtendedInformation(Image photoExtendedInformation) {
		Desktop.photoExtendedInformation = photoExtendedInformation;
	}
	public static PushVerticalLayout<MessageTemperatureComponent> getListBoxLayout() {
		return listBoxLayout;
	}
	public static List<MessageTemperatureInformation> getItemsListTemperatureInformation() {
		return itemsListTemperatureInformation;
	}
	public static LocalDateTime getLastedDateTime() {
		return lastedDateTime;
	}
	 

	
	
}
