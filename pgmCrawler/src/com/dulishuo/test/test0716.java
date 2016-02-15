package com.dulishuo.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import net.sf.json.JSONObject;

import com.dulishuo.util.CrawlerUtil;


public class test0716 {
	static List<String> fail = new ArrayList<String>();
	public static void main(String[] args) {
		System.out.println(test0716.class.getName()+" Start . . . ");
		long start = System.currentTimeMillis();
		
		test();
		
		long end = System.currentTimeMillis();
		System.out.println("End . . . use time : " + (end - start) + " ms . ");
	}
	private static void test() {
		// TODO Auto-generated method stub
		JSONObject jee = new JSONObject();
		//String con = "{"current-school":0,"internship":{"duration":0,"recommendation":0,"level":0},"competition":{"level":0},"toefl":{"total":1,"speaking":1},"GMAT":{"total":1,"writing":1},"gpa-trend-enum":0,"scholarship":{"level":0},"credential":{"credential":0},"gpa":1,"research":{"duration":0,"level":0,"achievement":0,"recommendation":0},"gre":{"total":1,"aw":1,"v":1},"ielts":{"total":1},"work":{"duration":0,"level":0,"recommendation":0},"activity":{"duration":0,"type":0}}";
		String url = "http://123.57.22.27:8000/assess_applier?condition="+URLDecoder.decode("{%22current-school%22:0,%22internship%22:{%22duration%22:0,%22recommendation%22:0,%22level%22:0},%22competition%22:{%22level%22:0},%22toefl%22:{%22total%22:1,%22speaking%22:1},%22GMAT%22:{%22total%22:1,%22writing%22:1},%22gpa-trend-enum%22:0,%22scholarship%22:{%22level%22:0},%22credential%22:{%22credential%22:0},%22gpa%22:1,%22research%22:{%22duration%22:0,%22level%22:0,%22achievement%22:0,%22recommendation%22:0},%22gre%22:{%22total%22:1,%22aw%22:1,%22v%22:1},%22ielts%22:{%22total%22:1},%22work%22:{%22duration%22:0,%22level%22:0,%22recommendation%22:0},%22activity%22:{%22duration%22:0,%22type%22:0}}")+"&major_type=accounting";
		String response = CrawlerUtil.httpRequest(url);
		System.out.println(response);
	}
}
