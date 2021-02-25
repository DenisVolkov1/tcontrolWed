package com.example.tControl.component;

import java.time.LocalTime;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

@CssImport(value="./styles/displayblock.css", themeFor="vaadin-text-field" )
public class TimeInputter extends HorizontalLayout {
	protected TextField hours;
	protected TextField minutes;

	public TimeInputter() {
		
		this.setAlignItems(Alignment.CENTER);
		this.setSpacing(false);
		
		hours = new TextField();
		hours.setClassName("display-block");
		hours.getStyle().set("margin-right", "5px");
		hours.setWidth("40px");
		hours.setValueChangeMode(ValueChangeMode.EAGER);
		
		H5 razdelOt = new H5(":");
		razdelOt.getStyle().set("margin-bottom", "20px");
		
		minutes = new TextField();
		minutes.setClassName("display-block");
		minutes.getStyle().set("margin-left", "5px");
		minutes.setWidth("40px");
		minutes.setValueChangeMode(ValueChangeMode.EAGER);
		
		// HOURS LISTENERS
		hours.addValueChangeListener(event -> {
			//
			if (event.getValue().length() == 1) {
				if (!(event.getValue().matches("[012]"))) {
					if (event.getValue().length() > event.getOldValue().length()) hours.setValue(event.getOldValue());
				}
			} else if (event.getValue().length() == 2) {
				if (!(event.getValue().matches("\\d{2}") && Integer.parseInt(event.getValue()) <= 23)) {
					hours.setValue(event.getOldValue());
				}
			} else if (event.getValue().length() == 3) {
				hours.setValue(event.getOldValue());
			}
		});
		hours.addBlurListener(event-> {
			if (hours.getValue().length() == 1) {
				hours.setValue("0"+hours.getValue());
			}
			
		});
		// MINUTES LISTENERS
		minutes.addValueChangeListener(event -> {
			if (event.getValue().length() == 1) {
				if (!(event.getValue().matches("[012345]"))) {
					if (event.getValue().length() > event.getOldValue().length()) minutes.setValue(event.getOldValue());
				}
			} else if (event.getValue().length() == 2) {
				
				if (!(event.getValue().matches("\\d+") && Integer.parseInt(event.getValue()) <= 59)) {
					minutes.setValue(event.getOldValue());
				}
			} else if (event.getValue().length() == 3) {
				minutes.setValue(event.getOldValue());
			}
		});
		minutes.addBlurListener(event-> {
			
			if (minutes.getValue().length() == 1) {
				minutes.setValue("0"+minutes.getValue());
			}
		});
		
		add(hours, razdelOt, minutes);
	}
	public void setHours(int hh) {
		if (hh > 23 || hh < 0) throw new IllegalArgumentException("values between >=0 and <=23");
		if (hh > 9) this.hours.setValue("0" + String.valueOf(hh));
		if (hh == 0) this.hours.setValue("00");
		else this.hours.setValue(String.valueOf(hh));
	}
	public void setMinutes(int mm) {
		if (mm > 59 || mm < 0) throw new IllegalArgumentException("values between >=0 and <=59");
		if (mm > 9) this.minutes.setValue("0" + String.valueOf(mm));
		if (mm == 0) this.minutes.setValue("00");
		else this.minutes.setValue(String.valueOf(mm));
	}
	public boolean isEmptyMinutes() {
		return (this.minutes.getValue().length() > 0) ? false : true;
	}
	public boolean isEmptyHours() {
		return (this.hours.getValue().length() > 0) ? false : true;
	}
	public void setTime(LocalTime localTime) {
		this.hours.setValue(  String.valueOf(localTime.getHour()));
		this.minutes.setValue(String.valueOf(localTime.getMinute()));
	}
	public LocalTime getTime() {
		System.out.println(this.hours.getValue());
		
		int hours =  (this.hours.getValue().length() > 0) ? Integer.parseInt(this.hours.getValue()) : 0;
		int minutes = (this.minutes.getValue().length() > 0) ? Integer.parseInt(this.minutes.getValue()) : 0;
			LocalTime localTime = LocalTime.of(hours, minutes, 0, 0);
		return localTime;
	}
	public String toString() {
		return getTime().toString();
	}
	
}
