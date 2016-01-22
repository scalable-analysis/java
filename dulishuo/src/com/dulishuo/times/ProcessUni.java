package com.dulishuo.times;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class ProcessUni {

	public static void main(String[] args) {
		//fieldProcess();
		//insIndPro();
		//addId();
		/*List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/facType/facType.json", "utf-8");
	
		
		for(JSONObject json : list){
			System.out.println(json.getString("name")+"\t"+json.getString("name_chinese"));
		}*/
		List<String> list = FileUtil.FileToList("C:/Users/强胜/Desktop/tt.json");
		String u = "";
		for(String xx : list)
			u = u + xx.trim();
		
		System.out.println(u);
	}

	private static void addId() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/times/1009uni.json", "utf-8");
		Sheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/dataCrawler/times/institute_id为-1.xls",0);
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		for(int i = 0 ; i < 52; i++){
			map.put(sht.getRow(i).getCell(0).toString(), Integer.parseInt(sht.getRow(i).getCell(1).toString().replace(".0", "")));
		}
		System.out.println(map.size());
		List<String> res = new ArrayList<String>();
		int id = 24501;
		for(JSONObject json : list){
			json.put("id", id++);
			if(json.getInt("institute_id") == -1){
				if(map.containsKey(json.getString("institute"))){
					json.put("institute_id", map.get(json.getString("institute")));
					System.out.println(json.getString("institute"));
				}
					
			}
			res.add(json.toString());
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/times/1010uni.json");
		System.out.println("___________---Exit___");
	}

	private static void insIndPro() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/times/1009uni.json", "utf-8");
	
		Set<String> set = new HashSet<String>();
		for(JSONObject json : list){
			if(json.getInt("institute_id") == -1)
				set.add(json.getString("institute"));
		}
		for(String xx : set)
			System.out.println(xx);
	}

	private static void fieldProcess() {
		// TODO Auto-generated method stub
		int flag = 1;
		// TODO Auto-generated method stub
		List<String> list = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/times/timesUni.txt");
		List<String> res = new ArrayList<String>();
		for(int i = 0 ; i < 6 ; i ++){
			JSONArray ja = JSONArray.fromObject(list.get(i));
			for(int j = 0 ; j < 200 ; j++){
				System.out.println("process____"+flag++);
				JSONObject json = ja.getJSONObject(j);
				int value = Util.id(json.getString("rank"));
				String title = json.getString("title");
				String country = json.getString("field_country");
				json.put("year", 2011+i);
				json.remove("rank");
				json.put("value", value);
				json.remove("rank_label");
				json.put("score", Float.parseFloat(json.getString("score_overall")));
				json.remove("score_overall");
				json.remove("add_to_list");
				json.remove("field_country");
				json.remove("institution_entity");
				json.put("url", "https://www.timeshighereducation.com"+json.getString("path"));
				json.remove("path");
				json.put("institute", title);
				json.remove("title");
				json.put("rank_type_id", 44);
				json.put("institute_id", -1);
				json.put("institute_id", Util.getInstitute(title));
				
				res.add(json.toString());
			}
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/times/1009uni.json");
		System.out.println("___________---Exit___");
	}

}
