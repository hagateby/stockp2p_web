package com.p2p.webapp.ajax.phonecpt.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.p2p.webapp.ajax.phonecpt.dao.CptDao;
import com.p2p.webapp.ajax.phonecpt.entity.MsgLog;
import com.p2p.webapp.ajax.phonecpt.service.CptService;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.phonemsg.PhoneMsg;
import com.p2p.webapp.common.util.AppUtil;

public class CptServiceImpl implements CptService{

	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(CptServiceImpl.class);
	//数据库实例
	private CptDao cptDao;

	public Map sendPhoneCpt(String phoneno){
		
		Map resultMap = new HashMap();
		AppUtil util = new AppUtil();
		String randomstr = util.createRandom(true, 6);
		logger.debug("手机验证码："+randomstr);
		String msgvalue = Constant.getCptModulStr().replaceAll("#x", randomstr);
		logger.debug("短信内容："+msgvalue);
		//发送短信
		PhoneMsg pm = new PhoneMsg();
		String msgresult = pm.sendMsg(phoneno, msgvalue);
		if(!"0".equals(msgresult)){
			logger.error("发送手机验证码失败："+phoneno);
			resultMap.put("result", "-1");
			return resultMap;
		}
		/* 更新短信日志 */
		//获取最大日志ID
		String msgid = cptDao.queryLogId();
		MsgLog msglog = new MsgLog();
		//短信验证码
		msglog.setMsg_biztyp("01");
		//短信ID
		msglog.setMsg_id(msgid);
		//手机号码
		msglog.setMsg_phoneno(phoneno);
		//上行
		msglog.setMsg_typ("0");
		//短信内容
		msglog.setMsg_value(msgvalue);
		cptDao.insertMsgLog(msglog);
		logger.error("更新短信日志表成功："+phoneno);
		resultMap.put("result", "0");
		resultMap.put("cptno", randomstr);
		return resultMap;
	}

	public CptDao getCptDao() {
		return cptDao;
	}

	public void setCptDao(CptDao cptDao) {
		this.cptDao = cptDao;
	}


}
