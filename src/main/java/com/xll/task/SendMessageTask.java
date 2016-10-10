package com.xll.task;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xll.bean.RobotResponse;
import com.xll.bean.WXData;
import com.xll.constant.Constants;
import com.xll.service.MessageService;

/**
 *@author xialonglei
 *@date 2016/10/07 
 */
@Component
public class SendMessageTask {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendMessageTask.class);

	@Resource
	private MessageService messageServiceImpl;
	
    @Scheduled(cron="0 0/1 * * * ?")
	public void sendMessage() {
    	if(WXData.getSingleton() != null && WXData.getSingleton().getContactors() != null && WXData.getSingleton().getContactors().get("One、Life") != null){
			List<String> sendUsernames = new ArrayList<String>();
			sendUsernames.add(WXData.getSingleton().getContactors().get("One、Life").getUserName());
			//sendUsernames.add(WXData.getSingleton().getContactors().get("世界未末日").getUserName());
			try{
				String message = RobotResponse.autoSend.get((int)(Math.random() * RobotResponse.autoSend.size()));
				messageServiceImpl.sendTextMessage(sendUsernames , message);
				LOGGER.info("{}发送的消息内容[{}]" , Constants.SEND_MSG_SUCCESS , message);
			}catch(Exception e){
				LOGGER.error("{}" , e , Constants.SEND_MSG_FAILURE);
			}	
		}else{
			LOGGER.warn(Constants.LOGIN_HINT);
		}
	}

}
