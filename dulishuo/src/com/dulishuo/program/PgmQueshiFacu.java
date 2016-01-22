package com.dulishuo.program;

import java.util.*;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class PgmQueshiFacu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> xx = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/program/matched_program.json", "utf-8");
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		for(JSONObject json : xx){
			String faculty = json.getString("faculty");
			if(map.containsKey(faculty))
				map.put(faculty, map.get(faculty)+1);
			else
				map.put(faculty, 1);
		}
		
		map = sortMap(map);
		
		for(Entry<String,Integer> entry : map.entrySet())
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		
	}
	
	public static Map sortMap(Map oldMap) {  
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
  
            @Override  
            public int compare(Entry<java.lang.String, Integer> arg0,  
                    Entry<java.lang.String, Integer> arg1) {  
                return arg1.getValue() - arg0.getValue();  
            }  
        });  
        Map newMap = new LinkedHashMap();  
        for (int i = 0; i < list.size(); i++) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        }  
        return newMap;  
    }  

}
