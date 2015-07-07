package com.p2p.webapp.sysmgr.issuemgr.vo;

public class InvestProductVo {
	
	private String invt_product_id;
	private String user_id;
	private String invt_product_name;
	private String risk_lvl;
	private String invt_product_status;
	private String invt_product_statusname;
	private String start_date;
	private String end_date;
	private String investstart_date;
	private String investend_date;
	//发行上限
	private String amount;
	private String protol_id;
	private String base_product_idarray;
	private String startdatearray;
	private String enddatearray;
	
	
	private String user_name;
	private String issue_fee;
	private String invest_rate;
	private String starter_bail;
	private String invester_bail;
	private String launcher_accruedcharges_type;
	private String launcher_accruedcharges_typename;
	private String launcher_accruedcharges_amount;
	private String user_accruedcharges_type;
	private String user_accruedcharges_typename;
	private String user_accruedcharges_amount;
	private String create_date;
	//已募集总额
	private String invest_all;
	//中签数量
	private String lot_count;
	//标识该产品是否已被投资 已被投资不能删除
	private String flag;
	//标识该产品是否超过募集期 0 否 1是
	private String dateflag;
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
	public String getInvt_product_statusname() {
		return invt_product_statusname;
	}
	public void setInvt_product_statusname(String invtProductStatusname) {
		invt_product_statusname = invtProductStatusname;
	}
	public String getBase_product_idarray() {
		return base_product_idarray;
	}
	public void setBase_product_idarray(String baseProductIdarray) {
		base_product_idarray = baseProductIdarray;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getIssue_fee() {
		return issue_fee;
	}
	public void setIssue_fee(String issueFee) {
		issue_fee = issueFee;
	}
	public String getInvt_product_name() {
		return invt_product_name;
	}
	public void setInvt_product_name(String invtProductName) {
		invt_product_name = invtProductName;
	}
	public String getInvt_product_id() {
		return invt_product_id;
	}
	public void setInvt_product_id(String invtProductId) {
		invt_product_id = invtProductId;
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
	public String getInvest_rate() {
		return invest_rate;
	}
	public void setInvest_rate(String investRate) {
		invest_rate = investRate;
	}
	public String getStarter_bail() {
		return starter_bail;
	}
	public void setStarter_bail(String starterBail) {
		starter_bail = starterBail;
	}
	public String getInvester_bail() {
		return invester_bail;
	}
	public void setInvester_bail(String investerBail) {
		invester_bail = investerBail;
	}
	public String getLauncher_accruedcharges_type() {
		return launcher_accruedcharges_type;
	}
	public void setLauncher_accruedcharges_type(String launcherAccruedchargesType) {
		launcher_accruedcharges_type = launcherAccruedchargesType;
	}
	public String getLauncher_accruedcharges_amount() {
		return launcher_accruedcharges_amount;
	}
	public void setLauncher_accruedcharges_amount(
			String launcherAccruedchargesAmount) {
		launcher_accruedcharges_amount = launcherAccruedchargesAmount;
	}
	public String getUser_accruedcharges_type() {
		return user_accruedcharges_type;
	}
	public void setUser_accruedcharges_type(String userAccruedchargesType) {
		user_accruedcharges_type = userAccruedchargesType;
	}
	public String getUser_accruedcharges_amount() {
		return user_accruedcharges_amount;
	}
	public void setUser_accruedcharges_amount(String userAccruedchargesAmount) {
		user_accruedcharges_amount = userAccruedchargesAmount;
	}
	public String getLauncher_accruedcharges_typename() {
		return launcher_accruedcharges_typename;
	}
	public void setLauncher_accruedcharges_typename(
			String launcherAccruedchargesTypename) {
		launcher_accruedcharges_typename = launcherAccruedchargesTypename;
	}
	public String getUser_accruedcharges_typename() {
		return user_accruedcharges_typename;
	}
	public void setUser_accruedcharges_typename(String userAccruedchargesTypename) {
		user_accruedcharges_typename = userAccruedchargesTypename;
	}
	public String getLot_count() {
		return lot_count;
	}
	public void setLot_count(String lotCount) {
		lot_count = lotCount;
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
	public String getDateflag() {
		return dateflag;
	}
	public void setDateflag(String dateflag) {
		this.dateflag = dateflag;
	}
	public String getInvest_all() {
		return invest_all;
	}
	public void setInvest_all(String investAll) {
		invest_all = investAll;
	}
	public String getStartdatearray() {
		return startdatearray;
	}
	public void setStartdatearray(String startdatearray) {
		this.startdatearray = startdatearray;
	}
	public String getEnddatearray() {
		return enddatearray;
	}
	public void setEnddatearray(String enddatearray) {
		this.enddatearray = enddatearray;
	}
	
	
}
