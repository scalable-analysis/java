package com.dulishuo.yimusanfendi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.Cookie;

public class testCapture {
	public static void main(String[] args) throws IOException{
		
		String charset = "UTF-8";  
		 String loginUrl = "https://secure.usnews.com/member/login";
	        // 需登陆后访问的 Url  
	        String dataUrl = "http://premium.usnews.com/best-colleges/princeton-university-2627?int=9ff208"; 
	        
	        //BufferedWriter fw = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/test.html"));
	        BufferedWriter fw1 = new BufferedWriter (new OutputStreamWriter (new FileOutputStream ("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/test1.html")));
	        FileWriter fw2 = new FileWriter("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/test3.html");
	        HttpClient httpClient = new HttpClient();  
	       
	  
	        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式  
	        PostMethod postMethod = new PostMethod(loginUrl);  
	        
	  
	        // 设置登陆时要求的信息，用户名和密码  
	        NameValuePair[] data = { 
	        		new NameValuePair("username", "andrew.liang@dulishuo.com"),  
	                new NameValuePair("password", "Lcy19930524!"),
	        		new NameValuePair("referer", "")
	        		};  
	        postMethod.setRequestHeader("Host", "secure.usnews.com");  
	        postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");    
	        postMethod.setRequestHeader("Accept", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");  
	        postMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");  
	        postMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");  
	        postMethod.setRequestHeader("DNT", "1");  
	        postMethod.setRequestHeader("Referer", "https://secure.usnews.com/member/login");  	 
	        postMethod.setRequestHeader("Connection", "keep-alive");  
            postMethod.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); 
	 
                   
	        postMethod.setRequestBody(data);  
	        try {  
	            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略  
	            httpClient.getParams().setCookiePolicy(  
	                    CookiePolicy.BROWSER_COMPATIBILITY);  
	            httpClient.executeMethod(postMethod);  
	            
	            fw1.write(postMethod.getResponseBodyAsString());
	            fw1.flush();
	            fw1.close();
	            // 获得登陆后的 Cookie  
	            Cookie[] cookies = (Cookie[]) httpClient.getState().getCookies();  
	            StringBuffer tmpcookies = new StringBuffer();  
	            for (Cookie c : cookies) {  
	                tmpcookies.append(c.toString() + ";");  
	                System.out.println(c);
	            }  
	            System.out.println(postMethod.getStatusText());
	            System.out.println(postMethod.getStatusCode());
	         //   System.out.println(postMethod.get);
	            
	           
	            String tt = "auth=\"926U70ZZZ30m64qMaJX1IJKCfvJmO4S01aWgzXW7nhmK98cAQx2jK7JlfmNUOGxQscpDsT71h9Y4QVtytPtl7cmQOJ9GoTZ9LlNFxv1-CXTcLicvZaj2COeYEcs_cRCBa8n3ILqfhcXV_wFnly7akKSCFbKzyw4_jVq51wl6sO4tKxwb-rz0eZHuZAhRkguBPkb1t6uxa6kNu5-1mAULLSWFOZ8xFjVqiqhqFXv0JRRJxLW4d9e-0FPj6llT3Hgj.eNqrVkrLLCouic9LzE1VslJQck7MrMxU0lFQyklEEvXJTMxLB4lmpgC5RgYWxoYG5kBuSX52ah5IgaVZYrJBsrFBUmpqikmyRapFcrKJsbmZsUGaUaJpmnmSUi0AirUeeQ\"; tk=96ac0c30beed4c8e8cc437630f2a5f7b; c=2085190804; compstat=compstu; usnQuantCast=D; __gads=ID=24381f81148ef0b6:T=1439363530:S=ALNI_MbygNq4VzFdG9nrMLE_yO1d_V3M8A; __qca=P0-376455809-1439363544561; _vis_opt_s=1%7C; _vis_opt_test_cookie=1; s_cc=true; s_sq=%5B%5BB%5D%5D; __ybotb=9597; __ybotu=id8fvkxmwqln2c3w6g; __ybotv=1439367772921; __ybots=id8iepkpqb11hvo56k.0.id8iepko78go619lul.1; OX_sd=1; __ybotc=http%3A//ads-adswest.yldbt.com/m/; _trp_hit_11882/41456_728x90=5; _trp_hit_11882/41456_300x250=5; __utma=1.69014033.1439363543.1439363543.1439367785.2; __utmb=1.1.10.1439367785; __utmz=1.1439363543.1.1.utmcsr=colleges.usnews.rankingsandreviews.com|utmccn=(referral)|utmcmd=referral|utmcct=/best-colleges; __utmt=1; ki_t=1439363545567%3B1439363545567%3B1439367788518%3B1%3B2; ki_r=; __ybota=; __ybote=; __ybotz=; OX_plg=swf|shk|pm; __utmc=1; usn_colleges_interstitial=2";
	            // 进行登陆后的操作1581,1602,1603,1610,1609,1608,1607,1606,1605,1620,1619,1617,1616,1622,1626,1642,1648,1647,1657  
	            GetMethod getMethod = new GetMethod(dataUrl);  
	            // 每次访问需授权的网址时需带上前面的 cookie 作为通行证  
	            getMethod.setRequestHeader("cookie", tt);  
	            getMethod.setRequestHeader("Host", "premium.usnews.com");  
	            getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");    
	            getMethod.setRequestHeader("Accept", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");  
	            getMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");  
	            getMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");   
	            getMethod.setRequestHeader("DNT", "1");   
	            getMethod.setRequestHeader("Connection", "keep-alive");  
		       
		       
	          
	            
	           
	            httpClient.executeMethod(getMethod);  
	            getMethod.getParams().setContentCharset("UTF-8");
	            System.out.println(getMethod.getResponseCharSet());
	            System.out.println(getMethod.getResponseHeader("Set-Cookie"));
	            // 打印出返回数据，检验一下是否成功  
	            String text = "Set-Cookie"; 
	           /*
	            BufferedReader reader=new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream(),"ISO-8859-1"));
	            
	            String tmp=null;
	            String htmlRet=""; 
	            while((tmp=reader.readLine())!=null){
	            htmlRet+=tmp+"\r\n";
	            }*/

	            //text = new String(htmlRet.getBytes("ISO-8859-1"),"utf-8");
	            InputStream inputStream=getMethod.getResponseBodyAsStream();
	            
	            //使用这个来读
	            GZIPInputStream gzipInputStream=new GZIPInputStream(inputStream);
	             
	             
	            InputStreamReader inputStreamReader=
	                    new InputStreamReader(gzipInputStream,Charset.forName("utf-8"));
	             
	            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
	            String line=null;
	            String sb = "";
	            while((line=bufferedReader.readLine())!=null){
	                sb += line;
	            }
	            System.out.println(getMethod.getStatusText());
	            System.out.println(getMethod.getStatusCode());
	            System.out.println(sb);
	            String xx =getMethod.getResponseBodyAsString();
	            //System.out.println(xx);
	            fw2.write(sb);
	            fw2.flush();
	            fw2.close();
	            
	            //System.out.println(text);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	}
}
