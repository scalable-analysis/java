package com.dulishuo.shenqingfang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PgmRank {
	static int count = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//pgmRank1();
		//pgmRank2();
		schRank1();
	}
	private static void schRank1() {
		// TODO Auto-generated method stub
		List<JSONObject> pgm = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/匹配了学校+id+pgmRank.json", "utf-8");
		
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
		
		List<JSONObject> schRank = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/QS/json/uniRank.json", "utf-8");
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(JSONObject json : schRank){
			if(!json.getString("value").contains("-") && !json.getString("value").equals(""))
				map.put(json.getInt("institute_id"), Integer.parseInt(json.getString("value")));
		}
		
		List<String> res = new ArrayList<String>();
		for(JSONObject json : pgm){
			json.put("school_rank", map.get(json.getInt("institute_id")));
			Map<Integer,Integer> tmp = rankMap.get(facMap.get(json.getInt("department_id")));
			if(tmp.containsKey(json.getInt("institute_id")))
				json.put("program_rank", tmp.get(json.getInt("institute_id")));
			res.add(json.toString());
		}
		//FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/匹配了学校+id+pgmRank+schRank.json");
		System.out.println("______Exit__________");
	}
	static void pgmRank2() {
		// TODO Auto-generated method stub
		List<JSONObject> pgm = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/匹配了学校+id.json", "utf-8");
		List<JSONObject> pgmRank = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/fac/pgmRank.json", "utf-8");
		List<String> res = new ArrayList<String>();
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(JSONObject json : pgmRank){
			String insid = json.get("institute_id").toString();
			String term = json.getString("deadline").substring(0,7);
			String title = json.getString("title");
			System.out.println(insid+term+title);
			map.put(insid+term+title, json.getInt("pgm_rank"));
		}
		for(JSONObject json : pgm){
			String insid = json.get("institute_id").toString();
			String term = json.getString("term");
			String title = json.getString("title");
			String key = insid+term+title;
			json.put("pgm_rank", -1);
			if(map.containsKey(key)){
				json.put("pgm_rank", map.get(key));
				System.out.println(count++);
			}
			res.add(json.toString());
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/匹配了学校+id+pgmRank.json");
		System.out.println("______Exit__________");
	}
	static void pgmRank1() {
		// TODO Auto-generated method stub
		List<JSONObject> fac = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/fac/applyfaculty3.json", "utf-8");
		List<JSONObject> ins = FileUtil.FileToJsonList("C:/Users/强胜/ins.json", "utf-8");
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(JSONObject json : ins){
			map.put(Util.id(json.get("id").toString()), json.getString("title"));
		}
		List<String> res = new ArrayList<String>();
		
		for(JSONObject json : fac){			
			JSONArray ja = json.getJSONArray("institutes");
			for(int i = 0 ; i < ja.size() ; i++){
				JSONObject xx = new JSONObject();
				JSONObject jo = ja.getJSONObject(i);
				String institute = "";
				try{
					institute = jo.getString("institute_title").split("\\(")[1].replace(")", "").trim();	
				}catch(Exception e){
					institute = jo.getString("institute_title");
				}
				int value = Integer.parseInt(jo.getString("rank").replace("#", ""));
				//System.out.println(institute);	
				JSONArray jsonA = jo.getJSONArray("programs");
				for(int j = 0 ; j < jsonA.size() ; j ++){
					System.out.println("pcocess____"+count++);
					JSONObject yy = jsonA.getJSONObject(j);
					JSONObject tt = new JSONObject();
					tt.put("id", count);
					tt.put("deadline", yy.getString("deadline"));
					tt.put("title", yy.getString("title"));
					tt.put("institute", institute);
					tt.put("pgm_rank", value);
					tt.put("institute_id", -1);
					for(Entry<Integer,String> entry : map.entrySet())
						if(Util.isSame(institute,entry.getValue())){
							tt.put("institute_id", entry.getKey());
						}
					res.add(tt.toString());
				}
			}
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/申请方/fac/pgmRank.json");
		System.out.println("__________exit_________");
	}

}
