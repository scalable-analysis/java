package com.dulishuo.test;

import com.dulishuo.util.FileUtil;

import java.util.*;

import net.sf.json.JSONObject;

public class test0807 {
	public static void main(String[] args){
		String path = "C:/Users/强胜/Desktop/服务器字典/institute.json";
		
		List<String> list = FileUtil.FileToList(path);
		List<String> rst = new ArrayList<String>();
		
		for(String xx : list){
			JSONObject json = JSONObject.fromObject(xx);
			if(json.get("brief") != null){
				String brief = process(json.getString("brief"));
				json.put("brief", brief);
			}
			
			if(json.get("famouspeople") != null){
				String famouspeople = process(json.getString("famouspeople"));
				json.put("famouspeople", famouspeople);
			}
			
			if(json.get("history") != null){
				String history = process(json.getString("history"));
				json.put("history", history);
			}
			
			if(json.get("tfamouspeople") != null){
				String tfamouspeople = process(json.getString("tfamouspeople"));
				json.put("tfamouspeople", tfamouspeople);
			}
			
			if(json.get("thistory") != null){
				String thistory = process(json.getString("thistory"));
				json.put("thistory", thistory);
			}
			
			
			
			if(json.get("school") != null){
				JSONObject school = json.getJSONObject("school");
				if(json.get("living") != null)
					school.put("living", school.getString("living"));
				if(json.get("surrounding") != null)
					school.put("surrounding", school.getString("surrounding"));
				
				json.put("school", school);
			}
			
			
			rst.add(json.toString());
			
		}
		
		String pa = "C:/Users/强胜/Desktop/服务器字典/institute11.json";
		FileUtil.ListToFile(rst, pa);
		System.out.println("______end_______");
	}
	
	public static String process(String oo){
		
		/*if(oo.indexOf("&lt;br /&gt;") != -1){
			oo = oo.replace("&lt;br /&gt;", "<br>");
		}
		if(oo.indexOf("&lt;br&gt;") != -1){
			oo =  oo.replace("&lt;br&gt;", "<br>");
		}
		if(oo.indexOf("\u0026lt;strong\u0026gt;") != -1)
			oo =  oo.replace("\u0026lt;strong\u0026gt;", "<strong>");
		if(oo.indexOf("\u0026lt;/strong\u0026gt;") != -1)
			oo =  oo.replace("\u0026lt;/strong\u0026gt;", "</strong>");*/
		
		oo= oo.replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&").replace("&quot;", "\"");
		//JSONObject xx = JSONObject.fromObject(oo);
		return oo;
	}
}
