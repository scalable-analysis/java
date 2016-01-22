package com.dulishuo.program;

import java.util.List;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import java.util.*;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class EarseGuoshiPgm {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<JSONObject> list = FileUtil.FileToJsonList("H:/独立说/backup/program1013.json", "utf-8");
	
		List<String> res = new ArrayList<String>();
		Map<Integer,JSONObject> map = new HashMap<Integer,JSONObject>();
		int max = 0 ;
		for(JSONObject json : list){
			int id = Util.id(json.get("id").toString());
			/*if(json.containsKey("term")){
				String term = json.getString("term");
				//if(deadline.contains("2015") &&  term.contains("2016"))
				if(term.contains("2016"))
					//if(deadline.substring(0, 4).equals("2015") && (deadline.substring(5, 6).equals("1") || Integer.parseInt(deadline.substring(6, 7)) > 9)){
						map.put(id, json);	
					//}
						
			}*/
			max = max > id ? max : id;
		}
		System.out.println(max);
	/*	System.out.println(list.size());
		System.out.println(map.size());
		
		System.out.println("----");
		List<String> tt = new ArrayList<String>();
		for(JSONObject json : list){
			int flag = 0;
			for(Entry<Integer,JSONObject> entry : map.entrySet()){
				
				JSONObject ja = entry.getValue();
				try{
					if(Util.id(json.get("id").toString()) != entry.getKey()  && ja.getString("title").equals(json.getString("title")) && Util.id(ja.get("institute_id").toString())==Util.id(json.get("institute_id").toString())){
						
							String term = json.getString("term");
							if(term.contains("2015") || term.contains("2014")){
									
									//System.out.println((count++)+"____"+json.toString());
									//System.out.println((count)+"____"+ja.toString());
									flag++;
									kk++;
									if(flag==1 && kk > 0){
										tmp = ja.toString();
										count++;
									}
									if(flag>1 && kk > 0){
										System.out.println((count)+"\t"+flag+"____"+json.toString());
										System.out.println((count)+"\t"+flag+"____"+tmp);
										System.out.println((count)+"\t"+flag+"____"+ja.toString());
									}
									term = term.replace("2015", "2016").replace("2014", "2016");
									if(term.equals(ja.getString("term"))){
										tt.add(json.get("id").toString()+"\t"+entry.getKey().toString());
										flag = 1;
										System.out.println(count++);
										break;
									}
									
							}								
						}
					
				}catch(Exception e){
					
				}
			}
			if(flag == 0)
				res.add(json.toString());
		}
		
		System.out.println(res.size());
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program1013.json");
		FileUtil.ListToFile(tt, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/过时的program取代表1013.txt");
		System.out.println("_______end_______");*/
	}

}
