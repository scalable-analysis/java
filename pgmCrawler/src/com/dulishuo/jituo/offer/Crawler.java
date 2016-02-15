package com.dulishuo.jituo.offer;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dulishuo.util.CrawlerUtil;
import com.dulishuo.util.FileUtil;

public class Crawler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		System.out.println("start . . . ");
		
		test();
		
		long end = System.currentTimeMillis();		
		System.out.println("___end___"+(end-start)/1000+"秒");
	}

	private static void test() {
		// TODO Auto-generated method stub
		
		String url ; 
		String response ; 
		List<String> list = new ArrayList<String>();
		for(int i = 1 ; i < 34 ; i++){
			try {
				Thread.currentThread().sleep(100);
				System.out.println(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = "http://www.gter.net/offer/index/ajax?page="+i+"&page_num=24&kw=";
			response = CrawlerUtil.httpRequest(url);
			JSONObject json = JSONObject.fromObject(response);
			JSONArray ja = json.getJSONArray("data");
			for(int j = 0 ; j < ja.size() ; j++)
				list.add(ja.getJSONObject(j).toString());
		}
		
		FileUtil.ListToFile(list, "C:/Users/强胜/Desktop/圣诞/gter/toto.json");
	}

}
