package com.p2p.webapp.system.parammgr.service;

import java.util.List;

import com.p2p.webapp.system.parammgr.entity.MsgInfo;
import com.p2p.webapp.system.parammgr.entity.SystemParam;

public interface ParamMgrService {

	/*
	 * 根据ID获取系统参数信息
	 */
	public List<SystemParam>  querySystemParamInfo();
	/*
	 * 读取信息码表
	 */
	public List<MsgInfo>  queryMsgInfo();
	/*
	 * 根据code获取信息
	 */
	public MsgInfo queryMsgInfoByCode(String msg_code);
	/*
	 * 根据ID读取系统参数信息
	 */
	
	public SystemParam querySystemById(String id);
	/*
	 * 根据CODE读取系统参数信息
	 */
	public SystemParam querySystemByCode(String code);
	/*
	 * 根据类型读取系统参数信息
	 */
	public List<SystemParam> querySystemByTyp(String typ);
	/*
	 * 根据NAME读取系统参数信息
	 */
	public SystemParam querySystemByName(String name);
	/*
	 * 根据值、类型读取系统参数信息
	 */
	public SystemParam querySystemByValue(String value,String typ);
}
