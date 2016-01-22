package com.dulishuo.test;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class test08261703 {
	
	static Set<String> setStop = new HashSet<String>();
	static{
		setStop.add("univeristy");
		setStop.add("college");
		setStop.add("institute");
		setStop.add("in");
		setStop.add("and");
		setStop.add("the");
		setStop.add("of");
		setStop.add("at");
		setStop.add("subject");
		setStop.add("major");
		setStop.add("master");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> result = FileUtil.FileToList("C:/Users/强胜/Desktop/school_macthed11.json");
		List<String> result1 = FileUtil.FileToList("C:/Users/强胜/Desktop/city.json");
		List<String> ins = FileUtil.FileToList("C:/Users/强胜/institute.json");
		List<String> res = new ArrayList<String>();
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		Map<String,Integer> map1 = new HashMap<String,Integer>();
		for(String inss : result1){
			JSONObject json = JSONObject.fromObject(inss);
			map1.put(json.getString("title"), json.getInt("id"));
		}
		for(String inss : result){
			JSONObject json = JSONObject.fromObject(inss);
			if(json.getInt("institute_id") != -1)
				map.put(json.getInt("institute_id"), map1.get(json.getString("city")));
		}
		
		
		/*for(String rs : ins){
			JSONObject json = JSONObject.fromObject(rs);
			json.remove("id");
			json.put("institute_id", -1);
			String title = json.getString("title");
			//System.out.println(title);
			for(Entry<String,Integer> entry : map.entrySet()){
				if(entry.getKey().equals(title)){
					json.put("institute_id", entry.getValue());
					System.out.println(entry.getKey()+"_____"+title);
					break;
				}
				else{
					Set<String> src = extractWord(title);
					Set<String> dst = extractWord(entry.getKey());
					if(isSetEqual(src,dst)){
						json.put("institute_id", entry.getValue());
						System.out.println(entry.getKey()+"_____"+title);
						
						break;
					}
				}
			}
			res.add(json.toString());
			
		}*/
		
		for(String xx : ins){
			JSONObject json = JSONObject.fromObject(xx);
			json.put("city", -1);
			if(map.containsKey(json.getJSONObject("id").getInt("$numberLong"))){
				json.put("city", map.get(json.getJSONObject("id").getInt("$numberLong")));
			}
			res.add(json.toString());
				
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/institute11.json");
		
		System.out.println("___end____");
	}
	
	public static boolean isSetEqual(Set set1, Set set2){
		 if(set1 == null && set2 == null){
				return true; //Both are null
			}

			if (set1 == null || set2 == null || set1.size() != set2.size()
					|| set1.size() == 0 || set2.size() == 0) {
				return false;
			}

			Iterator ite1 = set1.iterator();
			Iterator ite2 = set2.iterator();

			boolean isFullEqual = true;
			
			while (ite2.hasNext()) {
				if (!set1.contains(ite2.next())) {
					isFullEqual = false;
				}
			}

			return isFullEqual;
		}
	
	public static Set<String> extractWord(String src){
		Set<String> rst = new HashSet<String>();		
		String reg = "\\w+";
		Pattern ptn = Pattern.compile(reg);
		Matcher matcher = ptn.matcher(src.toLowerCase());
		
		while(matcher.find()){
			if(!setStop.contains(matcher.group())){
				rst.add(matcher.group());
			}			
		}		
		return rst;
	}

}
