package com.st.dmp.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.st.dmp.util.CommUtil;

@SuppressWarnings("serial")
public class OppoAction extends ActionSupport {

	private int institute_id;
	private String title;
	private String facultytype;
	private String type;
	private String applyFee;
	private String link;
	private String feeUrl;
	private String fee;
	private String introduce;
	private String benefit;
	private String attention;
	private String requirement;
	private String term;
	private String deadline;
	private int id;
	private String t_title;
	private String t_fee;
	private String t_introduce;
	private String t_benefit;
	private String t_attention;
	private String t_requirement;
	private String t_term;
	private String t_deadline;
	
	
	static String url = "123.57.250.189";
	//static String url = "localhost";
	static int port = 27017;
	
/*	static HttpServletResponse response = ServletActionContext.getResponse();
	static{
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
	}*/
	
	public void add(){
		
		JSONObject json = new JSONObject();
		String res ="fail";
		json.put("institute_id", institute_id);
		json.put("title", title);
		json.put("type", type);
		json.put("facultytype", facultytype);
		json.put("applyFee", applyFee);
		json.put("feeUrl", feeUrl);
		json.put("link", link);
		json.put("fee", fee);
		json.put("introduce", introduce);
		json.put("benefit", benefit);
		json.put("attention", attention);
		json.put("requirement", requirement);
		json.put("term", term);
		json.put("deadline", deadline);
		
		String dbName = "jianzhi";
		DB db = CommUtil.getConnection(url, port, dbName);
		DBCollection oppo = db.getCollection("opportunity");
		DBObject obj = (DBObject) JSON.parse(json.toString());
		try {
			oppo.insert(obj);
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
	
	public void tran(){
		String res = "fail";
		
		try{
			String dbName = "jianzhi";
			DB db = CommUtil.getConnection(url, port, dbName);
			DBCollection oppo = db.getCollection("opportunity");
			
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.append("$set", new BasicDBObject().append("zh_title", t_title).append("zh_requirement", t_requirement).append("zh_introduce", t_introduce).append("zh_benefit", t_benefit).append("zh_term", t_term).append("zh_deadline", t_deadline).append("zh_attention", t_attention).append("zh_fee", t_fee)
					.append("title", title).append("requirement", requirement).append("introduce", introduce).append("benefit", benefit).append("term", term).append("deadline", deadline).append("attention", attention).append("fee", fee)
					.append("is_translated",1));
			int amount = oppo.update(new BasicDBObject("id",id),newDocument,true,true).getN();
			if(amount > 0)
				res = "success";	
			
		}catch(Exception e){
			System.out.println("res:"+res);
		}
		
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
	
	public void get(){
		String dbName = "jianzhi";
		DB db = CommUtil.getConnection(url, port, dbName);
		DBCollection oppo = db.getCollection("opportunity");
		DBObject obj ;
		JSONObject json = new JSONObject();
		if(oppo.count(new BasicDBObject("is_translated", 0).append("from", "学校官网")) > 0){
			obj = oppo.findOne(new BasicDBObject("is_translated", 0));
			json.put("id", obj.get("id").toString());
			json.put("institute_id", obj.get("institute_id").toString());
			json.put("title", obj.get("title").toString());
			json.put("type", obj.get("type").toString());
			json.put("facultytype", obj.get("facultytype").toString());
			json.put("applyFee", obj.get("applyFee").toString());
			json.put("feeUrl", obj.get("feeUrl").toString());
			json.put("link", obj.get("link").toString());
			json.put("fee", obj.get("fee").toString());
			json.put("introduce", obj.get("introduce").toString());
			json.put("benefit", obj.get("benefit").toString());
			json.put("attention", obj.get("attention").toString());
			json.put("requirement", obj.get("requirement").toString());
			json.put("term", obj.get("term").toString());
			json.put("deadline", obj.get("deadline").toString());
			
			json.put("zh_title", obj.get("zh_title").toString());
			json.put("zh_fee", obj.get("zh_fee").toString());
			json.put("zh_introduce", obj.get("zh_introduce").toString());
			json.put("zh_benefit", obj.get("zh_benefit").toString());
			json.put("zh_attention", obj.get("zh_attention").toString());
			json.put("zh_requirement", obj.get("zh_requirement").toString());
			json.put("zh_term", obj.get("zh_term").toString());
			json.put("zh_deadline", obj.get("zh_deadline").toString());
			json.put("count", oppo.count(new BasicDBObject("is_translated", 0)));
		}
		else
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
	
	
	
	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFacultytype() {
		return facultytype;
	}
	public void setFacultytype(String facultytype) {
		this.facultytype = facultytype;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getApplyFee() {
		return applyFee;
	}
	public void setApplyFee(String applyFee) {
		this.applyFee = applyFee;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getFeeUrl() {
		return feeUrl;
	}
	public void setFeeUrl(String feeUrl) {
		this.feeUrl = feeUrl;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getBenefit() {
		return benefit;
	}
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	public String getAttention() {
		return attention;
	}
	public void setAttention(String attention) {
		this.attention = attention;
	}
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getT_fee() {
		return t_fee;
	}
	public void setT_fee(String t_fee) {
		this.t_fee = t_fee;
	}

	public String getT_introduce() {
		return t_introduce;
	}
	public void setT_introduce(String t_introduce) {
		this.t_introduce = t_introduce;
	}

	public String getT_benefit() {
		return t_benefit;
	}
	public void setT_benefit(String t_benefit) {
		this.t_benefit = t_benefit;
	}

	public String getT_attention() {
		return t_attention;
	}
	public void setT_attention(String t_attention) {
		this.t_attention = t_attention;
	}

	public String getT_requirement() {
		return t_requirement;
	}
	public void setT_requirement(String t_requirement) {
		this.t_requirement = t_requirement;
	}

	public String getT_term() {
		return t_term;
	}
	public void setT_term(String t_term) {
		this.t_term = t_term;
	}

	public String getT_deadline() {
		return t_deadline;
	}
	public void setT_deadline(String t_deadline) {
		this.t_deadline = t_deadline;
	}

	public String getT_title() {
		return t_title;
	}

	public void setT_title(String t_title) {
		this.t_title = t_title;
	}
	
	
	
}
