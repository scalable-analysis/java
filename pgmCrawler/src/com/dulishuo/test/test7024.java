package com.dulishuo.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test7024 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String xx = "<div class=\\\"quote\\\"> <blockquote>  <font size=\\\"2\\\"><font color=\\\"#999999\\\">yangzeyao 发表于 2012-11-3 14:45<\\/font> <a href=\\\"http: \\/\\/www.1point3acres.com\\/bbs\\/forum.php?mod=redirect&amp;goto=findpost&amp;pid=583345&amp;ptid=40117\\\" target=\\\"_blank\\\"><style>.guestviewthumb {margin:10px auto; text-align:center;}.guestviewthumb a {font-size:12px;}.guestviewthumb_cur {cursor:url(static\\/image\\/common\\/scf.cur), default; max-width:100px;}.ie6 .guestviewthumb_cur { width:100px !important;}<\\/style><\\/a>   <div class=\\\"guestviewthumb\\\">    <a><br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <br><\\/a>    <a href=\\\"member.php?mod=logging&amp;action=login\\\" onclick=\\\"showWindow('login',this.href+'&amp;referer='+encodeURIComponent(location));\\\">登录\\/注册后可看大图<\\/a>   <\\/div><\\/font>    <br> 如果不根据历年的录取情况和CS排名，那么还有哪些信息是可靠的呢？看到论坛里面说UMass DM很强，可是我问了 ... <\\\\/blockquote><\\\\/div>";
		String regex = "<div[\\s]class=\\\\\"quote\\\\\"><blockquote>.*?<\\\\/blockquote><\\\\/div>";
		System.out.println(xx);
		System.out.println(xx.replaceAll(regex, ""));
	}

}
