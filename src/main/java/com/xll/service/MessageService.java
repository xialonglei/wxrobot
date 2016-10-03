package com.xll.service;

import java.util.List;

/**
 * 处理向联系人发送消息
 * 
 * @author xialonglei
 * @date 2016/10/03
 */
public interface MessageService {
	
	/**发送文本消息*/
	void sendTextMessage(List<String> userNames , String message);
	
	/**发送图片消息*/
	void sendMultiMessage(List<String> userNames , String path);

}
