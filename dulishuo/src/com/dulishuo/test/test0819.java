package com.dulishuo.test;

import java.util.*;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class test0819 {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> result = FileUtil.FileToList("C:/Users/强胜/Desktop/school_macthed11.json");
		List<String> ins = FileUtil.FileToList("C:/Users/强胜/institute.json");
		List<String> res = new ArrayList<String>();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		Set<String> set = new HashSet<String>();
		/*for(String inss : ins){
			JSONObject json11 = JSONObject.fromObject(inss);
		
			json11.put("famouspeople",null);
			json11.remove("acceptance_rate");
			int idd = json11.getJSONObject("id").getInt("$numberLong");
			for(String xx : result){
				try{
					JSONObject json = JSONObject.fromObject(xx);
					System.out.println(json.getJSONObject("id").getInt("$numberLong"));
					if(json.getJSONObject("id").getInt("$numberLong") == idd){
						System.out.println("proce__"+(count++));
						JSONObject yy = json.getJSONObject("schoolefellow");
						JSONArray ja = new JSONArray();
						Set<String> set1 = yy.keySet();
						for(String zz : set1){
							Map<String,Object> map2 = new HashMap<String,Object>();
							map2.put("name", zz);
							map2.put("position", map2.get(zz));
							JSONObject zx = JSONObject.fromObject(map2);
							ja.add(zx);
						}
						json11.put("famouspeople", ja);
						
						break;
					}
				
				}catch(Exception e){
					
				}
			}
			res.add(json11.toString());
		}*/
		for(String xx : result){
			JSONObject json = JSONObject.fromObject(xx);
			if(map1.containsKey(json.getString("city"))){
				if(json.getInt("institute_id") != -1)
					map1.put(json.getString("city"), String.valueOf(map1.get(json.getString("city")))+"-"+json.get("institute_id").toString());
			}
			else{
				if(json.getInt("institute_id") != -1)
					map1.put(json.getString("city"), json.get("institute_id").toString());
			}
				
		}
		System.out.println(map1.size());
		for(String xx : result){
			try{
				JSONObject json = JSONObject.fromObject(xx);
				if(!set.contains(json.getString("city"))){
					set.add(json.getString("city"));
					String climate = "春 "+json.getJSONObject("climate").getString("spring")+"\r\n"+"夏 "+json.getJSONObject("climate").getString("summer")+"\r\n"+"秋 "+json.getJSONObject("climate").getString("autumn")+"\r\n"+"冬 "+json.getJSONObject("climate").getString("winter");
					map.put("title",json.getString("city") );
					map.put("etitle", json.getString("schooladdress").split(",")[1].trim());
					map.put("schooladdress", json.getString("schooladdress"));
					map.put("weather", climate);
					map.put("area", json.getString("position"));
					map.put("security", json.getString("security"));
					map.put("state", json.getString("state"));
					map.put("traffic", json.getString("traffic"));
					map.put("maker", "xiaohe");
					
					map.put("id", count);
					String ids = map1.get(json.getString("city")).toString();
					String[] idds= ids.split("-");
					int size = idds.length;
					int[] iddi = new int[size];
					for(int j = 0 ; j < size ; j++){
						iddi[j] = Integer.parseInt(idds[j]);
					}
					map.put("institute_id", iddi);
					map.put("race_ratio", json.getJSONObject("race_ratio"));
					count++;
					JSONObject yy = JSONObject.fromObject(map);
					res.add(yy.toString());
				}
				
			}catch(Exception e){
				
			}
			
		}
		System.out.println(count);
		
	/*	map = sortMap(map);
		int sum = 0;
		for(Entry<String,Integer> entry : map.entrySet()){
			System.out.println(entry.getKey() + "\t" + entry.getValue());
			if(entry.getValue()>50)
				sum += entry.getValue();
		}*/
	/*	for(String xx : result){
			int i = xx.indexOf("numberLong");
			String in  = xx.substring(i+14, i+19).replace("\"", "").replace(",", "").replace("}", "").replace(" ", "").trim();
			if(set.contains(in))
				res.add(xx);	
		}

			*/
			
		
		/*for(String xx : set)
			System.out.println(xx+"\t"+(count++));*/
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/city.json");
		System.out.println("___end___");
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
