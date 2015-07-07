package com.p2p.webapp.sysmgr.productmgr.dao;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.sysmgr.productmgr.entity.BaseProduct;


public interface ProductMgrDao {

	
	/*
	 * ��ѯ������ı����ID
	 */
	public String queryMaxLastSq();
	
	/*
	 * �����Ʊ����Ͷ����Ŀ�� 
	 */
	public void insertProductStock(Map parammap);
	
	/*
	 * �������Ͷ�ʲ�Ʒ��
	 */
	public void insertBasicProduct(Map parammap);
	
	/*
	 * ��ѯ��������б�
	 */
	public List<BaseProduct>  selectBasicProductlist(Map parammap);
	
	/*
	 * ���ݻ������ID��ѯ��ϸ��Ϣ
	 */
	public BaseProduct selectBasicProductById(String basic_product_id);
	/*
	 * ��ѯ��Ʒ�б�
	 */
	public List<BaseProduct>  selectBIProductById(Map parammap);
	/*
	 * ���ݻ������ID�޸Ļ�����ϢBasicProduct
	 */
	public void updateBasicProductById(Map parammap);
	
	/*
	 * ���ݻ������ID�޸Ļ�����ϢBasicProductStock
	 */
	public void updateBasicProductStockById(Map parammap);
	
	/*
	 * ɾ�����������ϢBasicProduct
	 */
	public void delBasicProductById(String basic_product_id);
	
	/*
	 * ɾ�����������ϢBasicProductStock
	 */
	public void delBasicProductStockById(String basic_product_id);
	
}
