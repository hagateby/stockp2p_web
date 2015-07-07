package com.p2p.webapp.user.index.service;

import java.util.List;

import com.p2p.webapp.invest.investmgr.vo.InvestVo;
import com.p2p.webapp.sysmgr.issuemgr.vo.InvestProductVo;
import com.p2p.webapp.user.index.vo.IndexVo;

public interface IndexService {
	public IndexVo queryInexInfo();
	/*
	 * ��ѯ���²�Ʒ
	 */
	public List<InvestProductVo> queryNewProduct();
	/*
	 * ��ѯ����Ͷ��
	 */
	public List<InvestVo> queryNewInvest();
}
