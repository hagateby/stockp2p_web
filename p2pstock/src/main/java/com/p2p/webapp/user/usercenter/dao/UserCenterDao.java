package com.p2p.webapp.user.usercenter.dao;

import java.util.List;
import java.util.Map;
import com.p2p.webapp.user.login.entity.UserInfo;
import com.p2p.webapp.user.usercenter.entity.UserBank;

public interface UserCenterDao {
	
	/*
	 * ��ѯ�ͻ�������Ϣ
	 */
	public UserInfo queryUserById(String userid);
	/*
	 * ���¿ͻ�������Ϣ
	 */
	public void updateUserById(Map<String,String> paramMap);
	/*
	 * ��ѯ�ͻ����п���Ϣ
	 */
	public List<UserBank> queryBankInfo(String userid);
	/*
	 * ����ͻ�������Ϣ
	 */
	public void insertBankInfo(Map<String,Object> map);
	
	/*
	 * ���¿ͻ�������Ϣ
	 */
	public void updateBankInfo(Map<String,Object> map);
	/*
	 * ɾ���ͻ�������Ϣ
	 */
	public void delBankInfo(String user_bank_acc_id);
	/*
	 * ���¿ͻ���¼����
	 */
	public void updateLoginPwd(Map<String,Object> paramap);
	/*
	 * ��ѯ�û���¼���������Ƿ���ȷ
	 */
	public String queryUserByIdPd(Map<String,Object> paramap);
	/*
	 * ���¿ͻ���������
	 */
	public void updateTranPwd(Map<String,Object> paramap);
	/*
	 * �����û�Ĭ�����п�Ϊ�� 
	 */
	public void updateUserBankDefaultNo(Map<String,Object> paramap);
	/*
	 * �����û�Ĭ�����п�
	 */
	public void updateUserBankDefault(Map<String,Object> paramap);
}
