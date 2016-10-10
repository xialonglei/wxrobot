package com.xll.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WXData {
	
	private static WXData data = new WXData();
	
	private static Set<String> recieveFriends = new HashSet<String>();
	
	/**存放联系人 key:userName value:Contactor*/
	private Map<String , Contactor> contactors = new HashMap<String , Contactor>();
	
	private String uuid;
	
	private String qrUrl;
	
	private String loginUsername;
	
	private String passTicket;
	
	private String webDataTicket;
	/*********************发送消息需要的信息************************/
	private Map<String, Object> baseRequest = new HashMap<String, Object>();

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getQrUrl() {
		return qrUrl;
	}

	public void setQrUrl(String qrUrl) {
		this.qrUrl = qrUrl;
	}
	
	public static WXData getSingleton(){
		return data;
	}

	public Map<String, Contactor> getContactors() {
		return contactors;
	}

	public void setContactors(Map<String, Contactor> contactors) {
		this.contactors = contactors;
	}
	
	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public Map<String, Object> getBaseRequest() {
		return baseRequest;
	}

	public void setBaseRequest(Map<String, Object> baseRequest) {
		this.baseRequest = baseRequest;
	}

	public String getPassTicket() {
		return passTicket;
	}

	public void setPassTicket(String passTicket) {
		this.passTicket = passTicket;
	}

	public String getWebDataTicket() {
		return webDataTicket;
	}

	public void setWebDataTicket(String webDataTicket) {
		this.webDataTicket = webDataTicket;
	}

	public static Set<String> getRecieveFriends() {
		return recieveFriends;
	}

	public static void setRecieveFriends(Set<String> recieveFriends) {
		WXData.recieveFriends = recieveFriends;
	}
	
	
}
