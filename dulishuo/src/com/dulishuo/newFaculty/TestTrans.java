package com.dulishuo.newFaculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class TestTrans {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("_____start_____");
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/学院列表/美国/fac3.json", "utf-8");
		Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
		for(JSONObject json : list){
			List<String> tmp;
			if(map.containsKey(json.getInt("institute_id")))
				tmp = map.get(json.getInt("institute_id"));
			else
				tmp = new ArrayList<String>();
			
			tmp.add(json.getInt("id")+"    "+json.getString("name")+"    "+json.getString("name_chinese"));
			map.put(json.getInt("institute_id"), tmp);
		}
		
		for(int xx : map.keySet())
			FileUtil.ListToFile(map.get(xx), "C:/Users/强胜/Desktop/数据补全/学院列表/美国/按学校分类/"+xx+"-"+Util.getSchName(xx,1)+".txt");
		
		System.out.println("----Exit-------");
	}
}
