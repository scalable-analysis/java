package com.dulishuo.dict;

import java.util.*;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class PgmJCdict {
	static int count = 0;
	public static void main(String[] args) {
		//jiancheng();
		ToDict();
	}
	private static void ToDict() {
		// TODO Auto-generated method stub
		List<JSONObject> pgm = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/programRK11.json", "utf-8");
		List<String> res = new ArrayList<String>();
		//res.add(Util.id(json.get("id").toString())+",entity:school:"+Util.id(json.get("institute_id").toString())+",regex:"+json.getString("title"));
		//res.add(Util.id(json.get("id").toString())+",entity:school:"+Util.id(json.get("institute_id").toString())+",regex:"+json.getString("ttitle"));
		for(JSONObject json : pgm){
			if(json.containsKey("institute_id")){
				if(Util.id(json.getString("institute_id")) != -1){
					String title = json.getString("title");
					String ttitle = json.getString("ttitle");
					if(ttitle.contains("（"))
						ttitle = ttitle.replaceAll("\\（[\\s\\S]*?\\）", "");
					if(ttitle.contains("("))
						ttitle = ttitle.replaceAll("\\([\\s\\S]*?\\)", "");

					if(ttitle.contains("/硕士") || ttitle.contains("/博士"))
						ttitle = ttitle.replace("/硕士", "").replace("/博士", "");
					if(ttitle.contains("硕士") || ttitle.contains("博士"))
						ttitle = ttitle.replace("硕士", "").replace("博士", "");
					res.add(Util.id(json.get("id").toString())+",entity:school:"+Util.id(json.get("institute_id").toString())+",regex:"+ttitle);
					
					if(title.contains("("))
						title = title.replaceAll("\\([\\s\\S]*?\\)", "");
					if(title.contains("（"))
						title = title.replaceAll("\\（[\\s\\S]*?\\）", "");
					res.add(Util.id(json.get("id").toString())+",entity:school:"+Util.id(json.get("institute_id").toString())+",regex:"+title);

				}
			}
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/dict/program.csv");
		System.out.println("_____exit____");
	
	}
	private static void jiancheng() {
		// TODO Auto-generated method stub
			List<String> dict = FileUtil.FileToList("C:/Users/强胜/Desktop/服务器字典/专业简称.txt");
			List<JSONObject> pgm = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/programRK11.json", "utf-8");
			List<String> result = new ArrayList<String>();
			Set<String> jc = new HashSet<String>();
			Set<String> dicts = new HashSet<String>();
				
				for(String xx : dict){
					if(dicts.contains(xx))
						System.out.println(xx);
					dicts.add(xx);
					
				}
				System.out.println(dicts.size());
				for(JSONObject json : pgm){
					System.out.println("process\t"+(count++));
					json.put("abbreviation", -1);
					if(json.containsKey("title")){
						Set<String> set = Util.extractWord(json.getString("title"),1);
						String ab = "";
						int flag = 0;
						for(String xx : dict){
							if(set.contains(xx.toLowerCase())){
								flag++;
								if(flag == 1)
									ab = xx;
								else
									ab = ab +"-" +xx;
								if(json.containsKey("institute_id")){
									if(Util.id(json.getString("institute_id")) != -1){
										jc.add(Util.id(json.get("id").toString())+",entity:school:"+Util.id(json.get("institute_id").toString())+",keyword:"+xx);
									}
								}
							}
						}
						String[] abt = ab.split("-");
					
							
						json.put("abbreviation", abt);
					}
					result.add(json.toString());
				}
				List<String> ls = new ArrayList<String>();
				for(String xx : jc)
					ls.add(xx);
				//FileUtil.ListToFile(result, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/dict/pgm简称.json");
				FileUtil.ListToFile(ls, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/dict/pgm简称字典.txt");
		/*		List<String> dict = FileUtil.FileToList("C:/Users/强胜/Desktop/服务器字典/program/pgm简称字典.txt");
				Set<String> dicts = new HashSet<String>();
				List<String> jc = new ArrayList<String>();
				for(String xx : dict){
					
					
					if(!dicts.contains(xx)){
						dicts.add(xx);
						jc.add(xx);
					}
						
					
					
				}

				FileUtil.ListToFile(jc, "C:/Users/强胜/Desktop/服务器字典/program/pgm简称字典11.txt");*/
				
				System.out.println("___________end___________");
	}

}
