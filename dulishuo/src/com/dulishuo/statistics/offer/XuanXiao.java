package com.dulishuo.statistics.offer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Sheet;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class XuanXiao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		xuanxiaoOfferZhiChi();
	}
 
	private static void xuanxiaoOfferZhiChi() {
		// TODO Auto-generated method stub
		Sheet sheet = FileUtil.getExcelSht("C:/Users/强胜/Desktop/数据补全/选校系统/151010-理工文商专业二级分支.xls", 1);
	 
		Map<String,String> map = new HashMap<String,String>();
		for(int i = 0 ; i < 37 ; i++){
			map.put(sheet.getRow(i).getCell(1).toString().replace(".0", ""), sheet.getRow(i).getCell(0).toString());
		
		}
		
		List<JSONObject> offer = FileUtil.FileToJsonList("H:/独立说/backup/offer1013.json", "utf-8");
		
		Map<String,Integer> res = new HashMap<String,Integer>();
		for(String xx : map.keySet())
			res.put(xx, 0);
		int flag = 1;
		for(JSONObject json : offer){
			System.out.println("process_____"+flag++);
			if(json.containsKey("program_id")){
				if(json.containsKey("department_type")){
					String dep = json.get("department_type").toString();
					for(String xx : map.keySet()){
						if(isTrue(xx,dep)){
							if(res.containsKey(xx))
								res.put(xx, res.get(xx)+1);
						}
							
					}
				}
			}
			
		}
		
		for(String xx : res.keySet())
			System.out.println(map.get(xx)+"("+xx+")"+"\t"+res.get(xx));
		
		System.out.println("-----Exit");
	}

	private static boolean isTrue(String xx, String dep) {
		// TODO Auto-generated method stub
		dep = dep.replace("[", "").replace("]", "").trim();
		if(xx.contains("-")){
			String fir = xx.split("-")[0];
			String sec = xx.split("-")[1];
			if(dep.contains(",")){
				String[] tmp = dep.split(",");
				for(String yy : tmp){
					if(yy.equals(fir) || yy.equals(sec))
						return true;
				}
			}else{
				if(dep.equals(fir) || dep.equals(sec))
					return true;
			}
		}else{
			if(dep.contains(",")){
				String[] tmp = dep.split(",");
				for(String yy : tmp){
					if(yy.equals(xx))
						return true;
				}
			}else{
				if(dep.equals(xx))
					return true;
			}
		}
		return false;	
	}

}
