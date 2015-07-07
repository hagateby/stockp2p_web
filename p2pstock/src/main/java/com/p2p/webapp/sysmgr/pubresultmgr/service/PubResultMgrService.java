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
	 * ������ʷ��Ϣ��ѯ
	 */
	public List<InvtProductSettleDetailVo> querySettleHistory(Page page,SettleHisVo settleHisVo);
	/*
	 * ����ƷͶ��״̬
	 */
	public String checkInvestAjax(String invest_prt_id);
	/*
	 * ��ѯ����
	 */
	public Map<String,Object> querySettleMoreInfo(String invt_product_id);
	/*
	 * �������
	 */
	public String settlePay(String ustl_id);
	/*
	 * Ӧ��Ӧ����ѯ
	 */
	public List<UserSettleVo> querySettlePay(Page page,SettleVo settleVo);
}
