package com.p2p.webapp.user.login.entity;

/**
 * 用户信息表
 * @author cssong
 *
 */
public class UserInfo {
	//用户ID
	private String user_id = "";
	//用户代码
	private String user_cde = "";
	//用户状态
	private String user_status = "";
	//状态名称
	private String user_statusname = "";
	//用户类型
	private String user_type = "";
	//用户类型名称
	private String user_typename = "";
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
	//新登录密码
	private String newlogin_pwd = "";
	//支付密码
	private String tran_pwd = "";
	//新支付密码
	private String newtran_pwd = "";
	//错误登录次数
	private String err_count = "";
	//安全等级
	private String secty_level = "";
	//用户信用等级
	private String user_credit = "";
	//用户安全等级显示名
	private String secty_levelname = "";
	//账户余额
	private String acc_balance;
	private String acc_enchash;
	private String acc_freeze;
	private String acc_status;
	private String acc_statusname;
	private String bank_acc_code;
	private String bank_name;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
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
	public String getUser_credit() {
		return user_credit;
	}
	public void setUser_credit(String userCredit) {
		user_credit = userCredit;
	}
	public String getUser_cde() {
		return user_cde;
	}
	public void setUser_cde(String userCde) {
		user_cde = userCde;
	}
	public String getUser_statusname() {
		return user_statusname;
	}
	public void setUser_statusname(String userStatusname) {
		user_statusname = userStatusname;
	}
	public String getUser_typename() {
		return user_typename;
	}
	public void setUser_typename(String userTypename) {
		user_typename = userTypename;
	}
	public String getNewlogin_pwd() {
		return newlogin_pwd;
	}
	public void setNewlogin_pwd(String newloginPwd) {
		newlogin_pwd = newloginPwd;
	}
	public String getNewtran_pwd() {
		return newtran_pwd;
	}
	public void setNewtran_pwd(String newtranPwd) {
		newtran_pwd = newtranPwd;
	}
	public String getSecty_levelname() {
		return secty_levelname;
	}
	public void setSecty_levelname(String sectyLevelname) {
		secty_levelname = sectyLevelname;
	}
	public String getAcc_balance() {
		return acc_balance;
	}
	public void setAcc_balance(String accBalance) {
		acc_balance = accBalance;
	}
	public String getAcc_enchash() {
		return acc_enchash;
	}
	public void setAcc_enchash(String accEnchash) {
		acc_enchash = accEnchash;
	}
	public String getAcc_freeze() {
		return acc_freeze;
	}
	public void setAcc_freeze(String accFreeze) {
		acc_freeze = accFreeze;
	}
	public String getAcc_status() {
		return acc_status;
	}
	public void setAcc_status(String accStatus) {
		acc_status = accStatus;
	}
	public String getAcc_statusname() {
		return acc_statusname;
	}
	public void setAcc_statusname(String accStatusname) {
		acc_statusname = accStatusname;
	}
	public String getBank_acc_code() {
		return bank_acc_code;
	}
	public void setBank_acc_code(String bankAccCode) {
		bank_acc_code = bankAccCode;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bankName) {
		bank_name = bankName;
	}
	
}
