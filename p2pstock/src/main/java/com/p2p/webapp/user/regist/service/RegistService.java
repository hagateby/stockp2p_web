package com.p2p.webapp.user.regist.service;

import com.p2p.webapp.user.regist.dto.UserDto;

/**
 * ע�ᴦ����
 * @author Administrator
 *
 */
public interface RegistService {
	
	/*
	 * ��֤�ֻ����Ƿ��Ѿ�ע�� 0�� 1��
	 */
	public String checkPhone(String phoneno);
	
	/*
	 * ע���û�
	 */
	public String registUser(UserDto userdto);
}
