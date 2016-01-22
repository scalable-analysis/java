package com.dulishuo.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup; 
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




















import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;


public class QusetionAnswerCrawler {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws HttpException, IOException {
		// TODO Auto-generated method stub
			
		long start = System.currentTimeMillis();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/强胜/Desktop/result/succUrl.txt")));
		BufferedWriter writerFail = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/result/failUrl.txt"));
		
		Set<String> failOffset = new HashSet<String>();
		List<String> succUrl = new ArrayList<String>();
		
		ExecutorService pool = Executors.newFixedThreadPool(20);
		List<Future<Map<String,Set<String>> >> futures =new ArrayList<Future<Map<String,Set<String>> >>();
		 
		//19550
		int interval = 2000;
		for(int i = 0; i < 10; i++){
		   futures.add(pool.submit(new CrawlerTask(i,interval)));
		}
		 
		for(Future<Map<String,Set<String>> > future : futures){
			try {
				Map<String,Set<String>> map = future.get();
				for(String fail : map.get("fail")){
					failOffset.add(fail);
					writerFail.write(fail);
					writerFail.newLine();
					writerFail.flush();
				}
				for(String succ : map.get("succ")){
					succUrl.add(succ);
					writer.write(succ);
					writer.newLine();
					writer.flush();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
		pool.shutdown();
		
		writer.close();
		writerFail.close();
	      
	    long end = System.currentTimeMillis();
	    System.out.println("end--use time"+(end-start));
	}
}


@SuppressWarnings("rawtypes")
class CrawlerTask implements Callable{
	int start;
	int interval;
	String reg1 = ";offset=\\d+&amp;";
	String reg2 = ";id=\\d+";
	Pattern ptn1 = Pattern.compile(reg1);
	Pattern ptn2 = Pattern.compile(reg2);
	
   public CrawlerTask(int start,int interval) {
	// TODO Auto-generated constructor stub
	   this.start = start;
	   this.interval = interval;
   }

   public Map<String,Set<String>> call(){
	   Map<String,Set<String>> map = new HashMap<String,Set<String>>();
	   Set<String> failOffset = new HashSet<String>();
	   Set<String> succUrl = new HashSet<String>();
	   int end = start==9 ? 19560 : (start+1)*interval;
	   for(int offset = start*interval ; offset < end ; offset+=10){
		   System.out.println(Thread.currentThread().getName()+" grab offset at --"+offset+" ！");
			String res = "";
			int flag = 0;
			//重复请求10次
			while(res == "" && flag < 10){
				res = process(String.valueOf(offset));
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
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}			
			}
			if(flag == 10 && res ==""){
				failOffset.add(String.valueOf(offset));
				continue;
			}		
			try{
				int count = 0;
				Document html = Jsoup.parse(res);
				Element div = html.getElementsByClass("clearboth").get(0);
				Elements lis = div.getElementsByTag("li");
				List<String> lsTmp = new ArrayList<String>();
				for(Element li : lis){
					try{
						String h = li.getElementsByTag("h3").html(); 
						Matcher mth1 = ptn1.matcher(h);
						Matcher mth2 = ptn2.matcher(h);
						String key="";
						String value="";
						if(mth1.find()){
							key=mth1.group().replace(";offset=", "").replace("&amp;", "");
						}
						if(mth2.find()){
							value=mth2.group().replace(";id=", "");
						}
						if(!key.equals("")&&!value.equals("")){
							String urlT = "http://ukpass.prospects.ac.uk/pgsearch/UKPASSCourse;jsessionid=ca30a51edbab$A1$21$F?keyword=&type=Course&filter=&filter=m%2FFT&action=showdetails&offset="+key+"&2waynocompress=1&id="+value;
							lsTmp.add(urlT);
							count++;
						}
					}catch(Exception e){
						failOffset.add(String.valueOf(offset));
						System.out.println("wrong : "+Thread.currentThread().getName()+"--"+offset+"--"+e.toString());
					}
					}
				if(count == 10){
					for(String oo : lsTmp){
						succUrl.add(oo);
						
					}
				}
				}catch(Exception e){
					failOffset.add(String.valueOf(offset));
					System.out.println("wrong : "+Thread.currentThread().getName()+"--"+offset+"--"+e.toString());
				}		     
	     	}
	   	map.put("fail", failOffset);
	   	map.put("succ", succUrl);
	   	return map;
	}
   public static String process(String offset){
		String result = "";
		
		HttpClient httpClient;
		PostMethod postMethod;
		String url = "http://ukpass.prospects.ac.uk/pgsearch/UKPASSCourse;jsessionid=ca30a51edbab$A1$21$F?keyword=&filter=&filter=m%2FFT&action=search&offset="+offset+"&2waynocompress=1";  

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


