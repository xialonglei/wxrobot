package com.xll.util;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * ģ�����������
 * 
 * @author xialonglei
 * @date 2016/10/02
 */
@Component
public class MyWebClient extends WebClient {

	private static final long serialVersionUID = -4741634000362033009L;

	public MyWebClient() throws MalformedURLException {
		initArgs();
	}

	private void initArgs() throws MalformedURLException {

		this.getOptions().setJavaScriptEnabled(true);
		this.getOptions().setCssEnabled(false);
		this.getOptions().setUseInsecureSSL(true);

	}

	/**
	 * ��ִ��this.getPage(url)ʱ�ᴥ������������
	 * 
	 * @param url
	 *            Ҫ���ʵ�url
	 * @return void
	 */
	public void visitUrl(String url) throws IOException {
		@SuppressWarnings("unused")
		HtmlPage page = (HtmlPage) this.getCurrentWindow().getEnclosedPage();
		page = this.getPage(url);
	}

}
