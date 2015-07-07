package com.p2p.webapp.ajax.phonecpt.entity;

public class MsgLog {
	//短信ID
	private String msg_id;
	//短信业务类型
	private String msg_biztyp;
	//短信方式
	private String msg_typ;
	//手机号码
	private String msg_phoneno;
	//短信内容
	private String msg_value;
	//发送时间
	private String send_time;
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msgId) {
		msg_id = msgId;
	}
	public String getMsg_biztyp() {
		return msg_biztyp;
	}
	public void setMsg_biztyp(String msgBiztyp) {
		msg_biztyp = msgBiztyp;
	}
	public String getMsg_typ() {
		return msg_typ;
	}
	public void setMsg_typ(String msgTyp) {
		msg_typ = msgTyp;
	}
	public String getMsg_phoneno() {
		return msg_phoneno;
	}
	public void setMsg_phoneno(String msgPhoneno) {
		msg_phoneno = msgPhoneno;
	}
	public String getMsg_value() {
		return msg_value;
	}
	public void setMsg_value(String msgValue) {
		msg_value = msgValue;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String sendTime) {
		send_time = sendTime;
	}

}
