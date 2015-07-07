package com.p2p.webapp.user.regist.action;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.util.DateTimeFormatUtil;
import com.p2p.webapp.user.regist.dto.UserDto;
import com.p2p.webapp.user.regist.service.RegistService;
import com.p2p.webapp.user.regist.vo.RegistVo;
/**
 * �û�ע��ģ��
 * @author Administrator
 *
 */
public class RegistAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//д��־����
	public static Logger logger = LoggerFactory.getLogger(RegistAction.class);
	private RegistVo registvo;
	//service bean
	private RegistService registService;
	/*
	 * ע�ᰴťҳ����ת 
	 * @return
	 */
	public String registInit(){
		logger.debug("ע��init��ʼִ��");
		logger.debug("ע��init����ִ��");
		return "registinit";
	}
	/*
	 * �����ֻ���֤��
	 */
	public String regist(){
		logger.debug("ע��Action��ʼִ��");
		//����֤
		if("".equals(registvo.getCusMobile())||"".equals(registvo.getCusCpt())||"".equals(registvo.getCusPass1())||"".equals(registvo.getCusPass2())){
			logger.debug("����֤ʧ��");
			msgCode = "ERR0005";
			return "registinit";
		}
		//��֤�ֻ������Ƿ����
		String phonestatus = registService.checkPhone(registvo.getCusMobile());
		logger.debug("�Ƿ�ע�᣺" + phonestatus);
		//�Ѵ��ڲ���ע��
		if(!"0".equals(phonestatus)){
			msgCode = "ERR0006";
			return "registinit";
		}
		//��֤�ֻ���֤���Ƿ���ȷ
		String cptno = (String)session.get("cptno");
		String activtime =  (String)session.get("activetime");
		if(cptno == null || "".equals(cptno)||!cptno.equals(registvo.getCusCpt())){
			msgCode = "ERR0003";
			return "registinit";
		}
		//��֤�ֻ���֤����Ч��
		Date activedate = DateTimeFormatUtil.covertStringToDate(activtime,DateTimeFormatUtil.YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		Date nowdate = DateTimeFormatUtil.getCurrentDate();
		if(DateTimeFormatUtil.compareDateByDate(nowdate, activedate)){
			msgCode = "ERR0004";
			return "registinit";
		}
		//��������
		UserDto userdto = new UserDto();
		userdto.setPhone(registvo.getCusMobile());
		//�û�״̬ 
		userdto.setUser_status(Constant.getUser_status_active());
		//�û����� A ����Ա G��ͨ�û�
		userdto.setUser_type("G");
		userdto.setLogin_pwd(registvo.getCusPass1());
		String result = registService.registUser(userdto);
		logger.debug("ע��Action����");
		if("0".equals(result)){
			return "registok";
		}else{
			return "registfalse";
		}
		
	}
	public RegistVo getRegistvo() {
		return registvo;
	}
	public void setRegistvo(RegistVo registvo) {
		this.registvo = registvo;
	}

	public RegistService getRegistService() {
		return registService;
	}
	public void setRegistService(RegistService registService) {
		this.registService = registService;
	}
}
