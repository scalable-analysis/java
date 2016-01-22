package com.dulishuo.other;

import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class Top30 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/rank.json", "utf-8");
		List<Integer> set = new ArrayList<Integer>();
		List<String> result = new ArrayList<String>();
		for(JSONObject json : list){
			int type = Util.id(json.get("rank_type_id").toString());
			
			if(type == 42 ){
				if(json.containsKey("institute_id")){
					int value = Util.id(json.get("value").toString());
					if(value < 100){
						set.add(Util.id(json.getString("institute_id")));
					}
				}
				
			}
		}
		for(Integer id : set)
			System.out.println(id);
		
		
		System.out.println("read____end!");
		List<JSONObject> list1 = FileUtil.FileToJsonList("C:/Users/强胜/institute.json", "utf-8");
		for(int i = 0 ; i < set.size() ; i++){
			for(JSONObject json : list1){
				int id = Util.id(json.get("id").toString());
				if(id == set.get(i)){
					String xx = (i+1)+"\t"+json.getString("title")+json.getString("ttitle")+"\t"+json.getString("country")+"\t"+json.getString("league")+json.getString("tleague")+"\t"+json.getString("address")+json.getString("taddress")+"\t"+json.getString("brief")+"\t"+json.getString("thistory");
					if(json.containsKey("accept_rate"))
						xx = xx + "\t"+json.getString("accept_rate");
					else
						xx = xx + "\t"+"-";
					if(json.containsKey("graducation_rate"))
						xx = xx + "\t"+json.getString("graducation_rate");
					else
						xx = xx + "\t"+"-";
					if(json.containsKey("total_enrollment"))
						xx = xx + "\t"+json.getString("total_enrollment");
					else
						xx = xx + "\t"+"-";
					if(json.containsKey("tuition_and_fees"))
						xx = xx + "\t"+json.getString("tuition_and_fees");
					else
						xx = xx + "\t"+"-";
					
					JSONArray ja = json.getJSONArray("famouspeople");
					for(int j = 0 ; j < ja.size() ; j++){
						JSONObject jo = ja.getJSONObject(j);
						xx = xx +"\t"+ jo.getString("name")+"-"+jo.getString("position");
					}
					xx = xx +"\t"+json.getJSONObject("school").getString("surrounding")+"\t"+json.getJSONObject("school").getString("living");
					if(json.containsKey("website"))
						xx = xx + "\t"+ json.getString("website");
					else
						xx = xx + "\t"+ "-";
					
					
					result.add(xx);
				}
			}
		}
		FileUtil.ListToFile(result, "C:/Users/强胜/Desktop/top100.txt");
		
		System.out.println("_____end_______");
	}

}
