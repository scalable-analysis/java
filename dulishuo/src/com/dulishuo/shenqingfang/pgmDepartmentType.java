package com.dulishuo.shenqingfang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Sheet;

import com.dulishuo.util.FileUtil;

public class pgmDepartmentType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/缺乏departmentTypE(1).xls",0);
		List<JSONObject> ne = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program88.json", "utf-8");
		Map<String,Object> map = new HashMap<String,Object>();
		for(int i = 0 ; i < 1765 ; i++){
			String vv = sht.getRow(i).getCell(2).toString().replace(".0", "");
			String[] tmp = vv.split(",");
			int size = tmp.length;
			if(size == 1){
				int[] ttt = new int[1];
				ttt[0] = Integer.parseInt(tmp[0]);
				map.put(sht.getRow(i).getCell(0).toString(), ttt);
			}else if(size > 1){
				try{
					int[] ttt = new int[tmp.length];
					for(int j = 0 ; j < tmp.length ; j ++){
						ttt[j] = Integer.parseInt(tmp[j]);
					}
					map.put(sht.getRow(i).getCell(0).toString(), ttt);
				}catch(Exception e){
					System.out.println(vv);
				}
				
			}
		}
		
		List<String> res = new ArrayList<String>();
		for(JSONObject json : ne){
			if(json.get("department_type").toString().length() < 3){
				if(map.containsKey(json.getString("title"))){
					json.put("department_type", map.get(json.getString("title")));
				}
			}
			res.add(json.toString());	
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program99.json");
		System.out.println("_________eXIT_________");
	}

}
