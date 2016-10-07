package com.xll.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
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
			
			Map<String , Object> msg = new HashMap<String , Object>();
			
			msg.put("Type", 1);
			msg.put("Content", message);
			msg.put("ToUserName", toUserName);
			msg.put("LocalID", sendTime);
			msg.put("ClientMsgId", sendTime);
			msg.put("FromUserName", WXData.getSingleton().getLoginUsername());

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
			obj.put("Msg", msg);
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
		
		System.setProperty("jsse.enableSNIExtension", "false");
		
		File f = new File(path);
		String fileName = f.getName();
		long length = f.length();
		String type = new MimetypesFileTypeMap().getContentType(f);
		String mediaType = "pic";
		String lastModifiedDate = "Tue Jul 14 2009 13:32:31 GMT+0800 (中国标准时间)";
		LOGGER.info("发送文件名[{}],文件大小[{}],文件类型[{}]", fileName, length, type);

		HttpClient httpClient = HttpsUtil.newHttpsClient();

		for (String toUserName : userNames) {
			HttpPost post = new HttpPost(Constants.GET_MEDIA_ID_URL);

			post.setHeader(Constants.ACCEPT_NAME, "*/*");
			post.setHeader("Accept-Encoding", "gzip, deflate, br");
			post.setHeader(Constants.ACCEPT_LANGUAGE_NAME, Constants.ACCEPT_LANGUAGE_VALUE);
			post.setHeader(Constants.USER_AGENT_NAME, Constants.USER_AGENT_VALUE);
			post.setHeader(HTTP.CONTENT_TYPE, "multipart/form-data");
			post.setHeader("Host", "file.wx.qq.com");
			post.setHeader("Origin", "https://wx.qq.com");
			post.setHeader("Referer", "https://wx.qq.com/?&lang=zh_CN");

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addTextBody("id", "WU_FILE_0");
			builder.addTextBody("name", fileName);
			builder.addTextBody("type", type);
			builder.addTextBody("lastModifiedDate", lastModifiedDate);
			builder.addTextBody("size", length + "");
			builder.addTextBody("mediatype", mediaType);

			JSONObject obj = new JSONObject();
			obj.put("UploadType", 2);
			obj.put("BaseRequest", WXData.getSingleton().getBaseRequest());
			obj.put("ClientMediaId", new Date().getTime());
			obj.put("TotalLen", length);
			obj.put("StartPos", 0);
			obj.put("DataLen", length);
			obj.put("MediaType", 4);
			obj.put("FromUserName", WXData.getSingleton().getLoginUsername());
			obj.put("ToUserName", toUserName);

			builder.addTextBody("uploadmediarequest", obj.toJSONString());
			builder.addTextBody("webwx_data_ticket", WXData.getSingleton().getWebDataTicket());
			builder.addTextBody("pass_ticket" , WXData.getSingleton().getPassTicket());
			builder.addBinaryBody("filename", f, ContentType.APPLICATION_OCTET_STREAM, fileName);
			
			post.setEntity(builder.build());
			String resContent = null;
			HttpResponse res = null;
			String mediaId = null;
			try {
				res = httpClient.execute(post);
				resContent = EntityUtils.toString(res.getEntity());
				JSONObject resJson = JSON.parseObject(resContent);
				mediaId = resJson.getString("MediaId");
				LOGGER.info("向[{}]获取的MediaId[{}]", toUserName, mediaId);
			} catch (IOException e) {
				LOGGER.error("", e);
			}
			doSendMultiMessage(toUserName, mediaId , httpClient);
		}

	}

	/** 
	 * 根据获取到的mediaId真正执行文件发送请求
	 * @param toUserName 要发送的联系人
	 * @param mediaId
	 * @return void
	 */
	private void doSendMultiMessage(String toUserName, String mediaId , HttpClient httpClient) {

		StringEntity jsonParams = null;

		String sendTime = new Date().getTime() + "0000";
		
		Map<String , Object> msg = new HashMap<String , Object>();
		
		msg.put("Type", 3);
		msg.put("MediaId", mediaId);
		msg.put("ToUserName", toUserName);
		msg.put("LocalID", sendTime);
		msg.put("ClientMsgId", sendTime);
		msg.put("FromUserName", WXData.getSingleton().getLoginUsername());

		HttpPost post = new HttpPost(
				Constants.SEND_MULTI_MESSAGE_PREFIX + WXData.getSingleton().getPassTicket());

		post.setHeader(Constants.ACCEPT_NAME, "application/json, text/plain, */*");
		post.setHeader(Constants.ACCEPT_LANGUAGE_NAME, Constants.ACCEPT_LANGUAGE_VALUE);
		post.setHeader(Constants.USER_AGENT_NAME, Constants.USER_AGENT_VALUE);
		post.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
		post.setHeader("Host", "wx.qq.com");
		post.setHeader("Origin", "https://wx.qq.com");
		post.setHeader("Referer", "https://wx.qq.com/?&lang=zh_CN");

		JSONObject obj = new JSONObject();
		obj.put("BaseRequest", WXData.getSingleton().getBaseRequest());
		obj.put("Msg", msg);
		obj.put("Scene", 0);

		jsonParams = new StringEntity(obj.toJSONString(), "utf-8");

		post.setEntity(jsonParams);

		String resContent = null;
		try {
			HttpResponse res = httpClient.execute(post);
			resContent = EntityUtils.toString(res.getEntity());
			if (!resContent.contains("\"Ret\": 0")) {
				LOGGER.error("向" + toUserName + "发送消息失败!");
			}
		} catch (ClientProtocolException e) {
			LOGGER.error("", e);
		} catch (IOException e) {
			LOGGER.error("", e);
		}	
	}
}
