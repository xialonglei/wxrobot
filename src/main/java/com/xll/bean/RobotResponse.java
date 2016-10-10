package com.xll.bean;

import java.util.ArrayList;
import java.util.List;

public class RobotResponse {
	
	public static List<String> autoSend = new ArrayList<String>();
	
	public static List<String> dirtyWords = new ArrayList<String>();
	
	
	static {
		autoSend.add("您好!");
		
		autoSend.add("天王盖地虎!");
		
		autoSend.add("猜猜我是谁!");
		
		autoSend.add("不要屏蔽我!");
		
		autoSend.add("我是微信机器人!");
		
		autoSend.add("good good study , day day up!");
		
		autoSend.add("Thank you!");
		
		autoSend.add("我是一个逗逼!");
		
		autoSend.add("少点套路 , 多点真诚!");
		
		autoSend.add("这条可以不回复!");
		
		//===============================
		dirtyWords.add("操你妈");
		dirtyWords.add("SB");
		dirtyWords.add("脑子有病");
		dirtyWords.add("妈的");
		dirtyWords.add("傻逼");
		dirtyWords.add("我操");
		dirtyWords.add("狗东西");
		dirtyWords.add("什么东西");
		dirtyWords.add("垃圾");
		dirtyWords.add("坑逼");
		dirtyWords.add("你是什么玩意");
		dirtyWords.add("我是你爹");
	}
}
