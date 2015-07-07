package com.p2p.webapp.user.fundmgr.service;

import java.util.List;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.user.fundmgr.vo.AccountInfoVo;
import com.p2p.webapp.user.fundmgr.vo.TranInfoVo;

public interface FundMgrService {
	public AccountInfoVo queryAccountInfo(String user_id);
	/*
	 * ��ѯ���׼�¼
	 */
	public List<TranInfoVo> queryTranInfo(Page page,TranInfoVo tvo);
}
