package com.dulishuo.jituo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.dulishuo.util.CrawlerUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . ");
		long start = System.currentTimeMillis();
		
		test();
		
		long end = System.currentTimeMillis();
		System.out.println("end . . . , use time : "+(end-start)/1000 +" ms . ");
	}

	private static void test() {
		// TODO Auto-generated method stub
		String url = "http://www.gter.net/offer/index/ajax?page=1&page_num=24&kw=";
		
		String res = CrawlerUtil.httpRequest(url);
		
		//Document doc = Jsoup.parse(res);
		System.out.println(res);
		//System.out.println(doc.getElementById("offer-list-content").text());
		
	}
}
