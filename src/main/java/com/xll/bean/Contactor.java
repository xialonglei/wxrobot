package com.xll.bean;

/** 
 * �洢��ϵ�˵���Ϣ
 * 
 * @author xialonglei
 * @date 2016/10/03
 */
public class Contactor {
	
	/**�û���ʱ������,��Ӧ΢�ŵ��е�UserName,ÿ�ε�½����ı�*/
	private String userName; 
	
	/**����,��Ӧ΢�ŵ��е�NickName*/
	private String nickName;
	
	/**΢��ǩ��,��Ӧ΢�ŵ��е�Signature*/
	private String signature;
	
	/**��Ӧ΢�ŵ��е�AttrStatus*/
	private String attrStatus;
	
	/**ʡ��,��Ӧ΢�ŵ��е�Province*/
	private String province;
	
	/**����,��Ӧ΢�ŵ��е�City*/
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
