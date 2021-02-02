package com.example.tControl.views;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.example.tControl.component.PushVerticalLayout;
import com.example.tControl.mqttmessage.ClientMqtt;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Bottom;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLayout;


@Route(value = "desktop", layout = MainView.class)
@PageTitle("Desktop")
@CssImport("./styles/views/desktop/desktop.css")
@RouteAlias(value = "", layout = MainView.class)
@Tag(value = "d1")


public class Desktop extends VerticalLayout {
	private HorizontalLayout extendedInformation;
	private PushVerticalLayout listBoxLayout;
	private Button expandButton;
	private List itemsList;
	
	public Desktop() {
		
		try {
			new ClientMqtt(itemsList);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extendedInformation = new HorizontalLayout();
		//extendedInformation.getStyle().set("display","block");
		//extendedInformation.setSizeUndefined();

		VerticalLayout labelExtendedInformation = new VerticalLayout();
		H6 fioExtendedInformation = new H6("Фролов Максим Викторович");
		H6 divisionExtendedInformation = new H6("Отдел продаж");
		H6 positionExtendedInformation = new H6("Бухгалтер");
		H6 idCardExtendedInformation = new H6("132456258");
		H6 tCExtendedInformation = new H6("37.8");
		H6 timeExtendedInformation = new H6("20:30");
		labelExtendedInformation.add(fioExtendedInformation, divisionExtendedInformation,positionExtendedInformation ,idCardExtendedInformation,tCExtendedInformation,timeExtendedInformation);
		
		Image photoExtendedInformation = new Image("images/noPhoto.png", "noPhoto");
		photoExtendedInformation.setWidth("400px");
		photoExtendedInformation.setHeight("400px");
		
		expandButton = new Button(new Icon(VaadinIcon.ARROW_DOWN), event -> {
			extendedInformation.setEnabled(true);
			extendedInformation.setVisible(true);
			//
			expandButton.setEnabled(false);
			expandButton.setVisible(false);
			listBoxLayout.setHeight("650px");
		});
		expandButton.setEnabled(false);
		expandButton.setVisible(false);
		Button rollUpButton = new Button(new Icon(VaadinIcon.ARROW_UP), event -> {
			extendedInformation.setEnabled(false);
			extendedInformation.setVisible(false);
			listBoxLayout.setHeight("650px");
			expandButton.setEnabled(true);
			expandButton.setVisible(true);

		});

		
		
		extendedInformation.add(labelExtendedInformation, photoExtendedInformation,rollUpButton,expandButton);
		
		listBoxLayout = new PushVerticalLayout();
		
		ListBox listBox = new ListBox<>();

		listBox.addValueChangeListener(event -> {
			extendedInformation.setEnabled(true);
			extendedInformation.setVisible(true);
			//
			expandButton.setEnabled(false);
			expandButton.setVisible(false);
		});

		listBoxLayout.getStyle().set("overflow", "auto");
		
		//listBoxLayout.getStyle().set("border", "1px solid");
		//listBoxLayout.setWidth("1300px");
		listBoxLayout.setHeight("400px");

		
		itemsList = new ArrayList();
		for (int i = 0; i < 70; i++) {

			//itemsList.add(layout);
		}
		
		listBox.setRenderer(new ComponentRenderer(item -> {

		    return item;
		}));
		//listBox.setItems(itemsList);
		//listBox.setSizeFull();

		
		
		listBoxLayout.setSpacing(true);
		//listBoxLayout.add(listBox);
		
		//setWidth("500px");
		//setMaxHeight("1500px");

		add(extendedInformation,expandButton,listBoxLayout);
		new TimeAndDateDeamonThread().start();
		
	}
	//"images/noPhoto.png", "noPhoto"
	private Component createMessageTemperatureComponent(String photoUrl,String fio,String tC,String time) {
		HorizontalLayout layout = new HorizontalLayout();
		//layout.setHeight("100px");
		layout.setWidthFull();
		layout.setClassName("hoverMesTempComp");
		
		Image image = new Image(photoUrl, "noPhoto");
		image.setWidth("90px");
		image.setHeight("90px");
		layout.add(image);
		VerticalLayout layout2 = new VerticalLayout();
		//layout2.setWidth("200px");
		
		H5 fioLabel = new H5(fio);
		fioLabel.setClassName("h5");
		fioLabel.setWidth("300px");

		H6 cardId = new H6("456789258");
		//cardId.setClassName("h5");

		layout2.add(fioLabel, cardId);
		
		VerticalLayout layout3 = new VerticalLayout();
		H6 tCLabel = new H6(tC);
		tCLabel.setClassName("h5");

		H6 timeLabel = new H6(time);
		//timeLabel.setClassName("h5");

		layout3.add(tCLabel, timeLabel);

		layout.add(image,layout2,layout3);
		
		layout.addClickListener(event -> {
			extendedInformation.setEnabled(true);
			extendedInformation.setVisible(true);
			//
			expandButton.setEnabled(false);
			expandButton.setVisible(false);
			
		});
		//layout.addEventListener("mouseover",enevt -> System.out.println("oblgfbdfg"));
           
        
		
		return layout;
	}
	
    private class TimeAndDateDeamonThread extends Thread {
    	
    	public void run() {
    		  try {sleep(5500);} catch (InterruptedException e) {}
    		  System.out.println("in thread");
    		  try {
	    		  for (int i = 0; i < 70; i++) {
	            	
	                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	                //DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
	                Component layout = createMessageTemperatureComponent("images/noPhoto.png", "Бессонов Владимир Игнатьевич", "45.5",  timeFormat.format( System.currentTimeMillis()));
	    			listBoxLayout.addWithPush(layout);
	                //time.setText(timeFormat.format( System.currentTimeMillis()));
	               
	                //System.out.println("time "+timeFormat.format(System.currentTimeMillis())+"date "+dateFormat.format(System.currentTimeMillis()));
	    			  System.out.println("add comp");
	                try {sleep(4500);} catch (InterruptedException e) {}                   	
	            }
    		  } catch (Exception e) {
				e.printStackTrace();
			}	  
        }
    	
    }
	 

	
	
}
