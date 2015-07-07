package com.p2p.webapp.user.index.action;

import java.util.List;

import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.invest.investmgr.vo.InvestVo;
import com.p2p.webapp.sysmgr.issuemgr.vo.InvestProductVo;
import com.p2p.webapp.user.index.service.IndexService;
import com.p2p.webapp.user.index.vo.IndexVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class IndexAction extends BaseAction{

	/**
	 * 首页初始化跳转
	 */
	private static final long serialVersionUID = 1L;

	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(IndexAction.class);
	private IndexVo indexVo;
	private IndexService indexService;
	private List<InvestProductVo> productlist;
	private List<InvestVo>  investlist;
	public String init() {
		logger.debug("自动跳转至首页");
		indexVo = indexService.queryInexInfo();
		return "success";
	}
	
	public String queryNewProduct(){
		
		productlist = indexService.queryNewProduct();
		
		return "queryNewProductOk";
	}
	public String queryNewInvest(){
		
		investlist = indexService.queryNewInvest();
		
		return "queryNewInvestOk";
	}
	
	public IndexVo getIndexVo() {
		return indexVo;
	}
	public void setIndexVo(IndexVo indexVo) {
		this.indexVo = indexVo;
	}
	public IndexService getIndexService() {
		return indexService;
	}
	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}



	public List<InvestProductVo> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<InvestProductVo> productlist) {
		this.productlist = productlist;
	}

	public List<InvestVo> getInvestlist() {
		return investlist;
	}

	public void setInvestlist(List<InvestVo> investlist) {
		this.investlist = investlist;
	}

	
}
