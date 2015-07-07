package com.p2p.webapp.sysmgr.pubresultmgr.dao;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.invest.investmgr.entity.Invest;
import com.p2p.webapp.sysmgr.pubresultmgr.entity.InvtProductSettleDetail;
import com.p2p.webapp.sysmgr.pubresultmgr.entity.InvtProductSumInfo;
import com.p2p.webapp.sysmgr.pubresultmgr.entity.ProductDetailCount;
import com.p2p.webapp.sysmgr.pubresultmgr.entity.UserSettle;

public interface PubResultMgrDao {
	
	/*
	 * ������ǩ����
	 */
	public void updateResultNo(Map<String,Object> paramap);
	/*
	 * ��ѯ��ǩ�������
	 */
	public int selectLovCount(Map<String,Object> paramap);
	/*
	 * ��ѯĳ������ı�֤���ܶͶ�ʹ�����ܶ��Ʒ�ܶ�ܶ� 
	 */
	public ProductDetailCount selectDetailCount(Map<String,Object> paramap);
	/*
	 * �����Ʒ������ϸ��Ϣ
	 */
	public void insertSettleDetail(Map<String,Object> paramap);
	/*
	 * ��ѯ����id
	 */
	public String queryMaxLastSq();
	/*
	 * ���½���״̬�����������ˮ
	 */
	public void updateSettle(Map<String,Object> paramap);
	/*
	 * �����н�����
	 */
	public void insertLovNo(Map<String,Object> paramap);
	/*
	 * ɾ���н�����
	 */
	public void delLovNo(String invest_product_id);
	/*
	 * ��ѯ�û�Ͷ������
	 */
	public List<InvtProductSumInfo>  selectUserProfitSum(String invt_product_id);
	/*
	 * ��ѯ��Ʒ��Ͷ�ʶ�ȡ�����
	 */
	public InvtProductSumInfo selectInvtProductSum(String invt_product_id);
	/*
	 * �����û�����������Ϣ
	 */
	public void insertProductSettle(Map<String,Object> paramap);
	/*
	 * ��ѯ������Ϣ
	 */
	public List<InvtProductSettleDetail> selectSettleDetail(Map<String,Object> paramap);
	/*
	 * ���ݲ�Ʒ����������Ϣ
	 */
	public InvtProductSettleDetail  selectSettleSum(Map<String,Object> paramap);
	/*
	 * ��ѯͶ�����������Ϣ
	 */
	public List<UserSettle> selectSettleByPrt(Map<String,Object> paramap);
	/*
	 * ����ID��ѯͶ�����������Ϣ
	 */
	public UserSettle selectSettleById(String ustl_id);
	/*
	 * �����û���ѯͶ��������Ϣ
	 */
	public List<UserSettle> selectSettleByUser(Map<String,Object> paramap);
	/*
	 * ��ʷͶ��
	 */
	public List<UserSettle> selectSettleByUserHis(Map<String,Object> paramap);
	/*
	 * ɾ��������ϸ
	 */
	public void delSettleDetail(String invt_product_id);
	/*
	 * ɾ���û������
	 */
	public void delSettle(String invt_product_id);
	/*
	 * ���ݲ�Ʒ��ѯͶ��������ϸ��Ϣ
	 */
	public List<InvtProductSettleDetail> selectSettleDetailByPrt(String invt_product_id);
	/*
	 * ����Ͷ��������䡢�����
	 */
	public void updateInvestSettle(Map<String,Object> paramap);
	/*
	 * ���½���״̬�����������ˮ
	 */
	public void updateSettleStatus(Map<String,Object> paramap);
	
	/*
	 * ��ѯ��ЧͶ��
	 */
	public List<Invest> selectActiveInvest(String invest_prdouct_id);
	
	/*
	 * ��ѯδ��˵�Ͷ��
	 */
	public List<Invest> selectUnCheckInvest(String invest_prdouct_id);
	
}
