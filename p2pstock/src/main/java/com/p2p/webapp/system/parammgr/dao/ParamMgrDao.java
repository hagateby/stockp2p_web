package com.p2p.webapp.system.parammgr.dao;

import java.util.List;

import com.p2p.webapp.system.parammgr.entity.MsgInfo;
import com.p2p.webapp.system.parammgr.entity.SystemParam;


	
	public interface ParamMgrDao{
		
		/*
		 * 根据ID获取系统参数信息
		 */
		public List<SystemParam> getParamInfo();

		/*
		 * 根据ID获取信息
		 */
		public List<MsgInfo> getMsgInfo();
	}
