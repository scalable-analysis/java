package com.dulishuo.usnews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class SixFacultyRanking {

	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> src = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/usnews/每个学院的每个faculty的东西/96个匹配到的学校.json","utf-8");
		List<String> result = new ArrayList<String>();
		for(JSONObject json : src){
			System.out.println("process____"+(count++));
			Set<String> set = json.keySet();
			Map<String,Object> map = new HashMap<String,Object>();
			for(String xx : set){
				if(xx.equals("name"))
					map.put("name", json.getString("name"));
				else if(!xx.equals("id")){
					System.out.println(xx);
					JSONArray ja = json.getJSONArray(xx);
					JSONObject faja = new JSONObject();
					faja = process(ja,xx);
				}					
			}
			JSONObject rstj = JSONObject.fromObject(map);
			result.add(rstj.toString());
		}
		
		FileUtil.ListToFile(result, "C:/Users/强胜/Desktop/dataCrawler/usnews/每个学院的每个faculty的东西/96个匹配到的学11.json");
		System.out.println("+++____end____+++");
	}
	public static JSONObject process(JSONArray ja, String id){
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		
		//map.put("", value)
		for(int i = 0 ; i < ja.size() ; i++){
			JSONObject joo = ja.getJSONObject(i);
			for(String xx : (Set<String>)joo.keySet()){
				map.put(xx, joo.getString(xx));
				System.out.println(xx="__"+joo.getString(xx));
			}
		}
		return json;
	}
}

