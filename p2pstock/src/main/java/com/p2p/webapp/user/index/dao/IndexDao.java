package com.p2p.webapp.user.index.dao;

import java.util.List;

import com.p2p.webapp.invest.investmgr.entity.Invest;
import com.p2p.webapp.sysmgr.issuemgr.entity.InvestProduct;
import com.p2p.webapp.user.index.entity.IndexCount;

public interface IndexDao {
	
	public IndexCount queryUserAllProfit();
	
	public String queryUserAllTrans();
	
	public List<InvestProduct> queryNewProduct();
	
	public List<Invest> queryNewInvest();
}
