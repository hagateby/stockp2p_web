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
	 * 更新中签号码
	 */
	public void updateResultNo(Map<String,Object> paramap);
	/*
	 * 查询中签号码个数
	 */
	public int selectLovCount(Map<String,Object> paramap);
	/*
	 * 查询某基础标的保证金总额、投资管理费总额、产品总额、总额 
	 */
	public ProductDetailCount selectDetailCount(Map<String,Object> paramap);
	/*
	 * 插入产品结算明细信息
	 */
	public void insertSettleDetail(Map<String,Object> paramap);
	/*
	 * 查询最新id
	 */
	public String queryMaxLastSq();
	/*
	 * 更新结算状态、利润分配流水
	 */
	public void updateSettle(Map<String,Object> paramap);
	/*
	 * 生成中奖号码
	 */
	public void insertLovNo(Map<String,Object> paramap);
	/*
	 * 删除中奖号码
	 */
	public void delLovNo(String invest_product_id);
	/*
	 * 查询用户投资利润
	 */
	public List<InvtProductSumInfo>  selectUserProfitSum(String invt_product_id);
	/*
	 * 查询产品总投资额度、利润
	 */
	public InvtProductSumInfo selectInvtProductSum(String invt_product_id);
	/*
	 * 新增用户结算收益信息
	 */
	public void insertProductSettle(Map<String,Object> paramap);
	/*
	 * 查询结算信息
	 */
	public List<InvtProductSettleDetail> selectSettleDetail(Map<String,Object> paramap);
	/*
	 * 根据产品查结算汇总信息
	 */
	public InvtProductSettleDetail  selectSettleSum(Map<String,Object> paramap);
	/*
	 * 查询投资收益汇总信息
	 */
	public List<UserSettle> selectSettleByPrt(Map<String,Object> paramap);
	/*
	 * 根据ID查询投资收益汇总信息
	 */
	public UserSettle selectSettleById(String ustl_id);
	/*
	 * 根据用户查询投资收益信息
	 */
	public List<UserSettle> selectSettleByUser(Map<String,Object> paramap);
	/*
	 * 历史投资
	 */
	public List<UserSettle> selectSettleByUserHis(Map<String,Object> paramap);
	/*
	 * 删除结算明细
	 */
	public void delSettleDetail(String invt_product_id);
	/*
	 * 删除用户结算表
	 */
	public void delSettle(String invt_product_id);
	/*
	 * 根据产品查询投资收益明细信息
	 */
	public List<InvtProductSettleDetail> selectSettleDetailByPrt(String invt_product_id);
	/*
	 * 更新投资利润分配、管理费
	 */
	public void updateInvestSettle(Map<String,Object> paramap);
	/*
	 * 更新结算状态、利润分配流水
	 */
	public void updateSettleStatus(Map<String,Object> paramap);
	
	/*
	 * 查询有效投资
	 */
	public List<Invest> selectActiveInvest(String invest_prdouct_id);
	
	/*
	 * 查询未审核的投资
	 */
	public List<Invest> selectUnCheckInvest(String invest_prdouct_id);
	
}
