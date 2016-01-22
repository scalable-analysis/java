package com.dulishuo.test;
import java.util.*;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import net.sf.json.JSONObject;

public class test0830 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> all = FileUtil.FileToJsonList("C:/Users/强胜/program.json", "utf-8");
		List<JSONObject> gg = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/program/program_id_not_matched_program.json", "utf-8");
	
		
		List<String> list = new ArrayList<String>();
		for(JSONObject json : all){
			int id = Util.id(json.get("id").toString());
			int ids = Util.id(json.get("institute_id").toString());
			if(id > 10000 && id < 25170 && ids < 698){
				list.add(json.toString());
			/*for(JSONObject jsona : gg){
					
					try{
						if(jsona.getString("deadline").equals(json.getString("deadline"))   &&  jsona.getString("reletter").equals(json.getString("reletter"))  &&   jsona.getString("title").equals(json.getString("title"))  &&  jsona.getString("institute").equals(json.getString("institute"))){
						if()	
						System.out.println(id);
							list.add(String.valueOf(id));
						}
					}catch(Exception e){
						System.out.println("error");
					}
				
					
				}*/
			}
		}
		
		FileUtil.ListToFile(list, "C:/Users/强胜/Desktop/tyty1.json");
		
		System.out.println("____end____");
	}
}
