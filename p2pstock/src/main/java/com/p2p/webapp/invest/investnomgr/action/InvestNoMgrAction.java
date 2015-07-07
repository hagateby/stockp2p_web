package com.p2p.webapp.invest.investnomgr.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.invest.investnomgr.service.InvestNoMgrService;
import com.p2p.webapp.invest.investnomgr.vo.InvestNoInfoVo;

public class InvestNoMgrAction   extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(InvestNoMgrAction.class);
	
	private InvestNoInfoVo investNoInfoVo;
	private InvestNoMgrService investNoMgrService;
	private List<InvestNoInfoVo> nolist;
	private Page page;
	
	/*
	 * 配号查询初始化
	 */
	public String queryInvestNoInit(){

		return "queryinvestnoinitok";
	}	
	
	/*
	 * 配号查询
	 */
	public String queryInvestNoInfo(){
		//分页
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		if(investNoInfoVo == null){
			investNoInfoVo = new InvestNoInfoVo();
		}
		investNoInfoVo.setUser_type((String)request.getSession().getAttribute(Constant.getSession_usertyp()));
		nolist = investNoMgrService.queryInvestNoInfo(page, investNoInfoVo);
		//查询条件保存session
		paramSave(page);
		return "queryinvestnook";
	}
	
	
	
	public InvestNoInfoVo getInvestNoInfoVo() {
		return investNoInfoVo;
	}
	public void setInvestNoInfoVo(InvestNoInfoVo investNoInfoVo) {
		this.investNoInfoVo = investNoInfoVo;
	}
	public InvestNoMgrService getInvestNoMgrService() {
		return investNoMgrService;
	}
	public void setInvestNoMgrService(InvestNoMgrService investNoMgrService) {
		this.investNoMgrService = investNoMgrService;
	}



	public Page getPage() {
		return page;
	}



	public void setPage(Page page) {
		this.page = page;
	}



	public List<InvestNoInfoVo> getNolist() {
		return nolist;
	}



	public void setNolist(List<InvestNoInfoVo> nolist) {
		this.nolist = nolist;
	}

}
