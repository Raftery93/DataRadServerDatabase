package com.geog.Model;

import javax.faces.bean.ManagedBean;

@ManagedBean

public class Country {
	
	private String countryCode;
	private String countryName;
	private String description;
	
	public Country() {
		super();
	}	
	
	public Country(String countryCode, String countryName, String description) {
		super();
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.description = description;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}