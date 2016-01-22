package com.dulishuo.oppo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class Test1224 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . \n");
		long start = System.currentTimeMillis();
		
		//test();
		test0105();
				
		long end = System.currentTimeMillis();
		System.out.println("end use time : " + (end - start) + " ms .");
	}

	private static void test0105() {
		// TODO Auto-generated method stub
		Sheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/圣诞/机会产品名称纠正&分级.xls", 0);
		Map<Integer , Integer> mapLevel = new HashMap<Integer , Integer>();
		Map<Integer , String> mapTitle = new HashMap<Integer , String>();
		Map<Integer , String> mapZhtitle = new HashMap<Integer , String>();
		int idd;
		Row row;
		for(int i = 0 ; i < sht.getLastRowNum()+1; i++){
			row = sht.getRow(i);
			idd = Util.id(row.getCell(1).toString());
			mapLevel.put(idd, Util.id(row.getCell(0).toString()));
			mapTitle.put(idd, row.getCell(2).toString());
			mapZhtitle.put(idd, row.getCell(3).toString());
		}
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/0105oppo.json", "utf-8");
		List<String> res = new ArrayList<String>();
		int count = 1;
		for(JSONObject json : list){
			json.remove("_id");
			if(Util.id(json.get("id").toString()) == 12)
				continue;
			
			if(json.getString("from").equals("学校官网")){			
				json.put("title", mapTitle.get(Util.id(json.get("id").toString())));
				json.put("zh_title", mapZhtitle.get(Util.id(json.get("id").toString())));
				json.put("level", mapLevel.get(Util.id(json.get("id").toString())));
				json.put("id", count++);
				res.add(json.toString());
			}else{
				json.put("id", count++);
				res.add(json.toString());
			}
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/圣诞/oppo160105.json");
	}

	private static void test() {
		// TODO Auto-generated method stub
		
		Sheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/圣诞/机会产品XXX.xls", 0);
		Map<Integer , String > map = new HashMap<Integer , String>();
		for(int i = 1 ; i < 43; i++){
			map.put(Util.id(sht.getRow(i).getCell(0).toString()), sht.getRow(i).getCell(2).toString());
		}
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/oppo1223.json", "utf-8");
		
		List<String> res = new ArrayList<String>();
		for(JSONObject json : list){
			json.remove("_id");
			int id = json.getInt("id");
			json.put("zh_title",map.get(id));
			JSONObject js = new JSONObject();
			if(json.getString("type").equals("Visiting"))
				js.put("id", 1);
			if(json.getString("type").equals("Summer school"))
				js.put("id", 2);
			js.put("name", json.getString("type"));
			
			json.remove("type");
			json.put("type", js);
			json.put("comment", "");
			json.put("from", "学校官网");
			json.put("tag", "");
			
			res.add(json.toString());
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/圣诞/oppo入库.json");
	}

}
