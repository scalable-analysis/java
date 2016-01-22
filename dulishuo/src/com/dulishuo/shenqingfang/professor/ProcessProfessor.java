package com.dulishuo.shenqingfang.professor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class ProcessProfessor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> professor = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/中秋版本更新/professor.json", "utf-8");
		List<JSONObject> department = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/facType/facType.json", "utf-8");
		Map<String,Integer> depMap = new HashMap<String,Integer>();
		for(JSONObject json : department)
			depMap.put(json.getString("name_chinese"), json.getInt("id"));
		List<String> res = new ArrayList<String>();
		
		for(JSONObject json : professor){
			json.remove("_id");
			String title = json.getString("title");
			String depart= title.split("@")[0].trim();
			json.put("department_type", -1);
			json.put("institute", "");
			json.put("institute_id", -1);
			
			if(depMap.get(depart) != null){
				int department_type = depMap.get(depart);
				json.put("department_type", department_type);
				try{
					String institute = title.split("@")[1].split("20")[0].trim();
					json.put("institute", institute);
					if(institute.length() > 1){
						int ins_id = Util.getInstitute(institute);
						if(ins_id != -1)
							json.put("institute_id", ins_id);
					}
				}catch(Exception e){
				}
			}
				
			res.add(json.toString());
		}
		
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/教授招生信息/1009professor11.json");
		System.out.println("--------______Exit----------------");
	}

}
