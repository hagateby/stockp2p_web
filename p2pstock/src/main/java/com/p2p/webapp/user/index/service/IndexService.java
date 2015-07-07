package com.p2p.webapp.user.index.service;

import java.util.List;

import com.p2p.webapp.invest.investmgr.vo.InvestVo;
import com.p2p.webapp.sysmgr.issuemgr.vo.InvestProductVo;
import com.p2p.webapp.user.index.vo.IndexVo;

public interface IndexService {
	public IndexVo queryInexInfo();
	/*
	 * 查询最新产品
	 */
	public List<InvestProductVo> queryNewProduct();
	/*
	 * 查询最新投资
	 */
	public List<InvestVo> queryNewInvest();
}
