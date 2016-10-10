package com.xll.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.xll.bean.Contactor;
import com.xll.bean.RobotResponse;
import com.xll.bean.WXData;
import com.xll.constant.Constants;
import com.xll.service.MessageService;

/**
 * 接收消息的监听器
 * 
 * @author xialonglei
 * @date 2016/10/09
 */
public class GetMessageListener extends ResponseListener {
	
	private boolean stop = false;

	private MessageService messageServiceImpl;

	private static final Logger LOGGER = LoggerFactory.getLogger(GetMessageListener.class);

	public GetMessageListener(WebClient webClient , MessageService messageServiceImpl) throws IllegalArgumentException {
		super(webClient);
		this.messageServiceImpl = messageServiceImpl;
	}

	@Override
	public WebResponse getResponse(WebRequest request) throws IOException {
		WebResponse response = super.getResponse(request);
		String url = response.getWebRequest().getUrl().toString();
		if (url.contains("mmwebwx-bin/webwxsync")) {
			handle(response);
		}

		return response;
	}

	@Override
	public void handle(WebResponse response) {
		String conJson = response.getContentAsString();
		JSONObject obj = JSON.parseObject(conJson);
		JSONObject messageObj = null;
		if(obj.getJSONArray("AddMsgList") != null && obj.getJSONArray("AddMsgList").size() != 0){
			messageObj = obj.getJSONArray("AddMsgList").getJSONObject(0);
		}else{
			return;
		}
		String con = messageObj.getString("Content");
		String friend = messageObj.getString("FromUserName");
		Map<String, Contactor> contactors = WXData.getSingleton().getContactors();
		String nickName = null;
		for (Entry<String, Contactor> contactor : contactors.entrySet()) {
			if (contactor.getValue().getUserName().equals(friend)) {
				nickName = contactor.getKey();
				break;
			}
		}

		if (nickName == null) {
			LOGGER.warn("不明飞行物向您发送消息,消息的内容是[{}],请检查!", con);
			List<String> autoRes = new ArrayList<String>();
			autoRes.add(friend);
			messageServiceImpl.sendTextMessage(autoRes, "您是怎么向我发送消息的,您好像不是我的好友呀!");
			return;
		}
		
		LOGGER.info("好友[{}]向您发送消息,消息的内容是[{}]", nickName, con);
		
		if(con.contains("msg&gt")){
			return;
		}
		
		if(!WXData.getRecieveFriends().contains(friend)){
			List<String> autoRes = new ArrayList<String>();
			autoRes.add(friend);
			messageServiceImpl.sendTextMessage(autoRes, "您可以尝试发送,如下指令[loop]");
			WXData.getRecieveFriends().add(friend);
			return;
		}
		
		autoResponse(friend , con);

	}

	private void autoResponse(String friend , String con) {
		if(con.trim().equals("loop")){
			new Thread(new Runnable(){
				@Override
				public void run() {
					List<String> autoRes = new ArrayList<String>();
					autoRes.add(friend);
					while(!stop){
						messageServiceImpl.sendTextMessage(autoRes, "您中毒了！[想要终止试试stop,有5s延迟!]");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							LOGGER.error("" , e);
						}
					}
				}
				
			}).start();	

		}else if(con.trim().equals("stop")){
			stop = true;
		}else{
			List<String> autoRes = new ArrayList<String>();
			autoRes.add(friend);
			String message = RobotResponse.autoSend.get((int)(Math.random() * RobotResponse.autoSend.size()));
			messageServiceImpl.sendTextMessage(autoRes, message);
			LOGGER.info("{}发送的消息内容[{}]" , Constants.SEND_MSG_SUCCESS , message);
		}
		
	}

}
