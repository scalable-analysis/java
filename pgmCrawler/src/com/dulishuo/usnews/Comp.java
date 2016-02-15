package com.dulishuo.usnews;

import java.util.*;

import com.dulishuo.util.FileUtil;

public class Comp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> xx = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/usnews/schoolurl22.txt");
		List<String> oo = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/usnews/url.txt");
		List<String> fail = new ArrayList<String>();
		
		for(String jj : xx){
			if(!oo.contains(jj))
				fail.add(jj);
		}
		FileUtil.ListToFile(fail, "C:/Users/强胜/Desktop/dataCrawler/usnews/fail.txt");
		System.out.println("__end__");
			
		
	}

}
