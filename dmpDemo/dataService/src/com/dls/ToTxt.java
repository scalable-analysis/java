package com.dls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ToTxt {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/强胜/Desktop/mailattachment/offerR.txt")));

	     
	     BufferedReader brO = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/强胜/Desktop/mailattachment/offer22.txt")));
			String tmpO;
			StringBuilder sbO = new StringBuilder();
			while((tmpO=brO.readLine()) != null){
				
				sbO.append(tmpO);
			}
			
			String srcO = sbO.toString();
			JSONArray jArrayO = JSONArray.fromObject(srcO);
			JSONObject jobjO;
			
			for(int i = 0 ; i < jArrayO.size(); i++){
				jobjO = (JSONObject) jArrayO.get(i);
				writer.write("id:"+jobjO.get("id").toString()+"\t"+"school_id:"+jobjO.get("school_id").toString()+"\t"+"subject:"+jobjO.get("major").toString());
				writer.newLine();
			}
			
			writer.close();
			System.out.println("-------------end------------------");
	}

}
