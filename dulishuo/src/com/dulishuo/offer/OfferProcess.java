package com.dulishuo.offer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dulishuo.util.FileUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OfferProcess {
	static int count = 1;
	static int Scount = 1;
	public static void main(String[] args) throws IOException{
		
		long start = System.currentTimeMillis();
		List<String> list = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/bash/mailattachment/offer1.json")));
		
		//读取
		//StringBuilder sbR = new StringBuilder();
		//sbR.append("[");
		StringBuilder sbT = new StringBuilder();
		String tmp = "";
		while((tmp=br.readLine()) != null){
			sbT.append(tmp);
		}
		
		//处理
		JSONArray json = JSONArray.fromObject(sbT.toString());
		for(int i = 0 ; i < json.size() ; i++){
			if(i%1000 == 0)
				System.out.println("执行到第----"+i+"----条");
			JSONObject jo = (JSONObject) json.get(i);
			jo.put("result", Integer.parseInt(result(jo.getString("result"))));
			jo.put("ielts", ielts(jo.getString("IELTS")));
			
			jo.put("toefl", toefl(jo.getString("TOEFL")));
			jo.put("gre", gre(jo.getString("GRE")));
			jo.put("gpa", Double.parseDouble(gpa(jo.getString("GPA"))));	
			jo.remove("IELTS");
			jo.remove("TOEFL");
			jo.remove("GRE");
			jo.remove("GPA");
			jo.put("from", jo.getString("title"));
			
			list.add(jo.toString());
		}
		FileUtil.ListToFile(list, "C:/Users/强胜/Desktop/bash/result.json");
  
	
        long end = System.currentTimeMillis();
        System.out.println("一共成功提取"+Scount+"条");
        System.out.println("end----"+(end-start)+"豪秒");
		
	}
	
	//1代表ms,2代表ma，3代表phd,4代表llm，5代表其他，默认值为0
	public static String degree(String src){
		String result = "0";
		if(src.toLowerCase().equals("ms")){
			result = "1";
		}
		if(src.toLowerCase().equals("ma")){
			result = "2";
		}
		if(src.toLowerCase().equals("phd")){
			result = "3";
		}
		if(src.toLowerCase().equals("llm")){
			result = "4";
		}
		if(src.equals("其它")){
			result = "5";
		}
		return result;
	}
	
		//offer结果
	public static String result(String src){
		String result = "0";
		if(src.toLowerCase().equals("offer")){
			result = "1";
		}
		if(src.toLowerCase().equals("ad无奖励")){
			result = "2";
		}
		if(src.toLowerCase().equals("被拒")){
			result = "3";
		}
		if(src.equals("ad小奖")){
			result = "4";
		}
		return result;
	}
	
	public static JSONObject gre(String src){
		JSONObject result = new JSONObject();
		String rst1 = "-1";
		String rst2 = "";
		String rst3 = "";
		String rst4 = "";
		
		int flag = 0;
		
		String str = src;
		
		String regex1 = "Overall\\:.*?[\\,]"; 
		String regex2 = "V\\:.*?[/]"; 
		String regex3 = "Q\\:.*?[/]"; 
		String regex4 = "AW\\:[\\s]+?[\\d]+([\\.][\\d]{1,2})?"; 
		String regex5 = "[v]?[\\d]{1,3}[v]?[\\+,\\,,\\、][q]?[\\d]{1,3}[q]?[\\+,\\,,\\、][aw]?[\\d]{1}([\\.][\\d]{1,2})?[aw]?";
		String regex6 = "[\\d]+([\\.][\\d]{1,2})?";
		
		Pattern ptn1 = Pattern.compile(regex1);
		Pattern ptn2 = Pattern.compile(regex2);
		Pattern ptn3 = Pattern.compile(regex3);
		Pattern ptn4 = Pattern.compile(regex4);
		Pattern ptn5 = Pattern.compile(regex5);
		Pattern ptn6 = Pattern.compile(regex6);
		
		Matcher mth1 = ptn1.matcher(str);
		Matcher mth2 = ptn2.matcher(str);
		Matcher mth3 = ptn3.matcher(str);
		Matcher mth4 = ptn4.matcher(str);
		Matcher mth5 = ptn5.matcher(str);
		
		if(mth1.find()){
			rst1 = mth1.group();
			Matcher mth6 = ptn6.matcher(rst1);
			if(mth6.find()){
				rst1 = mth6.group();
			}
			else{
				rst1 = "-1";
			}
		}
		if(mth2.find()){
			
			rst2 = mth2.group();
			
			Matcher mth6 = ptn6.matcher(rst2);
			if(mth6.find()){
				rst2 = mth6.group();
				flag++;
			}else{
				rst2 = "";
			}
		}
		if(mth3.find()){
			rst3 = mth3.group();
			Matcher mth6 = ptn6.matcher(rst3);
			if(mth6.find()){
				rst3 = mth6.group();
				flag++;
			}
			else{
				rst3 = "";
			}
		}
		if(mth4.find()){
			rst4 = mth4.group();
			Matcher mth6 = ptn6.matcher(rst4);
			if(mth6.find()){
				rst4 = mth6.group();
				
			}
			else{
				rst4 = "";
			}
		}
		
		if(flag==2 && rst1 =="-1"){
			rst1 = String.valueOf((Double.parseDouble(rst2)+Double.parseDouble(rst3)));
		}else if(mth5.find()){
			String tmp = mth5.group();
			if(tmp.contains("+")){
				rst2 = tmp.split("\\+")[0].replaceAll("[\\D]", "");
				rst3 = tmp.split("\\+")[1].replaceAll("[\\D]", "");
				rst4 = tmp.split("\\+")[2].replaceAll("[^\\d\\.]", "");
			}else if(tmp.contains(",")){
				rst2 = tmp.split("\\,")[0].replaceAll("[\\D]", "");
				rst3 = tmp.split("\\,")[1].replaceAll("[\\D]", "");
				rst4 = tmp.split("\\,")[2].replaceAll("[^\\d\\.]", "");
			}
			else if(tmp.contains("、")){
				rst2 = tmp.split("\\、")[0].replaceAll("[\\D]", "");
				rst3 = tmp.split("\\、")[1].replaceAll("[\\D]", "");
				rst4 = tmp.split("\\、")[2].replaceAll("[^\\d\\.]", "");
			}
			rst1 = String.valueOf((Double.parseDouble(rst2)+Double.parseDouble(rst3)));
		}
		
	
		
		if(rst1 != "-1" && Double.valueOf(rst1) <= 340){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("total",rst1.equals("")?rst1:Double.parseDouble(rst1));
			map.put("v",rst2.equals("")?rst2:Double.parseDouble(rst2));
			map.put("q",rst3.equals("")?rst3:Double.parseDouble(rst3));
			map.put("aw",rst4.equals("")?rst4:Double.parseDouble(rst4));
			map.put("other",null);
			result =  JSONObject.fromObject(map);
			
		}else{
			Map<String,String> map = new HashMap<String,String>();
			if(src.equals("") || src.toLowerCase() ==null || src.toLowerCase().equals("null"))
				return null;
			else
				map.put("other",src);
			result = JSONObject.fromObject(map);
		}
		return result;
	}
	
	public static String gpa(String str){
		String rst = "-1" ;
		
		//匹配4分制
		String regex1 = "(([1-3](\\.[0-9]{1,2})?)|(4\\.0{1,2}))/(4(\\.0{1,2})?)";
		String regex4 = "[2-3](\\.[0-9]{1,2}?)";
		//匹配5分制
		String regex2 = "(([1-4](\\.[0-9]{1,2})?)|(5\\.0{1,2}))/(5(\\.0{1,2})?)";
		//匹配100分制
		String regex3 = "(([7-9][0-9](\\.[0-9]{1,2})?)|(100\\.0{1,2}))/(100(\\.0{1,2})?)";
		String regex5 = "[7-9][0-9](\\.[0-9]{1,2}?)";	
		
		Pattern ptn1 = Pattern.compile(regex1);
		Pattern ptn2 = Pattern.compile(regex2);
		Pattern ptn3 = Pattern.compile(regex3);
		Pattern ptn4 = Pattern.compile(regex4);
		Pattern ptn5 = Pattern.compile(regex5);
		
		Matcher matcher1 = ptn1.matcher(str);
		
		if(matcher1.find()){
			rst = matcher1.group().substring(0,matcher1.group().indexOf("/"));
			if(rst.length()>4)
			rst=rst.substring(0,3);
		}else{
			Matcher matcher2 = ptn2.matcher(str);
			if(matcher2.find()){
				rst = matcher2.group().substring(0,matcher2.group().indexOf("/"));
				rst = String.valueOf(Double.parseDouble(rst)*0.8);
				if(rst.length()>4)
				rst=rst.substring(0,3);
			}else{
				Matcher matcher3 = ptn3.matcher(str);
				if(matcher3.find()){
					rst = matcher3.group().substring(0,matcher3.group().indexOf("/"));
					rst = String.valueOf(Math.min((Double.parseDouble(rst)-95)/10+4.0,4.0));
					if(rst.length()>4)
					rst=rst.substring(0,3);
				}
				else{
					Matcher matcher4 = ptn4.matcher(str);
					if(matcher4.find()){
						rst = matcher4.group();
						if(rst.length()>4)
						rst=rst.substring(0,3);
					}
					else{
						Matcher matcher5 = ptn5.matcher(str);
						if(matcher5.find()){
							rst = matcher5.group();
							rst = String.valueOf(Math.min((Double.parseDouble(rst)-95)/10+4.0,4.0));
							if(rst.length()>4)
							rst=rst.substring(0,3);
						}
				}
				}
			}
		}
		
		return rst;
	}
	
	public static JSONObject ielts(String src){
		JSONObject result ;
		
		String rst1 = "-1";
		String rst2 = "";
		String rst3 = "";
		String rst4 = "";
		String rst5 = "";
		
		int flag = 0;
		
		String str = src;
		
		String regex1 = "Overall\\:.*?[\\d]+[\\,]"; 
		String regex2 = "R\\:.*?[/]"; 
		String regex3 = "L\\:.*?[/]"; 
		String regex4 = "S\\:.*?[/]"; 
		String regex5 = "W\\:[\\s]*(([1-8](\\.[0-9]{1,2})?)|([9](\\.[0]{1,2})))"; 
		String regex6 = "([1-8](\\.[0-9]{1,2})?)|([9](\\.[0]{1,2}))";
		String regex7 = "((([1-8](\\.[0-9]{1,2})?)|([9](\\.[0]{1,2}))))[\\+,\\,,\\、](([1-8](\\.[0-9]{1,2})?)|([9](\\.[0]{1,2})))[\\+,\\,,\\、](([1-8](\\.[0-9]{1,2})?)|([9](\\.[0]{1,2})))[\\+,\\,,\\、](([1-8](\\.[0-9]{1,2})?)|([9](\\.[0]{1,2})))";
		
		Pattern ptn1 = Pattern.compile(regex1);
		Pattern ptn2 = Pattern.compile(regex2);
		Pattern ptn3 = Pattern.compile(regex3);
		Pattern ptn4 = Pattern.compile(regex4);
		Pattern ptn5 = Pattern.compile(regex5);
		Pattern ptn6 = Pattern.compile(regex6);
		Pattern ptn7 = Pattern.compile(regex7);
		
		Matcher mth1 = ptn1.matcher(str);
		Matcher mth2 = ptn2.matcher(str);
		Matcher mth3 = ptn3.matcher(str);
		Matcher mth4 = ptn4.matcher(str);
		Matcher mth5 = ptn5.matcher(str);
		Matcher mth7 = ptn7.matcher(str);
		
		if(mth1.find()){
			rst1 = mth1.group();
			Matcher mth6 = ptn6.matcher(rst1);
			if(mth6.find()){
				rst1 = mth6.group();
			}
			else{
				rst1 = "-1";
			}
		}
		if(mth2.find()){
			
			rst2 = mth2.group();
			
			Matcher mth6 = ptn6.matcher(rst2);
			if(mth6.find()){
				rst2 = mth6.group();
				flag++;
			}else{
				rst2 = "";
			}
		}
		if(mth3.find()){
			rst3 = mth3.group();
			Matcher mth6 = ptn6.matcher(rst3);
			if(mth6.find()){
				rst3 = mth6.group();
				flag++;
			}
			else{
				rst3 = "";
			}
		}
		if(mth4.find()){
			rst4 = mth4.group();
			Matcher mth6 = ptn6.matcher(rst4);
			if(mth6.find()){
				rst4 = mth6.group();
				flag++;
			}
			else{
				rst4 = "";
			}
		}
		if(mth5.find()){
			rst5 = mth5.group();
			Matcher mth6 = ptn6.matcher(rst5);
			if(mth6.find()){
				rst5 = mth6.group();
				flag++;
			}
			else{
				rst5 = "";
			}
		}
		
		if(flag==4 && rst1 =="-1"){
			
			double tmp =(Double.parseDouble(rst2)+Double.parseDouble(rst3)+Double.parseDouble(rst4)+Double.parseDouble(rst5))/4;
			
			if(tmp-Double.parseDouble(String.valueOf(tmp).substring(0, 1))  > 0.5){
				rst1 = String.valueOf(Integer.parseInt(String.valueOf(tmp).substring(0, 1))+1)+".0";
			}else{
				rst1 = String.valueOf(Integer.parseInt(String.valueOf(tmp).substring(0, 1)))+".5";
			}
			
		}else if(mth7.find()){
			String tmp = mth7.group();
			if(tmp.contains("+")){
				rst2 = tmp.split("\\+")[0].replaceAll("[^\\d\\.]", "");
				rst3 = tmp.split("\\+")[1].replaceAll("[^\\d\\.]", "");
				rst4 = tmp.split("\\+")[2].replaceAll("[^\\d\\.]", "");
				rst5 = tmp.split("\\+")[3].replaceAll("[^\\d\\.]", "");
			}else if(tmp.contains(",")){
				rst2 = tmp.split("\\,")[0].replaceAll("[^\\d\\.]", "");
				rst3 = tmp.split("\\,")[1].replaceAll("[^\\d\\.]", "");
				rst4 = tmp.split("\\,")[2].replaceAll("[^\\d\\.]", "");
				rst5 = tmp.split("\\,")[3].replaceAll("[^\\d\\.]", "");
			}
			else if(tmp.contains("、")){
				rst2 = tmp.split("\\、")[0].replaceAll("[^\\d\\.]", "");
				rst3 = tmp.split("\\、")[1].replaceAll("[^\\d\\.]", "");
				rst4 = tmp.split("\\、")[2].replaceAll("[^\\d\\.]", "");
				rst5 = tmp.split("\\、")[3].replaceAll("[^\\d\\.]", "");
			}
			double tmpp =(Double.parseDouble(rst2)+Double.parseDouble(rst3)+Double.parseDouble(rst4)+Double.parseDouble(rst5))/4;
			if(tmpp-Double.parseDouble(String.valueOf(tmpp).substring(0, 1))  > 0.5){
				rst1 = String.valueOf(Integer.parseInt(String.valueOf(tmpp).substring(0, 1))+1)+".0";
			}else{
				rst1 = String.valueOf(Integer.parseInt(String.valueOf(tmpp).substring(0, 1)))+".5";
			}
		}

		
		if(rst1 != "-1"){
			Map<String,Object> map = new HashMap<String,Object>();
			
			map.put("total",rst1.equals("")?rst1:Double.parseDouble(rst1));
			map.put("reading",rst2.equals("")?rst2:Double.parseDouble(rst2));
			map.put("listening",rst3.equals("")?rst3:Double.parseDouble(rst3));
			map.put("speaking",rst4.equals("")?rst4:Double.parseDouble(rst4));
			map.put("writing",rst5.equals("")?rst5:Double.parseDouble(rst5));
			map.put("other",null);
			result = JSONObject.fromObject(map);
		}else{
			Map<String,String> map = new HashMap<String,String>();
			
			if(src.equals("") || src.toLowerCase() ==null || src.toLowerCase().equals("null"))
				return null;
			else
				map.put("other",src);
			result = JSONObject.fromObject(map);
		}
		return result;
	}
	
	public static JSONObject toefl(String src){
		JSONObject result;
		
		String rst1 = "-1";
		String rst2 = "";
		String rst3 = "";
		String rst4 = "";
		String rst5 = "";
		
		int flag = 0;
		
		String str = src;
		
		String regex1 = "Overall\\:.*?[\\,]"; 
		String regex2 = "R\\:.*?[/]"; 
		String regex3 = "L\\:.*?[/]"; 
		String regex4 = "S\\:.*?[/]"; 
		String regex5 = "W\\:[\\s]*(([1][0-2][0-9])|([1-9][0-9]))"; 
		String regex6 = "([1][0-2][0-9])|([1-9][0-9])";
		String regex7 = "(([1][0-2][0-9])|([1-9][0-9]))[\\+,\\,,\\、](([1][0-2][0-9])|([1-9][0-9]))[\\+,\\,,\\、](([1][0-2][0-9])|([1-9][0-9]))[\\+,\\,,\\、](([1][0-2][0-9])|([1-9][0-9]))";
		
		Pattern ptn1 = Pattern.compile(regex1);
		Pattern ptn2 = Pattern.compile(regex2);
		Pattern ptn3 = Pattern.compile(regex3);
		Pattern ptn4 = Pattern.compile(regex4);
		Pattern ptn5 = Pattern.compile(regex5);
		Pattern ptn6 = Pattern.compile(regex6);
		Pattern ptn7 = Pattern.compile(regex7);
		
		
		Matcher mth1 = ptn1.matcher(str);
		Matcher mth2 = ptn2.matcher(str);
		Matcher mth3 = ptn3.matcher(str);
		Matcher mth4 = ptn4.matcher(str);
		Matcher mth5 = ptn5.matcher(str);
		Matcher mth7 = ptn7.matcher(str);
		
		if(mth1.find()){
			rst1 = mth1.group();
			Matcher mth6 = ptn6.matcher(rst1);
			if(mth6.find()){
				rst1 = mth6.group();
			}else{
				rst1 = "-1";
			}
		}
		if(mth2.find()){
			
			rst2 = mth2.group();
			
			Matcher mth6 = ptn6.matcher(rst2);
			if(mth6.find()){
				rst2 = mth6.group();
				flag++;
			}else{
				rst2 = "";
			}
		}
		if(mth3.find()){
			rst3 = mth3.group();
			Matcher mth6 = ptn6.matcher(rst3);
			if(mth6.find()){
				rst3 = mth6.group();
				flag++;
			}
			else{
				rst3 = "";
			}
		}
		if(mth4.find()){
			rst4 = mth4.group();
			Matcher mth6 = ptn6.matcher(rst4);
			if(mth6.find()){
				rst4 = mth6.group();
				flag++;
			}
			else{
				rst4 = "";
			}
		}
		if(mth5.find()){
			rst5 = mth5.group();
			Matcher mth6 = ptn6.matcher(rst5);
			if(mth6.find()){
				rst5 = mth6.group();
				flag++;
			}
			else{
				rst5 = "";
			}
		}
		
		if(flag==4 && rst1 =="-1"){
			rst1=String.valueOf(Integer.parseInt(rst2)+Integer.parseInt(rst3)+Integer.parseInt(rst4)+Integer.parseInt(rst5));
		}else if(mth7.find()){
			String tmp = mth7.group();
			
			if(tmp.contains("+")){
				rst2 = tmp.split("\\+")[0].replaceAll("[^\\d\\.]", "");
				rst3 = tmp.split("\\+")[1].replaceAll("[^\\d\\.]", "");
				rst4 = tmp.split("\\+")[2].replaceAll("[^\\d\\.]", "");
				rst5 = tmp.split("\\+")[3].replaceAll("[^\\d\\.]", "");
			}else if(tmp.contains(",")){
				rst2 = tmp.split("\\,")[0].replaceAll("[^\\d\\.]", "");
				rst3 = tmp.split("\\,")[1].replaceAll("[^\\d\\.]", "");
				rst4 = tmp.split("\\,")[2].replaceAll("[^\\d\\.]", "");
				rst5 = tmp.split("\\,")[3].replaceAll("[^\\d\\.]", "");
			}
			else if(tmp.contains("、")){
				rst2 = tmp.split("\\、")[0].replaceAll("[^\\d\\.]", "");
				rst3 = tmp.split("\\、")[1].replaceAll("[^\\d\\.]", "");
				rst4 = tmp.split("\\、")[2].replaceAll("[^\\d\\.]", "");
				rst5 = tmp.split("\\、")[3].replaceAll("[^\\d\\.]", "");
			}
		
			rst1=String.valueOf(Integer.parseInt(rst2)+Integer.parseInt(rst3)+Integer.parseInt(rst4)+Integer.parseInt(rst5));
		}
		

		
		if(rst1 != "-1"){
			Map<String,Object> map = new HashMap<String,Object>();
			
			map.put("total",rst1.equals("")?rst1:Double.parseDouble(rst1));
			map.put("reading",rst2.equals("")?rst2:Double.parseDouble(rst2));
			map.put("listening",rst3.equals("")?rst3:Double.parseDouble(rst3));
			map.put("speaking",rst4.equals("")?rst4:Double.parseDouble(rst4));
			map.put("writing",rst5.equals("")?rst5:Double.parseDouble(rst5));
			map.put("other",null);
			JSONObject jsonT = JSONObject.fromObject(map);
			result = JSONObject.fromObject(map);
		}else{
			Map<String,String> map = new HashMap<String,String>();
			if(src.equals("") || src.toLowerCase() ==null || src.toLowerCase().equals("null"))
				return null;
			else
				map.put("other",src);
			result = JSONObject.fromObject(map);
		}

		
		return result;
	}
}
