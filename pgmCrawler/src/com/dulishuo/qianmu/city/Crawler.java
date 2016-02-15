package com.dulishuo.qianmu.city;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dulishuo.qianmu.Util;
import com.dulishuo.util.CrawlerUtil;
import com.dulishuo.util.FileUtil;

public class Crawler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//usa();
		british();
		
	}

	
	private static void british() {
		// TODO Auto-generated method stub
		String root_url = "http://www.qianmu.org/%E5%8A%A0%E6%8B%BF%E5%A4%A7%E5%9C%B0%E5%8C%BA%E5%9F%8E%E5%B8%82%E5%88%97%E8%A1%A8";
		String response = Util.crawler(root_url);
		process_british(response);
		System.out.println("End-------");
	}


	private static void process_british(String str) {
		// TODO Auto-generated method stub
		List<String> res = new ArrayList<String>();
		Document doc = Jsoup.parse(str);
		Elements hrefs = doc.getElementById("content").getElementsByTag("a"); 
		String href;
		for(Element ele : hrefs){
			href = ele.attr("href").toString();
			
			if(!ele.text().equals("英格兰")){
				try{
					JSONObject json = new JSONObject();
					href = "http://www.qianmu.org/"+URLEncoder.encode(href.split("org/")[1],"utf-8");
					String response = Util.crawler(href);
					
					Document cDoc = Jsoup.parse(response);
					String introduce = cDoc.getElementById("content").html();
					if(!href.equals("http://www.qianmu.org/%E8%92%99%E7%89%B9%E5%88%A9%E5%B0%94")){
						String cityInfo = cDoc.getElementsByClass("infobox").get(0).getElementsByTag("table").get(0).html();
						json.put("cityInfo", cityInfo);
					}
					json.put("introduce", introduce);
					
					json.put("country", "canada");
					json.put("name", ele.text());
					res.add(json.toString());
					
				}catch(Exception e){
					System.out.println(href);
				}
			}
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/迁木网/地区城市/加拿大.json");
	}


	private static void usa() {
		// TODO Auto-generated method stub
		String root_url = "http://www.qianmu.org/%E7%BE%8E%E5%9B%BD%E5%9C%B0%E5%8C%BA%E5%9F%8E%E5%B8%82%E5%88%97%E8%A1%A8";
		String response = Util.crawler(root_url);
		process_usa(response);
		System.out.println("End-------");
	}


	private static void process_usa(String str) {
		// TODO Auto-generated method stub
		
		List<String> res = new ArrayList<String>();
		Document doc = Jsoup.parse(str);
		Element content = doc.getElementById("content");
		Elements states = content.getElementsByTag("h2");
		Elements citys = content.getElementsByTag("a");
		
		Set<String> statSet = new HashSet<String>();
		for(Element ele : states){
			statSet.add(ele.text().toString());
		}
		
		int flag = 1;
		for(Element ele : citys){
			String name = ele.text();
			if(statSet.contains(name))
				continue;
			if(name.equals("阿肯色州") || name.equals("爱荷华州") || name.equals("怀俄明州") || name.equals("华盛顿哥伦比亚特区"))
				continue;
			String href = "";
			try{
				href = ele.attr("href").toString();
			}catch(Exception e){
				href = "http://www.qianmu.org/"+name;
				
			}
			
			if(href.contains("华盛顿") || href.contains("波特兰") || href.contains("达拉斯")){
				try {
					href = "http://www.qianmu.org/"+URLEncoder.encode(name.substring(0, 3),"utf-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			try{
				String response = Util.crawler(href);
				
				Document cDoc = Jsoup.parse(response);
				String introduce = cDoc.getElementById("content").html();
				String cityInfo = cDoc.getElementsByClass("infobox").get(0).getElementsByTag("table").get(0).html();
				
				JSONObject json = new JSONObject();
				json.put("introduce", introduce);
				json.put("cityInfo", cityInfo);
				json.put("country", "USA");
				json.put("name", name);
				res.add(json.toString());
				
			}catch(Exception e){
				System.out.println(href);
			}
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/迁木网/地区城市/美国.json");
	}

}
