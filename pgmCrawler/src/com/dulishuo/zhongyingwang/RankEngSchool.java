package com.dulishuo.zhongyingwang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

public class RankEngSchool {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/中英网/rankEngSch.txt")));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/dataCrawler/中英网/result.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/强胜/Desktop/dataCrawler/中英网/rankEngSchh.txt"));
		
		List<String> set = new ArrayList<String>();
		List<String> ss = new ArrayList<String>();
		Map<String,String> map = new HashMap<String,String>();
		String each_line = "";
		while((each_line = br2.readLine()) !=null){
			map.put(each_line.split("\t")[0], each_line.split("\t")[1]);
		}
		br2.close();
		
		String each = "";
		while((each= br1.readLine()) !=null){
			bw.write(each+"\t"+map.get(each.split("\t")[1]));
			bw.newLine();
			bw.flush();
		}
		br1.close();
		System.out.println("___________end__________");
	}

}
