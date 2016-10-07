package com.xll.listener;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.xll.bean.WXData;
import com.xll.constant.Constants;
import com.xll.listener.ResponseListener;

public class LoginListener extends ResponseListener{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginListener.class);

	public LoginListener(WebClient webClient) throws IllegalArgumentException {
		super(webClient);
	}
	
	@Override
	public WebResponse getResponse(WebRequest request) throws java.io.IOException {
		WebResponse response = super.getResponse(request);
		handle(response);
		return response;
	}
	
	@Override
	public void handle(WebResponse response) {
		String resContent = null;
		String detectUrl = response.getWebRequest().getUrl().toString();
		
		if(detectUrl.contains("mmwebwx-bin/webwxnewloginpage")){
			
			LOGGER.info("��������¼��URL��[{}]" , response.getWebRequest().getUrl().toString());
			
			resContent = Constants.XML_HEADER + response.getContentAsString();
			
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(resContent);
			} catch (DocumentException e) {
				LOGGER.error("" , e);
			}
			
			Element root = doc.getRootElement();
			
			WXData.getSingleton().setPassTicket(root.elementText("pass_ticket"));
			
			Map<String , Object> baseRequest = WXData.getSingleton().getBaseRequest();
			baseRequest.put("Uin", Long.parseLong(root.elementText("wxuin")));
			baseRequest.put("Sid", root.elementText("wxsid"));
			baseRequest.put("Skey", root.elementText("skey"));
			baseRequest.put("DeviceID", "e872491039942961");
			
		}else if(WXData.getSingleton().getLoginUsername() == null && detectUrl.contains("mmwebwx-bin/webwxinit")){
			
			resContent = response.getContentAsString();
			JSONObject obj = JSON.parseObject(resContent);
			String loginUsername = obj.getJSONObject("User").getString("UserName");
			
			LOGGER.info("��������ȡ��¼�û�����URL��[{}]�����ҵ�¼�û���:[{}]" , response.getWebRequest().getUrl().toString() , loginUsername);
			
			WXData.getSingleton().setLoginUsername(loginUsername);
			WXData.getSingleton().getMsg().put("FromUserName", loginUsername);
		}
	}
}
