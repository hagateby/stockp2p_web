package com.p2p.webapp.system.parammgr.dto;

public class SystemParamDto {
	//ID
	private String para_id = "";
	//��������
	private String para_code = "";
	//��������
	private String para_name = "";
	//����ֵ
	private String para_values = "";
	//��������
	private String para_type = "";
	//��������
	private String para_length = "";
	//˵��
	private String remark = "";
	//����ʱ��
	private String create_date = "" ;
	//������ʱ��
	private String update_date = "";
	public String getPara_id() {
		return para_id;
	}
	public void setPara_id(String paraId) {
		para_id = paraId;
	}
	public String getPara_code() {
		return para_code;
	}
	public void setPara_code(String paraCode) {
		para_code = paraCode;
	}
	public String getPara_name() {
		return para_name;
	}
	public void setPara_name(String paraName) {
		para_name = paraName;
	}
	public String getPara_values() {
		return para_values;
	}
	public void setPara_values(String paraValues) {
		para_values = paraValues;
	}
	public String getPara_type() {
		return para_type;
	}
	public void setPara_type(String paraType) {
		para_type = paraType;
	}
	public String getPara_length() {
		return para_length;
	}
	public void setPara_length(String paraLength) {
		para_length = paraLength;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
}
