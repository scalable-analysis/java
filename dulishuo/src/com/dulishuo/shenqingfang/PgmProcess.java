package com.dulishuo.shenqingfang;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PgmProcess {
	static int count = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . \n");
		long start = System.currentTimeMillis();
		
		//old();
		//process1126();
		//addDeptype();
		//addDeadLine();
		//update();
		test();
		//hebing();
		//trans();
		//haha();
		//updateTitle();
		
		long end = System.currentTimeMillis();
		System.out.println("end use time : " + (end - start) + " ms .");
	}
	
	
	private static void updateTitle() {
		// TODO Auto-generated method stub
		HSSFSheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/圣诞/天通苑.xls", 0);
		
		Map<String , String> map = new HashMap<String , String>();
		
		
		
		for(int i = 0 ; i < sht.getLastRowNum()+1; i++)
			map.put(sht.getRow(i).getCell(0).toString(),sht.getRow(i).getCell(2).toString());
				
		List<String> titleList = FileUtil.FileToList("C:/Users/强胜/Desktop/pythontest/result.txt");
		
		for(String xx : titleList)
			map.put(xx.split("\t")[1], xx.split("\t")[0]);
		
		System.out.println("end :  read to map .");
		
		List<String> res = new ArrayList<String>();
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/addDeadLineFacId.json", "utf-8");
		int count = 1;
		for(JSONObject json : list){
			if(map.containsKey(json.getString("url"))){
				json.put("title", map.get(json.getString("url")));
				res.add(json.toString());
			}
				
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/updateTitle.json");
	}


	private static void haha() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/addTtitle1208.json", "utf-8");
		Set<String> set = new HashSet<String>();
		List<String> res = new ArrayList<String>();
		for(JSONObject json : list){
			if(json.getString("ttitle").length()<2){
				System.out.println(json.getString("title")+"\t"+json.get("institute_id"));
				System.out.println(json.getString("url"));
				set.add(json.getString("title"));
			}
		}
		for(JSONObject json : list){
			if(json.getString("ttitle").length()<2){
				if(!set.contains(json.getString("title")))
					System.out.println(json.getString("title"));
			}
		}
		/*System.out.println("read end . . . with size of set : "+set.size());
		String tmp = "";
		for(String xx : set){
			tmp = Util.trans(xx);
			System.out.println(xx+"\t"+tmp);
		}*/
	}


	private static void trans() {
		// TODO Auto-generated method stub
		HSSFSheet sheet = FileUtil.getExcelSht("C:/Users/强胜/Desktop/数据补全/program/program校对0904.xls", 0);
		Map<String , String> map = new HashMap<String , String>();
		for(int i = 0 ; i < sheet.getLastRowNum()+1 ; i++){
			try{
				map.put(sheet.getRow(i).getCell(1).toString().trim().toLowerCase(), sheet.getRow(i).getCell(2).toString().trim());
			}catch(Exception e){}
		}
		
		System.out.println("read to map end . . . ");
		
		
		List<String> res = new ArrayList<String>();
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/updateTitle.json", "utf-8");
		String tmp = "";
		int flag1 = 1;
		int flag2 = 1;
		int flag3 = 1;
		for(JSONObject json : list){
			if(json.containsKey("ttitle"))
				json.remove("ttitle");
			
				tmp = json.getString("title").toLowerCase();
				if(map.containsKey(tmp)){
					json.put("ttitle", map.get(tmp));
					flag1++;
				}
				else{
					try{
						if(tmp.subSequence(0, 6).equals("master")){
							tmp = "ms"+tmp.substring(6);
							if(map.containsKey(tmp)){
								json.put("ttitle", map.get(tmp));
								flag2++;
							}
						}
					}catch(Exception e){}
					
					try{
						 if(tmp.subSequence(0, 3).equals("phd")){
							tmp = "ms" + tmp.substring(3);
							if(map.containsKey(tmp)){
								json.put("ttitle", map.get(tmp).subSequence(0, map.get(tmp).length()-2)+"博士");
								System.out.println("phd"+tmp.substring(2));
								System.out.println(map.get(tmp).subSequence(0, map.get(tmp).length()-2)+"博士");
								System.out.println();
								flag3++;
							}
						}
					}catch(Exception e){}
					
				}
			
			res.add(json.toString());
		}
		
		int cou = 1;
		List<String>  ress = new ArrayList<String>();
		for(String xx : res){
			
			JSONObject json = JSONObject.fromObject(xx);
			if(!json.containsKey("ttitle")){
				ress.add(json.getString("title")+"\t"+Util.trans(json.getString("title")));
				System.out.println("cou : " + cou++);
			}
		}
		System.out.println(flag1+"\t"+flag2+"\t"+flag3);
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/addTtitle1225.json");
		FileUtil.ListToFile(ress, "C:/Users/强胜/Desktop/我是你爹/xxpp.txt");
	}


	private static void hebing() {
		// TODO Auto-generated method stub
		List<JSONObject> old = FileUtil.FileToJsonList("C:/Users/强胜/program1130.json", "utf-8");
		
		List<JSONObject> nnew = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/addDeadLine.json", "utf-8");
		Map<Integer , Map<String , Integer>> facMap = new HashMap<Integer , Map<String , Integer>>();
		Map<String , String> map = new HashMap<String , String>();
		for(JSONObject json : old){
			try{
				int insid = Util.id(json.get("institute_id").toString());
				String title = json.getString("faculty");
				int facId = Util.id(json.get("faculty_id").toString());
				
				Map<String , Integer> tmpMap;
				if(facMap.containsKey(insid))
					tmpMap = facMap.get(insid);
				else
					tmpMap = new HashMap<String , Integer>();
				tmpMap.put(title, facId);
				facMap.put(insid, tmpMap);
				if(json.getString("title").length() > 1)
					map.put(json.getString("title"), json.getString("ttitle"));
			}catch(Exception e){
			}
		}
		
		System.out.println("read end . . . ");
		System.out.println("map.size() : "+map.size());
		String fac;
		int insIdT = -1;
		int flag = 1;
		List<String> result = new ArrayList<String>();
		for(JSONObject json : nnew){
			json.put("faculty_id",-1);
			json.put("ttitle", "");
			if(json.containsKey("faculty") && json.getString("faculty").length() > 3){
				fac = json.getString("faculty");
				insIdT = json.getInt("institute_id");
				if(facMap.containsKey(insIdT)){
					if(facMap.get(insIdT).containsKey(fac)){
						json.put("faculty_id", facMap.get(insIdT).get(fac));
						System.out.println("process : " + flag++);
					}
				}
			}
			if(map.containsKey(json.getString("title")))
				json.put("ttitle", map.get(json.getString("title")));
			result.add(json.toString());
		}
		
		FileUtil.ListToFile(result, "C:/Users/强胜/Desktop/我是你爹/addDeadLineFacId.json");
	}


	private static void test() {
		// TODO Auto-generated method stub
		List<JSONObject> nnew = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/addTtitle1225.json", "utf-8");
		Set<String> set = new HashSet<String>();
		List<String> res = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int count = 1;
		for(JSONObject xx : nnew){
			if(!xx.get("deadline").toString().contains("[") && xx.get("deadline").toString().length() >2 ){
				Long[] tmp = new Long[1];
				try {
					tmp[0] = sdf.parse(xx.getString("deadline")).getTime()/1000;
					xx.put("deadline", tmp);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(xx.get("deadline").toString().length() < 2){
				Long[] tmp = new Long[0];
				xx.put("deadline", tmp);
			}
			
			xx.put("id", count++);
				res.add(xx.toString());
		}
		System.out.println(count);
		
	
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/total/total1.json");
	}


	private static void addDeadLine() {
		// TODO Auto-generated method stub
		
		File file = new File("C:/Users/强胜/Desktop/dataCrawler/申请方/program2015-12/呵呵xiao/");
		File[] files = file.listFiles();
		List<String> tmpList = new ArrayList<String>();
		Map<String , String> map = new HashMap<String , String>();
		Map<String , long[]> mapp = new HashMap<String , long[]>();
		for(File tmpFile : files){
			tmpList = FileUtil.FileToList(tmpFile.getAbsolutePath());
			for(String xx : tmpList)
				map.put(xx.split("\t")[0], xx.split("\t")[1]);
		}
		System.out.println(map.size());
		String tmpDate = "";
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
		long tmpShijianC ;
		for(String xx : map.keySet()){
			Matcher mth = ptnn.matcher(map.get(xx));
			if(mth.find()){
				tmpDate = mth.group();
			    if(tmpDate.length() > 10){
			    	int size = tmpDate.length()/10;
			    	long[] deadTmpA = new long[size];
			    	for(int i = 0 ; i < size ; i++){
			    		try {
							tmpShijianC = format.parse(tmpDate.substring(i*10, i*10+9)).getTime()/1000;
							deadTmpA[i] = tmpShijianC;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	}
			    	mapp.put(xx, deadTmpA);
			    }else{
			    	try {
						tmpShijianC = format.parse(tmpDate).getTime()/1000;
						long[] deadTmpA = new long[1];
						deadTmpA[0] = tmpShijianC;
						mapp.put(xx, deadTmpA);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
				
				System.out.println(mth.group());
			}
				
				
		}
		System.out.println(mapp.size());
		
		List<JSONObject> old = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/haAddDep11.json", "utf-8");
		List<String> result = new ArrayList<String>();
		int flag = 1;
		for(JSONObject json : old){
			System.out.println("flag : "+flag++);
			try{
				if(json.containsKey("deadline")){
					if(mapp.containsKey(json.getString("url"))){
						json.put("deadline", mapp.get(json.getString("url")));
					}					
				}
			}catch(Exception e){}	
			json.put("oldTranscript", json.getString("transcript"));
			json.put("transcript", "");
			result.add(json.toString());
		}
		
		FileUtil.ListToFile(result, "C:/Users/强胜/Desktop/我是你爹/addDeadLine.json");
	}


	static Pattern ptnn = Pattern.compile("(\\d{4}-\\d{1,2}-\\d{1,2}){1,4}");
	private static void update() {
		// TODO Auto-generated method stub
		//List<JSONObject> old = FileUtil.FileToJsonList("C:/Users/强胜/program1130.json", "utf-8");
		//List<JSONObject> nnew = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/program2015-12/2016年12月.json", "utf-8");
		
		System.out.println("read program file end . . . ");
		
		String path = "C:/Users/强胜/Desktop/dataCrawler/申请方/program2015-12/呵呵xiao/";
		File dir = new File(path);
		File[] files = dir.listFiles();
		List<String> tmpList = new ArrayList<String>();
		Map<String,String> map = new HashMap<String,String>();
		String deadTmp = "";
		for(int i = 0 ; i < files.length ; i++){
			File tmpFile = files[i];
			String fileName = tmpFile.getName().replace(".json", "");
			tmpList = FileUtil.FileToList(tmpFile.getAbsolutePath());
			for(String xx : tmpList){
				deadTmp = xx.split("\t")[1];
				Matcher mth = ptnn.matcher(deadTmp);
				if(mth.find()){
					System.out.println(mth.group());
					}
				//map.put(xx.split("\t")[0], );
			}
		}
		System.out.println("read dep end  . . . ");
		System.out.println("the length of map is : "+ map.size());
	}

	private static void addDeptype() {
		// TODO Auto-generated method stub
		List<JSONObject> file = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/hahaha1.json", "utf-8");
		List<String> result = new ArrayList<String>();
		String path = "C:/Users/强胜/Desktop/dataCrawler/申请方/program2015-12/呵呵大/";
		File dir = new File(path);
		File[] files = dir.listFiles();
		List<String> tmpList = new ArrayList<String>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(int i = 0 ; i < files.length ; i++){
			File tmpFile = files[i];
			String fileName = tmpFile.getName().replace(".json", "");
			tmpList = FileUtil.FileToList(tmpFile.getAbsolutePath());
			for(String xx : tmpList)
				map.put(xx, Integer.parseInt(fileName));
		}
		System.out.println("read dep end  . . . ");
		System.out.println("the length of map is : "+ map.size());
		
		//给program添加dep
		int flag = 1;
		for(JSONObject json : file){
			System.out.println("process : " + flag++);
			if(json.containsKey("url") && map.containsKey(json.getString("url"))){
				int[] depInt = new int[1];
				depInt[0] = map.get(json.getString("url"));
				json.put("department_type",depInt);
			}else{
				int[] depInt = new int[0];
				json.put("department_type",depInt);
			}
			result.add(json.toString());	
		}
		
		FileUtil.ListToFile(result, "C:/Users/强胜/Desktop/我是你爹/haAddDep.json");
	}

	private static void process1126() {
		// TODO Auto-generated method stub
		//List<JSONObject> file = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/trtr.txt", "utf-8");
		List<JSONObject> file = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/44.txt", "utf-8");
		List<String> result = new ArrayList<String>();
		
		String doc ="";
		String id = "";
		String res = "";
		String url = "";
		int flag = 1;
		for(JSONObject xx : file){
			System.out.println("process  "+ flag++);
			
			doc = xx.getString("content");
			id = xx.getString("id");
			url = xx.getString("url");
			res = parse1126(doc,id,url);
				
			if(res != null)
				result.add(res);
		}
		
		System.out.println("count____"+count+"\tflag : "+flag);
		FileUtil.ListToFile(result, "C:/Users/强胜/Desktop/我是你爹/haha.json",true);
	}

	static Set<String> fieldSet = new HashSet<String>();
	static{
		fieldSet.add("申请费");
		fieldSet.add("GRE");
		fieldSet.add("GRE Subject Test");
		fieldSet.add("GMAT");
		fieldSet.add("GPA要求");
		fieldSet.add("托福iBT");
		fieldSet.add("雅思");
		fieldSet.add("学历要求");
		fieldSet.add("成绩单");
		fieldSet.add("工作经历");
		fieldSet.add("PS");
		fieldSet.add("简历");
		fieldSet.add("推荐信");
		fieldSet.add("其他要求");
		fieldSet.add("学位");
		fieldSet.add("注意事项");
		fieldSet.add("学期");
		fieldSet.add("系");
		fieldSet.add("学院");
		fieldSet.add("LSAT");
		fieldSet.add("MCAT");
		fieldSet.add("链接");
		fieldSet.add("网申登录链接");
	}
	static Map<String,String> fieldMap= new HashMap<String,String>();
	static{
		fieldMap.put("申请费","apply_fee");
		fieldMap.put("GRE","gre");
		fieldMap.put("GRE Subject Test","gresub");
		fieldMap.put("GMAT","gmat");
		fieldMap.put("GPA要求","gpa");
		fieldMap.put("注意事项","attention");
		fieldMap.put("托福iBT","tofel");
		fieldMap.put("LSAT","lsat");
		fieldMap.put("MCAT","mcat");
		fieldMap.put("雅思","ielts");
		fieldMap.put("学历要求","degree_requirement");
		fieldMap.put("成绩单","transcript");
		fieldMap.put("工作经历","work");
		fieldMap.put("PS","ps");
		fieldMap.put("简历","cv");
		fieldMap.put("推荐信","reletter");
		fieldMap.put("学期","term");
		fieldMap.put("学位","degree");
		fieldMap.put("系","subject");
		fieldMap.put("学院","faculty");
		fieldMap.put("链接","website");
		fieldMap.put("网申登录链接","applyaddress");
	}
	static Pattern ptn = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}");
	private static String parse1126(String str , String id , String url) {
		// TODO Auto-generated method stub
		String res = null;
		try{
			
			
			Document doc = Jsoup.parse(str);
			Element pgm_title = doc.getElementsByClass("program-title").get(0);
			String title = pgm_title.getElementsByTag("h2").get(0).text();
			if(title.toLowerCase().indexOf("undergraduate") != -1)
				return null;
				
			JSONObject json = new JSONObject();
			json.put("apply_fee", "");
			json.put("gre", "");
			json.put("gpa", "");
			json.put("deadline", "");
			json.put("tofel", "");
			json.put("ielts", "");
			json.put("gmat", "");
			json.put("gresub", "");
			json.put("attention", "");
			json.put("lsat", "");
			json.put("mcat", "");
			json.put("degree_requirement", "");
			json.put("transcript", "");
			json.put("work", "");
			json.put("ps", "");
			json.put("cv", "");
			json.put("reletter", "");
			json.put("term", "");
			json.put("degree", "");
			json.put("subject", "");
			json.put("faculty", "");
			json.put("website", "");
			json.put("applyaddress", "");
			json.put("extra_requirement1", "");
			json.put("extra_requirement2", "");
			json.put("extra_requirement3", "");
			Element pgm_view = doc.getElementsByClass("program-view").get(0);
			Element left = pgm_view.getElementsByClass("col-md-5").get(0);
			Element right = pgm_view.getElementsByClass("col-md-4").get(0);
			Elements lbox = left.getElementsByClass("box");
			
			Elements rbox = right.getElementsByClass("box");
			String key = "";
			String value = "";
			int lsize = lbox.size();
			int rsize = rbox.size();
			String tmpDead = "";
			String deaddesc = "";
			String deadLink = "";
			//提取deadline
			Elements dds = lbox.get(0).getElementsByTag("dd");
			List<String> deadList = new ArrayList<String>();
			List<String> deadDescList = new ArrayList<String>();
			for(int i = 0 ; i < dds.size() ; i++){
				tmpDead = dds.get(i).text();
				Matcher mth = ptn.matcher(tmpDead);
				
				if(mth.find()){
					deadList.add(mth.group());
					deadDescList.add(tmpDead);
				}
			}
			Elements ellipsis = lbox.get(0).getElementsByClass("ellipsis");
			
			if(deadList.size() == 1){
				count++;
				json.put("deadline", deadList.get(0));
				json.put("oldDeadline", left.getElementsByClass("box").get(0).html());
			}else{
				json.put("deadline", "");
				json.put("oldDeadline", left.getElementsByClass("box").get(0).html());
			}
				
			
			
			
			int flag = 1;
			for(int i = 1 ; i < lsize ; i ++){
				key = lbox.get(i).getElementsByClass("box-req-title").get(0).text();
				value =lbox.get(i).getElementsByClass("box-req-content").get(0).html();
				
				if(key.indexOf("其他要求")  != -1){
					if(flag == 4)
						continue;
					json.put("extra_requirement"+flag, value);
					flag++;
					continue;
				}
				
				
				if(key.indexOf("注意事项") != -1){
					json.put("attention", value);
					continue;
				}
				
				if(fieldSet.contains(key)){
					json.put(fieldMap.get(key),value);
					continue;
				}else{
					System.out.println(key);
				}
				if(key.indexOf("SAT") != -1)
					return null;
				
			}
			Element frbox = rbox.get(0);
			Element srbox = rbox.get(1);
			int ddSize = frbox.getElementsByTag("dt").size();
			for(int j = 0 ; j < ddSize ; j++){
				//System.out.println(frbox.getElementsByTag("dt").get(j).text());
				key = frbox.getElementsByTag("dt").get(j).text();
				value = frbox.getElementsByTag("dt").get(j).nextElementSibling().text();
			    //System.out.println(frbox.getElementsByTag("dt").get(j).nextElementSibling().text());
				if(fieldSet.contains(key)){
					json.put(fieldMap.get(key),value);
				}
			}
			String contact = srbox.getElementsByClass("box-content").get(0).html();
		//	System.out.println(contact);
			json.put("contact", contact);
			json.put("url", url);
			json.put("institute_id", id);
			json.put("title", title);
			
			res = json.toString();
		}catch(Exception e){
		}
		
		
		return res;
	}

	private static void old() {
		// TODO Auto-generated method stub
		//List<String> list = FileUtil.FileToList("C:/Users/强胜/Desktop/dataCrawler/申请方/applyprogram.json");
		List<String> list = FileUtil.FileToList("C:/Users/强胜/Desktop/我是你爹/haha.json");
		List<String> res = new ArrayList<String>();
		System.out.println("read______end");
		for(String xx : list){
			JSONObject json = JSONObject.fromObject(xx);
			System.out.println("process___"+count++);
			
			//json.put("id", count);
			json.put("apply_fee", getFee(json.getString("apply_fee")));
			json.put("degree_requirement", getDegree_req(json.getString("degree_requirement")));
			json.put("gpa",getGpa(json.getString("gpa")));
			json.put("gmat",getGmat(json.getString("gmat")));
			json.put("gre",getGre(json.getString("gre")));
			json.put("transcript",getTranscript(json.getString("transcript")));
			json.put("ps",getPsCv(json.getString("ps")));
			json.put("cv",getPsCv(json.getString("cv")));
			json.put("reletter",getReletter(json.getString("reletter")));
			json.put("toefl", getToeflIelts(json.getString("tofel")));
			json.remove("tofel");
			json.put("ielts", getToeflIelts(json.getString("ielts")));
			json.put("attention", getAttention(json.getString("attention")));
			json.put("extra_requirement1", getAttention(json.getString("extra_requirement1")));
			json.put("extra_requirement2", getAttention(json.getString("extra_requirement2")));
			json.put("extra_requirement3", getAttention(json.getString("extra_requirement3")));
			json.put("attention", getAttention(json.getString("attention")));
			json.put("contact", getContact(json.getString("contact")));
			res.add(json.toString());
			
			
			
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/hahaha1.json");
	}

	private static String getContact(String str) {
		// TODO Auto-generated method stub
		String res = "";
		String emaill = "";
		String phonee = "";
		try{
			Document doc = Jsoup.parse(str);
			emaill = doc.getElementsByTag("li").get(1).text().trim();
			phonee = doc.getElementsByTag("li").get(2).text().trim();
			
		}catch(Exception e){
			
		}
		if(phonee.length() < 2)
			res = emaill;
		else
			res =  emaill+" | "+phonee;
		
		
		return res;
	}

	private static String getFee(String fee){
		Document xx = Jsoup.parse(fee);
		try{
			return xx.getElementsByTag("span").get(1).text().toString();
		}catch(Exception e){
			return "";
		}
		
	}
	private static String getDegree_req(String deg_req){
		Document xx = Jsoup.parse(deg_req);
		try{
			String tmp = xx.getElementsByTag("dd").get(0).text().toString();
			//返回全部信息-（残缺信息跟全部信息都放在一起）
			if(tmp.indexOf("显示全部") != -1){
				String reg = tmp.substring(0, 20);
				System.out.println(reg+tmp.split(reg)[1]);
				return reg+tmp.split(reg)[1];
			}else{
				//System.out.println(tmp);
				return tmp;
			}
				
		}catch(Exception e){
			return "";
		}
	}
	

	private static String getGreGmatGpa(String req){
		Document xx = Jsoup.parse(req);
		try{
			String tmp = xx.text().toString();
			//返回全部信息-（残缺信息跟全部信息都放在一起）
			if(tmp.indexOf("显示全部") != -1){
				String reg = tmp.substring(0, 20);
				//System.out.println(reg+tmp.split(reg)[1]);
				return reg+tmp.split(reg)[1];
			}else{
				System.out.println(tmp);
				return tmp;
			}
		}catch(Exception e){
			return "";
		}
	}

	private static String getTranscript(String transcript){
		Document xx = Jsoup.parse(transcript);
		try{
			String tmp = xx.text().toString();
			//返回全部信息-（残缺信息跟全部信息都放在一起）
			if(tmp.indexOf("显示全部") != -1){
				String reg = tmp.substring(0, 20);
				tmp = reg+tmp.split(reg)[1];
				if(tmp.indexOf("项目特别要求:") != -1){
					tmp = tmp.replace("项目特别要求:", "项目特别要求:<br>").replace("描述:", "<br>描述:<br>:");
					return tmp;
				}else{
					if(tmp.indexOf("https://www.grad....") == -1)
						return tmp;
					else
						return "";
				}
			}else{
				if(tmp.indexOf("项目特别要求:") != -1){
					tmp = tmp.replace("项目特别要求:", "项目特别要求:<br>").replace("描述:", "<br>描述:<br>:");
					return tmp;
				}else{
					if(tmp.indexOf("https://www.grad....") == -1)
						return tmp;
					else
						return "";
				}
			}
		}catch(Exception e){
			return "";
		}
	}
	
	private static String getPsCv(String ps_req){
		Document xx = Jsoup.parse(ps_req);
		try{
			String tmp = xx.text().toString();
			//返回全部信息-（残缺信息跟全部信息都放在一起）
			if(tmp.indexOf("显示全部") != -1){
				String reg = tmp.substring(0, 20);
				tmp = reg+tmp.split(reg)[1];
				if(tmp.indexOf("项目特别要求:") != -1){
					tmp = tmp.replace("项目特别要求:", "项目特别要求:<br>").replace("描述:", "<br>描述:<br>:");
					return tmp;
				}else{
					return "";
				}
			}else{
				if(tmp.indexOf("项目特别要求:") != -1){
					tmp = tmp.replace("项目特别要求:", "项目特别要求:<br>").replace("描述:", "<br>描述:<br>:");
					return tmp;
				}else{
					return "";
				}
			}
		}catch(Exception e){
			return "";
		}
	}
	
	private static JSONObject getReletter(String req){
		Document xx = Jsoup.parse(req);
		JSONObject json = new JSONObject();
		try{
			String num = xx.getElementsByTag("p").get(0).text();
			json.put("amount",Integer.parseInt(num));
			String miaoshu = xx.getElementsByTag("p").get(1).text();
			json.put("other", miaoshu.trim());
		}catch(Exception e){
			return null;
		}
		return json;
	}
	
	static JSONObject getToeflIelts(String str){
		Document xx = Jsoup.parse(str);
		Elements excerpt = xx.getElementsByClass("js-autocollapse-content");
		JSONObject json = new JSONObject();
		//有溢出，显示全部
		if(excerpt.size()>0){
			Elements ps = excerpt.get(0).getElementsByTag("p");
			for(Element p : ps){
				String text = p.text();
				if(text.contains("最低要求：")){
					if(text.contains("总分"))
						json.put("total", text.split("推荐分数")[0].replaceAll("[^\\.\\d]", ""));
					if(text.contains("阅读"))
						json.put("reading", text.split("推荐分数")[0].replaceAll("[^\\.\\d]", ""));
					if(text.contains("听力"))
						json.put("listening", text.split("推荐分数")[0].replaceAll("[^\\.\\d]", ""));
					if(text.contains("口语"))
						json.put("speaking", text.split("推荐分数")[0].replaceAll("[^\\.\\d]", ""));
					if(text.contains("写作"))
						json.put("writing", text.split("推荐分数")[0].replaceAll("[^\\.\\d]", ""));
				}
				else{
					json.put("other", text.trim());
				}
			}
		}else{
			Elements ps = xx.getElementsByTag("p");
			for(Element p : ps){
				String text = p.text();
				if(text.contains("最低要求：")){
					if(text.contains("总分"))
						json.put("total", text.split("推荐分数")[0].replaceAll("[^\\.\\d]", ""));
					if(text.contains("阅读"))
						json.put("reading", text.split("推荐分数")[0].replaceAll("[^\\.\\d]", ""));
					if(text.contains("听力"))
						json.put("listening", text.split("推荐分数")[0].replaceAll("[^\\.\\d]", ""));
					if(text.contains("口语"))
						json.put("speaking", text.split("推荐分数")[0].replaceAll("[^\\.\\d]", ""));
					if(text.contains("写作"))
						json.put("writing", text.split("推荐分数")[0].replaceAll("[^\\.\\d]", ""));
				}else{
					json.put("other", text.trim());
				}
			}
			
				
		}
		
		return json;	
	}
	
	static JSONObject getGpa(String req){
		Document xx = Jsoup.parse(req);
		JSONObject json = new JSONObject();
		try{
			String tmp = xx.text().toString();
			//返回全部信息-（残缺信息跟全部信息都放在一起）
			if(tmp.indexOf("显示全部") != -1){
				String reg = tmp.substring(0, 20);
				tmp = reg+tmp.split(reg)[1];
				if(!tmp.trim().equals("")){
					//最低要求
					Pattern ptn1 = Pattern.compile("最低要求 :[\\s]*?\\d(\\.\\d)?");
					Matcher mth1 = ptn1.matcher(tmp);
					while(mth1.find()){
						String min = mth1.group().replace("最低要求 : ", "").trim();
						if(Integer.parseInt(min.substring(0, 1)) < 4)
							json.put("min_score", min);
					}
					//推荐分数
					Pattern ptn2 = Pattern.compile("推荐分数 :[\\s]*?\\d(\\.\\d)?");
					Matcher mth2 = ptn2.matcher(tmp);
					while(mth2.find()){
						String rec = mth2.group().replace("推荐分数  :", "").trim();
						if(Integer.parseInt(rec.substring(0, 1)) < 4)
							json.put("rec_score", rec);
					}
					
					//其它描述性要求
					if(tmp.contains("项目特别要求 :")){
						String[] oth = tmp.split("项目特别要求 :");
						String other = "项目特殊要求：<br>"+oth[1].trim();
						json.put("other", other);
					}
				}
				else
					return null;
			}else{
				if(!tmp.trim().equals("")){
					//最低要求
					Pattern ptn1 = Pattern.compile("最低要求 :[\\s]*?\\d(\\.\\d)?");
					Matcher mth1 = ptn1.matcher(tmp);
					while(mth1.find()){
						String min = mth1.group().replace("最低要求 : ", "").trim();
						if(Integer.parseInt(min.substring(0, 1)) < 4)
							json.put("min_score", min);
					}
					//推荐分数
					Pattern ptn2 = Pattern.compile("推荐分数 :[\\s]*?\\d(\\.\\d)?");
					Matcher mth2 = ptn2.matcher(tmp);
					while(mth2.find()){
						String rec = mth2.group().replace("推荐分数  :", "").trim();
						if(Integer.parseInt(rec.substring(0, 1)) < 4)
							json.put("rec_score", rec);
					}
					
					//其它描述性要求
					if(tmp.contains("项目特别要求 :")){
						String[] oth = tmp.split("项目特别要求 :");
						String other = "项目特殊要求：<br>"+oth[1].trim();
						json.put(oth, other);
					}
				}else
					return null;
			}
		}catch(Exception e){
			return null;
		}
		return json;
	}
	static JSONObject getGmat(String req){
		Document xx = Jsoup.parse(req);
		JSONObject json = new JSONObject();
		try{
			String tmp = xx.text().toString();
			//返回全部信息-（残缺信息跟全部信息都放在一起）
			if(tmp.indexOf("显示全部") != -1){
				String reg = tmp.substring(0, 20);
				tmp = reg+tmp.split(reg)[1];
				if(!tmp.trim().equals("")){
					//最低要求
					Pattern ptn1 = Pattern.compile("总分(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}");
					Matcher mth1 = ptn1.matcher(tmp);
					while(mth1.find()){
						String min = mth1.group().replace("最低要求：", "").replace("总分", "").replace("百分比 ", "").trim();
						//System.out.println(min);
						json.put("min_score", min);
					}
					//推荐分数
					Pattern ptn2 = Pattern.compile("总分(百分比)?[\\s]*?推荐分数：[\\s]*?[\\.\\d]{1,4}");
					Matcher mth2 = ptn2.matcher(tmp);
					while(mth2.find()){
						String rec = mth2.group().replace("推荐分数：", "").replace("总分", "").replace("百分比 ", "").trim();
						json.put("rec_score",rec);
					}
					String other = tmp.replaceAll("总分(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}", "").replaceAll("总分(百分比)?[\\s]*?推荐分数：[\\s]*?[\\.\\d]{1,4}", "");
					json.put("other", other);
				}else
					return null;
			}else{
				if(!tmp.trim().equals("")){
					//最低要求
					Pattern ptn1 = Pattern.compile("总分(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}");
					Matcher mth1 = ptn1.matcher(tmp);
					while(mth1.find()){
						String min = mth1.group().replace("最低要求：", "").replace("总分", "").replace("百分比 ", "").trim();
						//System.out.println(min);
						json.put("min_score", min);
					}
					//推荐分数
					Pattern ptn2 = Pattern.compile("总分(百分比)?[\\s]*?推荐分数：[\\s]*?[\\.\\d]{1,4}");
					Matcher mth2 = ptn2.matcher(tmp);
					while(mth2.find()){
						String rec = mth2.group().replace("推荐分数：", "").replace("总分", "").replace("百分比 ", "").trim();
						json.put("rec_score",rec);
					}
					String other = tmp.replaceAll("总分(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}", "").replaceAll("总分(百分比)?[\\s]*?推荐分数：[\\s]*?[\\.\\d]{1,4}", "");
					json.put("other", other);
				}else
					return null;
			}
		}catch(Exception e){
			return null;
		}
		
		return json;
	}
	
	static JSONObject getGre(String req){
		Document xx = Jsoup.parse(req);
		JSONObject json = new JSONObject();
		try{
			String tmp = xx.text().toString();
			//返回全部信息-（残缺信息跟全部信息都放在一起）
			if(tmp.indexOf("显示全部") != -1){
				String reg = tmp.substring(0, 20);
				tmp = reg+tmp.split(reg)[1];
				if(!tmp.trim().equals("")){
					//System.out.println(tmp);
					//最低要求
					Pattern ptn1 = Pattern.compile("总分(百分比)?[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}");
					Matcher mth1 = ptn1.matcher(tmp);
					while(mth1.find()){
						String min = mth1.group().replace("最低要求：", "").replace("总分", "").replace("百分比 ", "").trim();
						json.put("min_score", min);
					}
					//verbal
					Pattern ptn2 = Pattern.compile("Verbal[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}");
					Matcher mth2 = ptn2.matcher(tmp);
					while(mth2.find()){
						String verbal = mth2.group().replace("最低要求：", "").replace("Verbal", "").trim();
						json.put("verbal",verbal);
					}
					Pattern ptn3 = Pattern.compile("Quantitative[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}");
					Matcher mth3 = ptn3.matcher(tmp);
					while(mth3.find()){
						String quantitative = mth3.group().replace("最低要求：", "").replace("Quantitative", "").trim();
						json.put("quantitative",quantitative);
					}
					Pattern ptn4 = Pattern.compile("Analytical[\\s]Writing[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}");
					Matcher mth4 = ptn4.matcher(tmp);
					while(mth4.find()){
						String writing = mth4.group().replace("最低要求：", "").replace("Analytical Writing", "").trim();
						json.put("writing",writing);
					}
					
					
					String other = tmp.replaceAll("总分(百分比)?[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}", "").replaceAll("Analytical[\\s]Writing[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}", "").replaceAll("Verbal[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}", "").replaceAll("Quantitative[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}", "");
					if(other.trim().length() > 5){
						json.put("other", other);
					}
						
				}else
					return null;
			}else{
				if(!tmp.trim().equals("")){
					//System.out.println(tmp);
					//最低要求
					Pattern ptn1 = Pattern.compile("总分(百分比)?[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}");
					Matcher mth1 = ptn1.matcher(tmp);
					while(mth1.find()){
						String min = mth1.group().replace("最低要求：", "").replace("总分", "").replace("百分比 ", "").trim();
						json.put("min_score", min);
					}
					//verbal
					Pattern ptn2 = Pattern.compile("Verbal[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}");
					Matcher mth2 = ptn2.matcher(tmp);
					while(mth2.find()){
						String verbal = mth2.group().replace("最低要求：", "").replace("Verbal", "").trim();
						json.put("verbal",verbal);
					}
					Pattern ptn3 = Pattern.compile("Quantitative[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}");
					Matcher mth3 = ptn3.matcher(tmp);
					while(mth3.find()){
						String quantitative = mth3.group().replace("最低要求：", "").replace("Quantitative", "").trim();
						json.put("quantitative",quantitative);
					}
					Pattern ptn4 = Pattern.compile("Analytical[\\s]Writing[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}");
					Matcher mth4 = ptn4.matcher(tmp);
					while(mth4.find()){
						String writing = mth4.group().replace("最低要求：", "").replace("Analytical Writing", "").trim();
						json.put("writing",writing);
					}
					
					
					String other = tmp.replaceAll("总分(百分比)?[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}", "").replaceAll("Analytical[\\s]Writing[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}", "").replaceAll("Verbal[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}", "").replaceAll("Quantitative[\\s]*?(百分比)?[\\s]*?最低要求：[\\s]*?[\\.\\d]{1,4}", "");
					if(other.trim().length() > 5){
						json.put("other", other);
					}
						
				}else
					return null;
			}
		}catch(Exception e){
			return null;
		}
		return json;
	}
	
	static String getAttention(String req){
		Document xx = Jsoup.parse(req);
		return xx.text();
	}
	
}
