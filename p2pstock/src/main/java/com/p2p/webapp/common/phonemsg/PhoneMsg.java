package com.p2p.webapp.common.phonemsg;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.http.HttpTools;
import com.p2p.webapp.common.http.impl.HttpToolsImpl;


public class PhoneMsg {

	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(PhoneMsg.class);
	/**
	 * 实时发送单条手机短信
	 * @param phoneno
	 * @param msg
	 * @return
	 */
	public String sendMsg(String phoneno,String msg){
		
		HttpTools httptool = new HttpToolsImpl();
		
		String url = Constant.getCptsendadress();
		Map<String,Object> sendMap = new HashMap<String,Object>();
		
		try{
			sendMap.put("account", Constant.getCptaccount());
			sendMap.put("pwd", Constant.getCptpwd());
			sendMap.put("product", Constant.getProductid());
			sendMap.put("mobile", phoneno);
			sendMap.put("message", msg);
			if("0".equals(Constant.getCptflag())){
				String msgrlt = httptool.postRequest(url, sendMap);
				logger.debug("短信返回结果："+msgrlt);
			}else{
				logger.debug("模拟发送短信");
				return "0";
			}
		}catch(Exception e){
			return "-1";
		}
		return "0";
	}
	
	public String sendMsgList(List<MsgPo> msgList){
		
		
		return "0";
	}

}
