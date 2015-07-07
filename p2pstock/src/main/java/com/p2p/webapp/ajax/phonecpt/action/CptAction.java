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
 * 手机验证码处理模块
 * @author Administrator
 *
 */
public class CptAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(CptAction.class);
	//验证码处理service
	private CptService cptService;
	//注册service
	private RegistService registService;
	private String ajxStr;
	
	/*
	 * 注册按钮页面跳转 
	 * @return
	 */
	public String registInit(){
		return "registinit";
	}
	/*
	 * 注册发送手机验证码
	 */
	public String ajaxCpt(){
		logger.debug("发送验证码Action开始执行");
		ActionContext context = ActionContext.getContext();  
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String mobile = (String)request.getSession().getAttribute("phone");
		boolean optflag = true;
		if(mobile == null || "".equals(mobile)){
			mobile = request.getParameter("cptcusMobile");
		}else{
			optflag = false;
		}
		logger.debug("手机号码："+mobile);
		//验证手机号码是否已经注册
		String registflag = registService.checkPhone(mobile);
		if("1".equals(registflag) && optflag){
			//手机号码已经注册过
			ajxStr = "2";
			return "cptsendok";
		}
		
		Map resultMap = cptService.sendPhoneCpt(mobile);
		String msgresult = (String)resultMap.get("result");
		String cptno = (String)resultMap.get("cptno");
		//验证码存入session
		request.getSession().setAttribute("cptno", cptno);
		//验证码有效时间
		Date nowtime = DateTimeFormatUtil.getCurrentDate();
		Date activetime = DateTimeFormatUtil.addTimeBySeconds(nowtime,60);
		String activestr = DateTimeFormatUtil.covertDateToString(activetime, DateTimeFormatUtil.YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		request.getSession().setAttribute("activetime", activestr);
		logger.debug("手机验证码："+cptno);
		logger.debug("手机验证码有效时间："+activestr);
		if("0".equals(msgresult)){
			//成功
			ajxStr = "0";
		}else{
			//失败
			ajxStr = "1";
		}
		logger.debug("发送验证码Action开始结束");
		return "cptsendok";
	}
	/*
	 * 发送手机验证码
	 */
	public String ajaxCptOnly(){
		logger.debug("发送验证码Action开始执行");
		ActionContext context = ActionContext.getContext();  
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String mobile = (String)request.getSession().getAttribute("phone");
		boolean optflag = true;
		if(mobile == null || "".equals(mobile)){
			mobile = request.getParameter("cptcusMobile");
		}else{
			optflag = false;
		}
		logger.debug("手机号码："+mobile);
		Map resultMap = cptService.sendPhoneCpt(mobile);
		String msgresult = (String)resultMap.get("result");
		String cptno = (String)resultMap.get("cptno");
		//验证码存入session
		request.getSession().setAttribute("cptno", cptno);
		//验证码有效时间
		Date nowtime = DateTimeFormatUtil.getCurrentDate();
		Date activetime = DateTimeFormatUtil.addTimeBySeconds(nowtime,60);
		String activestr = DateTimeFormatUtil.covertDateToString(activetime, DateTimeFormatUtil.YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		request.getSession().setAttribute("activetime", activestr);
		logger.debug("手机验证码："+cptno);
		logger.debug("手机验证码有效时间："+activestr);
		if("0".equals(msgresult)){
			//成功
			ajxStr = "0";
		}else{
			//失败
			ajxStr = "1";
		}
		logger.debug("发送验证码Action开始结束");
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
