package com.dulishuo.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class test07161 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader brSucc = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/result/success.txt")));
		BufferedReader brUrl = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/result/url.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/result/failZ.txt"));
		
		List<String> succ = new ArrayList<String>();
		
		String tmp = "";
		while((tmp = brSucc.readLine()) != null){
			JSONObject json = JSONObject.fromObject(tmp);
			String id = json.getString("id");
			succ.add(id);
		}
		
		Pattern pn = Pattern.compile("&id=\\d+");
		String each_line = "";
		int i = 0;
		while((each_line = brUrl.readLine()) != null){
			
			Matcher mh = pn.matcher(each_line);
			if(mh.find()){
				String idT = mh.group().replace("&id=", "");
				if(!succ.contains(idT)){
					bw.write(each_line);
					bw.newLine();
					bw.flush();
					System.out.println("---"+(i++)+"---&id="+idT);
				}
			}
		}
		
		bw.close();
		System.out.println("----------------end--------------");
	}

}
