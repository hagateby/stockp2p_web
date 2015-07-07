package com.p2p.webapp.user.fundmgr.dao;


import java.util.List;
import java.util.Map;

import com.p2p.webapp.user.fundmgr.entity.AccountInfo;
import com.p2p.webapp.user.fundmgr.entity.TranInfo;

public interface FundMgrDao {
	
	/*
	 * ��ѯ�û��˻���Ϣ
	 */
	public AccountInfo queryUserAccount(String user_id);
	
	/*
	 * ���Ӹ����˻���ϸ
	 */
	public void insertUserAccountDetail(Map<String,Object> paramap);
	
	/*
	 * �޸��˻����
	 */
	public void updateUserAccount(Map<String,Object> paramap);
	
	/*
	 * ��ѯ���׼�¼
	 */
	public List<TranInfo> queryTranInfo(Map<String,Object> paramap);
	
}
