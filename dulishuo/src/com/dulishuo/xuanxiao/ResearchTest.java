package com.dulishuo.xuanxiao;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class ResearchTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . \n");
		long start = System.currentTimeMillis();

		test();

		long end = System.currentTimeMillis();
		System.out.println("end use time : " + (end - start) + " ms .");
	}

	private static void test() {
		// TODO Auto-generated method stub
		Sheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/数据补全/选校系统/科研/Visiting-Research入库.xls", 0);
		List<String> res = new ArrayList<String>();
		int count = 1;
		for(int i = 1 ; i < 7 ; i++){
			JSONObject json = new JSONObject();
			json.put("id", count++);	//项目id
			Row row = sht.getRow(i);
			int insId = Util.id(row.getCell(0).toString().replace(".0", ""));
			String institute = row.getCell(1).toString().trim();
			String tinstitute = row.getCell(2).toString().trim();
			String title = row.getCell(3).toString();
			int type = Util.id(row.getCell(4).toString().replace(".0", ""));
			int level = Util.id(row.getCell(5).toString().replace(".0", ""));
			String url = row.getCell(6).toString().trim();
			String introduce = row.getCell(7).toString();
			
			json.put("institute_id", insId);	//学校id
			json.put("institute", institute);	//学校英文名
			json.put("tinstitute", tinstitute);		//学校中文名
			json.put("title", title);		//项目
			json.put("type", type);	//项目类型 1--visiting  2--internship
			
			json.put("level", level);	//项目等级
			json.put("url", url);		//项目链接
			json.put("introduce", introduce);		//项目介绍
			
			//项目好处
			if(!isNull(row.getCell(8))){
				String benefit = row.getCell(8).toString(); 
				json.put("benefit", benefit);
			}else
				json.put("benefit", "");
			//项目对象
			if(!isNull(row.getCell(9))){
				String to_who = row.getCell(9).toString(); 
				json.put("to_who", to_who);
			}else
				json.put("to_who", "");
			//项目申请费用
			if(!isNull(row.getCell(10))){
				String apply_fee = row.getCell(10).toString(); 
				json.put("apply_fee", apply_fee);
			}else
				json.put("apply_fee", "");
			//项目费用
			if(!isNull(row.getCell(11))){
				String cost = row.getCell(11).toString(); 
				json.put("cost", cost);
			}else
				json.put("cost", "");
			//费用详情链接
			if(!isNull(row.getCell(12))){
				String cost_url = row.getCell(12).toString(); 
				json.put("cost_url", cost_url);
			}else
				json.put("cost_url", "");
			//注意事项
			if(!isNull(row.getCell(13))){
				String attention = row.getCell(13).toString(); 
				json.put("attention", attention);
			}else
				json.put("attention", "");
			//申请链接
			if(!isNull(row.getCell(14))){
				String apply_url = row.getCell(14).toString(); 
				json.put("apply_url", apply_url);
			}else
				json.put("apply_url", "");
			//截至日期
			if(!isNull(row.getCell(15))){
				String deadline = row.getCell(15).toString(); 
				json.put("deadline", deadline);
			}else
				json.put("deadline", "");
			//项目要求
			if(!isNull(row.getCell(16))){
				String requirement = row.getCell(16).toString(); 
				json.put("requirement", requirement);
			}else
				json.put("requirement", "");
			//学期
			if(!isNull(row.getCell(17))){
				String term = row.getCell(17).toString(); 
				json.put("term", term);
			}else
				json.put("term", "");
			//项目申请流程链接
			if(!isNull(row.getCell(18))){
				String apply_process_url = row.getCell(18).toString(); 
				json.put("apply_process_url", apply_process_url);
			}else
				json.put("apply_process_url", "");
			
			
			res.add(json.toString());
			
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/数据补全/选校系统/科研/visiting.json");

	}
	static boolean isNull(Cell cell){
		if(cell == null)
			return true;
		else if(cell.toString().replace(" ", "").equals(""))
			return true;
		return false;
	}

}
