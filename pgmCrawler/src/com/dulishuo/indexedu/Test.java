package com.dulishuo.indexedu;

import java.io.BufferedWriter;
import java.io.IOException;

import com.dulishuo.util.FileUtil;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String tt = "<div class=\\\"editor\\\">  <span>责任编辑：admin</span> <\\/div>vvv";
		BufferedWriter bw = FileUtil.FileWriter("C:/Users/强胜/Desktop/dataCrawler/索学网/tt.json");
		String reg_footer1 = "<div[\\s]class=\\\\\"editor\\\\\">[\\s\\S]*?<\\\\/div>";
		String reg_footer2 = "<p[\\s]class=\\\"article_bj\\\">[\\s\\S]*?</p>";
		
		System.out.println(reg_footer1);
		
		System.out.println(tt);
		tt = tt.replaceAll(reg_footer1, "").replaceAll(reg_footer2, "");
		String yy = "类型：</u></p><p> 全额奖学金：";
		System.out.println(tt);
		bw.write(tt);
		bw.flush();
		bw.close();
	}

}
