package com.dulishuo.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Translate0718 {
	static int count = 1;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/result.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/dataCrawler/英国专业/program.txt"));
		
		
		String each_line = "";
		while((each_line = br.readLine()) !=null){
			System.out.println("translate________"+(count++));
			JSONObject json = JSONObject.fromObject(each_line);
			json.put("tsubject", process(json.getString("subject")));
			bw.write(json.toString());
			bw.newLine();
			bw.flush();
		}
		br.close();
		bw.close();
		System.out.println("______________end_______________");
	}

	private static String process(String str) {
		// TODO Auto-generated method stub
		String response = "";
		String url ="http://openapi.baidu.com/public/2.0/bmt/translate";
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.addParameter("client_id", "6j46EjCjQKVkMMTAQGsFPWa4");
		method.addParameter("from", "auto");
		method.addParameter("to", "zh");
		method.addParameter("q", str);
		
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
