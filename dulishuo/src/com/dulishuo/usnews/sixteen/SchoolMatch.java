package com.dulishuo.usnews.sixteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class SchoolMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> uni = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/usnews/2016/全球高校综合排名/uni1008.json", "utf-8");
		List<JSONObject> ins = FileUtil.FileToJsonList("C:/Users/强胜/ins.json", "utf-8");
		List<String> res= new ArrayList<String>();
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(JSONObject json : ins){
			map.put(json.getString("title"),Util.id(json.get("id").toString()));
		}
		
		for(JSONObject json : uni){
			String institute = json.getString("institute");
			json.put("institute_id", -1);
			for(String key : map.keySet()){
				if(Util.isSame(institute,key)){
					json.put("institute_id", map.get(key));
					break;
				}
			}
			if(json.getInt("value") < 200 && json.getInt("institute_id") == -1)
				System.out.println(json.getInt("value")+"\t"+institute+"\t");
			res.add(json.toString());
		}
		
		FileUtil.ListToFile(res,"C:/Users/强胜/Desktop/dataCrawler/usnews/2016/全球高校综合排名/uni1008001.json");
		System.out.println("___________________----Exit------");
	}

}
