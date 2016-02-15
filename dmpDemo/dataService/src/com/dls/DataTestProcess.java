package com.dls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class DataTestProcess {

	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
		JSONObject jbt2 = null;
		int count = 0 ;
		Map<String,String> map = new HashMap<String,String>();
		// TODO Auto-generated method stub
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/强胜/Desktop/0702.json")));
		@SuppressWarnings("resource")
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/77777777.json")));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/88888.txt")));
		
		
		String tmp1 = "";
		
		while((tmp1  = br2.readLine()) != null){
			/*if(tmp1.contains("QS")){
				System.out.println(tmp1.substring(0, 3)+"---"+tmp1.substring(tmp1.indexOf("QS")+2));
				map.put(tmp1.substring(0, 3), tmp1.substring(tmp1.indexOf("QS")+2));
			}
			else{
				map.put(tmp1.substring(0, 3), "0");
				count++;
			}*/
			String[] tA = tmp1.split(" ");
			if(tA[tA.length-1].contains("USNEWS")){
				map.put(tA[0].substring(0, 3),"0");
				System.out.println(tA[0].substring(0, 3)+"\t"+"0");
			}else{
				map.put(tA[0].substring(0, 3), tA[tA.length-1].replace("QS", ""));
				System.out.println(tA[0].substring(0, 3)+"\t"+tA[tA.length-1].replace("QS", ""));
			}
				
			
		}
		System.out.println("count=="+map.size());
		
		String tmp2 = "";
		while((tmp2  = br1.readLine()) != null){
			JSONObject jbt = JSONObject.fromObject(tmp2);
			//System.out.println("44444");
			if(jbt.getString("rank").equals("0")){
				
				jbt.put("rank", map.get(jbt.getJSONObject("id").getString("$numberLong")));
				writer.write(jbt.toString());
				writer.newLine();
			}
					
		}
		
		writer.close();
		System.out.println("-----------end------------");
		
	}

}
