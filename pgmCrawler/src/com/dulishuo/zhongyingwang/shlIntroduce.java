package com.dulishuo.zhongyingwang;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class shlIntroduce {
	static int count = 1;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/中英网/school.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/dataCrawler/中英网/schoolZ.txt"));
		//BufferedWriter bw1 = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/dataCrawler/中英网/result.txt"));
		
		List<String> set = new ArrayList<String>();
		List<String> ss = new ArrayList<String>();
		String each_line = "";
		while((each_line = br.readLine()) !=null){
			set.add(each_line);			
		}
		br.close();
		
		for(String xx : set){
			System.out.println("process--------"+(count++));
			//if(xx)
			//JSONObject json = process(xx);
			JSONObject json = JSONObject.fromObject(xx);
			String yy ="";
			if(json.containsKey("tele")){
				yy+=json.getString("tele")+"\t";
			}else{
				yy+="-1"+"\t";
			}
			if(json.containsKey("title")){
				yy+=json.getString("title")+"\t";
			}else{
				yy+="-1"+"\t";
			}
			if(json.containsKey("ttitle")){
				yy+=json.getString("ttitle")+"\t";
			}else{
				yy+="-1"+"\t";
			}
			if(json.containsKey("address")){
				yy+=json.getString("address")+"\t";
			}else{
				yy+="-1"+"\t";
			}
			if(json.containsKey("web")){
				yy+=json.getString("web")+"\t";
			}else{
				yy+="-1"+"\t";
			}
			if(json.containsKey("costs")){
				yy+=json.getString("costs")+"\t";
			}else{
				yy+="-1"+"\t";
			}
			if(json.containsKey("istranslated")){
				yy+=json.getString("istranslated")+"\t";
			}else{
				yy+="-1"+"\t";
			}
			if(json.containsKey("school")){
				yy+=process(json.getString("school"));
			}
			
			bw.write(yy);
			bw.newLine();
			bw.flush();
			//bw1.write(json.getString("title")+"\t"+json.getString("ttitle"));
			//bw1.newLine();
			//bw1.flush();
			
		}
		bw.close();
		//bw.close();
		System.out.println("___________end___________");
	}
	
	public static String process(String str){
		String result = "";
		
		String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
         
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(str); 
        str=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(str); 
        str=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(str); 
        str=m_html.replaceAll(""); //过滤html标签 

        return str.trim().replaceAll("\\s*", ""); //返回文本字符串 
		
		
	}
}
