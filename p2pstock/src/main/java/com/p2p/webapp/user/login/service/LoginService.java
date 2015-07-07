package com.p2p.webapp.user.login.service;

import java.util.List;

import com.p2p.webapp.system.menu.entity.MenuInfo;
import com.p2p.webapp.user.login.vo.LoginVo;
import com.p2p.webapp.user.regist.dto.UserDto;

public interface LoginService {
	
	/*
	 * ��֤�Ƿ��ܹ���¼
	 */
	public UserDto checkUser(String userno,String pwd,String ip);
	
	/*
	 * �ж��û�״̬
	 */
	public String queryUserStatus(String userno);
	/*
	 * �����һ�
	 */
	public UserDto updatePwd(LoginVo loginvo,String ip);
	
	/*
	 * ��ѯ�û��˵�����
	 */
	public List<MenuInfo>  queryUserMenu(String user_id);
}
