package com.p2p.webapp.sysmgr.usrmgr.action;

import java.util.List;

import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.usrmgr.service.UsrMgrService;
import com.p2p.webapp.sysmgr.usrmgr.vo.UserInfoVo;

public class UsrMgrAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserInfoVo userInfoVo;
	private UsrMgrService usrMgrService;
	private List<UserInfoVo> userlist;
	private String queryuserid;
	private Page page;
	/*
	 * �û��б���Ϣ��ѯ
	 */
	public String queryUserInfo(){
		
		if(userInfoVo == null){
			userInfoVo = new UserInfoVo();
		}
		
		//��ҳ
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		userlist = usrMgrService.queryUserInfoList(page,userInfoVo);
		//��ѯ��������session
		paramSave(page);
		return "queryUserInfook";
	}
	/*
	 * �û�������Ϣ��ѯ
	 */
	public String queryUserBaseInfo(){
		
		userInfoVo = usrMgrService.queryUserBaseInfo(queryuserid);
		
		return "queryUserBaseInfoOk";
	}
	/*
	 * �û���ϸ��Ϣ��ѯ
	 */
	public String queryMoreInfo(){
		
		return "queryMoreInfoOk";
	}
	
	
	public UserInfoVo getUserInfoVo() {
		return userInfoVo;
	}

	public void setUserInfoVo(UserInfoVo userInfoVo) {
		this.userInfoVo = userInfoVo;
	}

	public UsrMgrService getUsrMgrService() {
		return usrMgrService;
	}

	public void setUsrMgrService(UsrMgrService usrMgrService) {
		this.usrMgrService = usrMgrService;
	}

	public List<UserInfoVo> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<UserInfoVo> userlist) {
		this.userlist = userlist;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	public String getQueryuserid() {
		return queryuserid;
	}
	public void setQueryuserid(String queryuserid) {
		this.queryuserid = queryuserid;
	}

}
