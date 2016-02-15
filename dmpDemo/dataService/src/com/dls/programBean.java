package com.dls;

import java.io.Serializable;

public class programBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -914690905573089208L;
	private String id;
	private String institute;
	private String subject;
	private String institute_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getInstitute_id() {
		return institute_id;
	}
	public void setInstitute_id(String institute_id) {
		this.institute_id = institute_id;
	}
	
	
}
