package com.xll.data;

public class WXData {
	
	private static WXData data = new WXData();
	
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
}
