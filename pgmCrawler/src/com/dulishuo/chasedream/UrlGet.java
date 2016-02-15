package com.dulishuo.chasedream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dulishuo.util.CrawlerUtil;

public class UrlGet {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String urlBasic = "http://forum.chasedream.com/forum.php?mod=forumdisplay&fid=14&typeid=230&filter=typeid&typeid=230&page=";
		BufferedWriter bw  = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/商学院Master申请区/选校/url.txt"));
		
		for(int i = 1 ; i < 11 ; i++){
			for(String xx : parseHtml(CrawlerUtil.httpRequest(urlBasic+i))){
				bw.write("http://forum.chasedream.com/"+xx);
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
		Element ele = html.getElementById("threadlist");
		Elements sibling = ele.getElementById("separatorline").siblingElements();
		int size = sibling.size();
		int start = ele.getElementById("separatorline").elementSiblingIndex()+1;
		for(int i = start ; i < size ; i++){
			Element each = sibling.get(i);
			String date = each.getElementsByClass("by").get(0).getElementsByTag("em").get(0).getElementsByTag("span").get(0).text().replace("&nbsp;", "");
			System.out.println(date);
		}
		
		/*for(Element element : tbody){
			String url = element.getElementsByClass("icn").select("a").attr("href").toString();
			System.out.println("url__"+url);
			result.add(url);
		}
		*/
		
		return result;	
	}
}
