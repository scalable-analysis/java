package com.dulishuo.shenqingfang;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dulishuo.util.CrawlerUtil;
import com.dulishuo.util.FileUtil;

import java.util.*;

public class pgmGet {
	static Logger log = Logger.getLogger(pgmGet.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		System.out.println(" . . . start . . .");
		long start = System.currentTimeMillis();
		
		//getPgmUrl();
		test();
		
		long end = System.currentTimeMillis();
		System.out.println(" . . . end . . . use time : " + (end - start)
				+ "ms ");
		
	}

	private static void test() {
		// TODO Auto-generated method stub
		//String filePath = "C:/Users/强胜/Desktop/sch.txt.json";
		String filePath = "C:/Users/强胜/Desktop/xxxxx.txt";
		List<String> urlList =  FileUtil.FileToList(filePath);
		System.out.println(urlList.size());
		int flag = 2;
		String url = "";
		String response = "";
		List<String> ress = new ArrayList<String>();
		String link = "";
		for(String xx : urlList){
			url = "https://www.applysquare.com/zh-cn/institute/"+xx.split("\t")[1]+"/programs/";
			try{
				response = CrawlerUtil.httpsRequest(url);
				Document doc = Jsoup.parse(response);
				Elements eles = doc.getElementsByClass("box-content-flat");
				if(eles.size() < 1)
					continue;
				for(Element ele : eles){
					try{
						//判断年份
						String year = ele.getElementsByTag("h2").get(0).text().substring(0, 4);
						if(year.equals("2016")){
							Elements hrefs = ele.getElementsByTag("a");
							for(Element href : hrefs){
								link = href.attr("href");
								if(link.indexOf("zh-cn/program") != -1)
									System.out.println(href.parent().text());
									//ress.add(link+"\t"+);
							}
						}else
							continue;
					}catch(Exception e){
						log.error("something is wrong . . . ");
					}
					
				}
			}catch(Exception e){
				
			}
		}
	}

	private static void getPgmUrl() {
		// TODO Auto-generated method stub
		String filePath = "sch.txt";
		List<String> urlList =  FileUtil.FileToList(filePath);
		List<String> fail = new ArrayList<String>();
		List<String> urlResult = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		String url ="";
		String response = "";
		int flag = 0;
		for(String xx : urlList){
			log.info("url抓取执行到 " + flag++ +" 个 . ");
			//每次调用沉睡100ms
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			url = "https://www.applysquare.com/zh-cn/institute/"+xx.split("\t")[1]+"/programs/";
			try{
				response = CrawlerUtil.httpsRequest(url);
				urlResult.addAll(process1(response,xx.split("\t")[0]));
			}catch(Exception e){
				fail.add(xx);
			}
		}
		FileUtil.ListToFile(urlResult, "result/urls.txt");
		log.info("抓取programd的url地址完毕 ,一共 "+urlResult.size()+" 个 。");
		//抓取具体的program
		String tmp = "";
		int total = urlResult.size();
		flag = 0;
		for(String xx : urlResult){
			//每次调用沉睡100ms
			try {
				Thread.currentThread().sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			log.info("一共 "+total+" 个，执行到 " + flag++ +" 个 . ");
			try{
				response = CrawlerUtil.httpsRequest(xx.split("\t")[1]);
				Document doc = Jsoup.parse(response);
				tmp = doc.getElementsByTag("body").get(0).html();
				JSONObject json = new JSONObject();
				json.put("content", tmp);
				json.put("id", xx.split("\t")[0]);
				json.put("url",xx.split("\t")[1]);
				if(tmp.length() > 100)
					result.add(json.toString());
				
			}catch(Exception e){
				fail.add(xx);
			}
		}
		
		FileUtil.ListToFile(result, "result/result.json");
	}

	/*private static String process2(String response) {
		// TODO Auto-generated method stub
		String res = "";
		if(response.length() < 100)
			return res;
		return res;
	}*/

	private static List<String> process1(String response , String insid) {
		// TODO Auto-generated method stub
		List<String> res = new ArrayList<String>();
		String link = "";
		Document doc = Jsoup.parse(response);
		Elements eles = doc.getElementsByClass("box-content-flat");
		//System.out.println(eles.size());
		if(eles.size() < 1)
			return res;
		for(Element ele : eles){
			try{
				//判断年份
				String year = ele.getElementsByTag("h2").get(0).text().substring(0, 4);
				if(year.equals("2016")){
					Elements hrefs = ele.getElementsByTag("a");
					for(Element href : hrefs){
						link = href.attr("href");
						if(link.indexOf("zh-cn/program") != -1)
							res.add(insid+"\t"+"https://www.applysquare.com"+link);
					}
				}else
					continue;
			}catch(Exception e){
				log.error("something is wrong . . . ");
			}
			
		}
		

		return res;
	}
	
	
	

}
