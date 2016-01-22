package com.dulishuo.faculty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class TranFac {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		String path = "C:/Users/强胜/Desktop/数据补全/faculty/faculty校对11.xls";
		POIFSFileSystem ps = new POIFSFileSystem(new FileInputStream(path));
		HSSFWorkbook wb = new HSSFWorkbook(ps);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		Map<Object, Object> map = new HashMap<Object, Object>();
		HSSFRow row;
		List<String> res = new ArrayList<String>();
		
		
		for(int i = 1 ; i < rows; i++){
			row = sheet.getRow(i);
			String id = row.getCell(0).toString().replace(".0", "");
			String ttitle = row.getCell(2).toString().trim();
			map.put(ttitle, id);
		}
		
		//Util.printMap(map);
		
		List<JSONObject> faculty = FileUtil.FileToJsonList("C:/Users/强胜/faculty.json", "utf-8");
		System.out.println("____start____");
		
		
		
		for(JSONObject json : faculty){
			json.put("istranslated", 1);
			int idd = Util.id(json.get("id").toString());
			if(map.containsKey(idd)){
				json.put("name-chinese", map.get(idd));
			}
			res.add(json.toString());
			
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/数据补全/faculty/faculty校对22.json");
		
		System.out.println("___end_____");
	}

}
