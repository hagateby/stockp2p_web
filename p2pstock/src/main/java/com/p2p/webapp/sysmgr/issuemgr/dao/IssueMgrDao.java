package com.p2p.webapp.sysmgr.issuemgr.dao;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.sysmgr.issuemgr.entity.InvestMoreInfo;
import com.p2p.webapp.sysmgr.issuemgr.entity.InvestProduct;

public interface IssueMgrDao {
	
	/*
	 * 查询发行产品列表
	 */
	public  List<InvestProduct> selectInvestProductlist(Map parammap);
	/*
	 * 查询可投资产品列表
	 */
	public  List<InvestProduct> selectActiveProductlist();
	/*
	 * 查询历史投资产品列表
	 */
	public  List<InvestProduct> selectHisProductlist();
	/*
	 * 查询投资产品详细信息
	 */
	public  List<InvestMoreInfo> selectInvestMoreInfo(String invest_product_id);
	/*
	 * 查询投资产品基本信息
	 */
	public InvestMoreInfo selectInvtMoreInfoById(String invest_product_id);
	
	public  List<InvestMoreInfo> selectInvestMoreInfoByib(Map paramap);
	
	public  List<InvestMoreInfo> selectInvestMoreInfoByibSetl(Map paramap);
	
	/*
	 * 增加投资产品
	 */
	public void insertInvestProduct(Map paramap);
	
	/*
	 * 增加投资-基础-关联关系
	 */
	public void insertInvestBase(Map paramap);
	/*
	 * 删除投资产品
	 */
	public void delInvestProduct(String invest_product_id);
	/*
	 * 更新投资产品
	 */
	public void updateInvestProduct(Map paramap);
	/*
	 * 更新投资产品状态
	 */
	public void updateInvestProductStatus(Map paramap);
	
	/*
	 * 删除投资基础关联
	 */
	public void delInvestBase(String invt_product_id);
	/*
	 * 查询产品投资收益率
	 */
	public String selectInvestRate(String invt_product_id);
	/*
	 * 查询产品总中签数量
	 */
	public int selectInvtLovCount(String invt_product_id);
	/*
	 * 查询产品总投资额
	 */
	public String selectInvtCountAll(String invt_product_id);

}
