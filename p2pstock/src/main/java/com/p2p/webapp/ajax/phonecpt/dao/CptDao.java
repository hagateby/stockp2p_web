package com.p2p.webapp.ajax.phonecpt.dao;

import com.p2p.webapp.ajax.phonecpt.entity.MsgLog;

public interface CptDao {
	
	
	/*
	 * ��ȡ������־ID+1
	 */
	public String queryLogId();
	
	/*
	 * ���������־��
	 */
	public void insertMsgLog(MsgLog msglog);
	

}
