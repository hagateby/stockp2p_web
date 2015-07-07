package com.p2p.webapp.invest.investmgr.dto;

public class InvestCountDto {
	
	//保证金总额
	private String acount_bailall;
	//管理费总额
	private String acount_chargefeeall;
	//购买产品费用
	private String acount_prtfeeall;
	//总交费额度
	private String acount_all;
	public String getAcount_bailall() {
		return acount_bailall;
	}
	public void setAcount_bailall(String acountBailall) {
		acount_bailall = acountBailall;
	}

	public String getAcount_prtfeeall() {
		return acount_prtfeeall;
	}
	public void setAcount_prtfeeall(String acountPrtfeeall) {
		acount_prtfeeall = acountPrtfeeall;
	}
	public String getAcount_all() {
		return acount_all;
	}
	public void setAcount_all(String acountAll) {
		acount_all = acountAll;
	}
	public String getAcount_chargefeeall() {
		return acount_chargefeeall;
	}
	public void setAcount_chargefeeall(String acountChargefeeall) {
		acount_chargefeeall = acountChargefeeall;
	}
	

	
}
