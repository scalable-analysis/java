package com.dulishuo.shenqingfang.program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import scala.runtime.Int;
import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;

public class AddFacultyType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("start . . . \n");
		long start = System.currentTimeMillis();
		
		test();
		
		long end = System.currentTimeMillis();
		System.out.println("\nEnd . . .use time :  "+(end - start) +" ms");
	}

	private static void test() {
		// TODO Auto-generated method stub
		/*List<JSONObject> program = FileUtil.FileToJsonList("C:/Users/强胜/program1130.json", "utf-8");
		
		Map<String,Set<String>> map = new HashMap<String,Set<String>>();
		int flag = 1;
		for(JSONObject json : program){
			try{
				System.out.println(flag++);
				String dep = json.get("department_type").toString().replace("[", "").replace("]", "").replace(" ", "");
				int size = dep.split(",").length;
				Set<String> set = new HashSet<String>();
				String tmp = "";
				if(size == 1){
					tmp = json.getString("title").trim();
					
					if(map.containsKey(tmp))
						set = map.get(tmp);
					else
						set = new HashSet<String>();
					
					set.add(dep.replace("\"", ""));
					map.put(tmp, set);
				}else{
					
					tmp = json.getString("title").trim();
					
					if(map.containsKey(tmp))
						set = map.get(tmp);
					else
						set = new HashSet<String>();
					for(int i = 0 ; i < size ; i++){
						set.add(dep.split(",")[i].replace("\"", ""));
					}
					map.put(tmp, set);
				}
				
			}catch(Exception e){
				
			}
		}
		*/
		int flag = 1;
		/*Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("pharmacy\t3", 4);
		map.put("fine_arts\t2", 4);
		map.put("public_management_administration\t1", 2);
		map.put("anthropology\t4", 4);
		map.put("mechanical_engineering\t6", 6);
		map.put("computer_engineering\t6", 5);
		map.put("accounting\t7", 2);
		map.put("architecture\t8", 2);
		map.put("religion\t9", 3);
		map.put("psychology\t11", 4);
		map.put("materials\t10", 4);
		map.put("political_science\t12",);
		map.put("medicine\t13",);
		map.put("biology\t14",);
		map.put("philosophy\t16",);
		map.put("nuclear_engineering\t15",);
		map.put("agriculture\t17",);
		map.put("literature\t18",);
		map.put("environmental_science\t19",);
		map.put("supply_chain_and_logistics\t20",);
		map.put("criminology\t21",);
		map.put("health\t22",);
		map.put("biomedical_engineering\t23",);
		map.put("social_work\t24",);
		map.put("public_policy_analysis\t25",);
		map.put("public_health\t26",);
		map.put("economics\t27",);
		map.put("language\t28",);
		map.put("english\t29",);
		map.put("management\t30",);
		map.put("actuarial_science\t31",);
		map.put("financial_engineering\t32",);
		map.put("physics\t33",);
		map.put("sociology\t34",);
		map.put("biological_agricultural_engineering\t35",);
		map.put("education\t36",);
		map.put("mathematics\t37",);
		map.put("computer_science\t38",);
		map.put("statistics\t39",);
		map.put("electrical_engineering\t40",);
		map.put("public_affairs\t41",);
		map.put("aerospace\t42",);
		map.put("law\t43",);
		map.put("geosciences\t44",);
		map.put("environmental_engineering\t45",);
		map.put("marketing\t47",);
		map.put("communication\t46",);
		map.put("strategy\t49",);
		map.put("epidemiology\t50",);
		map.put("urban_planning\t48",);
		map.put("civil_engineering\t51",);
		map.put("information_systems\t52",);
		map.put("mba\t53",);
		map.put("chemical_engineering\t54",);
		map.put("entrepreneurship\t55",);
		map.put("history\t56",);
		map.put("industrial_engineering\t57",);
		map.put("chemistry\t58",);
		map.put("international_business\t60",);
		map.put("finance\t61",);
		map.put("human_resource_management\t59",);
		map.put("operations\t62",);
		map.put("area_studies\t63",);
		map.put("dentistry\t64",);
		map.put("Veterinary Science\t65",);
		map.put("journalism\t66",);
		map.put("public_relations\t67",);
		map.put("tesol\t68",);*/
		int noo = 1;
		
		/*List<JSONObject> newprogram = FileUtil.FileToJsonList("C:/Users/强胜/Desktop/我是你爹/hahaha1.json","utf-8");
		List<String> res = new ArrayList<String>();
		Set<String> heheda =  new HashSet<String>();
		for(JSONObject json : newprogram){
			System.out.println(flag++);
			if(map.containsKey(json.getString("title"))){
				noo++;
				heheda = map.get(json.getString("title"));
				int[] tmp = new int[heheda.size()];
				int i = 0;
				for(String xx : heheda){
					tmp[i] = Integer.parseInt(xx.toString());
					i++;
				}
				json.put("department_type", heheda);
				
			}
			else{
				Int[] tmp = new Int[0];
				json.put("department_type", tmp);
			}
			res.add(json.toString());
		}
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/姐姐.json");
		
		System.out.println("noo : "+noo);*/
	}

}
