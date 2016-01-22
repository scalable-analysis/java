package com.dulishuo.offer;
import java.util.*;
import java.util.Map.Entry;

import scala.runtime.Int;
import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;


public class SchPgmMatch {
	static int xx = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/寄托/匹配了学校Offer.json", "utf-8");
		List<JSONObject> pgm = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/dataCrawler/申请方/翻译后的/匹配了学校+id.json", "utf-8");
	
		Map<Integer,Map<Integer,String>> map = new HashMap<Integer,Map<Integer,String>>();
		Set<String> set = new HashSet<String>();
		List<String> res = new ArrayList<String>();
		for(JSONObject json : pgm){
			int ins = json.getInt("institute_id");
			Map<Integer,String> tmp = new HashMap<Integer,String>();
			if(map.containsKey(ins))
				tmp = map.get(ins);
			tmp.put(json.getInt("id"), json.getString("title"));
			map.put(ins,tmp);
		}
		System.out.println("read  to  map   end________");
		for(JSONObject json : list){
			json.remove("IELTS");
			json.remove("TOEFL");
			json.remove("GRE");
			json.remove("GPA");
			json.put("from", json.getString("title"));
			int ins = json.getInt("institute_id");
			String title = json.getString("major");
			//System.out.println("process         "+count++);
			json.put("program_id", -1);
			if(map.containsKey(ins)){
				/*Map<Integer,String> tmp = map.get(ins);
				List<String> pgId = new ArrayList<String>();
				for(Entry<Integer,String> entry : tmp.entrySet()){
					if(Util.isSameProgram(title, entry.getValue())){
						pgId.add(entry.getKey().toString());
					}
						
					
				}
				
				//如果能够匹配到
				if(pgId.size() > 0){
					System.out.println(pgId.size());
					
					int[] pgIntA = new int[pgId.size()];
					for(int i = 0 ; i < pgId.size(); i++)
						pgIntA[i] = Integer.parseInt(pgId.get(i));
						
						
					json.put("program_id", pgIntA);
				}*/
				int rst = -1;
				int count = 0;
				int total = 0;
				double perc = 0.00d;
				double percTmp = 0.00d;
				int flag = -1;
				
				Set<String> srcSet = new HashSet<String>();
				Set<String> descSet = new HashSet<String>();
				srcSet = Util.extractWord(title,2);
				
				Map<Integer,String> mapTmpShl = new HashMap<Integer,String>();
				mapTmpShl = map.get(ins);
				
				for(Map.Entry<Integer, String> entrySbj : mapTmpShl.entrySet()){
					int pgmId = entrySbj.getKey();
					String sbjTmp = entrySbj.getValue();
					
					count = 0;
					total = 0;
					
					descSet = Util.extractWord(sbjTmp,2);
					total = descSet.size();
					
					for(String tmpSrc : srcSet){					
						//System.out.println("tmpSrc--"+tmpSrc);
						for(String tmpDesc : descSet){							
								//System.out.println("tmpDesc--"+tmpDesc);
								if(tmpSrc.equals(tmpDesc)){
									count++;
							}							
						}					
					}
				
				percTmp = Double.parseDouble(String.valueOf(count))/Double.parseDouble(String.valueOf(total));
						
					if(percTmp > perc){				
						perc = percTmp;
						flag = pgmId;
					}
					
				}
				if(perc > 0.5){
					rst = flag;
					if(rst != -1){
						System.out.println(title+"\t"+mapTmpShl.get(flag)+"\t"+xx++);
					}
				}
					
					//System.out.println("process____"+xx++);
			}	
			//res.add(json.toString());
		}
		
		//FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/dataCrawler/寄托/匹配了学校+pgm的Offer.json");
		System.out.println("_______________exit_____________");
	}

}
