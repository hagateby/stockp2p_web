package com.p2p.webapp.user.login.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.p2p.webapp.system.menu.entity.MenuInfo;
import com.p2p.webapp.user.login.entity.OptLog;
import com.p2p.webapp.user.login.entity.UserInfo;
import com.p2p.webapp.user.regist.entity.UserCtl;

public interface LoginDao {
	
	/*
	 * �����ֻ��Ų�ѯ�û�
	 */
	public UserInfo queryUser(String pno);
	/*
	 * ����ʧ�ܴ���
	 */
	public void updateErrCt(@Param(value="updateErrCt")String updateErrCt,@Param(value="phoneno")String phoneno);
	/*
	 * ��ѯ����ʧ�ܴ���
	 */
	public UserCtl queryErrCt(String pno);
	/*
	 * �����û�״̬
	 */
	public void updateUserStatus(@Param(value="phoneno")String phoneno,@Param(value="status")String status);
	/*
	 * ��ѯ�û���¼��־
	 */
	public List<OptLog> queryUserOptLog(Map paramMap);
	
	/*
	 * �����û�������־
	 */
	public void insertUserOptLog(OptLog optlog);
	
	/*
	 * ��ѯ�û��˵�
	 */
	public List<MenuInfo> queryUserMenu(String user_id);
	
}
