package com.xll.listener;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.FalsifyingWebConnection;
import com.xll.util.MyWebClient;

/**
 *监听器工厂
 *
 *@author xialonglei
 *@date 2016/10/02
 */
public abstract class ResponseListener extends FalsifyingWebConnection{

	public ResponseListener(MyWebClient myWebClient) throws IllegalArgumentException {
		super(myWebClient);
	}
	
	public void handle(WebResponse response){
		
	}

}
