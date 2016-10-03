package com.xll.listener.factory;

import com.xll.listener.ContactListener;
import com.xll.listener.LoginListener;
import com.xll.listener.QRListener;
import com.xll.listener.ResponseListener;
import com.xll.util.MyWebClient;

/**
 * 简单工厂，用来返回ResponseListener实例
 *
 * @author xialonglei
 * @date 2016/10/03
 */
public class ListenerFactory {

	public static ResponseListener getListenerInstance(String type, MyWebClient myWebClient) {

		switch (type) {
			case "login":
				return new LoginListener(myWebClient);
			case "contact":
				return new ContactListener(myWebClient);
			case "qr":
				return new QRListener(myWebClient);
		}

		return null;
	}

}
