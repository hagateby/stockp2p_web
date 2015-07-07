package com.p2p.webapp.sysmgr.usrmgr.service;

import java.util.List;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.usrmgr.vo.UserInfoVo;


public interface UsrMgrService {
	/*
	 * 查询用户信息列表
	 */
	
	public List<UserInfoVo> queryUserInfoList(Page page,UserInfoVo uvo);
	/*
	 * 查询用户基本信息
	 */
	public UserInfoVo queryUserBaseInfo(String userid);
}
