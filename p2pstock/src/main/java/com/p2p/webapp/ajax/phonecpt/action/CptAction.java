package com.p2p.webapp.ajax.phonecpt.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.p2p.webapp.ajax.phonecpt.service.CptService;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.util.DateTimeFormatUtil;
import com.p2p.webapp.user.regist.service.RegistService;
/**
 * �ֻ���֤�봦��ģ��
 * @author Administrator
 *
 */
public class CptAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//д��־����
	public static Logger logger = LoggerFactory.getLogger(CptAction.class);
	//��֤�봦��service
	private CptService cptService;
	//ע��service
	private RegistService registService;
	private String ajxStr;
	
	/*
	 * ע�ᰴťҳ����ת 
	 * @return
	 */
	public String registInit(){
		return "registinit";
	}
	/*
	 * ע�ᷢ���ֻ���֤��
	 */
	public String ajaxCpt(){
		logger.debug("������֤��Action��ʼִ��");
		ActionContext context = ActionContext.getContext();  
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String mobile = (String)request.getSession().getAttribute("phone");
		boolean optflag = true;
		if(mobile == null || "".equals(mobile)){
			mobile = request.getParameter("cptcusMobile");
		}else{
			optflag = false;
		}
		logger.debug("�ֻ����룺"+mobile);
		//��֤�ֻ������Ƿ��Ѿ�ע��
		String registflag = registService.checkPhone(mobile);
		if("1".equals(registflag) && optflag){
			//�ֻ������Ѿ�ע���
			ajxStr = "2";
			return "cptsendok";
		}
		
		Map resultMap = cptService.sendPhoneCpt(mobile);
		String msgresult = (String)resultMap.get("result");
		String cptno = (String)resultMap.get("cptno");
		//��֤�����session
		request.getSession().setAttribute("cptno", cptno);
		//��֤����Чʱ��
		Date nowtime = DateTimeFormatUtil.getCurrentDate();
		Date activetime = DateTimeFormatUtil.addTimeBySeconds(nowtime,60);
		String activestr = DateTimeFormatUtil.covertDateToString(activetime, DateTimeFormatUtil.YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		request.getSession().setAttribute("activetime", activestr);
		logger.debug("�ֻ���֤�룺"+cptno);
		logger.debug("�ֻ���֤����Чʱ�䣺"+activestr);
		if("0".equals(msgresult)){
			//�ɹ�
			ajxStr = "0";
		}else{
			//ʧ��
			ajxStr = "1";
		}
		logger.debug("������֤��Action��ʼ����");
		return "cptsendok";
	}
	/*
	 * �����ֻ���֤��
	 */
	public String ajaxCptOnly(){
		logger.debug("������֤��Action��ʼִ��");
		ActionContext context = ActionContext.getContext();  
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String mobile = (String)request.getSession().getAttribute("phone");
		boolean optflag = true;
		if(mobile == null || "".equals(mobile)){
			mobile = request.getParameter("cptcusMobile");
		}else{
			optflag = false;
		}
		logger.debug("�ֻ����룺"+mobile);
		Map resultMap = cptService.sendPhoneCpt(mobile);
		String msgresult = (String)resultMap.get("result");
		String cptno = (String)resultMap.get("cptno");
		//��֤�����session
		request.getSession().setAttribute("cptno", cptno);
		//��֤����Чʱ��
		Date nowtime = DateTimeFormatUtil.getCurrentDate();
		Date activetime = DateTimeFormatUtil.addTimeBySeconds(nowtime,60);
		String activestr = DateTimeFormatUtil.covertDateToString(activetime, DateTimeFormatUtil.YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		request.getSession().setAttribute("activetime", activestr);
		logger.debug("�ֻ���֤�룺"+cptno);
		logger.debug("�ֻ���֤����Чʱ�䣺"+activestr);
		if("0".equals(msgresult)){
			//�ɹ�
			ajxStr = "0";
		}else{
			//ʧ��
			ajxStr = "1";
		}
		logger.debug("������֤��Action��ʼ����");
		return "cptsendok";
	}
	
	public String getAjxStr() {
		return ajxStr;
	}
	public void setAjxStr(String ajxStr) {
		this.ajxStr = ajxStr;
	}

	public CptService getCptService() {
		return cptService;
	}
	public void setCptService(CptService cptService) {
		this.cptService = cptService;
	}
	public RegistService getRegistService() {
		return registService;
	}
	public void setRegistService(RegistService registService) {
		this.registService = registService;
	}

}
