package com.p2p.webapp.invest.investnomgr.dao;

import java.util.List;
import java.util.Map;
import com.p2p.webapp.invest.investnomgr.entity.InvestNoInfo;

public interface InvestNoMgrDao {
	
	public List<InvestNoInfo> selectInvestNoInfo(Map<String,Object> paramap);


}
