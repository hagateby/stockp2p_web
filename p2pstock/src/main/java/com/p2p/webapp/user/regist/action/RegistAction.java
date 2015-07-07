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
 * 用户注册模块
 * @author Administrator
 *
 */
public class RegistAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(RegistAction.class);
	private RegistVo registvo;
	//service bean
	private RegistService registService;
	/*
	 * 注册按钮页面跳转 
	 * @return
	 */
	public String registInit(){
		logger.debug("注册init开始执行");
		logger.debug("注册init结束执行");
		return "registinit";
	}
	/*
	 * 发送手机验证码
	 */
	public String regist(){
		logger.debug("注册Action开始执行");
		//表单验证
		if("".equals(registvo.getCusMobile())||"".equals(registvo.getCusCpt())||"".equals(registvo.getCusPass1())||"".equals(registvo.getCusPass2())){
			logger.debug("表单验证失败");
			msgCode = "ERR0005";
			return "registinit";
		}
		//验证手机号码是否存在
		String phonestatus = registService.checkPhone(registvo.getCusMobile());
		logger.debug("是否注册：" + phonestatus);
		//已存在不能注册
		if(!"0".equals(phonestatus)){
			msgCode = "ERR0006";
			return "registinit";
		}
		//验证手机验证码是否正确
		String cptno = (String)session.get("cptno");
		String activtime =  (String)session.get("activetime");
		if(cptno == null || "".equals(cptno)||!cptno.equals(registvo.getCusCpt())){
			msgCode = "ERR0003";
			return "registinit";
		}
		//验证手机验证码有效期
		Date activedate = DateTimeFormatUtil.covertStringToDate(activtime,DateTimeFormatUtil.YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		Date nowdate = DateTimeFormatUtil.getCurrentDate();
		if(DateTimeFormatUtil.compareDateByDate(nowdate, activedate)){
			msgCode = "ERR0004";
			return "registinit";
		}
		//插入数据
		UserDto userdto = new UserDto();
		userdto.setPhone(registvo.getCusMobile());
		//用户状态 
		userdto.setUser_status(Constant.getUser_status_active());
		//用户类型 A 管理员 G普通用户
		userdto.setUser_type("G");
		userdto.setLogin_pwd(registvo.getCusPass1());
		String result = registService.registUser(userdto);
		logger.debug("注册Action结束");
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
