package com.geog.Controller;

import java.io.Console;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

import com.geog.DAO.DAO;
import com.geog.Model.Country;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@SessionScoped
@ManagedBean
public class CountryController {
	
	ArrayList<Country> countries;
	private DAO dao;


	public CountryController() throws Exception {
		super();
		countries = new ArrayList<Country>();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CountryController(ArrayList<Country> countries) {
		super();
		this.countries = countries;
	}

	public ArrayList<Country> getCountries() {
		return countries;
	}

	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
	}
	
	
	public void loadCountries() throws Exception {
		countries.clear();
		if (dao != null) {
			try {
				countries = dao.loadCountries();
				System.out.println(countries.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public String addCountry(Country country) {
		if (dao != null) {
			try {
				dao.addCountry(country);
				return "list_countries";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Country ID " + country.getCountryCode() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert Country " + country.getCountryCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}
	
	public String deleteCountry(Country country) {
		if (dao != null) {
			try {
				dao.deleteCountry(country);
				return "index";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Cannot delete Country: " + country.getCountryCode() + " as there are associated Regions");
				//JOptionPane.showMessageDialog(null, message);
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to delete Country " + country.getCountryCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}
	
	public String updateCountry(Country country) {
		if (dao != null) {
			try {
				System.out.println(country);
				dao.updateCountry(country);
				return "list_countries";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Cannot update Country: " + country.getCountryCode() + ", Code must remain the same");
				//JOptionPane.showMessageDialog(null, message);
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to update Country " + country.getCountryCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}

}
// public method get countries.......dao