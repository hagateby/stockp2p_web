package com.p2p.webapp.system.parammgr.dao;

import java.util.List;

import com.p2p.webapp.system.parammgr.entity.MsgInfo;
import com.p2p.webapp.system.parammgr.entity.SystemParam;


	
	public interface ParamMgrDao{
		
		/*
		 * ����ID��ȡϵͳ������Ϣ
		 */
		public List<SystemParam> getParamInfo();

		/*
		 * ����ID��ȡ��Ϣ
		 */
		public List<MsgInfo> getMsgInfo();
	}
