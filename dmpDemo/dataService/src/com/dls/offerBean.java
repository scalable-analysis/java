package com.dls;

import java.io.Serializable;




@SuppressWarnings("serial")
public class offerBean implements Serializable{
	private String id;
	private String title;
	private String school;
	private String school_id;
	private String degree;
	private String major;
	private String result;
	private String year;
	private String term;
	private String time;
	private String GRE;
	private String IELTS;
	private String TOEFL;
	private String currentschool;
	private String subject;
	private String GPA;
	private String program_id;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
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
	public String getGRE() {
		return GRE;
	}
	public void setGRE(String gRE) {
		GRE = gRE;
	}
	public String getIELTS() {
		return IELTS;
	}
	public void setIELTS(String iELTS) {
		IELTS = iELTS;
	}
	public String getTOEFL() {
		return TOEFL;
	}
	public void setTOEFL(String tOEFL) {
		TOEFL = tOEFL;
	}
	public String getCurrentschool() {
		return currentschool;
	}
	public void setCurrentschool(String currentschool) {
		this.currentschool = currentschool;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getGPA() {
		return GPA;
	}
	public void setGPA(String gPA) {
		GPA = gPA;
	}
	
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getProgram_id() {
		return program_id;
	}
	public void setProgram_id(String program_id) {
		this.program_id = program_id;
	}
	

}
