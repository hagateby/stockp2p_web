package com.p2p.webapp.ajax.phonecpt.dto;

public class MsgLogDto {

	//����ID
	private String MSG_ID;
	//����ҵ������
	private String MSG_BIZTYP;
	//���ŷ�ʽ
	private String MSG_TYP;
	//�ֻ�����
	private String MSG_PHONENO;
	//��������
	private String MSG_VALUE;
	//����ʱ��
	private String SEND_TIME;
	public String getMSG_ID() {
		return MSG_ID;
	}
	public void setMSG_ID(String mSGID) {
		MSG_ID = mSGID;
	}
	public String getMSG_BIZTYP() {
		return MSG_BIZTYP;
	}
	public void setMSG_BIZTYP(String mSGBIZTYP) {
		MSG_BIZTYP = mSGBIZTYP;
	}
	public String getMSG_TYP() {
		return MSG_TYP;
	}
	public void setMSG_TYP(String mSGTYP) {
		MSG_TYP = mSGTYP;
	}
	public String getMSG_PHONENO() {
		return MSG_PHONENO;
	}
	public void setMSG_PHONENO(String mSGPHONENO) {
		MSG_PHONENO = mSGPHONENO;
	}
	public String getMSG_VALUE() {
		return MSG_VALUE;
	}
	public void setMSG_VALUE(String mSGVALUE) {
		MSG_VALUE = mSGVALUE;
	}
	public String getSEND_TIME() {
		return SEND_TIME;
	}
	public void setSEND_TIME(String sENDTIME) {
		SEND_TIME = sENDTIME;
	}
	
}
