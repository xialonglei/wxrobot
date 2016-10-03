package com.xll.bean;

import java.util.HashMap;
import java.util.Map;

public class WXData {
	
	private static WXData data = new WXData();
	
	/**存储所有联系人 key:userName value:Contactor*/
	private Map<String , Contactor> contactors = new HashMap<String , Contactor>();
	
	private String uuid;
	
	private String qrUrl;

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
}
