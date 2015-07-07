package com.p2p.webapp.user.regist.dao;

import java.util.Map;

import com.p2p.webapp.user.regist.entity.User;
import com.p2p.webapp.user.regist.entity.UserCtl;

public interface RegistDao {
	
	/*
	 * 根据手机号查询用户
	 */
	public User queryUserByPhone(String phoneno);
	
	public void insertUser(User user);
	public void insertUserCtl(UserCtl userctl);
	public String queryUserId();
	/*
	 * 新增用户账户
	 */
	public void insertUserAccount(Map paramap);
	
	
	public void insertUserPrvg(Map<String,Object> paramap);
}
