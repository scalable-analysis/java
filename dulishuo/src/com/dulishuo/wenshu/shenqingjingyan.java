package com.dulishuo.wenshu;

import java.util.*;
import java.io.*;

import net.sf.json.JSONObject;

import org.apache.poi.hpsf.extractor.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.dulishuo.util.FileUtil;

public class shenqingjingyan {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*String path="C:/Users/强胜/Desktop/数据补全/关键字/文书/";
		List<String> list = new ArrayList<String>();
		File file=new File(path);
		File[] tempList = file.listFiles();
		System.out.println(tempList.length);
		int flag = 5000;
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				BufferedReader br = FileUtil.FileReader(tempList[i].getPath());
				 
			    
				Map<String,Object> map = new HashMap<String,Object>();
				String line = "";
				String tmp = "";
				
				while((tmp = br.readLine()) != null)
					line = line + tmp;
				
				
				//line = new String(new String(line.getBytes("UTF-8"),"ISO-8859-1").getBytes("ISO-8859-1"),"UTF-8");
				
				map.put("id", flag++);
				map.put("title", tempList[i].getName());
				map.put("content", line);
			    map.put("faculty_id", -1);
			    map.put("facultytype_id", -1);
			    map.put("type", "key-15");
			    
			    JSONObject json = JSONObject.fromObject(map);
			    list.add(json.toString());
			}
		}
		
		FileUtil.ListToFile(list, "C:/Users/强胜/Desktop/数据补全/关键字/文书/文书.json");
		FileUtil.ListToFile(list, "C:/Users/强胜/Desktop/数据补全/关键字/key.json", true);*/
		
		String path="C:/Users/强胜/Desktop/申请经验（按网站分类）改/一亩三分地/新建文件夹/";
		List<String> res = new ArrayList<String>();
		File file=new File(path);
		File[] tempList = file.listFiles();
		int flag = 5320;
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				String name = tempList[i].getName();
				String cha = "";
				try {
					cha = codeString(tempList[i].getPath());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BufferedReader br = null;
				if(cha.indexOf("G") != -1)
					br = FileUtil.FileReader(tempList[i].getPath(),"GBK");
				else
					br = FileUtil.FileReader(tempList[i].getPath());
				
			    
				Map<String,Object> map = new HashMap<String,Object>();
				String line = "";
				String tmp = "";
				
				while((tmp = br.readLine()) != null)
					line = line + tmp+"<br>";
				
				if(cha.indexOf("G") != -1)
					line = new String(new String(line.getBytes("UTF-8"),"ISO-8859-1").getBytes("ISO-8859-1"),"UTF-8");
				
				System.out.println(line);
				
				map.put("id", flag++);
				map.put("title", tempList[i].getName());
				map.put("content", line);
				map.put("remark", "1point3acres");
			    map.put("faculty_id", -1);
			    map.put("facultytype_id", -1);
			    map.put("institute_id", -1);
			    map.put("type", "ae");
			    
			    JSONObject json = JSONObject.fromObject(map);
			    res.add(json.toString());
			}
		}
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/申请经验/ae.json",true);
		System.out.println(flag);
		System.out.println("____end____");
	}
	
	
	
	public static String codeString(String fileName) throws Exception{  
	    BufferedInputStream bin = new BufferedInputStream(  
	    new FileInputStream(fileName));  
	    int p = (bin.read() << 8) + bin.read();  
	    String code = null;  
	      
	    switch (p) {  
	        case 0xefbb:  
	            code = "UTF-8";  
	            break;  
	        case 0xfffe:  
	            code = "Unicode";  
	            break;  
	        case 0xfeff:  
	            code = "UTF-16BE";  
	            break;  
	        default:  
	            code = "GBK";  
	    }  
	      
	    return code;  
	} 
}
