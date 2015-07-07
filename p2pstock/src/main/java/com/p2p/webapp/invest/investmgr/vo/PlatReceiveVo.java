package com.p2p.webapp.invest.investmgr.vo;

public class PlatReceiveVo {
	private String transion_seq;
	private String transion_type;
	private String payment_type;
	private String amount;
	public String getTransion_seq() {
		return transion_seq;
	}
	public void setTransion_seq(String transionSeq) {
		transion_seq = transionSeq;
	}
	public String getTransion_type() {
		return transion_type;
	}
	public void setTransion_type(String transionType) {
		transion_type = transionType;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String paymentType) {
		payment_type = paymentType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

}
