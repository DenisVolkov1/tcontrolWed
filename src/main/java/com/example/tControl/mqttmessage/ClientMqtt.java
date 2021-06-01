package com.example.tControl.mqttmessage;

import java.time.LocalDateTime;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import com.example.tControl.base.PastEmployeesBase;
import com.example.tControl.component.MessageTemperatureComponent;
import com.example.tControl.pojo.Employee;
import com.example.tControl.views.Desktop;

public class ClientMqtt {
	
	private static IMqttClient client;
	
	private ClientMqtt() {}
	
	public static void setClientMqtt(/*final List<Component> itemsList, PushVerticalLayout push*/) throws MqttException {
		  String publisherId = UUID.randomUUID().toString();
		  if (client == null || (client != null && !client.isConnected())) {
			  client =  new MqttClient("tcp://localhost:1883",publisherId,new MqttDefaultFilePersistence("/tmpMQTT"));
			  client.setCallback(new MqttCallback() {
	            @Override
	            public void connectionLost(Throwable throwable) {
	                System.out.println("lost connection");

	            }

	            @Override
	            public void messageArrived(String s, MqttMessage mqttMessage) {

	                System.out.println("message is : "+mqttMessage);
	                LocalDateTime timePassed = LocalDateTime.now();
	                try {
	                	String[] mes =  mqttMessage.toString().split("_");
						Employee informationPassedEmployee = PastEmployeesBase.insertPastEmployee(Integer.valueOf(mes[0]), mes[1],timePassed);
						
							MessageTemperatureComponent temperatureComponent = new MessageTemperatureComponent(informationPassedEmployee, mes[0], timePassed);
							System.out.println(temperatureComponent);
							Desktop.getListBoxLayout().addWithPush(temperatureComponent);
								Desktop.getItemsList().add(temperatureComponent);
							
			
						    	

					} catch (Exception e) {
						e.printStackTrace();
					}
	                
	                
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
	
	

}
