package com.p2p.webapp.sysmgr.usrmgr.dao;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.user.login.entity.UserInfo;


public interface UsrMgrDao {
	
	/*
	 * ��ѯ�ͻ���Ϣ
	 */
	public List<UserInfo> queryUserInfo(Map<String,Object> paramap);
}
