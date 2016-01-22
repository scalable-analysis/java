package com.dulishuo.shenqingfang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class Test {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program55.json", "utf-8");
		List<JSONObject> sch = FileUtil.FileToJsonList("C:/Users/强胜/ins.json", "utf-8");
		Set<Integer> res = new HashSet<Integer>();
		for(JSONObject json : sch){
			System.out.println(Util.id(json.get("id").toString())+"\t"+json.getString("title")+"  "+json.getString("ttitle"));
		}
		/*Map<String,String> map = new HashMap<String,String>();
		HSSFSheet sheet = FileUtil.getExcelSht("C:/Users/强胜/Desktop/数据补全/program/program校对0904.xls");
		for(int i = 0 ; i < 13499 ; i++){
			HSSFRow row= sheet.getRow(i);
			try{
				map.put(row.getCell(1).toString().trim(), row.getCell(2).toString().trim());
			}catch(Exception e){
				System.out.println(e.toString());
				System.out.println(i);
			}
			
		}
		
		System.out.println("read  to map  end!");
		for(JSONObject json : list){
			json.put("ttitle","");
			System.out.println("process__"+count++);
			if(map.containsKey(json.getString("title")))
				json.put("ttitle", map.get(json.getString("title")));
			res.add(json.toString());
		}*/
		Map<Integer,String> map1 = new HashMap<Integer,String>();
		for(JSONObject json : sch){
			map1.put(Util.id(json.get("id").toString()), json.getString("ttitle"));
		}
		Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
		for(JSONObject json : list){
			int ins = json.getInt("institute_id");
			List<String> xx;
			if(map.containsKey(ins)){
				xx = map.get(ins);
			}else{
				xx = new ArrayList<String>();
			}
			xx.add(json.getInt("id")+"\t"+json.getString("title")+"\t"+json.getString("ttitle")+"\t"+json.getString("term")+"\t"+json.getString("subject").replace("\n", ""));
			map.put(ins, xx);
		}
		for(int xx : map.keySet()){
			List<String> yy = map.get(xx);
			FileUtil.ListToFile(yy, "C:/Users/强胜/Desktop/dataCrawler/申请方/按学校分类/"+xx+"-"+map1.get(xx)+".txt");
		}
		//FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/匹配了学校+id.json");
		/*for(JSONObject json : list){
			if(!json.getString("ttitle").equals(""))
				map.put(json.getString("title"), json.getString("ttitle"));
			else
				map.put(json.getString("title"), "-1");
		}
		
		for(Entry<String,String> entry : map.entrySet())
			if(!entry.getValue().equals("-1"))
				res.add(entry.getKey()+"\t"+entry.getValue());
		for(Entry<String,String> entry : map.entrySet()){
			System.out.println("process__"+count++);
			if(entry.getValue().equals("-1"))
				res.add(entry.getKey()+"\t"+Util.trans(entry.getKey()));
		}*/
			
		
		//FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/pgm翻译0921.txt");
		System.out.println("______end___________");
	}

}
