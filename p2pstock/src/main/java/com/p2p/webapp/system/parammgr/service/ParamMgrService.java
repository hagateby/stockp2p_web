package com.p2p.webapp.system.parammgr.service;

import java.util.List;

import com.p2p.webapp.system.parammgr.entity.MsgInfo;
import com.p2p.webapp.system.parammgr.entity.SystemParam;

public interface ParamMgrService {

	/*
	 * ����ID��ȡϵͳ������Ϣ
	 */
	public List<SystemParam>  querySystemParamInfo();
	/*
	 * ��ȡ��Ϣ���
	 */
	public List<MsgInfo>  queryMsgInfo();
	/*
	 * ����code��ȡ��Ϣ
	 */
	public MsgInfo queryMsgInfoByCode(String msg_code);
	/*
	 * ����ID��ȡϵͳ������Ϣ
	 */
	
	public SystemParam querySystemById(String id);
	/*
	 * ����CODE��ȡϵͳ������Ϣ
	 */
	public SystemParam querySystemByCode(String code);
	/*
	 * �������Ͷ�ȡϵͳ������Ϣ
	 */
	public List<SystemParam> querySystemByTyp(String typ);
	/*
	 * ����NAME��ȡϵͳ������Ϣ
	 */
	public SystemParam querySystemByName(String name);
	/*
	 * ����ֵ�����Ͷ�ȡϵͳ������Ϣ
	 */
	public SystemParam querySystemByValue(String value,String typ);
}
