package com.st.dmp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.st.dmp.util.CommUtil;

public class OfferAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1035195061595408533L;

	private String marker;
	private String applicant;
	private String school;
	private String year;
	private String term;
	private String major;
	private String dep;
	private String depOther;
	private String degree;
	private String gpa;
	private String ti;
	private String ti_total;
	private String ti_r;
	private String ti_w;
	private String ti_l;
	private String ti_s;
	private String gg;
	private String gg_total;
	private String gg_aw;
	private String gg_q;
	private String gg_v;
	private String result_count;
	private String sch_result;

	private String drea1;
	private String brea1;
	private String crea1;
	private String drea2;
	private String brea2;
	private String crea2;
	private String drea3;
	private String brea3;
	private String crea3;

	private String dcmp1;
	private String bcmp1;
	private String ccmp1;
	private String dcmp2;
	private String bcmp2;
	private String ccmp2;
	private String dcmp3;
	private String bcmp3;
	private String ccmp3;

	private String dwork1;
	private String bwork1;
	private String cwork1;
	private String dwork2;
	private String bwork2;
	private String cwork2;
	private String dwork3;
	private String bwork3;
	private String cwork3;

	private String dins1;
	private String bins1;
	private String cins1;
	private String dins2;
	private String bins2;
	private String cins2;
	private String dins3;
	private String bins3;
	private String cins3;
	private String marker_s;
	private int id;
	private String ps;
	private String rl1;
	private String rl2;
	private String rl3;
	private String analysis;
	private int flag;
	private String background;
	
	//标签
	private String lchec;
	private String ichec;
	private String ochec;
	

	
	private int cv;
	

	static String url = "123.57.250.189";
	//static String url = "localhost";
	static int port = 27017;
	
	//随机提取一个offer
	public void tagGet(){
		String dbName = "jianzhi";
		DB db = CommUtil.getConnection(url, port, dbName);
		DBCollection offer = db.getCollection("jianzhioffer");
		DBObject obj ;
		JSONObject json = new JSONObject();
		if(offer.count(new BasicDBObject("tag_used" , 0)) > 0){
			obj = offer.findOne(new BasicDBObject("tag_used" , 0));
			json.put("status", 1);
			json.put("applicant", obj.get("applicant").toString());
			json.put("id", obj.get("id").toString());	
			json.put("flag", obj.get("flag").toString());	
			json.put("cv", obj.get("cv").toString());	
			json.put("currentschool", obj.get("currentschool").toString());
			json.put("gpa", obj.get("gpa").toString());
			json.put("major", obj.get("major").toString());
			json.put("rl1", obj.get("rl1").toString());
			json.put("rl2", obj.get("rl2").toString());
			json.put("rl3", obj.get("rl3").toString());
			if(obj.get("toefl").toString().length() < 10)
				json.put("toefl", JSONObject.fromObject(obj.get("ielts").toString()).get("total"));
			else
				json.put("toefl", JSONObject.fromObject(obj.get("toefl").toString()).get("total"));
			
			if(obj.get("gre").toString().length() < 10)
				json.put("gre", JSONObject.fromObject(obj.get("gmat").toString()).get("total"));
			else
				json.put("gre", JSONObject.fromObject(obj.get("gre").toString()).get("total"));
		}else
			json.put("status", -1);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//给offer 打标签
	public void tag(){
		String res = "fail";
		String dbName = "jianzhi";
		DB db = CommUtil.getConnection(url, port, dbName);
		DBCollection offer = db.getCollection("jianzhioffer");
		
		BasicDBObject newDocument = new BasicDBObject();
		JSONObject tag = new JSONObject();
		if(lchec.indexOf(",") != -1){
			String[] lchecArr = lchec.substring(0, lchec.length()-1).split(",");
			tag.put("language", lchecArr);
		}else{
			String[] lchecArr = new String[0];
			tag.put("language", lchecArr);
		}
			
		if(ichec.indexOf(",") != -1){
			String[] ichecArr = ichec.substring(0, ichec.length()-1).split(",");
			tag.put("inner", ichecArr);
		}else{
			String[] ichecArr = new String[0];
			tag.put("inner", ichecArr);
		}
			
		if(ochec.indexOf(",") != -1){
			String[] ochecArr = ochec.substring(0, ochec.length()-1).split(",");
			tag.put("outter", ochecArr);
		}else{
			String[] ochecArr = new String[0];
			tag.put("outter", ochecArr);
		}
		
		
	
		
		
		newDocument.append("$set", new BasicDBObject().append("tag", tag).append("tag_used",1));
		int amount = offer.update(new BasicDBObject("cv",cv),newDocument,true,true).getN();
		if(amount > 0)
			res = "success";
				
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 修改offer from水哥
	public void modify(){
		String dbName = "jianzhi";
		DB db = CommUtil.getConnection(url, port, dbName);
		DBCollection offer = db.getCollection("jianzhioffer");
		JSONObject json = new JSONObject();
		json.put("status","fail");
		System.out.println("flag:"+flag);
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append("is_used", 1).append("ps",ps).append("rl1", rl1).append("rl2", rl2).append("rl3", rl3).append("cv",id).append("analysis", analysis));
		int amount = offer.update(new BasicDBObject("flag", flag),newDocument,true,true).getN();
		if(amount > 0){
			json.put("status", "success");
			System.out.println("一共 "+ amount + " 个记录被更新！");
		}
			
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 查询offer from水哥
	public void get() {		
		String dbName = "jianzhi";
		DB db = CommUtil.getConnection(url, port, dbName);
		DBCollection offer = db.getCollection("jianzhioffer");
		DBObject obj ;
		JSONObject json = new JSONObject();
		if(offer.count(new BasicDBObject("marker",marker_s).append("is_used", 0)) > 0){
			obj = offer.findOne(new BasicDBObject("marker",marker_s).append("is_used", 0));
			json.put("status", 1);
			json.put("applicant", obj.get("applicant").toString());
			json.put("id", obj.get("id").toString());
			json.put("marker", obj.get("marker").toString());
			json.put("currentschool", obj.get("currentschool").toString());
			json.put("gpa", obj.get("gpa").toString());
			json.put("major", obj.get("major").toString());
			json.put("flag", obj.get("flag"));
			if(obj.get("toefl").toString().length() < 10)
				json.put("toefl", JSONObject.fromObject(obj.get("ielts").toString()).get("total"));
			else
				json.put("toefl", JSONObject.fromObject(obj.get("toefl").toString()).get("total"));
			
			if(obj.get("gre").toString().length() < 10)
				json.put("gre", JSONObject.fromObject(obj.get("gmat").toString()).get("total"));
			else
				json.put("gre", JSONObject.fromObject(obj.get("oldGre").toString()).get("total"));
			json.put("research", obj.get("research").toString());
		}
		else
			json.put("status", -1);
		
		System.out.println("marker_s : " + marker_s);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	// 录入offer from水哥
	public void add() {

		// 输出
		/*
		 * System.out.println("applicant : " + applicant);
		 * System.out.println("year : " + year); System.out.println("term : " +
		 * term); System.out.println("degree : " + degree);
		 * System.out.println("major : " + major);
		 * System.out.println("currentschool : " + school);
		 * System.out.println("dep : " + dep);
		 */

		JSONObject json = new JSONObject();
		json.put("applicant", applicant);
		json.put("year", year);
		json.put("term", term);
		json.put("degree", degree);
		/*
		 * if(Integer.parseInt(dep) == -1) json.put("degree", depOther);
		 */
		json.put("oldMajor", depOther);
		
		json.put("major", major);
		json.put("currentschool", school);
		json.put("from", "luntan");
		String[] dept = new String[1];

		if (dep.indexOf("-") != -1)
			dept[0] = "-1";
		else
			dept[0] = dep.trim();
		json.put("department_type", dept);
		json.put("background", background);

		// 随机取个别名
		// int no = (int)(Math.random()*1023);
		// json.put("alias", CommUtil.person.get(no));

		json.put("marker", marker);
		// System.out.println("执行到三围前"+user);

		// ielts && toefl
		json.put("gpa", gpa);
		JSONObject toefl = new JSONObject();
		toefl.put("total", ti_total);
		toefl.put("listening", ti_l);
		toefl.put("speaking", ti_s);
		toefl.put("reading", ti_r);
		toefl.put("writing", ti_w);
		toefl.put("other", "");
		// System.out.println(toefl.toString());
		if (ti.equals("toefl"))
			json.put("toefl", toefl);
		else
			json.put("ielts", toefl);

		// gre && gmat

		if (gg.equals("gre")) {
			JSONObject gre = new JSONObject();
			gre.put("aw", gg_aw);
			gre.put("total", gg_total);
			gre.put("q", gg_q);
			gre.put("v", gg_v);
			gre.put("other", "");
			json.put("gre", gre);
			// System.out.println(gre.toString());
		} else {
			JSONObject gmat = new JSONObject();
			gmat.put("aw", gg_aw);
			gmat.put("total", gg_total);
			gmat.put("other", "");
			json.put("gmat", gmat);
		}

		// System.out.println("执行到及格赛前");

		// 竞赛
		JSONObject cjson1 = new JSONObject();
		JSONObject cjson2 = new JSONObject();
		JSONObject cjson3 = new JSONObject();
		cjson1.put("title", bcmp1);
		cjson1.put("date", dcmp1);
		cjson1.put("content", ccmp1);
		cjson2.put("title", bcmp2);
		cjson2.put("date", dcmp2);
		cjson2.put("content", ccmp2);
		cjson3.put("title", bcmp3);
		cjson3.put("date", dcmp3);
		cjson3.put("content", ccmp3);
		JSONArray cja = new JSONArray();
		if (bcmp1.length() < 2) {
			json.put("competition", cja);
		} else if (bcmp2.length() < 2) {
			cja.add(cjson1);
			json.put("competition", cja);
		} else if (bcmp3.length() < 2) {
			cja.add(cjson1);
			cja.add(cjson2);
			json.put("competition", cja);
		} else {
			cja.add(cjson1);
			cja.add(cjson2);
			cja.add(cjson3);
			json.put("competition", cja);
		}
		System.out.println("competition:" + cja.toString());
		// 科研
		JSONObject rjson1 = new JSONObject();
		JSONObject rjson2 = new JSONObject();
		JSONObject rjson3 = new JSONObject();
		rjson1.put("title", brea1);
		rjson1.put("date", drea1);
		rjson1.put("content", crea1);
		rjson2.put("title", brea2);
		rjson2.put("date", drea2);
		rjson2.put("content", crea2);
		rjson3.put("title", brea3);
		rjson3.put("date", drea3);
		rjson3.put("content", crea3);
		JSONArray rja = new JSONArray();
		if (brea1.length() < 2) {
			json.put("competition", rja);
		} else if (brea2.length() < 2) {
			rja.add(rjson1);
			json.put("research", rja);
		} else if (brea3.length() < 2) {
			rja.add(rjson1);
			rja.add(rjson2);
			json.put("research", rja);
		} else {
			rja.add(rjson1);
			rja.add(rjson2);
			rja.add(rjson3);
			json.put("research", rja);
		}

		// 工作
		JSONObject wjson1 = new JSONObject();
		JSONObject wjson2 = new JSONObject();
		JSONObject wjson3 = new JSONObject();
		wjson1.put("title", bwork1);
		wjson1.put("date", dwork1);
		wjson1.put("content", cwork1);
		wjson2.put("title", bwork2);
		wjson2.put("date", dwork2);
		wjson2.put("content", cwork2);
		wjson3.put("title", bwork3);
		wjson3.put("date", dwork3);
		wjson3.put("content", cwork3);
		JSONArray wja = new JSONArray();
		if (bwork1.length() < 2) {
			json.put("work", wja);
		} else if (bwork2.length() < 2) {
			wja.add(wjson1);
			json.put("work", wja);
		} else if (bwork3.length() < 2) {
			wja.add(wjson1);
			wja.add(wjson2);
			json.put("work", wja);
		} else {
			wja.add(wjson1);
			wja.add(wjson2);
			wja.add(wjson3);
			json.put("work", wja);
		}

		// 实习
		JSONObject ijson1 = new JSONObject();
		JSONObject ijson2 = new JSONObject();
		JSONObject ijson3 = new JSONObject();
		ijson1.put("title", bins1);
		ijson1.put("date", dins1);
		ijson1.put("content", cins1);
		ijson2.put("title", bins2);
		ijson2.put("date", dins2);
		ijson2.put("content", cins2);
		ijson3.put("title", bins3);
		ijson3.put("date", dins3);
		ijson3.put("content", cins3);
		JSONArray ija = new JSONArray();
		if (bins1.length() < 2) {
			json.put("internship", ija);
		} else if (bins2.length() < 2) {
			ija.add(ijson1);
			json.put("internship", ija);
		} else if (bins3.length() < 2) {
			ija.add(ijson1);
			ija.add(ijson2);
			json.put("internship", ija);
		} else {
			ija.add(ijson1);
			ija.add(ijson2);
			ija.add(ijson3);
			json.put("internship", ija);
		}
		String[] schhhh = sch_result.split("&&&&");
		String tmpSch = "";
		int result = 0;
		JSONObject insJson = new JSONObject();
		for (int i = 0; i < Integer.parseInt(result_count); i++) {
			// System.out.println("schhhh[i] : " + schhhh[i]);
			tmpSch = schhhh[i].split("---")[0].toString();
			result = Integer.parseInt(schhhh[i].split("---")[1].toString());
			
			// System.out.println(json.toString());
			insJson.put(tmpSch, result);
		}
		json.put("oldInstitute", insJson);
		insertMongo(json.toString());
	}
	// 录入offer from水哥
	public void addAll() {

			// 输出
			/*
			 * System.out.println("applicant : " + applicant);
			 * System.out.println("year : " + year); System.out.println("term : " +
			 * term); System.out.println("degree : " + degree);
			 * System.out.println("major : " + major);
			 * System.out.println("currentschool : " + school);
			 * System.out.println("dep : " + dep);
			 */

			JSONObject json = new JSONObject();
			json.put("applicant", applicant);
			json.put("year", year);
			json.put("term", term);
			json.put("degree", degree);
			json.put("ps", ps);
			json.put("cv", applicant);
			json.put("analysis", analysis);
			json.put("rl1", rl1);
			json.put("rl2", rl2);
			json.put("rl3", rl3);
			/*
			 * if(Integer.parseInt(dep) == -1) json.put("degree", depOther);
			 */
			json.put("oldMajor", depOther);
			
			json.put("major", major);
			json.put("currentschool", school);
			json.put("from", "luntan");
			String[] dept = new String[1];

			if (dep.indexOf("-") != -1)
				dept[0] = "-1";
			else
				dept[0] = dep.trim();
			json.put("department_type", dept);
			json.put("background", background);

			// 随机取个别名
			// int no = (int)(Math.random()*1023);
			// json.put("alias", CommUtil.person.get(no));

			json.put("marker", marker);
			// System.out.println("执行到三围前"+user);

			// ielts && toefl
			json.put("gpa", gpa);
			JSONObject toefl = new JSONObject();
			toefl.put("total", ti_total);
			toefl.put("listening", ti_l);
			toefl.put("speaking", ti_s);
			toefl.put("reading", ti_r);
			toefl.put("writing", ti_w);
			toefl.put("other", "");
			// System.out.println(toefl.toString());
			if (ti.equals("toefl"))
				json.put("toefl", toefl);
			else
				json.put("ielts", toefl);

			// gre && gmat

			if (gg.equals("gre")) {
				JSONObject gre = new JSONObject();
				gre.put("aw", gg_aw);
				gre.put("total", gg_total);
				gre.put("q", gg_q);
				gre.put("v", gg_v);
				gre.put("other", "");
				json.put("gre", gre);
				// System.out.println(gre.toString());
			} else {
				JSONObject gmat = new JSONObject();
				gmat.put("aw", gg_aw);
				gmat.put("total", gg_total);
				gmat.put("other", "");
				json.put("gmat", gmat);
			}

			// System.out.println("执行到及格赛前");

			// 竞赛
			JSONObject cjson1 = new JSONObject();
			JSONObject cjson2 = new JSONObject();
			JSONObject cjson3 = new JSONObject();
			cjson1.put("title", bcmp1);
			cjson1.put("date", dcmp1);
			cjson1.put("content", ccmp1);
			cjson2.put("title", bcmp2);
			cjson2.put("date", dcmp2);
			cjson2.put("content", ccmp2);
			cjson3.put("title", bcmp3);
			cjson3.put("date", dcmp3);
			cjson3.put("content", ccmp3);
			JSONArray cja = new JSONArray();
			if (bcmp1.length() < 2) {
				json.put("competition", cja);
			} else if (bcmp2.length() < 2) {
				cja.add(cjson1);
				json.put("competition", cja);
			} else if (bcmp3.length() < 2) {
				cja.add(cjson1);
				cja.add(cjson2);
				json.put("competition", cja);
			} else {
				cja.add(cjson1);
				cja.add(cjson2);
				cja.add(cjson3);
				json.put("competition", cja);
			}
			System.out.println("competition:" + cja.toString());
			// 科研
			JSONObject rjson1 = new JSONObject();
			JSONObject rjson2 = new JSONObject();
			JSONObject rjson3 = new JSONObject();
			rjson1.put("title", brea1);
			rjson1.put("date", drea1);
			rjson1.put("content", crea1);
			rjson2.put("title", brea2);
			rjson2.put("date", drea2);
			rjson2.put("content", crea2);
			rjson3.put("title", brea3);
			rjson3.put("date", drea3);
			rjson3.put("content", crea3);
			JSONArray rja = new JSONArray();
			if (brea1.length() < 2) {
				json.put("competition", rja);
			} else if (brea2.length() < 2) {
				rja.add(rjson1);
				json.put("research", rja);
			} else if (brea3.length() < 2) {
				rja.add(rjson1);
				rja.add(rjson2);
				json.put("research", rja);
			} else {
				rja.add(rjson1);
				rja.add(rjson2);
				rja.add(rjson3);
				json.put("research", rja);
			}

			// 工作
			JSONObject wjson1 = new JSONObject();
			JSONObject wjson2 = new JSONObject();
			JSONObject wjson3 = new JSONObject();
			wjson1.put("title", bwork1);
			wjson1.put("date", dwork1);
			wjson1.put("content", cwork1);
			wjson2.put("title", bwork2);
			wjson2.put("date", dwork2);
			wjson2.put("content", cwork2);
			wjson3.put("title", bwork3);
			wjson3.put("date", dwork3);
			wjson3.put("content", cwork3);
			JSONArray wja = new JSONArray();
			if (bwork1.length() < 2) {
				json.put("work", wja);
			} else if (bwork2.length() < 2) {
				wja.add(wjson1);
				json.put("work", wja);
			} else if (bwork3.length() < 2) {
				wja.add(wjson1);
				wja.add(wjson2);
				json.put("work", wja);
			} else {
				wja.add(wjson1);
				wja.add(wjson2);
				wja.add(wjson3);
				json.put("work", wja);
			}

			// 实习
			JSONObject ijson1 = new JSONObject();
			JSONObject ijson2 = new JSONObject();
			JSONObject ijson3 = new JSONObject();
			ijson1.put("title", bins1);
			ijson1.put("date", dins1);
			ijson1.put("content", cins1);
			ijson2.put("title", bins2);
			ijson2.put("date", dins2);
			ijson2.put("content", cins2);
			ijson3.put("title", bins3);
			ijson3.put("date", dins3);
			ijson3.put("content", cins3);
			JSONArray ija = new JSONArray();
			if (bins1.length() < 2) {
				json.put("internship", ija);
			} else if (bins2.length() < 2) {
				ija.add(ijson1);
				json.put("internship", ija);
			} else if (bins3.length() < 2) {
				ija.add(ijson1);
				ija.add(ijson2);
				json.put("internship", ija);
			} else {
				ija.add(ijson1);
				ija.add(ijson2);
				ija.add(ijson3);
				json.put("internship", ija);
			}
			String[] schhhh = sch_result.split("&&&&");
			String tmpSch = "";
			int result = 0;
			JSONObject insJson = new JSONObject();
			for (int i = 0; i < Integer.parseInt(result_count); i++) {
				// System.out.println("schhhh[i] : " + schhhh[i]);
				tmpSch = schhhh[i].split("---")[0].toString();
				result = Integer.parseInt(schhhh[i].split("---")[1].toString());
				
				// System.out.println(json.toString());
				insJson.put(tmpSch, result);
			}
			json.put("oldInstitute", insJson);
			
			
			//insert to Mongo
			String dbName = "jianzhi";
			DB db = CommUtil.getConnection(url, port, dbName);

			DBCollection offer = db.getCollection("shuige1216");
			DBObject dbObject = (DBObject) JSON.parse(json.toString());
			String res = "fail";
			try {
				offer.insert(dbObject);
				res = "success";
			} catch (Exception e) {}
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer;
			try {
				writer = response.getWriter();
				writer.print(res);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	private void insertMongo(String str) {
		// TODO Auto-generated method stub
		//String url = "123.57.250.189";
		
		String dbName = "jianzhi";
		DB db = CommUtil.getConnection(url, port, dbName);

		DBCollection offer = db.getCollection("offerLuntan");
		DBObject dbObject = (DBObject) JSON.parse(str);
		try {
			offer.insert(dbObject);
			System.out.println("Insert success . . . ");
		} catch (Exception e) {
			System.out.println("Insert fail . . . ");
		}

	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getGpa() {
		return gpa;
	}

	public void setGpa(String gpa) {
		this.gpa = gpa;
	}

	public String getTi() {
		return ti;
	}

	public void setTi(String ti) {
		this.ti = ti;
	}

	public String getTi_total() {
		return ti_total;
	}

	public void setTi_total(String ti_total) {
		this.ti_total = ti_total;
	}

	public String getTi_r() {
		return ti_r;
	}

	public void setTi_r(String ti_r) {
		this.ti_r = ti_r;
	}

	public String getTi_w() {
		return ti_w;
	}

	public void setTi_w(String ti_w) {
		this.ti_w = ti_w;
	}

	public String getTi_l() {
		return ti_l;
	}

	public void setTi_l(String ti_l) {
		this.ti_l = ti_l;
	}

	public String getTi_s() {
		return ti_s;
	}

	public void setTi_s(String ti_s) {
		this.ti_s = ti_s;
	}

	public String getGg() {
		return gg;
	}

	public void setGg(String gg) {
		this.gg = gg;
	}

	public String getGg_total() {
		return gg_total;
	}

	public void setGg_total(String gg_total) {
		this.gg_total = gg_total;
	}

	public String getGg_aw() {
		return gg_aw;
	}

	public void setGg_aw(String gg_aw) {
		this.gg_aw = gg_aw;
	}

	public String getGg_q() {
		return gg_q;
	}

	public void setGg_q(String gg_q) {
		this.gg_q = gg_q;
	}

	public String getGg_v() {
		return gg_v;
	}

	public void setGg_v(String gg_v) {
		this.gg_v = gg_v;
	}

	public String getResult_count() {
		return result_count;
	}

	public void setResult_count(String result_count) {
		this.result_count = result_count;
	}

	public String getSch_result() {
		return sch_result;
	}

	public void setSch_result(String sch_result) {
		this.sch_result = sch_result;
	}

	public String getDrea1() {
		return drea1;
	}

	public void setDrea1(String drea1) {
		this.drea1 = drea1;
	}

	public String getBrea1() {
		return brea1;
	}

	public void setBrea1(String brea1) {
		this.brea1 = brea1;
	}

	public String getCrea1() {
		return crea1;
	}

	public void setCrea1(String crea1) {
		this.crea1 = crea1;
	}

	public String getDrea2() {
		return drea2;
	}

	public void setDrea2(String drea2) {
		this.drea2 = drea2;
	}

	public String getBrea2() {
		return brea2;
	}

	public void setBrea2(String brea2) {
		this.brea2 = brea2;
	}

	public String getCrea2() {
		return crea2;
	}

	public void setCrea2(String crea2) {
		this.crea2 = crea2;
	}

	public String getDrea3() {
		return drea3;
	}

	public void setDrea3(String drea3) {
		this.drea3 = drea3;
	}

	public String getBrea3() {
		return brea3;
	}

	public void setBrea3(String brea3) {
		this.brea3 = brea3;
	}

	public String getCrea3() {
		return crea3;
	}

	public void setCrea3(String crea3) {
		this.crea3 = crea3;
	}

	public String getDcmp1() {
		return dcmp1;
	}

	public void setDcmp1(String dcmp1) {
		this.dcmp1 = dcmp1;
	}

	public String getBcmp1() {
		return bcmp1;
	}

	public void setBcmp1(String bcmp1) {
		this.bcmp1 = bcmp1;
	}

	public String getCcmp1() {
		return ccmp1;
	}

	public void setCcmp1(String ccmp1) {
		this.ccmp1 = ccmp1;
	}

	public String getDcmp2() {
		return dcmp2;
	}

	public void setDcmp2(String dcmp2) {
		this.dcmp2 = dcmp2;
	}

	public String getBcmp2() {
		return bcmp2;
	}

	public void setBcmp2(String bcmp2) {
		this.bcmp2 = bcmp2;
	}

	public String getCcmp2() {
		return ccmp2;
	}

	public void setCcmp2(String ccmp2) {
		this.ccmp2 = ccmp2;
	}

	public String getDcmp3() {
		return dcmp3;
	}

	public void setDcmp3(String dcmp3) {
		this.dcmp3 = dcmp3;
	}

	public String getBcmp3() {
		return bcmp3;
	}

	public void setBcmp3(String bcmp3) {
		this.bcmp3 = bcmp3;
	}

	public String getCcmp3() {
		return ccmp3;
	}

	public void setCcmp3(String ccmp3) {
		this.ccmp3 = ccmp3;
	}

	public String getDwork1() {
		return dwork1;
	}

	public void setDwork1(String dwork1) {
		this.dwork1 = dwork1;
	}

	public String getBwork1() {
		return bwork1;
	}

	public void setBwork1(String bwork1) {
		this.bwork1 = bwork1;
	}

	public String getCwork1() {
		return cwork1;
	}

	public void setCwork1(String cwork1) {
		this.cwork1 = cwork1;
	}

	public String getDwork2() {
		return dwork2;
	}

	public void setDwork2(String dwork2) {
		this.dwork2 = dwork2;
	}

	public String getBwork2() {
		return bwork2;
	}

	public void setBwork2(String bwork2) {
		this.bwork2 = bwork2;
	}

	public String getCwork2() {
		return cwork2;
	}

	public void setCwork2(String cwork2) {
		this.cwork2 = cwork2;
	}

	public String getDwork3() {
		return dwork3;
	}

	public void setDwork3(String dwork3) {
		this.dwork3 = dwork3;
	}

	public String getBwork3() {
		return bwork3;
	}

	public void setBwork3(String bwork3) {
		this.bwork3 = bwork3;
	}

	public String getCwork3() {
		return cwork3;
	}

	public void setCwork3(String cwork3) {
		this.cwork3 = cwork3;
	}

	public String getDins1() {
		return dins1;
	}

	public void setDins1(String dins1) {
		this.dins1 = dins1;
	}

	public String getBins1() {
		return bins1;
	}

	public void setBins1(String bins1) {
		this.bins1 = bins1;
	}

	public String getCins1() {
		return cins1;
	}

	public void setCins1(String cins1) {
		this.cins1 = cins1;
	}

	public String getDins2() {
		return dins2;
	}

	public void setDins2(String dins2) {
		this.dins2 = dins2;
	}

	public String getBins2() {
		return bins2;
	}

	public void setBins2(String bins2) {
		this.bins2 = bins2;
	}

	public String getCins2() {
		return cins2;
	}

	public void setCins2(String cins2) {
		this.cins2 = cins2;
	}

	public String getDins3() {
		return dins3;
	}

	public void setDins3(String dins3) {
		this.dins3 = dins3;
	}

	public String getBins3() {
		return bins3;
	}

	public void setBins3(String bins3) {
		this.bins3 = bins3;
	}

	public String getCins3() {
		return cins3;
	}

	public void setCins3(String cins3) {
		this.cins3 = cins3;
	}

	public String getDepOther() {
		return depOther;
	}

	public void setDepOther(String depOther) {
		this.depOther = depOther;
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}
	public String getMarker_s() {
		return marker_s;
	}

	public void setMarker_s(String marker_s) {
		this.marker_s = marker_s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getRl1() {
		return rl1;
	}

	public void setRl1(String rl1) {
		this.rl1 = rl1;
	}

	public String getRl2() {
		return rl2;
	}

	public void setRl2(String rl2) {
		this.rl2 = rl2;
	}

	public String getRl3() {
		return rl3;
	}

	public void setRl3(String rl3) {
		this.rl3 = rl3;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getLchec() {
		return lchec;
	}
	public void setLchec(String lchec) {
		this.lchec = lchec;
	}
	public String getIchec() {
		return ichec;
	}
	public void setIchec(String ichec) {
		this.ichec = ichec;
	}
	public String getOchec() {
		return ochec;
	}
	public void setOchec(String ochec) {
		this.ochec = ochec;
	}
	public int getCv() {
		return cv;
	}
	public void setCv(int cv) {
		this.cv = cv;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	
	
}
