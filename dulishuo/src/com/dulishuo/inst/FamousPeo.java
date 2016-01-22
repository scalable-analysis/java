package com.dulishuo.inst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class FamousPeo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> ins = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/服务器字典/institute.json", "utf-8");
		List<String> res = new ArrayList<String>();
		for(JSONObject json : ins){
			
			if(json.get("famouspeople").toString().indexOf("[{") == -1){
				JSONArray ja = new JSONArray();
				Map<String,String> map = new HashMap<String,String>();
				map.put("name", "");
				map.put("position", "");
				JSONObject jo = JSONObject.fromObject(map);
				ja.add(jo);
				ja.add(jo);
				ja.add(jo);
				ja.add(jo);
				ja.add(jo);
				json.put("famouspeople", ja);
			}
			res.add(json.toString());
				
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/服务器字典/instituteFam.json");
	}

}
