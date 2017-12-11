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

import com.geog.Model.City;
import com.geog.Model.Country;
import com.geog.Model.Region;

public class DAO {

private DataSource mysqlDS;
private StringBuilder sql;
	
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
	
	
	public ArrayList<Region> loadRegions() throws Exception {
		ArrayList<Region> regions = new ArrayList<Region>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from region";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			Region region = new Region();
			
			// retrieve data from result set row
			region.setcCode(myRs.getString("co_code"));
			region.setRegionCode(myRs.getString("reg_code"));
			region.setRegionName(myRs.getString("reg_name"));
			region.setrDescription(myRs.getString("reg_desc"));
			
			// create new country object
			//Country country = new Country(code, description, name);

			regions.add(region);
		}	
		return regions;
	}
	
	public void addRegion(Region region) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into region values (?, ?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, region.getcCode());
		myStmt.setString(2, region.getRegionCode());
		myStmt.setString(3, region.getRegionName());
		myStmt.setString(4, region.getrDescription());
		myStmt.execute();			
	}
	
	public ArrayList<City> loadCities() throws Exception {
		ArrayList<City> cities = new ArrayList<City>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from city inner join country"
				+ " on country.co_code = city.co_code inner join region on region.reg_code = city.reg_code";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			City city = new City();
			
			// retrieve data from result set row
			city.setCityCode(myRs.getString("cty_code"));
			city.setCouCode(myRs.getString("co_code"));
			city.setrCode(myRs.getString("reg_code"));
			city.setCityName(myRs.getString("cty_name"));
			city.setPopulation(myRs.getLong("population"));
			city.setByTheSea(myRs.getBoolean("isCoastal"));
			city.setArea(myRs.getFloat("areaKM"));
			
			city.setCountryName(myRs.getString("co_name"));
			city.setRegionName(myRs.getString("reg_name"));
			//String countryName = myRs.getString("co_name");
			//String reg_name = myRs.getString("reg_name");
			
			// create new country object
			//Country country = new Country(code, description, name);

			cities.add(city);
		}	
		return cities;
	}
	public void addCity(City city) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into city values (?, ?, ?, ?, ?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, city.getCityCode());
		myStmt.setString(2, city.getCouCode());
		myStmt.setString(3, city.getrCode());
		myStmt.setString(4, city.getCityName());
		myStmt.setLong(5, city.getPopulation());
		myStmt.setString(6, city.isByTheSea() ? "true" : "false");
		myStmt.setFloat(7, city.getArea());
		myStmt.execute();			
	}
	//=========================================
	public ArrayList<City> findCity(City city, String opt) throws Exception{
		ArrayList<City> cityList = new ArrayList<City>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int i = 1;
		
		myConn = mysqlDS.getConnection();

		String test = "select * from city inner join country"
				+ " on country.co_code = city.co_code inner join region"
				+ " on city.reg_code = region.reg_code where isCoastal = ?";
		
		//String test = "SELECT * from city where isCoastal = ?";

		if(city.getCountryName() != "") {
			//System.out.println("CountryName: "+city.getCountryName());

			test += " and city.co_code = ?";

		}
		//System.out.println("1.SQL:::" + test);
		if(city.getPopulation() != 0) {
			if(opt.equals("less")) {

				test += " and population < ?";
			} else if (opt.equals("greater")) {
	
				test += " and population > ?";
			} else if (opt.equals("equal")) {
			
				test += " and population = ?";
			}
		}
		
		myStmt = myConn.prepareStatement(test);
		
		myStmt.setString(1, city.isByTheSea() ? "true" : "false");
		//myStmt.setBoolean(i, city.isByTheSea());
		if(city.getPopulation() != 0) {
			myStmt.setLong(3, city.getPopulation());
		}
		if(!city.getCouCode().equals("")) {
			myStmt.setString(2, city.getCouCode());
		}
		
		System.out.println("Co_code: "+ city.getCouCode()
		+ " isCoastal: " + city.isByTheSea()
		+ " population: " + city.getPopulation() );
		
		System.out.println("SQL:::" + test);
		
		myRs = myStmt.executeQuery();
		
		while (myRs.next()) {
			
			City cityOut = new City();
			
			cityOut.setCityCode(myRs.getString("cty_code"));
			cityOut.setArea(myRs.getFloat("areaKM"));
			cityOut.setByTheSea(myRs.getBoolean("isCoastal"));
			cityOut.setCouCode(myRs.getString("co_code"));
			cityOut.setCityName(myRs.getString("cty_name"));
			cityOut.setPopulation(myRs.getInt("population"));
			cityOut.setrCode(myRs.getString("reg_code"));
			//System.out.println("Before reg_name");
			cityOut.setRegionName(myRs.getString("reg_name"));
			//System.out.println("Before reg_name");
			cityOut.setCountryName(myRs.getString("co_name"));
			
			cityList.add(cityOut);
		} // while
		
		myRs.close();
		myStmt.close();
		test = "";
		
		return cityList;		
		
	}
	

	
	
}
	

