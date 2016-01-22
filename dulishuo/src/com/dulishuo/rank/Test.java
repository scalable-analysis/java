package com.dulishuo.rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("start . . . \n");
		long start = System.currentTimeMillis();

		getTop30();

		long end = System.currentTimeMillis();
		System.out.println("end use time : " + (end - start) + " ms .");
	}

	private static void getTop30() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("H:/独立说/backup/rank1110.json","utf-8");
		List<JSONObject> sch = FileUtil.FileToJsonList("H:/独立说/backup/institute1104.json","utf-8");
		
		Map<Integer,String> mapp = new HashMap<Integer,String>();
		for(JSONObject json : sch){
			if(json.containsKey("country") && json.getString("country").equals("USA"))
				mapp.put(Util.id(json.get("id").toString()),json.getString("country"));
		}
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(JSONObject json : list){
			try{
				int year = Util.id(json.get("year").toString());
				int form = Util.id(json.get("rank_type_id").toString());
				
				if(year == 2016 && form == 42 && mapp.containsKey(json.getInt("institute_id"))){
					map.put(json.getString("institute"), Util.id(json.get("value").toString()));
				}
			}catch(Exception e){
				
			}
		}
		
		map = Util.sortMap(map);
		int i = 0 ;
		for(Entry<String,Integer> entry : map.entrySet()){
			i++;
			if(i > 40)
				break;
			System.out.println(entry.getKey());
		}
			
	}

}
