package com.dulishuo.mongo;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Rank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" . . . start . . .");
		long start = System.currentTimeMillis();
		
		shenQFupdate();
		
		long end = System.currentTimeMillis();
		System.out.println(" . . . end . . . use time : " + (end - start)
				+ "ms ");
	}

	private static void shenQFupdate() {
		// TODO Auto-generated method stub
		HSSFSheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/hehe.xls", 0);
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(int i = 0 ; i < 42; i++){
			HSSFRow row = sht.getRow(i);
			
			map.put(row.getCell(0).toString(), Util.id(row.getCell(3).toString()));
		}
		
		System.out.println(map.size());
		
		String url = "123.57.250.189";
		//String url = "localhost";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, port, dbName);

		DBCollection rank = db.getCollection("rank");
		DBCursor find = rank.find();
		while (find.hasNext()) {
		DBObject obj = find.next();
			try{
				if(map.containsKey(obj.get("institute").toString())){
					System.out.println(obj.get("institute").toString());
					BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject().append(
							"institute_id", map.get(obj.get("institute").toString())));
					if (rank.update(obj, newDocument, false, true).getN() >= 1)
						System.out.println("result :   success");
					else
						System.out.println("result :   fail");
				}
			}catch(Exception e){
				
			}
			
		}
	}

}
