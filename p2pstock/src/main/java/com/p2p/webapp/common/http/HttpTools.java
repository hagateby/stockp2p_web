package com.p2p.webapp.common.http;

import java.util.Map;

/**
 * 使用HTTPCLIENT进行数据通讯
 * @author Administrator
 *
 */
public interface HttpTools {

	/*
	 * post方式发送报文
	 */
	public String postRequest(String url,Map  sendMap)throws Exception;
	
}
