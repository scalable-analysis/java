package com.dulishuo.dict;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class Institute1013 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> school = FileUtil.FileToList("C:/Users/强胜/Desktop/dict/school.csv");
		
		Set<String> ids = new HashSet<String>();
		for(String xx : school){
			ids.add(xx.substring(0,4)+"regex:");
		}
		for(String xx : school){
			if(ids.contains(xx.substring(0, 10)))
				ids.remove(xx.substring(0, 10));
		}
		for(String xx : ids)
			System.out.println(xx.substring(0, 3)+",regex:"+Util.getSchName(Integer.parseInt(xx.substring(0, 3)),2));
		
		System.out.println("______Exit--------");
	}

}
