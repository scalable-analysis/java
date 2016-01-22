package com.dulishuo.program;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.dulishuo.util.FileUtil;

import net.sf.json.JSONObject;
public class englishPgmFaculty {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		List<JSONObject> ukp = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/英国专业/ukprogram1.json", "utf-8");
		
		List<String> res = new ArrayList<String>();
		List<String> res1 = new ArrayList<String>();
		List<String> res2 = new ArrayList<String>();
		List<String> res3 = new ArrayList<String>();
		Map<String,String> map = new HashMap<String,String>();
		
		List<JSONObject> list1 = FileUtil.FileToJsonList("C:/Users/强胜/faculty.json", "utf-8");
		System.out.println("statt");
		Map<Integer,String> mapkuli = new HashMap<Integer,String>();
		for(JSONObject json : list1){
			mapkuli.put(json.getInt("institute_id"), json.getString("name"));
		}
		
		POIFSFileSystem ps = new POIFSFileSystem(new FileInputStream("C:/Users/强胜/Desktop/数据补全/英国专业faculty1111.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(ps);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		HSSFRow row;
		
		for(int i = 0 ; i < 339; i++){
			row = sheet.getRow(i);
			String xx = row.getCell(0).toString();		
			String yy = row.getCell(2).toString().substring(0, row.getCell(2).toString().indexOf("."));	
			//System.out.println(xx+"____"+yy);
			if(!yy.equals("-1"))
				map.put(xx, yy);
		}
		int flag = 1457;
		Map<String,Object> mapp = new HashMap<String,Object>();
		
		for(Entry<String,String> entry : map.entrySet()){
			Set<Integer> set = new HashSet<Integer>();
			
			for(JSONObject xx : ukp){
				
				if(xx.getString("faculty").equals(entry.getKey())){
					if(!set.contains(xx.getInt("institute_id"))){
						set.add(xx.getInt("institute_id"));
						mapp.put("id", flag++);
						mapp.put("institute_id", xx.getInt("institute_id"));
						mapp.put("name", xx.getString("faculty"));
						int[] type = new int[1];
						type[0] = Integer.parseInt(map.get(xx.getString("faculty")));
						mapp.put("faculty-type", type);
						JSONObject json = JSONObject.fromObject(mapp);
						res.add(json.toString());
					}
					
					
				}
			}
		}
		for(JSONObject xx : ukp){
			res3.add(xx.toString());
		}
		for(JSONObject xx : ukp){
			for(String yy :  res){
				JSONObject json = JSONObject.fromObject(yy);
				if(xx.getString("faculty").equals(json.getString("name")) && xx.getInt("institute_id") == json.getInt("institute_id")){					
					res3.remove(xx.toString());
					xx.put("faculty_id", json.getInt("id"));	
					res1.add(xx.toString());
					
					break;
				}
			}
		}	
		
		
		
		FileUtil.ListToFile(res1, "C:/Users/强胜/Desktop/dataCrawler/英国专业/ukprogram111.json");
		FileUtil.ListToFile(res3, "C:/Users/强胜/Desktop/dataCrawler/英国专业/ukprogramfail.json");
		
		
		System.out.println("___end____");
	}
	

}
