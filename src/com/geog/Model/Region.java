package com.geog.Model;

import javax.faces.bean.ManagedBean;

@ManagedBean

public class Region {
	
	private String cCode;
	private String regionCode;
	private String regionName;
	private String rDescription;
	
	public Region() {
		super();
	}	
	
	public Region(String cCode, String regionCode, String countryName, String description) {
		super();
		this.cCode = cCode;
		this.regionCode = regionCode;
		this.regionName = countryName;
		this.rDescription = description;
	}
	
	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getrDescription() {
		return rDescription;
	}
	public void setrDescription(String rDescription) {
		this.rDescription = rDescription;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

}