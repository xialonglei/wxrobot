package com.xll.listener;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.xll.bean.WXData;
import com.xll.constant.Constants;
import com.xll.listener.ResponseListener;
import com.xll.util.MyWebClient;

@Component
public class QRListener extends ResponseListener{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginListener.class);
	
	private WXData data = WXData.getSingleton();
	
	@Autowired(required = true)
	public QRListener(@Qualifier("myWebClient")MyWebClient myWebClient) throws IllegalArgumentException {
		super(myWebClient);
	}

	@Override
	public WebResponse getResponse(WebRequest request) throws IOException {
		WebResponse response = super.getResponse(request);
		String url = response.getWebRequest().getUrl().toString();
		if(url.contains("jslogin")){
			
			String resContent = response.getContentAsString().replace(" ", "").split(";")[1];
			String uuid = resContent.substring(resContent.indexOf("=") + 1).replace("\"", "");
			
			LOGGER.info("监听获取二维码URL:[{}],返回的二维码URL:[{}]" , response.getWebRequest().getUrl().toString() , Constants.QR_URL_PREFIX + uuid);
			
			data.setUuid(uuid);
			data.setQrUrl(Constants.QR_URL_PREFIX + uuid);
		}
		return response;
	}
}
