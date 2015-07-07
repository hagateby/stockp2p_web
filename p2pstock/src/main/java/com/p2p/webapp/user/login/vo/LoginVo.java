package com.p2p.webapp.user.login.vo;

public class LoginVo {
	
	//登录方式0手机号 1用户名
	private String loginflg = "";
	//用户名
	private String userno = "";
	//密码
	private String passwd = "";
	//验证码
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
