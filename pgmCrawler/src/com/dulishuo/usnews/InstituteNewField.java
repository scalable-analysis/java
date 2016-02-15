package com.dulishuo.usnews;

import com.dulishuo.util.FileUtil;

import java.util.*;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

public class InstituteNewField {
	static int count  =1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> ins = FileUtil.FileToList("C:/Users/强胜/Desktop/学校皮.csv");
		Map<String,String> mthMap = new HashMap<String,String>();
		for(String xx :ins)
			mthMap.put(xx.split(",")[2], xx.split(",")[1]);
		List<String> rst = new ArrayList<String>();
		List<JSONObject> require = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/usnews/result11.json","utf-8");
		for(JSONObject json : require){
			if(mthMap.containsKey(json.getString("name"))){
				//System.out.println((count++)+"__"+json.getString("name"));
				
				json.put("id", mthMap.get(json.getString("name")));
				mthMap.remove(json.getString("name"));
				rst.add(json.toString());
			}
			
			/*json.put("accept_rate", "");
			json.put("graduation_rate", "");
			json.put("tuition_and_fees", "");
			json.put("total_enrollment", "");
			json.put("average_freshman_retention_rate","");
			json.put("classes_with_fewer_than_20_students", "");
			json.put("SAT/ACT_25th-75th_percentile", "");
			String id = json.getJSONObject("id").getString("$numberLong");*/
			/*for(JSONObject jo : require){
				if(jo.containsKey("id")){
					if(jo.getString("id").equals(id)){
						System.out.println(jo.getString("Fall 2013 acceptance rate"));
						json.put("accept_rate", jo.getString("Fall 2013 acceptance rate"));
						json.put("graduation_rate", jo.getString("6-year graduation rate"));
						json.put("tuition_and_fees", jo.getString("Tuition and Fees"));
						json.put("total_enrollment", jo.getString("Total enrollment"));
						json.put("average_freshman_retention_rate", jo.getString("Average freshman retention rate"));
						json.put("classes_with_fewer_than_20_students", jo.getString("Classes with fewer than 20 students"));
						json.put("SAT/ACT_25th-75th_percentile", jo.getString("SAT/ACT 25th-75th percentile"));
						break;
					}
				}
			}*/
			
		}
		
		for(Entry<String,String> entry : mthMap.entrySet())
			System.out.println(entry.getKey());
		
		FileUtil.ListToFile(rst, "C:/Users/强胜/Desktop/dataCrawler/usnews/result22.json");
		/*List<JSONObject> require = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/usnews/result22.json","utf-8");
		for(JSONObject xx: require){
			if(!xx.containsKey("id"))
				System.out.println((count++)+xx.getString("school"));
		}
		*/
		System.out.println("__end__");
		
	}

}
