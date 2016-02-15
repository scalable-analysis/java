package com.dulishuo.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.RowFilter.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import net.sf.json.JSONObject;

import com.dulishuo.util.FileUtil;
import com.dulishuo.util.Util;

public class test {
	static int count = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		
		//hehe();
		//test();
		//argi();
		tt();
		long end = System.currentTimeMillis();		
		System.out.println("___end___"+(end-start)/1000+"秒");
	}
	private static void tt() {
		// TODO Auto-generated method stub
		String url = "http://123.57.22.27:8000/assess_applier?condition={%22current-school%22:0,%22internship%22:{%22duration%22:0,%22recommendation%22:0,%22level%22:0},%22competition%22:{%22level%22:0},%22toefl%22:{%22total%22:1,%22speaking%22:1},%22GMAT%22:{%22total%22:1,%22writing%22:1},%22gpa-trend-enum%22:0,%22scholarship%22:{%22level%22:0},%22credential%22:{%22credential%22:0},%22gpa%22:1,%22research%22:{%22duration%22:0,%22level%22:0,%22achievement%22:0,%22recommendation%22:0},%22gre%22:{%22total%22:1,%22aw%22:1,%22v%22:1},%22ielts%22:{%22total%22:1},%22work%22:{%22duration%22:0,%22level%22:0,%22recommendation%22:0},%22activity%22:{%22duration%22:0,%22type%22:0}}&major_type=accounting";
		String response = Util.httpRequest(url);
		System.out.println(url);
	}
	private static void argi() {
		Map<String,Integer> xxggxx = new HashMap<String, Integer>();
		xxggxx.put("全国",2);
		xxggxx.put("北京",3);
		xxggxx.put("天津",4);
		xxggxx.put("河北",5);
		xxggxx.put("山西",6);
		xxggxx.put("内蒙古",7);
		xxggxx.put("辽宁",8);
		xxggxx.put("吉林",9);
		xxggxx.put("黑龙江",10);
		xxggxx.put("上海",11);
		xxggxx.put("江苏",12);
		xxggxx.put("浙江",13);
		xxggxx.put("安徽",14);
		xxggxx.put("福建",15);
		xxggxx.put("江西",16);
		xxggxx.put("山东",17);
		xxggxx.put("河南",18);
		xxggxx.put("湖北",19);
		xxggxx.put("湖南",20);
		xxggxx.put("广东",21);
		xxggxx.put("广西",22);
		xxggxx.put("海南",23);
		xxggxx.put("重庆",24);
		xxggxx.put("四川",25);
		xxggxx.put("贵州",26);
		xxggxx.put("云南",27);
		xxggxx.put("西藏",28);
		xxggxx.put("陕西",29);
		xxggxx.put("甘肃",30);
		xxggxx.put("青海",31);
		xxggxx.put("宁夏",32);
		xxggxx.put("新疆",33);

		Map<String, Integer> fruit = new HashMap<String, Integer>();
		fruit.put("水果",1);
		fruit.put("香蕉",3);
		fruit.put("菠萝",5);
		fruit.put("荔枝",7);
		fruit.put("龙眼",9);
		fruit.put("桃",11);
		fruit.put("猕猴桃",13);
		fruit.put("葡萄",15);
		fruit.put("红枣",17);
		fruit.put("苹果",19);
		fruit.put("梨",21);
		fruit.put("柑橘类",23);
		fruit.put("柿子",25);
		
		Map<String, Integer> vegetable = new HashMap<String, Integer>();
		vegetable.put("胡萝卜",1);
		vegetable.put("白菜",4);
		vegetable.put("茄子",7);
		vegetable.put("南瓜",10);
		vegetable.put("其他",13);
		vegetable.put("草莓",16);
		vegetable.put("莲藕",19);
		vegetable.put("莴苣",22);
		vegetable.put("甘蓝",25);
		vegetable.put("大蒜",28);
		vegetable.put("黄瓜",31);
		vegetable.put("茭白",34);
		vegetable.put("蔬菜",37);
		vegetable.put("芦笋",40);
		vegetable.put("丝瓜",43);
		vegetable.put("竹笋",46);
		vegetable.put("普通白菜",49);
		vegetable.put("萝卜",52);
		vegetable.put("大葱",55);
		vegetable.put("藠头",58);
		vegetable.put("豇豆",61);
		vegetable.put("芥菜",64);
		vegetable.put("冬瓜",67);
		vegetable.put("花椰菜",70);
		vegetable.put("韭菜",73);
		vegetable.put("辣椒",76);
		vegetable.put("苦瓜",79);
		vegetable.put("菜豆",82);
		vegetable.put("蕹菜",85);
		vegetable.put("甜椒",88);
		vegetable.put("洋葱",91);
		vegetable.put("马铃薯",94);
		vegetable.put("金针菜",97);
		vegetable.put("大白菜",100);
		vegetable.put("蕃茄",103);
		vegetable.put("菠菜",106);
		vegetable.put("苋菜",109);
		vegetable.put("芹菜",112);
		vegetable.put("姜",115);
		vegetable.put("芋",118);
		
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();  
		List<JSONObject> fruitt = FileUtil.FileToJsonList("C:/Users/强胜/Downloads/fruit.txt", "utf-8");
		//List<JSONObject> vege = FileUtil.FileToJsonList("C:/Users/强胜/Downloads/vegetable.txt", "utf-8");
		Map<Integer,List<JSONObject>> map = new HashMap<Integer,List<JSONObject>>();
		List<JSONObject> tmp;
		for(JSONObject json : fruitt){
			if(map.containsKey(json.getInt("year")))
				tmp = map.get(json.getInt("year"));
			else
				tmp = new ArrayList<JSONObject>();
			tmp.add(json);
			map.put(json.getInt("year"), tmp);
		}
		List<JSONObject> tmpp ;
		List<JSONObject> tmxx ;
		for(int i = 2014 ; i > 1948 ; i--){
			System.out.println("执行到年分："+i);
			tmpp = map.get(i);
			Map<String,List<JSONObject>> mapp = new HashMap<String,List<JSONObject>>();
			HSSFSheet sheet = wb.createSheet(String.valueOf(i)); 
			for(JSONObject json : tmpp){
				if(mapp.containsKey(json.getString("province_id")))
					tmp = mapp.get(json.getString("province_id"));
				else
					tmp = new ArrayList<JSONObject>();
				tmp.add(json);
				mapp.put(json.getString("province_id"), tmp);
			}
			int hangshu = -1;
			String ff = "";
			Row row1 = sheet.createRow(0);
			Row row2 = sheet.createRow(1);
			for(String xx : mapp.keySet()){
				tmxx = mapp.get(xx);
				hangshu = xxggxx.get(xx);
				Row row = sheet.createRow(hangshu);
				Cell cell = row.createCell(0); 
				cell.setCellValue(xx);
				for(JSONObject yy : tmxx){
					ff = yy.getString("fruit_id");
					String xxoo = "";
					if(yy.getString("total").contains("符合"))
						xxoo = "0";
					else
						xxoo = yy.getString("total");
					if(yy.getString("type").contains("面积")){
						Cell cell3 = row1.createCell(fruit.get(ff));
						cell3.setCellValue(yy.getString("fruit_id"));
						Cell cell4 = row2.createCell(fruit.get(ff));
						cell4.setCellValue("面积（千公顷）");
						Cell cell1 = row.createCell(fruit.get(ff)); 
						cell1.setCellValue(xxoo);
					}else if(yy.getString("type").contains("产量")){
						
						Cell cell4 = row2.createCell(fruit.get(ff)+1);
						cell4.setCellValue("产量（吨）");
						Cell cell2 = row.createCell(fruit.get(ff)+1); 
						cell2.setCellValue(xxoo);
					}/*else{
						Cell cell4 = row2.createCell(vegetable.get(ff)+2);
						cell4.setCellValue("产量（吨）");
						Cell cell2 = row.createCell(vegetable.get(ff)+2); 
						cell2.setCellValue(xxoo);
					}*/
					
					
				}
				
				
				/*for(int j =1 ; j < 27 ; j = j+2){
					Cell cell1 = row.createCell(j); 
					cell1.setCellValue();
					Cell cell2 = row.createCell(j+1); 
				}*/
			}
		}
		
		//保存为Excel文件  
				FileOutputStream out = null;  
				  
				try {  
				    out = new FileOutputStream("C:/Users/强胜/Desktop/gig.xls");  
				    wb.write(out);        
				} catch (IOException e) {  
				    System.out.println(e.toString());  
				} finally {  
				    try {  
				        out.close();  
				    } catch (IOException e) {  
				        System.out.println(e.toString());  
				    }  
				}     
			
	}
	private static void test() {
		// TODO Auto-generated method stub
		
		 HSSFWorkbook wb = new HSSFWorkbook();  
		  
		//添加Worksheet（不添加sheet时生成的xls文件打开时会报错）  
		@SuppressWarnings("unused")  
		HSSFSheet sheet1 = wb.createSheet();  
		@SuppressWarnings("unused")  
		HSSFSheet sheet2 = wb.createSheet();  
		@SuppressWarnings("unused")  
		HSSFSheet sheet3 = wb.createSheet("new sheet");  
		@SuppressWarnings("unused")  
		HSSFSheet sheet4 = wb.createSheet("rensanning");  

	    Row row1 = sheet1.createRow(1);  
	    Cell cell1_1 = row1.createCell(1);  
	    cell1_1.setCellValue(123);  
	      
	    Row row4 = sheet1.createRow(4);  
	    Cell cell4_3 = row4.createCell(3);  
	    cell4_3.setCellValue("中国");  
		  
		//保存为Excel文件  
		FileOutputStream out = null;  
		  
		try {  
		    out = new FileOutputStream("C:/Users/强胜/Desktop/gig.xls");  
		    wb.write(out);        
		} catch (IOException e) {  
		    System.out.println(e.toString());  
		} finally {  
		    try {  
		        out.close();  
		    } catch (IOException e) {  
		        System.out.println(e.toString());  
		    }  
		}     
	}
	private static void hehe() {
		// TODO Auto-generated method stub
		List<JSONObject> list = FileUtil.FileToJsonList("C:/Users/强胜/programappend1225.json", "utf-8");
		HSSFSheet sht = FileUtil.getExcelSht("C:/Users/强胜/Desktop/圣诞/merry.xls", 0);
		
		List<String> name = FileUtil.FileToList("C:/Users/强胜/Desktop/圣诞/hehe.txt");
		Map<String , String> heehda = new HashMap<String , String>();
		heehda.put("一月", "1");
		heehda.put("二月", "2");
		heehda.put("三月", "3");
		heehda.put("四月", "4");
		heehda.put("五月", "5");
		heehda.put("六月", "6");
		heehda.put("七月", "7");
		heehda.put("八月", "8");
		heehda.put("九月", "9");
		heehda.put("十月", "10");
		heehda.put("十一月", "11");
		heehda.put("十二月", "12");
		
		Map<String , Long[]> map = new HashMap<String , Long[]>();
		String value ;
		for(int i = 0 ; i < sht.getLastRowNum()+1; i++){
			try{
			if(!sht.getRow(i).getCell(0).equals(null) && sht.getRow(i).getCell(0).toString().length()>2){
				value = sht.getRow(i).getCell(1).toString().replace("-", "/");
				for(String xx : heehda.keySet()){
					if(value.contains(xx))
						value = value.replace(xx, heehda.get(xx));
				}
				if(value.split("/").length > 4)
					value = value.split("/")[0]+"/"+value.split("/")[1]+"/"+value.split("/")[2]+"-"+value.split("/")[3]+"/"+value.split("/")[4]+"/"+value.split("/")[5];
				if(value.contains("-")){
					Long[] tmp = new Long[2];
					tmp[0] = new Date(value.split("-")[0]).getTime()/1000;
					tmp[1] = new Date(value.split("-")[1]).getTime()/1000;
					map.put(name.get(i), tmp);
				}else{
					Long[] tmp = new Long[1];
					tmp[0] = new Date(value).getTime()/1000;
					map.put(name.get(i), tmp);
				}
				
				System.out.println(value);
			}
			}catch(Exception e){}
		}
		
		List<String> res = new ArrayList<String>();
		int count = 12361;
		for(JSONObject json : list){
			json.remove("id");
			json.remove("_id");
			json.put("id", count++);
			json.put("oldDeadline", json.getString("deadline"));
			if(map.containsKey(json.getString("deadline")))
				json.put("deadline", map.get(json.getString("deadline")));
			else{
				Long[] tmp = new Long[0];
				json.put("deadline", tmp);
			}		
			res.add(json.toString());
		}
		
		
		FileUtil.ListToFile(res, "C:/Users/强胜/Desktop/我是你爹/pgmAppend.json");
	}


}
