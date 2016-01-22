package com.dulishuo.QS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class uniRank {
	static int count = 1;
	public static void main(String[] args) {
		List<String> list = FileUtil.FileToList("C:/Users/强胜/Desktop/杂项/QS.txt");
		List<JSONObject> sch = FileUtil.FileToJsonList("C:/Users/强胜/institute.json", "utf-8");
		Map<String , Integer> map = new HashMap<String,Integer>();
		for(JSONObject json : sch){
			int id = Util.id(json.get("id").toString());
			String title = json.getString("title");
			map.put(title, id);
		}
		
		String title = "";
		for(String xx : list){
			title = xx.split("\t")[2];
			String id = "-1";
			for(String zz : map.keySet()){
				if(Util.isSame(title,zz)){
					id = map.get(zz).toString();
				}
			}
			System.out.println(xx.split("\t")[0]+"\t"+title+"\t"+id);
		}
	
		System.out.println("___end____");
	}

}
