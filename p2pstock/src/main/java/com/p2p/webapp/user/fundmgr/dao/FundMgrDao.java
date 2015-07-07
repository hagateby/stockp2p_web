package com.p2p.webapp.user.fundmgr.dao;


import java.util.List;
import java.util.Map;

import com.p2p.webapp.user.fundmgr.entity.AccountInfo;
import com.p2p.webapp.user.fundmgr.entity.TranInfo;

public interface FundMgrDao {
	
	/*
	 * 查询用户账户信息
	 */
	public AccountInfo queryUserAccount(String user_id);
	
	/*
	 * 增加个人账户明细
	 */
	public void insertUserAccountDetail(Map<String,Object> paramap);
	
	/*
	 * 修改账户额度
	 */
	public void updateUserAccount(Map<String,Object> paramap);
	
	/*
	 * 查询交易记录
	 */
	public List<TranInfo> queryTranInfo(Map<String,Object> paramap);
	
}
