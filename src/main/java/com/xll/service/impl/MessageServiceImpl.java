package com.xll.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xll.service.MessageService;


@Service("messageService")
public class MessageServiceImpl implements MessageService{

	@Override
	public void sendTextMessage(List<String> userNames, String message) {
		
	}

	@Override
	public void sendMultiMessage(List<String> userNames, String path) {
		
	}

}
