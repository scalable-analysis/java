package com.dulishuo.mongo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Institute {

	static Map<String, String> pgmType = new HashMap<String, String>();
	static {
		pgmType.put("cs", "38");
		pgmType.put("pr", "67");
		pgmType.put("tesol", "68");
		pgmType.put("law", "43");
		pgmType.put("economics", "27");
		pgmType.put("journalism", "66");
		pgmType.put("marketing", "47");
		pgmType.put("finance", "61");
		pgmType.put("accounting", "7");
		pgmType.put("mis", "52");
		pgmType.put("ee", "40");
		pgmType.put("me", "6");
		pgmType.put("ce", "54");
		pgmType.put("environment", "45");
		pgmType.put("biology", "14");
		pgmType.put("civil", "51");
		pgmType.put("materials", "10");
	}
	static Map<String, String> repgmType = new HashMap<String, String>();
	static {
		repgmType.put("38", "cs");
		repgmType.put("67", "pr");
		repgmType.put("68", "tesol");
		repgmType.put("43", "law");
		repgmType.put("27", "economics");
		repgmType.put("66", "journalism");
		repgmType.put("47", "marketing");
		repgmType.put("61", "finance");
		repgmType.put("7", "accounting");
		repgmType.put("52", "mis");
		repgmType.put("40", "ee");
		repgmType.put("6", "me");
		repgmType.put("54", "ce");
		repgmType.put("45", "environment");
		repgmType.put("14", "biology");
		repgmType.put("51", "civil");
		repgmType.put("10", "materials");
	}
	static Map<Integer, Integer> gre = new HashMap<Integer, Integer>();
	static {
		gre.put(1, 330);
		gre.put(2, 325);
		gre.put(3, 320);
		gre.put(4, 315);
		gre.put(5, 308);
		gre.put(6, 300);
		gre.put(7, 295);
		gre.put(8, 290);
		gre.put(9, 285);
		gre.put(10, 280);
	}
	static Map<Integer, Integer> toefl = new HashMap<Integer, Integer>();
	static {
		toefl.put(1, 110);
		toefl.put(2, 108);
		toefl.put(3, 105);
		toefl.put(4, 103);
		toefl.put(5, 100);
		toefl.put(6, 97);
		toefl.put(7, 94);
		toefl.put(8, 90);
		toefl.put(9, 85);
		toefl.put(10, 80);
	}
	static Map<Integer, Float> gpa = new HashMap<Integer, Float>();
	static {
		gpa.put(1, 3.7f);
		gpa.put(2, 3.6f);
		gpa.put(3, 3.5f);
		gpa.put(4, 3.4f);
		gpa.put(5, 3.3f);
		gpa.put(6, 3.2f);
		gpa.put(7, 3.1f);
		gpa.put(8, 3.0f);
		gpa.put(9, 2.9f);
		gpa.put(10, 2.8f);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" . . . start . . .");
		long start = System.currentTimeMillis();

		// test();
		// updateCity();
		// buquanLanguage();
		// haha();
		// test0109();
		 //test0110();
		//test1011();
		test0113();

		long end = System.currentTimeMillis();
		System.out.println(" . . . end . . . use time : " + (end - start)
				+ "ms ");
	}

	private static void test0113() {
		// TODO Auto-generated method stub
		//String url = "localhost";
		String url = "123.57.250.189";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, dbName);

		DBCollection institute = db.getCollection("institute");
		DBCursor find = institute.find();
		while(find.hasNext()){
			DBObject obj = find.next();
			int id = Util.id(obj.get("id").toString());
			
			if (obj.containsField("application_difficulty")) {
				JSONObject apply = JSONObject.fromObject(obj
						.get("application_difficulty"));
				Iterator itt = apply.keys();
				String key;
				while (itt.hasNext()) {
					key = itt.next().toString();
					JSONObject tmpJ = apply.getJSONObject(key);
					try{
						/*if(tmpJ.getDouble("gpa") < 1.0f){
							System.out.println("gpa:"+tmpJ.getDouble("gpa"));
							System.out.println(id+"\t"+key);
							}
						if(tmpJ.getInt("gre") < 200){
							System.out.println("gre"+tmpJ.getInt("gre"));
							System.out.println(id+"\t"+key);
							}
						if(tmpJ.getInt("toefl") < 70){
							System.out.println("toefl"+tmpJ.getInt("toefl"));
							System.out.println(id+"\t"+key);
						}*/
						if(tmpJ.get("rank").toString().equals("N/A")){
							System.out.println("rank"+tmpJ.get("rank"));
							System.out.println(id+"\t"+key);
						}
							
					}catch(Exception e){}
				}
			}
		}
	}

	private static void test1011() {
		// TODO Auto-generated method stub
		String url = "localhost";
		// String url = "123.57.250.189";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, dbName);

		DBCollection rank = db.getCollection("rank");
		DBCursor find = rank.find();
		int value = 0;
		int insId = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<String> resss = new ArrayList<String>();
		while (find.hasNext()) {
			DBObject obj = find.next();
			if (Util.id(obj.get("year").toString()) == 2016
					&& Util.id(obj.get("rank_type_id").toString()) == 43
					&& Util.id(obj.get("institute_id").toString()) != -1) {
				map.put(Util.id(obj.get("institute_id").toString()),
						Util.id(obj.get("value").toString()));

			}
		}

		map = Util.sortMap(map);

		DBCollection institute = db.getCollection("institute");
		Map<Integer , Float> mapp = new HashMap<Integer , Float>();
		Map<Integer , Integer> mappp = new HashMap<Integer , Integer>();
		DBCursor fid = institute.find();
		while (fid.hasNext()) {
			DBObject obj = fid.next();
			if (obj.get("country").toString().toLowerCase().equals("usa")) {
				try{
					if (obj.containsField("accept_rate") && obj.get("accept_rate").toString().length()>2) {
						int idd = Util.id(obj.get("id").toString());
						mapp.put(idd, Float.parseFloat(obj.get("accept_rate").toString().replace("%", "")) / 100);
						mappp.put(idd, map.get(idd));
					}
				}catch(Exception e){
					System.out.println(obj.get("id"));
				}
				
			}
		}
		
		mappp = Util.sortMap(mappp);
		for(int xx : mappp.keySet())
			System.out.println(xx+"\t"+mappp.get(xx));
	}

	private static void test0110() {
		// TODO Auto-generated method stub
		String url = "localhost";
		// String url = "123.57.250.189";
		int port = 27017;

		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, dbName);

		DBCollection program = db.getCollection("program");
		DBCursor find = program.find();

		Map<String, String> map = new HashMap<String, String>();
		String month = "每年";
		while (find.hasNext()) {
			DBObject obj = find.next();
			Set<String> facSet = Util.extractStrArrayToSet(obj.get(
					"department_type").toString());

			if (facSet.size() == 0)
				continue;

			List<String> facInsSet = new ArrayList<String>();
			for (String xx : facSet) {
				if (repgmType.containsKey(xx)) {
					facInsSet.add(repgmType.get(xx));
				}
			}
			// 如果该program不包含选校开放专业
			if (facInsSet.size() == 0)
				continue;
			int insid = Util.id(obj.get("institute_id").toString());
			if (obj.containsField("deadline")) {
				Set<String> set = Util.extractStrArrayToSet(obj.get("deadline")
						.toString());
				if (set.size() == 0)
					continue;
				int[] monA = new int[set.size()];
				int flag = 0;
				for (String xx : set) {
					try {
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd");
						Long time = new Long(xx) * 1000;
						String d = format.format(time);
						month = d.substring(5, 7);
						if (month.substring(0, 1).equals("0"))
							month = month.substring(1);

						if (set.size() == 1) {
							for (String xxFac : facInsSet) {
								map.put(insid + "-" + xxFac, "每年" + month + "月");
							}
							continue;
						}
						if (month.equals("12"))
							monA[flag] = -1;
						else if (month.equals("11"))
							monA[flag] = -2;
						else
							monA[flag] = Integer.parseInt(month);
						flag++;

					} catch (Exception e) {
						break;
					}
				}

				if (set.size() == 1)
					continue;

				Set<Integer> seet = new HashSet<Integer>();
				for (int i = 0; i < monA.length; i++)
					seet.add(monA[i]);

				int[] monB = new int[seet.size()];
				int flaggg = 0;
				for (int xx : seet)
					monB[flaggg++] = xx;

				for (int i = 0; i < monB.length - 1; i++) {
					for (int j = 0; j < monB.length - i - 1; j++) {
						if (monB[j] > monB[j + 1]) {
							monB[j] = monB[j] + monB[j + 1];
							monB[j + 1] = monB[j] - monB[j + 1];
							monB[j] = monB[j] - monB[j + 1];
						}
					}
				}

				for (int i = 0; i < monB.length; i++) {
					if (monB[i] == -1)
						monB[i] = 12;
					else if (monB[i] == -2)
						monB[i] = 11;
				}

				for (String xxFac : facInsSet) {
					map.put(insid + "-" + xxFac, monthToStr(monB));
				}
			}
		}

		for (String xx : map.keySet())
			System.out.println(xx + "\t" + map.get(xx));

		// update institute
		db = MongoUtil.getConnection("123.57.250.189", "dulishuo");
		//db = MongoUtil.getConnection("url", "dulishuo");
		DBCollection institute = db.getCollection("institute");
		DBCursor update = institute.find();

		int idd;
		int count_succ = 0;
		int count_fail = 0;
		while (update.hasNext()) {
			DBObject obj = update.next();
			idd = Util.id(obj.get("id").toString());
			if (obj.containsField("application_difficulty")) {
				JSONObject apply = JSONObject.fromObject(obj
						.get("application_difficulty"));
				Iterator itt = apply.keys();
				String key;
				while (itt.hasNext()) {
					key = itt.next().toString();
					JSONObject facJson = apply.getJSONObject(key);
					if (map.containsKey(idd + "-" + key))
						facJson.put("deadline", map.get(idd + "-" + key));
					else
						facJson.put("deadline", "-");

					apply.put(key, facJson);
				}

				BasicDBObject newDocument = new BasicDBObject();
				newDocument.append("$set", new BasicDBObject().append(
						"application_difficulty", apply));
				if (institute.update(obj, newDocument, false, true).getN() >= 1)
					System.out.println("success : " + obj.get("id"));
				else
					System.out.println("error:" + obj.get("id"));
			}
		}

		System.out.println("count_succ:" + count_succ);
		System.out.println("count_fail:" + count_fail);

	}

	private static String monthToStr(int[] str) {
		String res = "每年";
		for (int i = 0; i < str.length; i++) {
			if (i == str.length - 1) {
				res += str[i] + "月";
			} else {
				res += str[i] + "月、";
			}
		}

		return res;
	}

	private static String monthIntToString(int src) {
		switch (src) {
		case 1:
			return "一";
		case 2:
			return "二";
		case 3:
			return "三";
		case 4:
			return "四";
		case 5:
			return "五";
		case 6:
			return "六";
		case 7:
			return "七";
		case 8:
			return "八";
		case 9:
			return "九";
		case 10:
			return "十";
		case 11:
			return "十一";
		case 12:
			return "十二";
		default:
			return "一";
		}
	}

	private static void test0109() {
		// TODO Auto-generated method stub
		String url = "localhost";
		// String url = "123.57.250.189";
		int port = 27017;

		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, dbName);
		DBCollection institute = db.getCollection("institute");
		DBCursor find = institute.find();
		DBCursor findd = institute.find();
		Map<Integer, Float> mapp = new HashMap<Integer, Float>();
		while (findd.hasNext()) {
			DBObject obj = findd.next();
			try {
				mapp.put(
						Util.id(obj.get("id").toString()),
						Float.parseFloat(obj.get("accept_rate").toString()
								.replace("%", "")) / 100);
			} catch (Exception e) {
				System.out.println(obj.get("id"));
			}
		}

		Map<String, Map<Integer, Float>> map = new HashMap<String, Map<Integer, Float>>();
		while (find.hasNext()) {
			DBObject obj = find.next();
			int iddd = Util.id(obj.get("id").toString());

			if (obj.containsField("application_difficulty")) {
				JSONObject apply = JSONObject.fromObject(obj.get(
						"application_difficulty").toString());
				Iterator it = apply.keys();
				String fac;
				Map<Integer, Float> tmp;
				int level;
				JSONObject jsss;

				while (it.hasNext()) {
					fac = (String) it.next();
					jsss = apply.getJSONObject(fac);
					level = jsss.getInt("level");
					if (map.containsKey(fac)) {
						tmp = map.get(fac);

						if (tmp.containsKey(level)) {
							if (mapp.containsKey(iddd))
								tmp.put(level,
										(tmp.get(level) + mapp.get(iddd)) / 2);
						} else {
							if (mapp.containsKey(iddd))
								tmp.put(level, mapp.get(iddd));
						}
						map.put(fac, tmp);
					} else {
						tmp = new HashMap<Integer, Float>();
						if (mapp.containsKey(iddd)) {
							tmp.put(level, mapp.get(iddd));
							map.put(fac, tmp);
						}

					}

				}
			}
		}

		System.out.println("size of map is : " + map.size());
		List<String> ress = new ArrayList<String>();
		// print fac \t level \t rate
		System.out.println("facultytype\tlevel\trate");
		for (String xx : map.keySet()) {
			System.out
					.println("---------------------------------------------------------");
			for (int yy : map.get(xx).keySet()) {

				System.out.println(xx + "\t" + yy + "\t" + map.get(xx).get(yy));
				ress.add(xx + "\t" + yy + "\t" + map.get(xx).get(yy));

			}
			System.out.println("");
		}
	}

	static void haha() {
		// TODO Auto-generated method stub
		//String url = "localhost";
		String url = "123.57.250.189";
		int port = 27017;

		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, dbName);
		DBCollection institute = db.getCollection("institute");
		DBCursor find = institute.find();
		String facType;
		String greTmp;
		String gpaTmp;
		String toeflTmp;
		int level;
		while (find.hasNext()) {
			DBObject obj = find.next();
			try {
				if (obj.containsField("application_difficulty")) {

					JSONObject apply = JSONObject.fromObject(obj.get(
							"application_difficulty").toString());
					Set<String> set = apply.keySet();
					if (Util.id(obj.get("id").toString()) == 230) {
						for (String xx : set)
							System.out.println(xx);
					}

					JSONObject jss = apply;

					for (String key : set) {

						facType = key;
						if (pgmType.containsKey(facType)) {
							if (Util.id(obj.get("id").toString()) == 230)
								System.out.println(facType);

							JSONObject facTmpObj = apply.getJSONObject(facType);
							level = facTmpObj.getInt("level");
							greTmp = facTmpObj.get("gre").toString();
							gpaTmp = facTmpObj.get("gpa").toString();
							toeflTmp = facTmpObj.get("toefl").toString();
							/*
							 * if(Util.id(obj.get("id").toString()) == 230 &&
							 * facType.equals("marketing")){
							 * System.out.println("gre:" + greTmp);
							 * System.out.println("gpa:" + toeflTmp);
							 * System.out.println("toefl:" + toeflTmp); }
							 */
							if (greTmp.contains("-")) {
								System.out.println(greTmp);
								facTmpObj.put("gre", gre.get(level));
							}

							if (toeflTmp.contains("-")) {

								facTmpObj.put("toefl", toefl.get(level));
							}

							if (gpaTmp.contains("-") || Float.parseFloat(gpaTmp) < 1.0f) {

								facTmpObj.put("gpa", gpa.get(level));
							}

							jss.put(facType, facTmpObj);
						} else
							jss.put(facType, apply.get(facType));

					}
					BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject().append(
							"application_difficulty", jss));
					if (institute.update(obj, newDocument, false, true).getN() >= 1)
						System.out.println("success : " + obj.get("id"));
					else
						System.out.println("error:" + obj.get("id"));
				}
			} catch (Exception e) {
			}
		}
	}

	private static void buquanLanguage() {
		// TODO Auto-generated method stub
		String url = "123.57.250.189";
		// String url = "localhost";
		int port = 27017;

		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, dbName);
		DBCollection institute = db.getCollection("institute");
		DBCursor find = institute.find();

		Map<String, Map<Integer, Float>> toeflMap = new HashMap<String, Map<Integer, Float>>();
		Map<String, Map<Integer, Float>> greMap = new HashMap<String, Map<Integer, Float>>();
		Map<String, Map<Integer, Float>> gpaMap = new HashMap<String, Map<Integer, Float>>();
		int level = 0;
		String facType = "";
		Map<Integer, Float> toeflMtmp;
		Map<Integer, Float> greMtmp;
		Map<Integer, Float> gpaMtmp;
		String greTmp;
		String gpaTmp;
		String toeflTmp;
		while (find.hasNext()) {
			DBObject obj = find.next();
			try {
				if (obj.containsField("application_difficulty")) {

					JSONObject apply = JSONObject.fromObject(obj.get(
							"application_difficulty").toString());

					for (Object key : apply.keySet()) {

						if (!pgmType.containsKey(key.toString()))
							continue;

						facType = (String) key;
						JSONObject facTmpObj = apply.getJSONObject(facType);
						level = facTmpObj.getInt("level");
						greTmp = facTmpObj.get("gre").toString();
						gpaTmp = facTmpObj.get("gpa").toString();
						toeflTmp = facTmpObj.get("toefl").toString();
						if (!toeflTmp.contains("-")) {
							if (toeflMap.containsKey(facType)) {
								toeflMtmp = toeflMap.get(facType);
								if (toeflMtmp.containsKey(level))
									toeflMtmp
											.put(level,
													(Float.parseFloat(toeflTmp) + toeflMtmp
															.get(level)) / 2);
								else
									toeflMtmp.put(level,
											Float.parseFloat(toeflTmp));
							} else {
								toeflMtmp = new HashMap<Integer, Float>();
								toeflMtmp
										.put(level, Float.parseFloat(toeflTmp));
							}
							toeflMap.put(facType, toeflMtmp);
						}

						if (!greTmp.contains("-")) {
							if (greMap.containsKey(facType)) {
								greMtmp = greMap.get(facType);
								if (greMtmp.containsKey(level))
									greMtmp.put(level, (Float
											.parseFloat(greTmp) + greMtmp
											.get(level)) / 2);
								else
									greMtmp.put(level, Float.parseFloat(greTmp));
							} else {
								greMtmp = new HashMap<Integer, Float>();
								greMtmp.put(level, Float.parseFloat(greTmp));
							}
							greMap.put(facType, greMtmp);
						}

						if (!gpaTmp.contains("-")) {
							if (gpaMap.containsKey(facType)) {
								gpaMtmp = gpaMap.get(facType);
								if (gpaMtmp.containsKey(level))
									gpaMtmp.put(level, (Float
											.parseFloat(gpaTmp) + gpaMtmp
											.get(level)) / 2);
								else
									gpaMtmp.put(level, Float.parseFloat(gpaTmp));
							} else {
								gpaMtmp = new HashMap<Integer, Float>();
								gpaMtmp.put(level, Float.parseFloat(gpaTmp));
							}
							gpaMap.put(facType, gpaMtmp);
						}

					}

					/*
					 * BasicDBObject newDocument = new BasicDBObject();
					 * newDocument.append("$set", new BasicDBObject().append(
					 * "application_difficulty", apply)); if
					 * (institute.update(obj, newDocument, false, true).getN()
					 * >= 1) System.out.println("result :   success"); else
					 * System.out.println("result :   fail");
					 */

				}
			} catch (Exception e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}

		}

		for (String xx : toeflMap.keySet()) {
			for (int yy : toeflMap.get(xx).keySet())
				System.out.println(xx + "\t" + yy + "\t"
						+ toeflMap.get(xx).get(yy));
		}

		System.out.println("read end . . . ");

		DBCursor findd = institute.find();
		while (findd.hasNext()) {
			DBObject object = findd.next();
			try {
				if (object.containsField("application_difficulty")) {

					JSONObject apply = JSONObject.fromObject(object.get(
							"application_difficulty").toString());

					for (Object key : apply.keySet()) {
						facType = (String) key;

						if (!pgmType.containsKey(facType))
							continue;

						JSONObject facTmpObj = apply.getJSONObject(facType);
						level = facTmpObj.getInt("level");
						greTmp = facTmpObj.get("gre").toString();
						gpaTmp = facTmpObj.get("gpa").toString();
						toeflTmp = facTmpObj.get("toefl").toString();

						if (greTmp.contains("-")) {
							if (greMap.containsKey(facType)
									&& greMap.get(facType).containsKey(level))
								facTmpObj.put("gre", (int) Math.rint(greMap
										.get(facType).get(level)));
							else
								facTmpObj.put("gre", gre.get(level));
						}

						if (toeflTmp.contains("-")) {
							if (toeflMap.containsKey(facType)
									&& toeflMap.get(facType).containsKey(level)) {
								facTmpObj.put("toefl", (int) Math.rint(toeflMap
										.get(facType).get(level)));
							} else
								facTmpObj.put("toefl", toefl.get(level));
						}

						if (gpaTmp.contains("-")) {
							if (gpaMap.containsKey(facType)
									&& gpaMap.get(facType).containsKey(level)) {
								String hehe = gpaMap.get(facType).get(level)
										.toString();
								Float gpaScore = -1.0f;
								if (hehe.length() > 4)
									gpaScore = Float.parseFloat(hehe.substring(
											0, hehe.indexOf(".") + 3));
								else
									gpaScore = Float.parseFloat(hehe);
								if (gpaScore.toString().contains("-"))
									facTmpObj.put("gpa", gpa.get(level));
								else
									facTmpObj.put("gpa", gpaScore);
							} else
								facTmpObj.put("gpa", gpa.get(level));
						}

						apply.remove(facType);
						apply.put(facType, facTmpObj);
					}

					BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject().append(
							"application_difficulty", apply));
					if (institute.update(object, newDocument, false, true)
							.getN() >= 1)
						System.out.println("result :   success");
					else
						System.out.println(object.get("id"));
				}
			} catch (Exception e) {

			}
		}

	}

	private static void updateCity() {
		// TODO Auto-generated method stub

		List<String> list = FileUtil
				.FileToList("C:/Users/强胜/Desktop/institute1121.csv");
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (String xx : list) {
			int city_id = Util.id(xx.split(",")[0]);
			int ins_id = Util.id(xx.split(",")[1].replace("id:", ""));
			// System.out.println(city_id+"\t"+ins_id);
			// map.put(ins_id,city_id);
		}

		Map<Integer, String> locMap = new HashMap<Integer, String>();
		String url = "123.57.250.189";
		// String url = "localhost";
		int port = 27017;

		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, dbName);
		DBCollection city = db.getCollection("city");
		DBCursor loc = city.find();
		while (loc.hasNext()) {
			DBObject objLoc = loc.next();
			if (Util.id(objLoc.get("id").toString()) > 79) {
				System.out.println(objLoc.get("id").toString() + ",keyword:"
						+ objLoc.get("title"));
				System.out.println(objLoc.get("id").toString() + ",keyword:"
						+ objLoc.get("etitle"));
			}

		}
		/*
		 * Util.printMapItoS(locMap); url = "localhost";
		 * 
		 * 
		 * DBCollection institute = db.getCollection("institute"); DBCursor find
		 * = institute.find();
		 * 
		 * 
		 * while (find.hasNext()) { DBObject object = find.next(); int id =
		 * Util.id(object.get("id").toString()); if(map.containsKey(id)){
		 * 
		 * json.put("city_id", map.get(id)); json.put("city_name",
		 * locMap.get(map.get(id))); BasicDBObject newDocument = new
		 * BasicDBObject(); newDocument.append("$set", new
		 * BasicDBObject("city_id", map.get(id))); newDocument.append("$set",
		 * new BasicDBObject("city_name", locMap.get(map.get(id)))); if
		 * (institute.update(object, newDocument, false, true).getN() >= 1)
		 * System.out.println("result :   success"); else
		 * System.out.println("result :   fail"); } }
		 */
	}

	private static void test() {
		// TODO Auto-generated method stub
		/*
		 * HSSFSheet sht = FileUtil.getExcelSht(
		 * "c:/Users/强胜/Desktop/ttttttttttt.xls", 0); Map<Integer, String> map =
		 * new HashMap<Integer, String>(); for (int i = 0; i <
		 * sht.getLastRowNum() + 1; i++) {
		 * map.put(Util.id(sht.getRow(i).getCell(0).toString()), "$" +
		 * sht.getRow(i).getCell(1).toString().substring(0, 2) + "," +
		 * sht.getRow(i).getCell(1).toString().substring(2, 5)); }
		 */

		String url = "123.57.250.189";
		// String url = "localhost";
		int port = 27017;

		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url, dbName);
		DBCollection institute = db.getCollection("institute");
		DBCollection offercase = db.getCollection("offercase");

		DBCursor update = offercase.find();
		DBCursor find = institute.find();
		Map<Integer, String> map = new HashMap<Integer, String>();
		while (find.hasNext()) {
			DBObject obj = find.next();

			if (obj.containsField("logo")) {
				JSONObject json = JSONObject.fromObject(obj.get("logo")
						.toString());
				map.put(Util.id(obj.get("id").toString()), json.get("153")
						.toString());
			}
		}
		List<String> res = new ArrayList<String>();
		int idd;
		while (update.hasNext()) {
			DBObject obj = update.next();
			idd = Util.id(obj.get("id").toString());
			JSONArray result = JSONArray.fromObject(obj.get("result")
					.toString());
			JSONObject jss;
			for (int i = 0; i < result.size(); i++) {
				jss = result.getJSONObject(i);
				if (jss.getInt("result") == 0) {
					JSONObject resoffer = new JSONObject();
					resoffer.put("id", jss.getInt("result"));
					resoffer.put("zh_name", jss.getString("zh_name"));
					resoffer.put("en_name", jss.getString("en_name"));
					resoffer.put("logo", map.get(jss.getInt("id")));
					BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject(
							"offer_result", resoffer));

					if (offercase.update(obj, newDocument, false, true).getN() >= 1)
						System.out.println("result :   success");
					else
						System.out.println("result :   fail");
					break;
				}
			}
		}

		/*
		 * BasicDBObject newDocument = new BasicDBObject();
		 * newDocument.append("$set", new
		 * BasicDBObject("application_difficulty", apply));
		 * 
		 * if (institute.update(object, newDocument, false, true).getN() >= 1)
		 * System.out.println("result :   success"); else
		 * System.out.println("result :   fail");
		 */
	}

}
