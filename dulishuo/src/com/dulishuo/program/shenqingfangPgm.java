package com.dulishuo.program;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class shenqingfangPgm {
	static int count = 1;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/faculty.json", "utf-8");
		List<JSONObject> list1 = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/program/27日入库的、要进行处理、包括英国的/包括英国、的申请方的program处理11.json", "utf-8");
		
		List<String> result = new ArrayList<String>();
	/*	Map<Integer,String> map1 = new HashMap<Integer,String>();
		Map<Integer,Integer> map2 = new HashMap<Integer,Integer>();
		for(JSONObject json : list){
			map1.put(json.getInt("id"),json.getString("name")+json.getInt("institute_id") );
		}*/
		for(JSONObject json : list1){
			int id = Util.id(json.get("id").toString());
			if(id < 19392 && id > 10000){
				try{
					if(json.getString("website").subSequence(1, 9).equals("pgsearch")){
						System.out.println((count++));
						json.put("website", "http://www.prospects.ac.uk"+json.getString("website"));
					}
				}catch(Exception e){
					//System.out.println(json.getString("website"));
				}
				
			}
			
		/*	String tsubject = trans(json.getString("title"));
			
			if(tsubject.equals("-1")){
				tsubject = trans(json.getString("title"));
				if(tsubject.equals("-1")){
					tsubject = trans(json.getString("title"));
					if(tsubject.equals("-1")){
						tsubject = trans(json.getString("title"));
						if(tsubject.equals("-1")){
							tsubject = trans(json.getString("title"));
							if(tsubject.equals("-1")){
								tsubject = trans(json.getString("title"));
							}
						}
					}
				}
			}	*/
			
			//json.put("ttitle", tsubject);
		/*	for(Entry<Integer,String> entry : map1.entrySet()){
				if(entry.getValue().equals(json.getString("faculty")+Util.id(json.get("institute_id").toString()))){
						json.put("faculty_id", entry.getKey());
					
				}
			}*/
			result.add(json.toString());
		}
		/*List<JSONObject> list1 = FileUtil.FileToJsonList("C:/Users/强胜/faculty.json", "utf-8");
		System.out.println("statt");
		Map<String,Integer> mapkuli = new HashMap<String,Integer>();
		for(JSONObject json : list1){
			mapkuli.put(json.getInt("institute_id")+json.getString("name"),json.getInt("institute_id"));
		}
		
		List<JSONObject> list = FileUtil.FileToJsonList("F:/数据/8.29/program_id_not_matched_program.json", "utf-8");
		List<String> result = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		
		Map<String,String> map = new HashMap<String,String>();
		Map<String,Integer> map1 = new HashMap<String,Integer>();
		POIFSFileSystem ps = new POIFSFileSystem(new FileInputStream("C:/Users/强胜/Desktop/数据补全/program/program补faculty.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(ps);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		HSSFRow row;
		
		for(int i = 0 ; i < 549; i++){
			row = sheet.getRow(i);
			String xx = row.getCell(0).toString();		
			String yy = row.getCell(1).toString();	
			//String yy = row.getCell(2).toString().substring(0, row.getCell(2).toString().indexOf("."));	
			//System.out.println(xx+"____"+yy);
			if(!yy.equals("-1") ){
				
				map.put(xx, yy);
			}
				
		}
		for(JSONObject json : list){
			String faculty = json.getString("faculty");
			map1.put(faculty, Integer.parseInt(json.getString("institute_id")));
		
		}
		int flag = 2700;
		for(Entry<String,String> entry : map.entrySet()){
			String fac = entry.getKey();
			for(Entry<String,Integer> entry1 : map1.entrySet()){
				int insid = entry1.getValue();
				if(entry1.getKey().equals(fac)){
					for(Entry<String,Integer> entry2 : mapkuli.entrySet()){
						if(entry2.getKey().equals(entry1.getValue()+entry1.getKey())){
							insid = entry2.getValue();
							break;
						}
					}
					Map<String,Object> map22 = new HashMap<String,Object>();
					map22.put("id", flag++);
					map22.put("name", entry1.getKey());
					
					if(entry.getValue().toString().indexOf("-") == -1){
						int[] type = new int[1];
						type[0] = Integer.parseInt(entry.getValue().replace(".0", ""));
						map22.put("faculty-type", type);
					}else{
						String[] tt = entry.getValue().split("-");
						int size = tt.length;
						int[] type = new int[size];
						for(int i = 0 ; i < size ; i++){
							if(!tt[i].replace(".0", "").equals(""))
								type[i] = Integer.parseInt(tt[i].replace(".0", ""));
						}
							
						map22.put("faculty-type", type);
					}
					
					
					map22.put("institute_id", insid);
					map22.put("name-chinese", trans(entry1.getKey()));
					System.out.println(map22.get("name-chinese")+String.valueOf((flag-2700)));
					JSONObject jo = JSONObject.fromObject(map22);
					result.add(jo.toString());
					
				}
			}
		}
		
		
		for(String xx : set)
			System.out.println(xx);
		
		for(Entry<String,String> entry : map.entrySet()){
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
			*/
		
		FileUtil.ListToFile(result, "C:/Users/强胜/Desktop/数据补全/program/27日入库的、要进行处理、包括英国的/包括英国、的申请方的program处理22.json");
		System.out.println("__end+___");
	
	}
	
	public static String trans(String xx){
		String response = "";
		String url ="http://openapi.baidu.com/public/2.0/bmt/translate";
		HttpClient client = new HttpClient();
		
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Host", "http://openapi.baidu.com/");  
		method.addParameter("client_id", "6j46EjCjQKVkMMTAQGsFPWa4");
		method.addParameter("from", "auto");
		method.addParameter("to", "zh");
		method.addParameter("q", xx);
		
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
			dst = jsonTmp.getString("dst");
		}
		catch(Exception e){
			dst="-1";
		}
		return dst;
	}

}
