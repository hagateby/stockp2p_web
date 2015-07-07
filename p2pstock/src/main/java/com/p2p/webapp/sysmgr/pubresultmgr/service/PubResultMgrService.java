package com.p2p.webapp.sysmgr.pubresultmgr.service;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.InvtProductSettleDetailVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.PubResultVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.SettleHisVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.SettleVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.UserSettleVo;

public interface PubResultMgrService {
	public String pubResultNo(PubResultVo pubResultVo);
	public Map<String,Object> settleProduct(SettleVo settleVo);
	/*
	 * 结算历史信息查询
	 */
	public List<InvtProductSettleDetailVo> querySettleHistory(Page page,SettleHisVo settleHisVo);
	/*
	 * 检查产品投资状态
	 */
	public String checkInvestAjax(String invest_prt_id);
	/*
	 * 查询详情
	 */
	public Map<String,Object> querySettleMoreInfo(String invt_product_id);
	/*
	 * 利润分配
	 */
	public String settlePay(String ustl_id);
	/*
	 * 应收应付查询
	 */
	public List<UserSettleVo> querySettlePay(Page page,SettleVo settleVo);
}
