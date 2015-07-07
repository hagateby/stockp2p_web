package com.p2p.webapp.user.regist.entity;

/**
 * 用户安全控制表
 * @author Administrator
 *
 */
public class UserCtl {
	
	//ID
	private String city_id;
	//用户ID
	private String user_id;
	//登录密码
	private String login_pwd;
	//支付密码
	private String tran_pwd;
	//用户状态
	private String user_status;
	//登录失败次数
	private String err_count;
	//安全级别
	private String secty_level;
	//创建时间
	private String create_date;
	//最后更新时间
	private String update_date;
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String cityId) {
		city_id = cityId;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getLogin_pwd() {
		return login_pwd;
	}
	public void setLogin_pwd(String loginPwd) {
		login_pwd = loginPwd;
	}
	public String getTran_pwd() {
		return tran_pwd;
	}
	public void setTran_pwd(String tranPwd) {
		tran_pwd = tranPwd;
	}
	public String getUser_status() {
		return user_status;
	}
	public void setUser_status(String userStatus) {
		user_status = userStatus;
	}
	public String getErr_count() {
		return err_count;
	}
	public void setErr_count(String errCount) {
		err_count = errCount;
	}
	public String getSecty_level() {
		return secty_level;
	}
	public void setSecty_level(String sectyLevel) {
		secty_level = sectyLevel;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String createDate) {
		create_date = createDate;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String updateDate) {
		update_date = updateDate;
	}
}
