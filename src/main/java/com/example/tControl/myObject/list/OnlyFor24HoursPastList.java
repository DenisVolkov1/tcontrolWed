package com.example.tControl.myObject.list;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import com.example.tControl.component.MessageTemperatureComponent;

public class OnlyFor24HoursPastList<T extends MessageTemperatureComponent> extends ArrayList<T>{

	
	public boolean addOnlyFor24Hours(T e) {
		LocalDateTime now =  LocalDateTime.now();
		//LocalDateTime nowMinus24hours =  now.minus(Duration.ofHours(24));
		LocalDateTime nowMinus24hours =  now.minus(Duration.ofSeconds(24));
		Iterator<T> i = this.iterator();
		while (i.hasNext()) {
		   T t = i.next(); // must be called before you can call i.remove()
		   // Do something
		   boolean timePassed_less_than_NowMinus24hours = t.getMessageInformation().getDateTimePassed().isBefore(nowMinus24hours);
		   if(timePassed_less_than_NowMinus24hours) {
			   i.remove();
		   }
		}
		super.add(0,e);
		return true;
	}
}
