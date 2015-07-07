package com.p2p.webapp.user.fundmgr.action;

import java.util.ArrayList;
import java.util.List;

import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.system.parammgr.entity.SystemParam;
import com.p2p.webapp.system.parammgr.service.ParamMgrService;
import com.p2p.webapp.system.parammgr.service.impl.ParamMgrServiceImpl;
import com.p2p.webapp.user.fundmgr.service.FundMgrService;
import com.p2p.webapp.user.fundmgr.vo.TranInfoVo;

public class FundMgrAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FundMgrService fundMgrService;
	
	private List<TranInfoVo> traninfolist;
	private TranInfoVo tranInfoVo;
	private List<SystemParam>  investstatuslist;
	private Page page;
	/*
	 * 查询交易记录
	 */
	public String queryTranInfo(){
		//产品状态列表初始化
		ParamMgrService ps= new ParamMgrServiceImpl();
		investstatuslist = new ArrayList<SystemParam>();
		investstatuslist = ps.querySystemByTyp(Constant.getTran_paytyp());
		
		if(tranInfoVo == null){
			tranInfoVo = new TranInfoVo();
		}
		//分页
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		traninfolist = fundMgrService.queryTranInfo(page,tranInfoVo);
		//查询条件保存session
		paramSave(page);
		return "queryTranInfook";
	}

	public FundMgrService getFundMgrService() {
		return fundMgrService;
	}

	public void setFundMgrService(FundMgrService fundMgrService) {
		this.fundMgrService = fundMgrService;
	}

	public List<TranInfoVo> getTraninfolist() {
		return traninfolist;
	}

	public void setTraninfolist(List<TranInfoVo> traninfolist) {
		this.traninfolist = traninfolist;
	}

	public TranInfoVo getTranInfoVo() {
		return tranInfoVo;
	}

	public void setTranInfoVo(TranInfoVo tranInfoVo) {
		this.tranInfoVo = tranInfoVo;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<SystemParam> getInveststatuslist() {
		return investstatuslist;
	}

	public void setInveststatuslist(List<SystemParam> investstatuslist) {
		this.investstatuslist = investstatuslist;
	}

}
