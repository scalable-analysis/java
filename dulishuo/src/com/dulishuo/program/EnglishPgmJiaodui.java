package com.dulishuo.program;

import net.sf.json.JSONObject;

import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class EnglishPgmJiaodui {
	static int count = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		//List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/faculty.json", "utf-8");
		List<String> list = FileUtil.FileToList("C:/Users/强胜/Desktop/dict/faculty.csv");
		Set<String> set = new HashSet<String>();
		List<String> result = new ArrayList<String>();
		for(String xx : list){
			if(!set.contains(xx)){
				set.add(xx);
				result.add(xx);
			}
			else
				System.out.println(xx);
				
		}
		
		/*
		HSSFSheet sheet= FileUtil.getExcelSht("C:/Users/强胜/Downloads/program校对0904.xls");
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(int i = 0 ; i < sheet.getLastRowNum()+1; i ++){
			int id = Integer.parseInt(sheet.getRow(i).getCell(0).toString().replace(".0", ""));
			String chi  = sheet.getRow(i).getCell(2).toString().trim();
			map.put(id, chi);
		}*/
	/*	for(JSONObject json : list){
			//System.out.println("process\t"+(count++));
			int id = Util.id(json.get("id").toString());
			
				String tit = json.getString("name").toString();
				String ttit = json.getString("name-chinese").toString();
			int ins = Util.id(json.get("institute_id").toString());
			
			result.add(id+",entity:school:"+ins+",regex:"+ttit);
			result.add(id+",entity:school:"+ins+",keyword:"+tit);
							
			}
				*/	
			
		
		FileUtil.ListToFile(result, "C:/Users/强胜/Desktop/faculty字典.txt");
		long end = System.currentTimeMillis();
		System.out.println("end______"+((end-start)/1000)+"秒");
	}

}
