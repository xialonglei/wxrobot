package com.xll.service;

import java.util.List;

/**
 * ��������ϵ�˷�����Ϣ
 * 
 * @author xialonglei
 * @date 2016/10/03
 */
public interface MessageService {
	
	/**�����ı���Ϣ*/
	void sendTextMessage(List<String> userNames , String message);
	
	/**����ͼƬ��Ϣ*/
	void sendMultiMessage(List<String> userNames , String path);

}
