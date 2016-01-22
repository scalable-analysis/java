package com.dulishuo.statistics;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class ChaseDream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//商学院
		List<String> list = new ArrayList<String>();
		for(int i = 1 ; i < 100 ; i++){
			String url = "http://forum.chasedream.com/forum.php?mod=forumdisplay&fid=43&orderby=dateline&filter=author&orderby=dateline&page="+i;
			System.out.println("process___"+i);
			String res = Util.httpRequest(url);
			Document html = Jsoup.parse(res);
			Element ele = html.getElementById("threadlist");
			Elements sibling = ele.getElementById("separatorline").siblingElements();
			int size = sibling.size();
			int start = ele.getElementById("separatorline").elementSiblingIndex()+1;
			for(int j = start ; j < size ; j++){
				Element each = sibling.get(j);
				
				String link = each.getElementsByClass("icn").get(0).select("a").attr("href").toString();
				String date = each.getElementsByClass("by").get(0).getElementsByTag("em").get(0).getElementsByTag("span").get(0).text().replace("&nbsp;", "");			
				String cmt = each.getElementsByClass("num").get(0).getElementsByTag("a").first().text();
				String see = each.getElementsByClass("num").get(0).getElementsByTag("em").first().text();
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("link", link);
				map.put("date", date);
				map.put("cmt", cmt);
				map.put("see", see);
				map.put("type", "TOEFL");
				map.put("flag", "TOEFL CBT与笔考专区");
				JSONObject json = JSONObject.fromObject(map);
				list.add(json.toString());
			}
		}
		
		FileUtil.ListToFile(list, "C:/Users/强胜/Desktop/杂项/chaseDream/TOEFL/TOEFL.txt",true);
		FileUtil.ListToFile(list, "C:/Users/强胜/Desktop/杂项/chaseDream/TOEFL/TOEFL CBT与笔考专区.txt");
		System.out.println("_____end_______");
		
	}

}
