package com.geog.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.geog.Model.Country;
import com.geog.Model.HeadOfState;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDao {
	
	private MongoClient mongoC;
	private MongoDatabase data;
	
	public MongoDao() {
		mongoC = new MongoClient();
		data = mongoC.getDatabase("headsOfStateDB");
	}
	
	
	public ArrayList<HeadOfState> loadStates() throws Exception{
		MongoCollection<Document> MongDoc = data.getCollection("headsOfState");
		ArrayList<HeadOfState> headList = new ArrayList<HeadOfState>();
		FindIterable<Document> dockable = MongDoc.find();
		Gson gson = new Gson();
		
		for(Document docs:dockable){
			HeadOfState hos = gson.fromJson(docs.toJson(), HeadOfState.class);
			headList.add(hos);
		}		
		return headList;	
	}
	
	
	public void addHeadOfState(HeadOfState headOfState) {
		
		final MongoCollection<Document> headsCollec = data.getCollection("headsOfState");
		final Document doc = new Document();
		doc.append("_id", headOfState.get_id());
		doc.append("headOfState", headOfState.getHeadOfState());
		headsCollec.insertOne(doc);

	}
	
	public void deleteHeadOfState(HeadOfState headOfState) {
		
		final MongoCollection<Document> headsCollec = data.getCollection("headsOfState");
		headsCollec.deleteOne(new Document("_id", headOfState.get_id()));
	
	}
		
}