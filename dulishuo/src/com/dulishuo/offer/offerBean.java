package com.dulishuo.offer;

import java.io.Serializable;

import net.sf.json.JSONObject;




@SuppressWarnings("serial")
public class offerBean implements Serializable{
	private String id;
	private int institute_id;
	private int degree;
	private int result;
	private String year;
	private String term;
	private String time;
	private JSONObject gre;
	private JSONObject ielts;
	private JSONObject toefl;
	private String currentschool;
	private String exsubject;
	private Double gpa;
	private int program_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public int getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(int institute_id) {
		this.institute_id = institute_id;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public JSONObject getGre() {
		return gre;
	}
	public void setGre(JSONObject gre) {
		this.gre = gre;
	}
	public JSONObject getIelts() {
		return ielts;
	}
	public void setIelts(JSONObject ielts) {
		this.ielts = ielts;
	}
	public JSONObject getToefl() {
		return toefl;
	}
	public void setToefl(JSONObject toefl) {
		this.toefl = toefl;
	}
	public String getCurrentschool() {
		return currentschool;
	}
	public void setCurrentschool(String currentschool) {
		this.currentschool = currentschool;
	}
	public String getExsubject() {
		return exsubject;
	}
	public void setExsubject(String exsubject) {
		this.exsubject = exsubject;
	}
	public Double getGpa() {
		return gpa;
	}
	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}
	public int getProgram_id() {
		return program_id;
	}
	public void setProgram_id(int program_id) {
		this.program_id = program_id;
	}
	
	
	
}
