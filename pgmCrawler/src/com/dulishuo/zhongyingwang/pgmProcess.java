package com.dulishuo.zhongyingwang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class pgmProcess {
	static int count = 1;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/中英网/institute.json")));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/中英网/engPgm.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/dataCrawler/中英网/program.json"));
		
		Set<String> set = new HashSet<String>();
		Map<String,String> map = new HashMap<String,String>();
		String each_line = "";
		while((each_line = br1.readLine()) !=null){
			JSONObject json = JSONObject.fromObject(each_line);
			map.put(json.getString("title"), json.getString("id"));
			set.add(json.getString("id"));			
		}
		br1.close();
		
		String each_lin = "";
		while((each_lin = br2.readLine()) !=null){
			System.out.println("process______"+(count++));
			JSONObject jso = JSONObject.fromObject(each_lin);
			if(map.containsKey(jso.getString("institute"))){
				jso.put("id", map.get(jso.getString("institute")));
				bw.write(jso.toString());
				bw.newLine();
				bw.flush();
			}else{
				jso.put("id", "-1");
				bw.write(jso.toString());
				bw.newLine();
				bw.flush();
			}
		}
		br2.close();
	/*	
		for(String xx : set){
			System.out.println("process--------"+(count++));
			//if(xx)
			JSONObject json = process(xx);
			
			bw.write(json.toString());
			
		}*/
		bw.close();
		System.out.println("___________end___________");
	}
	private static JSONObject process(String xx) {
		// TODO Auto-generated method stub
		JSONObject result = null;
		JSONObject json = JSONObject.fromObject(xx);
		Map<String,String> map = new HashMap<String,String>();
		
		String name = json.getString("name");
		String ttitle = name.substring(0,name.indexOf("[收藏该学校]"));
		String title = name.substring(name.indexOf("]")+1,name.indexOf("一键免费申请")).trim();
		System.out.println(title+"--"+ttitle);
		map.put("title",title);
		map.put("ttitle",ttitle);
		if(!json.getString("mode").trim().equals("")){
			//授课对象类型
			map.put("teach_type", json.getString("mode"));
		}
			
			
		
		

		map.put("address", json.getString("city")+json.getString("district"));
		//map.put("taddress", address(json.getString("city")+json.getString("district")));
		
		if(!json.getString("tele").trim().equals("")){
			//联系方式
			map.put("tele", json.getString("tele").replace("电话:", "").trim());
			System.out.println(json.getString("tele").replace("电话:", "").trim());
			
		}
		if(!json.getString("web").trim().equals("")){
			map.put("web", json.getString("web"));
		}
		if(!json.getString("fee").trim().equals("")){
			map.put("costs",json.getString("fee").replace("生活费:", "").replace(",", "").replace(" ", "").trim());
			System.out.println("fee"+json.getString("fee").replace("生活费:", "").replace(",", "").trim());
		}
		if(!json.getString("discription").equals("")){
			map.put("school",json.getString("discription"));
		}

		map.put("istranslated", "1");

		result = JSONObject.fromObject(map);
		return result;
	}
	
	private static String address(String str) {
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
