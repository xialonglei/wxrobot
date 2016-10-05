package wxrobot.https.test;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class HttpsTest {
	@Test
	public void httpsTest(){
		System.setProperty("jsse.enableSNIExtension", "false");
		CloseableHttpClient closeableHttpClient = 
			      HttpClients.custom()
			                 .setSSLHostnameVerifier(new NoopHostnameVerifier())
			                 .build();
		HttpGet get = new HttpGet("https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxsendmsg?lang=zh_CN&pass_ticket=gYHV3BSJtmQCYpAcgycT%2FGmsNcVOn%2BnrZ0ajAB2PZUyth5uiyPJBaSpSh723zkbE");
		try {
			HttpResponse res = closeableHttpClient.execute(get);
			System.out.println(res.getStatusLine());
			System.out.println(new Date().getTime() + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
