package com.dulishuo.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int k=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/yyyy.txt")));
		BufferedWriter writerFail = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/fail.txt"));
		
		
		List<String> all = new ArrayList<String>();
		//Pattern pn = Pattern.compile("&offset=[\\d]{1,5}&");
		
		String each_line = "";
		while((each_line = br.readLine()) != null){
			//System.out.println(each_line.substring(each_line.indexOf("&offset=")+8, each_line.indexOf("&2waynocompress=")));
			all.add(each_line.substring(each_line.indexOf("&offset=")+8, each_line.indexOf("&2waynocompress=")));			
		}
		System.out.println("--all--size--"+all.size());
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(int i = 0 ; i < all.size(); i++){
			String tmp = String.valueOf(all.get(i));
			if(map.containsKey(tmp)){
				map.put(tmp, map.get(tmp)+1);
			}else{
				map.put(tmp, 1);
			}
		}
		for(Entry<String, Integer> entry : map.entrySet()){
			if(entry.getValue() < 10){
				writerFail.write(entry.getKey());
				writerFail.newLine();
				System.out.println("k--------"+(k++));
			}
		}
		System.out.println("-----分割线-----------");
		for(int i = 0 ; i < 19560 ; i +=10){
			
			if(!all.contains(String.valueOf(i))){
				writerFail.write(String.valueOf(i));
				writerFail.newLine();
				System.out.println("k--------"+(k++));
			}
		}
		writerFail.close();
		System.out.println("-----end-----");
	}

}
