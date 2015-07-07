package com.p2p.webapp.invest.investmgr.dto;

public class InvestDetailDto {
	
	private String user_invest_id;
	private String invest_prdouct_id;
	private String basic_product_id;
	private String start_no;
	private String sub_code;
	//保证金总额
	private String acount_bail;
	//管理费总额
	private String acount_chargefee;
	//购买产品费用
	private String acount_prtfee;
	//总交费额度
	private String acount;
	public String getBasic_product_id() {
		return basic_product_id;
	}
	public void setBasic_product_id(String basicProductId) {
		basic_product_id = basicProductId;
	}
	public String getStart_no() {
		return start_no;
	}
	public void setStart_no(String startNo) {
		start_no = startNo;
	}
	public String getSub_code() {
		return sub_code;
	}
	public void setSub_code(String subCode) {
		sub_code = subCode;
	}
	public String getAcount_bail() {
		return acount_bail;
	}
	public void setAcount_bail(String acountBail) {
		acount_bail = acountBail;
	}

	public String getAcount() {
		return acount;
	}
	public void setAcount(String acount) {
		this.acount = acount;
	}
	public String getAcount_chargefee() {
		return acount_chargefee;
	}
	public void setAcount_chargefee(String acountChargefee) {
		acount_chargefee = acountChargefee;
	}
	public String getAcount_prtfee() {
		return acount_prtfee;
	}
	public void setAcount_prtfee(String acountPrtfee) {
		acount_prtfee = acountPrtfee;
	}
	public String getInvest_prdouct_id() {
		return invest_prdouct_id;
	}
	public void setInvest_prdouct_id(String investPrdouctId) {
		invest_prdouct_id = investPrdouctId;
	}
	public String getUser_invest_id() {
		return user_invest_id;
	}
	public void setUser_invest_id(String userInvestId) {
		user_invest_id = userInvestId;
	}
	
	
}
