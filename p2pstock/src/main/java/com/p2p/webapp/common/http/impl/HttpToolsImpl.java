package com.p2p.webapp.common.http.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.p2p.webapp.common.http.HttpTools;

public class HttpToolsImpl implements HttpTools{
	
	public String postRequest(String url,Map  sendMap) throws Exception{
		if(sendMap == null){
			throw new Exception("the post-map param can not be null!");
		}
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "gb2312");
		//getMethod.addRequestHeader("Content-Type","text/html;charset=gb2312");
		Set keySet=sendMap.keySet();
	     String key="";
	     for(Iterator<String> it=keySet.iterator();it.hasNext();)
	     {
	         key=it.next();
	         if(key!=null && !key.trim().equals(""))
	         {
	        	 postMethod.setParameter(key, (String)sendMap.get(key));

	         }
	      }
	    httpClient.executeMethod(postMethod);
		return postMethod.getResponseBodyAsString();
	}
}
