package com.dulishuo.usnews.sixteen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dulishuo.util.FileUtil;

public class GlobalSchoolRank {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		crawler();
	}
	
	private static void crawler() {
		// TODO Auto-generated method stub
		String basicUrl = "http://www.usnews.com/education/best-global-universities/rankings?page=";
		List<String> res = new ArrayList<String>();
		for(int i = 1 ; i  < 76 ; i++){
			System.out.println("process____"+count++);
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String url = basicUrl+i;
			try {
				List<String> list = new ArrayList<String>();
				list = parseHtml(getHttp(url));
				res.addAll(list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/usnews/2016/全球高校综合排名/uni1008.json");
		System.out.println("________________---Exit-------");
	}
	
	private static List<String> parseHtml(String str) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		Document html = Jsoup.parse(str);
		Element result = html.getElementById("resultsMain");
		Elements schs = result.getElementsByClass("sep");
		for(Element ele : schs){
			int value = Integer.parseInt(ele.getElementsByClass("thumb-left").get(0).text().toString().replace("#", "").replace("Tie", "").trim());
			String score = ele.getElementsByClass("thumb-right").get(0).text().replace("Global Score", "").toString().trim();
			String ins = ele.getElementsByClass("h-taut").get(0).text();
			JSONObject json = new JSONObject();
			json.put("institute", ins);
			json.put("score", score);
			json.put("value", value);
			json.put("year", 2016);
			json.put("rank_type_id", 43);
			list.add(json.toString());
		}
		return list ;
	}

	public static String getHttp(String url) throws IOException{
		String xx = "-1";
		HttpClient httpClient = new HttpClient();  
		httpClient.setConnectionTimeout(10000);
		httpClient.setTimeout(10000);
	       
            String cookie = "auth=\"926U70ZZZ30m64qMaJX1IJKCfvJmO4S01aWgzXW7nhmK98cAQx2jK7JlfmNUOGxQscpDsT71h9Y4QVtytPtl7cmQOJ9GoTZ9LlNFxv1-CXTcLicvZaj2COeYEcs_cRCBa8n3ILqfhcXV_wFnly7akKSCFbKzyw4_jVq51wl6sO4tKxwb-rz0eZHuZAhRkguBPkb1t6uxa6kNu5-1mAULLSWFOZ8xFjVqiqhqFXv0JRRJxLW4d9e-0FPj6llT3Hgj.eNqrVkrLLCouic9LzE1VslJQck7MrMxU0lFQyklEEvXJTMxLB4lmpgC5RgYWxoYG5kBuSX52ah5IgaVZYrJBsrFBUmpqikmyRapFcrKJsbmZsUGaUaJpmnmSUi0AirUeeQ\"; tk=96ac0c30beed4c8e8cc437630f2a5f7b; c=2085190804; compstat=compstu; usnQuantCast=D; __gads=ID=24381f81148ef0b6:T=1439363530:S=ALNI_MbygNq4VzFdG9nrMLE_yO1d_V3M8A; __qca=P0-376455809-1439363544561; _vis_opt_s=1%7C; _vis_opt_test_cookie=1; s_cc=true; s_sq=%5B%5BB%5D%5D; __ybotb=9597; __ybotu=id8fvkxmwqln2c3w6g; __ybotv=1439367772921; __ybots=id8iepkpqb11hvo56k.0.id8iepko78go619lul.1; OX_sd=1; __ybotc=http%3A//ads-adswest.yldbt.com/m/; _trp_hit_11882/41456_728x90=5; _trp_hit_11882/41456_300x250=5; __utma=1.69014033.1439363543.1439363543.1439367785.2; __utmb=1.1.10.1439367785; __utmz=1.1439363543.1.1.utmcsr=colleges.usnews.rankingsandreviews.com|utmccn=(referral)|utmcmd=referral|utmcct=/best-colleges; __utmt=1; ki_t=1439363545567%3B1439363545567%3B1439367788518%3B1%3B2; ki_r=; __ybota=; __ybote=; __ybotz=; OX_plg=swf|shk|pm; __utmc=1; usn_colleges_interstitial=2";  
            GetMethod getMethod = new GetMethod(url);  
            // 每次访问需授权的网址时需带上前面的 cookie 作为通行证  
            getMethod.setRequestHeader("cookie", cookie);  
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
				return xx;   
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				return xx;   
			}  

            
			try {
				xx = getMethod.getResponseBodyAsString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return xx;   
			}
			
			getMethod.releaseConnection();
		
			return xx;    
	}

}
