package com.dulishuo.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class PgmStatistics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//tongjiTop50SchPgm();
		//getTop50Sch();
		tongjiTop20FacQueShiPgm();
		
	}

	private static void tongjiTop20FacQueShiPgm() {
		// TODO Auto-generated method stub
		List<JSONObject> facTypeRank = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/fac/facRank4.json", "utf-8");
		List<JSONObject> pgm = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program99.json", "utf-8");
		List<JSONObject> type = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/facType/facType.json", "utf-8");
	
		Map<Integer,String> typeMap = new HashMap<Integer,String>();
		for(JSONObject json : type){
			typeMap.put(json.getInt("id"),json.getString("name_chinese"));
		}
		Map<Integer,Map<String,Integer>> map = new HashMap<Integer,Map<String,Integer>>();
		Set<Integer> set = new HashSet<Integer>();
		Map<Integer,Set<String>> mapFac = new HashMap<Integer,Set<String>>();
		for(JSONObject json : facTypeRank){
			int insid = json.getInt("institute_id");
			String name = json.getString("name_chinese");
			int value = json.getInt("value");
			if(value < 21){
				Set<String> tmp;
				if(mapFac.containsKey(insid))
					tmp = mapFac.get(insid);
				else
					tmp = new HashSet<String>();
				tmp.add(name);
				mapFac.put(insid, tmp);
			}
		}
		for(JSONObject json : pgm){
			int insid = json.getInt("institute_id");
			String depType = json.get("department_type").toString().replace("[", "").replace("]", "");
			if(depType.length() > 0){
				if(map.containsKey(insid)){
					Map<String,Integer> tmp;
					String[] dep = depType.split(",");
					if(dep.length==1){
						String name = typeMap.get(Integer.parseInt(depType));
						tmp = map.get(insid);
						if(map.get(insid).containsKey(name)){
							tmp.put(name, tmp.get(name)+1);
						}
						else{
							tmp.put(name, 1);
						}
						map.put(insid, tmp);
					}else{
						for(int i = 0 ; i < dep.length ; i++){
							String name = typeMap.get(Integer.parseInt(dep[i]));
							tmp = map.get(insid);
							if(map.get(insid).containsKey(name)){
								tmp.put(name, tmp.get(name)+1);
							}
							else{
								tmp.put(name, 1);
							}
							map.put(insid, tmp);
						}
					}
					
				}else{
					Map<String,Integer> tmp  = new HashMap<String,Integer>();;
					String[] dep = depType.split(",");
					if(dep.length==1){
						String name = typeMap.get(Integer.parseInt(depType));
						tmp.put(name, 1);
						
					}else{
						for(int i = 0 ; i < dep.length ; i++){
							String name = typeMap.get(Integer.parseInt(dep[i]));
							tmp.put(name, 1);
						}
					}
					map.put(insid, tmp);
				}
			}	
		}
		System.out.println(map.size());
		
		List<String> res = new ArrayList<String>();
		List<String> res11 = new ArrayList<String>();
	
		for(int ins : mapFac.keySet()){
			try{
				Map<String,Integer> tmp = map.get(ins);
				for(String xx : mapFac.get(ins)){
					if(tmp.containsKey(xx)){
						if(tmp.get(xx) < 3)
							res11.add(ins+"-"+Util.getSchName(ins,1)+"\t"+xx+"\t"+tmp.get(xx));
					}
						
					else
						res11.add(ins+"-"+Util.getSchName(ins,1)+"\t"+xx+"\t"+0);
				}
			}catch(Exception e){
				System.out.println(ins);
			}
			
		}
		FileUtil.ListToFile(res11, "C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/统计每个专业前20的pgm(数量小于3).txt");
		System.out.println("_______----XIAOHE----__________");
	}

	private static void tongjiTop50SchPgm(){
		// TODO Auto-generated method stub
		List<Integer> top50 = getTop50Sch();
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/去掉过时的/program11.json", "utf-8");
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();		
		for(JSONObject json : list){
			if(top50.contains(json.getInt("institute_id")))
				if(map.containsKey(json.getInt("institute_id")))
					map.put(json.getInt("institute_id"), map.get(json.getInt("institute_id"))+1);
				else
					map.put(json.getInt("institute_id"),1);
		}
		map = Util.sortMap(map);
		for(Integer xx : top50)
			System.out.println(xx+"-"+Util.getSchName(xx,1)+"\t"+map.get(xx));
	}

	private static List<Integer> getTop50Sch() {
		// TODO Auto-generated method stub
		List<JSONObject> rank = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/数据补全/QS/json/uniRankRuKu.json", "utf-8");
	
		List<Integer> res = new ArrayList<Integer>();
		res.add(547);
		res.add(584);
		Map<Integer,String> schMap = new HashMap<Integer,String>();
		List<JSONObject> sch = FileUtil.FileToJsonList("C:/Users/强胜/ins.json", "utf-8");
		for(JSONObject json : sch)
			schMap.put(Util.id(json.get("id").toString()),json.getString("country"));
		
		for(JSONObject json : rank){
			try{
				if(schMap.get(json.getInt("institute_id")).equals("USA")){
					res.add(json.getInt("institute_id"));
					if(res.size() > 50)
						return res;
				}
			}catch(Exception e){
				System.out.println(json);
			}
			
				
		}
		return res;
	}

}
