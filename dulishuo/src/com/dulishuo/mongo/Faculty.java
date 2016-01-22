package com.dulishuo.mongo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Faculty {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" . . . start . . .");
		long start = System.currentTimeMillis();

		test();

		long end = System.currentTimeMillis();
		System.out.println(" . . . end . . . use time : " + (end - start)
				+ "ms ");

	}

	private static void test() {
		// TODO Auto-generated method stub
		// String url = "localhost";
		List<String> hehe = FileUtil.FileToList("H:/独立说/backup/usa1125.csv");
		List<Integer> fac = new ArrayList<Integer>();
		List<String> res = new ArrayList<String>();
		for(String xx : hehe){
			fac.add(Integer.parseInt(xx.split(",")[0]));
		}

		fac.add(584);
		fac.add(547);
		fac.add(650);
		fac.add(602);
		fac.add(599);
		fac.add(561);
		fac.add(783);
		fac.add(629);
		fac.add(686);
		String url = "123.57.250.189";
		int port = 27017;

		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);
		DBCollection fans = db.getCollection("faculty");
		DBCursor find = fans.find();
		Set<String> set = new HashSet<String>();
		int count = 1;
		int insId = -1;
		while (find.hasNext()) {
			try{
				DBObject obj = find.next();
				if(obj.containsField("institute_id")){
					insId = Util.id(obj.get("institute_id").toString());
					if(fac.contains(insId)){
						res.add(count+",entity:school:"+insId+",regex:"+obj.get("name"));
						res.add(count++ +",entity:school:"+insId+",regex:"+obj.get("name_chinese"));
					}
				}
			}catch(Exception e){}
			
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/fac.txt");
	}

}
