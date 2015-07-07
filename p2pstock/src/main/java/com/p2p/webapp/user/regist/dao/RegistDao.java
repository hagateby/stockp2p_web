package com.p2p.webapp.user.regist.dao;

import java.util.Map;

import com.p2p.webapp.user.regist.entity.User;
import com.p2p.webapp.user.regist.entity.UserCtl;

public interface RegistDao {
	
	/*
	 * �����ֻ��Ų�ѯ�û�
	 */
	public User queryUserByPhone(String phoneno);
	
	public void insertUser(User user);
	public void insertUserCtl(UserCtl userctl);
	public String queryUserId();
	/*
	 * �����û��˻�
	 */
	public void insertUserAccount(Map paramap);
	
	
	public void insertUserPrvg(Map<String,Object> paramap);
}
