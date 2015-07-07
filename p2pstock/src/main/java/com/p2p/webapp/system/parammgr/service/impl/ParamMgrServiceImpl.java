package com.p2p.webapp.system.parammgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.p2p.webapp.common.init.AppContext;
import com.p2p.webapp.system.parammgr.dao.ParamMgrDao;
import com.p2p.webapp.system.parammgr.entity.MsgInfo;
import com.p2p.webapp.system.parammgr.entity.SystemParam;
import com.p2p.webapp.system.parammgr.service.ParamMgrService;
public class ParamMgrServiceImpl implements ParamMgrService{
	
	private ParamMgrDao paramMgrDao;
	
	/*
	 * ��ȡϵͳ������Ϣ
	 */
	public List<SystemParam>  querySystemParamInfo(){
		
		//����id��ȡϵͳ������Ϣ
		List<SystemParam> list = paramMgrDao.getParamInfo();
		return list;
	}
	
	/*
	 * ��ȡ��Ϣ���
	 */
	public List<MsgInfo>  queryMsgInfo(){
		
		//����id��ȡϵͳ������Ϣ
		List<MsgInfo> list = paramMgrDao.getMsgInfo();
		return list;
	}
	
	/*
	 * ����code��ȡ��Ϣ
	 */
	public MsgInfo queryMsgInfoByCode(String msg_code){
		List<MsgInfo> list = AppContext.getMsginfolist();
		for(MsgInfo minfo:list){
			if(msg_code.equals(minfo.getMsg_code())){
				return minfo;
			}
		}
		return null;
	}
	/*
	 * ����ID��ȡϵͳ������Ϣ
	 */
	
	public SystemParam querySystemById(String id){
		 List<SystemParam> listp = AppContext.getSysteminfolist();
		 if(listp!=null){
			 for(int i=0;i<listp.size();i++){
				 SystemParam sp = listp.get(i);
				 if(id.equals(sp.getPara_id())){
					 return sp;
				 }
			 }
		 }
		 return null;
	}
	/*
	 * ����CODE��ȡϵͳ������Ϣ
	 */
	public SystemParam querySystemByCode(String code){
		 List<SystemParam> listp = AppContext.getSysteminfolist();
		 if(listp!=null){
			 for(int i=0;i<listp.size();i++){
				 SystemParam sp = listp.get(i);
				 if(code.equals(sp.getPara_code())){
					 return sp;
				 }
			 }
		 }
		 return null;
	}
	/*
	 * ����NAME��ȡϵͳ������Ϣ
	 */
	public SystemParam querySystemByName(String name){
		 List<SystemParam> listp = AppContext.getSysteminfolist();
		 if(listp!=null){
			 for(int i=0;i<listp.size();i++){
				 SystemParam sp = listp.get(i);
				 if(name.equals(sp.getPara_name())){
					 return sp;
				 }
			 }
		 }
		 return null;
	}
	/*
	 * �������Ͷ�ȡϵͳ������Ϣ
	 */
	public List<SystemParam> querySystemByTyp(String typ){
		 List<SystemParam> rlist = new ArrayList<SystemParam>();
		 List<SystemParam> listp = AppContext.getSysteminfolist();
		 if(listp!=null){
			 for(int i=0;i<listp.size();i++){
				 SystemParam sp = listp.get(i);
				 if(typ.equals(sp.getPara_type())){
					 rlist.add(sp);
				 }
			 }
		 }
		 return rlist;
	}
	/*
	 * ����ֵ�����Ͷ�ȡϵͳ������Ϣ
	 */
	public SystemParam querySystemByValue(String value,String typ){
		 SystemParam returnsp = new SystemParam();
		 if(value == null || typ == null || "".equals(value) || "".equals(typ)){
			 return returnsp;
		 }
		 List<SystemParam> listp = AppContext.getSysteminfolist();
		 if(listp!=null){
			 for(int i=0;i<listp.size();i++){
				 SystemParam sp = listp.get(i);
				 if(value.equals(sp.getPara_values()) &&  typ.equals(sp.getPara_type()) ){
					return sp;
				 }
			 }
		 }
		 return returnsp;
	}
	
	public ParamMgrDao getParamMgrDao() {
		return paramMgrDao;
	}


	public void setParamMgrDao(ParamMgrDao paramMgrDao) {
		this.paramMgrDao = paramMgrDao;
	}
}
