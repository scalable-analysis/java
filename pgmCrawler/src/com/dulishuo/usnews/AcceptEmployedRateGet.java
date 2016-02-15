package com.dulishuo.usnews;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ch.epfl.lamp.fjbg.JConstantPool.Entry;

import com.dulishuo.util.FileUtil;

public class AcceptEmployedRateGet {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> src = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/usnews/result.json","utf-8");
		List<String> result = new ArrayList<String>();
		for(JSONObject json : src){
			System.out.println("process____"+(count++));
			Set<String> set = json.keySet();
			Map<String,Object> map = new HashMap<String,Object>();
			for(String xx : set){
				if(xx.equals("name"))
					map.put("name", json.getString("name"));
				else if(!xx.equals("rank")){
					System.out.println(xx);
					JSONArray ja = json.getJSONArray(xx);
					JSONArray faja = new JSONArray();
					if(ja.size() == 1){
						Set<String> ss = ja.getJSONObject(0).keySet();
						Map<String,String> pgmap = new HashMap<String,String>();
						for(String yy : ss){
							if(process(yy) != "-1")
								pgmap.put(yy, ja.getJSONObject(0).getString(yy));								
						}
						if(pgmap.size() != 0){
							pgmap.put("name", ja.getJSONObject(0).getString("name"));	
							JSONObject pgj = JSONObject.fromObject(pgmap);
							faja.add(pgj);
							map.put(xx, faja);
						}
					}else{
						for(int i = 0 ; i < ja.size() ; i++){
							Set<String> ss = ja.getJSONObject(0).keySet();
							Map<String,String> pgmap = new HashMap<String,String>();
							for(String yy : ss){
								if(process(yy) != "-1")
									pgmap.put(yy, ja.getJSONObject(0).getString(yy));								
							}
							if(pgmap.size() != 0){
								pgmap.put("name", ja.getJSONObject(0).getString("name"));	
								JSONObject pgj = JSONObject.fromObject(pgmap);
								faja.add(pgj);
							}
						}
						map.put(xx, faja);
					}
				}					
			}
			JSONObject rstj = JSONObject.fromObject(map);
			result.add(rstj.toString());
		}
		
		FileUtil.ListToFile(result, "C:/Users/强胜/Desktop/dataCrawler/usnews/result11.json");
		System.out.println("+++____end____+++");
	}
	
	public static String process(String str){
		String result = "-1";
		if(str.equals("name"))
			return result;
		else if(str.toLowerCase().indexOf("acceptance") != -1 && str.toLowerCase().indexOf("rate") != -1)
			return str;
		else if(extractWord(str).contains("gra"))
			return str;
		else if(extractWord(str).contains("gre"))
			return str;
		else if(extractWord(str).contains("toefl"))
			return str;
		else if(extractWord(str).contains("ielts"))
			return str;
		else if(extractWord(str).contains("gmat"))
			return str;
		else if(str.toLowerCase().indexOf("enrollment") != -1)
			return str;
		else if(str.toLowerCase().indexOf("graduates") != -1 && str.indexOf("employed") != -1)
			return str;
		return result;
	}
	
	public static Set<String> extractWord(String src){
		Set<String> rst = new HashSet<String>();		
		String reg = "\\w+";
		Pattern ptn = Pattern.compile(reg);
		Matcher matcher = ptn.matcher(src.toLowerCase());
		
		while(matcher.find()){
			rst.add(matcher.group());			
		}		
		return rst;
	}

}
