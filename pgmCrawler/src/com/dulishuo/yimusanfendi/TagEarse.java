package com.dulishuo.yimusanfendi;

import com.dulishuo.util.FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang.StringEscapeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TagEarse {

	static int flag = 1;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<String> post = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/postAll.json");
		BufferedWriter bw = FileUtil.FileWriter("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/postAllT2.json");
		
		for(String xx : post){
		
			
			//String say = json.getString("author_say");
			JSONObject json = JSONObject.fromObject(xx);
			System.out.println("process___"+(flag++)+"___"+json.getString("title"));
			//System.out.println(json.getString("author_say"));
			//System.out.println(say);
			
			String[] atm = new String[0];
			json.put("atm", atm);
			
			String zz = tt(json.getString("title"));
			if(!zz.equals("-1")){
				JSONObject rst  = JSONObject.fromObject(zz);
				 if(rst.containsKey("property-dict")){
					 if(rst.getString("property-dict")!=null && !rst.getString("property-dict").equals("{}")){
						 //保留字段
						 json.put("property-dict", rst.getString("property-dict"));
			        	 JSONObject dict = rst.getJSONObject("property-dict");
			        	 if(dict.containsKey("school")){
			        		 //匹配到的school的id，如果有多个，则是数组[123,345]
			        		 String school  = dict.get("school").toString().replace("[", "").replace("]", "");
			 				if(school.indexOf(",") == -1){
			 					if(school.length()>1)
			 						json.put("school", school);
			 					else
			 						json.put("school", -1);
			 						
			 				}else
			 					json.put("school", school.split(",")[0]);			        		 
			        	 }else
			        		 json.put("school", -1);
			        	 
			        	 if(dict.containsKey("program")){
			        		 //匹配到的school的id，如果有多个，则是数组[123,345]
			        		 String program  = dict.get("program").toString().replace("[", "").replace("]", "");
			 				if(program.indexOf(",") == -1){
			 					if(program.length()>1)
			 						json.put("program", program);
			 					else
			 						json.put("program", -1);
			 						
			 				}else
			 					json.put("program", program.split(",")[0]);			        		 
			        	 }else
			        		 json.put("program", -1);
					 }
					 else{
						 json.put("property-dict", null);
						 json.put("school", -1);
						 json.put("program", -1);
					 }
					
		         }else{
		        	 json.put("property-dict", null);
		        	 json.put("school", -1);
		        	 json.put("program", -1);
		         }
			}else{
				json.put("property-dict", null);
				json.put("school", -1);
				json.put("program", -1);
	         }
			//时间戳转化
			long date = -1;
			date = getDate(json.getString("author_date"),1);
			json.put("author_date", date);
			JSONArray ja = JSONArray.fromObject(json.get("comment"));
			JSONArray jaa = new JSONArray();
			for(int i = 0 ; i < ja.size() ; i++){
				JSONObject jo = ja.getJSONObject(i);
				String dd = jo.getString("date");
				if(dd.indexOf("天") == -1 || dd.indexOf("小时") == -1)
					jo.put("date", getDate(dd,1));
				else
					jo.put("date", -1);
				jaa.add(jo);
			}
			json.put("comment", jaa);
			
			String yy  = json.toString();
			String reg_img1 = "<img.*?\\\\/>";
			String reg_img2 = "<img.*?>.*?<\\\\/img>";
			String reg_footer1 = "<div[\\s]class=\\\\\"editor\\\\\">[\\s\\S]*?<\\\\/div>";
			String reg_footer2 = "<p[\\s]class=\\\\\"article_bj\\\\\">[\\s\\S]*?<\\\\/p>";
			String reg_a = "<a.*?>";
			String reg_font1 = "<font[\\s]class=\\\\\"jammer\\\\\">.*?<\\\\/font&gt;";
			String reg_font2 = "<font[\\s]class=\\\\\"jammer\\\\\">.*?<\\\\/font>";
			String reg_font3 = "<font[\\s]class=\\\\\"jammer\\\\\"><\\\\/font&gt;>";
			String reg_font4 = "<font[\\s]class=\\\\\"jammer\\\\\"><\\\\/font>";
			
			
			String reg_span1 = "<span[\\s]style=\\\\\"display\\:[\\s]*none\\\\\">\\\\/span&gt;";
			String reg_span2 = "<span[\\s]style=\\\\\"display\\:[\\s]*none\\\\\"><\\\\/span>";
			String reg_span3 = "<span[\\s]style=\\\\\"display\\:[\\s]*none\\\\\">.*?<\\\\/span&gt;";
			String reg_span4 = "<span[\\s]style=\\\\\"display\\:[\\s]*none\\\\\">.*?<\\\\/span>";
			//String reg_span5 = "<span[\\s]class=\\\\\"atips_close\\\\\"[\\s]onclick=\\\\\"this.parentNode.style.display='none'\\\\\">x<\\\\/span>[\\s\\S]*<\\\\/div>";
			

			String reg_quote = "<div[\\s]class=\\\\\"quote\\\\\">[\\s\\S]*?<blockquote>.*?<\\\\/blockquote>[\\s\\S]*?<\\\\/div>";
			
			yy = yy.replaceAll(reg_img1, "").replaceAll(reg_img2, "").replaceAll(reg_footer1, "").replaceAll(reg_footer2, "").replaceAll(reg_a, "");
			
			yy = yy.replaceAll(reg_font1, "").replaceAll(reg_font2, "").replaceAll(reg_font3, "").replaceAll(reg_font4, "");
			
			yy = yy.replaceAll(reg_span1,"").replaceAll(reg_span2,"").replaceAll(reg_span3,"").replaceAll(reg_span4,"");
			
			yy = yy.replaceAll(reg_quote, "");
			//.replaceAll(reg_guestViewThumb, "").replaceAll(reg_div1, "")
			yy = yy.replace("\\/", "/").replace("\\n", "").replace("</a>", "");
			//System.out.println(json.getString("author_say"));
			
			bw.write(yy);
			
			bw.newLine();
			bw.flush();
		}
		
		bw.close();
		System.out.println("____end____");
	}

	public static long getDate(String str, int type){
		long result = 0 ;
		SimpleDateFormat sdf = null;
		if(type == 1)
			sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(type ==2)
			sdf=new SimpleDateFormat("yyyy年MM月dd日");
		try{
			Date d=sdf.parse(str);
			result = d.getTime()/1000;
		}catch(Exception e){			
			return result;
		}
			
		return result;
	}
	
	
	public static String tt(String req){
		String result = "-1";
		//String regex = "[^\u4e00-\u9fa5_a-zA-Z]+";
		
		//req = req.replaceAll(regex, "");
		String str = "";
		try {
			str = "http://123.57.250.189:8818/question_process?q="+URLEncoder.encode(req,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			URL url = new URL(str);
	         HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
	         urlcon.connect();         //获取连接
	         InputStream is = urlcon.getInputStream();
	         BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
	         StringBuffer bs = new StringBuffer();
	         String each = null;
	         while((each=buffer.readLine())!=null){
	             bs.append(each);
	         } 
	         result = bs.toString();
		}catch(Exception e){
			e.printStackTrace();
			return result;
		}
		       
         return result;
	}
}
