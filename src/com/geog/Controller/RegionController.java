package com.geog.Controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.geog.DAO.DAO;
import com.geog.Model.Region;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@SessionScoped
@ManagedBean
public class RegionController {
	
	ArrayList<Region> regions;
	private DAO dao;


	public RegionController() throws Exception {
		super();
		regions = new ArrayList<Region>();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RegionController(ArrayList<Region> regions) {
		super();
		this.regions = regions;
	}

	public ArrayList<Region> getRegions() {
		return regions;
	}

	public void setRegions(ArrayList<Region> regions) {
		this.regions = regions;
	}
	
	
	public void loadRegions() throws Exception {
		regions.clear();
		if (dao != null) {
			try {
				regions = dao.loadRegions();
				System.out.println(regions.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public String addRegion(Region region) {
		if (dao != null) {
			try {
				dao.addRegion(region);
				return "index";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Region ID " + region.getRegionCode() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert Country " + region.getRegionCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}
	

}
