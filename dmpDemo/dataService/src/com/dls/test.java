package com.dls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class test {
	public static void main(String[] args) throws IOException{
		
		/*String rst = "-1" ;
		String str = "3.3 专业第9";
		//匹配4分制
		String regex1 = "(([1-3](\\.[0-9]{1,2})?)|(4\\.0{1,2}))/(4(\\.0{1,2})?)";
		String regex4 = "[2-3](\\.[0-9]{1,2}?)";
		//匹配5分制
		String regex2 = "(([1-4](\\.[0-9]{1,2})?)|(5\\.0{1,2}))/(5(\\.0{1,2})?)";
		//匹配100分制
		String regex3 = "(([7-9][0-9](\\.[0-9]{1,2})?)|(100\\.0{1,2}))/(100(\\.0{1,2})?)";
		String regex5 = "[7-9][0-9](\\.[0-9]{1,2}?)";
		
		
		
		
		Pattern ptn1 = Pattern.compile(regex1);
		Pattern ptn2 = Pattern.compile(regex2);
		Pattern ptn3 = Pattern.compile(regex3);
		Pattern ptn4 = Pattern.compile(regex4);
		Pattern ptn5 = Pattern.compile(regex5);
		
		Matcher matcher1 = ptn1.matcher(str);
		
		if(matcher1.find()){
			rst = matcher1.group().substring(0,matcher1.group().indexOf("/"));
		}else{
			Matcher matcher2 = ptn2.matcher(str);
			if(matcher2.find()){
				rst = matcher2.group().substring(0,matcher2.group().indexOf("/"));
				rst = String.valueOf(Double.parseDouble(rst)*0.8);
			}else{
				Matcher matcher3 = ptn3.matcher(str);
				if(matcher3.find()){
					rst = matcher3.group().substring(0,matcher3.group().indexOf("/"));
					rst = String.valueOf(Math.min((Double.parseDouble(rst)-95)/10+4.0,4.0));
				}
				else{
					Matcher matcher4 = ptn4.matcher(str);
					if(matcher4.find()){
						rst = matcher4.group();
					}
					else{
						Matcher matcher5 = ptn5.matcher(str);
						if(matcher5.find()){
							rst = matcher3.group();
							rst = String.valueOf(Math.min((Double.parseDouble(rst)-95)/10+4.0,4.0));
						}
				}
				}
			}
		}
		
		System.out.println(rst);*/
		
		/*String result = "";
		String rst1 = "-1";
		String rst2 = "-1";
		String rst3 = "-1";
		String rst4 = "-1";
		String rst5 = "-1";
		
		int flag = 0;
		
		String str = "Overall: 102, R: 26 / L: 28 / S: 22 / W: 26";
		
		String regex1 = "Overall\\:[^\\d]*?(([1][0-2][0-9])|([1-9][0-9]))[^\\d]*?[\\,]?"; 
		String regex2 = "R\\:.*?[/]"; 
		String regex3 = "L\\:.*?[/]"; 
		String regex4 = "S\\:.*?[/]"; 
		String regex5 = "W\\:.*"; 
		String regex6 = "([1][0-2][0-9])|([1-9][0-9])";
		
		Pattern ptn1 = Pattern.compile(regex1);
		Pattern ptn2 = Pattern.compile(regex2);
		Pattern ptn3 = Pattern.compile(regex3);
		Pattern ptn4 = Pattern.compile(regex4);
		Pattern ptn5 = Pattern.compile(regex5);
		Pattern ptn6 = Pattern.compile(regex6);
		Matcher mth1 = ptn1.matcher(str);
		Matcher mth2 = ptn2.matcher(str);
		Matcher mth3 = ptn3.matcher(str);
		Matcher mth4 = ptn4.matcher(str);
		Matcher mth5 = ptn5.matcher(str);
		
		if(mth1.find()){
			rst1 = mth1.group();
			Matcher mth6 = ptn6.matcher(rst1);
			if(mth6.find()){
				rst1 = mth6.group();
			}
		}
		if(mth2.find()){
			
			rst2 = mth2.group();
			
			Matcher mth6 = ptn6.matcher(rst2);
			if(mth6.find()){
				rst2 = mth6.group();
				flag++;
			}else{
				rst2 = "-1";
			}
		}
		if(mth3.find()){
			rst3 = mth3.group();
			Matcher mth6 = ptn6.matcher(rst3);
			if(mth6.find()){
				rst3 = mth6.group();
				flag++;
			}
			else{
				rst3 = "-1";
			}
		}
		if(mth4.find()){
			rst4 = mth4.group();
			Matcher mth6 = ptn6.matcher(rst4);
			if(mth6.find()){
				rst4 = mth6.group();
				flag++;
			}
			else{
				rst4 = "-1";
			}
		}
		if(mth5.find()){
			rst5 = mth5.group();
			System.out.println(rst5);
			Matcher mth6 = ptn6.matcher(rst5);
			if(mth6.find()){
				rst5 = mth6.group();
				flag++;
			}
			else{
				rst5 = "-1";
			}
		}
		
		if(flag==4){
			System.out.println(Integer.parseInt(rst2)+Integer.parseInt(rst3)+Integer.parseInt(rst4)+Integer.parseInt(rst5));
		}
		
		System.out.println(rst2);
		System.out.println(rst3);
		System.out.println(rst4);
		System.out.println(rst5);
		
		if(rst1 != "-1"){
			result = "Overall: "+rst1+", R: "+rst2+" / L: "+rst3+" / S: "+rst4+" / W: "+rst5;
		}
		System.out.println("result: "+result);*/
		
	/*	String result = "-1";
		String rst1 = "-1";
		String rst2 = "-1";
		String rst3 = "-1";
		String rst4 = "-1";
		String rst5 = "-1";
		
		int flag = 0;
		
		String str = "R: / L: 7.5 / S: 6 / W: 5.5）";
		
		String regex1 = "Overall\\:.*?[\\,]"; 
		String regex2 = "R\\:.*?[/]"; 
		String regex3 = "L\\:.*?[/]"; 
		String regex4 = "S\\:.*?[/]"; 
		String regex5 = "W\\:.*"; 
		String regex6 = "([1-8](\\.[0-9]{1,2})?)|([9](\\.[0]{1,2}))";
		
		Pattern ptn1 = Pattern.compile(regex1);
		Pattern ptn2 = Pattern.compile(regex2);
		Pattern ptn3 = Pattern.compile(regex3);
		Pattern ptn4 = Pattern.compile(regex4);
		Pattern ptn5 = Pattern.compile(regex5);
		Pattern ptn6 = Pattern.compile(regex6);
		Matcher mth1 = ptn1.matcher(str);
		Matcher mth2 = ptn2.matcher(str);
		Matcher mth3 = ptn3.matcher(str);
		Matcher mth4 = ptn4.matcher(str);
		Matcher mth5 = ptn5.matcher(str);
		
		if(mth1.find()){
			rst1 = mth1.group();
			Matcher mth6 = ptn6.matcher(rst1);
			if(mth6.find()){
				rst1 = mth6.group();
			}
			else{
				rst1 = "-1";
			}
		}
		if(mth2.find()){
			
			rst2 = mth2.group();
			
			Matcher mth6 = ptn6.matcher(rst2);
			if(mth6.find()){
				rst2 = mth6.group();
				flag++;
			}else{
				rst2 = "-1";
			}
		}
		if(mth3.find()){
			rst3 = mth3.group();
			Matcher mth6 = ptn6.matcher(rst3);
			if(mth6.find()){
				rst3 = mth6.group();
				flag++;
			}
			else{
				rst3 = "-1";
			}
		}
		if(mth4.find()){
			rst4 = mth4.group();
			Matcher mth6 = ptn6.matcher(rst4);
			if(mth6.find()){
				rst4 = mth6.group();
				flag++;
			}
			else{
				rst4 = "-1";
			}
		}
		if(mth5.find()){
			rst5 = mth5.group();
			Matcher mth6 = ptn6.matcher(rst5);
			if(mth6.find()){
				rst5 = mth6.group();
				flag++;
			}
			else{
				rst5 = "-1";
			}
		}
		
		if(flag==4 && rst1 =="-1"){
			rst1 = String.valueOf((Double.parseDouble(rst2)+Double.parseDouble(rst3)+Double.parseDouble(rst4)+Double.parseDouble(rst5))/4);
		}
		
		System.out.println(rst2);
		System.out.println(rst3);
		System.out.println(rst4);
		System.out.println(rst5);
		
		if(rst1 != "-1" ){
			result = "Overall: "+rst1+", R: "+rst2+" / L: "+rst3+" / S: "+rst4+" / W: "+rst5;
		}
		System.out.println("result: "+result);*/
		
		
		
		//gre
		/*String result = "-1";
		String rst1 = "-1";
		String rst2 = "";
		String rst3 = "";
		String rst4 = "";
		
		int flag = 0;
		
		String str = "Overall: 319, V: / Q: / AW:";
		
		String regex1 = "Overall\\:.*?[\\,]"; 
		String regex2 = "V\\:.*?[/]"; 
		String regex3 = "Q\\:.*?[/]"; 
		String regex4 = "AW\\:.*"; 
		String regex6 = "[\\d]+([\\.][\\d]{1,2})?";
		
		Pattern ptn1 = Pattern.compile(regex1);
		Pattern ptn2 = Pattern.compile(regex2);
		Pattern ptn3 = Pattern.compile(regex3);
		Pattern ptn4 = Pattern.compile(regex4);
		Pattern ptn6 = Pattern.compile(regex6);
		Matcher mth1 = ptn1.matcher(str);
		Matcher mth2 = ptn2.matcher(str);
		Matcher mth3 = ptn3.matcher(str);
		Matcher mth4 = ptn4.matcher(str);
		
		if(mth1.find()){
			rst1 = mth1.group();
			Matcher mth6 = ptn6.matcher(rst1);
			if(mth6.find()){
				rst1 = mth6.group();
			}
			else{
				rst1 = "-1";
			}
		}
		if(mth2.find()){
			
			rst2 = mth2.group();
			
			Matcher mth6 = ptn6.matcher(rst2);
			if(mth6.find()){
				rst2 = mth6.group();
				flag++;
			}else{
				rst2 = "";
			}
		}
		if(mth3.find()){
			rst3 = mth3.group();
			Matcher mth6 = ptn6.matcher(rst3);
			if(mth6.find()){
				rst3 = mth6.group();
				flag++;
			}
			else{
				rst3 = "";
			}
		}
		if(mth4.find()){
			rst4 = mth4.group();
			Matcher mth6 = ptn6.matcher(rst4);
			if(mth6.find()){
				rst4 = mth6.group();
				
			}
			else{
				rst4 = "";
			}
		}
		
		if(flag==2 && rst1 =="-1"){
			rst1 = String.valueOf((Double.parseDouble(rst2)+Double.parseDouble(rst3)));
		}
		
		System.out.println(rst2);
		System.out.println(rst3);
		System.out.println(rst4);
		
		if(rst1 != "-1" ){
			result = "Overall: "+rst1+", V: "+rst2+" / Q: "+rst3+" / AW: "+rst4;
		}
		System.out.println("result: "+result);*/
		//Overall: 7, R: / L: / S: / W:  335
		/*String reg = "Overall\\:.*?[\\d]+[\\,]"; 
		String reg2 = "([1-8](\\.[0-9]{1,2})?)|([9](\\.[0]{1,2}))"; 
		String str = "Overall: hh6#7, R: / L: / S: / W:";
			
		Pattern pth = Pattern.compile(reg);
		Matcher mth = pth.matcher(str); 
		
		if(mth.find()){
			System.out.println("---"+mth.group());
			Pattern pth2 = Pattern.compile(reg2);
			Matcher mth2 = pth2.matcher(mth.group());
				if(mth2.find()){
					System.out.println("---"+mth2.group());
				}
		}*/
		String tt = "http://ukpass.prospects.ac.uk/pgsearch/UKPASSCourse;jsessionid=ca30a51edbab$A1$21$F?keyword=&type=Course&filter=&filter=m%2FFT&action=showdetails&offset=3740&2waynocompress=1&id=42967";
		int i = 3740;
		String yyy= "&offset="+i+"&";
		System.out.println(tt.contains(yyy));
	}
	
}
