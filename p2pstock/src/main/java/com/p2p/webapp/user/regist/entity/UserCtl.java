package com.p2p.webapp.user.regist.entity;

/**
 * �û���ȫ���Ʊ�
 * @author Administrator
 *
 */
public class UserCtl {
	
	//ID
	private String city_id;
	//�û�ID
	private String user_id;
	//��¼����
	private String login_pwd;
	//֧������
	private String tran_pwd;
	//�û�״̬
	private String user_status;
	//��¼ʧ�ܴ���
	private String err_count;
	//��ȫ����
	private String secty_level;
	//����ʱ��
	private String create_date;
	//������ʱ��
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
