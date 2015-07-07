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
	 * 根据手机号查询用户
	 */
	public UserInfo queryUser(String pno);
	/*
	 * 更新失败次数
	 */
	public void updateErrCt(@Param(value="updateErrCt")String updateErrCt,@Param(value="phoneno")String phoneno);
	/*
	 * 查询当天失败次数
	 */
	public UserCtl queryErrCt(String pno);
	/*
	 * 更新用户状态
	 */
	public void updateUserStatus(@Param(value="phoneno")String phoneno,@Param(value="status")String status);
	/*
	 * 查询用户登录日志
	 */
	public List<OptLog> queryUserOptLog(Map paramMap);
	
	/*
	 * 增加用户操作日志
	 */
	public void insertUserOptLog(OptLog optlog);
	
	/*
	 * 查询用户菜单
	 */
	public List<MenuInfo> queryUserMenu(String user_id);
	
}
