package com.xll.util;

import com.xll.listener.factory.ListenerFactory;

/**
 * 真正执行监听器初始化类
 *
 * @author xialonglei
 * @date 2016/10/02
 */
public class ListenerLauncher {
	
	public void init(MyWebClient webClient) {
		ListenerFactory.getListenerInstance("qr", webClient);
		ListenerFactory.getListenerInstance("login", webClient);
		ListenerFactory.getListenerInstance("contact", webClient);
	}
}
