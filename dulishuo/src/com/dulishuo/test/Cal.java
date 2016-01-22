package com.dulishuo.test;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import net.sf.json.JSONObject;

public class Cal{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//buyaohuang();
		
		//gg();
		
		//gg1();
		
		test();
	}

	private static void test() {
		// TODO Auto-generated method stub
		List<String> ins = FileUtil.FileToList("C:/Users/强胜/faculty1117.csv");
		for(String xx : ins){
			String id = xx.split(",")[0].toString();
			String name = xx.split(",")[1];
			String name_chinese = xx.split(",")[2];
			System.out.println("<option value=\""+id+"\">"+name+"--"+name_chinese+"</option>");
		}
	}
		

	private static void gg1() {
 		// TODO Auto-generated method stub
		List<JSONObject> lod = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program.json","utf-8");
		Map<String,String> map = new HashMap<String,String>();
		Set<String> set = new HashSet<String>();
		for(JSONObject json : lod){	
			if(map.containsKey(json.get("faculty").toString()+json.get("institute").toString()+"\t"+json.getString("title")+"\t"+json.getString("term")+json.getString("deadline")))
				System.out.println(map.get(json.get("faculty").toString()+json.get("institute").toString()+"\t"+json.getString("title")+"\t"+json.getString("term")+json.getString("deadline"))+"\t"+json.get("id").toString());
		
			if(!map.containsKey(json.get("faculty").toString()+json.get("institute").toString()+"\t"+json.getString("title")+"\t"+json.getString("term")+json.getString("deadline")))
				map.put(json.get("faculty").toString()+json.get("institute").toString()+"\t"+json.getString("title")+"\t"+json.getString("term")+json.getString("deadline"), json.get("id").toString());
			}
	}

	private static void gg() {
		// TODO Auto-generated method stub
		List<JSONObject> lod = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program.json","utf-8");
		List<JSONObject> lod44 = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program44.json","utf-8");
		Set<String> set = new HashSet<String>();
		Map<String,String> map = new HashMap<String,String>();
		List<String> res = new ArrayList<String>();
		int count = 1;
		for(JSONObject json : lod){	
			map.put(json.get("faculty").toString()+json.get("institute").toString()+"\t"+json.getString("title")+"\t"+json.getString("term")+json.getString("deadline"), json.get("id").toString());
			
		}
		System.out.println("map size: "+map.size());
		for(JSONObject json : lod44){
			if(map.containsKey(json.get("faculty").toString()+json.get("institute").toString()+"\t"+json.getString("title")+"\t"+json.getString("term")+json.getString("deadline"))){
				json.put("id",map.get(json.get("faculty").toString()+json.get("institute").toString()+"\t"+json.getString("title")+"\t"+json.getString("term")+json.getString("deadline")));
				map.remove(json.get("faculty").toString()+json.get("institute").toString()+"\t"+json.getString("title")+"\t"+json.getString("term")+json.getString("deadline"));
				res.add(json.toString());
			}
				
			System.out.println("map size: "+map.size());
				
			
		}
			
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program55.json");
		System.out.println("exit__________");
	}

	private static void buyaohuang() {
		// TODO Auto-generated method stub
		int count = 1;
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/newoffer.json","utf-8");
		List<String> li = FileUtil.FileToList("C:/Users/强胜/Desktop/哥哥哥哥.txt");
		List<String> lod = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/过时的program取代表.txt");
		List<String> res = new ArrayList<String>();
		List<JSONObject> ress = new ArrayList<JSONObject>();
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		Map<Integer,Integer> mapp = new HashMap<Integer,Integer>();
		for(String xx : lod){
			map.put(Integer.parseInt(xx.split("\t")[0]), Integer.parseInt(xx.split("\t")[1]));
		}
		for(String xx : li){
			mapp.put(Integer.parseInt(xx.split("\t")[0]), Integer.parseInt(xx.split("\t")[1]));
		}
		System.out.println(map.size());
		for(JSONObject json : list){
			
			if(json.containsKey("program_id")){
				json.put("program_id", Util.id(json.get("program_id").toString()));
				if(map.containsKey(Util.id(json.get("program_id").toString())))
					json.put("program_id", map.get(Util.id(json.get("program_id").toString())));
			}
			ress.add(json);
		}
		for(JSONObject json : ress){
			if(json.containsKey("program_id")){
				if(mapp.containsKey(json.getInt("program_id")))
					json.put("program_id", mapp.get(json.getInt("program_id")));
			}
			res.add(json.toString());
			
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/newoffer11.json");
		System.out.println("___end___");
	}

}
