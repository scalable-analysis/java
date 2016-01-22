package com.dulishuo.mongo;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;


public class MongoUtil {
	static Logger log = Logger.getLogger(MongoUtil.class);
	public static DB getConnection(String url, String dbName){
		Mongo mongo = null;
		try {
			mongo = new Mongo(url, 27017);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		DB db = mongo.getDB(dbName);
		if(!url.equals("localhost"))
			db.authenticate("dulishuo", "Dulishuo123".toCharArray());
		return db;		
	}
	
	public static void insert(List<String> record){
		String url = "123.57.250.189";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = getConnection(url,dbName);
		
		DBCollection wenda = db.getCollection("wenda");
		for(String each : record){
			DBObject dbObject = (DBObject) JSON.parse(each);
			wenda.insert(dbObject);
		}
		log.info("all record hava push to mongo-dulishuo.wenda!");
	}
}
