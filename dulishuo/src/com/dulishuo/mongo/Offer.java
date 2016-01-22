package com.dulishuo.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class Offer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" . . . start . . .");
		long start = System.currentTimeMillis();

		// offerLanguageStatic();
		// updateInstitute();
		//tmp1219();
		//addShuigeOffer();
		
		test1223();

		long end = System.currentTimeMillis();
		System.out.println(" . . . end . . . use time : " + (end - start)
				+ "ms ");
	}

	private static void test1223() {
		// TODO Auto-generated method stub
		

		
		String url = "123.57.250.189";
		//String url = "localhost";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);

		DBCollection offer = db.getCollection("offercase");
		
		DBCursor find = offer.find();
		Set<String> set = new HashSet<String>();
		String xx;
		int flag = 1;
		while (find.hasNext()) {
			DBObject obj = find.next();
			try{
				if(obj.get("major").toString().contains("["))
					continue;
			JSONObject result = JSONObject.fromObject(obj.get("major").toString());
			List<JSONObject> lll = new ArrayList<JSONObject>();
			Iterator itt = result.keys();
			while(itt.hasNext()){
				String idd = (String) itt.next();
				JSONObject jso = result.getJSONObject(idd);
				jso.put("id", Util.id(idd));
				
				lll.add(jso);
			}
			JSONObject[] tttt = new JSONObject[lll.size()];
			for(int i = 0 ; i < lll.size() ; i++){
				tttt[i] = lll.get(i);
			}
				
			
			BasicDBObject newDocument = new BasicDBObject();
			
			newDocument.append("$set",new BasicDBObject().append("major",tttt));
			if(offer.update(obj, newDocument).getN() > 0)
				System.out.println("success");
			
			}catch(Exception e){
				System.out.println(obj.get("department_type").toString());
			}
			
		}
	}

	private static void addShuigeOffer() {
		// TODO Auto-generated method stub
	    String url = "123.57.250.189";
		//String url = "localhost";
		int port = 27017;
		String dbName = "jianzhi";
		DB db = MongoUtil.getConnection(url, port, dbName);

		DBCollection offer = db.getCollection("jianzhioffer");
		DBObject object = offer.findOne(new BasicDBObject("id",24205));
		
		JSONObject tag = JSONObject.fromObject(object.get("tag").toString());
		
		String[] xx = new String[4];
		int flag = Util.id(object.get("flag").toString());
		xx[0] = "科研能力强";
		xx[1] = "省级实验室/项目";
		xx[2] = "校级竞赛";
		xx[3] = "国内核心二作";
		
		tag.remove("inner");	
		tag.put("inner", xx);
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject("tag", tag));
		
		if (offer.update(new BasicDBObject("flag",flag), newDocument, false, true).getN() >= 1)
			System.out.println("result :   success");
		else
			System.out.println("result :   fail");
		
	}

	private static void tmp1219() {
		// TODO Auto-generated method stub

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Edu", 36);
		map.put("Physics", 33);
		map.put("Earth", 44);
		map.put("Envir", 1945);
		map.put("CS", 38);
		map.put("Material", 10);
		map.put("ME", 6);
		map.put("IEOR", 57);
		map.put("Math/AppliedMath", 37);
		map.put("MIS", 52);
		map.put("Econ/Biz", 27);
		map.put("CE", 54);
		map.put("EE", 40);
		map.put("CivilEng", 51);
		map.put("Bio", 14);
		 String url = "123.57.250.189";
		//String url = "localhost";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);

		DBCollection offer = db.getCollection("offer");
		DBCursor find = offer.find();
		
		while (find.hasNext()) {
			DBObject obj = find.next();
			if (obj.containsField("fac")) {
				if (map.containsKey(obj.get("fac").toString())) {
					
					if(obj.containsField("department_type")){
						
						if (obj.get("department_type").toString().length() > 5 ) {
							BasicDBObject newDocument = new BasicDBObject();
							if(obj.get("fac").equals("Envir")){
								int[] dep = new int[2];
								dep[0] = 19;
								dep[1] = 45;
								newDocument.append("$set",new BasicDBObject().append("department_type",dep));
							}else{
								int[] dep = new int[1];
								dep[0] = map.get(obj.get("fac"));
								newDocument.append("$set",new BasicDBObject().append("department_type",dep));
								
							}
							if (offer.update(obj, newDocument, false, true).getN() >= 1)
								System.out.println("result :   success");
							else
								System.out.println("result :   fail");
							//System.out.println(obj.get("department_type"));
						}
					}
				}
			}
		}

		
	}

	private static void updateInstitute() {
		// TODO Auto-generated method stub
		// String url = "123.57.250.189";
		String url = "localhost";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);

		DBCollection offer = db.getCollection("offer");
		DBCursor find = offer.find();
		while (find.hasNext()) {
			DBObject obj = find.next();
			if (obj.containsField("institute_id")) {

				BasicDBObject newDocument = new BasicDBObject();
				newDocument.append("$set",
						new BasicDBObject().append("institute", Util
								.getSchName(Util.id(obj.get("institute_id")
										.toString()), 2)));
				// newDocument.append("$set", new BasicDBObject().append(
				// "tinstitute",
				// Util.getSchName(Util.id(obj.get("institute_id").toString()),
				// 1)));
				if (offer.update(obj, newDocument, false, true).getN() >= 1)
					System.out.println("result :   success");
				else
					System.out.println("result :   fail");
			}
		}
	}

	private static void offerLanguageStatic() {
		// TODO Auto-generated method stub
		// String url = "123.57.250.189";
		String url = "localhost";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);

		DBCollection rank = db.getCollection("offer");
		DBCursor find = rank.find();
		while (find.hasNext()) {
			DBObject obj = find.next();
		}
	}

}
