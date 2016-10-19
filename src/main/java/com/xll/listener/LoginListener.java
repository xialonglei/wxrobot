package com.xll.listener;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.xll.bean.WXData;
import com.xll.constant.Constants;
import com.xll.listener.ResponseListener;
import com.xll.util.MailUtil;
import com.xll.util.MailUtil.MessageContent;
import com.xll.util.MyWebClient;

@Component
public class LoginListener extends ResponseListener{
	
	@Resource
	private MailUtil mailUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginListener.class);

	@Autowired(required = true)
	public LoginListener(@Qualifier("myWebClient")MyWebClient myWebClient) throws IllegalArgumentException {
		super(myWebClient);
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
			
			LOGGER.info("监听到已扫描微信URL[{}]" , response.getWebRequest().getUrl().toString());
			
			List<NameValuePair> headers = response.getResponseHeaders();
			for(NameValuePair pair : headers){
				if(pair.getValue().contains("webwx_data_ticket")){
					String webDataTicket = pair.getValue().split(";")[0].split("=")[1];
					LOGGER.info("获取的web_data_ticket[{}]" , webDataTicket);
					WXData.getSingleton().setWebDataTicket(webDataTicket);
				}
			}
			
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
			new Thread(new Runnable(){

				@Override
				public void run() {
					MessageContent messageContent = mailUtil.new MessageContent();
					messageContent.setContent("<h3>[ " + obj.getJSONObject("User").getString("NickName") + " ]  登录了您微信机器人服务器！</h3>");
					mailUtil.sendEmail(messageContent);
					
				}
				
			}).start();
			
			LOGGER.info("监听获取登录用户URL[{}],登录用户名[{}]" , response.getWebRequest().getUrl().toString() , loginUsername);
			
			WXData.getSingleton().setLoginUsername(loginUsername);
		}
	}
}
