package com.dulishuo.offer.shuige;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . ");
		//tmp();
		//haha();
		statistic();
		
		System.out.println("Exit . . . ");
	}

	private static void statistic() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/institute12121l.json", "utf-8");
		Set<String> set = new HashSet<String>();
		set.add("pr");set.add("journalism");set.add("tesol");set.add("cs");set.add("marketing");
		set.add("accounting");set.add("finance");set.add("economics");set.add("mis");set.add("law");
		
		Map<String , Map<String , String>> map = new HashMap<String , Map<String , String>>();
		Map<String , Set<String>> mapp = new HashMap<String , Set<String>>();
		Map<String , Set<String>> mappp = new HashMap<String , Set<String>>();
		for(JSONObject json : list){
			if(json.containsKey("application_difficulty")){
				JSONObject apply = json.getJSONObject("application_difficulty");
				JSONObject  pp;
				int level = -1;
				String hot = "热";
				Set<String> tmp  ;
				for(String xx : set){
					if(apply.containsKey(xx)){
						pp = apply.getJSONObject(xx);
						level = pp.getInt("level");
						if(pp.getInt("hot") == 1)
							hot="中";
						if(pp.getInt("hot") == 2)
							hot="冷";
						if(mapp.containsKey(xx))
							tmp = mapp.get(xx);
						else
							tmp = new HashSet<String>();
						tmp.add(Util.id(json.get("id").toString())+"-"+json.getString("ttitle")+"\t\t"+hot+"\t\t"+level+"\t\t"+status(apply.getJSONObject(xx)));
						mapp.put(xx, tmp);
					}
				}
				/*Map<String , String> tmp = new HashMap<String , String>();
				for(String xx : set){
					if(apply.containsKey(xx)){
						tmp.put(xx, status(apply.getJSONObject(xx)));
					}
				}
				map.put(Util.id(json.get("id").toString())+"-"+json.getString("ttitle"), tmp);*/
				
			}
		}
		//System.out.println("一共有 "+map.size()+" 个学校存在不同程度的offer三围成绩缺失！");
		System.out.println("学校\t\t热度\t\t"+"等级\t\t"+"统计");
		
		System.out.println("**************************************************************************************");
		for(String xx : mapp.keySet()){
			System.out.println(xx);
			System.out.println("-------------------------");
			for(String yy : mapp.get(xx))
				System.out.println(yy);
			System.out.println("**************************************************************************************");
			System.out.println();
			System.out.println();
		}
	}
	
	static String status(JSONObject json){
		String res = "";
		String[] language = new String[3];
		language[0] = "gpa";
		language[1] = "toefl";
		language[2] = "gre";
		for(int i = 0 ; i < language.length ; i++){
			if(json.get(language[i]).toString().indexOf("-") != -1)
				res += 0;
			else
				res += 1;
		}
		
		
		return res;
	}

	private static void haha() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/121232xx.json", "utf-8");
		
		Map<String,List<Integer>> res = new HashMap<String,List<Integer>>();
		Map<String , Integer> map = new HashMap<String , Integer>();
		map.put("xuyue",0);
		map.put("xurun",0);
		map.put("mochengkang",0);
		map.put("fanlei",0);
		map.put("jianzhi11",0);
		map.put("jianzhi33",0);
		map.put("jianzhi66",0);
		map.put("jianzhi88",0);
		map.put("other", 0);
		for(JSONObject json : list){
			List<Integer> tmp;
			if(json.getString("marker").length() >2){
			if(res.containsKey(json.getString("marker"))){
				tmp = res.get(json.getString("marker"));
				if(!tmp.contains(json.getInt("flag"))){
					tmp.add(json.getInt("flag"));
					map.put(json.getString("marker"), map.get(json.getString("marker"))+1);
				}
			}else{
				tmp = new ArrayList<Integer>();
				tmp.add(json.getInt("flag"));
				map.put(json.getString("marker"), map.get(json.getString("marker"))+1);
			}
				res.put(json.getString("marker"), tmp);
			}else{
				if(res.containsKey("other")){
					tmp = res.get("other");
					if(!tmp.contains(json.getInt("flag"))){
						tmp.add(json.getInt("flag"));
						map.put("other", map.get("other")+1);
					}
				}else{
					tmp = new ArrayList<Integer>();
					tmp.add(json.getInt("flag"));
					map.put("other", map.get("other")+1);
				}
					res.put("other", tmp);
			}
			
			
		}
		
		for(String xx : map.keySet())
			System.out.println(xx+" --- "+map.get(xx));
		
	}

	private static void tmp() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/jianzhioffer1209.json", "utf-8");
		Map<String,Integer> map = new HashMap<String,Integer>();
		String currentschool = "";
		String marker = "";
		String gpa = "";
		String year = "";
		String total = "";
		String institute = "";
		String applicant = "";
		String toefl = "";
		String gre = "";
		String intership = "";
		String research = "";
		String work = "";
		String major;
		String competition = "";
		int flag = 1;
		List<String> res = new ArrayList<String>();
		for(JSONObject json : list){
			try{
				applicant = json.getString("applicant");
				currentschool = json.getString("currentschool");
				
				major = json.getString("major");
				marker = json.getString("marker");
				gpa = json.getString("gpa").replace("\"", "").replace(".0", "").trim();
				
				year = json.getString("year");
				
				toefl = json.get("toefl").toString();
				gre = json.get("oldGre").toString();
				intership = json.get("internship").toString();
				research = json.get("research").toString();
				work = json.get("work").toString();
				competition = json.get("competition").toString();
				//total = marker+"\t"+applicant+"\t"+institute+"\t"+major+"\t"+gpa+"\t"+toefl+"\t"+gre+"\t"+year+"\t"+currentschool+"\t"+intership+"\t"+research+"\t"+work+"\t"+competition;
				total = marker+"\t"+applicant+"\t"+major+"\t"+gpa+"\t"+toefl+"\t"+gre+"\t"+year+"\t"+currentschool+"\t"+intership+"\t"+research+"\t"+work+"\t"+competition;
				
				if(!map.containsKey(total)){
					map.put(total, flag);
					json.put("flag", flag);
					flag++;
				}
				else
					json.put("flag", map.get(total));
				res.add(json.toString());
			}catch(Exception e){}
		}
		System.out.println(flag);
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/121232xx.json");
	}

}
