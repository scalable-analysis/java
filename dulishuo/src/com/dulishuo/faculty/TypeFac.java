package com.dulishuo.faculty;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import java.util.*;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import net.sf.json.JSONObject;

public class TypeFac {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//tmp();
		//type();
		//tmp1();
		//guiBin();
		tran();
	}

	private static void tran() {
		// TODO Auto-generated method stub
		List<JSONObject> faculty = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/学院列表/美国/fac3.json", "utf-8");
		Map<Integer,List<String>> map = new HashMap<Integer,List<String>>(); 
		List<String> res = new ArrayList<String>();
		for(JSONObject json : faculty){
			if(json.containsKey("is_new")){
				List<String> tmp;
				if(map.containsKey(json.getInt("institute_id")))
					tmp = map.get(json.getInt("institute_id"));
				else
					tmp = new ArrayList<String>();
				tmp.add(json.get("id").toString()+"\t"+json.getString("name")+"\t"+json.getString("name_chinese"));
				
				map.put(json.getInt("institute_id"), tmp);
			}
		}
		//System.out.println("map size:"+map.size());
		for(Entry<Integer,List<String>> entry : map.entrySet())
			for(String xx : entry.getValue())
				System.out.println(entry.getKey()+Util.getSchName(entry.getKey(),1)+"\t"+xx);
				
	}

	private static void guiBin() {
		// TODO Auto-generated method stub
		int count = 3100;
		List<JSONObject> faculty = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/学院列表/美国/fac2.json", "utf-8");
		List<JSONObject> old = FileUtil.FileToJsonList("C:/Users/强胜/faculty.json", "utf-8");
		
		List<String> res = new ArrayList<String>();
		List<String> res1 = new ArrayList<String>();
		Set<Integer> set = new HashSet<Integer>();
		for(JSONObject json : faculty){
			set.add(json.getInt("institute_id"));
			json.put("id", count++);
			res1.add(json.toString());
		}
		for(JSONObject json : old){
			json.remove("_id");
			if(!set.contains(Util.id(json.get("institute_id").toString())))
				res.add(json.toString());
		}
		res.addAll(res1);
		System.out.println(res.size());
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/数据补全/学院列表/美国/fac3.json");
		System.out.println("_________--Exit_-_________");
	}

	private static void tmp1() {
		// TODO Auto-generated method stub
		List<JSONObject> faculty = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/学院列表/美国/fac1.json", "utf-8");
		
		List<String> res = new ArrayList<String>();
		for(JSONObject json : faculty){
			json.put("is_new", 1);
			json.remove("id");
			res.add(json.toString());
		}
	
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/数据补全/学院列表/美国/fac2.json");
		
		System.out.println("____end______");
	}

	private static void type() {
		// TODO Auto-generated method stub
		int id = 1;
		List<JSONObject> type = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/fac/facTypeRank.json", "utf-8");
		List<String> res = new ArrayList<String>();
		
		for(JSONObject json : type){
			JSONObject js = new JSONObject();
			js.put("id", id++);
			js.put("name", json.getString("subject"));
			js.put("name_chinese", json.getString("subject_chinese"));
			js.put("ranktype_id", Util.id(json.get("id").toString()));
			res.add(js.toString());
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/facType/facType.json");
		System.out.println("______Exit________");
		
	}

	private static void tmp() {
		// TODO Auto-generated method stub
		int count = 1;
		List<JSONObject> faculty = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/学院列表/美国/fac.json", "utf-8");
		HSSFSheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/数据补全/学院列表/美国/学院校对.xls",0);
		Map<String,String> map = new HashMap<String,String>();
		for(int i = 0 ; i < sht.getLastRowNum()+1; i++){
			map.put(sht.getRow(i).getCell(0).toString(), sht.getRow(i).getCell(2).toString().trim());
		}
		System.out.println("map size :"+map.size());
		List<String> res = new ArrayList<String>();
		for(JSONObject json : faculty){
			String key = json.getString("name");
			System.out.println(key);
			if(map.containsKey(key)){
				System.out.println("process\t"+count++);
				json.put("name_chinese", map.get(key));
			}
			res.add(json.toString());
		}
	
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/数据补全/学院列表/美国/fac1.json");
		
		System.out.println("____end______");
	}

}
