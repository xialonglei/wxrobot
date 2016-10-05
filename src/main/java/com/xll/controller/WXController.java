package com.xll.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xll.bean.WXData;
import com.xll.constant.Constants;
import com.xll.service.MessageService;
import com.xll.util.ListenerLauncher;
import com.xll.util.MyWebClient;


/**
 *@author xialonglei
 *@date  2016/10/02 
 */
@Controller
@RequestMapping("/wx")
public class WXController {
	
	@Resource
	private MessageService messageServiceImpl;
	
	@Resource
	private MyWebClient myWebClient;
	
	@RequestMapping("/getImage")
	public String getImage(HttpSession session){
		//Æô¶¯¼àÌýÆ÷
		new ListenerLauncher().init(myWebClient);
		try {
			myWebClient.visitUrl(Constants.LOGIN_URL);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
		}
		session.setAttribute("qrUrl", WXData.getSingleton().getQrUrl());
		return "qr";
	}
	
	@RequestMapping("/sendMessage")
	@ResponseBody
	public String sendMessage(){
		if(WXData.getSingleton() != null && WXData.getSingleton().getContactors() != null && WXData.getSingleton().getContactors().get("One¡¢Life") != null){
			List<String> sendUsernames = new ArrayList<String>();
			sendUsernames.add(WXData.getSingleton().getContactors().get("One¡¢Life").getUserName());
			messageServiceImpl.sendTextMessage(sendUsernames , "??");
			return "send message success.";
		}else{
			return "first , you should login the weixin.";
		}
		
	}
	
}
