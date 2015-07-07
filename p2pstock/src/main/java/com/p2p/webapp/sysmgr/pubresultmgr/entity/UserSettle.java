package com.p2p.webapp.sysmgr.pubresultmgr.entity;

public class UserSettle {
	
	private String ustl_id;
	private String invest_user_id;
	//产品投资人
	private String user_name;
	private String invt_product_name;
	private String invt_product_id;
	private String transion_seq;

	private String create_date;
	private String update_date;
	//产品发起人
	private String create_username;
	//结算信息
	private String user_count;
	private String user_investbail_all;
	private String user_investcharge_all;
	private String user_buycount_all;
	private String user_investcount_all;
	private String user_recivecount_all;
	private String user_invest_profitpay;
	private String user_invest_profit;
	private String settle_flag;
	private String settle_flagname;
	private String user_paycount_all;
	
	public String getInvest_user_id() {
		return invest_user_id;
	}
	public void setInvest_user_id(String investUserId) {
		invest_user_id = investUserId;
	}
	public String getInvt_product_id() {
		return invt_product_id;
	}
	public void setInvt_product_id(String invtProductId) {
		invt_product_id = invtProductId;
	}
	public String getTransion_seq() {
		return transion_seq;
	}
	public void setTransion_seq(String transionSeq) {
		transion_seq = transionSeq;
	}
	public String getUser_invest_profitpay() {
		return user_invest_profitpay;
	}
	public void setUser_invest_profitpay(String userInvestProfitpay) {
		user_invest_profitpay = userInvestProfitpay;
	}
	public String getUser_invest_profit() {
		return user_invest_profit;
	}
	public void setUser_invest_profit(String userInvestProfit) {
		user_invest_profit = userInvestProfit;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String createDate) {
		create_date = createDate;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String updateDate) {
		update_date = updateDate;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getInvt_product_name() {
		return invt_product_name;
	}
	public void setInvt_product_name(String invtProductName) {
		invt_product_name = invtProductName;
	}
	public String getCreate_username() {
		return create_username;
	}
	public void setCreate_username(String createUsername) {
		create_username = createUsername;
	}
	public String getSettle_flag() {
		return settle_flag;
	}
	public void setSettle_flag(String settleFlag) {
		settle_flag = settleFlag;
	}
	public String getSettle_flagname() {
		return settle_flagname;
	}
	public void setSettle_flagname(String settleFlagname) {
		settle_flagname = settleFlagname;
	}
	public String getUser_count() {
		return user_count;
	}
	public void setUser_count(String userCount) {
		user_count = userCount;
	}
	public String getUser_investbail_all() {
		return user_investbail_all;
	}
	public void setUser_investbail_all(String userInvestbailAll) {
		user_investbail_all = userInvestbailAll;
	}
	public String getUser_investcharge_all() {
		return user_investcharge_all;
	}
	public void setUser_investcharge_all(String userInvestchargeAll) {
		user_investcharge_all = userInvestchargeAll;
	}
	public String getUser_buycount_all() {
		return user_buycount_all;
	}
	public void setUser_buycount_all(String userBuycountAll) {
		user_buycount_all = userBuycountAll;
	}
	public String getUser_investcount_all() {
		return user_investcount_all;
	}
	public void setUser_investcount_all(String userInvestcountAll) {
		user_investcount_all = userInvestcountAll;
	}
	public String getUser_recivecount_all() {
		return user_recivecount_all;
	}
	public void setUser_recivecount_all(String userRecivecountAll) {
		user_recivecount_all = userRecivecountAll;
	}
	public String getUser_paycount_all() {
		return user_paycount_all;
	}
	public void setUser_paycount_all(String userPaycountAll) {
		user_paycount_all = userPaycountAll;
	}
	public String getUstl_id() {
		return ustl_id;
	}
	public void setUstl_id(String ustlId) {
		ustl_id = ustlId;
	}

	
}
