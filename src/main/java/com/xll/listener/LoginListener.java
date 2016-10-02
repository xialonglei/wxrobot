package com.xll.listener;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.xll.listener.ResponseListener;

public class LoginListener extends ResponseListener{

	public LoginListener(WebClient webClient) throws IllegalArgumentException {
		super(webClient);
	}
	
	@Override
	public WebResponse getResponse(WebRequest request) throws java.io.IOException {
		WebResponse response = super.getResponse(request);
		String detectUrl = response.getWebRequest().getUrl().toString();
		if(detectUrl.contains("mmwebwx-bin/webwxnewloginpage")){
			String resContent = response.getContentAsString();
			System.out.println(resContent);
		}
		return response;
	}

	@Override
	public void handle() {
		
	}

}
