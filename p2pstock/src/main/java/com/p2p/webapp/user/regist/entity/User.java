package com.p2p.webapp.user.regist.entity;

/**
 * �û�������Ϣ��
 * @author Administrator
 *
 */
public class User {
	
	//�û�ID
	private String user_id;
	//�û�����
	private String user_code;
	//�û�״̬
	private String user_status;
	//�û�����
	private String user_type;
	//�û�����
	private String user_name;
	//�û��ֻ���
	private String phone;
	//�û�����
	private String mail;
	//֤������
	private String certif_type;
	//֤������
	private String certif_no;
	//����ʱ��
	private String create_date;
	//����ʱ��
	private String update_date;

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
	
}
