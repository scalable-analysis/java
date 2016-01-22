package com.dulishuo.shenqingfang;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NewFaculty {
	static int count = 50;
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		//history();
		//buquanweipipeixuexiao();
		//();
		pgmADDdepmType2();
		//noTypePgm();
		//tmp();
		//pgmADDdepmType();
		
	}
	private static void tmp() {
		// TODO Auto-generated method stub
		List<JSONObject> type = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/facType/facType.json", "utf-8");
	
		for(JSONObject json : type)
			System.out.println(json.getInt("id")+" "+json.getString("name") + "  "+json.getString("name_chinese"));
	}
	private static void noTypePgm() {
		// TODO Auto-generated method stub
		List<JSONObject> ne = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program44.json", "utf-8");
		Set<String> set = new HashSet<String>();
		
		
		List<String> res = new ArrayList<String>();
		for(JSONObject json : ne){
			if(Util.id(json.get("department_type").toString()) == -1)
				set.add(json.getString("title"));
		}
		for(String xx : set)
			res.add(xx+"\t"+Util.trans(xx));
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/缺乏departmentType的11.txt");
		System.out.println("____exit___");
			
	}
	private static void pgmADDdepmType2() {
		// TODO Auto-generated method stub
		List<JSONObject> ne = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program77.json", "utf-8");
		List<String> res = new ArrayList<String>();
		List<JSONObject> type = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/facType/facType.json", "utf-8");
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(JSONObject json : type){
			map.put(json.getString("name_chinese"), json.getInt("id"));
		}
		
		for(JSONObject json : ne){
			if(json.containsKey("department_type")){
				if(!json.get("department_type").toString().equals("-1")){
					String[] tmp = json.getString("department_type").split("-");
					int size = tmp.length;
					if(size == 2){
						int[] ttt = new int[1];
						ttt[0] = map.get(tmp[1]);
						json.put("department_type", ttt);
					}else{
						int[] ttt = new int[tmp.length-1];
						for(int i = 1 ; i < tmp.length ; i ++){
							ttt[i-1] = map.get(tmp[i]);
						}
						json.put("department_type", ttt);
					}
				}else{
					int[] ttt = new int[0];
					json.put("department_type", ttt);
				}
				//json.put("department_type", map.get());
			}else{
				int[] ttt = new int[0];
				json.put("department_type", ttt);
			}
			res.add(json.toString());
			
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program88.json");
		System.out.println("____exit___");
	}
	private static void pgmADDdepmType() {
		int count = 1;
		// TODO Auto-generated method stub
		List<JSONObject> fac = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/fac/applyfaculty3.json", "utf-8");
		Map<String,Map<Integer,Set<String>>> map = new HashMap<String,Map<Integer,Set<String>>>();
		Map<String,Set<String>> txt = new HashMap<String,Set<String>> ();
		for(JSONObject json : fac){			
			JSONArray ja = json.getJSONArray("institutes");
			String name_chinese = json.getString("field_chinese");
			Map<Integer,Set<String>> schMap;
			Set<String> xtx;
			if(map.containsKey(name_chinese))
				xtx = txt.get(name_chinese);
			else
				xtx = new HashSet<String>();;
			if(map.containsKey(name_chinese))
				schMap = map.get(name_chinese);
			else
				schMap = new HashMap<Integer,Set<String>>();;
			for(int i = 0 ; i < ja.size() ; i++){
				
				JSONObject xx = new JSONObject();
				JSONObject jo = ja.getJSONObject(i);
				String institute = "";
				try{
					institute = jo.getString("institute_title").split("\\(")[1].replace(")", "").trim();	
				}catch(Exception e){
					institute = jo.getString("institute_title");
				}
				if(institute.equals("伊利诺州"))
					institute = "Northwestern University";
				if(institute.equals("佛罗里达州"))
					institute = "Florida State University";
				
				int idd = toInsId(institute);
				if(idd == -1)
					continue;
				Set<String> set;
				if(schMap.containsKey(idd))
					set = schMap.get(idd);
				else
					set = new HashSet<String>();
				if(jo.containsKey("programs")){
					JSONArray jaa = jo.getJSONArray("programs");
					for(int j = 0 ; j < jaa.size() ; j++){
						JSONObject joo = jaa.getJSONObject(j);
						if(joo.containsKey("title")){
							set.add(joo.getString("title").trim());
							xtx.add(joo.getString("title").trim());
						}
							
					}	
				}
				schMap.put(idd, set);
				
			}
			map.put(name_chinese, schMap);
			txt.put(name_chinese, xtx);
		}
		System.out.println(txt.size());
		List<JSONObject> ne = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program66.json", "utf-8");
		List<String> res = new ArrayList<String>();
	/*	Map<Integer,Map<Integer,String>> mmap = new HashMap<Integer,Map<Integer,String>>();
		for(JSONObject json : ne){
			int iddd = json.getInt("institute_id");
			Map<Integer,String> tmp;
			if(mmap.containsKey(iddd))
				tmp = mmap.get(iddd);
			else
				tmp = new HashMap<Integer,String>();
			tmp.put(json.getInt("id"),json.getString("title"));
			
			mmap.put(iddd, tmp);
		}*/
		Set<String> ssss = new HashSet<String>();
		for(JSONObject json : ne){
			ssss.add(json.getString("title"));
			
		}
		System.out.println("read program   end_____");
		
	/*	for(Entry<Integer,Map<String,Integer>> entry : mmap.entrySet()){
			Map<String,Integer> tmp = new HashMap<String,Integer>();
		}*/
		Map<String,String> mmm = new HashMap<String,String>();
		int aa = 1;
		int bb = 0;
		int cc = 0;

		System.out.println("aa_____"+aa++);
		for(String tt : ssss){
			bb=0;
			aa++;
			String flag = "";
			for(Entry<String,Set<String>> entry : txt.entrySet()){
				System.out.println("aa_--"+aa+"____bb_____"+bb++);
				for(String yy : entry.getValue()){
					if(Util.isSameProgram(yy, tt)){
						flag += "-"+entry.getKey();
						break;
					}
				}
			}	
			if(!flag.equals(""))
				mmm.put(tt, flag);
		}		
		/*for(Entry<String,Map<Integer,Set<String>>> entry : map.entrySet()){
			aa++;
			for(Entry<Integer,Set<String>> ent : entry.getValue().entrySet()){
				System.out.println("aa___bb___"+aa+bb++);
				if(mmap.containsKey(ent.getKey())){
					Map<Integer,String> tmp = mmap.get(ent.getKey());
					Set<String> set = ent.getValue();
					for(int xx : tmp.keySet()){
						for(String yy : set){
							if(Util.isSameProgram(yy, tmp.get(xx)))
								mmm.put(xx, entry.getKey());
						}
					}
				}
			}
		}*/
		
		for(JSONObject json : ne){
			String ttt = json.getString("title");
			if(mmm.containsKey(ttt))
				json.put("department_type", mmm.get(ttt));
			res.add(json.toString());
		}
		System.out.println("mmm   size():"+mmm.size());
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program77.json");
		System.out.println("____-Exit-__________");
	}
	static Map<String,Integer> schMap = new HashMap<String,Integer>();
	static{
		List<JSONObject> sch = FileUtil.FileToJsonList("C:/Users/强胜/ins.json", "utf-8");
		for(JSONObject json : sch)
			schMap.put(json.getString("title"),Util.id(json.get("id").toString()));
	}
	static int toInsId(String str){
		int result = -1;
		for(Entry<String,Integer> entry : schMap.entrySet()){
			if(Util.isSame(str, entry.getKey()))
				return entry.getValue();
		}
		
		List<String> ttt = null;
		try {
			ttt = FileUtil.FileToList("C:/Users/强胜/Desktop/institute_id为-1的缺失的学校(1).txt", "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> res = new ArrayList<String>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(String xx : ttt){
			if(Integer.parseInt(xx.split("!")[0].toString()) > 830)
				map.put(xx.split("!")[1], Integer.parseInt(xx.split("!")[0].toString()));
		}
		if(map.containsKey(str))
			return map.get(str);
		return result;
	}
	private static void buquanweipipeixuexiao() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		List<JSONObject> fac = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/fac/facRank3.json", "utf-8");
		List<String> ttt = FileUtil.FileToList("C:/Users/强胜/Desktop/institute_id为-1的缺失的学校(1).txt", "utf-8");
		List<String> res = new ArrayList<String>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(String xx : ttt){
			map.put(xx.split("!")[1], Integer.parseInt(xx.split("!")[0].toString()));
		}
		
		for(JSONObject json : fac){	
			if(json.getInt("institute_id") == -1){
				String tti = json.getString("institute");
				if(map.containsKey(tti))
					json.put("institute_id", map.get(tti));
			}
			res.add(json.toString());	
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/fac/facRank4.json");
	}
	private static void history() {
		// TODO Auto-generated method stub
		/*	List<JSONObject> fac = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/fac/applyfaculty3.json", "utf-8");
		List<String> res = new ArrayList<String>();
		
		for(JSONObject json : fac){			
			JSONArray ja = json.getJSONArray("institutes");
			String name = json.getString("field");
			String name_chinese = json.getString("field_chinese");
			for(int i = 0 ; i < ja.size() ; i++){
				JSONObject xx = new JSONObject();
				JSONObject jo = ja.getJSONObject(i);
				String institute = "";
				try{
					institute = jo.getString("institute_title").split("\\(")[1].replace(")", "").trim();	
				}catch(Exception e){
					institute = jo.getString("institute_title");
				}
				System.out.println(institute);	
				
				int value = Integer.parseInt(jo.getString("rank").replace("#", ""));
				xx.put("id", count++);
				xx.put("name", name);
				xx.put("name_chinese", name_chinese);
				xx.put("institute", institute);
				xx.put("value", value);
				xx.put("fac_type", value);
				xx.put("from", "shenQF");
				xx.put("year", 2015);
				res.add(xx.toString());
			}
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/fac/facRank.json");*/
		
		List<JSONObject> fac = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/fac/facRank4.json", "utf-8");
		List<JSONObject> sch = FileUtil.FileToJsonList("C:/Users/强胜/ins.json", "utf-8");
		Map<Integer,JSONObject> map1 = new HashMap<Integer,JSONObject>();
		for(JSONObject json : sch){
			map1.put(Util.id(json.get("id").toString()), json.getJSONObject("logo"));
		}
		Map<Integer,String> map2 = new HashMap<Integer,String>();
		for(JSONObject json : sch){
			if(Util.id(json.get("id").toString()) > -1)
				map2.put(Util.id(json.get("id").toString()), json.getString("ttitle"));
		}
		List<String> res = new ArrayList<String>();
		Map<String,JSONObject> map = new HashMap<String,JSONObject>();
		for(JSONObject json : fac){		
			if(json.getInt("value") < 4 && json.getInt("value") > 0){
				String name = json.getString("name");
				String name_chinese = json.getString("name_chinese");
				
				if(map.containsKey(name)){
					JSONObject jo = map.get(name);
					JSONArray ja = jo.getJSONArray("school_list");
					JSONObject jso = new JSONObject();
					jso.put("value", json.getInt("value"));
					jso.put("title", json.getString("institute"));
					if(json.getInt("institute_id") == -1){
						jso.put("ttitle","");
						jso.put("logo", null);
					}
					else{
						jso.put("ttitle", map2.get(json.get("institute_id")));
						jso.put("logo", map1.get(json.get("institute_id")));
					};
					ja.add(jso);
					jo.put("school_list", ja);
					map.put(name, jo);
				}else{
					
					JSONObject jo = new JSONObject();
					jo.put("id", count++);
					jo.put("source", "shenQF");
					jo.put("subject", name);
					jo.put("subject_chinese", name_chinese);
					JSONArray ja = new JSONArray();
					JSONObject jso = new JSONObject();
					jso.put("value", json.getInt("value"));
					jso.put("title", json.getString("institute"));
					if(json.getInt("institute_id") == -1){
						jso.put("ttitle","");
						jso.put("logo", null);
					}
					else{
						jso.put("ttitle", map2.get(json.get("institute_id")));
						jso.put("logo", map1.get(json.get("institute_id")));
					}
						
					ja.add(jso);
					jo.put("school_list", ja);
					map.put(name, jo);
				}
			}
		}
		System.out.println("size  :"+map.size());
		for(Entry<String,JSONObject> entry : map.entrySet())
			res.add(entry.getValue().toString());
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/facType/facTypeRank0926.json");
		
		/*List<JSONObject> fac = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/fac/facRank.json", "utf-8");
		List<JSONObject> ins = FileUtil.FileToJsonList("C:/Users/强胜/ins.json", "utf-8");
		
		List<String> res = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(JSONObject json : ins){
			map.put(Util.id(json.get("id").toString()), json.getString("title"));
		}
		for(JSONObject json : fac){
			json.put("institute_id", -1);
			for(Entry<Integer,String> entry : map.entrySet())
				if(Util.isSame(json.getString("institute"),entry.getValue())){
					json.put("institute_id", entry.getKey());
				}
			if(json.getInt("institute_id") == -1)
				set.add(json.getString("institute"));
			res.add(json.toString());
			
		}
		System.out.println("_______start_______");
		for(String xx : set)
			System.out.println(xx);
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/fac/facRank1.json");*/
		System.out.println("________end________");
	}

}
