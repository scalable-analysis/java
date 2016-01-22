package com.dulishuo.mongo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Opportunity {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		System.out.println("start . . . ");
		
		//test();
		//logo();
		test0115();
		
		long end = System.currentTimeMillis();
		System.out.println("end . . . ");
	}

	private static void test0115() {
		// TODO Auto-generated method stub
		String url = "123.57.250.189";
		//String url = "localhost";
		DB dulishuo = MongoUtil.getConnection(url, "dulishuo");
		DBCollection oppo = dulishuo.getCollection("opportunity");
		DBCursor find = oppo.find();
		
		List<String> ress = new ArrayList<String>();
		StringBuffer tmpstr = new StringBuffer();
		while(find.hasNext()){
			DBObject obj = find.next();
			if(Util.id(obj.get("id").toString()) < 42){
				tmpstr.append(obj.get("introduce").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("zh_introduce").toString().replace("\n",""));
				tmpstr.append("\t");
				/*tmpstr.append(obj.get("title").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("zh_title").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(JSONObject.fromObject(obj.get("type")).get("name").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("facultytype").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("link").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("apply_fee").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("institute").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("zh_institute").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("institute_id").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("cost").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("cost_detail_zh").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("cost_detail").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("fee_url").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("term").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("zh_term").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("introduce").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("zh_introduce").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("deadline").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("zh_deadline").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("requirement").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("zh_requirement").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("benefit").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("zh_benefit").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("attention").toString().replace("\n",""));
				tmpstr.append("\t");
				tmpstr.append(obj.get("zh_attention").toString().replace("\n",""));*/
				
				
				ress.add(tmpstr.toString());
				tmpstr.delete(0, tmpstr.length());
			}
		}
		
		FileUtil.ListToFile(ress, "C:\\Users\\强胜\\Desktop\\gygygygyg.txt");
	}

	private static void logo() {
		// TODO Auto-generated method stub
		//String url = "123.57.250.189";
		String url = "localhost";
		DB dulishuo = MongoUtil.getConnection(url, "dulishuo");
		DBCollection offercase = dulishuo.getCollection("offercase");
		DBCollection institute = dulishuo.getCollection("institute");
		DBCursor find = offercase.find();
		DBCursor ins = institute.find();
		Map<Integer , String> map = new HashMap<Integer , String>();
		int insId;
		while(ins.hasNext()){
			DBObject obj = ins.next();
			if(obj.containsField("logo")){
				insId = Util.id(obj.get("id").toString());
				map.put(insId, JSONObject.fromObject(obj.get("logo").toString()).get("153").toString());
			}
		}
		int flag ;
		while(find.hasNext()){
			DBObject obj = find.next();
			if(obj.containsField("offer_result")){
				JSONArray jona = JSONArray.fromObject(obj.get("result"));
				JSONObject jsss = new JSONObject();
				flag = 0 ;
				Set<Integer> seti = new HashSet<Integer>();
				for(int i = 0 ; i < jona.size(); i++){
					JSONObject js = jona.getJSONObject(i);
					
					seti.add(js.getInt("result"));
					if(js.getInt("result") == 0){
						JSONObject jss = new JSONObject();
						jss.put("id", js.getInt("id"));
						jss.put("result", 0);
						jss.put("zh_name", js.getString("zh_name"));
						jss.put("en_name", js.getString("en_name"));
						jss.put("logo", map.get(js.getInt("id")));
						BasicDBObject newDocument = new BasicDBObject(); 
						newDocument.append("$set", new BasicDBObject().append("offer_result", jss));
						if(offercase.update(obj, newDocument).getN() > 0)
							System.out.println("success");
						break;
					}else if(js.getInt("result") == 1){
						if(flag == 1){
							if(i == jona.size() -1){
								BasicDBObject newDocument = new BasicDBObject(); 
								newDocument.append("$set", new BasicDBObject().append("offer_result", jsss));
								if(offercase.update(obj, newDocument).getN() > 0)
									System.out.println("success");
								
								break;
							}else
								continue;
						}
						
						jsss.put("id", js.getInt("id"));
						jsss.put("result", 1);
						jsss.put("zh_name", js.getString("zh_name"));
						jsss.put("en_name", js.getString("en_name"));
						jsss.put("logo", map.get(js.getInt("id")));
						
						flag = 1;
						
					}
				}
				
			}
			
		}
	}

	private static void test() {
		// TODO Auto-generated method stub
		
		//String url = "123.57.250.189";
		String url = "localhost";
		String dbName = "dulishuo";
		DB dulishuo = MongoUtil.getConnection(url, dbName);
		DBCollection oppo = dulishuo.getCollection("opportunity");
		
		DBCursor cursor = oppo.find();
		List<String> res = new ArrayList<String>();
		
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			
			if(Util.id(obj.get("id").toString()) < 42){
				
				res.add("\""+obj.get("term").toString().replace("\n", "")+"\"");
				res.add("\n");
				
				
			}
			
			FileUtil.ListToFile(res, "C:\\Users\\强胜\\Desktop\\term160119.txt");
		/*	else if(Util.id(obj.get("id").toString()) > 41){
				System.out.println(obj.get("institute_id").toString() );
				BasicDBObject newDocument = new BasicDBObject();  
	    		newDocument.append("$set", new BasicDBObject()
	    				.append("url","")
	    				.append("apply_url", "")
	    				.append("zh_title", obj.get("title").toString())
	    				.append("title","")
	    				.append("zh_introduce", obj.get("introduce").toString())
	    				.append("introduce", "")
	    				.append("zh_benefit", obj.get("benefit").toString())
	    				.append("benefit","")
	    				.append("zh_term", obj.get("term").toString())
	    				.append("term","")
	    				.append("cost_detail_zh", "empty")
	    				.append("cost_detail","empty")
	    				.append("zh_requirement", obj.get("requirement").toString())
	    				.append("requirement","")
	    				.append("institute_id",-1)
	    				.append("institute","")
	    				.append("zh_institute","")
	    				);
				if(oppo.update(obj, newDocument).getN() > 0)
					System.out.println("success");
				else
					System.out.println("fail");
			}*/
		}
	}
}
