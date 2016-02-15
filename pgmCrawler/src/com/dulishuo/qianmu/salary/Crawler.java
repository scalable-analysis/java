package com.dulishuo.qianmu.salary;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dulishuo.qianmu.Util;
import com.dulishuo.util.FileUtil;

public class Crawler {

	static int id = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//crawler();
		//extra();
	}
	private static void extra() {
		// TODO Auto-generated method stub
		List<String> list = FileUtil.FileToList("C:/Users/强胜/Desktop/tt.html");
		List<String> res = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		for(String xx : list){
			sb.append(xx.trim());
		}
		
		Document doc = Jsoup.parse(sb.toString());
		Elements trs = doc.getElementsByTag("tr");
		System.out.println(trs.size());
		
		List<String> mba = new ArrayList<String>();
		List<String> master = new ArrayList<String>();
		List<String> phd = new ArrayList<String>();
		List<String> jd = new ArrayList<String>();
		
		int flag1 = 1;
		int flag2 = 1;
		int flag3 = 1;
		int flag4 = 1;
		int pre1 = 1;
		int pre2 = 1;
		int pre3 = 1;
		int pre4 = 1;
		int size = 1;
		for(int i = 2 ; i < trs.size() ; i +=10){
			int rank = Integer.parseInt(trs.get(i).getElementsByTag("td").get(0).text().toString().replace("(tie)", "").trim());
			String name = trs.get(i).getElementsByTag("td").get(1).text();
			String degree = trs.get(i+2).getElementsByTag("td").get(1).text();
			String early = trs.get(i+4).getElementsByTag("td").get(1).text();
			String mid = trs.get(i+5).getElementsByTag("td").get(1).text();
			
			JSONObject json = new JSONObject();
			json.put("排名", rank);
			json.put("name", name);
			json.put("type", "学校综合");
			json.put("year", 2016);
			json.put("degree", "研究生");
			json.put("early_salary", early);
			json.put("mid_salary", mid);
			res.add(json.toString());
			 
			
			if(degree.equals("PhD")){
				json.put("type", "phd项目");
				if(pre1 == rank){
					json.put("排名", flag1);
				}else{
					size = phd.size()+1;
					json.put("排名", size);
					pre1 = rank;
					flag1 = size;
				}
				phd.add(json.toString());
			}else if(degree.equals("MBA")){
				json.put("type", "MBA项目");
				if(pre2 == rank){
					json.put("排名", flag2);
				}else{
					size = mba.size()+1;
					json.put("排名", size);
					pre2 = rank;
					flag2 = size;
				}
				mba.add(json.toString());
			}else if(degree.equals("JD")){
				json.put("type", "JD项目");
				if(pre3 == rank){
					json.put("排名", flag3);
				}else{
					size = jd.size()+1;
					json.put("排名", size);
					pre3 = rank;
					flag3 = size;
				}
				jd.add(json.toString());
			}else if(degree.equals("Master's")){
				json.put("type", "硕士项目");
				if(pre4 == rank){
					json.put("排名", flag4);
				}else{
					size = master.size()+1;
					json.put("排名", size);
					pre4 = rank;
					flag4 = size;
				}
				master.add(json.toString());
			}else{
				System.out.println(degree);
			}
			
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/迁木网/毕业生待遇排名/grad.json",true);
		FileUtil.ListToFile(phd, "C:/Users/强胜/Desktop/dataCrawler/迁木网/毕业生待遇排名/grad.json",true);
		FileUtil.ListToFile(master, "C:/Users/强胜/Desktop/dataCrawler/迁木网/毕业生待遇排名/grad.json",true);
		FileUtil.ListToFile(mba, "C:/Users/强胜/Desktop/dataCrawler/迁木网/毕业生待遇排名/grad.json",true);
		FileUtil.ListToFile(jd, "C:/Users/强胜/Desktop/dataCrawler/迁木网/毕业生待遇排名/grad.json",true);
		System.out.println("end.........");
	}
	private static void crawler() {
		// TODO Auto-generated method stub
		
		System.out.println("start.............");
		Map<String,String> listUrl = new HashMap<String,String>();
		List<String> res = new ArrayList<String>();
		
		//2016 - 本科
		listUrl.put("http://www.qianmu.org/2016PayScale美国本科毕业生工资排行","2016-本科-综合");
		listUrl.put("http://www.qianmu.org/2016PayScale商科专业本科薪水排行","2016-本科-商科");
		listUrl.put("http://www.qianmu.org/2016PayScale计算机专业本科薪水排行","2016-本科-计算机");
		listUrl.put("http://www.qianmu.org/2016PayScale物理与生命科学专业本科薪水排行","2016-本科-物理与生命科学");
		listUrl.put("http://www.qianmu.org/2016PayScale人文科学专业本科薪水排行","2016-本科-人文科学");
		listUrl.put("http://www.qianmu.org/2016PayScale工程专业本科薪水排行","2016-本科-工程");
		listUrl.put("http://www.qianmu.org/2016PayScale新闻与传媒专业本科薪水排行","2016-本科-新闻与媒体");
		listUrl.put("http://www.qianmu.org/2016PayScale艺术与设计专业本科薪水排行","2016-本科-艺术与设计");
		listUrl.put("http://www.qianmu.org/2016PayScale师范专业本科薪水排行","2016-本科-教育与师范");
		listUrl.put("http://www.qianmu.org/2016PayScale社会科学专业本科薪水排行","2016-本科-社会科学");
		
		//专业薪资排名
		listUrl.put("http://www.qianmu.org/2016PayScale美国本科专业薪水排行", "2016-本科专业");
		listUrl.put("http://www.qianmu.org/2016PayScale美国硕士专业薪水排行", "2016-硕士专业");
		listUrl.put("http://www.qianmu.org/2016PayScale美国PhD专业薪水排行", "2016-phD专业");
		listUrl.put("http://www.qianmu.org/2016PayScale美国MBA专业薪水排行", "2016-MBA专业");
		
		/*//2016-研究生
		listUrl.put("http://www.qianmu.org/2016PayScale美国研究生毕业生薪水排行总榜","2016-研究生-综合");
		listUrl.put("http://www.qianmu.org/2016PayScale美国硕士项目薪水排行","2016-研究生-硕士项目");
		listUrl.put("http://www.qianmu.org/2016PayScale美国研究生毕业生薪水排行总榜","2016-研究生-综合");
		listUrl.put("http://www.qianmu.org/2016PayScale美国研究生毕业生薪水排行总榜","2016-研究生-综合");
		listUrl.put("http://www.qianmu.org/2016PayScale美国研究生毕业生薪水排行总榜","2016-研究生-综合");*/
		
		//2015
		listUrl.put("http://www.qianmu.org/ranking/1529.htm","2015-本科-综合");
		listUrl.put("http://www.qianmu.org/2015PayScale商科专业本科薪水排行","2015-本科-商科");
		listUrl.put("http://www.qianmu.org/2015PayScale计算机专业本科薪水排行","2015-本科-计算机");
		listUrl.put("http://www.qianmu.org/2015PayScale物理与生命科学专业本科薪水排行","2015-本科-物理与生命科学");
		listUrl.put("http://www.qianmu.org/2015PayScale人文科学专业本科薪水排行","2015-本科-人文科学");
		listUrl.put("http://www.qianmu.org/2015PayScale工程专业本科薪水排行","2015-本科-工程");
		listUrl.put("http://www.qianmu.org/2015PayScale新闻与传媒专业本科薪水排行","2015-本科-新闻与媒体");
		listUrl.put("http://www.qianmu.org/2015PayScale艺术与设计专业本科薪水排行","2015-本科-艺术与设计");
		listUrl.put("http://www.qianmu.org/2015PayScale师范专业本科薪水排行","2015-本科-教育与师范");
		listUrl.put("http://www.qianmu.org/2015PayScale社会科学专业本科薪水排行","2015-本科-社会科学");
		
		int flag = 1;
		for(String url : listUrl.keySet()){
			System.out.println("process___"+flag++);
			String xx;
			if(url.contains("Scale"))
				 xx = url.split("Scale")[0]+"Scale"+URLEncoder.encode(url.split("Scale")[1]);
			else
				xx = url;
			String response = Util.crawler(xx);
			if(response.equals("-1"))
				continue;
			Document doc = Jsoup.parse(response);
			Element table = doc.getElementsByTag("table").get(0);
			Elements trs = table.getElementsByTag("tr");
			
			Elements first = trs.get(0).getElementsByTag("td");
			int size = first.size();
			List<String> key = new ArrayList<String>();
			for(int i = 0 ; i < size ; i++ )
				key.add(first.get(i).text());
			
			for(int i = 1 ; i < trs.size() ; i++){
				Elements tds = trs.get(i).getElementsByTag("td");
				JSONObject json = new JSONObject();
				json.put("id", id++);
				json.put("country", "USA");
				String[] value = listUrl.get(url).split("-");
				json.put("year", Integer.parseInt(value[0]));
				if(value.length == 2){
					json.put("type", value[1]);
				}else{
					json.put("type", value[2]);
					json.put("degree", value[1]);
				}
				
				for(int j = 0 ; j < tds.size() ; j++)
					json.put(key.get(j), tds.get(j).text().toString());
				
				res.add(json.toString());
			}
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/迁木网/毕业生待遇排名/origin.json");
		System.out.println("end.........");
	}

}
