package com.dls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.SystemUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class dataProcess {

	static List<offerBean> offerList = new ArrayList<offerBean>();
	static Map<String,Map<String,String>> pgmMap = new HashMap<String,Map<String,String>>();
	static Set<String> shlSet = new HashSet<String>();
	//subject集合
	static Set<String> sjtSet = new HashSet<String>();
	static List<programBean> programList = new ArrayList<programBean>();
	
	//去除公共词
		static Set<String> setStop = new HashSet<String>();
		static{
			setStop.add("univeristy");
			setStop.add("college");
			setStop.add("institute");
			setStop.add("in");
			setStop.add("and");
			setStop.add("the");
			setStop.add("of");
			setStop.add("at");
			setStop.add("subject");
			setStop.add("major");
			setStop.add("master");
		}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//读取program.json文件里的id，institute,subject键值对到 programList
		 BufferedReader brP = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/bash/mailattachment/program1.json")));
			String tmpP;
			StringBuilder sbP = new StringBuilder();
			while((tmpP=brP.readLine()) != null){
				
				sbP.append(tmpP);
			}
			
			String srcP = sbP.toString();
			JSONArray jArrayP = JSONArray.fromObject(srcP);
			System.out.println("the size of json is : "+jArrayP.size());
			JSONObject jobjP;
			
			for(int i = 0 ; i < jArrayP.size(); i++){
				jobjP = (JSONObject) jArrayP.get(i);
				programBean pb = new programBean();
				pb.setId(jobjP.get("id").toString());			
				pb.setSubject(jobjP.get("subject").toString());
				pb.setInstitute_id(jobjP.get("institute_id").toString());
				shlSet.add(jobjP.get("institute_id").toString());
				sjtSet.add(jobjP.get("subject").toString());
				
				programList.add(pb);
				
				//插入pgmMap, key 为institute_id,value为一个子map(key 为 program的id,value为 subject)
				if(pgmMap.containsKey(jobjP.get("institute_id").toString())){
					Map<String,String> mapPgmTmp = pgmMap.get(jobjP.get("institute_id"));
					mapPgmTmp.put(jobjP.get("id").toString(), jobjP.get("subject").toString());
					pgmMap.put(jobjP.get("institute_id").toString(),mapPgmTmp);
				}else{
					Map<String,String> mapPgmTmp = new HashMap<String,String>();
					mapPgmTmp.put(jobjP.get("id").toString(), jobjP.get("subject").toString());
					pgmMap.put(jobjP.get("institute_id").toString(),mapPgmTmp);
				}
				
			}
		
			
			//System.out.println("the jsonArray of program is : "+jArraZ.toString());
		
		try{
			long startTime = System.currentTimeMillis();
		     BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/强胜/Desktop/offer.json")));

		     
		     BufferedReader brO = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/bash/mailattachment/offer22.txt")));
				String tmpO;
				StringBuilder sbO = new StringBuilder();
				while((tmpO=brO.readLine()) != null){
					
					sbO.append(tmpO);
				}
				
				String srcO = sbO.toString();
				JSONArray jArrayO = JSONArray.fromObject(srcO);
				System.out.println("the size of json is : "+jArrayO.size());
				JSONObject jobjO;
				
				for(int i = 0 ; i < jArrayO.size(); i++){
					jobjO = (JSONObject) jArrayO.get(i);
					offerBean ob = new offerBean();
					ob.setId(jobjO.get("id").toString());
					ob.setTitle(jobjO.get("title").toString());
					ob.setSchool(jobjO.get("school").toString());
					ob.setSchool_id(jobjO.get("school_id").toString());
					ob.setDegree(jobjO.get("degree").toString());
					ob.setMajor(jobjO.get("major").toString());
					ob.setResult(jobjO.get("result").toString());
					ob.setYear(jobjO.get("year").toString());
					ob.setTerm(jobjO.get("term").toString());
					ob.setTime(jobjO.get("time").toString());
					ob.setGRE(jobjO.get("GRE").toString());
					ob.setIELTS(jobjO.get("IELTS").toString());
					ob.setTOEFL(jobjO.get("TOEFL").toString());
					ob.setCurrentschool(jobjO.get("currentschool").toString());
					ob.setSubject(jobjO.get("subject").toString());
					ob.setGPA(jobjO.get("GPA").toString());
					ob.setProgram_id(getProgramId(jobjO.get("school_id").toString(),jobjO.get("major").toString()));
					
					
					offerList.add(ob);
				}

				JSONArray jArrayyy = JSONArray.fromObject(offerList);
				writer.write(jArrayyy.toString());
				
				System.out.println(SystemUtils.LINE_SEPARATOR);
					writer.close();
					
		     	long endTime = System.currentTimeMillis();
		     	System.out.println("共耗时："+(endTime-startTime));
		     	
			}catch(Exception e){
				e.printStackTrace();
		     }
		
		
	}
	
	public boolean shlCompare(String fir, String sec){
		boolean rlt = false;
		
		
		return rlt;
	}
	public static String getProgramId(String shlId, String sbj){
		String rst = "-1";
		int count = 0;
		int total = 0;
		double perc = 0.00d;
		double percTmp = 0.00d;
		String flag = "-1";
		
		
		Set<String> srcSet = new HashSet<String>();
		Set<String> descSet = new HashSet<String>();
		srcSet = extractWord(sbj);
		
		if(shlSet.contains(shlId)){
			Map<String,String> mapTmpShl = new HashMap<String,String>();
			mapTmpShl = pgmMap.get(shlId);
			
			for(Map.Entry<String, String> entrySbj : mapTmpShl.entrySet()){
				String pgmId = entrySbj.getKey();
				String sbjTmp = entrySbj.getValue();
				
				count = 0;
				total = 0;
				
				descSet = extractWord(sbjTmp);
				total = descSet.size();
				
				for(String tmpSrc : srcSet){					
					//System.out.println("tmpSrc--"+tmpSrc);
					for(String tmpDesc : descSet){							
							//System.out.println("tmpDesc--"+tmpDesc);
							if(tmpSrc.equals(tmpDesc)){
								count++;
						}							
					}					
				}
			
			percTmp = Double.parseDouble(String.valueOf(count))/Double.parseDouble(String.valueOf(total));
					
				if(percTmp > perc){				
					perc = percTmp;
					flag = pgmId;
				}
			}
			rst = flag;
		}
		
		return rst;
	}
	
	public static Set<String> extractWord(String src){
		Set<String> rst = new HashSet<String>();		
		String reg = "\\w+";
		Pattern ptn = Pattern.compile(reg);
		Matcher matcher = ptn.matcher(src.toLowerCase());
		
		while(matcher.find()){
			if(!setStop.contains(matcher.group())){
				rst.add(matcher.group());
			}			
		}		
		return rst;
	}

	
}
