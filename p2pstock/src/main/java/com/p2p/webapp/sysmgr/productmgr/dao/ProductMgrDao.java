package com.p2p.webapp.sysmgr.productmgr.dao;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.sysmgr.productmgr.entity.BaseProduct;


public interface ProductMgrDao {

	
	/*
	 * 查询基础标的表最大ID
	 */
	public String queryMaxLastSq();
	
	/*
	 * 插入彩票基础投资项目表 
	 */
	public void insertProductStock(Map parammap);
	
	/*
	 * 插入基础投资产品表
	 */
	public void insertBasicProduct(Map parammap);
	
	/*
	 * 查询基础标的列表
	 */
	public List<BaseProduct>  selectBasicProductlist(Map parammap);
	
	/*
	 * 根据基础标的ID查询详细信息
	 */
	public BaseProduct selectBasicProductById(String basic_product_id);
	/*
	 * 查询产品列表
	 */
	public List<BaseProduct>  selectBIProductById(Map parammap);
	/*
	 * 根据基础标的ID修改基础信息BasicProduct
	 */
	public void updateBasicProductById(Map parammap);
	
	/*
	 * 根据基础标的ID修改基础信息BasicProductStock
	 */
	public void updateBasicProductStockById(Map parammap);
	
	/*
	 * 删除基础标的信息BasicProduct
	 */
	public void delBasicProductById(String basic_product_id);
	
	/*
	 * 删除基础标的信息BasicProductStock
	 */
	public void delBasicProductStockById(String basic_product_id);
	
}
