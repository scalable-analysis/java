package com.dulishuo.mongo;

import net.sf.json.JSONObject;

import com.dulishuo.util.Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class Wenda {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insertId();
	}

	public static void insertId(){
		String url = "123.57.250.189";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		
		DBCollection wenda = db.getCollection("wenda");
		
	    DBCursor cur = wenda.find();

	    int max = 1;
	    while (cur.hasNext()) {

	        DBObject obj = cur.next();
	        if(obj.containsField("id")){
	        	int id = Util.id(obj.get("id").toString());
		        max = max > id ? max : id;
		       // System.out.println((count++)+"\t"+id);
	        }

	    }
	    
	    System.out.println("the biggest id is :"+max);
	    
	    DBCursor update = wenda.find();
	    while(update.hasNext()){
	    	DBObject obj = update.next();
	    	if(!obj.containsField("id")){
	    		BasicDBObject newDocument = new BasicDBObject();  
	    		newDocument.append("$set", new BasicDBObject().append("id", ++max));  
	    		  
	    	 
	    		  
	    		wenda.update(obj, newDocument); 
	    		System.out.println("update___"+max);
	    	}
	    }
	    
	    
	    
	    System.out.println("____end_____");
	}
}
