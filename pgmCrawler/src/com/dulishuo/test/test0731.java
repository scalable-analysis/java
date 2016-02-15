package com.dulishuo.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.dulishuo.util.CrawlerUtil;

public class test0731 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String url = "http://123.57.250.189:8818/question_process?q=请问澳大利亚纽卡斯尔大学工程专业有奖学金么纽卡斯尔大学httpwwweduaucomnewcastle工程学院每年为以下专业的本科生提供个奖学金名额工程建筑与建筑管理br澳洲留学网httpwwwsharewithucomcollege欢迎您咨询";
		System.out.println(CrawlerUtil.httpRequest(URLEncoder.encode(url,"utf-8")));
		System.out.println("___end____");
	}

}
