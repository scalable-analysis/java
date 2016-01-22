package com.dulishuo.shenqingfang;

import com.dulishuo.util.FileUtil;

import java.util.*;

import net.sf.json.JSONObject;

public class Tongji {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/统计.json", "utf-8");
		int greC = 0;
		int gmatC = 0;
		int gpaC = 0;
		int tCa = 0;
		int tCl = 0;
		int tCs = 0;
		int tCr = 0;
		int tCw = 0;
		int iCa = 0;
		int iCl = 0;
		int iCs= 0;
		int iCr = 0;
		int iCw = 0;
		for(JSONObject json : list){
			if(!json.getString("gre_requirement").equals("-1"))
				greC++;
			if(!json.getString("gpa_requirement").equals("-1"))
				gpaC++;
			if(!json.getString("gmat_requirement").equals("-1"))
				gmatC++;
			JSONObject to = json.getJSONObject("toefl");
			if(to.containsKey("total"))
				tCa++;
			if(to.containsKey("listening"))
				tCl++;
			if(to.containsKey("speaking"))
				tCs++;
			if(to.containsKey("reading"))
				tCr++;
			if(to.containsKey("writing"))
				tCw++;
			JSONObject ie = json.getJSONObject("ielts");
			if(ie.containsKey("total"))
				iCa++;
			if(ie.containsKey("listening"))
				iCl++;
			if(ie.containsKey("speaking"))
				iCs++;
			if(ie.containsKey("reading"))
				iCr++;
			if(ie.containsKey("writing"))
				iCw++;
		}
		
		System.out.println("gre___"+greC);
		System.out.println("gpa___"+gpaC);
		System.out.println("gmat___"+gmatC);
		System.out.println("toefl__total_---"+tCa);
		System.out.println("toefl__l_---"+tCl);
		System.out.println("toefl__s_---"+tCs);
		System.out.println("toefl__r_---"+tCr);
		System.out.println("toefl__w_---"+tCw);
		System.out.println("ielts__total_---"+iCa);
		System.out.println("ielts__l_---"+iCl);
		System.out.println("ielts__s_---"+iCs);
		System.out.println("ielts__r_---"+iCr);
		System.out.println("ielts__w_---"+iCw);
	}

}
