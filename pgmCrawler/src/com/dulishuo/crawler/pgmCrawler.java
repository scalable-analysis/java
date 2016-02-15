package com.dulishuo.crawler;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class pgmCrawler {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		Pattern ptn1 = Pattern.compile("mailto:.*\"");
		Pattern ptn2 = Pattern.compile("&weblink=.*\"");
		Pattern ptn3 = Pattern.compile("href=\".*\"");
	    BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/result/result2.txt"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.toString());
		}
	    BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/pgmMap2.txt")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.toString());
		}
	    
	    JSONArray rstJsonA  = new JSONArray();
	    
	    String tmp = "";
	
	    
	    try {
			while((tmp= br.readLine())!= null ){
				
				String offset = tmp.split("\t")[1];
				String id = tmp.split("\t")[0];
				String url = "http://ukpass.prospects.ac.uk/pgsearch/UKPASSCourse;jsessionid=ca30a51edbab$A1$21$F?keyword=&type=Course&filter=&filter=m%2FFT&action=showdetails&offset="+offset+"&2waynocompress=1&id="+id;
				
				HttpClient httpClient = new HttpClient();
			      PostMethod postMethod = new PostMethod(url); 
			      httpClient.executeMethod(postMethod);
			      String res = "";
			      res = postMethod.getResponseBodyAsString();

			      try{
			    	  Document html = Jsoup.parse(res);
				      Element div = html.getElementById("vacprof");
				      Elements dds = div.getElementsByTag("dd");
				      Elements dts = div.getElementsByTag("dt");
				      Map<String,String> map = new HashMap<String,String>();
				      int size = dds.size();
				      
				      for(int i = 0 ; i < size ; i++){
				    	  try{
				    		  if(dts.get(i).text().toLowerCase().contains("email")){
					    		  String key  = dts.get(i).text();
					    		  String value  = dds.get(i).select("a").first().attr("href");
						    	  
					    		  Matcher mth = ptn1.matcher(value);
					    		  if(mth.find()){
					    			  value.replace("mailto:", "").replace("\"", "");
					    		  }
					    		 // System.out.println(key+"----"+value);
					    		  map.put(key, value);
					    	  }/*else if(dts.get(i).text().toLowerCase().contains("qualification")&&dts.get(i).text().toLowerCase().contains("duration")&&dts.get(i).text().toLowerCase().contains("mode")){
					    		  try{
					    			  
							    	  String value  = dds.get(i).text();
							    	  map.put("qualification", value.split(" ")[0]);
							    	  map.put("duration", value.split(" ")[1]);
							    	  map.put("mode", value.split(" ")[2]);
					    		  }catch(Exception e){
					    			  System.out.println(e);
					    		  }
					    		  
					    		  
					    	  }*/else if(dts.get(i).text().toLowerCase().contains("web")){
					    		  String key  = dts.get(i).text();
					    		  String value  = dds.get(i).select("a").first().attr("href");
					    		  Matcher mth = ptn2.matcher(value);
					    		  if(mth.find()){
					    			  value.replace("&weblink=", "").replace("\"", "");
					    		  }
					    		 // System.out.println(key+"----"+value);
					    		  map.put(key, value);
					    		  
					    	  }else if(dts.get(i).text().toLowerCase().contains("application")){
					    		  String key  = dts.get(i).text();
						    	  String value  = dds.get(i).select("a").first().attr("href");
					    		  Matcher mth = ptn3.matcher(value);
					    		  if(mth.find()){
					    			  value.replace("href=\"", "").replace("\"", "");
					    		  }
					    		  //System.out.println(key+"----"+value);
					    		  map.put(key, value);
					    		  break;
					    	  }else{
					    		  String key  = dts.get(i).text();
						    	  String value  = dds.get(i).html();
						    	 // System.out.println(key+"----"+value);
						    	  map.put(key, value);
					    	  }
					     
					      
				    	  }
				    	  catch(Exception e){
				    		  System.out.println(e.toString());
				    	  }
				      }
				      JSONObject json = JSONObject.fromObject(map);
				      try {
							writer.write(json.toString()+",");
							writer.newLine();
							writer.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				      //rstJsonA.add(json);
				      System.out.println("执行到第--"+(count++)+"--数据");
			      }catch(Exception e){
					  System.out.println(e.toString());
				  }
			      
			      
			    	  
			      
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    long end = System.currentTimeMillis();
	   
	    System.out.println("----end---"+(end-startTime)+"毫秒");
	}

}
