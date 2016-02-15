package com.dulishuo.yimusanfendi.offer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dulishuo.util.FileUtil;

public class GetUrl {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		getUrl();
	}

	private static void getUrl() throws UnsupportedEncodingException {
		BufferedWriter bw = FileUtil.FileWriter("result/urls.txt");
		// TODO Auto-generated method stub
		String basicUrl = "http://www.1point3acres.com/bbs/forum.php?mod=forumdisplay&fid=82&sortid=164&filter=sortid&sortid=164&page=";
		List<String> xx = new ArrayList<String>();
		for(int i = 1 ; i < 691 ; i++){
			System.out.println("process____"+i);
			try {
				Thread.currentThread().sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String res = httpRequest(basicUrl+i);
			Document html = Jsoup.parse(res);
			Element ele = html.getElementById("threadlisttableid");
			Elements tbodys = ele.children();
			int size = tbodys.size();
			for(int j = 1 ; j < size ; j++){
				try{
					String href = tbodys.get(j).getElementsByClass("icn").get(0).select("a").first().attr("href").toString();
					String mark = tbodys.get(j).getElementsByTag("th").get(0).getElementsByTag("span").get(0).text();
					bw.write(href+"\t"+mark);
					bw.newLine();
					bw.flush();
				}catch(Exception e){
					
				}
				
			}
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("_________exit");
		
	}
	
	
	public static String httpRequest(String url){
		String response = "";
		
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

}
