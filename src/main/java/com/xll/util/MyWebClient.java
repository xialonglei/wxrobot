package com.xll.util;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 模拟浏览器类
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
	 * 执行this.getPage(url)会触发监听器
	 * 
	 * @param url
	 *            请求的url
	 * @return void
	 */
	public void visitUrl(String url) throws IOException {
		@SuppressWarnings("unused")
		HtmlPage page = (HtmlPage) this.getCurrentWindow().getEnclosedPage();
		page = this.getPage(url);
	}

}
