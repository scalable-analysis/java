package com.dulishuo.newFaculty;

import java.util.*;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
public class toJson {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list  =  FileUtil.FileToList("C:/Users/强胜/Desktop/数据补全/学院列表/美国/学校.txt");
		List<String> res = new ArrayList<String>();
		System.out.println("_____start___________");
		int flag = -1;
		for(String each : list){
			if(!isSch(each).equals("")){
				flag = Integer.parseInt(isSch(each));
			}else{
				JSONObject json = new JSONObject();
				json.put("id", count++);
				json.put("institute_id", flag);
				if(each.contains("-")){
					String[] fac = each.split("-");
					if(fac.length == 1){
						json.put("name_chinese", "");
						json.put("istranslated", 0);
						json.put("origin_id", -1);
						//1代表只有undergraduate 2代表graduate 3代表all
						if(each.contains("（1）")){
							json.put("name", each.replace("（1）", "").trim());
							json.put("type", 1);
						}
						if(each.contains("（2）")){
							json.put("name", each.replace("（2）", "").trim());
							json.put("type", 2);
						}
						if(each.contains("（3）")){
							json.put("name", each.replace("（3）", "").trim());
							json.put("type", 3);
						}else{
							json.put("name", each.trim());
							json.put("type", 3);
						}
							
					}
					if(fac.length == 2){
						String num = fac[1];
						if(isNum(num)){
							json.put("name_chinese", "");
							json.put("istranslated", 0);
							json.put("origin_id", Integer.parseInt(num.trim()));
							//1代表只有undergraduate 2代表graduate 3代表all
							if(each.contains("（1）")){
								json.put("name", fac[0].replace("（1）", "").trim());
								json.put("type", 1);
							}
							if(each.contains("（2）")){
								json.put("name", fac[0].replace("（2）", "").trim());
								json.put("type", 2);
							}
							if(each.contains("（3）")){
								json.put("name", fac[0].replace("（3）", "").trim());
								json.put("type", 3);
							}else{
								json.put("name", fac[0].trim());
								json.put("type", 3);
							}
						}
						else if(isChi(num)){
							json.put("name", fac[0]);
							json.put("istranslated", 0);
							json.put("origin_id", -1);
							//1代表只有undergraduate 2代表graduate 3代表all
							if(each.contains("（1）")){
								json.put("name_chinese", num.replace("（1）", "").trim());
								json.put("type", 1);
							}
							if(each.contains("（2）")){
								json.put("name_chinese", num.replace("（2）", "").trim());
								json.put("type", 2);
							}
							if(each.contains("（3）")){
								json.put("name_chinese", num.replace("（3）", "").trim());
								json.put("type", 3);
							}else{
								json.put("name_chinese", num.trim());
								json.put("type", 3);
							}
						}else{
							json.put("name_chinese", "");
							json.put("istranslated", 0);
							json.put("origin_id", -1);
							//1代表只有undergraduate 2代表graduate 3代表all
							if(each.contains("（1）")){
								json.put("name", each.replace("（1）", "").trim());
								json.put("type", 1);
							}
							if(each.contains("（2）")){
								json.put("name", each.replace("（2）", "").trim());
								json.put("type", 2);
							}
							if(each.contains("（3）")){
								json.put("name", each.replace("（3）", "").trim());
								json.put("type", 3);
							}else{
								json.put("name", each.trim());
								json.put("type", 3);
							}
						}
					}
					if(fac.length == 3){
						String num = fac[1];
						if(isNum(num)){
							json.put("istranslated", 0);
							json.put("origin_id", num);
							//1代表只有undergraduate 2代表graduate 3代表all
							if(each.contains("（1）")){
								json.put("name_chinese", fac[2].replace("（1）", "").trim());
								json.put("type", 1);
							}
							if(each.contains("（2）")){
								json.put("name_chinese", fac[2].replace("（2）", "").trim());
								json.put("type", 2);
							}
							if(each.contains("（3）")){
								json.put("name_chinese", fac[2].replace("（3）", "").trim());
								json.put("type", 3);
							}
							else{
								json.put("name_chinese", fac[2].trim());
								json.put("type", 3);
							}
							if(each.contains("（1）")){
								json.put("name", fac[0].replace("（1）", "").trim());
								json.put("type", 1);
							}
							if(each.contains("（2）")){
								json.put("name", fac[0].replace("（2）", "").trim());
								json.put("type", 2);
							}
							if(each.contains("（3）")){
								json.put("name", fac[0].replace("（3）", "").trim());
								json.put("type", 3);
							}else{
								json.put("name", fac[0].trim());
								json.put("type", 3);
							}
						}else{
							System.out.println(each);
						}
					}
					if(fac.length == 4){
						json.put("istranslated", 0);
						if(each.contains("肯纳弗雷格商学院") || each.contains("达莫尔麦金商学院")){
							json.put("origin_id", fac[2]);
							json.put("name", fac[3].trim());
							json.put("name_chinese", fac[0]+"-"+fac[1]);
						}else{
							json.put("origin_id", fac[2]);
							json.put("name_chinese", fac[0].trim());
							json.put("name", fac[2]+"-"+fac[3]);
						}
						
					}
				}else{
					json.put("name_chinese", "");
					json.put("istranslated", 0);
					json.put("origin_id", -1);
					json.put("name",each);
				}
				res.add(json.toString());	
			}
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/数据补全/学院列表/美国/fac.json");
		System.out.println("_____________Exit_______________");
	}
	
	static String isSch(String str){
		try{
			String startThree = str.substring(0,3).replaceAll("\\D", "");
			if(!startThree.equals(""))
				return startThree;
		}catch(Exception e){
			return "";
		}
		return "";
	}
	
	static boolean isNum(String str){
		String tmp = str.replace("（1）", "").replace("（2）", "").replace("（3）", "").replaceAll("\\D", "");
		if(!tmp.equals(""))
			return true;
		else
			return false;
	}
	static boolean isChi(String str){
		String tmp = str.substring(0, 2).replaceAll("[[\u4e00-\u9fa5]]", "");
		if(tmp.equals(""))
			return true;
		else
			return false;
	}

}
