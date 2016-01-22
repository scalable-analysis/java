package com.dulishuo.usnews.sixteen;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class RuKu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		addId();
	}

	private static void addId() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/usnews/2016/全球高校综合排名/uni1008001.json", "utf-8");
		List<String> res = new ArrayList<String>();
		
		int flag = 23751;
		for(JSONObject json : list){
			json.put("id", flag++);
			res.add(json.toString());
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/usnews/2016/全球高校综合排名/uniRuKu1009.json");
		System.out.println("----Exi____________");
	}

}
