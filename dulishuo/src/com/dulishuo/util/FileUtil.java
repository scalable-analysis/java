package com.dulishuo.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import net.sf.json.JSONObject;

public class FileUtil {
	
	
	public static void gxtx() {
		// TODO Auto-generated method stub
		System.out.println("success");
	}

	public static String earse(String src){
		String result = "";
		
		String regex = "<(?!br).*?>";   //去除所有标签，只剩br
		
		src = src.replace("</div>", "<br>").replace("</p>","").replace("</h>","").replace("</ul>","").replace("</li>","").replace("\\n", "").replace("</ol>","").replace("</tr>","");
		
		result = src.replaceAll(regex, "").replaceAll("&nbsp[\\;]?","").replace("<br />","<br>");
		
		return result.trim();
	}
	
	public static BufferedReader FileReader(String path) throws UnsupportedEncodingException{
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reader;
	}
	public static BufferedReader FileReader(String path, String cha) throws UnsupportedEncodingException{
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),cha));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reader;
	}
	
	public static BufferedWriter FileWriter(String path,boolean append){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,append)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return writer;
	}
	
	public static BufferedWriter FileWriter(String path){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return writer;
	}
	
	public static List<String> FileToList(String path){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list = new ArrayList<String>();
		String each_line = "";
		
		try {
			while((each_line=reader.readLine()) != null){
				if(!each_line.equals("")){
					list.add(each_line);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static String FileToString(String path){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String each_line = "";
		String result = "";
		try {
			while((each_line=reader.readLine()) != null){
				System.out.println(each_line.trim());
				if(!each_line.equals("")){
					result = result + each_line.trim();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static List<String> FileToList(String path , String charSet) throws UnsupportedEncodingException{
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),charSet));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list = new ArrayList<String>();
		String each_line = "";
		
		try {
			while((each_line=reader.readLine()) != null){
				if(!each_line.equals("")){
					list.add(each_line);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static List<JSONObject> FileToJsonList(String path,String ch){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<JSONObject> list = new ArrayList<JSONObject>();
		String each_line = "";
		
		try {
			while((each_line=reader.readLine()) != null){
				if(!each_line.equals("")){
					JSONObject json = JSONObject.fromObject(each_line);
					list.add(json);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(each_line);
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void ListToFile(List<String> list , String path){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String each : list){	
			try {
				writer.write(each);
				writer.newLine();
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ListToFile(List<String> list , String path , boolean flag){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path , flag)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String each : list){	
			try {
				writer.write(each);
				writer.newLine();
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static HSSFSheet getExcelSht(String path, int num){
		POIFSFileSystem ps;
		HSSFSheet sheet = null;
		try {
			ps = new POIFSFileSystem(new FileInputStream(path));
			HSSFWorkbook wb = new HSSFWorkbook(ps);
			sheet = wb.getSheetAt(num);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sheet;
	}
	public static String getWordDoc(String path) {
		InputStream is = null;
		try {
			is = new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WordExtractor ex = null;
		try {
			ex = new WordExtractor(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text2003 = ex.getText();
		return text2003;
	}
	
	
	public static List<JSONObject> FileToListJson(String path, int cols) throws IOException{
		List<JSONObject> list = new ArrayList<JSONObject>();
		
		POIFSFileSystem ps = new POIFSFileSystem(new FileInputStream(path));
		HSSFWorkbook wb = new HSSFWorkbook(ps);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		HSSFRow row;
		List<String> keys = new ArrayList<String>();
		
		row = sheet.getRow(0);
		for(int i = 0 ; i < cols ; i++){
			String key = row.getCell(i).toString();
			keys.add(key);
		}
		
		for(int i = 1 ; i < rows; i++){
			row = sheet.getRow(i);
			Map<String,String> map = new HashMap<String,String>();
			for(int j = 0 ; j < cols ; j++){
				try{
					if(!row.getCell(j).toString().equals("") && row.getCell(j)!=null){
						String value = row.getCell(j).toString();
						map.put(keys.get(j), value);
					}else
						map.put(keys.get(j), "-1");
				}catch(Exception e){
					System.out.println(e.toString());
					System.out.println(row.toString());
					map.put(keys.get(j), "-1");
				}
			}
			JSONObject json = JSONObject.fromObject(map);
			list.add(json);			
		}
		
		return list;
	}
}
