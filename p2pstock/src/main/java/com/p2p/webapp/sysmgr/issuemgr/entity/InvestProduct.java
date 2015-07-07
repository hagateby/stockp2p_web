package com.p2p.webapp.sysmgr.issuemgr.entity;

public class InvestProduct {
	
	private String invt_product_id;
	private String user_id;
	private String user_name;
	private String invt_product_name;
	private String risk_lvl;
	private String invt_product_status;
	private String invt_product_statusname;
	private String create_date;
	private String start_date;
	private String end_date;
	private String amount;
	
	private String investstart_date;
	private String investend_date;
	//产品协议
	private String protol_id;
	//发起管理费
	private String issue_fee;
	
	private String flag;
	

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}

	public String getRisk_lvl() {
		return risk_lvl;
	}
	public void setRisk_lvl(String riskLvl) {
		risk_lvl = riskLvl;
	}
	public String getInvt_product_status() {
		return invt_product_status;
	}
	public void setInvt_product_status(String invtProductStatus) {
		invt_product_status = invtProductStatus;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String startDate) {
		start_date = startDate;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String endDate) {
		end_date = endDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getProtol_id() {
		return protol_id;
	}
	public void setProtol_id(String protolId) {
		protol_id = protolId;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getInvt_product_statusname() {
		return invt_product_statusname;
	}
	public void setInvt_product_statusname(String invtProductStatusname) {
		invt_product_statusname = invtProductStatusname;
	}
	public String getInvt_product_id() {
		return invt_product_id;
	}
	public void setInvt_product_id(String invtProductId) {
		invt_product_id = invtProductId;
	}
	public String getInvt_product_name() {
		return invt_product_name;
	}
	public void setInvt_product_name(String invtProductName) {
		invt_product_name = invtProductName;
	}
	public String getIssue_fee() {
		return issue_fee;
	}
	public void setIssue_fee(String issueFee) {
		issue_fee = issueFee;
	}
	public String getInveststart_date() {
		return investstart_date;
	}
	public void setInveststart_date(String investstartDate) {
		investstart_date = investstartDate;
	}
	public String getInvestend_date() {
		return investend_date;
	}
	public void setInvestend_date(String investendDate) {
		investend_date = investendDate;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String createDate) {
		create_date = createDate;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
