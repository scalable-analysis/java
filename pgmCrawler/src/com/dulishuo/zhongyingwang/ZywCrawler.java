package com.dulishuo.zhongyingwang;
import java.io.*;
import java.util.*;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZywCrawler {

	static int count = 1;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/中英网/url.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/dataCrawler/中英网/zywSchool.txt"));
		
		Set<String> set = new HashSet<String>();
		String each_line = "";
		while((each_line = br.readLine()) !=null){
			if(each_line.contains("html")){
				set.add(each_line);
			}
		}
		br.close();
		
		for(String xx : set){
			System.out.println("grab--------"+(count++));
			String response = "";
			response = process(xx);			
			JSONObject json = domParse(response);
			bw.write(json.toString());
			bw.newLine();
			bw.flush();
		}
		bw.close();
		System.out.println("___________end___________");
		
		
	}
		
	 public static String process(String url){
			String result = "";
			
			HttpClient httpClient;
			PostMethod postMethod;
			
		    httpClient = new HttpClient();
			postMethod = new PostMethod(url); 
			
			try {
				httpClient.executeMethod(postMethod);
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				result = postMethod.getResponseBodyAsString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			postMethod.releaseConnection();	
			return result;
		}

	 public static JSONObject domParse(String dom){
		 JSONObject json = null;
		 Map<String,String> map = new HashMap<String,String>();
		 
		 Document html = Jsoup.parse(dom);
		 Elements name = html.getElementsByClass("ny_tit_r_box_title");
		 
		 
		 Elements uls = html.getElementById("schoolzong").getElementsByTag("ul");
		 int size = uls.size();
		 String district = uls.get(0).getElementsByTag("li").get(0).getElementsByTag("a").get(0).text().toString();
		 String city = uls.get(0).getElementsByTag("li").get(1).getElementsByTag("a").get(0).text().toString();
		 String web = uls.get(0).getElementsByTag("li").get(2).select("a").first().attr("href").toString();
		 Elements modes = uls.get(1).getElementsByTag("li").get(0).getElementsByTag("a");
		 String modeS = "";
		 for(Element mode : modes){
			 modeS += mode.text().toString();
		 }
		 String fee = "";
		 try{
			 fee = uls.get(1).getElementsByTag("li").get(1).text().toString();
		 }catch(Exception e){
			 System.out.println(uls.get(1).getElementsByTag("li").text());
		 }
		 
		 String tele = uls.get(size-2).getElementsByTag("li").get(0).text().toString();
		 String post = uls.get(size-2).getElementsByTag("li").get(1).text().toString();
		 String address = uls.get(size-1).getElementsByTag("li").get(0).text().toString();
		
		 
		 String discription = html.getElementsByAttributeValueContaining("onClick", "ga('send','event', '学校中心', '大学概况', '大学概况')").get(0).html();
		 map.put("discription", discription);
		 map.put("district", district);
		 map.put("city", city);
		 map.put("web", web);
		 map.put("fee", fee);
		 map.put("mode", modeS);
		 map.put("tele", tele);
		 map.put("post", post);
		 map.put("address", address);
		 map.put("name", name.get(0).text().toString());
			
		 json = JSONObject.fromObject(map);
		 
		 return json;
	 }

}
