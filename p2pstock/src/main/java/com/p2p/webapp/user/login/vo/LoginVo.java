package com.p2p.webapp.user.login.vo;

public class LoginVo {
	
	//��¼��ʽ0�ֻ��� 1�û���
	private String loginflg = "";
	//�û���
	private String userno = "";
	//����
	private String passwd = "";
	//��֤��
	private String cusCpt;
	
	public String getLoginflg() {
		return loginflg;
	}
	public void setLoginflg(String loginflg) {
		this.loginflg = loginflg;
	}
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getCusCpt() {
		return cusCpt;
	}
	public void setCusCpt(String cusCpt) {
		this.cusCpt = cusCpt;
	}

	
}
