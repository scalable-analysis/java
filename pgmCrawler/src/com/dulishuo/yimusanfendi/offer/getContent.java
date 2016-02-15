package com.dulishuo.yimusanfendi.offer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class getContent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> urls = FileUtil.FileToList("result/urls.txt");
		
		BufferedWriter bw = FileUtil.FileWriter("result/offer.json");
		int count = 1;
		for(String xx : urls){
			try{
				System.out.println("process____"+count++);
				JSONObject json = new JSONObject();
				json.put("mark", xx.split("\t")[1]);
				json.put("url",xx.split("\t")[0]);
				String url = xx.split("\t")[0];
				
				String response = httpRequest(url);
			    Document html = Jsoup.parse(response);
			    String title = html.getElementById("thread_subject").text().toString();
			    
			    
			   
				Element ele = html.getElementById("postlist");
				Elements tbodys = ele.children();		
			 
				String content = tbodys.get(2).getElementsByClass("pcb").get(0).html();
				
				json.put("content", content);
				json.put("title", title);
				
				Element pcb = tbodys.get(2).getElementsByClass("pcb").get(0).getElementsByTag("li").get(0);
				int siz = pcb.getElementsByTag("a").size();
				if(siz > 0){
					String href = pcb.select("a").first().attr("href").toString();
					
					json.put("back_url", href);
					String ress = httpRequest(href);
				    Document htm = Jsoup.parse(ress);
				    
					String con = htm.getElementById("postlist").children().get(2).getElementsByClass("pcb").get(0).html();
					
					json.put("background", con);
				}
				
				try {
					bw.write(json.toString());
					bw.newLine();
					bw.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(Exception e){
				
			}
			
		}
		
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("_____Exit_______");
	}

	

	public static String httpRequest(String url){
		String response = "";
		
		HttpClient client = new HttpClient();
		//设置代理服务器的ip地址和端口  
		//client.getHostConfiguration().setProxy("202.108.23.247", 80);  
		//使用抢先认证  
		//client.getParams().setAuthenticationPreemptive(true); 
		GetMethod getMethod = new GetMethod(url);
		//请求配置
		String ck = "tjpctrl=1443511249981; __utma=142000562.1509796625.1436709690.1443504799.1443507176.20; __utmz=142000562.1443496328.18.11.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; pgv_pvi=3811975168; 4Oaf_61d6_ulastactivity=5cd1JoDhrrDBrq%2BFJy68G%2Fpm9oh%2BNuF9oxspm5Z6lcNCl7Q9zobK; 4Oaf_61d6_lastcheckfeed=175519%7C1443509443; 4Oaf_61d6_nofavfid=1; 4Oaf_61d6_smile=4D1; 4Oaf_61d6_saltkey=KS2COM8X; 4Oaf_61d6_lastvisit=1442223198; 4Oaf_61d6_atarget=1; 4Oaf_61d6_visitedfid=82D79D27; 4Oaf_61d6_sid=Qo6ocY; 4Oaf_61d6_lastact=1443510183%09forum.php%09misc; 4Oaf_61d6_forum_lastvisit=D_27_1443496323D_82_1443504804; __utmc=142000562; 4Oaf_61d6_nofocus_forum=1; 4Oaf_61d6_home_diymode=1; 4Oaf_61d6_auth=28cfq1UHLksHhDfeHHD2FVUjAj%2BM6s5a%2FprmXq61DstgcI8jQhx7i8uyIAaTttaTlqbPIVE%2F3yVwGJks9dpueWM5zHk; 4Oaf_61d6_lip=123.116.122.133%2C1443510173; 4Oaf_61d6_viewid=tid_128680; __utmb=142000562.3.10.1443507176";
        
        // 每次访问需授权的网址时需带上前面的 cookie 作为通行证  
		 getMethod.setRequestHeader("Cookie", ck);
		 getMethod.setRequestHeader("Host", "www.1point3acres.com");  
		 getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36");    
		 getMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
		 getMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");  
		 //getMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");  
		 getMethod.setRequestHeader("DNT", "1");  
		 getMethod.setRequestHeader("Referer", "http://www.1point3acres.com/bbs/");  
	    try {
			client.executeMethod(getMethod);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			response = getMethod.getResponseBodyAsString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return response;
	}
	
	
	public static String getContent(String content){
		String result = "";
		
		content = content.replace("\r", "").replace("\n", "").replace("&nbsp", "");
		Pattern font = Pattern.compile("<font class=\"jammer\">.*</font>");
		Pattern div = Pattern.compile("<div class=\"quote\">.*</font>");
		Pattern img = Pattern.compile("<img.*/>");
		Matcher mth1 = font.matcher(content);
		Matcher mth2 = font.matcher(content);
		Matcher mth3 = font.matcher(content);
		while(mth1.find()){
			//System.out.println(mth1.group());
			content.replace(mth1.group(), "");
		}
		while(mth2.find()){
			content.replace(mth2.group(), "");
			//System.out.println(mth2.group());
		}
		while(mth3.find()){
			content.replace(mth3.group(), "");
			//System.out.println(mth3.group());
		}
		
		result = Jsoup.clean(content, Whitelist.none());
		
		return result;
	}

}
