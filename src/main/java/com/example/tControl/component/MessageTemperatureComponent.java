package com.example.tControl.component;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.example.tControl.myObject.MessageTemperatureInformation;
import com.example.tControl.pojo.Employee;
import com.example.tControl.views.Desktop;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;

public class MessageTemperatureComponent extends HorizontalLayout {

	private MessageTemperatureInformation information;
	public StreamResource imageResource;
	
//	public MessageTemperatureComponent(Employee informationPassedEmployee,String temperaturePassedEmployee, LocalDateTime dateTimePassed) {
//		this.information = new MessageTemperatureInformation(informationPassedEmployee, temperaturePassedEmployee, dateTimePassed);
//
//		setWidthFull();
//		setClassName("hoverMesTempComp");
//		PushImage photo = new PushImage(informationPassedEmployee);
//		imageResource = photo.getImageResource();
//		
//		photo.setWidth("90px");
//		photo.setHeight("90px");
//		add(photo);
//		VerticalLayout layout2 = new VerticalLayout();
//
//		H5 fioLabel = new H5(informationPassedEmployee.getFio());
//		fioLabel.setClassName("h5");
//		fioLabel.setWidth("300px");
//
//		H6 cardId = new H6("456789258");
//
//		layout2.add(fioLabel, cardId);
//		
//		VerticalLayout layout3 = new VerticalLayout();
//		H6 tCLabel = new H6(temperaturePassedEmployee);
//		tCLabel.setClassName("h5");
//
//		H6 timeLabel = new H6("Проход №1");
//
//		layout3.add(tCLabel, timeLabel);
//
//		add(photo,layout2,layout3);
//		
//		// ADD LISTENERS
//		DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
//			String time = dateTimePassed.format(formatTime);
//				addClickListener(Desktop.getExtendedInformationListener(informationPassedEmployee.getFio(), informationPassedEmployee.getDivision(), informationPassedEmployee.getPosition(), informationPassedEmployee.getIdCard(), time, imageResource));
//	}
	
	public MessageTemperatureComponent(MessageTemperatureInformation information) {
		this.information = information;

		setWidthFull();
		setClassName("hoverMesTempComp");
		PushImage photo = new PushImage(information.getInformationPassedEmployee());
		imageResource = photo.getImageResource();
		
		photo.setWidth("90px");
		photo.setHeight("90px");
		add(photo);
		VerticalLayout layout2 = new VerticalLayout();

		
		H5 fioLabel = new H5(information.getInformationPassedEmployee().getFio());
		fioLabel.setClassName("h5");
		fioLabel.setWidth("300px");

		H6 cardId = new H6("456789258");

		layout2.add(fioLabel, cardId);
		
		VerticalLayout layout3 = new VerticalLayout();
		H6 tCLabel = new H6(information.getTemperaturePassedEmployee());
		tCLabel.setClassName("h5");

		H6 timeLabel = new H6("Проход №1");
		
		layout3.add(tCLabel, timeLabel);
		add(photo,layout2,layout3);
		
		// ADD LISTENERS
		DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
			String time = (information.getDateTimePassed()).format(formatTime);
		addClickListener(Desktop.getExtendedInformationListener(information.getInformationPassedEmployee().getFio(), information.getInformationPassedEmployee().getDivision(), information.getInformationPassedEmployee().getPosition(), information.getInformationPassedEmployee().getIdCard(), time, imageResource));
	}
	

	public StreamResource getImageResource() {
		return imageResource;
	}

	public MessageTemperatureInformation getMessageInformation() {
		return information;
	}
	
	
	

}
