package com.dulishuo.offer.shuige;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class ShengQF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . \n");
		long start = System.currentTimeMillis();
		
		update();
		
		long end = System.currentTimeMillis();
		System.out.println("\nend use time : " + (end - start) + " ms .");
	}

	private static void update() {
		// TODO Auto-generated method stub
		List<JSONObject> rank = FileUtil.FileToJsonList("C:/Users/强胜/rank1123.json", "utf-8");
		Set<String> set = new HashSet<String>();
		for(JSONObject json : rank){
			if(Util.id(json.get("institute_id").toString()) == -1){
				set.add(json.getString("institute"));
			}
		}
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(String xx : set){
			map.put(xx, Util.getInstitute(xx));
		}
		
		map = Util.sortMap(map);
		
		for(String xx : map.keySet())
			System.out.println(xx+"\t"+Util.getSchName(map.get(xx), 2)+"\t"+Util.getSchName(map.get(xx), 1)+"\t"+map.get(xx));
	}

}
