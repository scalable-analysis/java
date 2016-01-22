package com.dulishuo.newFaculty;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		princeton();
	}

	private static void princeton() {
		// TODO Auto-generated method stub
		String str = "人类学系、艺术与考古学系、天文学系、生物化学系、生物学系、化学系、古典文化系、比较文学系、东亚研究系、经济学系、英语系、地理学系、德国语言与文学系、历史系、数学系、分子生物学系、音乐系、近东系、物理系、哲学系、政治系、心理系、宗教系、罗马语系和语言与文学系、斯拉夫语系的语言与文学、社会学系、统计学系、化学工程系，民用工程系、计算机科学系、电机工程系、机械与航空系";
		List<String> list = new ArrayList<String>();
		list.add("人类学系\tAnthropology");
		list.add("美国黑人研究\tAfrican American Studies");
		list.add("艺术与考古学系\tArt and Archaeology");
		list.add("东亚文学与考古学系\tEast Asian Art and Archaeology");
		list.add("天文学系\tAstrophysical Sciences");
		list.add("生化工程系\tChemical and Biological Engineering");
		list.add("化学系\tChemistry");
		list.add("土木与环境工程\tCivil and Environmental Engineering");
		list.add("大气与海洋科学系\tAtmospheric and Oceanic Sciences");
		list.add("古典文化系\tClassics");
		list.add("比较文学系\tComparative Literature");
		list.add("东亚研究系\tEast Asian Studies");
		list.add("生态学与进化生物学 \tEcology and Evolutionary Biology");
		list.add("经济学系\tEconomics");
		list.add("应用数学系\tApplied and Computational Mathematics ");
		list.add("英语系\tEnglish");
		list.add("德语系\tGerman");
		list.add("法语和意大利语系\tFrench and Italian");
		list.add("西班牙语和葡萄牙语系的语言和文化\tSpanish and Portuguese Languages and Cultures");
		list.add("地理学系\tGeosciences");
		list.add("历史系\tHistory");
		list.add("数学系\tMathematics");
		list.add("分子生物学系\tMolecular Biology");
		list.add("音乐系\tMusic");
		list.add("近东系\tNear Eastern Studies");
		list.add("物理系\tPhysics");
		list.add("哲学系\tPhilosophy");
		list.add("政治系\tPolitics");
		list.add("金融系\tFinance");
		list.add("心理系\tPsychology");
		list.add("神经学\tNeuroscience");
		list.add("宗教系\tReligion");
		list.add("人口研究系\tOffice of Population Research");
		list.add("斯拉夫语系的语言与文学\tSlavic Languages and Literatures");
		list.add("社会学系\tSociology");
		list.add("计算机科学系\tComputer Science");
		list.add("电机工程系\tElectrical Engineering");
		list.add("计量生物学\tQuantitative and Computational Biology");
		list.add("运筹学与金融工程\tOperations Research and Financial Engineering");
		list.add("机械与航空系\tMechanical and Aerospace Engineering");
		list.add("工程和应用科学院\tEngineering and Applied Science");
		list.add("建筑学院\tArchitecture");
		list.add("威尔逊公共和国际事务学院\tWoodrow Wilson School of Public and International Affairs");
		int count = 4371;
		for(int i = 0 ; i < list.size(); i++){
			JSONObject json = new JSONObject();
			json.put("id", count++);
			json.put("name_chinese", list.get(i).split("\t")[0]);
			json.put("name", list.get(i).split("\t")[1]);
			json.put("institute_id", 239);
			json.put("istranslated", 0);
			json.put("is_new", 1);
			if(i< list.size()-3)
				json.put("type", "department");
			else
				json.put("type", "school");
			
			
		}
		
	}

}
