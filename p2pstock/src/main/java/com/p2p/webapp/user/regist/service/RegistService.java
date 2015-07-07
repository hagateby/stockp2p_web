package com.p2p.webapp.user.regist.service;

import com.p2p.webapp.user.regist.dto.UserDto;

/**
 * 注册处理类
 * @author Administrator
 *
 */
public interface RegistService {
	
	/*
	 * 验证手机号是否已经注册 0否 1是
	 */
	public String checkPhone(String phoneno);
	
	/*
	 * 注册用户
	 */
	public String registUser(UserDto userdto);
}
