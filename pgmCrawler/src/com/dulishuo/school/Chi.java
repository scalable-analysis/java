package com.dulishuo.school;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dulishuo.util.CrawlerUtil;

public class Chi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . ");
		long start = System.currentTimeMillis();
		
		
		test();
		
		long end = System.currentTimeMillis();
		System.out.println("end . . . ");
	}

	private static void test() {
		// TODO Auto-generated method stub
		Map<String,String> provinceMap = new HashMap<String,String>();
		provinceMap.put("151", "北京");
		provinceMap.put("152", "天津");
		provinceMap.put("153", "河北");
		provinceMap.put("622", "山西");
		provinceMap.put("623", "内蒙古");
		provinceMap.put("322", "辽宁");
		provinceMap.put("642", "吉林");
		provinceMap.put("643", "黑龙江");
		provinceMap.put("159", "江苏");
		provinceMap.put("160", "安徽");
		provinceMap.put("624", "山东");
		provinceMap.put("162", "上海");
		provinceMap.put("163", "浙江");
		provinceMap.put("625", "江西");
		provinceMap.put("626", "福建");
		provinceMap.put("166", "湖北");
		provinceMap.put("627", "湖南");
		provinceMap.put("628", "河南");
		provinceMap.put("170", "广东");
		provinceMap.put("632", "广西");
		provinceMap.put("633", "海南");
		provinceMap.put("342", "重庆");
		provinceMap.put("174", "四川");
		provinceMap.put("176", "贵州");
		provinceMap.put("629", "云南");
		provinceMap.put("630", "西藏");
		provinceMap.put("179", "陕西");
		provinceMap.put("631", "甘肃");
		provinceMap.put("181", "宁夏");
		provinceMap.put("634", "青海");
		provinceMap.put("635", "新疆");
		provinceMap.put("184", "香港");
		provinceMap.put("636", "澳门");
		provinceMap.put("637", "台湾");
		
		String url = "";
		String response = "";
		List<String> result = new ArrayList<String>();
		for(String xx : provinceMap.keySet()){
			url = "http://ziyuan.eol.cn/list.php?listid="+xx;
			
			try {
				response = new String(CrawlerUtil.httpRequest(url).getBytes("ISO-8859-1"),"utf-8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Document doc = Jsoup.parse(response);
			Elements eles = doc.getElementsByTag("table");
			
			Element ele = eles.get(7);
			
			System.out.println(ele.text());
			
			break;
		}
	}

}
