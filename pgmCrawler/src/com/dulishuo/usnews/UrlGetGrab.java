package com.dulishuo.usnews;

import java.io.BufferedWriter;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.dulishuo.util.FileUtil;

import java.util.*;
import java.util.Map.Entry;

public class UrlGetGrab {
	static int count =0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> stc = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/usnews/schoolurl.txt");
		List<String> urllist = new ArrayList<String>();
		BufferedWriter bw = FileUtil.FileWriter("C:/Users/强胜/Desktop/dataCrawler/usnews/schoolurl22.txt",true);
		for(int i = 0  ; i < stc.size() ; i++){
			System.out.println("process__"+(i));
			try{
				bw.write(process(stc.get(i).split("____")[1]));
				bw.newLine();
				bw.flush();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FileUtil.ListToFile(urllist, "C:/Users/强胜/Desktop/dataCrawler/usnews/schoolurl22.txt");
		System.out.println("___end____");
	}

	
	private static String process(String each) {
		// TODO Auto-generated method stub
		String result = "-1";
		Document html = null;
		try {
			html = Jsoup.parse(getHttp(each));
			Element e = html.getElementsByClass("school-directory-top-nav").get(0);
			result = e.getElementsByTag("ul").get(0).getElementsByTag("li").get(1).select("a").first().attr("href");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
		
		return result;
	}


	public static String getHttp(String url) throws IOException{
		String xx = "-1";
		HttpClient httpClient = new HttpClient();  
	    try{
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
	              
            httpClient.executeMethod(getMethod);
            xx = getMethod.getResponseBodyAsString();
            
           getMethod.releaseConnection();
	    } catch(Exception e){
	    	e.printStackTrace();
	    	return xx;
	    }
		

			return xx;    
	}
}
