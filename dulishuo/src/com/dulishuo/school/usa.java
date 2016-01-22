package com.dulishuo.school;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dulishuo.util.FileUtil;

public class usa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		System.out.println("start . . . ");
		
		//test();
		test0207();
		
		System.out.println("end . . . ");
		long end = System.currentTimeMillis();
	}

	private static void test0207() {
		// TODO Auto-generated method stub
		List<String> src = FileUtil.FileToList("C:/Users/强胜/Desktop/数据补全/学院列表/2016美国院校/thr.txt");
		
		for(String xx : src){
			if(xx.substring(xx.lastIndexOf(":")).contains(","))
				System.out.println(xx);
		}
			
	}

	private static void test() {
		// TODO Auto-generated method stub
		List<String> src = FileUtil.FileToList("C:/Users/强胜/Desktop/数据补全/学院列表/2016美国院校/origin.txt");
		
		/*去重*/
		Set<String> fir = new HashSet<String>();
		for(String xx : src){
			//System.out.println(xx.substring(xx.indexOf("school:")+7,xx.indexOf("school:")+10));
			if(xx.length() > 4)
				fir.add(xx);
		}
		
		Map<String , List<String>> map = new HashMap<String , List<String>>();
		List<String> thi = new ArrayList<String>();
		String insId;
		List<String> tmpList;
		for(String xx : fir){
			if(!xx.contains("entity")){
				if(xx.contains("regex:"))
					thi.add(xx.substring(0, xx.indexOf("regex:")+6)+xx.substring(xx.indexOf("regex:")+6).trim());
				else if(xx.contains("keyword:"))
					thi.add(xx.substring(0, xx.indexOf("keyword:")+7)+xx.substring(xx.indexOf("keyword:")+7).trim());
				continue;
			}
			insId = xx.substring(xx.indexOf("school:")+7,xx.indexOf("school:")+10);
			if(map.containsKey(insId))
				tmpList = map.get(insId);
			else
				tmpList = new ArrayList<String>();
			tmpList.add(xx);
			map.put(insId, tmpList);
		}
		System.out.println(fir.size());
		
		System.out.println("分组完毕 . . . ");
		for(String xx : map.keySet()){
			System.out.println("process :[institute_id]: " + xx +" , with size : " + map.get(xx).size() );
			thi.addAll(removeDuplicate(map.get(xx)));
		}
		
		List<String> ress = new ArrayList<String>();
		Set<String> settt = new HashSet<String>();
		for(String xx : thi)
			settt.add(xx);
		for(String xx : settt){
			if(xx.contains("regex:") && xx.substring(xx.lastIndexOf("regex:")).contains(","))
				ress.add(xx.substring(0, xx.indexOf("regex:")+6)+"\""+xx.substring(xx.indexOf("regex:")+6)+"\"");
			else if(xx.contains("keyword:") && xx.substring(xx.lastIndexOf("keyword:")).contains(","))
				ress.add(xx.substring(0, xx.indexOf("keyword:")+7)+"\""+xx.substring(xx.indexOf("keyword:")+7)+"\"");
			else
				ress.add(xx);
		}
		
		FileUtil.ListToFile(ress, "C:/Users/强胜/Desktop/数据补全/学院列表/2016美国院校/thr.txt");
	}

	private static List<String> removeDuplicate(List<String> list) {
		// TODO Auto-generated method stub
		List<String> res = new ArrayList<String>();
		
		Map<String , String> map = new HashMap<String , String>();
		for(String xx : list){
			if(xx.contains("regex:"))
				map.put(xx.substring(xx.indexOf("regex:")+6) , xx.substring(0, xx.indexOf("regex:")+6)+xx.substring(xx.indexOf("regex:")+6).trim());
			else if(xx.contains("keyword:"))
				res.add(xx.substring(0, xx.indexOf("keyword:")+7)+xx.substring(xx.indexOf("keyword:")+7).trim());
		}
		String firEach;
		String secEach;
		for(int i = 0 ; i < list.size() ; i ++){
			if(list.get(i).contains("regex:")){
				firEach = list.get(i).substring(list.get(i).indexOf("regex:")+6);
				List<String> tmp = new ArrayList<String>();
				for(int j = 0; j < list.size() ; j++){
					secEach = list.get(j).substring(list.get(j).indexOf("regex:")+6);
					if(firEach.length() > secEach.length() && firEach.contains(secEach))
						tmp.add(secEach);
				}
				if(tmp.size() > 0){
					tmp.add(firEach);
					for(String each : getMinEle(tmp))
						res.add(map.get(each));
				}else
					res.add(map.get(firEach));
			}		
		}
		return res;
	}

	private static List<String> getMinEle(List<String> list) {
		// TODO Auto-generated method stub
		List<String> res = new ArrayList<String>();
		
		int min = 100;
		for(int i = 0 ; i < list.size() ; i++)
			if(list.get(i).length() < min)
				min = list.get(i).length();
		
		for(int i = 0 ; i < list.size() ; i++)
			if(list.get(i).length() == min)
				res.add(list.get(i));
			
		return res;
	}

}
