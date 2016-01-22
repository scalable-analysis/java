package com.dulishuo.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerFail {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String reg1 = ";offset=\\d+&amp;";
		String reg2 = ";id=\\d+";
		Pattern ptn1 = Pattern.compile(reg1);
		Pattern ptn2 = Pattern.compile(reg2);
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/强胜/Desktop/tmp/succUrl1.txt")));
		BufferedWriter writerFail = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/tmp/fail111.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/failZ.txt")));
		
		 String tmp = "";
		 Set<String> all = new HashSet<String>();
		 while((tmp= br.readLine())!= null){
			 all.add(tmp);
		 }
		 
		 System.out.println("the size of all is :"+all.size());
		    	
		 //String url = "http://ukpass.prospects.ac.uk/pgsearch/UKPASSCourse;jsessionid=ca30a51edbab$A1$21$F?keyword=&filter=&filter=m%2FFT&action=search&offset="+offset+"&2waynocompress=1";
		 int kk = 0;
	
		   Set<String> failOffset = new HashSet<String>();
		   Set<String> succUrl = new HashSet<String>();
		   
		   for(String fail : all){
			   System.out.println("grab -------- "+(kk++));
				String res = "";
				int flag = 0;
				//重复请求10次
				while(res == "" && flag < 10){
					res = process(fail);
					flag++;
					//获取不到就等待10秒
					
					if(res == ""){
						try {
							Thread.currentThread().sleep(10000);
							System.out.println("sleep --- 10000 s");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						//获取到了就睡2秒
						try {
							//System.out.println("res_"+res);
							Thread.currentThread().sleep(2000);
							System.out.println("sleep --- 2 s");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}			
				}
				if(flag == 10 && res ==""){
					failOffset.add(fail);
					System.out.println("fail1----"+fail);
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
							//System.out.println(h);
							Matcher mth1 = ptn1.matcher(h);
							Matcher mth2 = ptn2.matcher(h);
							String key="";
							String value="";
							if(mth1.find()){
								key=mth1.group().replace(";offset=", "").replace("&amp;", "");
								System.out.println("offset--"+key);
							}
							if(mth2.find()){
								value=mth2.group().replace(";id=", "");
								System.out.println("id--"+value);
							}
							if(!key.equals("")&&!value.equals("")){
								String urlT = "http://ukpass.prospects.ac.uk/pgsearch/UKPASSCourse;jsessionid=ca30a51edbab$A1$21$F?keyword=&type=Course&filter=&filter=m%2FFT&action=showdetails&offset="+key+"&2waynocompress=1&id="+value;
								//System.out.println("urlT--"+urlT);
								lsTmp.add(urlT);
								//succUrl.add(urlT);
								count++;
								System.out.println("count--"+count);
							}
						}catch(Exception e){
							failOffset.add(fail);
							System.out.println("fail2---"+fail);
							break;
							//System.out.println("wrong : "+Thread.currentThread().getName()+"--"+offset+"--"+e.toString());
						}
					}
					if(count == 10){
						for(String oo : lsTmp){
							succUrl.add(oo);
							System.out.println("succ2----"+oo);
						}
						
					}
					}catch(Exception e){
						failOffset.add(fail);
						System.out.println("fail3---"+fail);
						
					}		     
		     	}
		   for(String result : succUrl){
				writer.write(result);
				writer.newLine();
			}
			for(String result : failOffset){
				writerFail.write(result);
				writerFail.newLine();
			}
			writer.close();
			writerFail.close();
	System.out.println("-------end-------");
	
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
