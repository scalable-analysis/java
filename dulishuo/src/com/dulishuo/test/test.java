package com.dulishuo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.RowFilter.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.sl.usermodel.Sheet;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class test {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		
		//hehe();
		test();
		long end = System.currentTimeMillis();		
		System.out.println("___end___"+(end-start)/1000+"秒");
	}
	private static void test() {
		// TODO Auto-generated method stub
		
		try {
			String str = "";
			System.out.println(Float.parseFloat(str));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void hehe() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/programappend1225.json", "utf-8");
		HSSFSheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/圣诞/merry.xls", 0);
		
		List<String> name = FileUtil.FileToList("C:/Users/强胜/Desktop/圣诞/hehe.txt");
		Map<String , String> heehda = new HashMap<String , String>();
		heehda.put("一月", "1");
		heehda.put("二月", "2");
		heehda.put("三月", "3");
		heehda.put("四月", "4");
		heehda.put("五月", "5");
		heehda.put("六月", "6");
		heehda.put("七月", "7");
		heehda.put("八月", "8");
		heehda.put("九月", "9");
		heehda.put("十月", "10");
		heehda.put("十一月", "11");
		heehda.put("十二月", "12");
		
		Map<String , Long[]> map = new HashMap<String , Long[]>();
		String value ;
		for(int i = 0 ; i < sht.getLastRowNum()+1; i++){
			try{
			if(!sht.getRow(i).getCell(0).equals(null) && sht.getRow(i).getCell(0).toString().length()>2){
				value = sht.getRow(i).getCell(1).toString().replace("-", "/");
				for(String xx : heehda.keySet()){
					if(value.contains(xx))
						value = value.replace(xx, heehda.get(xx));
				}
				if(value.split("/").length > 4)
					value = value.split("/")[0]+"/"+value.split("/")[1]+"/"+value.split("/")[2]+"-"+value.split("/")[3]+"/"+value.split("/")[4]+"/"+value.split("/")[5];
				if(value.contains("-")){
					Long[] tmp = new Long[2];
					tmp[0] = new Date(value.split("-")[0]).getTime()/1000;
					tmp[1] = new Date(value.split("-")[1]).getTime()/1000;
					map.put(name.get(i), tmp);
				}else{
					Long[] tmp = new Long[1];
					tmp[0] = new Date(value).getTime()/1000;
					map.put(name.get(i), tmp);
				}
				
				System.out.println(value);
			}
			}catch(Exception e){}
		}
		
		List<String> res = new ArrayList<String>();
		int count = 12361;
		for(JSONObject json : list){
			json.remove("id");
			json.remove("_id");
			json.put("id", count++);
			json.put("oldDeadline", json.getString("deadline"));
			if(map.containsKey(json.getString("deadline")))
				json.put("deadline", map.get(json.getString("deadline")));
			else{
				Long[] tmp = new Long[0];
				json.put("deadline", tmp);
			}		
			res.add(json.toString());
		}
		
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/pgmAppend.json");
	}


}
