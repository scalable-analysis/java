package com.dls.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class PgmTramslate {
	
	public static void main(String[] args) throws IOException{
		
		List<String> list = new ArrayList<String>();
		//List<String> list11 = new ArrayList<String>();
		Map<String,String> mapP = new HashMap<String,String>();
		Set<String> setP = new HashSet<String>();
		
		
		HSSFRow row;
		HSSFRow row2;
		
		long start = System.currentTimeMillis();
		
		/*POIFSFileSystem ps = null;
		try {
			ps = new POIFSFileSystem(new FileInputStream("C:/Users/强胜/Desktop/bash/program翻译/专业简称整理1.xls"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(ps);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		for(int i = 1 ; i < 63; i++){
			row = sheet.getRow(i);
			setP.add(String.valueOf(String.valueOf(row.getCell(0)).toLowerCase()));
			mapP.put(String.valueOf(String.valueOf(row.getCell(0)).toLowerCase()), String.valueOf(String.valueOf(row.getCell(2))));
		}*/
	
		 BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/强胜/Desktop/rstt3.txt")));
		POIFSFileSystem ps2 = null;
		try {
			ps2 = new POIFSFileSystem(new FileInputStream("C:/Users/强胜/Desktop/pgm.xls"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFWorkbook wb2 = null;
		try {
			wb2 = new HSSFWorkbook(ps2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFSheet sheet2 = wb2.getSheetAt(0);
		for(int i = 0 ; i < 1499; i++){
			row2 = sheet2.getRow(i);
			list.add(String.valueOf(String.valueOf(row2.getCell(0))));
		}
		System.out.println("----------excel解析完成---------");
		
		
		
		for(int i = 0 ; i < list.size() ; i++){
			String response = "";
			String url ="http://openapi.baidu.com/public/2.0/bmt/translate";
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(url);
			method.addParameter("client_id", "6j46EjCjQKVkMMTAQGsFPWa4");
			method.addParameter("from", "auto");
			method.addParameter("to", "zh");
			method.addParameter("q", list.get(i));
			
			try {
				client.executeMethod(method);
			} catch (HttpException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				response = method.getResponseBodyAsString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String dst = "";
			try{
				JSONObject json = JSONObject.fromObject(response);
				JSONArray jsonA = (JSONArray) json.get("trans_result");
				JSONObject jsonTmp = (JSONObject) jsonA.get(0);
				System.out.println("执行到第 "+i+"行");
				dst = jsonTmp.getString("dst");
			}
			catch(Exception e){
				dst="-----";
			}
				//list11.add(jsonTmp.getString("dst"));
				
				if(setP.contains(dst.trim().toLowerCase())){
					dst = mapP.get(dst.trim().toLowerCase());
				
			}
			
			try {
				writer.write(dst);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		 
		/*for(int i = 0 ; i < list11.size() ; i++){
			writer.write(list.get(i));
			writer.newLine();
		}*/
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("end--用时："+(end-start)+"ms");
	}
	
}
