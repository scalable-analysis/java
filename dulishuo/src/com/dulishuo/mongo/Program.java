package com.dulishuo.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.dulishuo.util.Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" . . . start . . .");
		long start = System.currentTimeMillis();
		
		//removeCopies();
		//processAttention();
		addApplication_condition();
		//spec_major_process();
		
		long end = System.currentTimeMillis();
		System.out.println(" . . . end . . . use time : " + (end - start ) +"ms ");
	
	}
	
	private static void spec_major_process() {
		// TODO Auto-generated method stub
		String url = "123.57.250.189";
		//String url = "localhost";
		int port = 27017;
	
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		DBCollection institute = db.getCollection("program");    
		DBCursor find = institute.find();
		
		int count = 1;
		while(find.hasNext()){
			DBObject obj = find.next();
			if(obj.containsField("title")){
				String title = obj.get("title").toString().toLowerCase();
				if(title.indexOf("tesol") != -1){
					JSONObject json = JSONObject.fromObject(obj.toString());
					String depStr =  json.get("department_type").toString().replace("[", "").replace("]", "").replace(" ", "");
					
					
					if(depStr.length() < 1){
						int[] dep = new int[1];
						dep[0] = 68;
						BasicDBObject newDocument = new BasicDBObject();  
				    	newDocument.append("$set", new BasicDBObject("department_type",dep));  
				    	if(institute.update(obj, newDocument,false,true).getN() >= 1){
				    		System.out.println("success");
				    	}
					}else{
						try{
						int len = depStr.split(",").length;
						int[] dep = new int[len+1];
						for(int i = 0 ; i < len ; i++)
							dep[i] = Integer.parseInt(depStr.split(",")[i]);
						dep[len] = 68;
						
						BasicDBObject newDocument = new BasicDBObject();  
				    	newDocument.append("$set", new BasicDBObject("department_type",dep));  
				    	if(institute.update(obj, newDocument,false,true).getN() >= 1){
				    		System.out.println("success");
				    	}
						}catch(Exception e){
							System.out.println(obj.get("id") + "\t" + depStr);
							
							
						}
					}
				}
			}
			/*if(obj.containsField("subject")){
				String subject = obj.get("subject").toString().toLowerCase();
				
				if(subject.indexOf("journalism") != -1 || subject.indexOf("relation") != -1)
					System.out.println(count++ + "\t" + subject);
			}*/
		}
	}

	private static void addApplication_condition() {
		// TODO Auto-generated method stub
		
		long start = System.currentTimeMillis();
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("7", "accounting");
		map.put("27", "economics");
		map.put("43", "law");
		map.put("61", "finance");
		map.put("47", "marketing");
		map.put("66", "journalism");
		map.put("67", "pr");
		map.put("68", "tesol");
		
		
		
				
		String url = "123.57.250.189";
		//String url = "localhost";
		int port = 27017;
		
		//读取institute库中的application_difficulty字段
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		DBCollection institute = db.getCollection("institute");    
		DBCursor find = institute.find();
		
		Map<Integer,DBObject> app = new HashMap<Integer,DBObject>();
		List<String> fail = new ArrayList<String>();  //失败记录
		while(find.hasNext()){
			DBObject obj = find.next();
			if(obj.containsField("application_difficulty"))
				app.put(Util.id(obj.get("id").toString()), ((DBObject) obj.get("application_difficulty")));
		}
		System.out.println("end : read fields from institute db . . . ");
		System.out.println("the amount of school which has app_diff is :  "+app.size());
		
		//为program库增加application_difficulty字段
		DBCollection program = db.getCollection("program");    
		DBCursor pgm = program.find();
		int count = 0;
		int flag = 0;
		while(pgm.hasNext()){
			
			DBObject obj = pgm.next();
			flag++;
			if(flag % 500 == 0)
				System.out.println("process  "+flag);
			
			try{
				if(obj.containsField("institute_id") && obj.containsField("department_type") &&  app.containsKey(Util.id(obj.get("institute_id").toString()))){					
					String id = obj.get("id").toString();
					DBObject pgmApp = app.get(Util.id(obj.get("institute_id").toString()));
					String[] facTypes = obj.get("department_type").toString().replace(".0","").replace("[", "").replace("]", "").split(",");

					if(facTypes.length > 1){
						
						for(int i = 0 ; i < facTypes.length ; i++ ){
							String fac = facTypes[i].trim();
							
							if(map.containsKey(fac)){
								Map<String , JSONObject> mapTmp = new HashMap<String, JSONObject>();
								if(pgmApp.containsField(map.get(fac))){
									JSONObject json = JSONObject.fromObject(pgmApp.get(map.get(fac)));
								//	System.out.println("id:  "+id);
									mapTmp.put(map.get(fac), json);
								}
								if(mapTmp.size() > 0){
									BasicDBObject newDocument = new BasicDBObject();
									for(String xx : mapTmp.keySet())
										newDocument.append("$set", new BasicDBObject().append(xx, mapTmp.get(xx)));  
							    	if(program.update(obj, newDocument,false,true).getN() >= 1){
							    		System.out.println("多个    "+obj.get("id"));
							    		System.out.println("result :   success");
							    		count++;
							    	}
							    	else{
							    		System.out.println("result :   fail");
							    		fail.add("error	id	: "+obj.get("id").toString());
							    	}
								}
								
							}
						}
					}
					else{
						
						String fac = facTypes[0].trim();
						
						if(map.containsKey(fac)){
							if(pgmApp.containsField(map.get(fac))){
								
								JSONObject json = JSONObject.fromObject(pgmApp.get(map.get(fac)));
							//	System.out.println("id:  "+id);
								BasicDBObject newDocument = new BasicDBObject();  
								newDocument.append("$set", new BasicDBObject().append(map.get(fac), json));  
						    	if(program.update(obj, newDocument,false,true).getN() >= 1){
						    		System.out.println("单个    "+obj.get("id"));
						    		System.out.println("result :   success");
						    		count++;
						    	}
						    	else{
						    		System.out.println("result :   fail");
						    		fail.add("error	id	: "+obj.get("id").toString());
						    	}
							}
						}
					}
			    	
				}
			}catch(Exception e){
				fail.add("error	id	: "+obj.get("id").toString());
			}
			
		}
		
		//更新记录打印
		System.out.println("----------------------------------------");
		System.out.println("total amount of updated records  ");
		System.out.println("\t"+count);
		if(fail.size() > 0){
			
			System.out.println("-------------");
			System.out.println("error records line ");
			for(String xx : fail)
				System.out.println(xx);
		}
		System.out.println("----------------------------------------");
		
		long end = System.currentTimeMillis();
		System.out.println("end ... use time : " + (end-start) +"ms!");
	}

	private static void processAttention() {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		String url = "123.57.250.189";
		//String url = "localhost";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		
		DBCollection wenda = db.getCollection("program");
	    DBCursor update = wenda.find();
	    while(update.hasNext()){
	    	DBObject obj = update.next();
	    	if(obj.containsField("attention")){
	    		BasicDBObject newDocument = new BasicDBObject(); 
	    		newDocument.append("$set", new BasicDBObject().append("attention", getAttention(obj.get("attention").toString())));  
	    		wenda.update(obj, newDocument); 
	    	}
	    }
	    
	    long end = System.currentTimeMillis();
	    System.out.println("end..............");
	    System.out.println("use time :"+(end-start)/1000+"seconds");
	}

	public static void removeCopies(){
		long start = System.currentTimeMillis();
		String url = "localhost";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		
		DBCollection wenda = db.getCollection("program");
		
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
	    
	    
	    long end = System.currentTimeMillis();
	    System.out.println("end..............");
	    System.out.println("use time :"+(end-start)/1000+"seconds");
	}
	
	private static String getAttention(String transcript){
		Document xx = Jsoup.parse(transcript);
		try{
			String tmp = xx.text().toString();
			//返回全部信息-（残缺信息跟全部信息都放在一起）
			if(tmp.indexOf("显示全部") != -1){
				String reg = tmp.substring(0, 20);
				tmp = reg+tmp.split(reg)[1];
				if(tmp.indexOf("项目特别要求:") != -1){
					tmp = tmp.replace("项目特别要求:", "项目特别要求:<br>").replace("描述:", "<br>描述:<br>:");
					return tmp;
				}else{
					if(tmp.indexOf("https://www.grad....") == -1)
						return tmp;
					else
						return "";
				}
			}else{
				if(tmp.indexOf("项目特别要求:") != -1){
					tmp = tmp.replace("项目特别要求:", "项目特别要求:<br>").replace("描述:", "<br>描述:<br>:");
					return tmp;
				}else{
					if(tmp.indexOf("https://www.grad....") == -1)
						return tmp;
					else
						return "";
				}
			}
		}catch(Exception e){
			return "";
		}
	}

}
