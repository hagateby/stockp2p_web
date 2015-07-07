package com.p2p.webapp.sysmgr.pubresultmgr.entity;

public class ProductDetailCount {
	
	private String invt_product_id;
	private String basic_product_id;
	private String acount_prtfee;
	private String acount_bail;
	private String acount_charge;
	private String amount;
	
	public String getInvt_product_id() {
		return invt_product_id;
	}
	public void setInvt_product_id(String invtProductId) {
		invt_product_id = invtProductId;
	}
	public String getBasic_product_id() {
		return basic_product_id;
	}
	public void setBasic_product_id(String basicProductId) {
		basic_product_id = basicProductId;
	}
	public String getAcount_prtfee() {
		return acount_prtfee;
	}
	public void setAcount_prtfee(String acountPrtfee) {
		acount_prtfee = acountPrtfee;
	}
	public String getAcount_bail() {
		return acount_bail;
	}
	public void setAcount_bail(String acountBail) {
		acount_bail = acountBail;
	}
	public String getAcount_charge() {
		return acount_charge;
	}
	public void setAcount_charge(String acountCharge) {
		acount_charge = acountCharge;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
