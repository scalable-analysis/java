package com.dulishuo.zhongyingwang;
import java.io.UnsupportedEncodingException;
import java.util.*;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		List<JSONObject> ins = FileUtil.FileToJsonList("C:/Users/强胜/institute.json", "UTF-8");
		List<JSONObject> ins22 = FileUtil.FileToJsonList("C:/Users/强胜/institute22.json", "UTF-8");
		List<String> list = new ArrayList<String>();
		
		for(JSONObject json : ins22){
			String id = json.getString("id");
			for(JSONObject jo : ins){
				if(jo.getString("id").equals(id)){
					//System.out.println(jo.getString("id").replace("{\"$numberLong\":\"", "").replace("\"}",""));
					int idd = Integer.parseInt(jo.getString("id").replace("{\"$numberLong\":\"", "").replace("\"}",""));
					if(idd > 534 && idd < 697){
						json.put("country", "UK");
					}else{
						if(jo.containsKey("country"))
							json.put("country", jo.getString("country"));
						else
							json.put("country", "");
					}
					if(jo.containsKey("practice_requirements"))
						json.put("practice_requirements", jo.getString("practice_requirements"));
					else
						json.put("practice_requirements", "");
					if(jo.containsKey("experience_requirements"))
						json.put("experience_requirements", jo.getString("experience_requirements"));
					else
						json.put("experience_requirements", "");
					if(jo.containsKey("rate_employment"))
						json.put("rate_employment", jo.getString("rate_employment"));
					else
						json.put("rate_employment", "");
				}
				
			}
			list.add(json.toString());
		}
			FileUtil.ListToFile(list, "C:/Users/强胜/institute33.json");
				//System.out.println(xx.split("!")[0]+",regex,"+xx.split("!")[1]);
				
		System.out.println("____end___");		
	}
}
