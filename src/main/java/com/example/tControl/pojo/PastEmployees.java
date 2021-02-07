package com.example.tControl.pojo;

public class PastEmployees {

	private String dateTime;
	private String fio;
	private String idCard;
	private String tC;
	private String passage;
	
	
	public PastEmployees() {}
	
	public PastEmployees(String dateTime, String fio, String idCard, String tC, String passage) {
		super();
		this.dateTime = dateTime;
		this.fio = fio;
		this.idCard = idCard;
		this.tC = tC;
		this.passage = passage;
	}
	
	public String getDateTimePass() {
		return dateTime;
	}
	public void setDateTimePass(String personnelNumber) {
		this.dateTime = personnelNumber;
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
