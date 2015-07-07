package com.p2p.webapp.invest.investnomgr.service;

import java.util.List;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.invest.investnomgr.vo.InvestNoInfoVo;

public interface InvestNoMgrService {
	
	public List<InvestNoInfoVo> queryInvestNoInfo(Page page,InvestNoInfoVo invo);
}
