package com.dulishuo.offer.gter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class Process {

	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		System.out.println("start. . . ");
		
		//test();
		process();
		
		
		
		long end = System.currentTimeMillis();
		System.out.println("end : use time : " + (end - start) +"ms.");
	}

	private static void process() {
		// TODO Auto-generated method stub
		HSSFSheet gpaSht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/圣诞/gter/人工/gpa配对.xls", 0);
		HSSFSheet facSht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/圣诞/gter/人工/专业配对.xls", 0);
		HSSFSheet insSht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/圣诞/gter/人工/学校校对.xls", 0);
		
		Map<String , Float> gpaMap = new HashMap<String , Float>();
		Map<String , int[]> facMap = new HashMap<String , int[]>();
		Map<String , Integer> insMap = new HashMap<String , Integer>();
		
		for(int i = 0 ; i < gpaSht.getLastRowNum()+1; i++){
			if(!gpaSht.getRow(i).getCell(1).toString().contains("-"))
				gpaMap.put(gpaSht.getRow(i).getCell(0).toString(), Float.parseFloat(gpaSht.getRow(i).getCell(1).toString()));
		}
		
		Pattern ptn = Pattern.compile("\\d{1,2}");
		for(int i = 0 ; i < facSht.getLastRowNum()+1; i++){
			if(facSht.getRow(i).getCell(1) != null){
				Matcher mth = ptn.matcher(facSht.getRow(i).getCell(1).toString());
				Set<Integer> tmpSet = new HashSet<Integer>();
				while(mth.find()){
					tmpSet.add(Integer.parseInt(mth.group()));
				}
				if(tmpSet.contains(0))
					tmpSet.remove(0);
				facMap.put(facSht.getRow(i).getCell(0).toString(), Util.convertStrSetToIntArray(tmpSet));
			}
		}
		
		for(int i = 0 ; i < insSht.getLastRowNum()+1; i++){
			if(!insSht.getRow(i).getCell(2).toString().contains("-"))
				insMap.put(insSht.getRow(i).getCell(0).toString(), Util.id(insSht.getRow(i).getCell(2).toString()));
		}
		
		List<JSONObject> src = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/圣诞/gter/toto.json", "utf-8");
		
		
		List<String> res = new ArrayList<String>();
		Set<String> schSet = new HashSet<String>();
		int resInt = 1;
		int degreeInt = -1;
		int flag = 24439;
		for(JSONObject json : src){
			JSONObject jobj = new JSONObject();
			try{
				if(insMap.containsKey(json.getString("schoolname"))){
					jobj.put("institute_id", insMap.get(json.getString("schoolname")));
					jobj.put("institute", Util.getSchName(insMap.get(json.getString("schoolname")), 2));
					jobj.put("tinstitute", Util.getSchName(insMap.get(json.getString("schoolname")), 1));
				}else
					continue;
				
				//jobj.put("subject", json.getString("professional"));
				if(facMap.containsKey(json.getString("undergraduate_subject"))){
					jobj.put("department_type", facMap.get(json.getString("undergraduate_subject")));
					jobj.put("oldMajor", json.getString("undergraduate_subject"));
				}else
					continue;
				if(gpaMap.containsKey(json.getString("undergraduate_gpa")))
					jobj.put("gpa", gpaMap.get(json.getString("undergraduate_gpa")));
				else
					continue;
				
				jobj.put("year", Integer.parseInt(json.getString("year")));
				
				//toefl
				JSONObject toefl = new JSONObject();
				if(json.getJSONObject("toefl").get("z").toString().length() > 0)
					toefl.put("total", Integer.parseInt(json.getJSONObject("toefl").get("z").toString()));
				else
					toefl.put("total", "");
				if(json.getJSONObject("toefl").get("l").toString().length() > 0)
					toefl.put("listening", Integer.parseInt(json.getJSONObject("toefl").get("l").toString()));
				else
					toefl.put("listening", "");
				if(json.getJSONObject("toefl").get("s").toString().length() > 0)
					toefl.put("speaking", Integer.parseInt(json.getJSONObject("toefl").get("s").toString()));
				else
					toefl.put("speaking", "");
				if(json.getJSONObject("toefl").get("p").toString().length() > 0)
					toefl.put("reading", Integer.parseInt(json.getJSONObject("toefl").get("p").toString()));
				else
					toefl.put("reading", "");
				if(json.getJSONObject("toefl").get("w").toString().length() > 0)
					toefl.put("writing", Integer.parseInt(json.getJSONObject("toefl").get("w").toString()));
				else
					toefl.put("writing", "");
				toefl.put("other", "");
				jobj.put("toefl", toefl);
				
				//ielts
				JSONObject ielts = new JSONObject();
				if(json.getJSONObject("ielts").get("z").toString().length() > 0)
					ielts.put("total", Float.parseFloat(json.getJSONObject("ielts").get("z").toString()));
				else
					ielts.put("total", "");
				if(json.getJSONObject("ielts").get("l").toString().length() > 0)
					ielts.put("listening", Float.parseFloat(json.getJSONObject("ielts").get("l").toString()));
				else
					ielts.put("listening", "");
				if(json.getJSONObject("ielts").get("s").toString().length() > 0)
					ielts.put("speaking", Float.parseFloat(json.getJSONObject("ielts").get("s").toString()));
				else
					ielts.put("speaking", "");
				if(json.getJSONObject("ielts").get("r").toString().length() > 0)
					ielts.put("reading", Float.parseFloat(json.getJSONObject("ielts").get("r").toString()));
				else
					ielts.put("reading", "");
				if(json.getJSONObject("ielts").get("w").toString().length() > 0)
					ielts.put("writing", Float.parseFloat(json.getJSONObject("ielts").get("w").toString()));
				else
					ielts.put("writing", "");
				ielts.put("other", "");
				jobj.put("ielts", ielts);
				
				//gre
				JSONObject gre = new JSONObject();
				if(json.getJSONObject("gre").get("z").toString().length() > 0)
					gre.put("total", Integer.parseInt(json.getJSONObject("gre").get("z").toString()));
				else
					gre.put("total", "");
				if(json.getJSONObject("gre").get("v").toString().length() > 0)
					gre.put("v", Integer.parseInt(json.getJSONObject("gre").get("v").toString()));
				else
					gre.put("v", "");
				if(json.getJSONObject("gre").get("q").toString().length() > 0)
					gre.put("q", Integer.parseInt(json.getJSONObject("gre").get("q").toString()));
				else
					gre.put("q", "");
				if(json.getJSONObject("gre").get("aw").toString().length() > 0)
					gre.put("aw", Float.parseFloat(json.getJSONObject("gre").get("aw").toString()));
				else
					gre.put("aw", "");
				gre.put("other", "");
				jobj.put("gre", gre);
				
				//gmat
				JSONObject gmat = new JSONObject();
				if(json.getJSONObject("gmat").get("z").toString().length() > 0)
					gmat.put("total", Integer.parseInt(json.getJSONObject("gmat").get("z").toString()));
				else
					gmat.put("total", "");
				if(json.getJSONObject("gmat").get("v").toString().length() > 0)
					gmat.put("v", Integer.parseInt(json.getJSONObject("gmat").get("v").toString()));
				else
					gmat.put("v", "");
				if(json.getJSONObject("gmat").get("q").toString().length() > 0)
					gmat.put("q", Integer.parseInt(json.getJSONObject("gmat").get("q").toString()));
				else
					gmat.put("q", "");
				gmat.put("other", "");
				jobj.put("gmat", gmat);
				
				resInt = Integer.parseInt(json.getString("apply_results")) - 1;
				if(resInt == 3 || resInt == 2)
					resInt--;
				jobj.put("result", resInt);
				
				degreeInt = Integer.parseInt(json.getString("degree"));
				
				switch(degreeInt)
				{
				case 1:jobj.put("degree", "ms");
				break;
				case 2:jobj.put("degree", "phd");
				break;
				case 3:jobj.put("degree", "ma");
				break;
				case 4:jobj.put("degree", json.getString("degree_other").toLowerCase());
				break;
				case 9:jobj.put("degree", "llm");
				break;
				default:
					jobj.put("degree", "ms");
				break;
				}
				jobj.put("from", "gter");
				jobj.put("url", json.getString("url"));
				jobj.put("currentschool", json.getString("undergraduate_sid"));
				jobj.put("id", flag++);
				res.add(jobj.toString());
				
				
			}catch(Exception e){}
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/圣诞/gter/tot-01.json");
	}

	private static void test() {
		// TODO Auto-generated method stub
		List<JSONObject> src = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/圣诞/gter/toto.json", "utf-8");
		
		Set<String> schSet = new HashSet<String>();
		for(JSONObject json : src){
			try{
				/*if(json.getJSONObject("toefl").getString("z").length() > 1)
					schSet.add(json.getString("schoolname"));
				else if(json.getJSONObject("ielts").getString("z").length() > 0)
					schSet.add(json.getString("schoolname"));*/
				schSet.add(json.getString("undergraduate_gpa"));
				
			}catch(Exception e){}
		}
		for(String xx : schSet)
			System.out.println(xx);
		
		/*System.out.println("size of set : " + schSet.size());
		
		List<String> res = new ArrayList<String>();
		int insId = -1;
		for(String each : schSet){
			System.out.println("process : " + count++);
			insId = Util.getInstitute(each);
			res.add(each+"\t"+Util.getSchName(insId, 2)+"\t"+insId);
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/圣诞/gter/sch1.txt");*/
	}

}
