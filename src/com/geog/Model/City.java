package com.geog.Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class City {
	
	private String cityCode;
	private String couCode;
	private String rCode;
	private String cityName;
	private String countryName;
	private String regionName;
	private long population;
	private boolean byTheSea;
	private float area;
	
	
	public City() {
		super();
	}


	public City(String cityCode, String couCode, String rCode, String cityName, long population, boolean byTheSea,
			float area) {
		super();
		this.cityCode = cityCode;
		this.couCode = couCode;
		this.rCode = rCode;
		this.cityName = cityName;
		this.population = population;
		this.byTheSea = byTheSea;
		this.area = area;
	}
	
	public City(String cityCode, String couCode, String rCode, String cityName, long population, boolean byTheSea,
			float area, String countryName, String regionName) {
		super();
		this.cityCode = cityCode;
		this.couCode = couCode;
		this.rCode = rCode;
		this.cityName = cityName;
		this.population = population;
		this.byTheSea = byTheSea;
		this.area = area;
		this.countryName = countryName;
		this.regionName = regionName;
	}


	public String getCityCode() {
		return cityCode;
	}


	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}


	public String getCouCode() {
		return couCode;
	}


	public void setCouCode(String couCode) {
		this.couCode = couCode;
	}


	public String getrCode() {
		return rCode;
	}


	public void setrCode(String rCode) {
		this.rCode = rCode;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public long getPopulation() {
		return population;
	}


	public void setPopulation(long population) {
		this.population = population;
	}


	public boolean isByTheSea() {
		return byTheSea;
	}


	public void setByTheSea(boolean byTheSea) {
		this.byTheSea = byTheSea;
	}


	public float getArea() {
		return area;
	}


	public void setArea(float area) {
		this.area = area;
	}	
	
	public void setCountryName(String name) {
		this.countryName = name;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setRegionName(String name) {
		this.regionName = name;
	}
	
	public String getRegionName() {
		return this.regionName;
	}

}