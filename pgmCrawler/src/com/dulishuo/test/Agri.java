package com.dulishuo.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dulishuo.util.CrawlerUtil;
import com.dulishuo.util.FileUtil;

public class Agri {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(test0716.class.getName()+" Start . . . ");
		long start = System.currentTimeMillis();
		
		test();
		
		long end = System.currentTimeMillis();
		System.out.println("End . . . use time : " + (end - start) + " ms . ");
	}

	private static void test() {
		// TODO Auto-generated method stub
		
		Map<String, Integer> prov = new HashMap<String, Integer>();
		Map<String, Integer> vegetable = new HashMap<String, Integer>();
		Map<String, Integer> fruit = new HashMap<String, Integer>();
		prov.put("全国",00);
		prov.put("北京",11);
		prov.put("天津",12);
		prov.put("河北",13);
		prov.put("山西",14);
		prov.put("内蒙古",15);
		prov.put("辽宁",21);
		prov.put("吉林",22);
		prov.put("黑龙江",23);
		prov.put("上海",31);
		prov.put("江苏",32);
		prov.put("浙江",33);
		prov.put("安徽",34);
		prov.put("福建",35);
		prov.put("江西",36);
		prov.put("山东",37);
		prov.put("河南",41);
		prov.put("湖北",42);
		prov.put("湖南",43);
		prov.put("广东",44);
		prov.put("广西",45);
		prov.put("海南",46);
		prov.put("重庆",49);
		prov.put("四川",51);
		prov.put("贵州",52);
		prov.put("云南",53);
		prov.put("西藏",54);
		prov.put("陕西",61);
		prov.put("甘肃",62);
		prov.put("青海",63);
		prov.put("宁夏",64);
		prov.put("新疆",65);
		
		vegetable.put("胡萝卜",14);
		vegetable.put("白菜",2);
		vegetable.put("茄子",24);
		vegetable.put("南瓜",31);
		vegetable.put("其他",40);
		vegetable.put("草莓",39);
		vegetable.put("莲藕",34);
		vegetable.put("莴苣",9);
		vegetable.put("甘蓝",5);
		vegetable.put("大蒜",18);
		vegetable.put("黄瓜",29);
		vegetable.put("茭白",35);
		vegetable.put("蔬菜",1);
		vegetable.put("芦笋",37);
		vegetable.put("丝瓜",32);
		vegetable.put("竹笋",38);
		vegetable.put("普通白菜",4);
		vegetable.put("萝卜",13);
		vegetable.put("大葱",19);
		vegetable.put("藠头",22);
		vegetable.put("豇豆",28);
		vegetable.put("芥菜",12);
		vegetable.put("冬瓜",30);
		vegetable.put("花椰菜",6);
		vegetable.put("韭菜",21);
		vegetable.put("辣椒",25);
		vegetable.put("苦瓜",33);
		vegetable.put("菜豆",27);
		vegetable.put("蕹菜",10);
		vegetable.put("甜椒",26);
		vegetable.put("洋葱",20);
		vegetable.put("马铃薯",15);
		vegetable.put("金针菜",36);
		vegetable.put("大白菜",3);
		vegetable.put("蕃茄",23);
		vegetable.put("菠菜",8);
		vegetable.put("苋菜",11);
		vegetable.put("芹菜",7);
		vegetable.put("姜",16);
		vegetable.put("芋",17);
		
		fruit.put("水果",11);
		fruit.put("香蕉",15);
		fruit.put("菠萝",16);
		fruit.put("荔枝",17);
		fruit.put("龙眼",18);
		fruit.put("桃",19);
		fruit.put("猕猴桃",20);
		fruit.put("葡萄",21);
		fruit.put("红枣",22);
		fruit.put("苹果",12);
		fruit.put("梨",13);
		fruit.put("柑橘类",14);
		fruit.put("柿子",23);
		
		
		List<String> res_fruit = new ArrayList<String>();
		List<String> res_vegetable = new ArrayList<String>();
		
		BufferedWriter fw_vegetable = FileUtil.FileWriter("vegetable.txt");
		//BufferedWriter fw_fruit = FileUtil.FileWriter("fruit.txt");
		String url = "";
/*		for(int i = 2014 ; i > 1948 ; i--){
			for(int j = 1 ; j < 3 ; j++){
				for(String prov_key : prov.keySet()){
					for(String fruit_key : fruit.keySet()){
						System.out.println("fruit : process year : " + i );
						try {
							Thread.currentThread().sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							url = "http://zzys.agri.gov.cn/shuiguo_cx_result.aspx?year="+i+"&prov="+prov.get(prov_key)+"%20%20%20&item="+fruit.get(fruit_key)+"&type="+j+"&radio=1&order1=year_code&order2=prov_code&order3=item_code";
							JSONObject json = new JSONObject();
							json.put("year", i);
							json.put("province_id", prov_key);
							json.put("province", prov.get(prov_key));
							json.put("fruit_id", fruit_key);
							json.put("fruit", fruit.get(fruit_key));
							if(j == 1)
								json.put("type", "面积（千公顷）");
							else
								json.put("type", "产量（吨）");
							
							json.put("total", extract(url,fruit_key));
							
							fw_fruit.write(json.toString());
							fw_fruit.write("\r\n");
							fw_fruit.flush();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}
		}
		*/
		for(int i = 2002 ; i > 1948 ; i--){
			for(int j = 1 ; j < 4 ; j++){
				for(String prov_key : prov.keySet()){
					for(String vege_key : vegetable.keySet()){
						System.out.println("vegetable : process year : " + i);
						try {
							Thread.currentThread().sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							url = "http://zzys.agri.gov.cn/shucai_cx_result.aspx?year="+i+"&prov="+prov.get(prov_key)+"%20%20%20&item="+vegetable.get(vege_key)+"&type="+j+"&radio=1&order1=year_code&order2=prov_code&order3=item_code";
							JSONObject json = new JSONObject();
							json.put("year", i);
							json.put("province_id", prov_key);
							json.put("province", prov.get(prov_key));
							json.put("vegetable_id", vege_key);
							json.put("vegetable", vegetable.get(vege_key));
							if(j == 1)
								json.put("type", "面积（亩）");
							else if(i == 2)
								json.put("type", "产量（吨）");
							else
								json.put("type", "亩产（公斤）");
							
							json.put("total", extract(url,vege_key));
							fw_vegetable.write(json.toString());
							fw_vegetable.write("\r\n");
							fw_vegetable.flush();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}
		}
		
		//FileUtil.ListToFile(res_fruit, "result/fruit.txt");
		//FileUtil.ListToFile(res_vegetable, "result/vegetable.txt");
	}

	private static String extract(String url , String furit_key) {
		// TODO Auto-generated method stub

		String res = "0";
		try{
			String response = CrawlerUtil.httpRequest(url);
			Document doc = Jsoup.parse(response);
			String content = doc.getElementById("ContentPlaceHolder1_lbldata").text();
			if(content.contains("没有您查询的数据"))
				return res;
			else{
				try{
					res = content.split(furit_key)[1].replaceAll("\\s+", "");
					if(res.substring(0, 1).equals("."))
						res = "0"+res;
					System.out.println(res);
					return res;
				}catch(Exception e){
					return content;
				}
				
			}
		}catch(Exception e){
			return res;
		}
		
			
		
	}

}
