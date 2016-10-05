package com.xll.listener;

import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.xll.bean.Contactor;
import com.xll.bean.WXData;

public class ContactListener extends ResponseListener {

	public ContactListener(WebClient webClient) throws IllegalArgumentException {
		super(webClient);
	}

	@Override
	public WebResponse getResponse(WebRequest request) throws IOException {
		WebResponse response = super.getResponse(request);
		String contactUrl = response.getWebRequest().getUrl().toString();
		if (contactUrl.contains("mmwebwx-bin/webwxgetcontact")) {
			handle(response);
		}
		return response;
	}

	@Override
	public void handle(WebResponse response) {
		String friendList = response.getContentAsString();
		Map<String, Contactor> contactors = WXData.getSingleton().getContactors();
		JSONObject obj = JSON.parseObject(friendList);
		JSONArray memberList = obj.getJSONArray("MemberList");
		for (int i = 0; i < memberList.size(); i++) {
			JSONObject friend = memberList.getJSONObject(i);
			contactors.put(friend.getString("NickName"),
					new Contactor(friend.getString("UserName"), friend.getString("NickName"),
							friend.getString("Signature"), friend.getString("AttrStatus"), friend.getString("Province"),
							friend.getString("City")));

		}
	}

}
