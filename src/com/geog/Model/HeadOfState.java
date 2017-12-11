package com.geog.Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class HeadOfState {
	
	private String _id;
	private String headOfState;
	
	public HeadOfState() {
		
	}

	public HeadOfState(String id, String headOfState) {
		super();
		this._id = id;
		this.headOfState = headOfState;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String id) {
		this._id = id;
	}

	public String getHeadOfState() {
		return headOfState;
	}

	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}
}