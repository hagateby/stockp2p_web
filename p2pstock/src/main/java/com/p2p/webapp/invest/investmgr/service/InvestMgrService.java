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
	 * �û�Ͷ��
	 */
	public String userInvest(InvestMgrVo imvo);
	/*
	 * Ͷ�����
	 */
	public String auditInvest(InvestMgrVo investMgrVo);
	/*
	 * �û�Ͷ���޸ĳ�ʼ��
	 */
	public Map<String,Object> editInit(String user_invest_id);
	
	/*
	 * �û�Ͷ���޸�
	 */
	public String editInvest(InvestMgrVo imvo);
	/*
	 * Ͷ�ʷ��ü���
	 * basic_product_idarray ��ƷID(�����,�ָ�) start_noarray��ʼ����(�����,�ָ�)sub_codearray ��������(�����,�ָ�)
	 */
	public Map<String,Object> investCount(FeeCountDto feeCountDto);
	/*
	 * Ͷ�ʸ���
	 */
	public String investPay(InvestPayVo payvo);
	/*
	 * ƽ̨��Ŀ�䶯
	 */
	public String platReceive(PlatReceiveVo platReceiveVo);
	/*
	 * ��ѯ�û�Ͷ����Ϣ
	 */
	public List<InvestVo> queryInvestInfo(Page page,InvestMgrVo  investMgrVo);
	/*
	 * ��ѯ�û�Ͷ����ϸ��Ϣ
	 */
	public List<InvestVo> queryInvestMoreInfo(String  user_invest_id);
	
	/*
	 * ��ѯ�û�Ͷ����Ϣ
	 */
	public List<InvestVo> queryInvestNow(Page page,InvestMgrVo  investMgrVo);
}
