package com.dulishuo.offer.yunying;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class ToHtml {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("start . . . \n");
		long start = System.currentTimeMillis();
		
		toHtml();
		
		long end = System.currentTimeMillis();
		System.out.println("end use time : " + (end - start) + " ms .");
	}

	private static void toHtml() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/wenda11172.json", "utf-8");
		
		List<String> resl = new ArrayList<String>();
		int flag = 0;
		for(JSONObject json : list){
			try{
				flag ++;
				System.out.println("process  "+flag);
				
				String author_say = json.getString("author_say");
				String title = json.getString("title");
				String date = json.getString("author_date");
				String url = json.getString("url");
				StringBuffer res = new StringBuffer();
				res.append("<div style=\"width:1100px; padding-left:150px;\"><h1 style=\"text-align:center\">"+title+"<h1>");
				res.append("<p style=\"font-size:15px;text-align:center;\">"+date+"<p>");
				res.append("<div style=\"font-size:18px; font-family:宋体;\" >"+author_say+"</div>");
				res.append("<span style=\"font-size:15px;\"> 原文链接:</span><a style=\"font-size:15px; target=\"_Blank\"\" href=\""+url+"\">"+url+"</a>");
				res.append("</div>");
				
				
				resl.add(res.toString());
				
				
			}catch(Exception e){
				
			}
		}
		int ii = 1;
		StringBuffer tmp = new StringBuffer();
		for(int i = 0 ; i < resl.size(); i++){
			if((i%500 == 0 || i ==resl.size()-1)&& i !=0){
				List<String> tt = new ArrayList<String>();
				String header = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>论坛贴</title></head><body>";
				tt.add(header);
				tt.add(tmp.toString());
				tt.add("</body></html>");
				
				FileUtil.ListToFile(tt, "C:/Users/强胜/Desktop/运营申请论坛贴11/"+ii+++".html");
				tmp.delete(0, tmp.length());
			}
			tmp.append(resl.get(i));
			tmp.append("<br>");
			tmp.append("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp------------------------------------------------------------------          ");
			tmp.append("<br>");
			tmp.append("<br>");
			tmp.append("<br>");
		}
		
	}

}
