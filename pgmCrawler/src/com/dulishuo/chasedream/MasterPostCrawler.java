package com.dulishuo.chasedream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dulishuo.util.CrawlerUtil;

public class MasterPostCrawler {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		long start =System.currentTimeMillis();
		
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/学校信息/url.txt")));
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/校友答疑/url.txt")));
		BufferedReader reader3 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/Essay/url.txt")));
		BufferedReader reader4 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/面试经验/url.txt")));
		BufferedReader reader5 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/Resume/url.txt")));
		BufferedReader reader6 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/录取汇报/url.txt")));
		BufferedReader reader7 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/申请总结/url.txt")));
		BufferedReader reader8 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/实习就业/url.txt")));
		
		BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/学校信息/post_学校信息.json")));
		BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/校友答疑/post_校友答疑.json")));
		BufferedWriter writer3 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/Essay/post_Essay.json")));
		BufferedWriter writer4 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/面试经验/post_面试经验.json")));
		BufferedWriter writer5 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/Resume/post_Resume.json")));
		BufferedWriter writer6 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/录取汇报/post_录取汇报.json")));
		BufferedWriter writer7 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/申请总结/post_申请总结.json")));
		BufferedWriter writer8 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/实习就业/post_实习就业.json")));
	
		BufferedWriter failUr1 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/学校信息/fail_学校信息.txt")));
		BufferedWriter failUr2 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/校友答疑/fail_校友答疑.txt")));
		BufferedWriter failUr3 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/Essay/fail_Essay.txt")));
		BufferedWriter failUr4 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/面试经验/fail_面试经验.txt")));
		BufferedWriter failUr5 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/Resume/fail_Resume.txt")));
		BufferedWriter failUr6 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/录取汇报/fail_录取汇报.txt")));
		BufferedWriter failUr7 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/申请总结/fail_申请总结.txt")));
		BufferedWriter failUr8 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/实习就业/fail_实习就业.txt")));
	
		List<String> urls1 = new ArrayList<String>();
		List<String> urls2 = new ArrayList<String>();
		List<String> urls3 = new ArrayList<String>();
		List<String> urls4 = new ArrayList<String>();
		List<String> urls5 = new ArrayList<String>();
		List<String> urls6 = new ArrayList<String>();
		List<String> urls7 = new ArrayList<String>();
		List<String> urls8 = new ArrayList<String>();
		
		String each_line1 = "";		
		String each_line2 = "";		
		String each_line3 = "";		
		String each_line4 = "";		
		String each_line5 = "";		
		String each_line6 = "";		
		String each_line7 = "";		
		String each_line8 = "";
		
		while((each_line1 = reader1.readLine()) != null){			
				urls1.add(each_line1);				
		}
		while((each_line2 = reader2.readLine()) != null){			
			urls2.add(each_line2);			
		}
		while((each_line3 = reader3.readLine()) != null){			
			urls3.add(each_line3);			
		}
		while((each_line4 = reader4.readLine()) != null){			
			urls4.add(each_line4);	
		}
		while((each_line5 = reader5.readLine()) != null){			
			urls5.add(each_line5);		
		}
		while((each_line6 = reader6.readLine()) != null){			
			urls6.add(each_line6);		
		}
		while((each_line7 = reader7.readLine()) != null){			
			urls7.add(each_line7);		
		}
		while((each_line8 = reader8.readLine()) != null){			
			urls8.add(each_line8);		
		}
		
		reader1.close();
		reader2.close();
		reader3.close();
		reader4.close();
		reader5.close();
		reader6.close();
		reader7.close();
		reader8.close();
		
		System.out.println("_________end__read_________");
	
	
		//1经验总结   2申请哪些学校  3PS/CV 4面试 5推荐信   6申请总结 7学校信息 8校友答疑  9Essay 10Resume 11录取汇报 12实习就业
		for(int i = 0 ; i < urls1.size(); i++){
			System.out.println("capture_____"+i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				writer1.write(getPost(urls1.get(i),7));
				writer1.newLine();
				writer1.flush();
			}catch(Exception e){
				failUr1.write(urls1.get(i));
				failUr1.newLine();
				failUr1.flush();
			}			
		}
		for(int i = 0 ; i < urls2.size() ; i++){
			System.out.println("capture_____"+i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				writer2.write(getPost(urls2.get(i),8));
				writer2.newLine();
				writer2.flush();
			}catch(Exception e){
				failUr2.write(urls2.get(i));
				failUr2.newLine();
				failUr2.flush();
			}			
		}
		for(int i = 0 ; i < urls3.size() ; i++){
			System.out.println("capture_____"+i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				writer3.write(getPost(urls3.get(i),9));
				writer3.newLine();
				writer3.flush();
			}catch(Exception e){
				failUr3.write(urls3.get(i));
				failUr3.newLine();
				failUr3.flush();
			}			
		}
		for(int i = 0 ; i < urls4.size() ; i++){
			System.out.println("capture_____"+i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				writer4.write(getPost(urls4.get(i),4));
				writer4.newLine();
				writer4.flush();
			}catch(Exception e){
				failUr4.write(urls4.get(i));
				failUr4.newLine();
				failUr4.flush();
			}			
		}
		for(int i = 0 ; i < urls5.size() ; i++){
			System.out.println("capture_____"+i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				writer5.write(getPost(urls5.get(i),10));
				writer5.newLine();
				writer5.flush();
			}catch(Exception e){
				failUr5.write(urls5.get(i));
				failUr5.newLine();
				failUr5.flush();
			}			
		}
		for(int i = 0 ; i < urls6.size() ; i++){
			System.out.println("capture_____"+i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				writer6.write(getPost(urls6.get(i),11));
				writer6.newLine();
				writer6.flush();
			}catch(Exception e){
				failUr6.write(urls6.get(i));
				failUr6.newLine();
				failUr6.flush();
			}			
		}
		for(int i = 0 ; i < urls7.size() ; i++){
			System.out.println("capture_____"+i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				writer7.write(getPost(urls7.get(i),6));
				writer7.newLine();
				writer7.flush();
			}catch(Exception e){
				failUr7.write(urls7.get(i));
				failUr7.newLine();
				failUr7.flush();
			}			
		}
		for(int i = 0 ; i < urls8.size() ; i++){
			System.out.println("capture_____"+i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				writer8.write(getPost(urls8.get(i),12));
				writer8.newLine();
				writer8.flush();
			}catch(Exception e){
				failUr8.write(urls8.get(i));
				failUr8.newLine();
				failUr8.flush();
			}			
		}
		
		writer1.close();
		writer2.close();
		writer3.close();
		writer4.close();
		writer5.close();
		writer6.close();
		writer7.close();
		writer8.close();
		failUr1.close();
		failUr2.close();
		failUr3.close();
		failUr4.close();
		failUr5.close();
		failUr6.close();
		failUr7.close();
		failUr8.close();
    
		long end = System.currentTimeMillis();
		System.out.println("---end-----use time :"+(end-start));
	}

	
	private static String getPost(String url, int type) {
		// TODO Auto-generated method stub
		String result = "";
		String response = "";	   
		String author_say = "";
		String author_date = "";
		Map<String,Object> map = new HashMap<String,Object>();
		
		response = CrawlerUtil.httpRequest(url);
	    Document html = Jsoup.parse(response);
	    String title = html.getElementById("thread_subject").text().toString();
	   
	    Elements posts = html.getElementById("postlist").children();
	    System.out.println(posts.size());
	    JSONArray cmtList = new JSONArray();
	    for(Element element : posts){
			//String url = element.getElementsByClass("icn").select("a").attr("href").toString();
	    	if(element.attr("id").toString().indexOf("post")!= -1&&!element.attr("id").equals("postlistreply"))
			System.out.println("url__"+element.attr("id"));
			
		}
	    try{
	    	int count = 1;
	    	for(int i = 0 ; i < posts.size() ; i++){
	    		//判断是否是post
	    		if(posts.get(i).attr("id").toString().indexOf("post")!= -1&&!posts.get(i).attr("id").equals("postlistreply")){
	    			if(count == 1 ){
			    		author_say = posts.get(i).getElementsByClass("t_f").get(0).html();
			    		author_date = posts.get(i).getElementsByClass("authi").get(1).getElementsByTag("em").get(0).text().replace("发表于", "").trim();
			    	}else{
			    		Map<String,Object> cmtMap = new HashMap<String,Object>();
			    		String cmt = posts.get(i).getElementsByClass("t_f").get(0).html();
			    		String date = posts.get(i).getElementsByClass("authi").get(1).getElementsByTag("em").get(0).text().replace("发表于", "").trim();
				    	cmtMap.put("floor_seq", count);
				    	cmtMap.put("content", cmt);
				    	cmtMap.put("date", date);
				    	JSONObject cmtJson = JSONObject.fromObject(cmtMap);
				    	cmtList.add(cmtJson);
			    	}
	    			count++;
	    		}
		    	
		    	
		    }
	    }catch(Exception e){
	    	e.printStackTrace();
	    	
	    }
	    
	    	    		
	    //帖子日期
	    map.put("author_date", author_date);
	    //来源网站
	    map.put("origin", "http://forum.chasedream.com/");
	    //1代表来源一亩三分地 2代表chaseDream
	    map.put("from",2);
	    //内容链接
	    map.put("url", url);
	    //帖子标题
	    map.put("title", title);
	    //帖子类型
		map.put("type", type);
		//楼主1楼自述
		map.put("author_say", author_say);
		//楼下跟风评论
		map.put("comment", cmtList);
		//页码
		int pages = html.getElementById("pgt").getElementsByClass("pgt").get(0).childNodeSize();
		if(pages == 0 ){
			map.put("pages", pages);
		}else{
			int aSum = html.getElementById("pgt").getElementsByClass("pgt").get(0).getElementsByClass("pg").get(0).select("a").size();
	    	Element last = html.getElementById("pgt").getElementsByClass("pgt").get(0).select("a").get(aSum-2);
	    	int totalPages = Integer.parseInt(last.attr("href").toString().substring(last.attr("href").toString().lastIndexOf("=")+1));
	    	map.put("pages", totalPages);
		}
		
		System.out.println("pages__"+pages);
		
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();

		return result;
		
	}

}
