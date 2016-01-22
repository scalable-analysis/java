package com.dulishuo.newFaculty;
import java.util.*;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import net.sf.json.JSONObject;

public class ToDict {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			//factoDict();
			//facTypeDict();
			toDict();
	}

	private static void facTypeDict() {
		// TODO Auto-generated method stub
		List<JSONObject> fac = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/facType/facType.json","utf-8");
		List<String> dict = new ArrayList<String>();
		
		for(JSONObject json : fac){
			dict.add(json.get("id").toString()+",regex:"+json.getString("name").trim());
			dict.add(json.get("id").toString()+",keyword:"+json.getString("name_chinese").trim());
		}
		FileUtil.ListToFile(dict, "C:/Users/强胜/Desktop/中秋版本更新/字典/faculty-type.csv");
		System.out.println("____--Exit--______");
	}

	private static void toDict() {
		// TODO Auto-generated method stub
		List<JSONObject> fac = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/学院列表/美国/fac3.json","utf-8");
		List<String> dict = new ArrayList<String>();
		
		for(JSONObject json : fac){
			if(json.getInt("institute_id") > 0)
				dict.add(json.get("id").toString()+",entity:school:"+json.get("institute_id").toString()+",regex:"+json.getString("name").trim());
				dict.add(json.get("id").toString()+",entity:school:"+json.get("institute_id").toString()+",regex:"+json.getString("name_chinese").trim());
		}
		FileUtil.ListToFile(dict, "C:/Users/强胜/Desktop/中秋版本更新/字典/faculty.csv");
		System.out.println("____--Exit--______");
	}

}
