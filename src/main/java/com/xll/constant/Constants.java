package com.xll.constant;

/**
 * 常量类
 * 
 * @author xialonglei
 * @date 2016/10/02
 */
public class Constants {
	
	public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	public static final String LOGIN_URL = "https://wx.qq.com/";
	public static final String QR_URL_PREFIX = "https://login.weixin.qq.com/qrcode/";
	public static final String SEND_MSG_PREFIX = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxsendmsg";
	public static final String GET_MEDIA_ID_URL = "https://file.wx.qq.com/cgi-bin/mmwebwx-bin/webwxuploadmedia?f=json";
	public static final String SEND_MULTI_MESSAGE_PREFIX = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxsendmsgimg?fun=async&f=json&lang=zh_CN&pass_ticket=";
	
	/************************************ http header *************************************/
	public static final String USER_AGENT_NAME = "User-Agent";
	public static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";
	public static final String ACCEPT_NAME = "Accept";
	public static final String ACCEPT_VALUE = "*/*";
	public static final String ACCEPT_LANGUAGE_NAME = "Accept-Language";
	public static final String ACCEPT_LANGUAGE_VALUE = "zh-CN,zh;q=0.8";
	
	/************************************send message status******************************/
	public static final String SEND_MSG_FAILURE = "send message fail.";
	public static final String SEND_MSG_SUCCESS = "send message success.";
	public static final String LOGIN_HINT = "first , you should login the weixin.";

}
