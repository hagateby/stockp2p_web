package com.p2p.webapp.user.fundmgr.vo;

public class TranInfoVo {

	private String tran_id;
	private String transion_seq;
	private String user_id;
	private String user_name;
	private String transion_type;
	private String transion_typename;
	private String payment_type;
	private String payment_typename;
	private String amount;
	private String update_date;
	private String invt_product_name;
	private String start_date;
	private String end_date;
	public String getTran_id() {
		return tran_id;
	}
	public void setTran_id(String tranId) {
		tran_id = tranId;
	}
	public String getTransion_seq() {
		return transion_seq;
	}
	public void setTransion_seq(String transionSeq) {
		transion_seq = transionSeq;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getTransion_type() {
		return transion_type;
	}
	public void setTransion_type(String transionType) {
		transion_type = transionType;
	}
	public String getTransion_typename() {
		return transion_typename;
	}
	public void setTransion_typename(String transionTypename) {
		transion_typename = transionTypename;
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
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String updateDate) {
		update_date = updateDate;
	}
	public String getInvt_product_name() {
		return invt_product_name;
	}
	public void setInvt_product_name(String invtProductName) {
		invt_product_name = invtProductName;
	}
	public String getPayment_typename() {
		return payment_typename;
	}
	public void setPayment_typename(String paymentTypename) {
		payment_typename = paymentTypename;
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
}
