package com.example.tControl.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Basic;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.example.tControl.myObject.WrapperLocalDateTime;


@Entity
@Table( name = "PastEmployees")
public class PastEmployees {
	
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int ID;
    private String fio;
    private String idCard;
    private String tC;
    private String passage;
    @Basic
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime addDateTime;
    @Transient
	private WrapperLocalDateTime wrapperdateTime;
	

	public PastEmployees() {}
	
	public PastEmployees(LocalDateTime addDateTime, String fio, String idCard, String tC, String passage) {
		super();
		this.addDateTime = addDateTime;
		this.wrapperdateTime = (addDateTime != null) ? new WrapperLocalDateTime(addDateTime) : null;
		this.fio = fio;
		this.idCard = idCard;
		this.tC = tC;
		this.passage = passage;
	}
	

	public LocalDateTime getLocalDateTime() {
		return addDateTime;
	}
	public void setDateTimePass(LocalDateTime dateTime) {
		this.addDateTime = dateTime;
	}
	public WrapperLocalDateTime getWrapperLocalDateTime() {
		return wrapperdateTime;
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
	public String getTC() {
		return tC;
	}
	public void setTC(String tC) {
		this.tC = tC;
	}
	public String getPassage() {
		return passage;
	}
	public void setPassage(String passage) {
		this.passage = passage;
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
		PastEmployees other = (PastEmployees) obj;
		if (idCard == null) {
			if (other.idCard != null)
				return false;
		} else if (!idCard.equals(other.idCard))
			return false;
		return true;
	}

	
	
	
	
}
