package com.dulishuo.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthTest();
		//tmp();
	}

	private static void tmp() {
		// TODO Auto-generated method stub
		List<JSONObject> file = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/jianzhioffer.json", "utf-8");
		
		Set<String> hehe = new HashSet<String>();
		for(JSONObject json : file){
			if(json.containsKey("marker"))
				hehe.add(json.getString("marker"));
		}
		for(String xx : hehe)
			System.out.println(xx);
	}

	@SuppressWarnings("deprecation")
	private static void AuthTest() {
		// TODO Auto-generated method stub
		try{
			List<MongoCredential> mongoCredentialList = new ArrayList<MongoCredential>();
			mongoCredentialList.add(MongoCredential.createMongoCRCredential("dulishuo", "dulishuo", "Dulishuo123".toCharArray()));
			ServerAddress serverAddress = new ServerAddress("123.57.250.189", 27017); 
			MongoClient mongoClient = new MongoClient(serverAddress, mongoCredentialList); 
		
			
			
			//String sURI = String.format("mongodb://%s:%s@%s:%d/%s", "admin", "Dulishuo123", "123.57.22.27", 27017, "admin"); 
			//MongoClientURI uri = new MongoClientURI(sURI); 
			//MongoClient mongoClient = new MongoClient(uri); 
			
			
			DB db = mongoClient.getDB("dulishuo");
			DBCollection coll = db.getCollection("test");
			System.out.println(coll.findOne().toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	    
	}

}
