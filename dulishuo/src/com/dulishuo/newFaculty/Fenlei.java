package com.dulishuo.newFaculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class Fenlei {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> fac = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/fac/facRank2.json", "utf-8");
		List<JSONObject> type = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/fac/facTypeRank.json", "utf-8");
		List<String> res = new ArrayList<String>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(JSONObject json : type){
			map.put(json.getString("subject_chinese"), json.getInt("id"));
		}
		for(JSONObject json : fac){
			json.remove("fac_type");
			json.put("rank_type_id", map.get(json.getString("name_chinese")));
			res.add(json.toString());
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/fac/facRank3.json");
	}
	
	static String getClassify(String str){
		List<String> ls1 = new ArrayList<String>();
		List<String> ls2 = new ArrayList<String>();
		List<String> ls3 = new ArrayList<String>();
		List<String> ls4 = new ArrayList<String>();
		List<String> ls5 = new ArrayList<String>();
		List<String> ls6 = new ArrayList<String>();
		ls1.add("航空航天工程和科学");
		ls1.add("农业工程");
		ls1.add("生命工程");
		ls1.add("化学工程");
		ls1.add("土木工程");
		ls1.add("计算机工程");
		ls1.add("计算机科学");
		ls1.add("电机/电子工程");
		ls1.add("环境工程");
		ls1.add("工业工程/运筹学");
		ls1.add("材料工程和科学");
		ls1.add("机械工程");
		ls1.add("核能工程");
		
		ls2.add("会计学");
		ls2.add("创业学");
		ls2.add("金融学");
		ls2.add("金融工程");
		ls2.add("人力资源管理");
		ls2.add("信息系统学");
		ls2.add("国际贸易");
		ls2.add("管理学");
		ls2.add("市场营销学");
		ls2.add("工商管理学");
		ls2.add("运筹管理学");
		ls2.add("战略管理学");
		ls2.add("物流管理学");
		
		ls3.add("人类学");
		ls3.add("区域研究学");
		ls3.add("大众传媒与新闻学");
		ls3.add("犯罪学");
		ls3.add("经济学");
		ls3.add("教育学");
		ls3.add("法学");
		ls3.add("哲学");
		ls3.add("政治学");
		ls3.add("心理学");
		ls3.add("公共事务学");
		ls3.add("政府公共管理学");
		ls3.add("公共政策分析学");
		ls3.add("宗教学");
		ls3.add("社会工作学");
		ls3.add("社会学");
		ls3.add("城市规划");
		
		ls4.add("精算学");
		ls4.add("农学");
		ls4.add("化学");
		ls4.add("环境科学");
		ls4.add("地球科学");
		ls4.add("数学");
		ls4.add("物理");
		ls4.add("统计学");
		
		ls5.add("生命科学");
		ls5.add("流行病学");
		ls5.add("卫生学");
		ls5.add("医学");
		ls5.add("药学");
		ls5.add("公共卫生学");
		
		ls6.add("建筑学");
		ls6.add("英语");
		ls6.add("艺术学");
		ls6.add("历史学");
		ls6.add("语言文学");
		ls6.add("文学");
		
		if(ls1.contains(str))
			return "工程技术";
		else if(ls2.contains(str))
			return "商学与管理";
		else if(ls3.contains(str))
			return "社会科学";
		else if(ls4.contains(str))
			return "数学与自然科学";
		else if(ls5.contains(str))
			return "生命科学与医学";
		else if(ls6.contains(str))
			return "人文艺术";
		else
			return "-1";
		
	}

}
