package com.p2p.webapp.sysmgr.issuemgr.service;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.issuemgr.vo.InvestProductVo;

public interface IssueMgrService {
	
	/*
	 * 查询发行产品信息
	 */
	public List<InvestProductVo> queryIssurInfo(Page page,InvestProductVo ivo);
	/*
	 * 查询可投资产品信息
	 */
	public List<InvestProductVo> queryActiveIssurInfo(Page page);
	/*
	 * 查询历史投资产品信息
	 */
	public List<InvestProductVo> queryHisIssurInfo(Page page);
	/*
	 * 增加投资产品
	 */
	public String addInvestInfo(InvestProductVo invo);
	/*
	 * 查询详情
	 */
	public Map queryMoreinfo(String invest_product_id);
	/*
	 * 删除投资产品
	 */
	public String delInvestProduct(String invest_product_id);
	public String editInvestProduct(InvestProductVo invo);
}
