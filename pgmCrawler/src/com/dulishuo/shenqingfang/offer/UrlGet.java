package com.dulishuo.shenqingfang.offer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dulishuo.util.CrawlerUtil;
import com.dulishuo.util.FileUtil;

public class UrlGet {
	static Logger log = Logger.getLogger(UrlGet.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		log.info("start . . . ");
		long start = System.currentTimeMillis();

		//test();
		// getUrl();
		// getOffer();
		 process();

		long end = System.currentTimeMillis();
		log.info("end . . . use time : " + (end - start) / 1000 + " s.");
	}

	static Map<String, Integer> map = new HashMap<String, Integer>();
	static {
		map.put("药学", 3);
		map.put("艺术学", 2);
		map.put("政府公共管理学", 1);
		map.put("人类学", 4);
		map.put("机械工程", 6);
		map.put("计算机工程", 5);
		map.put("会计学", 7);
		map.put("建筑学", 8);
		map.put("宗教学", 9);
		map.put("心理学", 11);
		map.put("材料工程和科学", 10);
		map.put("政治学", 12);
		map.put("医学", 13);
		map.put("生命科学", 14);
		map.put("哲学", 16);
		map.put("核能工程", 15);
		map.put("农学", 17);
		map.put("文学", 18);
		map.put("环境科学", 19);
		map.put("物流管理学", 20);
		map.put("犯罪学", 21);
		map.put("卫生学", 22);
		map.put("生命工程", 23);
		map.put("社会工作学", 24);
		map.put("公共政策分析学", 25);
		map.put("公共卫生学", 26);
		map.put("经济学", 27);
		map.put("语言文学", 28);
		map.put("英语", 29);
		map.put("管理学", 30);
		map.put("精算学", 31);
		map.put("金融工程", 32);
		map.put("物理", 33);
		map.put("社会学", 34);
		map.put("农业工程", 35);
		map.put("教育学", 36);
		map.put("数学", 37);
		map.put("计算机科学", 38);
		map.put("统计学", 39);
		map.put("电机/电子工程", 40);
		map.put("公共事务学", 41);
		map.put("航空航天工程和科学", 42);
		map.put("法学", 43);
		map.put("地球科学", 44);
		map.put("环境工程", 45);
		map.put("市场营销学", 47);
		map.put("大众传媒与新闻学", 46);
		map.put("战略管理学", 49);
		map.put("流行病学", 50);
		map.put("城市规划", 48);
		map.put("土木工程", 51);
		map.put("信息系统学", 52);
		map.put("工商管理学", 53);
		map.put("化学工程", 54);
		map.put("创业学", 55);
		map.put("历史学", 56);
		map.put("工业工程/运筹学", 57);
		map.put("化学", 58);
		map.put("国际贸易", 60);
		map.put("金融学", 61);
		map.put("人力资源管理", 59);
		map.put("运筹管理学", 62);
		map.put("区域研究学", 63);
		map.put("牙医学", 64);
		map.put("兽医学", 65);
		map.put("新闻传媒", 66);
		map.put("公共关系", 67);
		map.put("对外英语教学", 68);
	}

	private static void process() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList(
				"C:/Users/强胜/Desktop/dataCrawler/申请方/offer/ori_content.json",
				"utf-8");
		Set<String> set = new HashSet<String>();
		for (JSONObject json : list) {
			Document doc = Jsoup.parse(json.getString("content"));
			Elements ps = doc.getElementsByTag("p");
			for(Element ele : ps){
				if(isLanguage(ele.text()))
					System.out.println(ele.text());
			}
			System.out.println("\n");
		}

	}
	
	private static boolean isLanguage(String str){
		Set<String> set = new HashSet<String>();
		set.add("ielts");
		set.add("toefl");
		set.add("gpa");
		set.add("gre");
		set.add("gmat");
		for(String xx : set){
			if(str.length() < 30 && str.toLowerCase().contains(xx))
				return true;
		}
		return false;
	}
	
	private static void getOffer() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList(
				"C:/Users/强胜/Desktop/dataCrawler/申请方/offer/url.json", "utf-8");
		String content = "";
		List<String> result = new ArrayList<String>();
		int flag = 1;
		for (JSONObject json : list) {
			System.out.println("process : " + flag++);
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				content = getContent(json.getString("href"));
				json.put("content", content);
				result.add(json.toString());
			} catch (Exception e) {
				log.error("error - class:" + UrlGet.class.getName()
						+ " method- getOffer():" + json.getString("href"));
				log.error(e.toString());
			}

		}
		FileUtil.ListToFile(result,
				"C:/Users/强胜/Desktop/dataCrawler/申请方/offer/ori_content.json");
	}

	private static String getContent(String url) {
		// TODO Auto-generated method stub
		String res = "";
		Document doc = Jsoup.parse(CrawlerUtil.httpsRequest(url));
		res = doc.getElementById("content").getElementsByClass("box").get(0)
				.html();
		return res;
	}

	private static void getUrl() {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("高GPA", "https://www.applysquare.com/zh-cn/case/?q=tag:高GPA");
		map.put("中等GPA", "https://www.applysquare.com/zh-cn/case/?q=tag:中等GPA");
		map.put("低GPA", "https://www.applysquare.com/zh-cn/case/?q=tag:低GPA");
		map.put("高语言成绩", "https://www.applysquare.com/zh-cn/case/?q=tag:高语言成绩");
		map.put("中等语言成绩",
				"https://www.applysquare.com/zh-cn/case/?q=tag:中等语言成绩");
		map.put("低语言成绩", "https://www.applysquare.com/zh-cn/case/?q=tag:低语言成绩");
		map.put("国外牛推", "https://www.applysquare.com/zh-cn/case/?q=tag:国外牛推");
		map.put("国内牛推", "https://www.applysquare.com/zh-cn/case/?q=tag:国内牛推");
		map.put("无牛推", "https://www.applysquare.com/zh-cn/case/?q=tag:无牛推");
		map.put("海外交换", "https://www.applysquare.com/zh-cn/case/?q=tag:海外交换");
		map.put("无海外交换", "https://www.applysquare.com/zh-cn/case/?q=tag:无海外交换");
		map.put("丰富科研经历",
				"https://www.applysquare.com/zh-cn/case/?q=tag:丰富科研经历");
		map.put("无科研经历", "https://www.applysquare.com/zh-cn/case/?q=tag:无科研经历");
		map.put("牛paper",
				"https://www.applysquare.com/zh-cn/case/?q=tag:牛paper");
		map.put("多paper",
				"https://www.applysquare.com/zh-cn/case/?q=tag:多paper");
		map.put("水paper",
				"https://www.applysquare.com/zh-cn/case/?q=tag:水paper");
		map.put("无paper",
				"https://www.applysquare.com/zh-cn/case/?q=tag:无paper");
		map.put("高质量套磁", "https://www.applysquare.com/zh-cn/case/?q=tag:高质量套磁");
		map.put("无套磁", "https://www.applysquare.com/zh-cn/case/?q=tag:无套磁");
		map.put("横扫", "https://www.applysquare.com/zh-cn/case/?q=tag:横扫");
		map.put("牛offer",
				"https://www.applysquare.com/zh-cn/case/?q=tag:牛offer");
		map.put("小offer",
				"https://www.applysquare.com/zh-cn/case/?q=tag:小offer");
		map.put("AD狂", "https://www.applysquare.com/zh-cn/case/?q=tag:AD狂");
		map.put("waiting list",
				"https://www.applysquare.com/zh-cn/case/?q=tag:waiting%20list");
		map.put("全悲剧", "https://www.applysquare.com/zh-cn/case/?q=tag:全悲剧");
		map.put("双飞", "https://www.applysquare.com/zh-cn/case/?q=tag:双飞");
		map.put("曲线救国", "https://www.applysquare.com/zh-cn/case/?q=tag:曲线救国");
		map.put("海外本科", "https://www.applysquare.com/zh-cn/case/?q=tag:海外本科");
		map.put("海外硕士", "https://www.applysquare.com/zh-cn/case/?q=tag:海外硕士");
		map.put("双非申请（非211非985）",
				"https://www.applysquare.com/zh-cn/case/?q=tag:双非申请（非211非985）");
		map.put("经验分享", "https://www.applysquare.com/zh-cn/case/?q=tag:经验分享");

		List<String> urlList = new ArrayList<String>();
		for (String xx : map.keySet()) {
			try {
				urlList.addAll(get(xx, map.get(xx)));
			} catch (Exception e) {
				log.error("error - " + UrlGet.class.getName()
						+ ", when execute :" + xx);
				log.error(e.toString());
			}
		}
		FileUtil.ListToFile(urlList,
				"C:/Users/强胜/Desktop/dataCrawler/申请方/offer/url.json");

	}

	private static List<String> get(String tag, String url) {
		// TODO Auto-generated method stub
		List<String> result = new ArrayList<String>();
		// 发送一次请求解析页面获取每个标签下的offer的页数
		int pages = 1;
		pages = getPages(url);
		String eachUrl = "";
		List<String> tmpList = new ArrayList<String>();
		for (int i = 1; i < pages + 1; i++) {
			log.info(tag + ": page: " + i);
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			eachUrl = url + "&page=" + i;
			tmpList = parse(tag, eachUrl);
			result.addAll(tmpList);
		}
		return result;
	}

	private static List<String> parse(String tag, String url) {
		// TODO Auto-generated method stub
		List<String> res = new ArrayList<String>();
		String response = "";
		response = CrawlerUtil.httpsRequest(url);
		Document doc = Jsoup.parse(response);
		Elements eles = doc.getElementById("main").getElementsByClass(
				"search-result");
		String major = "";
		String degree = "";
		int year = -1;
		String tmp = "";
		String summary = "";
		String href = "";
		String backgroup = "";
		String result = "";
		for (Element ele : eles) {
			try {
				JSONObject json = new JSONObject();
				major = ele.getElementsByTag("h3").get(0).text();

				try {
					tmp = ele.getElementsByTag("i").get(0).text();
					degree = tmp.split(",")[0].replace("-", "").trim();
					year = Integer.parseInt(tmp.split(",")[1].trim());
				} catch (Exception e) {
				}

				summary = ele.getElementsByTag("dd").get(0).text();
				backgroup = ele.getElementsByTag("dd").get(1).text();
				result = ele.getElementsByTag("dd").get(2).text();
				href = ele.getElementsByTag("a").first().attr("href")
						.toString();
				json.put("tag", "tag");
				json.put("major", major);
				json.put("degree", degree);

				json.put("summary", summary);
				json.put("backgroup", backgroup);
				json.put("result", result);
				json.put("href", "https://www.applysquare.com" + href);
				json.put("year", year);
				res.add(json.toString());
			} catch (Exception e) {
				log.error("error - class:" + UrlGet.class.getName()
						+ " method- parse():" + tag + ":" + url);
				log.error(e.toString());

			}

		}
		return res;
	}

	private static int getPages(String url) {
		// TODO Auto-generated method stub
		int ires = 1;
		String response = "";
		response = CrawlerUtil.httpsRequest(url);
		Document doc = Jsoup.parse(response);
		Element ele = doc.getElementById("main");
		ele.getElementsByTag("p").text();
		ires = Integer.parseInt(ele.getElementsByTag("p").text().split("/")[1]
				.replaceAll("\\D", "").toString());
		return ires;

	}

	private static void test() {
		// TODO Auto-generated method stub
		List<JSONObject> lsit = FileUtil.FileToJsonList(
				"H:/独立说/backup/facultytype1022.json", "utf-8");

		for (JSONObject json : lsit) {
			String id = json.getString("id");
			System.out.println("map.put(\"" + json.getString("name_chinese")
					+ "\"," + id + ");");
		}
	}

}
