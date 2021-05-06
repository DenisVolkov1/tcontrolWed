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
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
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
	private static List<MessageTemperatureComponent> itemsList = new ArrayList<MessageTemperatureComponent>();
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
		
		listBoxLayout =  new PushVerticalLayout<MessageTemperatureComponent>(itemsList);
		
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

		itemsList = new ArrayList<MessageTemperatureComponent>();
		
		listBox.setRenderer(new ComponentRenderer(item -> {

		    return item;
		}));
		
		
		listBoxLayout.setSpacing(true);
		add(extendedInformation,expandButton,listBoxLayout);
		
	}
	//"images/noPhoto.png", "noPhoto"
//	private Component createMessageTemperatureComponent(String photoUrl,String fio, String tC,String time) {
//		HorizontalLayout layout = new HorizontalLayout();
//		//layout.setHeight("100px");
//		layout.setWidthFull();
//		layout.setClassName("hoverMesTempComp");
//		
//		Image image = new Image(photoUrl, "noPhoto");
//		image.setWidth("90px");
//		image.setHeight("90px");
//		layout.add(image);
//		VerticalLayout layout2 = new VerticalLayout();
//		//layout2.setWidth("200px");
//		
//		H5 fioLabel = new H5(fio);
//		fioLabel.setClassName("h5");
//		fioLabel.setWidth("300px");
//
//		H6 cardId = new H6("456789258");
//		//cardId.setClassName("h5");
//
//		layout2.add(fioLabel, cardId);
//		
//		VerticalLayout layout3 = new VerticalLayout();
//		H6 tCLabel = new H6(tC);
//		tCLabel.setClassName("h5");
//
//		H6 timeLabel = new H6(time);
//		//timeLabel.setClassName("h5");
//
//		layout3.add(tCLabel, timeLabel);
//
//		layout.add(image,layout2,layout3);
//		
//		layout.addClickListener(event -> {
//			extendedInformation.setEnabled(true);
//			extendedInformation.setVisible(true);
//			//
//			expandButton.setEnabled(false);
//			expandButton.setVisible(false);
//			
//			
//		});
//		//layout.addEventListener("mouseover",enevt -> System.out.println("oblgfbdfg"));
//           
//        
//		
//		return layout;
//	}
	
//    private class TimeAndDateDeamonThread extends Thread {
//    	
//    	public void run() {
//    		  try {sleep(5500);} catch (InterruptedException e) {}
//    		 // System.out.println("in thread");
//    		  try {
//	    		  for (int i = 0; i < 70; i++) {
//	            	
//	                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
//	                //DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
//	                Component layout = createMessageTemperatureComponent("images/noPhoto.png", "Бессонов Владимир Игнатьевич", "45.5",  timeFormat.format( System.currentTimeMillis()));
//	    			listBoxLayout.addWithPush(layout);
//	    			itemsList.add(layout);
//	                //time.setText(timeFormat.format( System.currentTimeMillis()));
//	               
//	                //System.out.println("time "+timeFormat.format(System.currentTimeMillis())+"date "+dateFormat.format(System.currentTimeMillis()));
//	    			 // System.out.println("add comp");
//	                try {sleep(2500);} catch (InterruptedException e) {}                   	
//	            }
//    		  } catch (Exception e) {
//				e.printStackTrace();
//			}	  
//        }
//    	
//    }
    
    public static ComponentEventListener<ClickEvent<HorizontalLayout>> getExtendedInformationListener(String fio, String division, String position, String idCard, String time, StreamResource imageResource) {
    	ComponentEventListener<ClickEvent<HorizontalLayout>> listener = new ComponentEventListener<ClickEvent<HorizontalLayout>>() {
			
			@Override
			public void onComponentEvent(ClickEvent<HorizontalLayout> event) {
				extendedInformation.setEnabled(true);
				extendedInformation.setVisible(true);
				//
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
		    	
		    	//imageResource.setF
		    	//Desktop.photoExtendedInformation.se
		    	Desktop.photoExtendedInformation.getElement().setAttribute("src", imageResource);
				//(Desktop.photoExtendedInformation).setSrc(imageResource);
			}
		};
		return listener;
    }
    
//    public static void getI() {
//    	//Image i = new Image();
//    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("my");
//    	EntityManager em = emf.createEntityManager();
//    	// Retrieve image from database from user id = 1
//
//    	Query q = em.createQuery("SELECT photoByteArray FROM Employee WHERE id = 107");
//    	byte[] bytes = (byte[]) q.getSingleResult();
//		
//		// Set the image from database
//    	StreamResource imageResource = new StreamResource("",() -> new ByteArrayInputStream(bytes));
//    	
//    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//    	String filename = "myfilename-" + df.format(new Date()) + ".png";
//    	
//    	//imageResource.setF
//    	photoExtendedInformation.getElement().setAttribute("src", imageResource);
//		//return i;
//    }

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
	public static List<MessageTemperatureComponent> getItemsList() {
		return itemsList;
	}
	public static LocalDateTime getLastedDateTime() {
		return lastedDateTime;
	}
	 

	
	
}
