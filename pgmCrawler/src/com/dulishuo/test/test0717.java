package com.dulishuo.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class test0717 {

	public static void main(String[] args) throws IOException {
		
		System.out.println("-------start-------");
		long start = System.currentTimeMillis();
	
		BufferedWriter writerFail = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/result/fail/fail1.txt"));
		BufferedWriter writerSucc = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/result/success.txt",true));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/result/failZ.txt")));
		
		
		List<String> list = new ArrayList<String>();
		
		String tmp = "";
		 //将所有offset-id对装入list集合；
			while((tmp= br.readLine())!= null){
				if(!tmp.equals("")){
					list.add(tmp);
				}		
			}

		Pattern ptn1 = Pattern.compile("mailto:.*\"");
		Pattern ptn2 = Pattern.compile("&weblink=.*\"");
		Pattern ptn3 = Pattern.compile("href=\".*\"");
		Pattern ptn4 = Pattern.compile("&id=\\d+");
		Set<String> failOffset = new HashSet<String>();
		Set<String> succUrl = new HashSet<String>();
		
		for(int k = 0 ; k < list.size() ; k++){
			System.out.println(" grab offset at --"+k+" ！");
			String res = "";
			
			res = process(list.get(k));
				
			
			try{
				Document html = Jsoup.parse(res);
				Element div = html.getElementById("vacprof");
				Elements dds = div.getElementsByTag("dd");
				Elements dts = div.getElementsByTag("dt");
				
				Map<String,String> map = new HashMap<String,String>();
				int size = dds.size();
				
				for(int i = 0 ; i < size ; i++){
					if(dts.get(i).text().toLowerCase().contains("email")){
						String key  = dts.get(i).text();
						String value  = dds.get(i).select("a").first().attr("href");
						Matcher mth = ptn1.matcher(value);
						if(mth.find()){
							value.replace("mailto:", "").replace("\"", "");
						}
						map.put(key, value);
					}else if(dts.get(i).text().toLowerCase().contains("web")){
						String key  = dts.get(i).text();
						String value  = dds.get(i).select("a").first().attr("href");
						Matcher mth = ptn2.matcher(value);
						if(mth.find()){
							value.replace("&weblink=", "").replace("\"", "");
						}
					    map.put(key, value);					    		  
					}else if(dts.get(i).text().toLowerCase().contains("application")){
						String key  = dts.get(i).text();
						String value  = dds.get(i).select("a").first().attr("href");
						Matcher mth = ptn3.matcher(value);
						if(mth.find()){
							value.replace("href=\"", "").replace("\"", "");
						}
						map.put(key, value);
					 
					}else{
						String key  = dts.get(i).text();
						String value  = dds.get(i).text();
						map.put(key, value);
					}
				}
				Matcher mth4 = ptn4.matcher(list.get(k));
				if(mth4.find()){
					map.put("id", mth4.group().replace("&id=", ""));
					JSONObject json = JSONObject.fromObject(map);
					succUrl.add(json.toString());
					System.out.println("succ--"+json.toString());
				}
				
			}catch(Exception e){
				//e.printStackTrace();
				failOffset.add(list.get(k));
				System.out.println("fail-2------"+list.get(k));
			}			   	
		}	
		for(String fail : failOffset){
			writerFail.write(fail);
			writerFail.newLine();
		}
		for(String succ : succUrl){
			writerSucc.write(succ);
			writerSucc.newLine();
		}
		writerFail.flush();
		writerSucc.flush();
		
		writerSucc.close();
		writerFail.close();
		long end = System.currentTimeMillis();
		System.out.println("---end-----use time :"+(end-start));
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
}
