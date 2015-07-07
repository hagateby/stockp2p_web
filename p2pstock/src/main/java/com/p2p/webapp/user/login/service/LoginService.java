package com.p2p.webapp.user.login.service;

import java.util.List;

import com.p2p.webapp.system.menu.entity.MenuInfo;
import com.p2p.webapp.user.login.vo.LoginVo;
import com.p2p.webapp.user.regist.dto.UserDto;

public interface LoginService {
	
	/*
	 * 验证是否能够登录
	 */
	public UserDto checkUser(String userno,String pwd,String ip);
	
	/*
	 * 判断用户状态
	 */
	public String queryUserStatus(String userno);
	/*
	 * 密码找回
	 */
	public UserDto updatePwd(LoginVo loginvo,String ip);
	
	/*
	 * 查询用户菜单集合
	 */
	public List<MenuInfo>  queryUserMenu(String user_id);
}
