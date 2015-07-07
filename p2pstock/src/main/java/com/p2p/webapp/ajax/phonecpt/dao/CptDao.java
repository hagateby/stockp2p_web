package com.p2p.webapp.ajax.phonecpt.dao;

import com.p2p.webapp.ajax.phonecpt.entity.MsgLog;

public interface CptDao {
	
	
	/*
	 * 获取短信日志ID+1
	 */
	public String queryLogId();
	
	/*
	 * 插入短信日志表
	 */
	public void insertMsgLog(MsgLog msglog);
	

}
