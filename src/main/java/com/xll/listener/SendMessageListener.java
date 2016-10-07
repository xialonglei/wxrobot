package com.xll.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.xll.task.SendMessageTask;

/**
 *@author xialonglei
 *@date 2016/10/07 
 */
public class SendMessageListener implements ServletContextListener{
	
	private SendMessageTask sendMessageTask;
	private WebApplicationContext acc;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		acc = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		sendMessageTask = (SendMessageTask) acc.getBean("sendMessageTask");
		sendMessageTask.sendMessage();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
