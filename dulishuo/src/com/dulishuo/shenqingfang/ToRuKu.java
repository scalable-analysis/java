package com.dulishuo.shenqingfang;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class ToRuKu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> ne = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program99.json", "utf-8");
		
		List<String> res = new ArrayList<String>();
		for(JSONObject json : ne){
			JSONObject gre;
			JSONObject gmat;
			JSONObject gpa;
			JSONObject toefl;
			JSONObject ielts;
			if(json.containsKey("gre")){
				gre = json.getJSONObject("gre");
				if(!gre.containsKey("min_score"))
					gre.put("min_score", "");
				if(!gre.containsKey("verbal"))
					gre.put("verbal", "");
				if(!gre.containsKey("quantitative"))
					gre.put("quantitative", "");
				if(!gre.containsKey("writing"))
					gre.put("writing", "");
				if(!gre.containsKey("other"))
					gre.put("other", "");
				
				if(gre.containsKey("min_score"))
					gre.put("min_score", gre.get("min_score").toString());
				if(gre.containsKey("verbal"))
					gre.put("verbal", gre.get("verbal").toString());
				if(gre.containsKey("quantitative"))
					gre.put("quantitative", gre.get("quantitative").toString());
				if(gre.containsKey("writing"))
					gre.put("writing", gre.get("writing").toString());
				if(gre.containsKey("other"))
					gre.put("other", gre.get("other").toString());
			}else{
				gre = new JSONObject();
				gre.put("min_score", "");
				gre.put("verbal", "");
				gre.put("quantitative", "");
				gre.put("writing", "");
				gre.put("other", "");
			}
			
			if(json.containsKey("gmat")){
				gmat = json.getJSONObject("gmat");
				if(!gmat.containsKey("min_score"))
					gmat.put("min_score", "");
				if(!gmat.containsKey("rec_score"))
					gmat.put("rec_score", "");
				if(!gmat.containsKey("other"))
					gmat.put("other", "");
				if(gmat.containsKey("min_score"))
					gmat.put("min_score",  gmat.get("min_score").toString());
				if(gmat.containsKey("rec_score"))
					gmat.put("rec_score", gmat.get("rec_score").toString());
				if(gmat.containsKey("other"))
					gmat.put("other",  gmat.get("other").toString());
			}else{
				gmat = new JSONObject();
					gmat.put("min_score", "");
					gmat.put("rec_score", "");
					gmat.put("other", "");
			}
			
			
			if(json.containsKey("gpa")){
				gpa = json.getJSONObject("gpa");
				if(!gpa.containsKey("min_score"))
					gpa.put("min_score", "");
				if(!gpa.containsKey("rec_score"))
					gpa.put("rec_score", "");
				if(!gpa.containsKey("other"))
					gpa.put("other", "");
				if(gpa.containsKey("min_score"))
					gpa.put("min_score",  gpa.get("min_score").toString());
				if(gpa.containsKey("rec_score"))
					gpa.put("rec_score", gpa.get("rec_score").toString());
				if(gpa.containsKey("other"))
					gpa.put("other",  gpa.get("other").toString());
			}else{
				gpa = new JSONObject();
					gpa.put("min_score", "");
					gpa.put("rec_score", "");
					gpa.put("other", "");
			}
			
			if(json.containsKey("toefl")){
				toefl = json.getJSONObject("toefl");
				if(!toefl.containsKey("total"))
					toefl.put("total", "");
				if(!toefl.containsKey("listening"))
					toefl.put("listening", "");
				if(!toefl.containsKey("speaking"))
					toefl.put("speaking", "");
				if(!toefl.containsKey("reading"))
					toefl.put("reading", "");
				if(!toefl.containsKey("writing"))
					toefl.put("writing", "");
				if(!toefl.containsKey("other"))
					toefl.put("other", "");
				
				if(toefl.containsKey("total"))
					toefl.put("total", toefl.get("total").toString());
				if(toefl.containsKey("listening"))
					toefl.put("listening", toefl.get("listening").toString());
				if(toefl.containsKey("speaking"))
					toefl.put("speaking", toefl.get("speaking").toString());
				if(toefl.containsKey("reading"))
					toefl.put("reading", toefl.get("reading").toString());
				if(toefl.containsKey("writing"))
					toefl.put("writing", toefl.get("writing").toString());
				if(toefl.containsKey("other"))
					toefl.put("other", toefl.get("other").toString());
			}else{
				toefl = new JSONObject();
				if(!toefl.containsKey("total"))
					toefl.put("total", "");
					toefl.put("listening", "");
					toefl.put("speaking", "");
					toefl.put("reading", "");
					toefl.put("writing", "");
					toefl.put("other", "");
			}
			
			if(json.containsKey("ielts")){
				ielts = json.getJSONObject("ielts");
				if(!ielts.containsKey("total"))
					ielts.put("total", "");
				if(!ielts.containsKey("listening"))
					ielts.put("listening", "");
				if(!ielts.containsKey("speaking"))
					ielts.put("speaking", "");
				if(!ielts.containsKey("reading"))
					ielts.put("reading", "");
				if(!ielts.containsKey("writing"))
					ielts.put("writing", "");
				if(!ielts.containsKey("other"))
					ielts.put("other", "");
				
				if(ielts.containsKey("total"))
					ielts.put("total", ielts.get("total").toString());
				if(ielts.containsKey("listening"))
					ielts.put("listening", ielts.get("listening").toString());
				if(ielts.containsKey("speaking"))
					ielts.put("speaking", ielts.get("speaking").toString());
				if(ielts.containsKey("reading"))
					ielts.put("reading", ielts.get("reading").toString());
				if(ielts.containsKey("writing"))
					ielts.put("writing", ielts.get("writing").toString());
				if(ielts.containsKey("other"))
					ielts.put("other", ielts.get("other").toString());
			}else{
				ielts = new JSONObject();
				if(!ielts.containsKey("total"))
					ielts.put("total", "");
					ielts.put("listening", "");
					ielts.put("speaking", "");
					ielts.put("reading", "");
					ielts.put("writing", "");
					ielts.put("other", "");
			}
			
			
			json.put("gre", gre);
			json.put("gmat", gmat);
			json.put("gpa", gpa);
			json.put("toefl", toefl);
			json.put("ielts", ielts);
			json.put("id", Integer.parseInt(json.getString("id")));
			res.add(json.toString());
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/programRK11.json");
		System.out.println("____exit_____");
	}

}
