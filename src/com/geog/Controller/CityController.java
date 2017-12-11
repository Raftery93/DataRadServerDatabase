package com.geog.Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.geog.DAO.DAO;
import com.geog.Model.City;
import com.geog.Model.Country;
import com.geog.Model.Region;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@SessionScoped
@ManagedBean
public class CityController {
	
	ArrayList<City> cities;
	ArrayList<City> cityOut;
	private DAO dao;
	
	private City city;
	private Region region;
	private Country country;
	private String popequallessgreater;


	public CityController() throws Exception {
		super();
		cities = new ArrayList<City>();
		cityOut = new ArrayList<City>();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CityController(ArrayList<City> cities, ArrayList<City> cityOut) {
		super();
		this.cityOut = cityOut;
		this.cities = cities;
	}

	public ArrayList<City> getCityOut() {
		return cityOut;
	}

	public void setCityOut(ArrayList<City> cityOut) {
		this.cityOut = cityOut;
	}

	public ArrayList<City> getCities() {
		return cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}
	
	
	public void loadCities() throws Exception {
		cities.clear();
		if (dao != null) {
			try {
				cities = dao.loadCities();
				System.out.println(cities.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public String addCity(City city) {
		if (dao != null) {
			try {
				dao.addCity(city);
				return "list_cities";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error attempting to add City: " + city.getCityCode() + ",Region: " + city.getrCode() + ", Country: " + city.getCouCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert Country " + city.getCityCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	
	
	
	public String showDetails(City city) throws SQLException{
		this.city = city;
		
		//this.country = Country.getCountryCode();
		//this.region = City.getrCode();
		
		return "city_details.xhtml";
		
	}
	
	
	public String getPopequallessgreater() {
		return popequallessgreater;
	}

	public void setPopequallessgreater(String popequallessgreater) {
		this.popequallessgreater = popequallessgreater;
	}

	
	
	public String findCity(City city) throws Exception {
		

	
		cityOut.clear();
		
		try {
			this.city = city;
			cityOut = dao.findCity(city, getPopequallessgreater());
			//System.out.println("P=>" + city.toString());		
		} catch (Exception e) {
			System.out.println("Error::::::" + e);
			FacesMessage message = new FacesMessage("Error: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}	
		return "viewCity.xhtml";
	}
	
	
	

}
