package com.dulishuo.rateMyProfessors;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String response = Util.httpRequest("http://www.ratemyprofessors.com/campusRatings.jsp?sid=399");
		Document html = Jsoup.parse(response);
		Element ele = html.getElementById("department-dropdown");
		
		for(Element each : ele.children()){
			System.out.println(each.attr("value"));
		}
		List<String>  xx = new ArrayList<String>();
		xx.add(response);
		FileUtil.ListToFile(xx, "C:/Users/强胜/Desktop/typrofessors.html");
		
		
		System.out.println("_______end______");
	}

}
