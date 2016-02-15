package com.st.dmp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.st.dmp.util.CommUtil;

public class TranAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -794327192160201775L;
	private String tran_cnt;
	private int tran_type;
	
	
	static String url = "123.57.250.189";
	//static String url = "localhost";
	static int port = 27017;
	
	/*
	 * desc: 往库里增加人工完美翻译，分类型 1-program 2-college 3-school 4-department 5-faculty
	 * author: xiaohe
	 * date: 2015-12-11
	 */
	public void add(){
		System.out.println(tran_cnt);
		
		String[] cnt= tran_cnt.split("\n");
		System.out.println(cnt.length);
		
		String dbName = "jianzhi";
		DB db = CommUtil.getConnection(url, port, dbName);
		DBCollection offer = db.getCollection("translate");
		String eng;
		String chi;
		DBObject obj;
		JSONObject json = new JSONObject();
		int count = 0;
		for(int i = 0 ; i < cnt.length ; i += 2){
			eng = cnt[i].trim().toLowerCase();
			chi = cnt[i+1].trim().toLowerCase();
			json.put("type", tran_type);
			json.put("name",eng);
			json.put("name_chinese", chi);
			obj = (DBObject) JSON.parse(json.toString());
			try {
				offer.insert(obj);
				count++;
			} catch (Exception e) {}
		}
		
		//返回
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print("一共有 " + cnt.length/2 +" 个，成功插入 "+count+" 个！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getTran_cnt() {
		return tran_cnt;
	}
	public void setTran_cnt(String tran_cnt) {
		this.tran_cnt = tran_cnt;
	}
	public int getTran_type() {
		return tran_type;
	}
	public void setTran_type(int tran_type) {
		this.tran_type = tran_type;
	}
	
	
}
