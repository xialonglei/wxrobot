package com.xll.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xll.constant.Constants;
import com.xll.data.WXData;
import com.xll.util.ListenerLauncher;
import com.xll.util.MyWebClient;


@Controller
@RequestMapping("/wx")
public class WxController {
	
	@Resource
	private MyWebClient myWebClient;
	
	@RequestMapping("/getImage")
	public String getImage(HttpSession session) throws IOException{
		//Æô¶¯¼àÌýÆ÷
		new ListenerLauncher().init(myWebClient);
		myWebClient.visitUrl(Constants.LOGIN_URL);
		session.setAttribute("qrUrl", WXData.getSingleton().getQrUrl());
		return "qr";
	}
	
}
