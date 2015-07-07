package com.p2p.webapp.user.usercenter.dto;

public class UserInfoDto {
	//用户ID
	private String user_id = "";
	//用户代码
	private String user_code = "";
	//用户状态
	private String user_status = "";
	//用户类型
	private String user_type = "";
	//用户姓名
	private String user_name = "";
	//用户手机号
	private String phone = "";
	//用户邮箱
	private String mail = "";
	//用户昵称
	private String user_nickname = "";
	//推荐人手机
	private String recod_phone = "";
	//证件类型
	private String certif_type = "";
	//证件号码
	private String certif_no = "";
	//创建时间
	private String create_date = "";
	//更新时间
	private String update_date = "";
	//登录密码
	private String login_pwd = "";
	//支付密码
	private String tran_pwd = "";
	//错误登录次数
	private String err_count = "";
	//安全等级
	private String secty_level ="";

	//用户信用等级
	private String user_credit = "";
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String userCode) {
		user_code = userCode;
	}
	public String getUser_status() {
		return user_status;
	}
	public void setUser_status(String userStatus) {
		user_status = userStatus;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String userType) {
		user_type = userType;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCertif_type() {
		return certif_type;
	}
	public void setCertif_type(String certifType) {
		certif_type = certifType;
	}
	public String getCertif_no() {
		return certif_no;
	}
	public void setCertif_no(String certifNo) {
		certif_no = certifNo;
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
	public String getSecty_level() {
		return secty_level;
	}
	public void setSecty_level(String sectyLevel) {
		secty_level = sectyLevel;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String userNickname) {
		user_nickname = userNickname;
	}
	public String getRecod_phone() {
		return recod_phone;
	}
	public void setRecod_phone(String recodPhone) {
		recod_phone = recodPhone;
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
	public String getErr_count() {
		return err_count;
	}
	public void setErr_count(String errCount) {
		err_count = errCount;
	}
	public String getUser_credit() {
		return user_credit;
	}
	public void setUser_credit(String userCredit) {
		user_credit = userCredit;
	}
	
}
