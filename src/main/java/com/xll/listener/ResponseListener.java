package com.xll.listener;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.util.FalsifyingWebConnection;

public abstract class ResponseListener extends FalsifyingWebConnection{

	public ResponseListener(WebClient webClient) throws IllegalArgumentException {
		super(webClient);
	}
	
	public abstract void handle();

}
