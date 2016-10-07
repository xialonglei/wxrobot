package com.xll.listener;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.xll.bean.WXData;
import com.xll.constant.Constants;
import com.xll.listener.ResponseListener;

public class QRListener extends ResponseListener{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginListener.class);
	
	private WXData data = WXData.getSingleton();
	
	public QRListener(WebClient webClient) throws IllegalArgumentException {
		super(webClient);
	}

	@Override
	public WebResponse getResponse(WebRequest request) throws IOException {
		WebResponse response = super.getResponse(request);
		String url = response.getWebRequest().getUrl().toString();
		if(url.contains("jslogin")){
			
			String resContent = response.getContentAsString().replace(" ", "").split(";")[1];
			String uuid = resContent.substring(resContent.indexOf("=") + 1).replace("\"", "");
			
			LOGGER.info("监听到微信已经扫描的URL:[{}]，拼接的微信二维码URL:[{}]" , response.getWebRequest().getUrl().toString() , Constants.QR_URL_PREFIX + uuid);
			
			data.setUuid(uuid);
			data.setQrUrl(Constants.QR_URL_PREFIX + uuid);
		}
		return response;
	}
}
