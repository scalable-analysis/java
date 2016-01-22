package com.dulishuo.shenqingfang;

import net.sf.json.JSONObject;

import java.util.*;
import java.util.Map.Entry;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class SchoolMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("________start_______");
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/第一次ToJson.json", "utf-8");
		List<JSONObject> sch = FileUtil.FileToJsonList("C:/Users/强胜/ins.json", "utf-8");
		Map<String,Integer> map = new HashMap<String,Integer>();
		Set<String> set = new HashSet<String>();
		List<String> res = new ArrayList<String>();
		for(JSONObject json : sch){
			map.put(json.getString("title"), Util.id(json.get("id").toString()));
		}
		for(JSONObject json : list){
			String title = json.getString("institute");
			System.out.println("process____"+json.getInt("id"));
			json.put("institute_id", -1);
			for(Entry<String, Integer> entry: map.entrySet()){
				if(Util.isSame(title,entry.getKey())){
					json.put("institute_id", entry.getValue());
				}else{
					set.add(title);
				}
			}	
			res.add(json.toString());
		
		}
		for(String xx :set)
			System.out.println(xx);
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/匹配了学校Json.json");
		System.out.println("_______________exit_____________");
	}

}
