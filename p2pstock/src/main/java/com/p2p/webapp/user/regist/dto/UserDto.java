package com.p2p.webapp.user.regist.dto;

public class UserDto {
	//�û�ID
	private String user_id = "";
	//�û�����
	private String user_cde = "";
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
	//֤������
	private String certif_type = "";
	//֤������
	private String certif_no = "";
	//����ʱ��
	private String create_date = "";
	//����ʱ��
	private String update_date = "";
	//����
	private String login_pwd = "";
	//�Ƿ�ɹ�0��¼�ɹ� 1 �û��������� 2 ������� 3�û���Ч 4�û�����
	private String log_result = "";
	//�û��ǳ�
	private String user_nickname;
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
	public String getLog_result() {
		return log_result;
	}
	public void setLog_result(String logResult) {
		log_result = logResult;
	}
	public String getUser_cde() {
		return user_cde;
	}
	public void setUser_cde(String userCde) {
		user_cde = userCde;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String userNickname) {
		user_nickname = userNickname;
	}
}
