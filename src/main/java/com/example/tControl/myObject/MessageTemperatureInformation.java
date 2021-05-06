package com.example.tControl.myObject;

import java.time.LocalDateTime;

import com.example.tControl.pojo.Employee;

public class MessageTemperatureInformation {
	
	private Employee informationPassedEmployee;
	private LocalDateTime dateTimePassed;
	private String temperaturePassedEmployee;
	
	
	public MessageTemperatureInformation(Employee informationPassedEmployee,String temperaturePassedEmployee, LocalDateTime dateTimePassed) {
		super();
		this.informationPassedEmployee = informationPassedEmployee;
		this.dateTimePassed = dateTimePassed;
		this.temperaturePassedEmployee = temperaturePassedEmployee;
	}


	public Employee getInformationPassedEmployee() {
		return informationPassedEmployee;
	}


	public LocalDateTime getDateTimePassed() {
		return dateTimePassed;
	}


	public String getTemperaturePassedEmployee() {
		return temperaturePassedEmployee;
	}
	

}
