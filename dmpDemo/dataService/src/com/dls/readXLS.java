package com.dls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.SystemUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

public class readXLS {
	static Map<String,String> shlMap = new HashMap<String,String>();
	static Set<String> shlSet = new HashSet<String>();
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
	}
	
	
	static int amount = 0;
	static int amountA = 0;
	static int amountB = 0;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		HSSFRow row;
		
		
		POIFSFileSystem ps = new POIFSFileSystem(new FileInputStream("C:/Users/强胜/Desktop/mailattachment/school.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(ps);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		for(int i = 0 ; i < rows+1; i++){
			row = sheet.getRow(i);
			System.out.println(String.valueOf(row.getCell(1)).toLowerCase().trim()+"-------"+String.valueOf(row.getCell(0)).substring(0,3));
			shlMap.put(String.valueOf(row.getCell(1)).toLowerCase().trim(),String.valueOf(row.getCell(0)).substring(0,3));
			shlSet.add(String.valueOf(row.getCell(1)).toLowerCase().trim());
		}
		
		List<offerBean> offerList = new ArrayList<offerBean>();
		
		try{
			long startTime = System.currentTimeMillis();
		     BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/强胜/Desktop/mailattachment/offer22.txt")));

		     
		     BufferedReader brO = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/mailattachment/offer1.json")));
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
					ob.setSchool(jobjO.get("school").toString().trim());
					ob.setSchool_id(setSchoolId(jobjO.get("school").toString().trim()));
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
	
	
	public static String setSchoolId(String src){
		//-1 表示没有匹配到institute_id 
		String rsl = "-1";
		
		Set<String> srcSet = new HashSet<String>();
		Set<String> descSet = new HashSet<String>();
		srcSet = extractWord(src);
		
		
		int count = 0;
		int total = 0;
		
		int count11 = 0;
		int total22 = 0;
		
		String flag = "-1";
		
		double perc = 0.00d;
		double percTmp = 0.00d;
		amount++;
		if(shlSet.contains(src.toLowerCase().trim())){
			rsl = shlMap.get(src.toLowerCase().trim());
			amountA++;
			System.out.println("(A--- "+amountA+" -)执行到第--- "+ amount +" --条 "+", --- "+src);
		}else{
			//提取出关键词进行匹配			
			amountB++;
			
			for(String tmpShl : shlSet){
				count11 = 0;
				total22 = 0;
				descSet = extractWord(tmpShl);
				total22 = descSet.size();
				
				for(String tmpSrc : srcSet){					
						//System.out.println("tmpSrc--"+tmpSrc);
						for(String tmpDesc : descSet){							
								//System.out.println("tmpDesc--"+tmpDesc);
								if(tmpSrc.equals(tmpDesc)){
									count11++;
							}							
						}					
				}
				
				percTmp = Double.parseDouble(String.valueOf(count11))/Double.parseDouble(String.valueOf(total22));
				
				
				
					if(percTmp > perc){
						flag = tmpShl;
						perc = percTmp;
					
					
				}
			}
			//判断是否存在已有institute库
			
			if(perc > 0){
				rsl = shlMap.get(flag);
				System.out.println("(B--- "+amountB+" -)执行到第--- "+ amount +" --条 ,---"+flag);
			}
		}
		return rsl;
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
