package com.dulishuo.QS;

import java.util.*;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class RuKu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//toJson();
		ruKu();
	}

	private static void ruKu() {
		int count = 11500;
		// TODO Auto-generated method stub
		List<JSONObject> sch = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/QS/json/uniRank.json", "utf-8");
		List<String> res = new ArrayList<String>();
		for(JSONObject json : sch){
			if(!json.getString("value").contains("-")){
				json.remove("id");
				json.put("id", count++);
				json.put("value",Integer.parseInt(json.getString("value")));
				json.put("year", 2016);
				res.add(json.toString());
			}
		}
		System.out.println("___________Exit_________");
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/数据补全/QS/json/uniRankRuKu.json");
	}

	private static void toJson() {
		// TODO Auto-generated method stub
		List<String> res = new ArrayList<String>();
		HSSFSheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/数据补全/QS/排名/大学排名.xls",0);
		List<JSONObject> sch = FileUtil.FileToJsonList("C:/Users/强胜/institute.json", "utf-8");
		Map<Integer,String> mapp = new HashMap<Integer,String>();
		for(JSONObject json : sch){
			mapp.put(Util.id(json.get("id").toString()), json.getString("title"));
		}
		HSSFRow row = null;
		for(int i = 0 ; i < 702 ; i++){
			row = sht.getRow(i);
			String value = row.getCell(0).toString().replace(".0", "");
			int id = Integer.parseInt(row.getCell(2).toString().replace(".0", ""));
			String title = row.getCell(1).toString();
			//System.out.println(value+"\t"+id);
			Map<String,Object> map = new HashMap<String,Object>();
			
			map.put("id", i+1);
			map.put("rank_type_id", 42);
			map.put("value", value);
			map.put("institute_id", id);
			map.put("institute", title);
			if(id != -1){
				map.put("institute", mapp.get(id));
			}
			JSONObject json = JSONObject.fromObject(map);
			res.add(json.toString());
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/数据补全/QS/json/uniRank.json");
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/数据补全/QS/json/all.json",true);
		System.out.println("___end____");
	}

}
