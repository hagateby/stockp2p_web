package com.p2p.webapp.user.usercenter.dto;

public class UserInfoDto {
	//�û�ID
	private String user_id = "";
	//�û�����
	private String user_code = "";
	//�û�״̬
	private String user_status = "";
	//�û�����
	private String user_type = "";
	//�û�����
	private String user_name = "";
	//�û��ֻ���
	private String phone = "";
	//�û�����
	private String mail = "";
	//�û��ǳ�
	private String user_nickname = "";
	//�Ƽ����ֻ�
	private String recod_phone = "";
	//֤������
	private String certif_type = "";
	//֤������
	private String certif_no = "";
	//����ʱ��
	private String create_date = "";
	//����ʱ��
	private String update_date = "";
	//��¼����
	private String login_pwd = "";
	//֧������
	private String tran_pwd = "";
	//�����¼����
	private String err_count = "";
	//��ȫ�ȼ�
	private String secty_level ="";

	//�û����õȼ�
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
