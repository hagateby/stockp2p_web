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

	//д��־����
	public static Logger logger = LoggerFactory.getLogger(PhoneMsg.class);
	/**
	 * ʵʱ���͵����ֻ�����
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
				logger.debug("���ŷ��ؽ����"+msgrlt);
			}else{
				logger.debug("ģ�ⷢ�Ͷ���");
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
