package com.dulishuo.faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class pgmFacShenqf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program55.json", "utf-8");
		
		Sheet sht= FileUtil.getExcelSht("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/待翻译校对.xls",0);
		List<String> res = new ArrayList<String>();
		Map<String,String> map = new HashMap<String,String>();
		
		for(int i = 0 ; i < sht.getLastRowNum()+1; i++){
			map.put(sht.getRow(i).getCell(0).toString(), sht.getRow(i).getCell(1).toString());
		}
		
		for(JSONObject json : list){
			if(map.containsKey(json.getString("title"))) {
				json.put("ttitle", map.get(json.getString("title")));
			}else{
				System.out.println(json.getString("title"));
				if(json.getString("title").substring(0, 2).equals("MS"))
					json.put("ttitle", "人体因素与人体工程学硕士");
				if(json.getString("title").substring(0, 2).equals("MA"))
					json.put("ttitle", "罗马语和文学硕士（授课语言（法语和西班牙语））");
				if(json.getString("title").substring(0, 2).equals("MP"))
					json.put("ttitle", "健康政策和管理硕士");
				if(json.getString("title").substring(0, 2).equals("ph"))
					json.put("ttitle", "环境科学、政策、管理和生态科学博士");
				
			}
				
			res.add(json.toString());
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program66.json");
		System.out.println("____________Exit________");
	}

}
