package com.dulishuo.offer.shuige;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class Process1215 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . ");
		long start = System.currentTimeMillis();
		
		test();
		//hebin();
		
		long end = System.currentTimeMillis();
		System.out.println("end . . . , use time : "+(end-start)/1000 +" ms . ");
	}

	private static void hebin() {
		// TODO Auto-generated method stub
		List<JSONObject> src = FileUtil.FileToJsonList("C:/Users/强胜/jianzhioffer1220.json", "utf-8");
		Map<Integer , List<JSONObject>> map = new HashMap<Integer , List<JSONObject>>();
		Set<Integer> stop = new HashSet<Integer>();
		stop.add(24356);
		stop.add(24158);
		stop.add(23376);
		stop.add(23517);
		stop.add(23506);
		
		int id = -1;
		int flag = -1;
		List<JSONObject> tmp ;
		for(JSONObject json : src){
			id = Util.id(json.get("id").toString());
			flag = Util.id(json.get("flag").toString());
			if(!stop.contains(id)){
				if(map.containsKey(flag))
					tmp = map.get(flag);
				else
					tmp = new ArrayList<JSONObject>();
				
				tmp.add(json);
				map.put(flag, tmp);
			}
		}
		
		List<JSONObject> tmpp ; 
		List<String> res = new ArrayList<String>();
		int size;
		int sssss = 1;
		for(int xx : map.keySet()){
			tmpp = map.get(xx);
			JSONObject yy = tmpp.get(0);
			JSONObject ins = new JSONObject();
			size = tmpp.size();
			int[] intArr = new int[size];
			
			for(int i = 0 ; i < size ; i++){
				System.out.println("process : "+sssss++);
				int insId = tmpp.get(i).getInt("institute_id");
				intArr[i] = insId;
				
				JSONObject insTmp = new JSONObject();
				insTmp.put("en_name", Util.getSchName(insId, 2));
				insTmp.put("zh_name", Util.getSchName(insId, 1));
				insTmp.put("result", tmpp.get(i).getInt("result"));
				ins.put(insId, insTmp);
			}
			yy.remove("result");
			yy.remove("_id");
			yy.remove("institute_id");
			yy.remove("institute");
			yy.put("result", ins);
			yy.put("institute_id", intArr);
			res.add(yy.toString());
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/水哥/first.json");
		
	}

	private static void test() {
		// TODO Auto-generated method stub
		
		List<JSONObject> src = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/水哥/forth.json", "utf-8");
		List<String> res = new ArrayList<String>();
		int count = 1;
		List<String> set = new ArrayList<String>();
		set.add("机智的");
		set.add("可爱的");
		set.add("欢乐的");
		set.add("逗逼的");
		set.add("萌萌的");
		set.add("牛逼的");
		
		set.add("萝莉的");
		set.add("愚钝的");
		set.add("洛基的");
		set.add("神奇的");
		set.add("神气的");
		set.add("抓狂的");
		set.add("萌呆的");
		set.add("魔力的");
		set.add("欢快的");
		Random rd = new Random();
		Map<String , String> map = new HashMap<String , String>();
		for(JSONObject json : src){
			try{
			
				/*String dep = json.get("department_type").toString().replace("[", "").replace("]", "");
				if(!dep.equals("-1")){
					JSONObject js = new JSONObject();
					js.put("en_name", json.getString("major"));
					js.put("zh_name", Util.getFacTypeName(Util.id(dep), 2));
					JSONObject jss = new JSONObject();
					jss.put(Util.id(dep), js);
					json.remove("major");
					json.put("major", jss);
				}
				json.put("old_id", json.getInt("id"));
				json.put("old_cv", json.getInt("cv"));
				json.put("cv", count);
				json.put("id", count++);*/
				
			
			int rad = rd.nextInt(14);
			
			String gg = json.getJSONObject("gre").get("total").toString();
			JSONObject js = json.getJSONObject("gre");
			js.put("total", gg);
			json.put("gre", js);
			json.put("alias", set.get(rad)+json.getString("alias"));
			json.put("year", Integer.parseInt(json.getString("year")));
			res.add(json.toString());
			
			}catch(Exception e){
				System.out.println("出错了");
			}
			//System.out.println(q);
			/*try{
				Matcher mth = ptn.matcher(aw);
				if(!mth.find())
					System.out.println(aw);
			}catch(Exception e){}*/

		}
		
		//File file = new File("C:/Users/强胜/Desktop/12月份/");
	/*	for(File xx : file.listFiles()){
			if(xx.getName().indexOf("pdf") != -1){
				try{
					String yyy = xx.getName().substring(0, xx.getName().indexOf("."));
					if(map.containsKey(yyy)){
						xx.renameTo(new File("C:/Users/强胜/Desktop/12月份/"+map.get(yyy)+".pdf"));
						System.out.println(yyy);
					}
				}catch(Exception e){
					System.out.println(xx.getName());
					break;
				}
			}
		}*/
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/水哥/fifth.json");
	}

}
