package com.dulishuo.rank;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import java.util.*;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

public class rankShlQueshi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> rank = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/服务器字典/rank.json", "utf-8");
		List<JSONObject> ranktype = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/服务器字典/ranktype.json", "utf-8");
		List<String> ins = FileUtil.FileToList("C:/Users/强胜/institute.json");
		Set<Integer> set1 = new HashSet<Integer>();
		for(String xx : ins){
			JSONObject json = JSONObject.fromObject(xx);
			set1.add(Util.id(json.get("id").toString()));
		}
		
		/*Map<String,String> maptype = new HashMap<String,String>();
		for(JSONObject json : ranktype){
			maptype.put(json.getJSONObject("id").getString("$numberLong"),json.getString("subject"));
			
		}
		
*/		Map<String,String> map = new HashMap<String,String>();
		Map<String,Integer> map1 = new HashMap<String,Integer>();
		Set<String> set = new HashSet<String>();
		List<String> list = new ArrayList<String>();
		
		for(JSONObject json : rank){
			String belong = json.getJSONObject("rank_type_id").getString("$numberLong");
			int value = Integer.parseInt(json.getJSONObject("value").getString("$numberLong"));
			
			
			if(value<150 && belong.equals("42") && json.get("institute_id").toString() != "null" && !set1.contains(Util.id(json.get("institute_id").toString()))){
				/*if(map.containsKey(json.getString("institute")))
					map.put(json.getString("institute"), map.get(json.getString("institute"))+"!"+maptype.get(belong)+"("+belong+")");
				else
					map.put(json.getString("institute"), maptype.get(belong)+"("+belong+")");		
				
				set.add(json.getString("institute"));
				if(map1.containsKey(json.getString("institute")))
					map1.put(json.getString("institute"), map1.get(json.getString("institute"))+1);
				else
					map1.put(json.getString("institute"), 1);	*/
				System.out.println(json.getString("institute").toString());
				list.add(json.getString("institute").toString());
				
			}
				
			}
			
		
		map1 = sortMap(map1);
		
		for(Entry<String,Integer> entry : map1.entrySet())
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		for(Entry<String,String> entry : map.entrySet())
			list.add(entry.getKey()+"\t"+entry.getValue());
		FileUtil.ListToFile(list, "C:/Users/强胜/Desktop/数据补全/rank缺失TOP150学校专业.txt");
	
		//for(String xx : set)
			//System.out.println(xx);
	
		System.out.println("_____end____");
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
