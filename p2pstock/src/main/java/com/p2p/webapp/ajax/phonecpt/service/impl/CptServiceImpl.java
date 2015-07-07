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

	//д��־����
	public static Logger logger = LoggerFactory.getLogger(CptServiceImpl.class);
	//���ݿ�ʵ��
	private CptDao cptDao;

	public Map sendPhoneCpt(String phoneno){
		
		Map resultMap = new HashMap();
		AppUtil util = new AppUtil();
		String randomstr = util.createRandom(true, 6);
		logger.debug("�ֻ���֤�룺"+randomstr);
		String msgvalue = Constant.getCptModulStr().replaceAll("#x", randomstr);
		logger.debug("�������ݣ�"+msgvalue);
		//���Ͷ���
		PhoneMsg pm = new PhoneMsg();
		String msgresult = pm.sendMsg(phoneno, msgvalue);
		if(!"0".equals(msgresult)){
			logger.error("�����ֻ���֤��ʧ�ܣ�"+phoneno);
			resultMap.put("result", "-1");
			return resultMap;
		}
		/* ���¶�����־ */
		//��ȡ�����־ID
		String msgid = cptDao.queryLogId();
		MsgLog msglog = new MsgLog();
		//������֤��
		msglog.setMsg_biztyp("01");
		//����ID
		msglog.setMsg_id(msgid);
		//�ֻ�����
		msglog.setMsg_phoneno(phoneno);
		//����
		msglog.setMsg_typ("0");
		//��������
		msglog.setMsg_value(msgvalue);
		cptDao.insertMsgLog(msglog);
		logger.error("���¶�����־��ɹ���"+phoneno);
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
