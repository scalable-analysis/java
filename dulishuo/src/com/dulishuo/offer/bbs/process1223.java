package com.dulishuo.offer.bbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.sl.usermodel.Sheet;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class process1223 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . ");
		long start = System.currentTimeMillis();
		
		ins();
		//test();
		
		long end = System.currentTimeMillis();
		System.out.println("end . . . use time : " + (end-start) +"ms.");
	}

	private static void test() {
		// TODO Auto-generated method stub
		String str = "EE/ss";
		if(str.contains("/"))
			System.out.println("true");
	}

	private static void ins() {
		// TODO Auto-generated method stub
		HSSFSheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/圣诞/古城XXX.xls", 0);
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(int i = 0 ; i < sht.getLastRowNum()+1 ; i++){
			map.put(sht.getRow(i).getCell(0).toString(), Util.id(sht.getRow(i).getCell(2).toString()));
		}
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/offerLuntan1223.json", "utf-8");
		Iterator tmp ; 
		String key;
		List<String> res = new ArrayList<String>();
		Set<String> maj = new HashSet<String>();
		JSONObject eachJson;
		int majTmp;
		int count = 24439;
		for(JSONObject xx : list){
			JSONObject js = xx.getJSONObject("oldInstitute");
			xx.remove("_id");
			String[] majTmpA =  xx.getString("oldMajor").split("/");
			if(majTmpA.length > 1){
				Set<Integer> setMaj = new HashSet<Integer>();
				for(int i = 0 ; i < majTmpA.length ; i++){
					setMaj.add(Util.getFactypeBBS(majTmpA[i]));
				}
				int[] dep = new int[setMaj.size()];
				int flag = 0 ;
				for(int xxx : setMaj){
					dep[flag] = xxx;
					flag++;
				}
				
				xx.put("department_type", dep);
			}else{
				majTmp = Util.getFactypeBBS(majTmpA[0]);
				int[] dep = new int[1];
				dep[0] = majTmp;
				xx.put("department_type", dep);
			}
			tmp =  js.keys();
			
			/*if(xx.containsKey("toefl")){
				JSONObject toj = new JSONObject();
				toj.put("total", Integer.parseInt(xx.getJSONObject("toefl").getString("total")));
				toj.put("listeng", Integer.parseInt(xx.getJSONObject("toefl").getString("listening")));
				toj.put("speaking", Integer.parseInt(xx.getJSONObject("toefl").getString("speaking")));
				toj.put("reading", Integer.parseInt(xx.getJSONObject("toefl").getString("reading")));
				toj.put("writing", Integer.parseInt(xx.getJSONObject("toefl").getString("writing")));
				toj.put("other", "");
				xx.put("toefl", toj);
			}
			if(xx.containsKey("gre")){
				JSONObject rej = new JSONObject();
				rej .put("total", Integer.parseInt(xx.getJSONObject("gre").getString("total")));
				rej .put("aw", Integer.parseInt(xx.getJSONObject("gre").getString("aw")));
				rej .put("q", Integer.parseInt(xx.getJSONObject("gre").getString("q")));
				rej .put("v", Integer.parseInt(xx.getJSONObject("gre").getString("v")));
				rej .put("other", "");
				xx.put("gre", rej );
			}
			if(xx.containsKey("gmat")){
				JSONObject gmj = new JSONObject();
				gmj.put("total", Integer.parseInt(xx.getJSONObject("gmat").getString("total")));
				gmj.put("aw", Integer.parseInt(xx.getJSONObject("gmat").getString("aw")));
				gmj .put("other", "");
				xx.put("gmat", gmj);
			}
			if(xx.containsKey("ielts")){
				JSONObject iej = new JSONObject();
				iej.put("total", Float.parseFloat(xx.getJSONObject("ielts").getString("total")));
				iej.put("listeng", Float.parseFloat(xx.getJSONObject("ielts").getString("listening")));
				iej.put("speaking", Float.parseFloat(xx.getJSONObject("ielts").getString("speaking")));
				iej.put("reading",Float.parseFloat(xx.getJSONObject("ielts").getString("reading")));
				iej.put("writing", Float.parseFloat(xx.getJSONObject("ielts").getString("writing")));
				iej.put("other", "");
				xx.put("ielts", iej);
			}*/
			if(!xx.containsKey("toefl"))
				xx.put("toefl", "");
			if(!xx.containsKey("ielts"))
				xx.put("ielts", "");
			if(!xx.containsKey("gre"))
				xx.put("gre", "");
			if(!xx.containsKey("gmat"))
				xx.put("gmat", "");
			
			xx.put("year", Integer.parseInt(xx.getString("year")));
			
			while(tmp.hasNext()){
				key = (String) tmp.next();
				eachJson = xx;
				xx.remove("oldInstitute");
				xx.put("institute_id", map.get(key));
				xx.put("institute", Util.getSchName(map.get(key), 2));
				xx.put("tinstitute", Util.getSchName(map.get(key), 1));
				xx.put("id", count++);
				res.add(xx.toString());
			}
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/圣诞/bbs/luntan1224.json");
	}

}
