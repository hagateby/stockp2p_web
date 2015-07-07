package com.p2p.webapp.user.usercenter.vo;

public class UserBankVo {
	
	
	//用户银行ID
	private String user_bank_acc_id;
	//用户ID 
	private String user_id;
	//银行代码
	private String bank_code;
	//银行名称
	private String bank_name;
	//开户行名称
	private String bank_branch_name;
	//账号
	private String bank_acc_code;
	//是否是首选账号
	private String default_flag;
	//创建时间
	private String create_date;
	//更新时间
	private String update_date;
	//手机验证码
	private String cptno;
	
	public String getUser_bank_acc_id() {
		return user_bank_acc_id;
	}
	public void setUser_bank_acc_id(String userBankAccId) {
		user_bank_acc_id = userBankAccId;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bankCode) {
		bank_code = bankCode;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bankName) {
		bank_name = bankName;
	}
	public String getBank_branch_name() {
		return bank_branch_name;
	}
	public void setBank_branch_name(String bankBranchName) {
		bank_branch_name = bankBranchName;
	}
	public String getBank_acc_code() {
		return bank_acc_code;
	}
	public void setBank_acc_code(String bankAccCode) {
		bank_acc_code = bankAccCode;
	}
	public String getDefault_flag() {
		return default_flag;
	}
	public void setDefault_flag(String defaultFlag) {
		default_flag = defaultFlag;
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
	public String getCptno() {
		return cptno;
	}
	public void setCptno(String cptno) {
		this.cptno = cptno;
	}
	
	
	
	
	
	
	
}
