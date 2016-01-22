package com.dulishuo.offer.shuige;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import net.sf.json.JSONObject;

public class Process {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//去重
		System.out.println("--start");
		/*List<JSONObject> file = FileUtil.FileToJsonList("C:/Users/强胜/jianzhioffer1201.json", "utf-8");
		List<JSONObject> quchonghouList = Removeduplicate(file);
		
		quchonghouList = handle(quchonghouList);
		
		List<String> res = new ArrayList<String>();
		for(JSONObject json : quchonghouList)
			res.add(json.toString());
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/jianzhioffer.json");*/
		/*List<String> res = new ArrayList<String>();
		List<JSONObject> file = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/jianzhioffer.json", "utf-8");
		String currentschool = "";
		String marker = "";
		String gpa = "";
		String dep = "";
		String year = "";
		String total = "";
		String institute = "";
		String result = "";
		String applicant = "";
		String toefl = "";
		String gre = "";
		String intership = "";
		String research = "";
		String work = "";
		String major;
		String competition = "";
		for(JSONObject json : file){
			try{
				applicant = json.getString("applicant");
				currentschool = json.getString("currentschool");
				dep = json.get("department_type").toString().replace("[", "").replace("]", "").replace(" ", "").replace("\"", "").trim();
				major = json.getString("major");
				marker = json.getString("marker");
				gpa = json.getString("gpa").replace("\"", "").replace(".0", "").trim();
				
				year = json.getString("year");
				institute = json.getString("institute");
				result = json.get("result").toString();
				toefl = json.get("toefl").toString();
				gre = json.get("gre").toString();
				intership = json.get("internship").toString();
				research = json.get("research").toString();
				work = json.get("work").toString();
				competition = json.get("competition").toString();
				total = applicant+"\t"+institute+"\t"+major+"\t"+gpa+"\t"+toefl+"\t"+gre+"\t"+year+"\t"+currentschool+"\t"+result+"\t"+intership+"\t"+research+"\t"+work+"\t"+competition;
				System.out.println(applicant);
			}catch(Exception e){}
			
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/cao.txt");*/
		List<JSONObject> file = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/offer1202.json", "utf-8");
		System.out.println("start calculate . . . ");
		statistics(file);
		System.out.println("Exit . . . ");
	}



	private static List<JSONObject> handle(List<JSONObject> quchonghouList) {
		// TODO Auto-generated method stub
		List<JSONObject> res = new ArrayList<JSONObject>();
		List<Integer> tmp ;
		for(JSONObject json : quchonghouList){
			try{
				if(json.containsKey("department_type")){
					tmp = Util.extractIntArrayToList(json.get("department_type").toString());
					int[] dep = new int[1];
					dep[0] = tmp.get(0);
				
					json.put("department_type", dep);
					
					res.add(json);
				}
			}catch(Exception e){
				System.out.println("error _ "+Process.class+" _ handle : " + e.toString());
			}
		}
		return res;
	}


	/*
	 * author: xiaohe
	 * desc: 统计offer三围分数平均值 ( gpa 、toefl/ielts 、gre/gmat)
	 * date: 2015012-02
	 */
	private static void statistics(List<JSONObject> quchonghouList) {
		// TODO Auto-generated method stub
		
		Map<Integer,Map<Integer,Map<String,Float>>> gpa = new HashMap<Integer,Map<Integer,Map<String,Float>>>();
		Map<Integer,Map<Integer,Map<String,Float>>> toefl = new HashMap<Integer,Map<Integer,Map<String,Float>>>();
		Map<Integer,Map<Integer,Map<String,Float>>> gre = new HashMap<Integer,Map<Integer,Map<String,Float>>>();
		int dep = -1;
		int insId = -1;
		Map<Integer,Map<String,Float>> gpaTmp;
		Map<Integer,Map<String,Float>> toeflTmp;
		Map<Integer,Map<String,Float>> greTmp;
		Map<String,Float> gpaDepTmp;
		Map<String,Float> toeflDepTmp;
		Map<String,Float> greDepTmp;
		
		List<Integer> depList ;
		for(JSONObject json : quchonghouList){
			try{
				depList = Util.extractIntArrayToList(json.get("department_type").toString());
				insId = Util.id(json.get("institute_id").toString());
				
				for(int i = 0 ; i < depList.size() ; i ++){
					dep = depList.get(i);
					//gpa
					try{
						float gpa_total = Float.parseFloat(json.getString("gpa"));
						if(gpa.containsKey(insId)){
							gpaTmp = gpa.get(insId);
							if(gpaTmp.containsKey(dep)){
								gpaDepTmp = gpaTmp.get(dep);
								gpaDepTmp.put("amount", gpaDepTmp.get("amount")+1f);
								gpaDepTmp.put("sumGrade", gpaDepTmp.get("sumGrade")+gpa_total);
							}else{
								gpaDepTmp = new HashMap<String,Float>();
								gpaDepTmp.put("amount", 1f);
								gpaDepTmp.put("sumGrade", gpa_total);
							}
							gpaTmp.put(dep, gpaDepTmp);
						}
						else{
							gpaTmp = new HashMap<Integer,Map<String,Float>>();
							gpaDepTmp = new HashMap<String,Float>();
							gpaDepTmp.put("amount", 1f);
							gpaDepTmp.put("sumGrade", gpa_total);
							gpaTmp.put(dep, gpaDepTmp);
						}
						gpa.put(insId, gpaTmp);
					}catch(Exception e){
						System.out.println("error - type[gpa] : " +e.toString());
						System.out.println(json.get("gpa").toString());
					}
					
					
					//toefl
					try{
						if(json.containsKey("toefl") && json.getJSONObject("toefl").getString("total").length() > 2){
							float toefl_total = Float.parseFloat(json.getJSONObject("toefl").getString("total"));
							if(toefl.containsKey(insId)){
								toeflTmp = toefl.get(insId);
								if(toeflTmp.containsKey(dep)){
									toeflDepTmp = toeflTmp.get(dep);
									toeflDepTmp.put("amount", toeflDepTmp.get("amount")+1f);
									toeflDepTmp.put("sumGrade", toeflDepTmp.get("sumGrade")+toefl_total);
								}else{
									toeflDepTmp = new HashMap<String,Float>();
									toeflDepTmp.put("amount", 1f);
									toeflDepTmp.put("sumGrade", toefl_total);
								}
								toeflTmp.put(dep, toeflDepTmp);
							}
							else{
								toeflTmp = new HashMap<Integer,Map<String,Float>>();
								toeflDepTmp = new HashMap<String,Float>();
								toeflDepTmp.put("amount", 1f);
								toeflDepTmp.put("sumGrade", toefl_total);
								toeflTmp.put(dep, toeflDepTmp);
							}
							toefl.put(insId, toeflTmp);
						}
					}catch(Exception e){
						System.out.println("error - type[toefl] : " +e.toString());
						System.out.println(json.get("toefl").toString());
					}
					
					
					//gre
					try{
						if(json.containsKey("gre")){
							float gre_total = Float.parseFloat(json.getJSONObject("gre").getString("total"));
							if(gre.containsKey(insId)){
								greTmp = gre.get(insId);
								if(greTmp.containsKey(dep)){
									greDepTmp = greTmp.get(dep);
									greDepTmp.put("amount", greDepTmp.get("amount")+1f);
									greDepTmp.put("sumGrade", greDepTmp.get("sumGrade")+gre_total);
								}else{
									greDepTmp = new HashMap<String,Float>();
									greDepTmp.put("amount", 1f);
									greDepTmp.put("sumGrade", gre_total);
								}
								greTmp.put(dep, greDepTmp);
							}
							else{
								greTmp = new HashMap<Integer,Map<String,Float>>();
								greDepTmp = new HashMap<String,Float>();
								greDepTmp.put("amount", 1f);
								greDepTmp.put("sumGrade", gre_total);
								greTmp.put(dep, greDepTmp);
							}
							gre.put(insId, greTmp);
						}
					}catch(Exception e){
						System.out.println("error - type[gre] : " +e.toString());
						System.out.println(json.get("gre").toString());
					}
				}
			}catch(Exception e){
				
			}			
		}
		
		//print
		System.out.println("----------------------------------------------------------");
		System.out.println("学校名\t\t\tdep\t个数\t总分\t平均数");
		List<String> res = new ArrayList<String>();
		for(int x : toefl.keySet()){
			Map<Integer,Map<String,Float>> y = toefl.get(x);
			for(int xx : y.keySet()){
				res.add(Util.getSchName(x, 1)+"\t"+xx+"\t"+y.get(xx).get("amount")+"\t"+y.get(xx).get("sumGrade")+"\t"+y.get(xx).get("sumGrade")/y.get(xx).get("amount"));
			}
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/三围offer成绩统计.txt");
	}


	private static List<JSONObject> Removeduplicate(List<JSONObject> file) {
		// TODO Auto-generated method stub
		List<JSONObject> quchonghouList = new ArrayList<JSONObject>();
		HSSFSheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/呵呵大.xls", 0);		
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0 ; i < 954; i++){
			try{
				map.put(sht.getRow(i).getCell(0).toString().trim(),Util.id(sht.getRow(i).getCell(3).toString().trim()));	
			}catch(Exception e){
				System.out.println(i);
			}
			
			
		}
		
		Set<String> set = new HashSet<String>();
		int flag = 0;
		int flag1 = 0;
		String currentschool = "";
		String marker = "";
		String gpa = "";
		String dep = "";
		String year = "";
		String total = "";
		String institute = "";
		String result = "";
		String applicant = "";
		String toefl = "";
		String gre = "";
		String intership = "";
		String research = "";
		String work = "";
		String competition = "";
		
		for(JSONObject json : file){
			
			try{
				
				applicant = "";
				currentschool = json.getString("currentschool");
				dep = json.get("department_type").toString().replace("[", "").replace("]", "").replace(" ", "").replace("\"", "").trim();
				marker = json.getString("marker");
				gpa = json.getString("gpa").replace("\"", "").replace(".0", "").trim();
				
				year = json.getString("year");
				institute = json.getString("institute");
				result = json.get("result").toString();
				toefl = json.get("toefl").toString();
				gre = json.get("gre").toString();
				intership = json.get("internship").toString();
				research = json.get("research").toString();
				work = json.get("work").toString();
				competition = json.get("competition").toString();
				total = applicant+currentschool+dep+marker+gpa+year+institute+result+toefl+gre+intership+research+work+competition;
				
				
				if(!set.contains(total)){
					try{
						if(json.containsKey("toefl")){
							String toefl_total = json.getJSONObject("toefl").getString("total");
							if(Integer.parseInt(toefl_total.substring(0,toefl_total.indexOf(".")).toString()) < 50){
								json.put("ielts", json.getJSONObject("toefl"));
								json.put("toefl", "");
							}
						}
					}catch(Exception e){}
					
					
					if(Integer.parseInt(gpa.substring(0,gpa.indexOf("."))) > 4)
						json.put("gpa", String.valueOf(Float.parseFloat(gpa)/25).substring(0,4));
					//增加别名
					String alias = Util.loadPerson();
					json.put("alias", alias);
					json.put("oldGre", json.getJSONObject("gre"));
					
					
					json.put("gre", getGre(json.getJSONObject("gre")));
					if(map.containsKey(institute))
						json.put("institute_id", map.get(institute));
					else
						continue;
					
					quchonghouList.add(json);
					//System.out.println(total);
					
					set.add(total);
				}
				else{
					//System.out.println(json.toString());
				}
				
				if(json.containsKey("institute")){
					//set.add(json.getJSONObject("toefl").get("total").toString());
					//set.add(json.getString("year"));
					/*System.out.println(json.getJSONObject("gre").get("total"));
					System.out.println(json.getJSONObject("gre").get("q"));
					System.out.println(json.getJSONObject("gre").get("v"));
					System.out.println();*/
					/*if(Float.parseFloat(json.getJSONObject("toefl").getString("total")) < 9.0){
						System.out.println(json.getJSONObject("toefl").getString("listening"));
						System.out.println(json.getJSONObject("toefl").getString("reading"));
						System.out.println(json.getJSONObject("toefl").getString("writing"));
						System.out.println(json.getJSONObject("toefl").getString("speaking"));
						System.out.println();
					}*/
					
				}
				
			}catch(Exception e){
				
			}
			
		}
		
		try{
			System.out.println(quchonghouList.size());
		}catch(Exception e){
			
		}
		
		return quchonghouList;
		
	/*	for(String xx:  set){
			if(Util.getInstitute(xx) != -1)
				System.out.println(xx+"\t"+Util.getSchName(Util.getInstitute(xx), 1)+"\t"+Util.getSchName(Util.getInstitute(xx), 2)+"\t"+Util.getInstitute(xx));
			else
				System.out.println(xx);
		}
		System.out.println(flag);
		System.out.println(flag1);*/
	}
	
	
	static Map<String,String> q = new HashMap<String,String>();
	static{
		q.put("800", "166");
		q.put("790", "164");
		q.put("780", "163");
		q.put("770", "161");
		q.put("760", "160");
		q.put("750", "159");
		q.put("740", "158");
		q.put("730", "157");
		q.put("720", "156");
		q.put("710", "155");
		q.put("700", "155");
		q.put("690", "154");
		q.put("680", "153");
		q.put("670", "152");
		q.put("660", "152");
		q.put("650", "151");
		q.put("640", "151");
		q.put("630", "150");
		q.put("620", "149");
		q.put("610", "149");
		q.put("600", "148");
		q.put("590", "148");
		q.put("580", "147");
		q.put("570", "147");
		q.put("560", "146");
		q.put("550", "146");
		q.put("540", "145");
		q.put("530", "145");
		q.put("520", "144");
		q.put("510", "144");
		q.put("500", "144");
		q.put("490", "143");
		q.put("480", "143");
		q.put("470", "142");
		q.put("460", "142");
		q.put("450", "141");
		q.put("440", "141");
		q.put("430", "141");
		q.put("420", "140");
		q.put("410", "140");
		q.put("400", "140");
		q.put("390", "139");
		q.put("380", "139");
		q.put("370", "138");
		q.put("360", "138");
		q.put("350", "138");
		q.put("340", "137");
		q.put("330", "137");
		q.put("320", "136");
		q.put("310", "136");
		q.put("300", "136");
		q.put("290", "135");
		q.put("280", "135");
		q.put("270", "134");
		q.put("260", "134");
		q.put("250", "133");
		q.put("240", "133");
		q.put("230", "132");
		q.put("220", "132");
		q.put("210", "131");
		q.put("200", "131");
	}
	static Map<String,String> v = new HashMap<String,String>();
	static{
		v.put("800", "170");
		v.put("790", "170");
		v.put("780", "170");
		v.put("770", "170");
		v.put("760", "170");
		v.put("750", "169");
		v.put("740", "169");
		v.put("730", "168");
		v.put("720", "168");
		v.put("710", "167");
		v.put("700", "166");
		v.put("690", "165");
		v.put("680", "165");
		v.put("670", "164");
		v.put("660", "164");
		v.put("650", "163");
		v.put("640", "162");
		v.put("630", "162");
		v.put("620", "161");
		v.put("610", "160");
		v.put("600", "160");
		v.put("590", "159");
		v.put("580", "158");
		v.put("570", "158");
		v.put("560", "157");
		v.put("550", "156");
		v.put("540", "156");
		v.put("530", "155");
		v.put("520", "154");
		v.put("510", "154");
		v.put("500", "153");
		v.put("490", "152");
		v.put("480", "152");
		v.put("470", "151");
		v.put("460", "151");
		v.put("450", "150");
		v.put("440", "149");
		v.put("430", "149");
		v.put("420", "148");
		v.put("410", "147");
		v.put("400", "146");
		v.put("390", "146");
		v.put("380", "145");
		v.put("370", "144");
		v.put("360", "143");
		v.put("350", "143");
		v.put("340", "142");
		v.put("330", "141");
		v.put("320", "140");
		v.put("310", "139");
		v.put("300", "138");
		v.put("290", "137");
		v.put("280", "135");
		v.put("270", "134");
		v.put("260", "133");
		v.put("250", "132");
		v.put("240", "131");
		v.put("230", "130");
		v.put("220", "130");
		v.put("210", "130");
		v.put("200", "130");
	}
	
	
	
	
	static JSONObject getGre(JSONObject str){
		int total = 1000;
		
		try{		
			if(str.getString("q").trim().length() > 2 && str.getString("v").trim().length() > 2){
				str.put("q", q.get(str.getString("q")));
				str.put("v", v.get(str.getString("v")));
				str.put("total", String.valueOf(Integer.parseInt(str.getString("q"))+Integer.parseInt(str.getString("v"))));
			}
			else{
				if(str.getString("total").trim().length() > 2){
					total = Integer.parseInt(str.getString("total").trim());
					if(total >= 1000){
						if(total < 1100)
							total = 290 + (int)(0.6*(total-1000)/10);
						else if(total <= 1150)
							total = 296 + (int)(1.8*(total-1100)/10);
						else if(total < 1190)
							total = 305 + (int)((total-1150)/10);
						else if(total <= 1250)
							total = 310 + (int)((total-1200)/10);
						else if(total <= 1300)
							total = 316 + (int)((total-1260)/10);
						else if(total <= 1400)
							total = 320 + (int)(0.5*(total-1300)/10);
						else if(total <= 1500)
							total = 326 + (int)(0.5*(total-1410)/10);
						else if(total <= 1550)
							total = 331 + (int)((total-1510)/10);
						else if(total < 1600)
							total = 336 + (int)((total-1260)/10);
						}
					else
						total = 290;
					}
				str.put("total", total);
				if(str.getString("q").trim().length() > 2){
					str.put("q", q.get(str.getString("q")));
				}
				if(str.getString("v").trim().length() > 2){
					str.put("v", v.get(str.getString("v")));
				}
				
				
			}
		}catch(Exception e){
			return str;
		}
		return str;
	}
	

}
