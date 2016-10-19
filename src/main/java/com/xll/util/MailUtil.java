package com.xll.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

/**
 * 扫描登录后，发送登录人的信息给我的邮箱
 * 
 * @author xialonglei
 * @date 2016/10/19
 */
@Component
public class MailUtil {

	private String sender = "xialonglei@163.com";
	private String protocol = "mail.transport.protocol";
	private String smtp = "smtp";
	private String server = "smtp.163.com";
	private String senderName = "xialonglei";
	private String senderPwd = "948228357";
	private String contentType = "text/html;charset=utf-8";

	public void sendEmail(MessageContent mContent) {
		// 1. 登陆邮件客户端(创建会话session)
		Properties prop = new Properties();
		Session session = null;
		Message message = null;
		Transport transport = null;
		try {
			prop.setProperty(protocol, smtp);
			// 创建了session会话
			session = Session.getDefaultInstance(prop);
			// 设置debug模式来调试发送信息
			session.setDebug(true);
			// 创建一封邮件对象
			message = new MimeMessage(session);
			// 写信
			message.setSubject(mContent.getSubject());
			// 正文内容
			message.setContent(mContent.getContent(), contentType);
			// 附件人地址
			message.setFrom(new InternetAddress(sender));
			transport = session.getTransport();
			// 链接邮件服务器的认证信息
			transport.connect(server, senderName, senderPwd);
			// 设置收件人地址，并发送邮件
			transport.sendMessage(message, new InternetAddress[] { new InternetAddress(mContent.getReciever()) });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				transport.close();
			} catch (MessagingException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public class MessageContent {

		private String reciever = "xialonglei@163.com";
		private String subject = "登录消息";
		private String content;

		public String getReciever() {
			return reciever;
		}

		public String getSubject() {
			return subject;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}

}
