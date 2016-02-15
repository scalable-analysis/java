package com.dulishuo.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class test0810 {

	static int flag = 1;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//List<String> src = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/postAll.json");
		List<String> src = FileUtil.FileToList("C:/Users/强胜/Desktop/服务器字典/program1.json");
		List<JSONObject> src1 = FileUtil.FileToListJson("C:/Users/强胜/Desktop/dataCrawler/faculty/中1000programFaculty.xls", 7);
		List<String> tt = new ArrayList<String>();
		List<String> tt2 = new ArrayList<String>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		for(JSONObject xx : src1){
			map.put(xx.getString("id").replace(".0", ""), Integer.parseInt(xx.getString("faculty_id").replace(".0", "")));
		}
		for(String xx : src){
			JSONObject json = JSONObject.fromObject(xx);
			String id = json.getString("id").replace("{\"$numberLong\":\"","").replace("\"}", "");
			if(map.containsKey(id)){
				
				json.put("faculty_id", map.get(id));
				System.out.println("process__"+(flag++)+"__"+json.get("faculty_id"));
			}
				
			
			
			
			tt.add(json.toString());
		}
		
		FileUtil.ListToFile(tt, "C:/Users/强胜/Desktop/服务器字典/program2.json");
		System.out.println("____end____");
	}
	
	
	public static String filter(String src){
		String result = "-1";
		
		return result;
	}

}
