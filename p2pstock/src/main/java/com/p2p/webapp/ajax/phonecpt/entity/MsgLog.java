package com.p2p.webapp.ajax.phonecpt.entity;

public class MsgLog {
	//����ID
	private String msg_id;
	//����ҵ������
	private String msg_biztyp;
	//���ŷ�ʽ
	private String msg_typ;
	//�ֻ�����
	private String msg_phoneno;
	//��������
	private String msg_value;
	//����ʱ��
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
