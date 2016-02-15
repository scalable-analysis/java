package com.dulishuo.usnews;

import com.dulishuo.util.FileUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class SchoolMatch {
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

	static int succ = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> sch = FileUtil.FileToList("C:/Users/强胜/Desktop/服务器字典/英国.csv");
		List<JSONObject> require = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/英国专业/ukprogram.json","utf-8");
		Map<String,String> schmap = new HashMap<String,String>();
		List<String> result = new ArrayList<String>();
		List<String> gg = new ArrayList<String>();
		int flag = 1;
		for(String xx : sch){
			schmap.put(xx.split("!")[1], xx.split("!")[0]);
		}
		for(JSONObject json : require){
			String yy = json.getString("institute");
			int max = 0;
			String big = "";
			for(String xx : schmap.keySet()){
				json.put("institute_id", -1);
				if(xx.equals(yy)){
					json.put("institute_id", Integer.parseInt(schmap.get(xx)));
					//System.out.println((succ++)+"___"+yy+"\t"+xx);
					result.add(json.toString());
					break;
				}
				else{
					
				
					Set<String> src = extractWord(yy);
					Set<String> dst = extractWord(xx);
					if(isSetEqual(src,dst)){
						json.put("institute_id", Integer.parseInt(schmap.get(xx)));
						result.add(json.toString());
						break;
					}
						
					
				}			
			}
			
		}
		
	
		System.out.println(result.size());
	
		
		FileUtil.ListToFile(result, "C:/Users/强胜/Desktop/dataCrawler/英国专业/ukprogram1.json");
		
		System.out.println("---+++___end___+++---");
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
}
