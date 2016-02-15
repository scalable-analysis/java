package com.dulishuo.usnews;

import com.dulishuo.util.FileUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class InfoGet {
	static Logger log = Logger.getLogger(InfoGet.class);
	static int count = 1;
	//static Pattern pattern = Pattern.compile("");
	static Map<String,Integer> mapR = new HashMap<String,Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> url = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/usnews/schoolurl22.txt");
		List<String> rak = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/usnews/rank.txt");
		BufferedWriter bw = FileUtil.FileWriter("C:/Users/强胜/Desktop/dataCrawler/usnews/result.json",true);
		List<String> fail = new ArrayList<String>();
		
		for(String yy : rak){
			mapR.put(yy.split("____")[1],Integer.parseInt(yy.split("____")[0]));
		}
		
		for(int i = 128 ; i < url.size() ; i++){
			log.info("process___"+(i));
			String result = getInfo(url.get(i));
			try {
				if(result != "-1"){
					bw.write(result);
					bw.newLine();
					bw.flush();
				}
				else{
					fail.add(url.get(i));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail.add(url.get(i));
			}
			
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileUtil.ListToFile(fail, "C:/Users/强胜/Desktop/dataCrawler/usnews/fail1.txt");
		log.info("___end____");
	}

	private static String getInfo(String url) {
		// TODO Auto-generated method stub
		String result = "-1";
		
		Map<String,Object> map = new HashMap<String,Object>();		
		Document html = null;
		Document htmCnt = null;
		
		try {
			html = Jsoup.parse(getHttp(url));
			map.put("name", html.getElementsByClass("college-name").get(0).text());
			try{
				map.put("rank", mapR.get(html.getElementsByClass("college-name").get(0).text()));
			}catch(Exception e){
				map.put("rank", -1);
			}
			
			Element ul = html.getElementById("side-nav").getElementsByTag("ul").get(0);
			for(Element li : ul.children()){
				String fname = li.select("a").first().text().toLowerCase();
				if(fname.equals("overview") || fname.equals("more information") )
					continue;
				try{
					String href = "http://premium.usnews.com/"+li.select("a[href]").first().attr("href")+"/ranking";
					if(getHttp(href) != "-1"){
						htmCnt = Jsoup.parse(getHttp(href));
						//if(htmCnt.htmCnt.
						if(htmCnt.getElementsByClass("datadict").size() == 0)
							continue;
						Elements tt = htmCnt.getElementsByClass("datadict").get(0).getElementsByTag("table");
						JSONArray ja = new JSONArray();
						for(Element xx : tt){
							if(!xx.attr("class").equals("zebra-stripe") && !xx.attr("class").equals("fields US_News_ranking")){
								Map<String,String> childmap = new HashMap<String,String>();
								childmap.put("name", xx.attr("class"));
					
								for(Element tr : xx.getElementsByTag("tbody").get(0).children()){
									
									String key = tr.getElementsByTag("td").get(0).text();
									String value = tr.getElementsByTag("td").get(1).text();
									childmap.put(key, value);
								}
								JSONObject jsonT = JSONObject.fromObject(childmap);
								ja.add(jsonT);
							}		
						}	
						map.put(fname, ja);
					}
				}catch(Exception e){
					log.error(e.toString());
				}
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			return result;
		}
		
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return result;
	}
	
	public static String getHttp(String url) throws IOException{
		String xx = "-1";
		HttpClient httpClient = new HttpClient();  
		httpClient.setConnectionTimeout(10000);
		httpClient.setTimeout(10000);
	       
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
				log.error(e1.toString());
				return xx;   
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				log.error(e1.toString());
				return xx;   
			}  

            
			try {
				xx = getMethod.getResponseBodyAsString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error(e.toString());
				return xx;   
			}
			
			getMethod.releaseConnection();
		
			return xx;    
	}
	
	public static boolean isWant(String xx){
		boolean oo = false;
		//Pattern
		return oo;
	}

}
