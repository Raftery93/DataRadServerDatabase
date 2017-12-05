package com.geog.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.geog.Model.Country;

public class DAO {

private DataSource mysqlDS;
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/geography";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	
	public ArrayList<Country> loadCountries() throws Exception {
		ArrayList<Country> countries = new ArrayList<Country>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from country";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			Country country = new Country();
			
			// retrieve data from result set row
			country.setCountryCode(myRs.getString("co_code"));
			country.setCountryName(myRs.getString("co_name"));
			country.setDescription(myRs.getString("co_details"));
			
			// create new country object
			//Country country = new Country(code, description, name);

			countries.add(country);
		}	
		return countries;
	}

	public void addCountry(Country country) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into country values (?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, country.getCountryCode());
		myStmt.setString(2, country.getCountryName());
		myStmt.setString(3, country.getDescription());
		myStmt.execute();			
	}
	
	public void deleteCountry(Country country) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		//ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "delete from country where co_code=?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, country.getCountryCode());
		myStmt.execute();
	}

	
	public void updateCountry(Country country) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "UPDATE COUNTRY SET co_name=?, co_details=? WHERE co_code=?";
		
		
		
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(3, country.getCountryCode());
		myStmt.setString(1, country.getCountryName());
		myStmt.setString(2, country.getDescription());
		myStmt.execute();		
		
		
	}
	
}
