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

public class test07162 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader brSucc = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/success.txt")));
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/success.txt"));
		
		Set<String> succ = new HashSet<String>();
		Set<String> names = new HashSet<String>();
		
		String tmp = "";
		while((tmp = brSucc.readLine()) != null){
			JSONObject json = JSONObject.fromObject(tmp);
			String id = json.getString("id");
			System.out.println(id);
			if(!names.contains(id)){
				names.add(id);
				succ.add(tmp);
			}else{
				System.out.println("-----");
			}
			
		}
		
		for(String oo : succ){
			bw.write(oo);
			bw.newLine();
			bw.flush();
		}
		
		bw.close();
		System.out.println("----------------end--------------");
	}

}
