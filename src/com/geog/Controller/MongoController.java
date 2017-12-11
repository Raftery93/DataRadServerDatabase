package com.geog.Controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.geog.DAO.MongoDao;
import com.geog.Model.Country;
import com.geog.Model.HeadOfState;
import com.mongodb.MongoClient;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@ManagedBean
@SessionScoped
public class MongoController {
	
	private ArrayList<HeadOfState> state;
	private MongoDao dao;
	
	public MongoController() {
		super();
		state = new ArrayList<HeadOfState>();
		try {
			dao = new MongoDao();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public MongoController(ArrayList<HeadOfState> state){
		super();
		this.state = state;
	}

	public ArrayList<HeadOfState> getHeadsOfState() {
		return state;
	}
	
	public ArrayList<HeadOfState> getState() {
		if(dao != null) {
			try {
				state = dao.loadStates();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return state;
	}
	
	public void setState(ArrayList<HeadOfState> state) {
		this.state = state;
	}
	
	public String addHeadOfState(HeadOfState headOfState) throws Exception{
		
		dao.addHeadOfState(headOfState);	
		return "list_heads";
		
	}
	
	public String deleteHeadOfState(HeadOfState headOfState) {
		
		dao.deleteHeadOfState(headOfState);	
		return "list_heads";
		
	}


}
