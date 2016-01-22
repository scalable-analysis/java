package com.dulishuo.mongo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class OfferRank {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . ");
		long start = System.currentTimeMillis();
		//rankOffer();
		//statisticOffer();\
		//displayOffer();
		//idReplace();
		update();
		//randName();
		long end = System.currentTimeMillis();
		System.out.println("end use time : "+ (end-start)+" ms .");
	}

	private static void update() {
		// TODO Auto-generated method stub
		String url = "123.57.250.189";
		//String url = "localhost";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		
		DBCollection offer = db.getCollection("offer");    
	    DBCursor find = offer.find();
	    String applicant = "";
	    List<String> names =  Arrays.asList("赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "楮", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章");
	    int res = 0;
		int flag = 1;
		Random rand = new Random();
		
	    while(find.hasNext()){
	    	DBObject obj = find.next();
	    	System.out.println("process  record line  :  "+count++);
	    	try{
	    		/*if(obj.containsField("institute_id")){
		    		int ins_id = Util.id(obj.get("institute_id").toString());
		    		if(ins_id != -1){
		    			String tinstitute = Util.getSchName(ins_id, 2);
		    			BasicDBObject newDocument = new BasicDBObject();  
						newDocument.append("$set", new BasicDBObject().append("tinstitute", tinstitute));  
						offer.update(obj, newDocument);
		    		}
		    	}*/
	    		
	    		if(flag == 1)
					res = rand.nextInt(5);
				else if(flag == 2)
					res = rand.nextInt(10);
				else if(flag == 3)
					res = rand.nextInt(17);
				else if(flag == 4)
					res = rand.nextInt(27);
				else if(flag == 5)
					res = rand.nextInt(39);
				flag++;
			
				if(flag == 5)
					flag = 1;
				//钟xx
				applicant = names.get(res)+"XX";
	    		BasicDBObject newDocument = new BasicDBObject();  
	    		
				newDocument.append("$set", new BasicDBObject().append("applicant", applicant));  
				offer.update(obj, newDocument);
	    	}catch(Exception e){
	    		
	    	}
	    }
	}
	
	static String randName(){
		List<String> names =  Arrays.asList("赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "楮", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章");
		
		Random rand = new Random(10);
		int i = 0  ;
		int res = 0;
		int flag = 1;
		while(i < 1000){
			i++;
			if(flag == 1)
				res = rand.nextInt(5);
			else if(flag == 2)
				res = rand.nextInt(10);
			else if(flag == 3)
				res = rand.nextInt(17);
			else if(flag == 4)
				res = rand.nextInt(27);
			else if(flag == 5)
				res = rand.nextInt(39);
			flag++;
			if(flag == 5)
				flag = 1;
			System.out.println(names.get(res));
		}
		return "" ;
	}

	private static void idReplace() {
		// TODO Auto-generated method stub
		List<String> list = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/过时的program取代表1013.txt");
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(String xx : list)
			map.put(xx.split("\t")[0], Integer.parseInt(xx.split("\t")[1]));
		
		String url = "123.57.250.189";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		
		DBCollection offer = db.getCollection("offer");    
	    DBCursor find = offer.find();
	    
	    while(find.hasNext()){
	    	System.out.println("process_____"+count++);
	    	DBObject obj = find.next();
	    	if(obj.containsField("program_id")){
	    		String id = obj.get("program_id").toString();
		    	if(map.containsKey(id)){
		    		try{
			    		
			    		BasicDBObject newDocument = new BasicDBObject();  
						newDocument.append("$set", new BasicDBObject().append("program_id", map.get(id)));  
						offer.update(obj, newDocument);
			    		
			    	}catch(Exception e){
			    		
			    	}
		    	}
	    	}
	    }
	    System.out.println("______________---End---------------");
	}

	private static void displayOffer() {
		// TODO Auto-generated method stub
		//String url = "localhost";
		String url = "123.57.250.189";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		
		DBCollection offer = db.getCollection("offer");    
	    DBCursor find = offer.find();
	    
	    while(find.hasNext()){
	    	//System.out.println("process_____"+count++);
	    	DBObject obj = find.next();
	    	String toefl_total = ((DBObject)obj.get("toefl")).get("total").toString();
	    	String gre_total = ((DBObject)obj.get("gre")).get("total").toString();
	    	String gpa = obj.get("gpa").toString();
	    	try{
	    		if(Float.parseFloat(gpa) > 2){
	    			if(Integer.parseInt(gre_total) > 200){
	    				if(Integer.parseInt(toefl_total) > 60){
	    					BasicDBObject newDocument = new BasicDBObject();  
					    	newDocument.append("$set", new BasicDBObject().append("is_valid", 1));  
					    	offer.update(obj, newDocument);
	    				}
	    			}
	    		}
	    	}catch(Exception e){
	    		
	    	}
	    	
	    }
	    System.out.println("______________---End---------------");
	}

	private static void statisticOffer() {
		// TODO Auto-generated method stub
		String url = "localhost";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		
		DBCollection offer = db.getCollection("offer");    
	    DBCursor find = offer.find();
	    
	    while(find.hasNext()){
	    	
	    }
	}

	private static void rankOffer() {
		// TODO Auto-generated method stub
		List<JSONObject> pgmRank = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program55.json", "utf-8");
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(JSONObject json : pgmRank){
			if(json.containsKey("sch_rank") && json.containsKey("pgm_rank")){
				map.put(json.getInt("id"), json.get("sch_rank").toString()+"!"+json.get("pgm_rank").toString());
				//System.out.println(json.getInt("sch_rank")+"!"+json.getInt("pgm_rank"));
			}
		}
		//String url = "123.57.250.189";
		String url = "localhost";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		
		DBCollection offer = db.getCollection("newoffer");    
	    DBCursor update = offer.find();
	    
	    while(update.hasNext()){
	    	System.out.println("process_____"+count++);
	    	DBObject obj = update.next();
	    	if(!obj.containsField("school_rank") && !obj.containsField("program_rank")){
	    		if(obj.containsField("program_id")){
		    		int pgm_id = Integer.parseInt(obj.get("program_id").toString());
			    	if(pgm_id != -1){
			    		//如果匹配到的program_id，且不为-1，且存在专排与综排 
			    		if(map.containsKey(pgm_id)){
			    			String rank = map.get(pgm_id);
			    			try{
			    				int sch_rank = Integer.parseInt(rank.split("!")[0].toString());
				    			int pgm_rank = Integer.parseInt(rank.split("!")[1].toString());
				    			BasicDBObject newDocument = new BasicDBObject();  
						    	newDocument.append("$set", new BasicDBObject().append("school_rank", sch_rank).append("program_rank", pgm_rank));  
						    	offer.update(obj, newDocument);
			    			}catch(Exception e){
			    				System.out.println(rank);
			    				return;
			    			}
			    		}
			    		//如果匹配到的program_id，且不为-1，但不存在排名，则设为999
			    	}
		    	}
	    	}
	    }
	    System.out.println("____end_____");
	}

}
