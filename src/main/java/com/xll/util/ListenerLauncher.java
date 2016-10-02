package com.xll.util;

import com.gargoylesoftware.htmlunit.WebClient;
import com.xll.listener.LoginListener;
import com.xll.listener.QRListener;
public class ListenerLauncher {
	
	@SuppressWarnings("resource")
	public void init(WebClient webClient){
		new QRListener(webClient);
		new LoginListener(webClient);
	}

}
