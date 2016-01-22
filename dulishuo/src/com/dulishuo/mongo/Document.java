package com.dulishuo.mongo;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.dulishuo.util.FileUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Document {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		facTypeChange();
	}

	private static void facTypeChange() {
		// TODO Auto-generated method stub
		
		Sheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/中秋版本更新/facultytype.xls",0);
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		int count = 0;
		for(int i = 0 ; i < 102; i++){
			int value = Integer.parseInt(sht.getRow(i).getCell(1).toString().replace(".0", ""));
			if(value > -1)
				map.put(count, value);
			count++;
		}
		
		//String url = "localhost";
		String url = "123.57.250.189";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		
		DBCollection offer = db.getCollection("document");    
	    DBCursor find = offer.find();
	    
	    int flag = 1;
	    while(find.hasNext()){
	    	DBObject obj = find.next();
	    	int id = Integer.parseInt(obj.get("facultytype_id").toString());
	    	if( id > -1){
	    		if(map.containsKey(id)){
	    			BasicDBObject newDocument = new BasicDBObject();  
			    	newDocument.append("$set", new BasicDBObject().append("facultytype_id", map.get(id)));  
			    	offer.update(obj, newDocument);
	    		}else{
	    			BasicDBObject newDocument = new BasicDBObject();  
			    	newDocument.append("$set", new BasicDBObject().append("facultytype_id", -1));  
			    	offer.update(obj, newDocument);
	    		}
	    	}
	    }
	    
	    System.out.println("____________----Exit----------");
	}
	
	

}
