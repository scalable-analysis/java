package com.dulishuo.xuanxiao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.dulishuo.mongo.MongoUtil;
import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/*
 * author : xiaohe
 * date : 2015-10-16
 * description: 选校系统-将总结的所有专业的学校的等级和难度系数入库
 * example:
 * 'application-difficulty': {
 'law': {
 'level': 1,
 'score': 100, 
 }
 }
 */

public class ApplyCondition {
	// 学校等级level与申请难度系数对应关系
	static Map<String, Integer> levelMap = new HashMap<String, Integer>();
	static {
		levelMap.put("一等", 1);
		levelMap.put("二等", 2);
		levelMap.put("三等", 3);
		levelMap.put("四等", 4);
		levelMap.put("五等", 5);
		levelMap.put("六等", 6);
		levelMap.put("七等", 7);
	}

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
		pgmType.put("materials", "10");
		pgmType.put("ce", "12");
		pgmType.put("me", "6");
		pgmType.put("environment", "45");
		pgmType.put("civil", "14");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start . . . \n");
		long start = System.currentTimeMillis();

		String fac = "economics";
		String path = "C:/Users/强胜/Desktop/数据补全/选校系统/学校评论/美硕学校难度系数分级-点评（economics）-V1.0.xls";
		 //readHot(fac, path);
		readComment(fac, path);
		 //readApplyDiff(fac, path);
		//test();

		// 更新数据库
		 //update(fac);
		// hotChange();
		// addPgmRank();
		// addLanguage();
		//addLanguageRequirement();

		long end = System.currentTimeMillis();
		System.out.println("end use time : " + (end - start) + " ms .");
	}

	private static void addLanguageRequirement() {
		// TODO Auto-generated method stub
		List<JSONObject> file = FileUtil.FileToJsonList(
				"C:/Users/强胜/offer160103.json", "utf-8");
		statistics(file);
	}

	private static void addLanguage() {
		// TODO Auto-generated method stub

		Map<String, String> pgmType = new HashMap<String, String>();
		pgmType.put("cs", "38");
		pgmType.put("pr", "67");
		// pgmType.put("tesol","68");
		pgmType.put("law", "43");
		pgmType.put("economics", "27");
		pgmType.put("journalism", "66");
		pgmType.put("marketing", "47");
		pgmType.put("finance", "61");
		pgmType.put("accounting", "7");
		pgmType.put("mis", "52");

		Set<String> typeSet = new HashSet<String>();
		typeSet.add("38");
		typeSet.add("67");
		// typeSet.add("68");
		typeSet.add("43");
		typeSet.add("27");
		typeSet.add("66");
		typeSet.add("47");
		typeSet.add("61");
		typeSet.add("7");
		typeSet.add("52");

		Map<String, Map<String, Map<String, List<String>>>> rigou = new HashMap<String, Map<String, Map<String, List<String>>>>();

		List<JSONObject> program = FileUtil.FileToJsonList(
				"H:/独立说/backup/program1117.json", "utf-8");

		for (JSONObject json : program) {
			try {
				int insid = Util.id(json.getString("institute_id"));
				String depTmp = json.get("department_type").toString()
						.replace("[", "").replace("]", "").replace(" ", "");
				int size = depTmp.split(",").length;
				if (size > 2) {

				} else {
					if (typeSet.contains(depTmp)) {

					}
				}
			} catch (Exception e) {

			}

		}
	}

	private static void addPgmRank() {
		// TODO Auto-generated method stub

		List<String> rank = FileUtil.FileToList("C:/Users/强胜/rank1123.csv");
		Map<String, Map<String, String>> rankMap = new HashMap<String, Map<String, String>>();
		for (String xx : rank) {
			String insid = xx.split(",")[0].toString();
			String typeid = xx.split(",")[1];
			String rankv = xx.split(",")[2];

			Map<String, String> tmpRankMap;
			if (!rankMap.containsKey(insid))
				tmpRankMap = new HashMap<String, String>();
			else
				tmpRankMap = rankMap.get(insid);
			tmpRankMap.put(typeid, rankv);
			rankMap.put(insid, tmpRankMap);

		}
		if (rankMap.containsKey("183"))
			System.out.println(rankMap.get("230"));

		System.out.println("rank read end . . . ");

		Map<String, String> pgmType = new HashMap<String, String>();
		/*
		 * pgmType.put("cs","38"); pgmType.put("pr","67");
		 * pgmType.put("tesol","68"); pgmType.put("law","43");
		 * pgmType.put("economics","27"); pgmType.put("journalism","66");
		 * pgmType.put("marketing","47"); pgmType.put("finance","61");
		 * pgmType.put("accounting","7"); pgmType.put("mis","52");
		 */
		pgmType.put("cs", "65");
		pgmType.put("pr", "63");
		pgmType.put("tesol", "68");
		pgmType.put("law", "86");
		pgmType.put("economics", "67");
		pgmType.put("journalism", "63");
		pgmType.put("marketing", "89");
		pgmType.put("finance", "75");
		pgmType.put("accounting", "50");
		pgmType.put("mis", "83");
		pgmType.put("biology", "14");

		Map<String, String> pgmRankCount = new HashMap<String, String>();
		pgmRankCount.put("cs", "99");
		pgmRankCount.put("pr", "58");
		pgmRankCount.put("tesol", "103");
		pgmRankCount.put("law", "74");
		pgmRankCount.put("economics", "80");
		pgmRankCount.put("journalism", "58");
		pgmRankCount.put("marketing", "24");
		pgmRankCount.put("finance", "27");
		pgmRankCount.put("accounting", "27");
		pgmRankCount.put("mis", "20");
		pgmRankCount.put("biology", "14");

		// String url = "localhost";
		String url = "123.57.250.189";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url,  dbName);

		DBCollection institute = db.getCollection("institute");
		DBCursor find = institute.find();
		List<String> fail = new ArrayList<String>();
		int flag = 1;
		while (find.hasNext()) {
			// System.out.println("process   "+flag++);
			DBObject obj = find.next();
			String tmpInsId = String.valueOf(Util.id(obj.get("id").toString()));
			try {
				/*
				 * if (obj.containsField("application_difficulty")) { JSONObject
				 * apply = JSONObject.fromObject(obj.get(
				 * "application_difficulty").toString()); for(String key :
				 * pgmType.keySet()){ if(apply.containsKey(key) &&
				 * rankMap.containsKey(tmpInsId)){
				 * if(rankMap.get(tmpInsId).containsKey(pgmType.get(key))){
				 * JSONObject tmpJson = apply.getJSONObject(key);
				 * tmpJson.put("rank"
				 * ,rankMap.get(tmpInsId).get(pgmType.get(key)));
				 * apply.remove(key); apply.put(key, tmpJson); } } }
				 * 
				 * BasicDBObject newDocument = new BasicDBObject();
				 * newDocument.append("$set", new BasicDBObject().append(
				 * "application_difficulty", apply)); if (institute.update(obj,
				 * newDocument, false, true).getN() >= 1)
				 * System.out.println("result :   success"); else
				 * System.out.println("result :   fail");
				 * 
				 * }
				 */
				if (obj.containsField("application_difficulty")) {
					JSONObject apply = JSONObject.fromObject(obj.get(
							"application_difficulty").toString());

					for (String key : pgmType.keySet()) {
						if (apply.containsKey(key)
								&& rankMap.containsKey(tmpInsId)) {
							if (rankMap.get(tmpInsId).containsKey(
									pgmType.get(key))) {
								JSONObject tmpJson = apply.getJSONObject(key);
								apply.remove(key);
								tmpJson.put(
										"rank",
										rankMap.get(tmpInsId).get(
												pgmType.get(key)));

								apply.put(key, tmpJson);
							} else {
								JSONObject tmpJson = apply.getJSONObject(key);
								apply.remove(key);
								// tmpJson.put("rank",pgmRankCount.get(key)+"+");
								tmpJson.put("rank", "N/A");
								apply.put(key, tmpJson);
							}

						} else if (apply.containsKey(key)
								&& !rankMap.containsKey(tmpInsId)) {

							JSONObject tmpJson = apply.getJSONObject(key);
							apply.remove(key);
							// tmpJson.put("rank",pgmRankCount.get(key)+"+");
							tmpJson.put("rank", "N/A");

							apply.put(key, tmpJson);

						}

					}/*
					 * 
					 * for(String key : typeSetTmp){
					 * if(rankMap.containsKey(tmpInsId)){
					 * if(rankMap.get(tmpInsId).containsKey(pgmType.get(key))){
					 * JSONObject tmpJson = apply.getJSONObject(key);
					 * 
					 * tmpJson.put("rank",rankMap.get(tmpInsId).get(pgmType.get(key
					 * ))); apply.remove(key); apply.put(key, tmpJson); }else{
					 * JSONObject tmpJson = apply.getJSONObject(key);
					 * 
					 * tmpJson.put("rank",pgmRankCount.get(key)+"+");
					 * apply.remove(key); apply.put(key, tmpJson); } }else{
					 * JSONObject tmpJson = apply.getJSONObject(key);
					 * tmpJson.put("rank",pgmRankCount.get(key)+"+");
					 * apply.remove(key); apply.put(key, tmpJson); } }
					 */
					BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject().append(
							"application_difficulty", apply));
					if (institute.update(obj, newDocument, false, true).getN() >= 1)
						System.out.println("result :   success22222");
					else
						System.out.println("result :   fail22222");
				}

			} catch (Exception e) {
				fail.add("error	id	: " + obj.get("id").toString());
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}

		if (fail.size() > 0) {
			System.out.println("出错的记录...");
			System.out.println("----------------------------------------");
			for (String xx : fail)
				System.out.println(xx);
			System.out.println("----------------------------------------");
		}
	}

	private static void readApplyDiff(String fac, String path) {
		// TODO Auto-generated method stub
		Sheet insSheet = FileUtil.getExcelSht(
				"C:/Users/强胜/Desktop/数据补全/选校系统/理工文商/选校专业.xls", 12);
		Row row;

		Map<String, Integer> idsMap = new HashMap<String, Integer>();
		for (int j = 0; j < insSheet.getLastRowNum()+1; j++) {
			row = insSheet.getRow(j);
			if (row.getCell(2) != null) {
				if(idsMap.containsKey(row.getCell(0).toString()))
					System.out.println(row.getCell(0).toString());
				idsMap.put(
						row.getCell(0).toString(),
						Integer.parseInt(row.getCell(2).toString()
								.replace(".0", "")));
			} else {
				System.out.println(row.getCell(0));
			}
		}
		System.out.println("end : read institute to map :   " + idsMap.size());
		Sheet sheet = FileUtil.getExcelSht(path, 0);

		int level = -1;
		float score = -1; // 默认值为-1，即无任何难度
		int hot = -1; // 1-热 2-中 3-冷
		String hotTmp;
		Map<Integer, JSONObject> res = new HashMap<Integer, JSONObject>();

		for (int i = 1; i < sheet.getLastRowNum()+1; i++) {
			// 初始化
			if(sheet.getRow(i).getCell(1)==null || sheet.getRow(i).getCell(1).toString().length()<1)
				continue;
			score = -1;
			row = sheet.getRow(i);
			try {
				if (row.getCell(2) != null
						&& !row.getCell(2).toString().replace(" ", "")
								.equals("")) {
					level = Util.id(row.getCell(2).toString()
							.substring(0, 1));
				}

				if (row.getCell(4) != null && !row.getCell(4).toString().replace(" ", "").equals("")){
					if(row.getCell(4).toString().contains("-"))
						score = Integer.parseInt(row.getCell(4).toString().split("-")[1].replace(".0", ""));
					else
						score = Integer.parseInt(row.getCell(4).toString().replace(".0", ""));
				}
					

				if (row.getCell(3) != null
						&& !row.getCell(3).toString().replace(" ", "")
								.equals("")) {
					hotTmp = row.getCell(3).toString().replace(" ", "");
					if (hotTmp.equals("热"))
						hot = 1;
					else if (hotTmp.equals("中"))
						hot = 2;
					else
						hot = 3;
				}

			} catch (Exception e) {
				System.out.println(e.toString());
				System.out.println(row.getCell(1) + "\t" + row.getCell(2));
			}

			// 为学校匹配institute_id
			int instid = -1; // 默认值为-1，即未配到到institute库

			// 要配置的地方，学校英文名的列
			String name = row.getCell(1).toString();

			if (idsMap.containsKey(name)) {
				JSONObject faculty = new JSONObject();
				JSONObject child = new JSONObject();

				child.put("level", level);
				child.put("score", score);
				child.put("hot", hot);
				faculty.put(fac, child);
				res.put(idsMap.get(name), faculty);
			} else {
				System.out.println("name:" + name);
			}
		}
		System.out.println("read " + fac + " from excel end . . .");
		System.out.println("end : read scores and level  to map :   "
				+ res.size());
		System.out.println();

		System.out.println("start insert to mongo . . .");
		addApplictionCondition(res, fac);

		System.out.println("end insert to mongo . . . \n");
	}

	private static void test() {
		// TODO Auto-generated method stub
		List<String> list = FileUtil
				.FileToList("C:/Users/强胜/Desktop/数据补全/选校系统/text.txt");

		for (String xx : list) {
			int id = Util.getInstitute(xx);
			if (id != -1)
				System.out.println(xx + "\t" + Util.getSchName(id, 1) + "\t"
						+ id);
			else
				System.out.println(xx);
		}
	}
	// 提取出hot
	private static void readComment(String fac, String path) {
		// TODO Auto-generated method stub
		Sheet insSheet = FileUtil.getExcelSht(
				"C:/Users/强胜/Desktop/数据补全/选校系统/理工文商/选校专业.xls", 5);
		Row row;

		Map<String, Integer> idsMap = new HashMap<String, Integer>();
		for (int j = 0; j < insSheet.getLastRowNum()+1; j++) {
			row = insSheet.getRow(j);
			if (row.getCell(2) != null) {
				idsMap.put(
						row.getCell(0).toString(),
						Integer.parseInt(row.getCell(2).toString()
								.replace(".0", "")));
			} else {
				System.out.println(row.getCell(0));
			}
		}
		System.out.println("end : read institute to map :   " + idsMap.size());
		Sheet sheet = FileUtil.getExcelSht(path, 0);

		String cmt = ""; // 学校该专业的点评
		Map<Integer, String> cmtMap = new HashMap<Integer, String>();

		for (int i = 1; i < sheet.getLastRowNum()+1; i++) {
			// 初始化

			row = sheet.getRow(i);
			if (row.getCell(6) != null && row.getCell(6).toString().length() > 2) {
				cmt = row.getCell(6).toString().trim();
			}

			// 为学校匹配institute_id
			int instid = -1; // 默认值为-1，即未配到到institute库

			// 要配置的地方，学校英文名的列
			String name = row.getCell(1).toString();

			if (idsMap.containsKey(name)) {
				cmtMap.put(idsMap.get(name), cmt);
			} else {
				System.out.println("name:" + name);
			}
		}
		System.out.println("read " + fac + " from excel end . . .");
		System.out.println("end : read scores and level  to map :   "
				+ cmtMap.size());
		System.out.println();

		System.out.println("start insert to mongo . . .");
		addComment(cmtMap, fac);
		System.out.println("end insert to mongo . . . \n");
	}
	// 提取出hot
	private static void readHot(String fac, String path) {
		// TODO Auto-generated method stub
		Sheet insSheet = FileUtil.getExcelSht(
				"C:/Users/强胜/Desktop/数据补全/选校系统/理工文商/选校专业.xls", 2);
		Row row;

		Map<String, Integer> idsMap = new HashMap<String, Integer>();
		for (int j = 0; j < 62; j++) {
			row = insSheet.getRow(j);
			if (row.getCell(2) != null) {
				idsMap.put(
						row.getCell(0).toString(),
						Integer.parseInt(row.getCell(2).toString()
								.replace(".0", "")));
			} else {
				System.out.println(row.getCell(0));
			}
		}
		System.out.println("end : read institute to map :   " + idsMap.size());
		Sheet sheet = FileUtil.getExcelSht(path, 0);

		String hot = "冷"; // 学校该专业的热度， 中 冷 热
		Map<Integer, String> hotMap = new HashMap<Integer, String>();

		for (int i = 1; i < 63; i++) {
			// 初始化

			row = sheet.getRow(i);

			if (i < 52) {
				if (row.getCell(3) != null
						&& !row.getCell(3).toString().replace(" ", "")
								.equals("")) {
					hot = row.getCell(3).toString().replace(" ", "");
				}
			} else {
				hot = "冷";
			}

			// 为学校匹配institute_id
			int instid = -1; // 默认值为-1，即未配到到institute库

			// 要配置的地方，学校英文名的列
			String name = row.getCell(0).toString();

			if (idsMap.containsKey(name)) {
				hotMap.put(idsMap.get(name), hot);
			} else {
				System.out.println("name:" + name);
			}
		}
		System.out.println("read " + fac + " from excel end . . .");
		System.out.println("end : read scores and level  to map :   "
				+ hotMap.size());
		System.out.println();

		System.out.println("start insert to mongo . . .");
		//addHot(hotMap, fac);
		System.out.println("end insert to mongo . . . \n");
	}
	
	private static void addComment(Map<Integer, String> map, String fac) {
		// TODO Auto-generated method stub
		//String url = "localhost"; //本地环境
		String url = "123.57.250.189"; // 服务器环境
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url,  dbName);

		DBCollection institute = db.getCollection("institute");
		DBCursor find = institute.find();
		List<String> fail = new ArrayList<String>();
		int flag = 1;
		while (find.hasNext()) {
			DBObject obj = find.next();
			try {
				int id = Util.id(obj.get("id").toString());
				if (map.containsKey(id)) {

					JSONObject apply = JSONObject.fromObject(obj.get(
							"application_difficulty").toString());

					if (apply.containsKey(fac)) {

						JSONObject hotJson = apply.getJSONObject(fac);
						hotJson.put("comment", map.get(id));
						apply.remove(fac);
						apply.put(fac, hotJson);

						System.out.println("add hot  " + fac + " id : " + id
								+ "  record line  :  " + flag++ + "\n" + apply);

						BasicDBObject newDocument = new BasicDBObject();
						newDocument.put("$set", new BasicDBObject().append(
								"application_difficulty", apply));

						if (institute.update(obj, newDocument, false, true)
								.getN() >= 1)
							System.out.println("result :   success");
						else
							System.out.println("result :   fail");

						System.out.println();
						map.remove(id);
					}
				}
			} catch (Exception e) {
				System.out.println(e.toString());
				fail.add("error	id	: " + obj.get("id").toString());
			}
		}

		if (map.size() > 0) {
			System.out.println("------map 剩余的学校");
			for (int xx : map.keySet())
				System.out.println(xx + " \t" + Util.getSchName(xx, 1) + "\t"
						+ map.get(xx));
			System.out.println("           ----------------         \n");
		}

		if (fail.size() > 0) {
			System.out.println("出错的记录...");
			System.out.println("----------------------------------------");
			for (String xx : fail)
				System.out.println(xx);
			System.out.println("----------------------------------------");
		}
	}

	private static void addHot(Map<Integer, String> map, String fac) {
		// TODO Auto-generated method stub
		// String url = "localhost"; //本地环境
		String url = "123.57.250.189"; // 服务器环境
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url,  dbName);

		DBCollection institute = db.getCollection("institute");
		DBCursor find = institute.find();
		List<String> fail = new ArrayList<String>();
		int flag = 1;
		while (find.hasNext()) {
			DBObject obj = find.next();
			try {
				int id = Util.id(obj.get("id").toString());
				if (map.containsKey(id)) {

					JSONObject apply = JSONObject.fromObject(obj.get(
							"application_difficulty").toString());

					if (apply.containsKey(fac)) {

						JSONObject hotJson = apply.getJSONObject(fac);
						hotJson.put("hot", map.get(id));
						apply.remove(fac);
						apply.put(fac, hotJson);

						System.out.println("add hot  " + fac + " id : " + id
								+ "  record line  :  " + flag++ + "\n" + apply);

						BasicDBObject newDocument = new BasicDBObject();
						newDocument.put("$set", new BasicDBObject().append(
								"application_difficulty", apply));

						if (institute.update(obj, newDocument, false, true)
								.getN() >= 1)
							System.out.println("result :   success");
						else
							System.out.println("result :   fail");

						System.out.println();
						map.remove(id);
					}
				}
			} catch (Exception e) {
				System.out.println(e.toString());
				fail.add("error	id	: " + obj.get("id").toString());
			}
		}

		if (map.size() > 0) {
			System.out.println("------map 剩余的学校");
			for (int xx : map.keySet())
				System.out.println(xx + " \t" + Util.getSchName(xx, 1) + "\t"
						+ map.get(xx));
			System.out.println("           ----------------         \n");
		}

		if (fail.size() > 0) {
			System.out.println("出错的记录...");
			System.out.println("----------------------------------------");
			for (String xx : fail)
				System.out.println(xx);
			System.out.println("----------------------------------------");
		}
	}

	private static void addApplictionCondition(Map<Integer, JSONObject> map,
			String fac) {
		// TODO Auto-generated method stu

		//String url = "localhost"; //本地环境
		String url = "123.57.250.189"; // 服务器环境
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url,  dbName);

		DBCollection institute = db.getCollection("institute");
		DBCursor find = institute.find();
		List<String> fail = new ArrayList<String>();
		int flag = 1;
		int flag1 = 1;
		int flag2 = 1;
		while (find.hasNext()) {
			DBObject obj = find.next();
			try {
				int id = Util.id(obj.get("id").toString());
				if (map.containsKey(id)) {

					JSONObject json = map.get(id);
					JSONObject apply;

					if (obj.containsField("application_difficulty")) {
						apply = JSONObject.fromObject(obj.get(
								"application_difficulty").toString());
					} else {
						apply = new JSONObject();

					}

					apply.put(fac, json.get(fac));
					System.out.println("update  " + fac + " id : " + id
							+ "  record line  :  " + flag++ + "\n" + apply);
					BasicDBObject newDocument = new BasicDBObject();
					newDocument.put("$set", new BasicDBObject().append(
							"application_difficulty", apply));
					if (institute.update(obj, newDocument, false, true).getN() >= 1)
						System.out.println("result :   success");
					else
						System.out.println("result :   fail");
					System.out.println();
					map.remove(id);
				}
			} catch (Exception e) {
				System.out.println(e.toString());
				fail.add("error	id	: " + obj.get("id").toString());
			}
		}
		if (map.size() > 0) {
			for (int xx : map.keySet())
				System.out.println(xx + " \t" + map.get(xx));
		}

		if (fail.size() > 0) {
			System.out.println("出错的记录...");
			System.out.println("----------------------------------------");
			for (String xx : fail)
				System.out.println(xx);
			System.out.println("----------------------------------------");
		}
	}

	private static void update(String fac) {
		// TODO Auto-generated method stub

		String url = "localhost";
		//String url = "123.57.250.189";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url,  dbName);

		DBCollection institute = db.getCollection("institute");
		DBCursor find = institute.find();
		List<String> fail = new ArrayList<String>();
		int flag = 1;
		while (find.hasNext()) {
			DBObject obj = find.next();
			try {
				if (obj.containsField("application_difficulty")) {
					JSONObject apply = JSONObject.fromObject(obj.get(
							"application_difficulty").toString());
					
						for(String xx : pgmType.keySet()){
							if(apply.containsKey(xx)){
								JSONObject json = apply.getJSONObject(xx);
								/*if(json.getString("rank").indexOf("N") != -1)
									json.put("rank", 999);
								else if(Integer.parseInt(json.getString("rank")) < 1000)
									json.put("rank", Integer.parseInt(json.getString("rank")));*/
								json.put("gre", -1);
								json.put("toefl", -1);
								json.put("gpa", -1.0f);
								apply.remove(xx);
								apply.put(xx, json);
							}
							
						}
						BasicDBObject newDocument = new BasicDBObject();
						newDocument.append("$set", new BasicDBObject().append(
								"application_difficulty", apply));
						if (institute.update(obj, newDocument, false, true)
								.getN() >= 1)
							System.out.println("result :   success");
						else
							System.out.println("result :   fail");
					
				}
			} catch (Exception e) {
				System.out.println(e.toString());
				fail.add("error	id	: " + obj.get("id").toString());
			}
		}

		if (fail.size() > 0) {
			System.out.println("出错的记录...");
			System.out.println("----------------------------------------");
			for (String xx : fail)
				System.out.println(xx);
			System.out.println("----------------------------------------");
		}

	}

	private static void hotChange() {
		// TODO Auto-generated method stub
		// String url = "localhost";
		String url = "123.57.250.189";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url,  dbName);

		DBCollection institute = db.getCollection("institute");
		DBCursor find = institute.find();
		List<String> fail = new ArrayList<String>();
		int flag = 1;
		while (find.hasNext()) {
			DBObject obj = find.next();
			try {
				if (obj.containsField("application_difficulty")) {
					JSONObject apply = JSONObject.fromObject(obj.get(
							"application_difficulty").toString());
					for (Object key : apply.keySet()) {
						JSONObject facTmpObj = apply
								.getJSONObject((String) key);
						if (facTmpObj.containsKey("hot")) {
							String hot = facTmpObj.getString("hot");
							if (hot.equals("热"))
								facTmpObj.put("hot", 1);
							else if (hot.equals("中"))
								facTmpObj.put("hot", 2);
							else
								facTmpObj.put("hot", 3);
							apply.put(key, facTmpObj);
						}
					}

					BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject().append(
							"application_difficulty", apply));
					if (institute.update(obj, newDocument, false, true).getN() >= 1)
						System.out.println("result :   success");
					else
						System.out.println("result :   fail");

				}
			} catch (Exception e) {
				fail.add("error	id	: " + obj.get("id").toString());
			}
		}

		if (fail.size() > 0) {
			System.out.println("出错的记录...");
			System.out.println("----------------------------------------");
			for (String xx : fail)
				System.out.println(xx);
			System.out.println("----------------------------------------");
		}

	}

	/*
	 * author: xiaohe desc: 统计offer三围分数平均值 ( gpa 、toefl/ielts 、gre/gmat) date:
	 * 2015012-02
	 */
	private static void statistics(List<JSONObject> quchonghouList) {
		// TODO Auto-generated method stub

		Map<Integer, Map<Integer, Map<String, Float>>> gpa = new HashMap<Integer, Map<Integer, Map<String, Float>>>();
		Map<Integer, Map<Integer, Map<String, Float>>> toefl = new HashMap<Integer, Map<Integer, Map<String, Float>>>();
		Map<Integer, Map<Integer, Map<String, Float>>> gre = new HashMap<Integer, Map<Integer, Map<String, Float>>>();
		int dep = -1;
		int insId = -1;
		Map<Integer, Map<String, Float>> gpaTmp;
		Map<Integer, Map<String, Float>> toeflTmp;
		Map<Integer, Map<String, Float>> greTmp;
		Map<String, Float> gpaDepTmp;
		Map<String, Float> toeflDepTmp;
		Map<String, Float> greDepTmp;

		List<Integer> depList;
		for (JSONObject json : quchonghouList) {
			if (json.containsKey("result")) {
				if (Integer.parseInt(json.get("result").toString()) < 2) {
					try {
						if(json.get("department_type").toString().length() < 3)
							continue;
						depList = Util.extractIntArrayToList(json.get(
								"department_type").toString());
						
						insId = Util.id(json.get("institute_id").toString());

						for (int i = 0; i < depList.size(); i++) {
							dep = depList.get(i);
							// gpa
							try {
								if(json.get("gpa").toString().length() > 0){
									float gpa_total = Float.parseFloat(json
											.get("gpa").toString());
									if (gpa.containsKey(insId)) {
										gpaTmp = gpa.get(insId);
										if (gpaTmp.containsKey(dep)) {
											gpaDepTmp = gpaTmp.get(dep);
											gpaDepTmp.put("amount",
													gpaDepTmp.get("amount") + 1f);
											gpaDepTmp.put("sumGrade",
													gpaDepTmp.get("sumGrade")
															+ gpa_total);
										} else {
											gpaDepTmp = new HashMap<String, Float>();
											gpaDepTmp.put("amount", 1f);
											gpaDepTmp.put("sumGrade", gpa_total);
										}
										gpaTmp.put(dep, gpaDepTmp);
									} else {
										gpaTmp = new HashMap<Integer, Map<String, Float>>();
										gpaDepTmp = new HashMap<String, Float>();
										gpaDepTmp.put("amount", 1f);
										gpaDepTmp.put("sumGrade", gpa_total);
										gpaTmp.put(dep, gpaDepTmp);
									}
									gpa.put(insId, gpaTmp);
								}
							} catch (Exception e) {
								System.out.println("error - type[gpa] : "
										+ e.toString());
								System.out.println(json.get("gpa").toString());
							}

							// toefl
							try {
								if (json.containsKey("toefl")
										&& json.getJSONObject("toefl")
												.get("total").toString().length() > 2) {
									float toefl_total = Float.parseFloat(json
											.getJSONObject("toefl").get(
													"total").toString());
									if (toefl.containsKey(insId)) {
										toeflTmp = toefl.get(insId);
										if (toeflTmp.containsKey(dep)) {
											toeflDepTmp = toeflTmp.get(dep);
											toeflDepTmp
													.put("amount", toeflDepTmp
															.get("amount") + 1f);
											toeflDepTmp.put("sumGrade",
													toeflDepTmp.get("sumGrade")
															+ toefl_total);
										} else {
											toeflDepTmp = new HashMap<String, Float>();
											toeflDepTmp.put("amount", 1f);
											toeflDepTmp.put("sumGrade",
													toefl_total);
										}
										toeflTmp.put(dep, toeflDepTmp);
									} else {
										toeflTmp = new HashMap<Integer, Map<String, Float>>();
										toeflDepTmp = new HashMap<String, Float>();
										toeflDepTmp.put("amount", 1f);
										toeflDepTmp
												.put("sumGrade", toefl_total);
										toeflTmp.put(dep, toeflDepTmp);
									}
									toefl.put(insId, toeflTmp);
								}
							} catch (Exception e) {
								System.out.println("error - type[toefl] : "
										+ e.toString());
								System.out
										.println(json.get("toefl").toString());
							}

							// gre
							try {
								if (json.containsKey("gre") && json.getJSONObject("gre").get("total").toString().length() > 0) {
									float gre_total = Float.parseFloat(json
											.getJSONObject("gre").get(
													"total").toString());
									if (gre.containsKey(insId)) {
										greTmp = gre.get(insId);
										if (greTmp.containsKey(dep)) {
											greDepTmp = greTmp.get(dep);
											greDepTmp
													.put("amount", greDepTmp
															.get("amount") + 1f);
											greDepTmp.put("sumGrade",
													greDepTmp.get("sumGrade")
															+ gre_total);
										} else {
											greDepTmp = new HashMap<String, Float>();
											greDepTmp.put("amount", 1f);
											greDepTmp
													.put("sumGrade", gre_total);
										}
										greTmp.put(dep, greDepTmp);
									} else {
										greTmp = new HashMap<Integer, Map<String, Float>>();
										greDepTmp = new HashMap<String, Float>();
										greDepTmp.put("amount", 1f);
										greDepTmp.put("sumGrade", gre_total);
										greTmp.put(dep, greDepTmp);
									}
									gre.put(insId, greTmp);
								}
							} catch (Exception e) {
								System.out.println("error - type[gre] : "
										+ e.toString());
								System.out.println(json.get("gre").toString());
							}
						}
					} catch (Exception e) {

					}
				}
			}
		}
		
		System.out.println("end : statistic. . . ");
		
		// print
		System.out
				.println("----------------------------------------------------------");
		System.out.println("学校名\t\t\tdep\t个数\t总分\t平均数");
		List<String> res = new ArrayList<String>();
		for (int x : toefl.keySet()) {
			Map<Integer, Map<String, Float>> y = toefl.get(x);
			for (int xx : y.keySet()) {
				res.add(Util.getSchName(x, 1) + "\t" + xx + "\t"
						+ y.get(xx).get("amount") + "\t"
						+ y.get(xx).get("sumGrade") + "\t"
						+ y.get(xx).get("sumGrade")
						/ y.get(xx).get("amount"));
			}
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/三围offer成绩统计.txt");
		 
		System.out.println("---------------ruku-----------------");

		//String url = "localhost";
		String url = "123.57.250.189";
		int port = 27017;
		String dbName = "dulishuo";
		DB db = MongoUtil.getConnection(url,  dbName);

		DBCollection institute = db.getCollection("institute");
		DBCursor find = institute.find();
		List<String> fail = new ArrayList<String>();
		int flag = 1;
		float gpaScore;
		int toeflScore;
		int greScore;
		while (find.hasNext()) {
			DBObject obj = find.next();
			try {
				if (obj.containsField("application_difficulty")) {
					int insid = Util.id(obj.get("id").toString());
					JSONObject apply = JSONObject.fromObject(obj.get(
							"application_difficulty").toString());
					int facType = -1;
					for (Object key : apply.keySet()) {
						gpaScore = -1.0f;
						toeflScore = -1;
						greScore = -1;
						if(!pgmType.containsKey(key.toString()))
							continue;
						facType = Integer.parseInt(pgmType.get(key.toString()));
						//gpa
						if(gpa.containsKey(insid) && gpa.get(insid).containsKey(facType)){
							String hehe = String.valueOf(gpa.get(insid).get(facType).get("sumGrade") / gpa.get(insid).get(facType).get("amount"));
							if(hehe.length() > 4)
								gpaScore = Float.parseFloat(hehe.substring(0, hehe.indexOf(".")+3));
							else
								gpaScore = Float.parseFloat(hehe);
						}
						//toefl
						if(toefl.containsKey(insid) && toefl.get(insid).containsKey(facType)){
							toeflScore = (int)Math.rint(toefl.get(insid).get(facType).get("sumGrade") / toefl.get(insid).get(facType).get("amount"));
						}
						//gre
						if(gre.containsKey(insid) && gre.get(insid).containsKey(facType)){
							greScore = (int)Math.rint(gre.get(insid).get(facType).get("sumGrade") / gre.get(insid).get(facType).get("amount"));
						}
						JSONObject facTmpObj = apply
								.getJSONObject((String) key);
						facTmpObj.put("gpa", gpaScore);
						facTmpObj.put("toefl", toeflScore);
						facTmpObj.put("gre", greScore);
						apply.put(key.toString(), facTmpObj);
					}

					BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject().append(
							"application_difficulty", apply));
					if (institute.update(obj, newDocument, false, true).getN() >= 1)
						System.out.println("result :   success");
					else
						System.out.println("result :   fail");

				}
			} catch (Exception e) {
				fail.add("error	id	: " + obj.get("id").toString());
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}

		if (fail.size() > 0) {
			System.out.println("出错的记录...");
			System.out.println("----------------------------------------");
			for (String xx : fail)
				System.out.println(xx);
			System.out.println("----------------------------------------");
		}
	}

}
