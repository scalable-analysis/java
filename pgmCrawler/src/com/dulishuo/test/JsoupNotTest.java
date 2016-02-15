package com.dulishuo.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class JsoupNotTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/test.html")));
		
		StringBuffer sb = new StringBuffer();
		String each_line = "";
		int flag = 1 ; 
		while((each_line = reader.readLine()) != null ){			
				sb.append(each_line);
				
		}
		reader.close();
		
		Document html = Jsoup.parse(sb.toString());
		
		
		//String all = html.getElementById("11").select("table").get(0).getElementsByClass("t_f").get(0).html();
		String content = html.getElementById("11").select("table").get(0).getElementsByClass("t_f").get(0).html();
		//content = Jsoup.clean(content, Whitelist.none());
		content = content.replace("\r", "").replace("\n", "");
		Pattern font = Pattern.compile("<font class=\"jammer\">.*</font>");
		Pattern div = Pattern.compile("<div class=\"quote\">.*</font>");
		Pattern img = Pattern.compile("<img.*/>");
		Matcher mth1 = font.matcher(content);
		Matcher mth2 = font.matcher(content);
		Matcher mth3 = font.matcher(content);
		while(mth1.find()){
			System.out.println(mth1.group());
			content.replace(mth1.group(), "");
		}
		while(mth2.find()){
			content.replace(mth2.group(), "");
			System.out.println(mth2.group());
		}
		while(mth3.find()){
			content.replace(mth3.group(), "");
			System.out.println(mth3.group());
		}
	
		/*Matcher ma = ptn3.matcher(content);
		while(ma.find()){
			System.out.println(ma.group());
		}*/
		//.replaceAll("<div[\\s]class=\"quote\">.</div>", "")
		
		content = Jsoup.clean(content, Whitelist.none());
		System.out.println(content);
	
	
	
	}

}
