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
import com.xll.bean.Command;
import com.xll.bean.Contactor;
import com.xll.bean.RobotResponse;
import com.xll.bean.WXData;
import com.xll.service.MessageService;
import com.xll.util.PicBootstrap;

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
		
		//过滤msg&gt信息
		if(con.contains("msg&gt")){
			return;
		}
		
		if(!WXData.getRecieveFriends().contains(friend)){
			List<String> autoRes = new ArrayList<String>();
			autoRes.add(friend);
			messageServiceImpl.sendTextMessage(autoRes, "您可以尝试发送,如下指令[loop,all,pic]");
			WXData.getRecieveFriends().add(friend);
			return;
		}
		
		autoResponse(friend , con);

	}

	private void autoResponse(String friend , String con) {
		
		String message = null;
		List<String> autoRes = new ArrayList<String>();
		autoRes.add(friend);
		
		if(con.toLowerCase().trim().equals(Command.LOOP)){
			new Thread(new Runnable(){
				@Override
				public void run() {
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

		}else if(con.toLowerCase().trim().equals(Command.STOP)){
			stop = true;
		}else if(con.toLowerCase().trim().equals(Command.ALL)){
			StringBuilder s = new StringBuilder();
			s.append("我所有好友名单如下:\n");
			for(Entry<String , Contactor> entry : WXData.getSingleton().getContactors().entrySet()){
				s.append(entry.getKey() + "\n");
			}
			message = s.toString();
			
		}else if(con.toLowerCase().trim().equals(Command.OPEN)){
			stop = false;
		}else if(con.toLowerCase().trim().equals(Command.PIC)){
			if(PicBootstrap.picsPath.size() > 0){
				message = PicBootstrap.picsPath.get((int)(Math.random() * PicBootstrap.picsPath.size()));
				LOGGER.info("您向好友发送图片的路径[{}]" , message);
				messageServiceImpl.sendMultiMessage(autoRes, message);
				return;
			}else{
				message = "对不起,图片库还未初始化完成,请稍后再发送pic指令!";
			}
		}else{
			if(RobotResponse.dirtyWords.contains(con.trim().replace(" ", ""))){
				message = "维护网络环境,人人有责!";
			}else{
				message = RobotResponse.autoSend.get((int)(Math.random() * RobotResponse.autoSend.size()));
			}
		}
		messageServiceImpl.sendTextMessage(autoRes, message);
	}

}
