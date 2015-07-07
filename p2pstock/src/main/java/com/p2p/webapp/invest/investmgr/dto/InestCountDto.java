package com.p2p.webapp.invest.investmgr.dto;

public class InestCountDto {
	
	//保证金总额
	private String acount_bail;
	//管理费总额
	private String acount_mgrfee;
	//购买产品费用
	private String acount_prtfee;
	private String acount_all;
	
	public String getAcount_bail() {
		return acount_bail;
	}
	public void setAcount_bail(String acountBail) {
		acount_bail = acountBail;
	}
	public String getAcount_mgrfee() {
		return acount_mgrfee;
	}
	public void setAcount_mgrfee(String acountMgrfee) {
		acount_mgrfee = acountMgrfee;
	}
	public String getAcount_prtfee() {
		return acount_prtfee;
	}
	public void setAcount_prtfee(String acountPrtfee) {
		acount_prtfee = acountPrtfee;
	}
	public String getAcount_all() {
		return acount_all;
	}
	public void setAcount_all(String acountAll) {
		acount_all = acountAll;
	}
	
}
