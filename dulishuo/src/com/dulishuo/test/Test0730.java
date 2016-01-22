package com.dulishuo.test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class Test0730 {

	public static void main(String[] args) throws IOException {
		int count = 1;
		// TODO Auto-generated method stub
		BufferedWriter writer1 = FileUtil.FileWriter("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/post/post_北美MBA申请区1.json");
		BufferedWriter writer2 = FileUtil.FileWriter("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/商学院Master申请区/post/post_商学院Master申请区1.json");
		BufferedWriter writer3 = FileUtil.FileWriter("C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/商学院PHD申请区/post/post_商学院PHD申请区1.json");
		
		String path1 = "C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/北美MBA申请区/post/post_北美MBA申请区.json";
		String path2 = "C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/商学院Master申请区/post/post_商学院Master申请区.json";
		String path3 = "C:/Users/强胜/Desktop/dataCrawler/chasedream/商学院申请/商学院PHD申请区/post/post_商学院PHD申请区.json";
		
		List<String> src1 = FileUtil.FileToList(path1);
		List<String> src2 = FileUtil.FileToList(path2);
		List<String> src3 = FileUtil.FileToList(path3);
		/*List<String> urls1 = new ArrayList<String>();
		List<String> urls2 = new ArrayList<String>();
		List<String> urls3 = new ArrayList<String>();
		List<String> urls4 = new ArrayList<String>();
		List<String> urls5 = new ArrayList<String>();
		for(String xx : src1){
			JSONObject json = JSONObject.fromObject(xx);
			urls1.add(json.getString("url"));
		}
		for(String xx : src2){
			JSONObject json = JSONObject.fromObject(xx);
			urls2.add(json.getString("url"));
		}
		for(String xx : src3){
			JSONObject json = JSONObject.fromObject(xx);
			urls3.add(json.getString("url"));
		}
		for(String xx : src4){
			JSONObject json = JSONObject.fromObject(xx);
			urls4.add(json.getString("url"));
		}
		for(String xx : src5){
			JSONObject json = JSONObject.fromObject(xx);
			urls5.add(json.getString("url"));
		}
		*/
		
		for(String xx : src1){
			JSONObject json = JSONObject.fromObject(xx);
		
			String url = json.getString("url");
			List<Integer> types = new ArrayList<Integer>();	
			types.add(json.getInt("type"));
			json.put("type", types.toArray());
			writer1.write(json.toString());
			writer1.newLine();
			writer1.flush();
			
		}
		for(String xx : src2){
			JSONObject json = JSONObject.fromObject(xx);
		
			String url = json.getString("url");
			List<Integer> types = new ArrayList<Integer>();	
			types.add(json.getInt("type"));
			json.put("type", types.toArray());
			writer2.write(json.toString());
			writer2.newLine();
			writer2.flush();
			
		}
		for(String xx : src3){
			JSONObject json = JSONObject.fromObject(xx);
		
			String url = json.getString("url");
			List<Integer> types = new ArrayList<Integer>();	
			types.add(json.getInt("type"));
			json.put("type", types.toArray());
			writer3.write(json.toString());
			writer3.newLine();
			writer3.flush();
			
		}
		writer1.close();
		writer2.close();
		writer3.close();
		System.out.println("___end____");
	}

}
