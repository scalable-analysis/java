package com.dulishuo.crawler;

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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ThreadPgmCrawler {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
	
		System.out.println("-------start-------");
		long start = System.currentTimeMillis();
	
		BufferedWriter writerFail = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/result/fail/fail1.txt"));
		BufferedWriter writerSucc = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/result/success.txt",true));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/result/failZ.txt")));
		
		ExecutorService pool = Executors.newFixedThreadPool(30);
		List<String> list = new ArrayList<String>();
		Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
		
		String tmp = "";
		 //将所有offset-id对装入list集合；
		try {
			while((tmp= br.readLine())!= null){
				if(!tmp.equals("")){
					list.add(tmp);
				}			
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 int size = list.size();
		 //共10个线程，计算每个线程大概要处理的数据量
		 int threadNum = 20;
		 int cellSize = (size%threadNum)==0 ?size/threadNum : size/threadNum+1; 
		 
		 int keyM = 0;
		 List<String> listT = new ArrayList<String>();
		 List<String> listTmp = new ArrayList<String>();
		 for(int i = 0 ; i < size ; i++){
			 listTmp.add(list.get(i));
			 if((i+1)%cellSize == 0 || i+1 == size){
				 
				 listT = new ArrayList<String>();
				 listT = listTmp;
				 map.put(keyM, listT);
				 keyM++;
				 listTmp = new ArrayList<String>();
			 }
		 }
		
		List<Future<Map<String,Set<String>>>> futures =new ArrayList<Future<Map<String,Set<String>>>>();
		 
		for(int i = 0; i < 20; i++){
		   futures.add(pool.submit(new StringTask(map.get(i))));
		}
		//List<String> failOffset = new ArrayList<String>();
		for(Future<Map<String,Set<String>>> future : futures){
			try {
				Map<String,Set<String>> mapResult = future.get();
				
				//实时写入文件
				for(String fail : mapResult.get("fail")){				
					writerFail.write(fail);
					writerFail.newLine();
					writerFail.flush();
				}
				for(String succ : mapResult.get("succ")){
					writerSucc.write(succ);
					writerSucc.newLine();
					writerSucc.flush();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("error---111111111111");
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("error---222222222222");
			}
		}
		 
		pool.shutdown();
		
		writerSucc.close();
		writerFail.close();
		long end = System.currentTimeMillis();
		System.out.println("---end-----use time :"+(end-start));
	}

}

@SuppressWarnings("rawtypes")
class StringTask implements Callable{
		List<String> list;
		Pattern ptn1 = Pattern.compile("mailto:.*\"");
		Pattern ptn2 = Pattern.compile("&weblink=.*\"");
		Pattern ptn3 = Pattern.compile("href=\".*\"");
		Pattern ptn4 = Pattern.compile("&id=\\d+");
		
		
	   public StringTask(List<String> list) {
		// TODO Auto-generated constructor stub
		   this.list = list;
	   }

	public Map<String, Set<String>> call(){	
		Map<String,Set<String>> mapResult = new HashMap<String,Set<String>>();
		Set<String> failOffset = new HashSet<String>();
		Set<String> succUrl = new HashSet<String>();
		
		for(int k = 0 ; k < list.size() ; k++){
			System.out.println(Thread.currentThread().getName()+" grab offset at --"+k+" ！");
			String res = "";
			int flag = 0;
			//重复请求10次
			while(res == "" && flag < 10 ){
				res = process(list.get(k));
				flag++;
				//获取不到就等待10秒
				if(res == ""){
					try {
						Thread.currentThread();
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					//获取到了就睡2秒
					try {
						Thread.currentThread();
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}			
			}
			if(flag == 10 && res ==""){
				failOffset.add(list.get(k));
				//System.out.println("fail-1------"+list.get(k));
				continue;
			}		
			
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
					//System.out.println("succ--"+json.toString());
				}
				
			}catch(Exception e){
				//e.printStackTrace();
				failOffset.add(list.get(k));
				//System.out.println("fail-2------"+list.get(k));
			}			   	
		}
		
		mapResult.put("fail", failOffset);
		mapResult.put("succ", succUrl);
		return mapResult;
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
