package com.p2p.webapp.sysmgr.issuemgr.dao;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.sysmgr.issuemgr.entity.InvestMoreInfo;
import com.p2p.webapp.sysmgr.issuemgr.entity.InvestProduct;

public interface IssueMgrDao {
	
	/*
	 * ��ѯ���в�Ʒ�б�
	 */
	public  List<InvestProduct> selectInvestProductlist(Map parammap);
	/*
	 * ��ѯ��Ͷ�ʲ�Ʒ�б�
	 */
	public  List<InvestProduct> selectActiveProductlist();
	/*
	 * ��ѯ��ʷͶ�ʲ�Ʒ�б�
	 */
	public  List<InvestProduct> selectHisProductlist();
	/*
	 * ��ѯͶ�ʲ�Ʒ��ϸ��Ϣ
	 */
	public  List<InvestMoreInfo> selectInvestMoreInfo(String invest_product_id);
	/*
	 * ��ѯͶ�ʲ�Ʒ������Ϣ
	 */
	public InvestMoreInfo selectInvtMoreInfoById(String invest_product_id);
	
	public  List<InvestMoreInfo> selectInvestMoreInfoByib(Map paramap);
	
	public  List<InvestMoreInfo> selectInvestMoreInfoByibSetl(Map paramap);
	
	/*
	 * ����Ͷ�ʲ�Ʒ
	 */
	public void insertInvestProduct(Map paramap);
	
	/*
	 * ����Ͷ��-����-������ϵ
	 */
	public void insertInvestBase(Map paramap);
	/*
	 * ɾ��Ͷ�ʲ�Ʒ
	 */
	public void delInvestProduct(String invest_product_id);
	/*
	 * ����Ͷ�ʲ�Ʒ
	 */
	public void updateInvestProduct(Map paramap);
	/*
	 * ����Ͷ�ʲ�Ʒ״̬
	 */
	public void updateInvestProductStatus(Map paramap);
	
	/*
	 * ɾ��Ͷ�ʻ�������
	 */
	public void delInvestBase(String invt_product_id);
	/*
	 * ��ѯ��ƷͶ��������
	 */
	public String selectInvestRate(String invt_product_id);
	/*
	 * ��ѯ��Ʒ����ǩ����
	 */
	public int selectInvtLovCount(String invt_product_id);
	/*
	 * ��ѯ��Ʒ��Ͷ�ʶ�
	 */
	public String selectInvtCountAll(String invt_product_id);

}
