package com.p2p.webapp.sysmgr.productmgr.service;

import java.util.List;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.productmgr.vo.BaseProductVo;

public interface ProductMgrService {
	
	/*
	 * ���ӻ������
	 */
	public String addBasicStockProduct(BaseProductVo bvo);
	
	/*
	 * ��ѯ��������б�
	 */
	public List<BaseProductVo> queryBaseProductList(Page page,BaseProductVo paravo);
	/*
	 * ��ѯ��ϸ��Ϣ
	 */
	public BaseProductVo queryMoreInfo(String basic_product_id);
	/*
	 * �޸Ļ��������Ϣ
	 */
	public String editBaseProductInfo(BaseProductVo paravo);
	/*
	 * ɾ��������Ϣ
	 */
	public String delBaseProductInfo(String basic_product_id);
}
