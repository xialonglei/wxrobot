package com.xll.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.xll.bean.WXData;
import com.xll.constant.Constants;
import com.xll.service.MessageService;
import com.xll.util.HttpsUtil;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Override
	public void sendTextMessage(List<String> userNames, String message) {

		System.setProperty("jsse.enableSNIExtension", "false");

		StringEntity jsonParams = null;

		HttpClient httpClient = HttpsUtil.newHttpsClient();
		
		for (String toUserName : userNames) {

			String sendTime = new Date().getTime() + "0000";

			WXData.getSingleton().getMsg().put("Type", 1);
			WXData.getSingleton().getMsg().put("Content", message);
			WXData.getSingleton().getMsg().put("ToUserName", toUserName);
			WXData.getSingleton().getMsg().put("LocalID", sendTime);
			WXData.getSingleton().getMsg().put("ClientMsgId", sendTime);

			HttpPost post = new HttpPost(
					Constants.SEND_MSG_PREFIX + "?lang=zh_CN&pass_ticket=" + WXData.getSingleton().getPassTicket());


			post.setHeader(Constants.ACCEPT_NAME, "application/json, text/plain, */*");
			post.setHeader(Constants.ACCEPT_LANGUAGE_NAME, Constants.ACCEPT_LANGUAGE_VALUE);
			post.setHeader(Constants.USER_AGENT_NAME, Constants.USER_AGENT_VALUE);
			post.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
			post.setHeader("Host", "wx.qq.com");
			post.setHeader("Origin", "https://wx.qq.com");
			post.setHeader("Referer", "https://wx.qq.com/?&lang=zh_CN");

			JSONObject obj = new JSONObject();
			obj.put("BaseRequest", WXData.getSingleton().getBaseRequest());
			obj.put("Msg", WXData.getSingleton().getMsg());
			obj.put("Scene", 0);

			jsonParams = new StringEntity(obj.toJSONString(), "utf-8");

			post.setEntity(jsonParams);

			String resContent = null;
			try {
				HttpResponse res = httpClient.execute(post);
				resContent = EntityUtils.toString(res.getEntity());
				if (!resContent.contains("\"Ret\": 0")) {
					LOGGER.error("向" + toUserName + "发送消息失败!");
					continue;
				}
			} catch (ClientProtocolException e) {
				LOGGER.error("", e);
			} catch (IOException e) {
				LOGGER.error("", e);
			}
		}
	}

	@Override
	public void sendMultiMessage(List<String> userNames, String path) {

	}
}
