package com.dulishuo.indexedu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.dulishuo.util.CrawlerUtil;
import com.dulishuo.util.FileUtil;
import com.dulishuo.yimusanfendi.Crawler;

public class PostGet {

	static int count = 1;
	static Logger log = Logger.getLogger(PostGet.class);
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//String[] types = {"奖学金-13","留学费用-18","申请技巧-6","择校建议-2"};
		String[] types = {"择校建议-2"};
		BufferedWriter bw = FileUtil.FileWriter("result/post1.json");
		
		for(String xx : types){
			int flag = 1;
			List<String> src = FileUtil.FileToList("url/url_"+xx.split("-")[0]+".txt");
			//List<String> src = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/索学网/url_"+xx.split("-")[0]+".txt");
			int type = Integer.parseInt(xx.split("-")[1]);
			for(String yy : src){
				try {
					Thread.currentThread().sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				log.info("process___"+count+"___"+(flag++));
				String rst  =  getResult(yy,type);
				if(rst != "-1"){
					bw.write(rst);
					bw.newLine();
					bw.flush();
				}
					
			}
			count++;
			
		}
		
		bw.close();
		
		//写入文件
		//FileUtil.ListToFile(result,"C:/Users/强胜/Desktop/dataCrawler/索学网/postAll.json");
		
		log.info("____end_____");
	}
	
	public static String getResult(String url, int type){
		String result = "-1";
		String response = "-1";
		int flag = 0;
		//重复请求3次
		while(response == "-1" && flag < 3){
			response = CrawlerUtil.httpRequest(url);
			if(response != "-1")
				break;
			flag++;
		}
		//请求失败，则直接返回
		if(response == "-1")
			return result;
		Document html = Jsoup.parse(response);
		//页面404不存在直接返回
		if(html.toString().indexOf("class=\"content_404\"") != -1)
			return result;
		/*
		 * url页面分为两种类型
		 * http://www.indexedu.com/apply/sca/14594.html
		 * http://school.indexedu.com/leicester/news/2014102314498.html
		 */
		String author_say = "";
		long author_date = 0 ;
		String title = "";
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Element content = html.getElementById("content");
			if(url.indexOf("http://school.indexedu.com") == -1){
				
				Element article = content.getElementsByClass("articleleft_ct").get(0);
				title = article.getElementsByTag("h1").get(0).html();
				author_date = getDate(content.getElementsByClass("info_left").get(0).getElementsByTag("span").get(0).text().toString().replace("索学网", "").trim(),1);
				
				
				Element text = content.getElementById("endtext");
				//author_say = getText(text);
				author_say = text.html();
			
			}else{
				Element article = content.getElementsByClass("lf_article_content").get(0);
				title = article.getElementsByTag("h1").get(0).html();
				author_date = getDate(article.getElementsByClass("lf_article_title").get(0).getElementsByTag("span").get(0).text().toString().replace("发布日期：", "").trim(),2);
				Element text = article.getElementsByClass("lf_article_cntent").get(0);
				author_say = text.html();
			}
			
			map.put("author_date", author_date);
			//来源网站
			map.put("origin", "http://www.indexedu.com/");
			//1代表来源一亩三分地 2代表chaseDream3代表尚友網4代表索學網
			map.put("from",4);
			//内容链接
			map.put("url", url);
			//帖子标题
			map.put("title", title);
			//帖子类型
			map.put("type", type);
		    //楼主1楼自述
		    map.put("author_say", author_say);
			//页码
			map.put("page", 1);
			String xx = tt(title);
			if(!xx.equals("-1")){
				JSONObject rst  = JSONObject.fromObject(xx);
				 if(rst.containsKey("property-dict")){
					 if(rst.getString("property-dict")!=null && !rst.getString("property-dict").equals("{}")){
						 //保留字段
						 map.put("property-dict", rst.getString("property-dict"));
			        	 JSONObject dict = rst.getJSONObject("property-dict");
			        	 if(dict.containsKey("school")){
			        		 //匹配到的school的id，如果有多个，则是数组[123,345]
			        		 String school  = dict.get("school").toString().replace("[", "").replace("]", "");
			 				if(school.indexOf(",") == -1){
			 					if(school.length()>1)
			 						map.put("school", school);
			 					else
			 						map.put("school", -1);
			 						
			 				}else
			 					map.put("school", school.split(",")[0]);			        		 
			        	 }else
			        		 map.put("school", -1);
			        	 
			        	 if(dict.containsKey("program")){
			        		 //匹配到的school的id，如果有多个，则是数组[123,345]
			        		 String program  = dict.get("program").toString().replace("[", "").replace("]", "");
			 				if(program.indexOf(",") == -1){
			 					if(program.length()>1)
			 						map.put("program", program);
			 					else
			 						map.put("program", -1);
			 						
			 				}else
			 					map.put("program", program.split(",")[0]);			        		 
			        	 }else
			        		 map.put("program", -1);
					 }
					 else{
						 map.put("property-dict", null);
						 map.put("school", -1);
						 map.put("program", -1);
					 }
					
		         }else{
		        	 map.put("property-dict", null);
		        	 map.put("school", -1);
		        	 map.put("program", -1);
		         }
			}else{
	        	 map.put("property-dict", null);
	        	 map.put("school", -1);
	        	 map.put("program", -1);
	         }
			map.put("is_new",0);
			JSONObject json = JSONObject.fromObject(map);
			result = json.toString();
		}catch(Exception e){
			log.error(e.toString());
			return result;
		}
		
		
		return result;
	}

	public static long getDate(String str, int type){
		long result = 0 ;
		SimpleDateFormat sdf = null;
		if(type == 1)
			sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(type ==2)
			sdf=new SimpleDateFormat("yyyy年MM月dd日");
		try{
			Date d=sdf.parse(str);
			result = d.getTime()/1000;
		}catch(Exception e){
			log.error(e.toString());
			return result;
		}
			
		return result;
	}
	
	public static String getText(Element ele){
		String result = "";
		
		
		
		return result;
		
	}
	
	public static String tt(String req){
		String result = "-1";
		//String regex = "[^\u4e00-\u9fa5_a-zA-Z]+";
		
		//req = req.replaceAll(regex, "");
		String str = "";
		try {
			str = "http://123.57.250.189:8818/question_process?q="+URLEncoder.encode(req,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			URL url = new URL(str);
	         HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
	         urlcon.connect();         //获取连接
	         InputStream is = urlcon.getInputStream();
	         BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
	         StringBuffer bs = new StringBuffer();
	         String each = null;
	         while((each=buffer.readLine())!=null){
	             bs.append(each);
	         } 
	         result = bs.toString();
		}catch(Exception e){
			e.printStackTrace();
			return result;
		}
		       
         return result;
	}
}
