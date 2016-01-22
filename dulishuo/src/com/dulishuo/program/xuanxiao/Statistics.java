package com.dulishuo.program.xuanxiao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Sheet;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

/*
 * author : xiaohe
 * date : 2015-10-19
 * description: 选校系统-各专业的学校统计、program统计
 */

public class Statistics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		schoolStatistic();
		 //pgmStat();
		//test();
	}

	private static void test() {
		// TODO Auto-generated method stub
		System.out.println("-----start------");
		List<String> list = FileUtil.FileToList("H:/独立说/backup/institute1027.json");
		Set<String> set = new HashSet<String>();
		
		for(String xx : list){
			JSONObject json = JSONObject.fromObject(xx);
			if(Util.id(json.get("id").toString()) > 846)
				System.out.println(Util.id(json.get("id").toString())+"\t"+json.getString("title")+"\t"+json.getString("ttitle"));
			
		}
		
		
		System.out.println("-----end------");
	}

	private static void pgmStat() {
		// TODO Auto-generated method stub
		Sheet sheet = FileUtil.getExcelSht(
				"C:/Users/强胜/Desktop/数据补全/选校系统/理工文商/选校专业.xls", 13);
		Set<Integer> set = new HashSet<Integer>();

		List<JSONObject> program = FileUtil.FileToJsonList(
				"H:/独立说/backup/program1106.json", "utf-8");
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < 122; i++) {
			set.add(Integer.parseInt(sheet.getRow(i).getCell(2).toString()
					.replace(".0", "")));
			map.put(Integer.parseInt(sheet.getRow(i).getCell(2).toString()
					.replace(".0", "")), 0);
		}
		int flag = 1;
		for (JSONObject json : program) {
			System.out.println("process______" + flag++);
			int id = Util.id(json.get("institute_id").toString());

			if (set.contains(id)
					&& json.get("department_type").toString().contains("40")) {
				if (map.containsKey(id))
					map.put(id, map.get(id) + 1);
				else
					map.put(id, 1);
			}
		}

		for (int xx : map.keySet())
			System.out.println(xx + "\t" + Util.getSchName(xx, 1) + "\t"
					+ map.get(xx));
		System.out.println("---------End-----------");
	}

	private static void schoolStatistic() {
		// TODO Auto-generated method stub
		List<String> list = FileUtil
				.FileToList("C:/Users/强胜/Desktop/数据补全/选校系统/text.txt");

		for (String xx : list) {
			int institute_id = Util.getInstitute(xx);

			if (institute_id != -1) {
				System.out.println(xx + "\t" + Util.getSchName(institute_id, 1)
						+ "\t" + institute_id);
			} else
				System.out.println(xx);
		}

		System.out.println("---------___Exit___");
	}

}
