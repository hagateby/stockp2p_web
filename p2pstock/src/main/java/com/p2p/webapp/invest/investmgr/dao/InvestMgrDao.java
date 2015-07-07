package com.p2p.webapp.invest.investmgr.dao;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.invest.investmgr.entity.Invest;
import com.p2p.webapp.invest.investmgr.entity.InvestDetail;
import com.p2p.webapp.sysmgr.issuemgr.entity.InvestMoreInfo;



public interface InvestMgrDao {
	
	/*
	 * �����û�Ͷ����Ϣ
	 */
	public void insertInvestInfo(Map<String,Object> paramap);
	/*
	 * �����û�Ͷ��������Ϣ
	 */
	public void insertInvestDetail(Map<String,Object> paramap);
	
	/*
	 * ɾ���û�Ͷ����Ϣ
	 */
	public void delInvestInfo(String user_invest_id);
	
	/*
	 * ɾ���û�Ͷ����ϸ��Ϣ
	 */
	public void delInvestDetail(String user_invest_id);
	
	/*
	 * �����û�Ͷ��ID��ѯͶ����Ϣ
	 */
	public Invest queryInvest(String user_invest_id);
	
	/*
	 * ��ѯ�������ɵ�����
	 */
	public String queryMaxLastSq();
	/*
	 * ���ӽ�����ˮ��
	 */
	public void insertTranSeq(Map<String,Object> paramap);
	/*
	 * ����Ͷ��״̬
	 */
	public void updateInvestStatusByUser(Map<String,Object> paramap);
	/*
	 * ����Ͷ��״̬
	 */
	public void updateInvestDetailStatusByUser(Map<String,Object> paramap);
	/*
	 * ���ݲ�ƷID����Ͷ��״̬
	 */
	public void updateInvestStatusByInvt(Map<String,Object> paramap);
	/*
	 * ���ݲ�ƷID����Ͷ��״̬
	 */
	public void updateInvestDetailStatusByInvt(Map<String,Object> paramap);
	
	
	
	/*
	 * ����Ͷ�ʺ����
	 */
	public void insertInvestNo(Map<String,Object> paramap);
	/*
	 * ��ѯͶ�ʲ�ƷͶ����ϸ
	 */
	public List<InvestDetail> selectInvestDetail(Map<String,Object> paramap);
	/*
	 * ��ѯ�û�Ͷ����Ϣ
	 */
	public List<Invest> selectInvest(String invest_product_id);
	/*
	 * ��ѯ�û�������
	 */
	public String selectUserInvestProfit(String invest_user_id);
	/*
	 * ��ѯ�û���Ͷ�ʶ�
	 */
	public String selectUserInvestMoney(String invest_user_id);
	/*
	 * ��ѯ�û�Ͷ����Ϣ
	 */
	public List<Invest> selectUserInvestInfo(Map<String,Object> paramap);
	/*
	 * ��ѯ�û���ǰͶ����Ϣ
	 */
	public List<Invest> selectUserInvestNow(Map<String,Object> paramap);
	/*
	 * ��ѯ�û�Ͷ����ϸ��Ϣ
	 */
	public List<Invest> selectUserInvestMoreInfo(String user_invest_id);
	/*
	 * ����Ͷ��״̬
	 */
	public void updateInvestStatusByPrt(Map<String,Object> map);
	
	/*
	 * Ͷ����ϸ��Ϣ��ѯ
	 */
	public List<InvestMoreInfo> selectInvestMoreInfo(String user_invest_id);
	
}
