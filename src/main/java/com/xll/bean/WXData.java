package com.xll.bean;

import java.util.HashMap;
import java.util.Map;

public class WXData {
	
	private static WXData data = new WXData();
	
	/**存储所有联系人 key:userName value:Contactor*/
	private Map<String , Contactor> contactors = new HashMap<String , Contactor>();
	
	private String uuid;
	
	private String qrUrl;
	
	private String loginUsername;
	
	private String passTicket;
		
	/*********************对发送信息所要带的信息进行封装************************/
	private Map<String, Object> baseRequest = new HashMap<String, Object>();

	private Map<String, Object> msg = new HashMap<String, Object>();

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

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	public String getPassTicket() {
		return passTicket;
	}

	public void setPassTicket(String passTicket) {
		this.passTicket = passTicket;
	}
	
}
