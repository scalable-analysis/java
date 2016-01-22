package com.dulishuo.offer;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class TestOffer {
	static int count1 = 1;
	static int count2 = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/offer.json", "utf-8");
		List<String> res1 = new ArrayList<String>();
		for(JSONObject xx : list){
			xx.put("id", Integer.parseInt(xx.getString("id")));
			res1.add(xx.toString());
		}
		System.out.println("count1__"+count1);
		System.out.println("count2__"+count2);
		FileUtil.ListToFile(res1, "C:/Users/强胜/Desktop/Offer.json");
		
		System.out.println("_____exit____________");
	}

}
