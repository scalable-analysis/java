package com.dulishuo.qianmu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

public class Util {
	public static String crawler(String url){
		String response = "-1";
		
		//如果没返回正确，这重复请求，10次为上限
		int flag = 0 ;
		while(flag < 10){
			response = httpRequest(url);
			if(!response.equals("-1"))
				return response;
			flag++;
		}
		return "-1";
	}
	public static String httpRequest(String url){
		String response = "-1";
		String cookie = "Hm_lvt_f409979f9c1034edcba2b24ea2b0a835=1444730041,1444735066,1444788843,1444894003; JSESSIONID=7C19CDDCA87BE9E3548ADB93491848C0; Hm_lpvt_f409979f9c1034edcba2b24ea2b0a835=1444894022";
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.setRequestHeader("cookie", cookie);
		getMethod.setRequestHeader("Host", "www.qianmu.org");  
		getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36");    
		getMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
		getMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");  
		    //getMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");  
		getMethod.setRequestHeader("DNT", "1");
		
	    try {
			client.executeMethod(getMethod);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return response;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return response;
		}
	    try {
		    BufferedReader reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream()));  
		    StringBuffer stringBuffer = new StringBuffer();  
		    String str = "";  
		    while((str = reader.readLine())!=null){  
		        stringBuffer.append(str);  
		    }  
		    response = stringBuffer.toString();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return response;
		}
	    
	    return response;
	}
}
