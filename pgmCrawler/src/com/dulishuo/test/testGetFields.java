package com.dulishuo.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class testGetFields {
	static int count = 0 ;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub	
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/success.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/result.txt"));
		
		String tmp = "";
		int i = 0;
		while((tmp = br.readLine()) != null){
			i++;
			System.out.println("process---"+(count++));
			JSONObject json = JSONObject.fromObject(tmp);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("entry", json.getString("Months of entry"));
			map.put("institute", json.getString("University name"));
			map.put("orginal_id", json.getString("id"));
			
			if(json.containsKey("Entry requirements")){
				map.put("extra_requirement", json.getString("Entry requirements"));
			}else{
				map.put("extra_requirement", "-1");
			}
			
			if(json.containsKey("Department")){
				map.put("faculty", json.getString("Department"));
			}else{
				map.put("faculty", "-1");
			}
			
			if(json.containsKey("Web")){
				map.put("website", json.getString("Web"));
			}else{
				map.put("website", "-1");
			}
			
			if(json.containsKey("Course Title")){
				map.put("subject", json.getString("Course Title"));
			}else{
				map.put("subject", "-1");
			}
			
			if(json.containsKey("International student info")){
				map.put("international", json.getString("International student info"));
			}else{
				map.put("international", "-1");
			}
			
			if(json.containsKey("Application")){
				map.put("applyaddress", json.getString("Application"));
			}else{
				map.put("applyaddress", "-1");
			}
			
			if(json.containsKey("Pathways")){
				map.put("Path", json.getString("Pathways"));
			}else{
				map.put("Path", "-1");
			}
			
			//contact			
			map.put("contact", contact(json));
			//number_student
			map.put("number_student", numStudent(json));
			//reaserch
			map.put("reaserch", reaserch(json));
			//fee
			map.put("fee", reaserch(json));
			//description
			map.put("description", reaserch(json));
			
			
			JSONArray qdm;
			if(json.containsKey("Qualification, duration, mode")){
				qdm = qdm(json.getString("Qualification, duration, mode"));
				map.put("qdm", qdm);
			}else{
				map.put("qdm", null);
			}
			
			JSONObject jsonT = JSONObject.fromObject(map);
			bw.write(jsonT.toString());
			bw.newLine();
			bw.flush();
		}
		
		bw.close();
		System.out.println("-------------end-------------");
	}
    public static JSONArray qdm(String str){
    	
    	String reg1 = "((\\d+([\\s]*[-][\\s]*\\d+)?)|(n/a)|(month[s]?))FT";
		String reg2 = "((\\d+([\\s]*[-][\\s]*\\d+)?)|(n/a)|(month[s]?))PT";
		String reg3 = "((\\d+([\\s]*[-][\\s]*\\d+)?)|(n/a)|(month[s]?))BM";
		String reg4 = "((\\d+([\\s]*[-][\\s]*\\d+)?)|(n/a)|(month[s]?))DL";
		String reg5 = "[a-zA-Z]+";
		
		Pattern ptn1 = Pattern.compile(reg1);
		Pattern ptn2 = Pattern.compile(reg2);
		Pattern ptn3 = Pattern.compile(reg3);
		Pattern ptn4 = Pattern.compile(reg4);
		Pattern ptn5 = Pattern.compile(reg5);
		
		if(str.contains("*")){
			JSONArray jsonA = new JSONArray();
			String[] qmds = str.split("\\*");
			for(String qmd : qmds){
				Matcher mth1 = ptn1.matcher(qmd);
				Matcher mth2 = ptn2.matcher(qmd);
				Matcher mth3 = ptn3.matcher(qmd);
				Matcher mth4 = ptn4.matcher(qmd);
				Matcher mth5 = ptn5.matcher(qmd);
				
				Map<String,String> map = new HashMap<String,String>();
				
				if(mth1.find()){
					map.put("FT", mth1.group().replace("FT", ""));
				}else{
					map.put("FT", "-1");
				}
				if(mth2.find()){
					map.put("PT", mth2.group().replace("PT", ""));
				}else{
					map.put("PT", "-1");
				}
				if(mth3.find()){
					map.put("BM", mth3.group().replace("BM", ""));
				}else{
					map.put("BM", "-1");
				}
				if(mth4.find()){
					map.put("DL", mth4.group().replace("DL", ""));
				}else{
					map.put("DL", "-1");
				}
				if(mth5.find()){
					map.put("qualification", mth5.group());
				}else{
					map.put("qualification", "-1");
				}
				JSONObject json = JSONObject.fromObject(map);
				jsonA.add(json);
			}
			return jsonA;
		}
		else{
			JSONArray jsonA = new JSONArray();
			String qmd = str;
			Matcher mth1 = ptn1.matcher(qmd);
			Matcher mth2 = ptn2.matcher(qmd);
			Matcher mth3 = ptn3.matcher(qmd);
			Matcher mth4 = ptn4.matcher(qmd);
			Matcher mth5 = ptn5.matcher(qmd);
			
			Map<String,String> map = new HashMap<String,String>();
			
			if(mth1.find()){
				map.put("FT", mth1.group().replace("FT", ""));
			}else{
				map.put("FT", "-1");
			}
			if(mth2.find()){
				map.put("PT", mth2.group().replace("PT", ""));
			}else{
				map.put("PT", "-1");
			}
			if(mth3.find()){
				map.put("BM", mth3.group().replace("BM", ""));
			}else{
				map.put("BM", "-1");
			}
			if(mth4.find()){
				map.put("DL", mth4.group().replace("DL", ""));
			}else{
				map.put("DL", "-1");
			}
			if(mth5.find()){
				map.put("qualification", mth5.group());
			}else{
				map.put("qualification", "-1");
			}
			JSONObject json = JSONObject.fromObject(map);
			jsonA.add(json);
			return jsonA;
		}	
    }
    
    public static JSONObject contact(JSONObject json){
    	
    	Map<String,String> map = new HashMap<String,String>();
    	boolean flag =false;
    	if(json.containsKey("Contact name")){
			map.put("name", json.getString("Contact name"));
			flag = true;
		}
    	if(json.containsKey("Email")){
			map.put("email", json.getString("Email"));
			flag = true;
		}
    	if(json.containsKey("Telephone")){
			map.put("telephone", json.getString("Telephone"));
			flag = true;
		}
    	if(json.containsKey("Fax")){
			map.put("fax", json.getString("Fax"));
			flag = true;
		}
    	
    	if(flag){
    		JSONObject aa = JSONObject.fromObject(map);
			return aa;
    		
    	}else{
    		return null;
    	}
    }
    
    public static JSONObject numStudent(JSONObject json){
    	Map<String,String> map = new HashMap<String,String>();
    	boolean flag =false;
    	if(json.containsKey("Number of new students")){
			map.put("new", json.getString("Number of new students"));
			flag = true;
		}
    	if(json.containsKey("Total number of students")){
			map.put("total", json.getString("Total number of students"));
			flag = true;
		}
    	
    	if(flag){
    		JSONObject aa = JSONObject.fromObject(map);
			return aa;
    		
    	}else{
    		return null;
    	}
    }
    
    public static JSONObject reaserch(JSONObject json){
    	Map<String,String> map = new HashMap<String,String>();
    	boolean flag =false;
    	if(json.containsKey("Research description")){
			map.put("description", json.getString("Research description"));
			flag = true;
		}
    	if(json.containsKey("Research opportunity title")){
			map.put("opportunity_title", json.getString("Research opportunity title"));
			flag = true;
		}
    	
    	if(flag){
    		JSONObject aa = JSONObject.fromObject(map);
			return aa;
    		
    	}else{
    		return null;
    	}
    }
    
    public static JSONObject description(JSONObject json){
    	Map<String,String> map = new HashMap<String,String>();
    	boolean flag =false;
    	if(json.containsKey("Course description")){
			map.put("course_description", json.getString("Course description"));
			flag = true;
		}
    	if(json.containsKey("Further qualification details")){
			map.put("further_qualification_details", json.getString("Further qualification details"));
			flag = true;
		}
    	
    	if(flag){
    		JSONObject aa = JSONObject.fromObject(map);
			return aa;
    		
    	}else{
    		return null;
    	}
    }
    
    public static JSONObject fee(JSONObject json){
    	Map<String,String> map = new HashMap<String,String>();
    	boolean flag =false;
    	if(json.containsKey("Funding")){
			map.put("funding", json.getString("Funding"));
			flag = true;
		}
    	if(json.containsKey("Fees for international students")){
			map.put("fees_international_students", json.getString("Fees for international students"));
			flag = true;
		}
    	
    	if(flag){
    		JSONObject aa = JSONObject.fromObject(map);
			return aa;
    		
    	}else{
    		return null;
    	}
    }
}
