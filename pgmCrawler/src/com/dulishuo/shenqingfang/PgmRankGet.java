package com.dulishuo.shenqingfang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dulishuo.util.CrawlerUtil;
import com.dulishuo.util.FileUtil;

public class PgmRankGet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . \n");
		long start = System.currentTimeMillis();
		
		//test();
		process();
		//getHttp("https://www.applysquare.com/zh-cn/fos/materials/?page=2");
		
		long end = System.currentTimeMillis();
		System.out.println("\nEnd . . .use time :  "+(end - start) +" ms");
	}

	private static void process() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/program2015-12/呵呵大.json", "utf-8");
		int dep = -1;
		for(JSONObject json : list){
			dep = Integer.parseInt(json.getString("dep"));
			Document doc = Jsoup.parse(json.getString("content"));
			Elements eles = doc.getElementsByClass("search-content").get(0).getElementsByClass("col-md-6");
			String link = "";
			Map<String,String> map1 = new HashMap<String,String>();
			Map<Integer , Set<String>> haha = new HashMap<Integer , Set<String>>();
			List<String> set = new ArrayList<String>();
			Map<String,String> map2 = new HashMap<String , String>();
			for(Element ele : eles){
				/*Elements hrefs = ele.getElementsByTag("a");
				for(Element href : hrefs){
					
					link = "https://www.applysquare.com"+href.attr("href");	
					set.add(link);
					
					//	System.out.println(link);
				}*/
				Elements hrefs = ele.getElementsByTag("a");
				Elements divs = ele.getElementsByTag("div");
				if(hrefs.size() == divs.size()-1){
					for(int i = 0 ; i < hrefs.size() ; i++){
						set.add("https://www.applysquare.com"+hrefs.get(i).attr("href").trim() + "\t" + divs.get(i+1).text());
					}
					
				}
					
				/*List<String> deadList = new ArrayList<String>();
				String xoxo = ele.text();
				Matcher mth = ptn.matcher(xoxo);
				while(mth.find())
					deadList.add(mth.group());
				
				if(deadList.size() != hrefs.size()){
					System.out.println(ele.html());
					System.out.println();
					System.out.println();
					break;
				}*/
					
				/*for(Element href : hrefs){
				}*/
			}
			FileUtil.ListToFile(set, "C:/Users/强胜/Desktop/dataCrawler/申请方/program2015-12/呵呵xiao/"+String.valueOf(dep)+".json",true);
		}
		
		
	}

	private static void test() {
		// TODO Auto-generated method stub
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("pharmacy\t3",4);
		map.put("fine_arts\t2",4);
		map.put("public_management_administration\t1",2);
		map.put("anthropology\t4",4);
		map.put("mechanical_engineering\t6",6);
		map.put("computer_engineering\t5",6);
		map.put("accounting\t7",2);
		map.put("architecture\t8",2);
		map.put("religion\t9",3);
		map.put("psychology\t11",4);
		map.put("materials\t10",4);
		map.put("political_science\t12",5);
		map.put("medicine\t13",3);
		map.put("biology\t14",6);
		map.put("philosophy\t16",3);
		map.put("nuclear_engineering\t15",2);
		map.put("agriculture\t17",2);
		map.put("literature\t18",4);
		map.put("environmental_science\t19",4);
		map.put("supply_chain_and_logistics\t20",1);
		map.put("criminology\t21",2);
		map.put("health\t22",2);
		map.put("biomedical_engineering\t23",4);
		map.put("social_work\t24",5);
		map.put("public_policy_analysis\t25",2);
		map.put("public_health\t26",3);
		map.put("economics\t27",5);
		map.put("language\t28",4);
		map.put("english\t29",4);
		map.put("management\t30",3);
		map.put("actuarial_science\t31",1);
		map.put("financial_engineering\t32",3);
		map.put("physics\t33",6);
		map.put("sociology\t34",4);
		map.put("biological_agricultural_engineering\t35",2);
		map.put("education\t36",6);
		map.put("mathematics\t37",6);
		map.put("computer_science\t38",5);
		map.put("statistics\t39",4);
		map.put("electrical_engineering\t40",6);
		map.put("public_affairs\t41",5);
		map.put("aerospace\t42",3);
		map.put("law\t43",4);
		map.put("geosciences\t44",4);
		map.put("environmental_engineering\t45",4);
		map.put("marketing\t47",2);
		map.put("communication\t46",3);
		map.put("strategy\t49",1);
		map.put("epidemiology\t50",1);
		map.put("urban_planning\t48",1);
		map.put("civil_engineering\t51",4);
		map.put("information_systems\t52",1);
		map.put("mba\t53",5);
		map.put("chemical_engineering\t54",5);
		map.put("entrepreneurship\t55",2);
		map.put("history\t56",4);
		map.put("industrial_engineering\t57",4);
		map.put("chemistry\t58",6);
		map.put("international_business\t60",2);
		map.put("finance\t61",2);
		map.put("human_resource_management\t59",2);
		map.put("operations\t62",1);
		map.put("area_studies\t63",2);
		//map.put("dentistry\t64",);
		//map.put("Veterinary Science\t65",);
		//map.put("journalism\t66",);
		//map.put("public_relations\t67",);
		//map.put("tesol\t68",);
		
		String url = "";
		Map<String,Integer> ttres = new HashMap<String,Integer>();
		List<String> tmpList = new ArrayList<String>();
		for(Entry<String, Integer> entry : map.entrySet()){
			System.out.println(entry.getKey());
			for(int i = 1 ; i < entry.getValue()+1; i++){
				url = "https://www.applysquare.com/zh-cn/fos/"+entry.getKey().split("\t")[0]+"/?page="+i;
				JSONObject json = new JSONObject();
				json.put("dep", entry.getKey().split("\t")[1]);
				json.put("content", getHttp(url));
				tmpList.add(json.toString());
				try {
					Thread.currentThread().sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		FileUtil.ListToFile(tmpList, "C:/Users/强胜/Desktop/dataCrawler/申请方/program2015-12/呵呵大.json");
	}
	
	
	static Pattern ptn = Pattern.compile("(\\d{4}-\\d{1,2}-\\d{1,2}){1,4}");
	static int flag = 1;
	private static String getHttp(String str) {
		// TODO Auto-generated method stub
		//List<String> list = new ArrayList<String>();
		String response = "";
		response = CrawlerUtil.httpsRequest(str);
		
		Document doc = Jsoup.parse(response);
		/*Elements eles = doc.getElementsByClass("search-content").get(0).getElementsByClass("institute-lite");
		for(Element ele : eles){
			Elements elees= ele.nextElementSibling().getElementsByClass("col-md-6");
			for(Element elee : elees){
				Elements pgmTitles = elee.getElementsByTag("a");
				Elements divs = ele.getElementsByTag("div");
				for(int i = 0 ; i < pgmTitles.size() ; i++){
					System.out.println(pgmTitles.get(i).text());
					System.out.println(divs.get(i).text());
				}
			}			
		}*/
		
		Elements eles = doc.getElementsByClass("search-content").get(0).getElementsByClass("col-md-6");
		for(Element ele : eles){
				Elements hrefs = ele.getElementsByTag("a");
				for(Element href : hrefs){
					System.out.println(href.text());
					System.out.println("https://www.applysquare.com"+href.attr("href"));
					System.out.println();
					List<String> deadList = new ArrayList<String>();
					String xoxo = href.parent().parent().text();
					Matcher mth = ptn.matcher(xoxo);
					while(mth.find())
						deadList.add(mth.group());
					
					if(deadList.size() != hrefs.size())
						System.out.println("flag : "+flag++);
					/*for(int i = 0 ; i < xoxo.split("(\\d{4}-\\d{1,2}-\\d{1,2}){1,4}").length ; i++){
						System.out.println(xoxo.split("(\\d{4}-\\d{1,2}-\\d{1,2}){1,4}")[i].trim()+deadList.get(i));
					}*/
					System.out.println();
				}
		}
		return response;
	}

}
