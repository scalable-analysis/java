package com.dulishuo.statistics;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class test {
	static int count = 1;
	public static void main(String[] args){
	/*	List<String> list = FileUtil.FileToList("C:/Users/强胜/Desktop/杂项/chaseDream/Top200-5帖子.txt");
		List<String> ress = new ArrayList<String>();
		
		for(String xx : list){
			try{
				System.out.println("process__"+(count++));
				String url = xx.split("\t")[3];
				String res = Util.httpRequest(url);
				Document html = Jsoup.parse(res);
				String title = html.getElementById("thread_subject").text();
				System.out.println(title);
				url = title + "\t" + xx;
				ress.add(url);
			}catch(Exception e){

			}
		}
		
		FileUtil.ListToFile(ress, "C:/Users/强胜/Desktop/杂项/chaseDream/Top200帖子tt.txt");*/
		
		List<String> list = FileUtil.FileToList("C:/Users/强胜/Desktop/杂项/chaseDream/Top200帖子tt.txt");
		List<String> ress = new ArrayList<String>();
		for(String xx : list){
			String month = xx.split("\t")[1].split("-")[1];
			if(month.equals("9")){
				ress.add(xx);
			}
		}
		for(String xx : list){
			String month = xx.split("\t")[1].split("-")[1];
			if(month.equals("10")){
				ress.add(xx);
			}
		}
		for(String xx : list){
			String month = xx.split("\t")[1].split("-")[1];
			if(month.equals("11")){
				ress.add(xx);
			}
		}
		for(String xx : list){
			String month = xx.split("\t")[1].split("-")[1];
			if(month.equals("12")){
				ress.add(xx);
			}
		}
		FileUtil.ListToFile(ress, "C:/Users/强胜/Desktop/杂项/chaseDream/Top200帖子tttt.txt");
		
		
	}
}
