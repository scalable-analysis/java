package com.dulishuo.indexedu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dulishuo.util.CrawlerUtil;

public class UrlGet {
	static int count = 1;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String urlBasic = "http://www.indexedu.com/apply/cost/list_65_";
		BufferedWriter bw  = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/dataCrawler/索学网/url_留学费用.txt"));
		
		for(int i = 0 ; i < 31 ; i++){
			System.out.println("process___"+(count++));
			for(String xx : parseHtml(CrawlerUtil.httpRequest(urlBasic+i+".html"))){
				bw.write(xx);
				bw.newLine();
				bw.flush();
			}
		}
		bw.close();
		System.out.println("____end_____");
	}
				
	public static List<String> parseHtml(String str){
		List<String> result = new ArrayList<String>();
		
		Document html = Jsoup.parse(str);
		Element ele = html.getElementById("content");
		Elements sibling = ele.getElementsByClass("nr");
		
		
		
		for(Element element : sibling){
			String url = element.children().select("a").first().attr("href").toString();
			result.add(url);
		}
		
		
		return result;
	}
}
