package com.dulishuo.program;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class rengongPgm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> pgm = FileUtil.FileToJsonList("C:/Users/强胜/program.json", "utf-8");
		List<String> res = new ArrayList<String>();
		
		for(JSONObject json : pgm){
			int id = id(json.get("institute_id").toString());
			if(id > 697 && id != 733 && id != 744 && id != 755){
				if(id == 700){
					json.put("institute_id", 235);
					res.add(json.toString());
				}
				if(id == 719){
					json.put("institute_id", 241);
					res.add(json.toString());
				}
				if(id == 720){
					json.put("institute_id", 192);
					res.add(json.toString());
				}
				else
					res.add(json.toString());
			}
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/数据补全/program/新增世界各地大学.json");
		System.out.println("____end____");
	}
	
	
	public static int id(String xx){
		int id = -1;
		if(xx.indexOf("$numberLong")  != -1)
			id = Integer.parseInt(xx.replace("{\"$numberLong\":\"", "").replace("\"}", ""));
		else if(!xx.equals(" ") && !xx.equals(""))
			id = Integer.parseInt(xx.replace(".0",""));
		return id;
	}

}
