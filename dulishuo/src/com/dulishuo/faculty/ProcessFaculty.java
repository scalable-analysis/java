package com.dulishuo.faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

/*
 * author:xiaohe
 * date:2015-10-29
 * description:此类只要用来处理faculty字典的一些问题
 */
public class ProcessFaculty {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" . . . start . . . ");
		long start = System.currentTimeMillis();
		sortByIns();
		
		
		
		long end = System.currentTimeMillis();
		System.out.println(" . . . end . . . use time : " + ( end - start ) + " ms.");
	}

	private static void sortByIns() {
		// TODO Auto-generated method stub
		List<String> list = FileUtil.FileToList("H:/独立说/backup/faculty.csv");
		Map<String,Integer> map = new HashMap<String,Integer>();
		List<String> res = new ArrayList<String>();
		for(String each : list){
			try{
				int startIndex = each.indexOf(":school:");
				map.put( each , Integer.parseInt(each.substring(startIndex+8, startIndex+11)));
			}catch(Exception e){
				res.add(each);
			}
		}
		map = Util.sortMap(map);
		
		for(String xx : map.keySet())
			res.add(xx);
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/xxFac.csv");
	}

}
