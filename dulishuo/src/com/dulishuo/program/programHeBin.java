package com.dulishuo.program;

import java.util.*;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class programHeBin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		hebin1();
	
	}

	private static void hebin1() {
		// TODO Auto-generated method stub
	
		List<JSONObject> ne = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program11.json", "utf-8");
		List<JSONObject> fac = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/学院列表/美国/fac3.json", "utf-8");
		
		List<String> res = new ArrayList<String>();
		Map<Integer,String> facMap = new HashMap<Integer,String>();
		for(JSONObject json : fac){
			if(json.getInt("institute_id") == 239)
				facMap.put(json.getInt("id"),json.getString("name").toLowerCase().trim());
		}
		System.out.println("map  size :" + facMap.size());
	
		for(JSONObject json : ne){
			if(json.getInt("institute_id") == 239){
				String faculty = json.getString("faculty");
				if(json.getString("faculty").equals(""))
					faculty=json.getString("subject");
			
				json.put("faculty_id", -1);
				for(Entry<Integer,String> entry : facMap.entrySet()){
						if(Util.isSameFaculty(faculty, entry.getValue())){
							json.put("faculty_id", entry.getKey());
						}
					
				}
				
			}
			res.add(json.toString());
			
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program22.json");
		System.out.println("________---exiT--___________");
	}

}
