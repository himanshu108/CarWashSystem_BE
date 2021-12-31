package com.ondemandcarwash.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="address")
public class Address {
	@Id
	private int userId;
	private int houseNumber;
	private String streetName;
	private String colonyName;
	private String city;
	private String state;
	private int pincode;
	
	
	@Override
	public String toString() {
		return "Address [userId=" + userId + ", houseNumber=" + houseNumber + ", streetName=" + streetName
				+ ", colonyName=" + colonyName + ", city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
	}
	public Address() {
		
	}
	public Address(int userId, int houseNumber, String streetName, String colonyName, String city, String state, int pincode) {
		super();
		this.userId=userId;
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.colonyName = colonyName;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getColonyName() {
		return colonyName;
	}
	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
}