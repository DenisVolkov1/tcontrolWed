package com.example.tControl.myObject;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;


public class WrapperLocalDateTime implements Comparable<WrapperLocalDateTime> {
	
	private LocalDateTime localDateTime;
	
	public WrapperLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");  
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = (localDateTime != null) ? localDateTime.format(formatter) : "";
		return formatDateTime;
	}
	//return localDateTime.compareTo(other);

	@Override
	public int compareTo(WrapperLocalDateTime other) {
		// TODO Auto-generated method stub
		return localDateTime.compareTo(other.getLocalDateTime());
	}

}
