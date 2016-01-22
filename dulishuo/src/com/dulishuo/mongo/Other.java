package com.dulishuo.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Other {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" . . . start . . .");
		long start = System.currentTimeMillis();
		
		//fansUserinfo();
		//exportWenda();
		//distribute();
		//opportunity();
		
		long end = System.currentTimeMillis();
		System.out.println(" . . . end . . . use time : " + (end - start ) +"ms ");
	
	}

	private static void opportunity() {
		// TODO Auto-generated method stub
		Sheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/呵呵.xls", 0);
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(int i = 0 ; i < 42 ; i++){
			Row row= sht.getRow(i);
			map.put(Util.id(row.getCell(0).toString()), row.getCell(2).toString().trim());
		}
		
		String url = "123.57.250.189";
		//String url = "localhost";
		int port = 27017;
	
		String dbName = "jianzhi";
		DB db = MongoUtil.getConnection(url, port, dbName);
		DBCollection oppo = db.getCollection("opportunity");    
		DBCursor find = oppo.find();
		Set<String> set = new HashSet<String>();
		int count = 1;
		String institute = "";
		String zh_institute = "";
		String link = "";
		int id = -1;
		while(find.hasNext()){
			DBObject obj = find.next();
			id = Util.id(obj.get("id").toString());
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.append("$set", new BasicDBObject().append(
					"title", map.get(id)));
			if (oppo.update(obj, newDocument, false, true).getN() >= 1)
				System.out.println("result :   success");
			else
				System.out.println("result :   fail");	
		}
	}

	private static void distribute() {
		// TODO Auto-generated method stub
		String url = "123.57.250.189";
		//String url = "localhost";
		//String url = "localhost";
		int port = 27017;
	
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		DBCollection fans = db.getCollection("userinfo");    
		DBCursor find = fans.find();
		Set<String> set = new HashSet<String>();
		int count = 1;
		while(find.hasNext()){
			count++;
			if(count % 500 ==0)
				System.out.println("process "+count);
			DBObject obj = find.next();
			if(obj.containsField("openid"))
				set.add(obj.get("openid").toString());
		}
		
		System.out.println("read openid end . . . ");
		
		DBCollection distribute = db.getCollection("distribute");    
		DBCursor update = distribute.find();
		update.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT);
		Set<String> tmp = new HashSet<String>();
		
		int size = 0;
		int flag = 1;
		
		while(update.hasNext()){
			
			DBObject obj = update.next();
			List<String> tmpList = new ArrayList<String>();
			System.out.println("flag  : "+flag++);
			System.out.println(obj.get("title").toString());
			tmp = Util.extractStrArrayToSet(obj.get("openid").toString());
			for(String xx : tmp){
				if(set.contains(xx))
					tmpList.add(xx);
			}
			System.out.println("set : " + tmp.size());
		
			System.out.println("list : " +tmpList.size());
			size = tmpList.size();
			
			if(size > 0){
				String[] updateOpen = (String[])tmpList.toArray(new String[size]);
				
				BasicDBObject newDocument = new BasicDBObject();
				newDocument.append("$set", new BasicDBObject().append(
						"openid", updateOpen));
				if (distribute.update(obj, newDocument, false, true).getN() >= 1)
					System.out.println("result :   success");
				else
					System.out.println("result :   fail");	
			}
			if(size == 0){
				String[] updateOpen = new String[0];
				BasicDBObject newDocument = new BasicDBObject();
				newDocument.append("$set", new BasicDBObject().append(
						"openid", updateOpen));
				if (distribute.update(obj, newDocument, false, true).getN() >= 1)
					System.out.println("result :   success");
				else
					System.out.println("result :   fail");	
			}
			
			
		}
	}

	private static void exportWenda() {
		// TODO Auto-generated method stub
		String url = "123.57.250.189";
		//String url = "localhost";
		int port = 27017;
	
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		DBCollection fans = db.getCollection("wenda");    
		DBCursor find = fans.find();
		
		List<String> list = new ArrayList<String>();
		int count = 1;
		int flag = 1;
		while(find.hasNext()){
			count++;
			if(count % 500 ==0)
				System.out.println("process "+count);
			DBObject obj = find.next();
			if(obj.containsField("type")){
				
				String type = obj.get("type").toString().replace("[", "").replace("]", "").replace(" ", "");
				int size = type.split(",").length;
				
				if(size < 2){
					try{
						int itype = Integer.parseInt(type.split(",")[0].toString());
						if(itype == 1 || itype == 2 || itype == 6){
							JSONObject json = JSONObject.fromObject(obj);
							json.remove("_id");
							json.remove("is_new");
							json.remove("program");
							json.remove("school");
							json.remove("commnet");
							json.remove("pages");
							json.remove("atm");
							json.put("id", flag++);
							list.add(json.toString());
							System.out.println("flag   "+flag);
						}
							
					}catch(Exception e){
						
					}
				}
			}
			
			FileUtil.ListToFile(list, "C:/Users/强胜/Desktop/运营offer.json");
		}
	}

	/*private static void fansUserinfo() {
		// TODO Auto-generated method stub
		String url = "123.57.250.189";
		//String url = "localhost";
		int port = 27017;
	
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		DBCollection fans = db.getCollection("fans");    
		DBCursor find = fans.find();
		
		Set<String> list = new HashSet<String>();
		
		while(find.hasNext()){
			DBObject obj = find.next();
			if(obj.containsField("openid"))
				list.add(obj.get("openid").toString());
		}
		System.out.println("read  end with the set sizing : " + list.size());
		DBCollection userinfo = db.getCollection("userinfo");    
		DBCursor user = userinfo.find();
		
		Set<String> xx = new HashSet<String>();
		int count = 1;
		int count2 = 1;
		Map<String,String> map = new HashMap<String,String>();
		Map<String,Object> mapOpid = new HashMap<String,Object>();
		while(user.hasNext()){
			//System.out.println("process____"+count++);
			DBObject obj = user.next();
			if(obj.containsField("openid")){
				count2++;
				try{
					String openid = obj.get("openid").toString();
					
					if(xx.contains(openid)){
						if(map.get(openid).equals("1")){
							BasicDBObject oldDocument = new BasicDBObject();  
							oldDocument.put("_id",obj.get("_id"));
					    	if(userinfo.remove(oldDocument).getN() >= 1){
					    		System.out.println("success1"+count++);
					    	}
						}else{
							if(!obj.containsField("nickname")){
								BasicDBObject oldDocument = new BasicDBObject();  
								oldDocument.put("_id",obj.get("_id"));
						    	if(userinfo.remove(oldDocument).getN() >= 1){
						    		System.out.println("success2"+count++);
						    	}
							}else{
								map.put(openid, "1");
								BasicDBObject oldDocument = new BasicDBObject();  
								oldDocument.put("_id",mapOpid.get(openid));
						    	if(userinfo.remove(oldDocument).getN() >= 1){
						    		System.out.println("success3"+count++);
						    	}
							}
						}
						
					}else{
						xx.add(openid);
						mapOpid.put(openid, obj.get("_id"));
						if(obj.containsField("nickname"))
							map.put(openid, "1");
						else
							map.put(openid, "0");
					}
					if(list.contains(openid))
						continue;
					else{
						System.out.println(count++);
						BasicDBObject oldDocument = new BasicDBObject();  
						oldDocument.put("_id",obj.get("_id"));
				    	if(userinfo.remove(oldDocument).getN() >= 1){
				    		System.out.println("success");
				    	}
					}
				}catch(Exception e){
					BasicDBObject oldDocument = new BasicDBObject();  
					oldDocument.put("_id",obj.get("_id"));
			    	if(userinfo.remove(oldDocument).getN() >= 1){
			    		System.out.println("success4"+count++);
			    	}
				}
				
			}else{
				BasicDBObject oldDocument = new BasicDBObject();  
				oldDocument.put("_id",obj.get("_id"));
		    	if(userinfo.remove(oldDocument).getN() >= 1){
		    		System.out.println("success4"+count++);
		    	}
			}
		}
		
		System.out.println("xx.size() : "+xx.size());
		System.out.println("count2："+count2);

	}*/
}