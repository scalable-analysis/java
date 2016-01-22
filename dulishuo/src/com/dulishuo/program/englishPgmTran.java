package com.dulishuo.program;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.dulishuo.util.FileUtil;

import java.util.*;
import java.util.Map.Entry;

public class englishPgmTran {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/英国专业/ukprogram入库.json", "utf-8");
		List<JSONObject> list1 = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/英国专业/ukprogram入库22.json", "utf-8");
		
		List<String> res = new ArrayList<String>();
		List<Integer> ee = new ArrayList<Integer>();
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(JSONObject xx : list1){
			if(xx.getString("ttitle").equals("-1"))
				ee.add(xx.getInt("id"));
		}
		for(JSONObject xx : list){
			if(ee.contains(xx.getInt("id")))
				map.put(xx.getInt("id"), xx.getString("subject"));
		}

		for(JSONObject xx : list1){
			
			String tsubject = xx.getString("ttitle");
			if(xx.getString("ttitle").equals("-1")){
				try {
					Thread.currentThread().sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				tsubject = trans(map.get(xx.getInt("id")));
				
				if(tsubject.equals("-1")){
					tsubject = trans(map.get(xx.getInt("id")));
					if(tsubject.equals("-1")){
						tsubject = trans(map.get(xx.getInt("id")));
						if(tsubject.equals("-1")){
							tsubject = trans(map.get(xx.getInt("id")));
							if(tsubject.equals("-1")){
								tsubject = trans(map.get(xx.getInt("id")));
								if(tsubject.equals("-1")){
									tsubject = trans(map.get(xx.getInt("id")));
								}
							}
						}
					}
				}			
				System.out.println("process___"+(count++)+"\t"+tsubject);
				
										
			}
			
			xx.put("ttitle", tsubject);
			res.add(xx.toString());	
			
			
		}
	
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/英国专业/ukprogram入库222.json");
	
		System.out.println("_____end_____");
	}
	
	
	public static String trans(String xx){
		String response = "";
		String url ="http://openapi.baidu.com/public/2.0/bmt/translate";
		HttpClient client = new HttpClient();
		
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Host", "http://openapi.baidu.com/");  
		method.addParameter("client_id", "6j46EjCjQKVkMMTAQGsFPWa4");
		method.addParameter("from", "auto");
		method.addParameter("to", "zh");
		method.addParameter("q", xx);
		
		try {
			client.executeMethod(method);
		} catch (HttpException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			response = method.getResponseBodyAsString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dst = "";
		try{
			JSONObject json = JSONObject.fromObject(response);
			JSONArray jsonA = (JSONArray) json.get("trans_result");
			JSONObject jsonTmp = (JSONObject) jsonA.get(0);
			dst = jsonTmp.getString("dst");
		}
		catch(Exception e){
			dst="-1";
		}
		return dst;
	}

}
