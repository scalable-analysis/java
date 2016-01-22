package com.dulishuo.test;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class test082622 {
	static int i = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . ");
		
		test();
		
		System.out.println("end . . . ");
	}
	private static void test() {
		// TODO Auto-generated method stub
		List<Integer> list= new ArrayList<Integer>();
		list.add(3);
		list.add(5);
		list.add(4);
		Collections.sort(list,
				new Comparator(){ 
			public int compare(int o1, int o2){
				return (o1-o2);
			}

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return 0;
			}

			
		 
		 }) ;
		
		for(int xx : list)
			System.out.println(xx);

	}		
}
