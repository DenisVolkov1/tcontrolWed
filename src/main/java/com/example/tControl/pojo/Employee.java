package com.example.tControl.pojo;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;


@Entity
@Table( name = "EmployeesList" )
public class Employee {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int ID;
	private String personnelNumber;
	private String fio;
	private String idCard;
	private String division;
	private String position;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] photoByteArray;
	@Basic
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime addDateTime;
	
	
	public Employee() {}
	
	public Employee(String personnelNumber, String fio, String idCard, String division, String position) {
		super();
		this.personnelNumber = personnelNumber;
		this.fio = fio;
		this.idCard = idCard;
		this.division = division;
		this.position = position;
	}
	
	public Employee(String personnelNumber, String fio, String idCard, String division, String position, LocalDateTime addDateTime) {
		super();
		this.personnelNumber = personnelNumber;
		this.fio = fio;
		this.idCard = idCard;
		this.division = division;
		this.position = position;
		this.addDateTime = addDateTime;
	}
	
	public Employee(String personnelNumber, String fio, String idCard, String division, String position,LocalDateTime addDateTime, byte[] photoByteArray) {
		super();
		this.personnelNumber = personnelNumber;
		this.fio = fio;
		this.idCard = idCard;
		this.division = division;
		this.position = position;
		this.photoByteArray = photoByteArray;
		this.addDateTime = addDateTime;
	}

	public byte[] getPhotoByteArray() {
		return photoByteArray;
	}

	public void setPhotoByteArray(byte[] photoByteArray) {
		this.photoByteArray = photoByteArray;
	}

	public String getPersonnelNumber() {
		return personnelNumber;
	}
	public void setPersonnelNumber(String personnelNumber) {
		this.personnelNumber = personnelNumber;
	}
	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
	@Override
	public String toString() {
		return  fio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (idCard == null) {
			if (other.idCard != null)
				return false;
		} else if (!idCard.equals(other.idCard))
			return false;
		return true;
	}

	
	
	
	
}
