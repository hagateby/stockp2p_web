package com.p2p.webapp.sysmgr.issuemgr.service;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.issuemgr.vo.InvestProductVo;

public interface IssueMgrService {
	
	/*
	 * ��ѯ���в�Ʒ��Ϣ
	 */
	public List<InvestProductVo> queryIssurInfo(Page page,InvestProductVo ivo);
	/*
	 * ��ѯ��Ͷ�ʲ�Ʒ��Ϣ
	 */
	public List<InvestProductVo> queryActiveIssurInfo(Page page);
	/*
	 * ��ѯ��ʷͶ�ʲ�Ʒ��Ϣ
	 */
	public List<InvestProductVo> queryHisIssurInfo(Page page);
	/*
	 * ����Ͷ�ʲ�Ʒ
	 */
	public String addInvestInfo(InvestProductVo invo);
	/*
	 * ��ѯ����
	 */
	public Map queryMoreinfo(String invest_product_id);
	/*
	 * ɾ��Ͷ�ʲ�Ʒ
	 */
	public String delInvestProduct(String invest_product_id);
	public String editInvestProduct(InvestProductVo invo);
}
