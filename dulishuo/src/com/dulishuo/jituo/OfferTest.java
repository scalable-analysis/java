package com.dulishuo.jituo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class OfferTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//shuaixuan();
		tmp();
	}



	private static void tmp() {
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/newoffer122.json", "utf-8");
		List<String> res = new ArrayList<String>();
		for(JSONObject json : list){
			json.remove("_id");
			if(json.containsKey("school_rank") && json.containsKey("program_rank"))
				res.add(json.toString());
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/中秋版本更新/offer0925.json");
	}



	private static void shuaixuan() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/寄托/匹配了学校Offer.json", "utf-8");
		List<JSONObject> sch = FileUtil.FileToJsonList("C:/Users/强胜/ins.json", "utf-8");
		
		List<String> set = new ArrayList<String>();
		System.out.println("_________starting__________");
		
		for(JSONObject json : list){
			try{
				if(Float.parseFloat(json.get("gpa").toString())<5.0)
					if(Integer.parseInt(json.getJSONObject("gre").get("total").toString())<500)
						if(Integer.parseInt(json.getJSONObject("toefl").get("total").toString())<110 || Float.parseFloat(json.getJSONObject("ielts").get("total").toString())<9.0)
							if(json.getString("major")!="" && !json.getString("major").toLowerCase().equals("other"))
								if(json.getInt("institute_id") != -1)
									set.add(json.toString());			
			}catch(Exception e){
			}
			
		}
		FileUtil.ListToFile(set, "C:/Users/强胜/Desktop/dataCrawler/寄托/匹配了学校Offer1.json");
		System.out.println("_____________exit___________");
	}

}
