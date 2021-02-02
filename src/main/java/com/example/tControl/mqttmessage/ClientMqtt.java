package com.example.tControl.mqttmessage;

import java.util.List;
import java.util.UUID;
import org.eclipse.paho.client.mqttv3.*;

import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.UUID;

public class ClientMqtt {
	
	public ClientMqtt(List itemsList) throws MqttSecurityException, MqttException {
		  String publisherId = UUID.randomUUID().toString();
	        IMqttClient client = new MqttClient("tcp://localhost:1883",publisherId);
	        client.setCallback(new MqttCallback() {
	            @Override
	            public void connectionLost(Throwable throwable) {
	                System.out.println("lost connection");

	            }

	            @Override
	            public void messageArrived(String s, MqttMessage mqttMessage) {
	            
	                System.out.println("topic is : "+s);
	                System.out.println("message is : "+mqttMessage);
	                
	               
	            }

	            @Override
	            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

	            }
	        });

	        MqttConnectOptions options = new MqttConnectOptions();
	        options.setAutomaticReconnect(true);
	        options.setCleanSession(true);
	        options.setConnectionTimeout(10);

	        client.connect(options);
	        client.subscribe("home/temp");
		
	}

}
