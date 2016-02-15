package com.dulishuo.usnews;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.text.html.HTML;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.dulishuo.util.FileUtil;

public class UrlGet {
	static int count = 1; 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String url = "http://premium.usnews.com/best-colleges/rankings/national-universities/data/page+";
		List<String> urllist = new ArrayList<String>();
		for(int i = 1 ; i < 9 ; i++){
			System.out.println("process___"+(count++));
			urllist.addAll(getUrl(url+i,i));
		}
		
		FileUtil.ListToFile(urllist, "C:/Users/强胜/Desktop/dataCrawler/usnews/schoolurl.txt");
		System.out.println("___end____");
	}

	
	public static String getHttp(String url) throws IOException{
		String xx = "-1";
		HttpClient httpClient = new HttpClient();  
	       
            String tt = "auth=\"926U70ZZZ30m64qMaJX1IJKCfvJmO4S01aWgzXW7nhmK98cAQx2jK7JlfmNUOGxQscpDsT71h9Y4QVtytPtl7cmQOJ9GoTZ9LlNFxv1-CXTcLicvZaj2COeYEcs_cRCBa8n3ILqfhcXV_wFnly7akKSCFbKzyw4_jVq51wl6sO4tKxwb-rz0eZHuZAhRkguBPkb1t6uxa6kNu5-1mAULLSWFOZ8xFjVqiqhqFXv0JRRJxLW4d9e-0FPj6llT3Hgj.eNqrVkrLLCouic9LzE1VslJQck7MrMxU0lFQyklEEvXJTMxLB4lmpgC5RgYWxoYG5kBuSX52ah5IgaVZYrJBsrFBUmpqikmyRapFcrKJsbmZsUGaUaJpmnmSUi0AirUeeQ\"; tk=96ac0c30beed4c8e8cc437630f2a5f7b; c=2085190804; compstat=compstu; usnQuantCast=D; __gads=ID=24381f81148ef0b6:T=1439363530:S=ALNI_MbygNq4VzFdG9nrMLE_yO1d_V3M8A; __qca=P0-376455809-1439363544561; _vis_opt_s=1%7C; _vis_opt_test_cookie=1; s_cc=true; s_sq=%5B%5BB%5D%5D; __ybotb=9597; __ybotu=id8fvkxmwqln2c3w6g; __ybotv=1439367772921; __ybots=id8iepkpqb11hvo56k.0.id8iepko78go619lul.1; OX_sd=1; __ybotc=http%3A//ads-adswest.yldbt.com/m/; _trp_hit_11882/41456_728x90=5; _trp_hit_11882/41456_300x250=5; __utma=1.69014033.1439363543.1439363543.1439367785.2; __utmb=1.1.10.1439367785; __utmz=1.1439363543.1.1.utmcsr=colleges.usnews.rankingsandreviews.com|utmccn=(referral)|utmcmd=referral|utmcct=/best-colleges; __utmt=1; ki_t=1439363545567%3B1439363545567%3B1439367788518%3B1%3B2; ki_r=; __ybota=; __ybote=; __ybotz=; OX_plg=swf|shk|pm; __utmc=1; usn_colleges_interstitial=2";  
            GetMethod getMethod = new GetMethod(url);  
            // 每次访问需授权的网址时需带上前面的 cookie 作为通行证  
            getMethod.setRequestHeader("cookie", tt);  
            getMethod.setRequestHeader("Host", "premium.usnews.com");  
            getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");    
            getMethod.setRequestHeader("Accept", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");  
            getMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");  
          //  getMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");   
            getMethod.setRequestHeader("DNT", "1");   
            getMethod.setRequestHeader("Connection", "keep-alive");  
	              
            try {
				httpClient.executeMethod(getMethod);
			} catch (HttpException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return xx;   
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return xx;   
			}  

            
			try {
				xx = getMethod.getResponseBodyAsString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return xx;   
			}

			return xx;    
	}
	
	public static List<String> getUrl(String url , int page){
		List<String> rs = new ArrayList<String>();
		Document html = null;
		try {
			html = Jsoup.parse(getHttp(url));
			Element table = html.getElementById("article").getElementsByTag("table").get(0).getElementsByTag("tbody").get(0);
			int flag = 1;
			for(Element e : table.children()){
				if(e.hasAttr("valign")){
					Map<String,Object> map = new HashMap<String,Object>();
					if(page == 8 && flag > 9)
						break;
					String xx = e.getElementsByTag("td").get(2).select("a").first().attr("href").toString();
					String id = e.getElementsByTag("td").get(1).getElementsByTag("div").get(0).getElementsByTag("span").get(0).text().replaceFirst("#", "").replaceFirst("Tie", "").replaceFirst("tie", "");
					System.out.println(id);
					rs.add(id+"____"+"http://premium.usnews.com"+xx);
					flag++;
					map.put("rank",id );
					map.put("school", xx);
					
					map.put("Tuition and Fees", e.getElementsByTag("td").get(3).text());
					map.put("Total enrollment", e.getElementsByTag("td").get(4).text());
					map.put("Fall 2013 acceptance rate", e.getElementsByTag("td").get(5).text());
					map.put("Average freshman retention rate", e.getElementsByTag("td").get(6).text());
					map.put("6-year graduation rate", e.getElementsByTag("td").get(7).text());
					map.put("Classes with fewer than 20 students", e.getElementsByTag("td").get(8).text());
					map.put("SAT/ACT 25th-75th percentile", e.getElementsByTag("td").get(9).text());
					
					for(Entry<String, Object> entry : map.entrySet())
							System.out.println(entry.getKey()+"__"+entry.getValue());
				
					JSONObject json = JSONObject.fromObject(map);
					//rs.add(json.toString());
					
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
}
