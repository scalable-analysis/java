package com.dls;

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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DataProcess0701 {

	public static void main(String[] args) throws IOException {
		
		long start = System.currentTimeMillis();
		JSONArray ja  = null;
		String res = "";
		//String reg = "[\u4e00-\u9fa5]+";
		//Pattern ptn = Pattern.compile(reg);
		int i = 1;
		JSONObject json = null ; 
		// TODO Auto-generated method stub
		 BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/强胜/Desktop/777.json")));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/66666.json")));
		String tmp = "";
		String tmpIns = "";
		
		while((tmp=br.readLine())!=null){
			json = JSONObject.fromObject(tmp);
			Map<String,String> map = new HashMap<String,String>();
			String Ttitle = null;
			String title = null;
			
			tmpIns = json.getString("extra_requirement");
			//Matcher matcher = ptn.matcher(tmpIns);
			
			/*if(matcher.find()){
				Ttitle = matcher.group();
				}
			title = tmpIns.substring(tmpIns.indexOf("）")+1);
			if(!title.equals("")&&!Ttitle.equals("")){
				map.put("title", title);
				map.put("Ttitle", Ttitle);
				json.putAll(map);
				System.out.println("执行到--"+(i++)+"行");
				writer.write(json.toString());
				writer.newLine();
			}*/
			if(tmpIns.toLowerCase().indexOf("gpa")!=-1){
				writer.write(tmpIns);
				writer.newLine();
			}
			
			//if(tmpIns.)
		}
		
		System.out.println("------------");
		writer.close();
	
		long end = System.currentTimeMillis();
		System.out.println("---------共用时--------"+(end-start));
		
		 
	}

}
