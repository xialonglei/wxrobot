package com.xll.bean;

/** 
 * 存储联系人的信息
 * 
 * @author xialonglei
 * @date 2016/10/03
 */
public class Contactor {
	
	/**用户临时的名字,对应微信当中的UserName,每次登陆都会改变*/
	private String userName; 
	
	/**别名,对应微信当中的NickName*/
	private String nickName;
	
	/**微信签名,对应微信当中的Signature*/
	private String signature;
	
	/**对应微信当中的AttrStatus*/
	private String attrStatus;
	
	/**省份,对应微信当中的Province*/
	private String province;
	
	/**城市,对应微信当中的City*/
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
