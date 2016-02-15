package com.st.dmp.util;

import java.util.HashMap;
import java.util.Map;

public class WebUtil {
	public static boolean checkLogin(String username, String password) {
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("admin", "admin");
		userMap.put("st", "123");
		userMap.put("test", "123");
		userMap.put("zhaoyajing", "123");
		userMap.put("xuyue", "123");
		userMap.put("xurun", "123");
		userMap.put("mochengkang", "123");
		userMap.put("fanlei", "123");
		userMap.put("jianzhi11", "123");
		userMap.put("jianzhi22", "123");
		userMap.put("jianzhi33", "123");
		userMap.put("jianzhi44", "123");
		userMap.put("jianzhi55", "123");
		userMap.put("jianzhi66", "123");
		userMap.put("jianzhi77", "123");
		userMap.put("jianzhi88", "123");
		userMap.put("jianzhi99", "123");
		userMap.put("jianzhi110", "123");
		userMap.put("jianzhi111", "123");
		userMap.put("jianzhi112", "123");
		userMap.put("jianzhi113", "123");
		userMap.put("jianzhi114", "123");
		userMap.put("jianzhi115", "123");
		String pwd = userMap.get(username);

		if (pwd != null) {
			if (pwd.equals(password)) {
				return true;
			}
		}
		return false;
	}

}
