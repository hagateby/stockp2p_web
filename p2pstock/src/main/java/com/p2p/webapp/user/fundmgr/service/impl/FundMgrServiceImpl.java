package com.p2p.webapp.user.fundmgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.invest.investmgr.dao.InvestMgrDao;
import com.p2p.webapp.user.fundmgr.dao.FundMgrDao;
import com.p2p.webapp.user.fundmgr.entity.AccountInfo;
import com.p2p.webapp.user.fundmgr.entity.TranInfo;
import com.p2p.webapp.user.fundmgr.service.FundMgrService;
import com.p2p.webapp.user.fundmgr.vo.AccountInfoVo;
import com.p2p.webapp.user.fundmgr.vo.TranInfoVo;

public class FundMgrServiceImpl implements FundMgrService{
	
	private FundMgrDao fundMgrDao;
	private InvestMgrDao investMgrDao;
	public AccountInfoVo queryAccountInfo(String user_id){
		AccountInfoVo avo = new AccountInfoVo();
		AccountInfo   aio = new AccountInfo();
		aio = fundMgrDao.queryUserAccount(user_id);
		avo.setAcc_balance(AppUtil.numFormat(aio.getAcc_balance(),2));
		avo.setAcc_enchash(AppUtil.numFormat(aio.getAcc_enchash(),2));
		avo.setAcc_freeze(AppUtil.numFormat(aio.getAcc_freeze(),2));
		avo.setAcc_status(aio.getAcc_status());
		avo.setAcc_statusname(aio.getAcc_statusname());
		avo.setAcc_type(aio.getAcc_type());
		avo.setAcc_typename(aio.getAcc_typename());
		avo.setAccout_no(aio.getAccout_no());
		avo.setCreate_date(aio.getCreate_date());
		avo.setUpdate_date(aio.getUpdate_date());
		avo.setUser_id(aio.getUser_id());
		//用户总收益
		String user_invest_profit = investMgrDao.selectUserInvestProfit(user_id);
		//用户总投资额
		String product_buycount_all = investMgrDao.selectUserInvestMoney(user_id);
		avo.setUser_profit(AppUtil.numFormat(user_invest_profit, 2));
		//投资收益率
		String rate;
		if(product_buycount_all == null || "".equals(product_buycount_all) || user_invest_profit ==null || "".equals(user_invest_profit)){
			rate = "0.00";
		}else{
			rate = String.valueOf((Double.parseDouble(AppUtil.nvlNum(user_invest_profit))/Double.parseDouble(AppUtil.nvlNum(product_buycount_all)))*100.00d);
		}
		
		avo.setUser_profitrate(AppUtil.numFormat(rate, 2));
		return avo;
	}
	
	/*
	 * 查询交易记录
	 */
	public List<TranInfoVo> queryTranInfo(Page page,TranInfoVo tvo){
		List<TranInfoVo> rlist = new ArrayList<TranInfoVo>();
		Map<String,Object> paramap = new HashMap<String,Object>();
		if("1".equals(page.getPageflag())){
			paramap = page.getParamMap();
			paramap.put("page", page);
		}else{
			paramap.put("page", page);
			paramap.put("invt_product_name", AppUtil.nvlStr(tvo.getInvt_product_name()));
			paramap.put("invt_product_namelike", "%"+ AppUtil.nvlStr(tvo.getInvt_product_name()) +"%");
			paramap.put("user_id", AppUtil.nvlStr(tvo.getUser_id()));
			paramap.put("start_date", AppUtil.nvlStr(tvo.getStart_date()));
			paramap.put("end_date", AppUtil.nvlStr(tvo.getEnd_date()));
			paramap.put("payment_type", AppUtil.nvlStr(tvo.getPayment_type()));
			paramap.put("user_name", AppUtil.nvlStr(tvo.getUser_name()));
			page.setParamMap(paramap);
		}
		List<TranInfo> datalist = fundMgrDao.queryTranInfo(paramap);
		for(TranInfo info:datalist){
			TranInfoVo rvo = new TranInfoVo();
			rvo.setAmount(AppUtil.fmtAcount(info.getAmount()));
			rvo.setPayment_type(info.getPayment_type());
			rvo.setPayment_typename(info.getPayment_typename());
			rvo.setTran_id(info.getTran_id());
			rvo.setTransion_seq(info.getTransion_seq());
			rvo.setTransion_type(info.getTransion_type());
			rvo.setTransion_typename(info.getTransion_typename());
			rvo.setUpdate_date(info.getUpdate_date());
			rvo.setUser_id(info.getUser_id());
			rvo.setUser_name(info.getUser_name());
			rvo.setInvt_product_name(info.getInvt_product_name());
			rlist.add(rvo);
		}
		return rlist;
	}
	

	public FundMgrDao getFundMgrDao() {
		return fundMgrDao;
	}

	public void setFundMgrDao(FundMgrDao fundMgrDao) {
		this.fundMgrDao = fundMgrDao;
	}

	public InvestMgrDao getInvestMgrDao() {
		return investMgrDao;
	}

	public void setInvestMgrDao(InvestMgrDao investMgrDao) {
		this.investMgrDao = investMgrDao;
	}

}
