package com.dulishuo.shenqingfang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class offer1002 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//hebing();
		statistics();
	}

	private static void statistics() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerResRK1.json", "utf-8");
		List<JSONObject> facType = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/facType/facType.json", "utf-8");
		List<String> res = new ArrayList<String>();
		float sum_gpa = 0.00f;
		int sum_toefl = 0 ; 
		float sum_gre = 0.00f;
		float sum_ielts = 0.00f;
		float sum_gmat = 0.00f;
		
		int cGpa = 0;
		int cToefl = 0;
		int cGre = 0;
		int cGmat = 0;
		int cIelts = 0;
		
		int flag = 1;
		Map<Integer,String> facMap = new HashMap<Integer,String>();
		for(JSONObject json : facType)
			facMap.put(json.getInt("id"), json.getString("name_chinese"));

		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i = 1 ; i < 66 ; i++)
			map.put(i, 0);
		for(JSONObject json : list){
			//System.out.println("process____"+flag++);
		/*	if(json.containsKey("gpa")){
				
					cGpa++;
					sum_gpa += Float.parseFloat(json.get("gpa").toString());
				
			}
			
			if(json.containsKey("gmat")){
				JSONObject tmp = json.getJSONObject("gmat");
				if(tmp.containsKey("total")){
					cGmat++;
					sum_gmat += Float.parseFloat(tmp.get("total").toString());
				}
			}
			
			if(json.containsKey("gre")){
				JSONObject tmp = json.getJSONObject("gre");
				if(tmp.containsKey("total")){
					cGre++;
					sum_gre += Float.parseFloat(tmp.get("total").toString());
				}
			}
			
			if(json.containsKey("toefl")){
				JSONObject tmp = json.getJSONObject("toefl");
				if(tmp.containsKey("total")){
					cToefl++;
					sum_toefl += Integer.parseInt((tmp.get("total").toString()));
				}
			}
			if(json.containsKey("ielts")){
				JSONObject tmp = json.getJSONObject("ielts");
				if(tmp.containsKey("total")){
					cIelts++;
					sum_ielts += Float.parseFloat(tmp.get("total").toString());
				}
			}*/
			if(json.getInt("id")>20737){
				int[] tmp = new int[1];
				tmp[0] = json.getInt("id");
				json.put("department_id", tmp);
			}
			json.put("department_type", json.get("department_id"));
			res.add(json.toString());
				
		}
		
		/*System.out.println(cGre+"\tgre_____"+(sum_gre/cGre));
		System.out.println(cGpa+"\tgpa_____"+(sum_gpa/cGpa));
		System.out.println(cGmat+"\tgmat_____"+(sum_gmat/cGmat));
		System.out.println(cToefl+"\ttoefl_____"+(sum_toefl/cToefl));
		System.out.println(cIelts+"\tielts_____"+(sum_ielts/cIelts));*/
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerResRK2.json");
		System.out.println("___________-----Exit----_________");
	}

	private static void hebing() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerRes3.json", "utf-8");
		List<JSONObject> pgm = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/programRK11.json", "utf-8");
		List<String> res = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		int max = 20738 ; 
		Map<Integer,Object> map = new HashMap<Integer,Object>();
		for(JSONObject json : pgm){
			map.put(json.getInt("id"), json.get("department_type"));
		}
		System.out.println("read_____end");
		for(int i = 0 ; i<list.size() ; i++){
			JSONObject json = list.get(i);
			if(json.containsKey("program_id")){
				if(map.containsKey(Util.id(json.get("program_id").toString()))){
					json.put("department_type", map.get(Util.id(json.get("program_id").toString())));
				}
			}
			
			if(i < 3855){
				json.remove("_id");
				json.put("id", Util.id(json.getString("id")));
				res.add(json.toString());
			}
			if(i > 3854){
				json.remove("id");
				json.put("id", max++);
				res.add(json.toString());
			}
			
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerResRK1.json");
		System.out.println("___________-----Exit----_________");
	}

}
