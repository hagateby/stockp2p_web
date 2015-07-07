package com.p2p.webapp.invest.investmgr.dao;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.invest.investmgr.entity.Invest;
import com.p2p.webapp.invest.investmgr.entity.InvestDetail;
import com.p2p.webapp.sysmgr.issuemgr.entity.InvestMoreInfo;



public interface InvestMgrDao {
	
	/*
	 * 增加用户投资信息
	 */
	public void insertInvestInfo(Map<String,Object> paramap);
	/*
	 * 增加用户投资属性信息
	 */
	public void insertInvestDetail(Map<String,Object> paramap);
	
	/*
	 * 删除用户投资信息
	 */
	public void delInvestInfo(String user_invest_id);
	
	/*
	 * 删除用户投资明细信息
	 */
	public void delInvestDetail(String user_invest_id);
	
	/*
	 * 根据用户投资ID查询投资信息
	 */
	public Invest queryInvest(String user_invest_id);
	
	/*
	 * 查询最新生成的序列
	 */
	public String queryMaxLastSq();
	/*
	 * 增加交易流水表
	 */
	public void insertTranSeq(Map<String,Object> paramap);
	/*
	 * 更新投资状态
	 */
	public void updateInvestStatusByUser(Map<String,Object> paramap);
	/*
	 * 更新投资状态
	 */
	public void updateInvestDetailStatusByUser(Map<String,Object> paramap);
	/*
	 * 根据产品ID更新投资状态
	 */
	public void updateInvestStatusByInvt(Map<String,Object> paramap);
	/*
	 * 根据产品ID更新投资状态
	 */
	public void updateInvestDetailStatusByInvt(Map<String,Object> paramap);
	
	
	
	/*
	 * 生成投资号码库
	 */
	public void insertInvestNo(Map<String,Object> paramap);
	/*
	 * 查询投资产品投资明细
	 */
	public List<InvestDetail> selectInvestDetail(Map<String,Object> paramap);
	/*
	 * 查询用户投资信息
	 */
	public List<Invest> selectInvest(String invest_product_id);
	/*
	 * 查询用户总收益
	 */
	public String selectUserInvestProfit(String invest_user_id);
	/*
	 * 查询用户总投资额
	 */
	public String selectUserInvestMoney(String invest_user_id);
	/*
	 * 查询用户投资信息
	 */
	public List<Invest> selectUserInvestInfo(Map<String,Object> paramap);
	/*
	 * 查询用户当前投资信息
	 */
	public List<Invest> selectUserInvestNow(Map<String,Object> paramap);
	/*
	 * 查询用户投资详细信息
	 */
	public List<Invest> selectUserInvestMoreInfo(String user_invest_id);
	/*
	 * 更新投资状态
	 */
	public void updateInvestStatusByPrt(Map<String,Object> map);
	
	/*
	 * 投资详细信息查询
	 */
	public List<InvestMoreInfo> selectInvestMoreInfo(String user_invest_id);
	
}
