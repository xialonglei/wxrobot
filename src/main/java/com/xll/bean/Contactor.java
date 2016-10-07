package com.xll.bean;

/** 
 * 联系人类
 * 
 * @author xialonglei
 * @date 2016/10/03
 */
public class Contactor {
	
	/**对应微信UserName*/
	private String userName; 
	
	/**对应微信NickName*/
	private String nickName;
	
	/**对应微信Signature*/
	private String signature;
	
	/**对应微信AttrStatus*/
	private String attrStatus;
	
	/**对应微信Province*/
	private String province;
	
	/**对应微信City*/
	private String city;
	
	public Contactor(String userName, String nickName, String signature, String attrStatus, String province,
			String city) {
		super();
		this.userName = userName;
		this.nickName = nickName;
		this.signature = signature;
		this.attrStatus = attrStatus;
		this.province = province;
		this.city = city;
	}

	public String getUserName() {
		return userName;
	}

	public String getNickName() {
		return nickName;
	}

	public String getSignature() {
		return signature;
	}

	public String getAttrStatus() {
		return attrStatus;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}
}
