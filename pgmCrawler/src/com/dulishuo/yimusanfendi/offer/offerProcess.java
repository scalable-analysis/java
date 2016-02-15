package com.dulishuo.yimusanfendi.offer;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sf.json.JSONObject;

import com.dulishuo.util.CrawlerUtil;
import com.dulishuo.util.FileUtil;

public class offerProcess {
	static Pattern ptn = Pattern.compile("GPA[\\S\\s]*?\\:");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//toJson();
		//guilei();
		//hebing();
		//greToefl();
		//test();
		//getIns();
		//getDepartment();
		//getSchId();
		//getSchIdFail();
		fieldOth();
		//rank();
		
	}
	
	private static void rank() {
		// TODO Auto-generated method stub
		List<JSONObject> offer = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerRes22.json", "utf-8");
		List<JSONObject> schRank = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/QS/json/uniRank.json", "utf-8");
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(JSONObject json : schRank){
			if(!json.getString("value").contains("-") && !json.getString("value").equals(""))
				map.put(json.getInt("institute_id"), Integer.parseInt(json.getString("value")));
		}
		
		List<JSONObject> fac = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/facType/facType.json", "utf-8");
		Map<Integer,String> facMap = new HashMap<Integer,String>();
		for(JSONObject json : fac){
			facMap.put(json.getInt("id"), json.getString("name_chinese"));
		}
		
		List<JSONObject> facType = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/fac/facRank4.json", "utf-8");
		Map<String,Map<Integer,Integer>> rankMap = new HashMap<String,Map<Integer,Integer>>();
		for(JSONObject json : facType){
			Map<Integer,Integer> tmp ;
			if(rankMap.containsKey(json.getString("name_chinese")))
				tmp = rankMap.get(json.getString("name_chinese"));
			else
				tmp = new HashMap<Integer,Integer>();
			tmp.put(json.getInt("institute_id"),json.getInt("value"));
			rankMap.put(json.getString("name_chinese"), tmp);
		}
		
		
		List<String> res = new ArrayList<String>();
		
		for(JSONObject json : offer){
			json.put("school_rank", map.get(json.getInt("institute_id")));
			Map<Integer,Integer> tmp = rankMap.get(facMap.get(json.getInt("department_type")));
			if(tmp.containsKey(json.getInt("institute_id")))
				json.put("program_rank", tmp.get(json.getInt("institute_id")));
			res.add(json.toString());
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerRes33.json");
		System.out.println("____Exit________");
	}

	private static void fieldOth() {
		// TODO Auto-generated method stub
		List<JSONObject> offer = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerRes11.json", "utf-8");
		int count = 22503;
		List<String> res = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		for(JSONObject json : offer){
			json.put("id", count++);
			json.remove("backg");
			json.put("school", "");
			if(json.containsKey("uni")){
				json.put("school", json.getString("uni"));
				json.remove("uni");
			}
				
			json.put("year", "20"+json.getString("year"));
			json.put("from", "yimusanfendi");
			JSONObject toefl = new JSONObject();
			toefl.put("total", json.getString("toefl"));
			toefl.put("reading", "");
			toefl.put("speaking", "");
			toefl.put("writing", "");
			toefl.put("reading", "");
			toefl.put("other", null);
			
			JSONObject gre = new JSONObject();
			gre.put("total", json.getString("gre"));
			gre.put("aw", "");
			gre.put("q", "");
			gre.put("v", "");
			gre.put("other", null);
			
			
			json.put("gre", gre);
			json.put("toefl", toefl);
			res.add(json.toString());
			
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerRes22.json");
		System.out.println("____Exit________");
	}

	private static void getSchId() {
		// TODO Auto-generated method stub
		List<JSONObject> offer55 = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offer55.json", "utf-8");
		
		List<String> uni = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/uni.txt");
		Map<String,String> map = new HashMap<String,String>();
		List<String> fail = new ArrayList<String>();
		List<String> res = new ArrayList<String>();
		for(String xx : uni){
			System.out.println("process____"+flag++);
			if(xx.indexOf("!") != -1){
				map.put(xx.split("!")[1], xx.split("!")[0]);
			}else{
				if(getIns(xx) != -1)
					map.put(xx, String.valueOf(getIns(xx)));
				else
					fail.add(xx);
			}
		}
		for(JSONObject json : offer55){
			if(json.containsKey("uni")&&map.containsKey(json.getString("uni")))
				json.put("institute_id", map.get(json.getString("uni")));
			res.add(json.toString());
		}
		
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerTmp1.json");
		FileUtil.ListToFile(fail, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/failUni.txt");
		System.out.println("____Exit________");
	}
	
	private static void getSchIdFail() {
		// TODO Auto-generated method stub
		List<JSONObject> offer55 = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerTmp1.json", "utf-8");
		
		List<String> uni = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/failUni.txt");
		Map<String,String> map = new HashMap<String,String>();
		List<String> fail = new ArrayList<String>();
		List<String> res = new ArrayList<String>();
		for(String xx : uni){
			System.out.println("process____"+flag++);
			if(xx.indexOf("!") != -1){
				map.put(xx.split("!")[1], xx.split("!")[0]);
			}
		}
		for(JSONObject json : offer55){
			if(json.containsKey("uni")&&!json.containsKey("institute_id")&&map.containsKey(json.getString("uni"))){
				String xx = map.get(json.getString("uni"));
				int size = xx.length()/3;
				for(int i = 0 ; i < size ; i++){
					json.put("institute_id", Integer.parseInt(xx.substring(i*3, i*3+3)));
					res.add(json.toString());
				}
			}
		}
		
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerTmp2.json");
		System.out.println("____Exit________");
	}
	
	static int flag = 1;
	private static int getIns(String str) {
		// TODO Auto-generated method stub
		int res = -1;
		String hehe = "";
		
		String response = null;
		try {
			response = CrawlerUtil.getSchool(str);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!response.equals("-1")){
			JSONObject rst  = JSONObject.fromObject(response);
			 if(rst.containsKey("property-dict")){
				 if(rst.getString("property-dict")!=null && !rst.getString("property-dict").equals("{}")){
					 //保留字段
		        	 JSONObject dict = rst.getJSONObject("property-dict");
		        	 if(dict.containsKey("school")){
		        		 //匹配到的school的id，如果有多个，则是数组[123,345]
		        		 String school  = dict.get("school").toString().replace("[", "").replace("]", "");
		 				if(school.indexOf(",") == -1){
		 					if(school.length()>1)
		 						res = Integer.parseInt(school);
		 				}else if(school.substring(0, 3).equals(school.substring(4, 7))){
		 					res = Integer.parseInt(school.substring(0, 3));
		 				}	        		 
		        	 }
				 }
			 }
		}
			
		return res;
	}

	private static void getDepartment() {
		// TODO Auto-generated method stub
		List<JSONObject> offer = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerTmp2.json", "utf-8");
		
		List<String> res = new ArrayList<String>();
		for(JSONObject json : offer){
			json.put("origin_id",json.getInt("id"));
			
			if(json.containsKey("institute_id") && json.getString("institute_id").length() == 3){
				if(json.containsKey("fac") && getDep(json.getString("fac"))!= -1){
					json.put("department_type", getDep(json.getString("fac")));
					json.put("institute_id", Integer.parseInt(json.getString("institute_id")));
					if(json.containsKey("result"))
						json.put("result", getResult(json.getString("result")));
					res.add(json.toString());
				}
			}
			if(json.containsKey("institute_id") && json.getString("institute_id").length() > 3){
				if(json.containsKey("fac") && getDep(json.getString("fac"))!= -1){
					json.put("department_type", getDep(json.getString("fac")));
					int size = json.getString("institute_id").length()/3;
					String ins = json.getString("institute_id");
					for(int i = 0 ; i < size ; i++){
						if(json.containsKey("result"))
							json.put("result", getResult(json.getString("result")));
						json.put("institute_id", Integer.parseInt(ins.substring(i*3, i*3+3)));
						res.add(json.toString());
					}
				}
			}
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offerRes11.json");
		System.out.println("____Exit________");
	}

	private static int getResult(String str){
		if(str.equals("WaitingList"))
			return 0;
		else if(str.equals("Rej"))
			return 3;
		else if(str.equals("AD小奖"))
			return 4;
		else if(str.equals("Offer"))
			return 1;
		else if(str.equals("AD无奖"))
			return 2;
		else
			return 0;
	}
	private static int getDep(String str) {
		// TODO Auto-generated method stub
		int res = -1;
		
		if(str.equals("Stat/Biostat"))
			return 39;
		else if(str.equals("Material"))
			return 10;
		else if(str.equals("BioInfo"))
			return -1;
		else if(str.equals("Math/AppliedMath"))
			return 37;
		else if(str.equals("ME"))
			return 6;
		else if(str.equals("MIS"))
			return 52;
		else if(str.equals("LiberalArts"))
			return -1;
		else if(str.equals("Econ/Biz"))
			return 27;
		else if(str.equals("BME"))
			return -1;
		else if(str.equals("CivilEng"))
			return 45;
		else if(str.equals("Edu"))
			return 36;
		else if(str.equals("Physics"))
			return 33;
		else if(str.equals("Earth"))
			return 44;
		else if(str.equals("Envir"))
			return 19;
		else if(str.equals("CS"))
			return 38;
		else if(str.equals("Other"))
			return -1;
		else if(str.equals("IEOR"))
			return 57;
		else if(str.equals("CE"))
			return 5;
		else if(str.equals("MFE/Fin/FinMath"))
			return -1;
		else if(str.equals("Chem/CEng"))
			return -1;
		else if(str.equals("EE"))
			return 40;
		else if(str.equals("DataScience/Analytics"))
			return -1;
		else if(str.equals("Bio"))
			return 14;
		else if(str.equals("CIS"))
			return -1;
		else
			return -1;

	}

	private static void getIns() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("", "utf-8");
	}

	private static void hebing() {
		// TODO Auto-generated method stub
		List<JSONObject> offer33 = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offer33.json", "utf-8");
		List<JSONObject> offer44 = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offer44.json", "utf-8");
		
		List<String> res = new ArrayList<String>();
		Set<Integer> set = new HashSet<Integer>(); 
		for(JSONObject json : offer44){
			set.add(json.getInt("id"));
			res.add(json.toString());
		}
		for(JSONObject json : offer33){
			if(!set.contains(json.getInt("id")))
				res.add(json.toString());
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offer55.json");
		System.out.println("_____exit____");
	}

	private static void test() {
		// TODO Auto-generated method stub
		List<JSONObject> offer = FileUtil.FileToJsonList("C:/Users/强胜/Downloads/offer.json", "utf-8");
		List<String> res = new ArrayList<String>();
		int count = 1;
		for(JSONObject json : offer){
			json.put("id", count++);
			res.add(json.toString());
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offer.json");
		System.out.println("_____exit____");
	}

	private static void greToefl() {
		// TODO Auto-generated method stub
		List<JSONObject> offer = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offer11.json", "utf-8");
		Set<String> set = new HashSet<String>();
		int count = 1;
		List<String> res = new ArrayList<String>();
		System.out.println("______start");
		for(JSONObject json : offer){
			
			if(json.containsKey("gre") && !json.getString("gre").equals("-1")){
				if(!getGre(json.getString("gre")).equals("-1")){
					json.put("gre", getGre(json.getString("gre")));
					if(json.containsKey("toefl") && !json.getString("toefl").equals("-1")){
						if(!getToefl(json.getString("toefl")).equals("-1")){
							json.put("toefl", getToefl(json.getString("toefl")));
							if(!json.getString("gpa").equals("-1")){
								res.add(json.toString());
							}
						}
					}
				}
			}
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offer33.json");
		System.out.println("___Exit_________");
	}

	static Pattern ptnT = Pattern.compile("(\\d{3})|([4-9][0-9])");
	private static String getToefl(String str){
		Matcher mth = ptnT.matcher(str);
		String result = "-1";
		if(mth.find())
			result = mth.group();
		
		
		return result;
	}
	
	static Pattern ptnG = Pattern.compile("([3][0-3][0-9])|([2][0-9][0-9])");
	private static String getGre(String str){
		Matcher mth = ptnG.matcher(str);
		String result = "-1";
		if(mth.find())
			result = mth.group();
		return result;
	}
	
	private static void guilei() {
		// TODO Auto-generated method stub
		List<JSONObject> offer = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offer55.json", "utf-8");
		Set<String> set = new HashSet<String>();
		
		for(JSONObject json : offer){
			if(json.containsKey("uni"))
				set.add(json.getString("uni"));
		}
		System.out.println("______start");
		List<String> res = new ArrayList<String>();
		for(String xx : set)
			res.add(xx);
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/uni.txt");;
		System.out.println("_________end");
	}

	private static void toJson() {
		// TODO Auto-generated method stub
		List<JSONObject> offer = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offer.json", "utf-8");
		
		List<String> res = new ArrayList<String>();
		int count = 1;
		for(JSONObject json : offer){
			System.out.println("process___"+count++);
			JSONObject jo = new JSONObject();
			Document html;
			if(json.containsKey("background"))
				html = Jsoup.parse(json.getString("background"));
			else 
				continue;
			html = Jsoup.parse(json.getString("content"));
			
			Elements lis = html.getElementsByTag("li");
			//System.out.println(lis.size());
			
			try{
				
			String tmp = lis.get(1).text();
			String gpa = "-1";
			Matcher mth = ptn.matcher(tmp);
			if(mth.find()){
				if(!gpa(mth.group()).equals("-1"))
					gpa = gpa(mth.group());
			}
			String toefl = "-1";
			String field= "";
			String text = "";
			field = lis.get(3).getElementsByTag("b").get(0).text();
			text = lis.get(3).text().replace(field, "");
			if(text.trim().length() > 2)
				toefl = text.trim();
			
			String gre = "-1";
			field = lis.get(4).getElementsByTag("b").get(0).text();
			text = lis.get(4).text().replace(field, "");
			if(text.trim().length() > 2)
				gre = text.trim();
			
			String gresub = "-1";
			field = lis.get(5).getElementsByTag("b").get(0).text();
			text = lis.get(5).text().replace(field, "");
			if(text.trim().length() > 2)
				gresub = text.trim();
			
			String backg = "";
			field = lis.get(6).getElementsByTag("b").get(0).text();
			backg = lis.get(6).text().replace(field, "");
			
			String country = "";
			field = lis.get(8).getElementsByTag("b").get(0).text();
			country = lis.get(8).text().replace(field, "");
			
			jo.put("country", country);
			jo.put("backg", backg);
			jo.put("gresub", gresub);
			jo.put("gre", gre);
			jo.put("gpa", gpa);
			jo.put("toefl", toefl);
			
			
			}catch(Exception e){
			}
			
				
			String mark = json.getString("mark");
			String[] parts = mark.split("]");
			
			try{
				String year = parts[0].replace("[", "").substring(0, 2);
				jo.put("year", year);
			}catch(Exception e){
			}
			
			try{
				String date = parts[2].substring(3,13);
				jo.put("date", date);
			}catch(Exception e){
			}
			try{
				String term = parts[0].replace("[", "").split("\\.")[0].substring(2);
				jo.put("term", term);
			}catch(Exception e){
			}
			try{
				String degree = parts[0].replace("[", "").split("\\.")[1];
				jo.put("degree", degree);
			}catch(Exception e){
				
			}
			try{
				String result = parts[0].replace("[", "").split("\\.")[2];
				jo.put("result", result);
			}catch(Exception e){
			}
			
			try{
				String fac = parts[1].replace("[", "").split("@")[0];
				jo.put("fac", fac);
			}catch(Exception e){
			}
			try{
				String uni = parts[1].replace("[", "").split("@")[1];
				jo.put("uni", uni);
			}catch(Exception e){
			}
			jo.put("id", json.getInt("id"));
			res.add(jo.toString());
			
			
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/一亩三分地/data/offer/offer22.json");
		System.out.println("_________---exit---________");
	}

	public static String gpa(String str){
		String rst = "-1" ;
		
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
			if(rst.length()>4)
			rst=rst.substring(0,3);
		}else{
			Matcher matcher2 = ptn2.matcher(str);
			if(matcher2.find()){
				rst = matcher2.group().substring(0,matcher2.group().indexOf("/"));
				rst = String.valueOf(Double.parseDouble(rst)*0.8);
				if(rst.length()>4)
				rst=rst.substring(0,3);
			}else{
				Matcher matcher3 = ptn3.matcher(str);
				if(matcher3.find()){
					rst = matcher3.group().substring(0,matcher3.group().indexOf("/"));
					rst = String.valueOf(Math.min((Double.parseDouble(rst)-95)/10+4.0,4.0));
					if(rst.length()>4)
					rst=rst.substring(0,3);
				}
				else{
					Matcher matcher4 = ptn4.matcher(str);
					if(matcher4.find()){
						rst = matcher4.group();
						if(rst.length()>4)
						rst=rst.substring(0,3);
					}
					else{
						Matcher matcher5 = ptn5.matcher(str);
						if(matcher5.find()){
							rst = matcher5.group();
							rst = String.valueOf(Math.min((Double.parseDouble(rst)-95)/10+4.0,4.0));
							if(rst.length()>4)
							rst=rst.substring(0,3);
						}
					}
				}
			}
		}
		
		return rst;
	}

}
