package com.p2p.webapp.common.http;

import java.util.Map;

/**
 * ʹ��HTTPCLIENT��������ͨѶ
 * @author Administrator
 *
 */
public interface HttpTools {

	/*
	 * post��ʽ���ͱ���
	 */
	public String postRequest(String url,Map  sendMap)throws Exception;
	
}
