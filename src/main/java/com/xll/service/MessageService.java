package com.xll.service;

import java.util.List;


/**
 * 发送消息接口
 * 
 * @author xialonglei
 * @date 2016/10/03
 */
public interface MessageService {
	
	/**发送文本消息*/
	void sendTextMessage(List<String> userNames , String message);
	
	/**发送文件消息*/
	void sendMultiMessage(List<String> userNames , String path);

}
