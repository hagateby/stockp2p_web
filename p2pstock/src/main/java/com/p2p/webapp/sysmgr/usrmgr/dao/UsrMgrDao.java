package com.p2p.webapp.sysmgr.usrmgr.dao;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.user.login.entity.UserInfo;


public interface UsrMgrDao {
	
	/*
	 * 查询客户信息
	 */
	public List<UserInfo> queryUserInfo(Map<String,Object> paramap);
}
