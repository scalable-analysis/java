package com.st.dmp.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

public class HttpUtil {
	private static Logger log = Logger.getLogger(HttpUtil.class);
	private static String url = ConfManager.getStr("dmpDemo.get.url");
	private static String appid = ConfManager.getStr("dmpDemo.get.appid");
	private static String passwd = ConfManager.getStr("dmpDemo.get.passwd");
	private static String appkey = ConfManager.getStr("dmpDemo.get.appkey");
	/*
	 * 适用于请求参数为 appkey,size,startTime,endTime
	 * */
	public static String httpRequestV1(String method, String size, String startTime, String endTime){
		String response = null;

		/*System.out.println("urlhttp+"+url);
		System.out.println("appkeyhttp+"+appkey);
		System.out.println("methodhttp+"+method);
		System.out.println("size22+"+size);
		System.out.println("startTim22+"+startTime);
		System.out.println("endTime22+"+endTime);*/
		
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.addParameter("appid", appid);
		postMethod.addParameter("passwd", passwd);
		postMethod.addParameter("appkey", appkey);
		postMethod.addParameter("method", method);
		postMethod.addParameter("size", size);
		postMethod.addParameter("startTime", startTime);
		postMethod.addParameter("endTime", endTime);

		try {
			client.executeMethod(postMethod);
		} catch (HttpException e1) {
			// TODO Auto-generated catch block
			log.error(e1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			log.error(e1);
		}

		try {
			response = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		postMethod.releaseConnection();
		return response;
	}
	
	public static String httpRequestV2(String method, String itemid, String actionid, String page, String pageRecord,String startTime, String endTime){
		String response = null;

		/*System.out.println("urlhttp+"+url);
		System.out.println("appkeyhttp+"+appkey);
		System.out.println("methodhttp+"+method);
		
		System.out.println("startTim22+"+startTime);
		System.out.println("endTime22+"+endTime);*/
		
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.addParameter("appid", appid);
		postMethod.addParameter("passwd", passwd);
		postMethod.addParameter("appkey", appkey);
		postMethod.addParameter("method", method);
		postMethod.addParameter("actionid", actionid);
		postMethod.addParameter("itemid", itemid);
		postMethod.addParameter("page", page);
		postMethod.addParameter("pageRecord", pageRecord);
		postMethod.addParameter("startTime", startTime);
		postMethod.addParameter("endTime", endTime);

		try {
			client.executeMethod(postMethod);
		} catch (HttpException e1) {
			// TODO Auto-generated catch block
			log.error(e1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			log.error(e1);
		}

		try {
			response = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		postMethod.releaseConnection();
		return response;
	}
	
	/*
	 * 适用于请求参数为 method,itemid[itemtype], actionid,expkey,startTime,endTime
	 * */
	public static String httpRequestV3(String type, String method, String itemIdOrType,String actionid, String expkey, String startTime, String endTime){
		String response = null;

		/*System.out.println("urlhttp+"+url);
		System.out.println("appkeyhttp+"+appkey);
		System.out.println("methodhttp+"+method);
		System.out.println("itemIdOrType+"+itemIdOrType);
		System.out.println("startTim22+"+startTime);
		System.out.println("endTime22+"+endTime);*/
		
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.addParameter("appid", appid);
		postMethod.addParameter("passwd", passwd);
		postMethod.addParameter("appkey", appkey);
		postMethod.addParameter("method", method);
		if(type == "0"){
			postMethod.addParameter("itemid", itemIdOrType);
		}
		else if(type == "1" ){

			postMethod.addParameter("itemtype", itemIdOrType);
		}
		postMethod.addParameter("actionid", actionid);
		postMethod.addParameter("expkey", expkey);
		postMethod.addParameter("startTime", startTime);
		postMethod.addParameter("endTime", endTime);

		try {
			client.executeMethod(postMethod);
		} catch (HttpException e1) {
			// TODO Auto-generated catch block
			log.error(e1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			log.error(e1);
		}

		try {
			response = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		postMethod.releaseConnection();
		return response;
	}
	/**
	 * 处理PV、UV的HTTP请求
	 * @param type PV or UV
	 * @return
	 */
    public static String httpRequestForPvUv(String type, String itemID, String actionID, String startTime, String endTime, String flag)
    {   
    	String method = null;
    	String response = null;
    	HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.addParameter("appid", appid);
		postMethod.addParameter("passwd", passwd);
		postMethod.addParameter("appkey", appkey);
		postMethod.addParameter("startTime",startTime);
		postMethod.addParameter("endTime",endTime);
		postMethod.addParameter("flag",flag);
		//System.out.println(type + "-" + itemID + "-" + actionID + "-" + startTime + "-" + endTime + "-" + flag + "\n");
    	//http://211.151.59.249:8091/cdapi/req?appid=444&passwd=746749378&appkey=21&method=searchPv&itemid=12&actionid=11&startTime=1303708800&endTime=1423808800&flag=0
    	if(type == "PV")
    	{
    		method = "searchPv";
    		postMethod.addParameter("method","searchPv");
    		if(actionID.length()>0 && itemID.length()>0)
    		{
    			postMethod.addParameter("actionid",actionID);
    			postMethod.addParameter("itemid",itemID);
    		}
    		else if(actionID.length() == 0 && itemID.length() >0)
    		{
    			postMethod.addParameter("itemid",itemID);
    		}
    		else
    		{
    		}

    	}
    	else
    	{
    		method = "searchUv";
    		postMethod.addParameter("method","searchUv");
    		if(itemID.length()>0)
    		{
    			postMethod.addParameter("itemid",itemID);
    		}
    		else
    		{
    			
    		}
    	}
    	
    	
    	
    	
    	try {
			client.executeMethod(postMethod);
			response = postMethod.getResponseBodyAsString();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			log.error(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
    	postMethod.releaseConnection();
		return response;
    	
    }
  
    	
 }