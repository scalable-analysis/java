package com.dulishuo.indexedu;

import com.dulishuo.util.FileUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

import org.apache.commons.lang.StringEscapeUtils;

import net.sf.json.JSONObject;

public class TagEarse {

	static int flag = 1;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<String> post = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/索学网/post.json");
		BufferedWriter bw = FileUtil.FileWriter("C:/Users/强胜/Desktop/dataCrawler/索学网/postT1.json");
		
		for(String xx : post){
		
			
			//String say = json.getString("author_say");
			JSONObject json = JSONObject.fromObject(xx);
			System.out.println("process___"+(flag++)+"___"+json.getString("title"));
			//System.out.println(json.getString("author_say"));
			//System.out.println(say);
			
			String[] atm = new String[0];
			json.put("atm", atm);
			
			String yy  = json.toString();
			String reg_img1 = "<img.*?\\\\/>";
			String reg_img2 = "<img.*?>[\\s][\\S]*?<\\\\/img>";
			String reg_footer1 = "<div[\\s]class=\\\\\"editor\\\\\">[\\s\\S]*?<\\\\/div>";
			String reg_footer2 = "<p[\\s]class=\\\\\"article_bj\\\\\">[\\s\\S]*?<\\\\/p>";
			String reg_a = "<a.*?>";
			yy = yy.replaceAll(reg_img1, "").replaceAll(reg_img2, "").replaceAll(reg_footer1, "").replaceAll(reg_footer2, "").replaceAll(reg_a, "");
			
			//System.out.println(yy);
			yy = yy.replace("\\/", "/").replace("\\n", "").replace("</a>", "");
			//System.out.println(json.getString("author_say"));
			bw.write(yy);
			
			bw.newLine();
			bw.flush();
		}
		
		bw.close();
		System.out.println("____end____");
	}


}
