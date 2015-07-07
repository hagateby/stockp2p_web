package com.p2p.webapp.invest.investnomgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.invest.investnomgr.dao.InvestNoMgrDao;
import com.p2p.webapp.invest.investnomgr.entity.InvestNoInfo;
import com.p2p.webapp.invest.investnomgr.service.InvestNoMgrService;
import com.p2p.webapp.invest.investnomgr.vo.InvestNoInfoVo;

public class InvestNoMgrServiceImpl implements InvestNoMgrService{
	private InvestNoMgrDao investNoMgrDao;
	
	/*
	 * 产品配号信息查询
	 */
	public List<InvestNoInfoVo> queryInvestNoInfo(Page page,InvestNoInfoVo invo){
		List<InvestNoInfoVo> rlist = new ArrayList<InvestNoInfoVo>();
		Map<String,Object> parammap = new HashMap<String,Object>();
		
		if("1".equals(page.getPageflag())){
			parammap = page.getParamMap();
			parammap.put("page", page);
		}else{
			parammap.put("page", page);
			parammap.put("invt_product_name", AppUtil.nvlStr(invo.getInvt_product_name()));
			parammap.put("invt_product_namelike", "%"+ AppUtil.nvlStr(invo.getInvt_product_name()) +"%");
			parammap.put("invest_product_id", invo.getInvest_product_id());
			parammap.put("invt_product_id", invo.getInvt_product_id());
			page.setParamMap(parammap);
		}
		List<InvestNoInfo> inflist = investNoMgrDao.selectInvestNoInfo(parammap);
		for(InvestNoInfo info:inflist){
			InvestNoInfoVo ivo = new InvestNoInfoVo();
			ivo.setBasic_product_name(info.getBasic_product_name());
			ivo.setInvest_product_id(info.getInvest_product_id());
			if(!Constant.getUser_typ_manager().equals(invo.getUser_type())){
				ivo.setInvest_user_phone(AppUtil.fmtStr(info.getInvest_user_phone(), "0", 2));
			}else{
				ivo.setInvest_user_phone(info.getInvest_user_phone());
			}
			ivo.setInvt_product_name(info.getInvt_product_name());
			ivo.setPraise_count(info.getPraise_count());
			ivo.setProduct_buycount_all(AppUtil.numFormat(info.getProduct_buycount_all(), 2));
			ivo.setStart_no(info.getStart_no());
			ivo.setSub_code(info.getSub_code());
			ivo.setUser_settle_profit(AppUtil.numFormat(info.getUser_settle_profit(), 2));
			ivo.setResult_no(info.getResult_no());
			ivo.setInvt_product_id(info.getInvt_product_id());
			rlist.add(ivo);
		}
		return rlist;
	}

	public InvestNoMgrDao getInvestNoMgrDao() {
		return investNoMgrDao;
	}

	public void setInvestNoMgrDao(InvestNoMgrDao investNoMgrDao) {
		this.investNoMgrDao = investNoMgrDao;
	}
}
