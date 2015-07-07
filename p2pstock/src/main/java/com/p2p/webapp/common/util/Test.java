package com.p2p.webapp.common.util;

import java.util.HashMap;
import java.util.Map;

import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.http.HttpTools;
import com.p2p.webapp.common.http.impl.HttpToolsImpl;

public class Test {
	public static void main(String[] args){
		//String tt = DateTimeFormatUtil.covertDateToString(DateTimeFormatUtil.getCurrentDate(), DateTimeFormatUtil.YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		HttpTools httptool  = new HttpToolsImpl();
		Map sendMap = new HashMap();
		try{
		sendMap.put("account", Constant.getCptaccount());
		sendMap.put("pwd", Constant.getCptpwd());
		sendMap.put("product", "1060000123");
		sendMap.put("mobile", "13426179062,13911225598");
		sendMap.put("message", "²âÊÔÒ»ÏÂ¹þ¹þ");
		String rr = httptool.postRequest(Constant.getCptsendadress(), sendMap);
		System.out.println(rr);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
