package com.dulishuo.yimusanfendi;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class CrawlerTask implements Callable {
static boolean gl = true;
	static String stopSentence = "注册一亩三分地论坛，查看更多干货！ 您需要 登录 才可以下载或查看，没有帐号？获取更多干货，注册来地里当农民！   x";
	List<String> list = new ArrayList<String>();
	public CrawlerTask(List<String> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
	}

	@Override
	public List<String> call() throws Exception {
		// TODO Auto-generated method stub
		List<String> result = new ArrayList<String>();
		for(int i = 0 ; i < list.size() ; i++){
			System.out.println(Thread.currentThread().getName()+"____________"+i);
			Thread.currentThread().sleep(50);
			result.add(getPost(list.get(i)));
		}
		
		return result;
	}

	
	public static String getPost(String url) throws IOException{
		String result = "";
		String response = "";	   
		String author_say = "";
		String author_date = "";
		List<String> comment = new ArrayList<String>();
		List<String> date = new ArrayList<String>();
		Map<String,Object> map = new HashMap<String,Object>();
		
		response = httpRequest(url);
	    Document html = Jsoup.parse(response);
	    String title = html.getElementById("thread_subject").text().toString();
	   
		int type = 1;
	    //判断该帖子有多少页
	    int pages = html.getElementById("pgt").getElementsByClass("pgt").get(0).childNodeSize();
	    List<String> tmp = new ArrayList<String>();
	    if(pages == 0){
	    	tmp = parseHtml(httpRequest(url));
	    	author_say = tmp.get(0).split("\t")[0];
	    	author_date = tmp.get(0).split("\t")[1];
    		for(String xx : tmp){
    			comment.add(xx.split("\t")[0]);
    			date.add(xx.split("\t")[1]);
    		}
	    }else{
	    	int aSum = html.getElementById("pgt").getElementsByClass("pgt").get(0).getElementsByClass("pg").get(0).select("a").size();
	    	Element last = html.getElementById("pgt").getElementsByClass("pgt").get(0).select("a").get(aSum-2);
	    	int totalPages = Integer.parseInt(last.attr("href").toString().substring(last.attr("href").toString().lastIndexOf("=")+1));
	    	
	    	//依次获取每一页的内容
	    	for(int i = 1 ; i < totalPages+1 ; i++ ){
	    		try{
	    			String urlTmp = url+"&page="+i;
	    			//间隔50ms发送请求
	    			Thread.currentThread().sleep(50);	   
		    		
		    		tmp = parseHtml(httpRequest(urlTmp));
		    		if(i==1){
		    			author_say = tmp.get(0).split("t")[0];
		    			author_date = tmp.get(0).split("\t")[1];
		    		}
		    		for(String xx : tmp){
		    			comment.add(xx.split("t")[0]);
		    			date.add(xx.split("\t")[1]);
		    		}
	    		}catch(Exception e){
	    			System.out.println(e.toString());
	    		}
	    	}
	    }
	    JSONArray cmtList = new JSONArray();
	    
	    for(int j = 1 ; j < comment.size() ; j++){
	    	Map<String,Object> cmtMap = new HashMap<String,Object>();
	    	cmtMap.put("floor_seq", j+1);
	    	cmtMap.put("content", comment.get(j));
	    	cmtMap.put("date", date.get(j));
	    	JSONObject cmtJson = JSONObject.fromObject(cmtMap);
	    	cmtList.add(cmtJson);
	    }
	    
	    //帖子日期
	    map.put("author_date", author_date);
	    //来源网站
	    map.put("origin", "http://www.1point3acres.com/");
	    //1代表来源一亩三分地
	    map.put("from",1);
	    //内容链接
	    map.put("url", url);
	    //帖子标题
	    map.put("title", title);
	    //帖子类型
		map.put("type", type);
		//楼主1楼自述
		map.put("author_say", author_say.replace(stopSentence, ""));
		//楼下跟风评论
		map.put("comment", cmtList);
		
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();

		return result;
	}
	
	public static List<String> parseHtml(String str) throws IOException{
		List<String> result = new ArrayList<String>();
		
		Document html = Jsoup.parse(str);
		Element ele = html.getElementById("postlist");
		Elements tbodys = ele.children();		
		int size = html.getElementsByClass("authi").size();
	      
		try{
			for(int i = 0 ; i < size ; i++){
				String content = getContent(tbodys.get(i+2).getElementsByClass("t_f").html());
				String date = html.getElementsByClass("authi").get(i).getElementsByTag("em").get(0).text().replace("发表于", "").trim();
				result.add(content+"\t"+date);
			}	    
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("size___"+size+"authi__"+html.getElementsByClass("authi").size());
			
			if(gl){
			FileWriter fw = new FileWriter("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/经验总结/error.html");
			fw.write(str);
			fw.flush();
			fw.close();
			gl = false;
			}
		}
		
		return result;		
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
