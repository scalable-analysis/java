package com.dls.crawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;


public class test0701 {

	public static void main(String[] args) throws IOException, ParserException {
		// TODO Auto-generated method stub
		/*HttpClient client = new HttpClient();
		GetMethod  method = new GetMethod("http://www.zmnedu.com/");
		client.executeMethod(method);
		System.out.println(method.getResponseBodyAsString());*/
		
		Parser psr = new Parser((HttpURLConnection)(new URL("http://www.zmnedu.com/")).openConnection());
		TextExtractingVisitor visitor = new TextExtractingVisitor();
		psr.visitAllNodesWith(visitor);
		System.out.println(visitor.getExtractedText());
	}

}
