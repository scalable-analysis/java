package com.st.dmp.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.st.dmp.util.CommUtil;
import com.st.dmp.util.WebUtil;

public class LoginAction extends ActionSupport{

	private static final long serialVersionUID = 2134469324240179229L;
	private String username;
	private String password;
	
	public String checkLogin(){
		boolean logok = WebUtil.checkLogin(username, password);
		try {
			//加载人名
			//CommUtil.loadPerson();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(logok){
			ActionContext.getContext().getSession().put("USER", username);
			System.out.println(ActionContext.getContext().getSession().get("USER"));
			return "success";
		}
		else{
			return "error";
		}
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
