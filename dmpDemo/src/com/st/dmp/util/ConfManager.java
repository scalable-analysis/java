package com.st.dmp.util;

import java.io.InputStream;
import java.util.Properties;


public class ConfManager {
	
	private static String confFile = "config.properties";
	private static Properties prop = null;
	private ConfManager(){}

	public static void init() throws Exception{
		System.out.println("init conf file of : "+confFile);
		InputStream is = ConfManager.class.getClassLoader().getResourceAsStream(confFile);

		prop = new Properties();
		prop.load(is);
	}
	
	public static String getStr(String key){
		return (String) prop.get(key);
	}
	
	public static int getInt(String key) {
		Object value = prop.get(key);
		return Integer.parseInt(value.toString());
	}
	
	public static boolean getBoolean(String key){
		String value = (String) prop.get(key);
		return Boolean.parseBoolean(value);
	}
	
}
