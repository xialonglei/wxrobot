package com.xll.util;

import com.xll.listener.factory.ListenerFactory;
import com.xll.service.MessageService;

/**
 * 真正执行监听器初始化类
 *
 * @author xialonglei
 * @date 2016/10/02
 */
public class ListenerLauncher {
	
	public void init(MyWebClient webClient , MessageService messageServiceImpl) {
		ListenerFactory.getListenerInstance("qr", webClient , messageServiceImpl);
		ListenerFactory.getListenerInstance("login", webClient , messageServiceImpl);
		ListenerFactory.getListenerInstance("contact", webClient , messageServiceImpl);
		ListenerFactory.getListenerInstance("get", webClient , messageServiceImpl);
	}
}
