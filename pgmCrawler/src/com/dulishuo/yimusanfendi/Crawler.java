package com.dulishuo.yimusanfendi;

import java.io.BufferedWriter;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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

import com.dulishuo.util.CrawlerUtil;


public class Crawler {
	static boolean gl = true;
	static String stopSentence = "注册一亩三分地论坛，查看更多干货！ 您需要 登录 才可以下载或查看，没有帐号？获取更多干货，注册来地里当农民！   x";
	List<String> list = new ArrayList<String>();
	public static void main(String[] args) throws IOException{
		long start =System.currentTimeMillis();
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream("url/url_1.txt")));
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream("url/url_2.txt")));
		BufferedReader reader3 = new BufferedReader(new InputStreamReader(new FileInputStream("url/url_3.txt")));
		BufferedReader reader4 = new BufferedReader(new InputStreamReader(new FileInputStream("url/url_4.txt")));
		BufferedReader reader5 = new BufferedReader(new InputStreamReader(new FileInputStream("url/url_5.txt")));
		BufferedReader reader6 = new BufferedReader(new InputStreamReader(new FileInputStream("url/url_6.txt")));
		
		BufferedWriter fw = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("result/postAll.json")));
		BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("result/post_申请哪些学校.json")));
		BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("result/post_PSCV.json")));
		BufferedWriter writer3 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("result/post_面试.json")));
		BufferedWriter writer4 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("result/post_推荐信.json")));
		BufferedWriter writer5 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("result/post_申请总结.json")));
		BufferedWriter writer6 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("result/post_经验总结.json")));
		
		BufferedWriter bwfail1 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("fail/failUrl.txt")));
		BufferedWriter bwfail2 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("fail/failUrl.txt")));
		BufferedWriter bwfail3 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("fail/failUrl.txt")));
		BufferedWriter bwfail4 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("fail/推荐信failUrl.txt")));
		BufferedWriter bwfail5 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("fail/failUrl.txt")));
		BufferedWriter bwfail6 = new BufferedWriter(new OutputStreamWriter (new FileOutputStream ("fail/failUrl.txt")));
		
		List<String> urls1 = new ArrayList<String>();
		List<String> urls2 = new ArrayList<String>();
		List<String> urls3 = new ArrayList<String>();
		List<String> urls4 = new ArrayList<String>();
		List<String> urls5 = new ArrayList<String>();
		List<String> urls6 = new ArrayList<String>();
		
		String each_line1 = "";		
		String each_line2 = "";		
		String each_line3 = "";		
		String each_line4 = "";		
		String each_line5 = "";		
		String each_line6 = "";		
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
		
		reader1.close();
		reader2.close();
		reader3.close();
		reader4.close();
		reader5.close();
		reader6.close();
		
		System.out.println("_________end__read_________"+urls1.size());
		System.out.println("_________end__read_________"+urls2.size());
		System.out.println("_________end__read_________"+urls3.size());
		System.out.println("_________end__read_________"+urls4.size());
		System.out.println("_________end__read_________"+urls5.size());
		System.out.println("_________end__read_________"+urls6.size());
		
		for(int i = 0 ; i < urls1.size(); i++){
			System.out.println("capture___1__"+i);
			try{
				String xx = getPost(urls1.get(i),2);
				
				writer1.write(xx);
				writer1.newLine();
				writer1.flush();
				fw.write(xx);
				fw.newLine();
				fw.flush();
			}catch(Exception e){
				bwfail1.write(urls1.get(i));
				bwfail1.newLine();
				bwfail1.flush();
			}			
		}
		for(int i = 0 ; i < urls2.size() ; i++){
			System.out.println("capture__2___"+i);
			try{
				String xx = getPost(urls2.get(i),3);
				writer2.write(xx);
				writer2.newLine();
				writer2.flush();
				fw.write(xx);
				fw.newLine();
				fw.flush();
			}catch(Exception e){
				bwfail2.write(urls2.get(i));
				bwfail2.newLine();
				bwfail2.flush();
			}			
		}
		for(int i = 0 ; i < urls3.size() ; i++){
			System.out.println("capture__3___"+i);
			try{
				String xx = getPost(urls3.get(i),4);
				writer3.write(xx);
				writer3.newLine();
				writer3.flush();
				fw.write(xx);
				fw.newLine();
				fw.flush();
			}catch(Exception e){
				bwfail3.write(urls3.get(i));
				bwfail3.newLine();
				bwfail3.flush();
			}			
		}
		for(int i = 0 ; i < urls4.size() ; i++){
			System.out.println("capture__4___"+i);
			try{
				String xx = getPost(urls4.get(i),5);
				writer4.write(xx);
				writer4.newLine();
				writer4.flush();
				fw.write(xx);
				fw.newLine();
				fw.flush();
			}catch(Exception e){
				bwfail4.write(urls4.get(i));
				bwfail4.newLine();
				bwfail4.flush();
			}			
		}
		for(int i = 0 ; i < urls5.size() ; i++){
			System.out.println("capture__5___"+i);
			try{
				String xx = getPost(urls5.get(i),6);
				writer5.write(xx);
				writer5.newLine();
				writer5.flush();
				fw.write(xx);
				fw.newLine();
				fw.flush();
			}catch(Exception e){
				bwfail5.write(urls5.get(i));
				bwfail5.newLine();
				bwfail5.flush();
			}			
		}
		for(int i = 0 ; i < urls6.size() ; i++){
			System.out.println("capture__6___"+i);
			try{
				String xx = getPost(urls6.get(i),1);
				writer6.write(xx);
				writer6.newLine();
				writer6.flush();
				fw.write(xx);
				fw.newLine();
				fw.flush();
			}catch(Exception e){
				bwfail6.write(urls6.get(i));
				bwfail6.newLine();
				bwfail6.flush();
			}			
		}
		
		writer1.close();
		writer2.close();
		writer3.close();
		writer4.close();
		writer5.close();
		writer6.close();
		fw.close();
		
		bwfail1.close();
		bwfail2.close();
		bwfail3.close();
		bwfail4.close();
		bwfail5.close();
		bwfail6.close();
    
		long end = System.currentTimeMillis();
		System.out.println("---end-----use time :"+(end-start));
	    
		
	}
	
	public static String getPost(String url , int type) throws IOException{
		String result = "";
		String response = "";	   
		String author_say = "";
		String author_date = "";
		Map<String,Object> map = new HashMap<String,Object>();
		//System.out.println(url);
		response = httpRequest(url);
		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println(response);
	    Document html = Jsoup.parse(response);
	    String title = html.getElementById("thread_subject").text().toString();
	    Elements posts = html.getElementById("postlist").children();
	    JSONArray cmtList = new JSONArray();
	    
	    try{
	    	int count = 1;
	    	for(int i = 0 ; i < posts.size() ; i++){
	    		//判断是否是post
	    		if(posts.get(i).attr("id").toString().indexOf("post")!= -1 && !posts.get(i).attr("id").equals("postlistreply") && !posts.get(i).attr("id").equals("hiddenpoststip") && !posts.get(i).attr("id").equals("hiddenposts")){
	    			if(count == 1 ){
			    		author_say = posts.get(i).getElementsByClass("t_f").get(0).html();
			    		author_date = posts.get(i).getElementsByClass("authi").get(0).getElementsByTag("em").get(0).text().replace("发表于", "").trim();
			    	}else{
			    		Map<String,Object> cmtMap = new HashMap<String,Object>();
			    		try{
			    			String cmt = posts.get(i).getElementsByClass("t_f").get(0).html();
			    			String date = posts.get(i).getElementsByClass("authi").get(0).getElementsByTag("em").get(0).text().replace("发表于", "").trim();
					    	cmtMap.put("floor_seq", count);
					    	cmtMap.put("content", cmt);
					    	cmtMap.put("date", date);
					    	JSONObject cmtJson = JSONObject.fromObject(cmtMap);
					    	cmtList.add(cmtJson);
			    		}catch(Exception e){
			    			
			    		}
			    		
			    		
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
	    map.put("origin", "http://www.1point3acres.com");
	    //1代表来源一亩三分地 2代表chaseDream
	    map.put("from",1);
	    //内容链接
	    map.put("url", url);
	    //帖子标题
	    map.put("title", title);
	    //保留字段
	    String atm[] = new String[0];
		map.put("atm", atm);
		//是否是新加入的数据
		map.put("is_new",0);
		//楼主1楼自述
		map.put("author_say", author_say);
		//楼下跟风评论
		map.put("comment", cmtList);
		//页码
		int pages = html.getElementById("pgt").getElementsByClass("pgt").get(0).childNodeSize();
		if(pages == 0 ){
			map.put("pages", 1);
		}else{
			int aSum = html.getElementById("pgt").getElementsByClass("pgt").get(0).getElementsByClass("pg").get(0).select("a").size();
	    	Element last = html.getElementById("pgt").getElementsByClass("pgt").get(0).select("a").get(aSum-2);
	    	int totalPages = Integer.parseInt(last.attr("href").toString().substring(last.attr("href").toString().lastIndexOf("=")+1));
	    	map.put("pages", totalPages);
		}
		
		String res = tt(title);
		//System.out.println("---");
		if(!res.equals("-1")){
			JSONObject rst  = JSONObject.fromObject(res);
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
				 }
				 else{
					 map.put("property-dict", null);
					 map.put("school", -1);
				 }
				
	         }else{
	        	 map.put("property-dict", null);
	        	 map.put("school", -1);
	         }
		}
		
		//.out.println("pages__"+pages);
		
		JSONObject json = JSONObject.fromObject(map);
		//result = getContent(json.toString());
		result = json.toString();
		return result;
	}
	
	
	
	public static String httpRequest(String url){
		String response = "-1";
		
		HttpClient client = new HttpClient();
		//设置代理服务器的ip地址和端口  
		//client.getHostConfiguration().setProxy("202.108.23.247", 80);  
		//使用抢先认证  
		//client.getParams().setAuthenticationPreemptive(true); 
		GetMethod getMethod = new GetMethod(url);
		//请求配置
		String ck = "tjpctrl=1439455956083; __utma=142000562.1509796625.1436709690.1438412213.1439453720.13; __utmz=142000562.1439453720.13.9.utmcsr=mail.qq.com|utmccn=(referral)|utmcmd=referral|utmcct=/cgi-bin/readtemplate; pgv_pvi=3811975168; 4Oaf_61d6_saltkey=nnmfbob3; 4Oaf_61d6_lastvisit=1437378911; 4Oaf_61d6_atarget=1; 4Oaf_61d6_visitedfid=27; 4Oaf_61d6_sid=Q89d9W; 4Oaf_61d6_lastact=1439454242%09home.php%09spacecp; 4Oaf_61d6_sendmail=1; __utmb=142000562.12.10.1439453720; __utmc=142000562; __utmt=1; 4Oaf_61d6_nofocus_member=1; 4Oaf_61d6_nofocus_forum=1; 4Oaf_61d6_ulastactivity=b1ccszmakCkkoWTaybWumhPQy0hx1PDIt6FDBQ5WElNhsEaEot6p; 4Oaf_61d6_auth=5808WxQ6wi8Gf9YPAp2asvku33LiM5xIWqdR67gpl8HOZTL87ISKHL5cTtUOFQR8V%2Be4wSyU5Taa4q9Va33Bq9v%2FlRM; 4Oaf_61d6_lastcheckfeed=175519%7C1439454242; 4Oaf_61d6_lip=118.187.68.34%2C1439453999; 4Oaf_61d6_nofavfid=1; 4Oaf_61d6_onlineusernum=258; 4Oaf_61d6_forum_lastvisit=D_27_1439454207; 4Oaf_61d6_smile=4D1; 4Oaf_61d6_viewid=tid_72476; 4Oaf_61d6_checkpm=1; 4Oaf_61d6_checkfollow=1";
         
        // 每次访问需授权的网址时需带上前面的 cookie 作为通行证  
        getMethod.setRequestHeader("Cookie", ck);
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
	
	public static String getContent(String xx){
		String result = "";
		//System.out.println("result"+xx);
		String reg = "<font[\\s]class=\\\\\"jammer\\\\\">.*?<\\\\/font&gt;";
		String reg13 = "<font[\\s]class=\\\\\"jammer\\\\\">.*?<\\\\/font>";
		String reg14 = "<font[\\s]class=\\\\\"jammer\\\\\"><\\\\/font&gt;>";
		String reg2 = "<font[\\s]class=\\\\\"jammer\\\\\"><\\\\/font>";
		String reg3 = "<(img).*?>.*?</img>";
		String reg4 = "<div[\\s]class=\\\\\"attach_nopermission[\\s]attach_tips\\\\\">[\\s\\S]*?<\\\\/div>";
		String reg5 = "<span[\\s]class=\\\\\"atips_close\\\\\"[\\s]onclick=\\\\\"this.parentNode.style.display='none'\\\\\">x<\\\\/span>[\\s\\S]*<\\\\/div>";
		String reg6 = "<span[\\s]style=\\\\\"display\\:[\\s]*none\\\\\">\\\\/span&gt;";
		String reg7 = "<span[\\s]style=\\\\\"display\\:[\\s]*none\\\\\"><\\\\/span>";
		String reg8 = "<(img).*?/>";
		String reg9 = "<(br).*?>";
		String reg10 = "<span[\\s]style=\\\\\"display\\:[\\s]*none\\\\\">.*?<\\\\/span&gt;";
		String reg11 = "<span[\\s]style=\\\\\"display\\:[\\s]*none\\\\\">.*?<\\\\/span>";
		String reg12 = "<div[\\s]class=\\\"quote\\\">[\\s]*<blockquote>.*?<\\/blockquote>[\\s]*<\\/div>";
		
		result = xx.replace("\n", "").replaceAll(reg, "").replaceAll(reg2, "").replaceAll(reg3, "").replaceAll(reg4, "").replaceAll(reg5, "").replaceAll(reg6, "").replaceAll(reg7, "").replaceAll(reg8, "").replaceAll(reg9, "<br>").replaceAll(reg10, "").replaceAll(reg11, "").replaceAll(reg12, "").replaceAll(reg13, "").replaceAll(reg14, "");
		//result = xx;
		//.replace("\\/", "/").replace("\\\"", "\"")
		//result = earse(result);
		return result;
	}
	
	public static String earse(String src){
		String result = "";
		
		String regex1 = "<(br).*?>";   //去除所有标签，只剩br
		String regex2 = "<(div).*?>";   //去除所有标签，只剩br
		String regex3 = "<(p).*?>";   //去除所有标签，只剩br
		String regex4 = "<(h).*?>";   //去除所有标签，只剩br
		String regex5 = "<(ul).*?>";   //去除所有标签，只剩br
		String regex6 = "<(ol).*?>";   //去除所有标签，只剩br
		String regex7 = "<(li).*?>";   //去除所有标签，只剩br
		String regex8 = "<(table).*?>";   //去除所有标签，只剩br
		String regex9 = "<(tr).*?>";   //去除所有标签，只剩br
		String regex10 = "<(td).*?>";   //去除所有标签，只剩br
		String regex11 = "<(tbody).*?>";   //去除所有标签，只剩br
		String regex12 = "<(font).*?>";   //去除所有标签，只剩br
		String regex13 = "<(span).*?>";   //去除所有标签，只剩br
		String regex14 = "<(a).*?>";   //去除所有标签，只剩br
		
		//src = src.replace("</div>", "<br>").replace("</p>","").replace("</h>","").replace("</ul>","").replace("</li>","").replace("\\n", "").replace("</ol>","").replace("</tr>","");
		
		result = src.replaceAll(regex1, "<br>").replaceAll(regex2, "<div>").replaceAll(regex3, "<p>").replaceAll(regex4, "<h>").replaceAll(regex5, "<ul>").replaceAll(regex6, "<ol>").replaceAll(regex7, "<li>").replaceAll(regex8, "<table>").replaceAll(regex9, "<tr>").replaceAll(regex10, "<td>").replaceAll(regex11, "<tbody>").replaceAll(regex12, "<font>").replaceAll(regex13, "<span>").replaceAll(regex14, "<a>").replaceAll("<&nbsp[\\;]?","").replace("<br />","<br>");
		
		return result.trim();
	}
	
	public static String getAuthor_say(Element e){
		String result = "";
		
		String stop = e.child(0).html();
		System.out.println("xx___"+e.getElementsByClass("attach_nopermission").get(0).html());
		
		System.out.println("stop__"+stop);
		result = e.html().replace(stop, "");
		System.out.println("e__"+result);
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
