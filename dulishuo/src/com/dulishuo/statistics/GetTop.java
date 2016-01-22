package com.dulishuo.statistics;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import java.util.*;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

public class GetTop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> list1 = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/杂项/chaseDream/商学院/商学院.txt", "utf-8");
		List<JSONObject> list2 = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/杂项/chaseDream/TOEFL/TOEFL.txt", "utf-8");
		List<JSONObject> list3 = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/杂项/chaseDream/GRE/GRE.txt", "utf-8");
		List<JSONObject> list4 = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/杂项/chaseDream/GMAT/GMAT.txt", "utf-8");
		List<JSONObject> list = new ArrayList<JSONObject>();
		List<JSONObject> lst = new ArrayList<JSONObject>();
		List<String> res = new ArrayList<String>();
		List<String> ids = new ArrayList<String>();
		list.addAll(list1);
		list.addAll(list2);
		list.addAll(list3);
		list.addAll(list4);
		int id = 1;
		for(JSONObject json : list){
			json.put("id",id++);
			lst.add(json);
		}
		System.out.println("read__end+lst.size():"+lst.size());
		//按评论数排序
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(JSONObject json : lst){
			try{
				int year = Integer.parseInt(json.getString("date").substring(0, 4));
				int month = Integer.parseInt(json.getString("date").split("-")[1]);
				//System.out.println(year+"__"+month);
				if(year > 2012 && month <13 && month > 8){
					map.put(json.get("id").toString(), Util.id(json.get("cmt").toString()));
				}
			}catch(Exception e){
			}
		}
		System.out.println("put____to____map____end"+map.size());
		
		map = Util.sortMap(map);
		//取 TOP200个
		int flag = 0 ; 
		int fag = 0;
		//ids.add(entry.getKey());
		for(Entry<String, Integer> entry : map.entrySet()){
			if(flag%200==0){
				fag = 0;
			}else{
				if(fag < 5){
					ids.add(entry.getKey());
					fag++;
				}
			}
			flag++;
		}
		System.out.println("get___200___end+ids.size():"+ids.size());
		for(JSONObject json : lst){
			String idd = json.get("id").toString();
			if(ids.contains(idd)){
				res.add(json.getString("date")+"\t"+json.getString("cmt")+"\t"+json.getString("see")+"\t"+"http://forum.chasedream.com/"+json.getString("link"));
			}
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/杂项/chaseDream/Top200-5帖子.txt");
		System.out.println("____end____");
	}

}
