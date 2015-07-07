package com.p2p.webapp.invest.investmgr.service;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.invest.investmgr.dto.FeeCountDto;
import com.p2p.webapp.invest.investmgr.vo.InvestMgrVo;
import com.p2p.webapp.invest.investmgr.vo.InvestPayVo;
import com.p2p.webapp.invest.investmgr.vo.InvestVo;
import com.p2p.webapp.invest.investmgr.vo.PlatReceiveVo;

public interface InvestMgrService {
	
	/*
	 * 用户投资
	 */
	public String userInvest(InvestMgrVo imvo);
	/*
	 * 投资审核
	 */
	public String auditInvest(InvestMgrVo investMgrVo);
	/*
	 * 用户投资修改初始化
	 */
	public Map<String,Object> editInit(String user_invest_id);
	
	/*
	 * 用户投资修改
	 */
	public String editInvest(InvestMgrVo imvo);
	/*
	 * 投资费用计算
	 * basic_product_idarray 产品ID(多个以,分割) start_noarray起始号码(多个以,分割)sub_codearray 购买数量(多个以,分割)
	 */
	public Map<String,Object> investCount(FeeCountDto feeCountDto);
	/*
	 * 投资付款
	 */
	public String investPay(InvestPayVo payvo);
	/*
	 * 平台账目变动
	 */
	public String platReceive(PlatReceiveVo platReceiveVo);
	/*
	 * 查询用户投资信息
	 */
	public List<InvestVo> queryInvestInfo(Page page,InvestMgrVo  investMgrVo);
	/*
	 * 查询用户投资详细信息
	 */
	public List<InvestVo> queryInvestMoreInfo(String  user_invest_id);
	
	/*
	 * 查询用户投资信息
	 */
	public List<InvestVo> queryInvestNow(Page page,InvestMgrVo  investMgrVo);
}
