package com.p2p.webapp.sysmgr.usrmgr.service;

import java.util.List;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.usrmgr.vo.UserInfoVo;


public interface UsrMgrService {
	/*
	 * ��ѯ�û���Ϣ�б�
	 */
	
	public List<UserInfoVo> queryUserInfoList(Page page,UserInfoVo uvo);
	/*
	 * ��ѯ�û�������Ϣ
	 */
	public UserInfoVo queryUserBaseInfo(String userid);
}
