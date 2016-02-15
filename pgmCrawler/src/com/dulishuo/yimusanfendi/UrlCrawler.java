package com.dulishuo.yimusanfendi;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UrlCrawler {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String url = "http://www.1point3acres.com/bbs/forum.php?mod=forumdisplay&fid=27&sortid=189&filter=sortid&sortid=189&page=";
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/申请总结/url.txt")));
	

		for(int i = 1 ; i < 7 ; i++){
			System.out.println("capture__"+i);
			for(String xx : getUrl(url+i)){
				writer.write(xx);
				writer.newLine();
				writer.flush();
			}
		}
		writer.close();
		System.out.println("———————end________");
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
	
	public static List<String> getUrl(String url) throws IOException{
		List<String> result = new ArrayList<String>();
		try{
			Document html = Jsoup.parse(httpRequest(url));
			Element ele = html.getElementById("threadlisttableid");
			int size = ele.getElementsByClass("icn").size();
			System.out.println("size_"+size);
			for(int i = 1 ; i < size ; i++){
				String xx = ele.getElementsByClass("icn").get(i).select("a").attr("href").toString();
				result.add(xx);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("url_"+url);
		}
		
		
		
		return result;		
	}
}
