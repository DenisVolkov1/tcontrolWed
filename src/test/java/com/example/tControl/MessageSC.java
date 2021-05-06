package com.example.tControl;

import java.time.LocalDateTime;

import com.example.tControl.component.MessageTemperatureComponent;
import com.example.tControl.pojo.Employee;

public class MessageSC extends MessageTemperatureComponent {

	public MessageSC(Employee informationPassedEmployee, String temperaturePassedEmployee,
			LocalDateTime dateTimePassed) {
		super(informationPassedEmployee, temperaturePassedEmployee, dateTimePassed);
		// TODO Auto-generated constructor stub
	}

}
