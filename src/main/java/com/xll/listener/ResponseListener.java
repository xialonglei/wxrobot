package com.xll.listener;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.FalsifyingWebConnection;

/**
 *³éÏó¼àÌýÆ÷ 
 *
 *@author xialonglei
 *@date 2016/10/02
 */
public abstract class ResponseListener extends FalsifyingWebConnection{

	public ResponseListener(WebClient webClient) throws IllegalArgumentException {
		super(webClient);
	}
	
	public void handle(WebResponse response){
		
	}

}
