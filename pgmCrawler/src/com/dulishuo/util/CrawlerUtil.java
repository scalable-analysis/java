package com.dulishuo.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerUtil {
	public static List<String> getUrl(String url){
		List<String> result = new ArrayList<String>();
		String response = "";
	
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		//请求配置
		String ck = "tjpctrl=1439455956083; __utma=142000562.1509796625.1436709690.1438412213.1439453720.13; __utmz=142000562.1439453720.13.9.utmcsr=mail.qq.com|utmccn=(referral)|utmcmd=referral|utmcct=/cgi-bin/readtemplate; pgv_pvi=3811975168; 4Oaf_61d6_saltkey=nnmfbob3; 4Oaf_61d6_lastvisit=1437378911; 4Oaf_61d6_atarget=1; 4Oaf_61d6_visitedfid=27; 4Oaf_61d6_sid=Q89d9W; 4Oaf_61d6_lastact=1439454242%09home.php%09spacecp; 4Oaf_61d6_sendmail=1; __utmb=142000562.12.10.1439453720; __utmc=142000562; __utmt=1; 4Oaf_61d6_nofocus_member=1; 4Oaf_61d6_nofocus_forum=1; 4Oaf_61d6_ulastactivity=b1ccszmakCkkoWTaybWumhPQy0hx1PDIt6FDBQ5WElNhsEaEot6p; 4Oaf_61d6_auth=5808WxQ6wi8Gf9YPAp2asvku33LiM5xIWqdR67gpl8HOZTL87ISKHL5cTtUOFQR8V%2Be4wSyU5Taa4q9Va33Bq9v%2FlRM; 4Oaf_61d6_lastcheckfeed=175519%7C1439454242; 4Oaf_61d6_lip=118.187.68.34%2C1439453999; 4Oaf_61d6_nofavfid=1; 4Oaf_61d6_onlineusernum=258; 4Oaf_61d6_forum_lastvisit=D_27_1439454207; 4Oaf_61d6_smile=4D1; 4Oaf_61d6_viewid=tid_72476; 4Oaf_61d6_checkpm=1; 4Oaf_61d6_checkfollow=1";
        
        // 每次访问需授权的网址时需带上前面的 cookie 作为通行证  
        getMethod.setRequestHeader("Cookie", ck);
		getMethod.setRequestHeader("Host", "www.1point3acres.com");  
	    getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36");    
	    getMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
	    getMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");  
	    //getMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");  
	    getMethod.setRequestHeader("DNT", "1");  
	    getMethod.setRequestHeader("Referer", "http://www.1point3acres.com/bbs/");  
	    
	    try {
			client.executeMethod(getMethod);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			response = getMethod.getResponseBodyAsString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document html = Jsoup.parse(response);
		Element ele = html.getElementById("threadlisttableid");
		Elements tbodys = ele.children();
		int size = tbodys.size();
		for(int i = 1 ; i < size ; i++){
			String href = tbodys.get(i).getElementsByClass("icn").get(0).select("a").first().attr("href").toString();
			result.add(href);
		}	    
		return result;
	}
	
	public static String httpRequest(String url){
		String response = "-1";
		
		HttpClient client = new HttpClient();
		//设置代理服务器的ip地址和端口  
		//client.getHostConfiguration().setProxy("59.175.192.126", 3128);  
		//使用抢先认证  
		//client.getParams().setAuthenticationPreemptive(true); 
		GetMethod getMethod = new GetMethod(url);
		//请求配置
		//String cookie = "Hm_lvt_4bd66cbe45a640b607fe46c48f658746=1451016691; Hm_lpvt_4bd66cbe45a640b607fe46c48f658746=1451017061; PHPSESSID=86042607b79939dbfe1988582fd946dd; VMhOIbxho_checkcode=kiyd; VMhOIbxho_member=M6D2AcwfM0DbA3wcM3DeA6waM4Hcs9i4d6W8lek7I2j1o6i8M3j9g7y2O1TdU82eMyIsInNlc3Npb24iOiI4MDkyYWMwODczY2E4MjFmNTUwNjYwNGUzOGYyMGU5ZCIsInVzZXJuYW1lIjoiXHU2ZDZhXHU3OTVlIiwiZ3JvdXBpZCI6NCwidWluIjozNjc5MzIyfQO0O0OO0O0O; 4B5x_c0ae_saltkey=PlnUgvzf; 4B5x_c0ae_lastvisit=1451013385; 4B5x_c0ae_sid=3TpTYC; 4B5x_c0ae_lastact=1451016985%09dx_uicms_uc.php%09; 4B5x_c0ae_uicms_uid=3679322; 4B5x_c0ae_auth=b05fDAgbgUtBntSR0N%2FH4PDFESxBxYyXXxJMfRss1JXxC5zUyQ";
	   // getMethod.addRequestHeader("Cookie", cookie);
	    try {
			client.executeMethod(getMethod);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return response;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return response;
		}
	    try {
			response = getMethod.getResponseBodyAsString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return response;
		}
	    
	    return response;
	}
	
	public static String earse(String src){
		String result = "";
		
		String regex = "<(?!br).*?>";   //去除所有标签，只剩br
		
		src = src.replace("</div>", "<br>").replace("</p>","").replace("</h>","").replace("</ul>","").replace("</li>","").replace("\\n", "").replace("</ol>","").replace("</tr>","");
		
		result = src.replaceAll(regex, "").replaceAll("&nbsp[\\;]?","").replace("<br />","<br>");
		
		return result.trim();
	}
	
	//为每个帖子匹配school字段
		public static String getSchool(String req) throws UnsupportedEncodingException{
			String result = "-1";
			//String regex = "[^\u4e00-\u9fa5_a-zA-Z]+";
			
			//req = req.replaceAll(regex, "");
			String str = "";
			str = "http://123.57.250.189:8818/question_process?q="+URLEncoder.encode(req, "utf-8");
			
			try{
				URL url = new URL(str);
		         HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
		         urlcon.connect();         //获取连接
		         InputStream is = urlcon.getInputStream();
		         BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
		         StringBuffer bs = new StringBuffer();
		         String each = null;
		         while((each=buffer.readLine())!=null){
		             bs.append(each);
		         } 
		         result = bs.toString();
			}catch(Exception e){
				e.printStackTrace();
				return result;
			}
			       
	         return result;
		}
		
		private static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
	        public boolean verify(String s, SSLSession sslsession) {
	            System.out.println("WARNING: Hostname is not matched for cert.");
	            return true;
	        }
	    };


	     /**
	     * Ignore Certification
	     */
	    private static TrustManager ignoreCertificationTrustManger = new X509TrustManager() {
	        private X509Certificate[] certificates;

	        @Override
	        public void checkClientTrusted(X509Certificate certificates[],
	                String authType) throws CertificateException {
	            if (this.certificates == null) {
	                this.certificates = certificates;
	                System.out.println("init at checkClientTrusted");
	            }
	        }

	        @Override
	        public void checkServerTrusted(X509Certificate[] ax509certificate,
	                String s) throws CertificateException {
	            if (this.certificates == null) {
	                this.certificates = ax509certificate;
	                System.out.println("init at checkServerTrusted");
	            }
	        }

	        @Override
	        public X509Certificate[] getAcceptedIssuers() {
	            // TODO Auto-generated method stub
	            return null;
	        }
	    };


	    public static String httpsRequest(String urlString) {
	        ByteArrayOutputStream buffer = new ByteArrayOutputStream(512);
	        try {
	            URL url = new URL(urlString);
	            /*
	             * use ignore host name verifier
	             */
	            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
	            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
	            String cok = "csrftoken=GSqmfsT3VclDank3C4eXd4CAlXnLAa67; _ga=GA1.3.892752371.1440493108; Hm_lvt_50058a93dcd49a44afcea1dccb89a8e1=1448505213,1448534940,1448592899,1448604307; banner_remove=true; sessionid=hrw2rlln3sxre25otv4mrjdwtgnd2wvq; locale=; Hm_lpvt_50058a93dcd49a44afcea1dccb89a8e1=1448610187; _gat=1";
	            connection.setRequestProperty("Cookie", cok);
	            // Prepare SSL Context
	            TrustManager[] tm = { ignoreCertificationTrustManger };
	            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	            sslContext.init(null, tm, new java.security.SecureRandom());

	            // 从上述SSLContext对象中得到SSLSocketFactory对象
	            SSLSocketFactory ssf = sslContext.getSocketFactory();
	            connection.setSSLSocketFactory(ssf);
	            
	            InputStream reader = connection.getInputStream();
	            byte[] bytes = new byte[512];
	            int length = reader.read(bytes);

	            do {
	                buffer.write(bytes, 0, length);
	                length = reader.read(bytes);
	            } while (length > 0);

	            // result.setResponseData(bytes);
	            reader.close();
	            
	            connection.disconnect();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	        }
	        String repString= new String (buffer.toByteArray());
	        return repString;
	    }

}
