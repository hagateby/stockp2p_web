package com.p2p.webapp.sysmgr.productmgr.service;

import java.util.List;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.productmgr.vo.BaseProductVo;

public interface ProductMgrService {
	
	/*
	 * 增加基础标的
	 */
	public String addBasicStockProduct(BaseProductVo bvo);
	
	/*
	 * 查询基础标的列表
	 */
	public List<BaseProductVo> queryBaseProductList(Page page,BaseProductVo paravo);
	/*
	 * 查询详细信息
	 */
	public BaseProductVo queryMoreInfo(String basic_product_id);
	/*
	 * 修改基础标的信息
	 */
	public String editBaseProductInfo(BaseProductVo paravo);
	/*
	 * 删除基础信息
	 */
	public String delBaseProductInfo(String basic_product_id);
}
