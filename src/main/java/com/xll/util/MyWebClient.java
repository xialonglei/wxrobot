package com.xll.util;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@SuppressWarnings("serial")
@Component
public class MyWebClient extends WebClient{
	

	
	public MyWebClient() throws MalformedURLException{
		initArgs();
	}

	private void initArgs() throws MalformedURLException {
		
		this.getOptions().setJavaScriptEnabled(true);
		this.getOptions().setCssEnabled(false);
		this.getOptions().setUseInsecureSSL(true);
		
		
	}
	
	public void visitUrl(String url) throws IOException{
		@SuppressWarnings("unused")
		HtmlPage page = (HtmlPage) this.getCurrentWindow().getEnclosedPage();
		page = this.getPage(url);
	}

}
