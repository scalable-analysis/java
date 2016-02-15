package com.dls.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DataProcess0702 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/强胜/Desktop/77777777.json")));
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/institute_等_3_个文件_teambition/rank.json")));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/institute_等_3_个文件_teambition/institute.json")));
		
		Map<String,String> map = new HashMap<String,String>();
		String tmp1 = "";
		String tmp2 = "";
		StringBuilder sb = new StringBuilder();
		
		while((tmp1 = br1.readLine()) != null){
			sb.append(tmp1);
		}
		System.out.println("--------读取完成---------");
		JSONArray json = JSONArray.fromObject(sb.toString());
		for(int i = 0 ; i < json.size(); i++){
			JSONObject jonT = json.getJSONObject(i);
			if(jonT.getString("rank_type_id").equals("43")){
				if(jonT.get("institute_id") != null && !jonT.getString("institute_id").equals("null")){
					map.put(jonT.getString("institute_id"), jonT.getString("value"));
				}
			}
		}
		System.out.println(map.size());
		
		while((tmp2 = br2.readLine()) != null){
			JSONObject jbt = JSONObject.fromObject(tmp2);
			if(map.containsKey(jbt.getJSONObject("id").getString("$numberLong"))){
				jbt.put("rank", map.get(jbt.getJSONObject("id").getString("$numberLong")));				
			}else{
				jbt.put("rank","0");	
			}
			writer.write(jbt.toString());
			writer.newLine();		
		}
		
		writer.close();
		System.out.println("-----------end------------");
	}
	
}
