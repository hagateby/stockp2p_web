package com.p2p.webapp.ajax.phonecpt.service;

import java.util.Map;

/**
 * 手机验证码处理
 * @author Administrator
 *
 */
public interface CptService {

	/*
	 * 发送手机验证码
	 * @param phoneno
	 * @return
	 */
	public Map sendPhoneCpt(String phoneno);
}
