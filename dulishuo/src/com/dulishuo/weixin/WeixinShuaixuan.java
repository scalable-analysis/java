package com.dulishuo.weixin;

import com.dulishuo.util.FileUtil;

import java.util.*;

import net.sf.json.JSONObject;

public class WeixinShuaixuan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> fail = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/搜狗微信15-09-10/resultIns/urlfail.txt");
		List<String> list = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/搜狗微信15-09-10/resultIns/url4.txt");
		List<String> sch = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/搜狗微信15-09-10/关键词Ins/微信文章关键词4.txt");
		Set<String> set = new HashSet<String>();
		List<String> res = new ArrayList<String>();
		for(String xx : list){
			JSONObject json = JSONObject.fromObject(xx);
			
			set.add(json.getString("type_id"));
		}
		for(String xx : sch){
			if(!set.contains(xx.split("\t")[2]) && !fail.contains(xx)){
				res.add(xx);
			}
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/搜狗微信15-09-10/关键词Ins/微信文章关键词5.txt");
	
		System.out.println("$_____________end________________$");
	}

}
