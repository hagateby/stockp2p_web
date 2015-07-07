package com.p2p.webapp.user.login.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.system.menu.entity.MenuInfo;
import com.p2p.webapp.user.login.service.LoginService;
import com.p2p.webapp.user.login.vo.LoginVo;
import com.p2p.webapp.user.regist.dto.UserDto;
/**
 * �û�ע��ģ��
 * @author Administrator
 *
 */
public class LoginAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//д��־����
	public static Logger logger = LoggerFactory.getLogger(LoginAction.class);
	//service
	private LoginService loginService;
	//��
	private LoginVo loginvo;
	/*
	 * ��¼��ťҳ����ת 
	 * @return
	 */
	public String loginInit(){
		return "logininit";
	}
	/*
	 * �һ����밴ťҳ����ת 
	 * @return
	 */
	public String findpwdInit(){
		return "findpwdinit";
	}
	/*
	 * �һ�����
	 */
	public String findpwd(){
		
		//��֤�ֻ���֤���Ƿ���ȷ
		msgCode = AppUtil.jugeCpt(session, loginvo.getCusCpt());
		if(!"".equals(msgCode)){
			return "findpwdERR";
		}
		//��֤�û�״̬
		String userstatus = loginService.queryUserStatus(loginvo.getUserno());
		//��֤ʧ��
		if("1".equals(userstatus)){
			msgCode="ERR0007";
			return "findpwdERR";
		}
		if("3".equals(userstatus)){
			msgCode="ERR0009";
			return "findpwdERR";
		}
		if("4".equals(userstatus)){
			msgCode="ERR0010";
			return "findpwdERR";
		}
		
		return "findpwdOK";
	}
	/*
	 * ���õ�¼����
	 */
	public String setnewpwd(){
		
		//ִ�е�¼
		String clientip = AppUtil.getIpAddr(request);
		//ִ�е�¼ҵ�����
		UserDto udto = loginService.updatePwd(loginvo,clientip);
		//��¼�����ݴ��session
		saveSeesion(request,udto);
		return "setnewpwdOK";
	}
	/*
	 * ��¼
	 */
	public String login(){
		logger.debug("�����¼��֤action");
		//��̨�����
		if("".equals(loginvo.getUserno())||"".equals(loginvo.getPasswd())){
			msgCode = "ERR0005";
			return "loginerr";
		}
		String clientip = AppUtil.getIpAddr(request);
		//ִ�е�¼ҵ�����
		UserDto udto = loginService.checkUser(loginvo.getUserno(), loginvo.getPasswd(), clientip);
		//��֤�ɹ�
		if("0".equals(udto.getLog_result())){
		    /* �ؼ���Ϣ����session  */
			saveSeesion(request,udto);
			msgCode="MSG0009";
			return "loginok";
		}
		//��֤ʧ��
		if("1".equals(udto.getLog_result())){
			msgCode="ERR0007";
		}
		if("2".equals(udto.getLog_result())){
			msgCode="ERR0008";
		}
		if("3".equals(udto.getLog_result())){
			msgCode="ERR0009";
		}
		if("4".equals(udto.getLog_result())){
			msgCode="ERR0010";
		}
		if("5".equals(udto.getLog_result())){
			msgCode="ERR0011";
		}
		return "loginerr";
	}
	public String saveSeesion(HttpServletRequest request,UserDto udto){
		
	    /* �ؼ���Ϣ����session  */
		//�û�id
		request.getSession().setAttribute(Constant.getSession_userid(), udto.getUser_id());
		//�ֻ���
		request.getSession().setAttribute(Constant.getSession_phone(), udto.getPhone());
		//�û���
		if(udto.getUser_nickname() != null && !"".equals(udto.getUser_nickname())){
			request.getSession().setAttribute(Constant.getSession_username(), udto.getUser_nickname());
		}else if(udto.getUser_name() != null && !"".equals(udto.getUser_name())){
			request.getSession().setAttribute(Constant.getSession_username(), udto.getUser_name());
		}else{
			request.getSession().setAttribute(Constant.getSession_username(), udto.getPhone());
		}
		//�û�����
		request.getSession().setAttribute(Constant.getSession_usertyp(), udto.getUser_type());
		//��ȡ�û��˵���Ϣ
		List<MenuInfo>  menulist = loginService.queryUserMenu(udto.getUser_id());
		request.getSession().setAttribute(Constant.getSession_mymenulist(),menulist);
		request.getSession().setAttribute("menusec","MYACOUNT");
		request.getSession().setAttribute("menusecname","�ҵ��˻�");
		logger.debug("�û���===="+ request.getSession().getAttribute(Constant.getSession_username()));
		logger.debug("�û�����===="+ request.getSession().getAttribute(Constant.getSession_usertyp()));
		return "0";
	}
	/*
	 * �˳���¼����session
	 */
	public String logout(){
		logger.debug("��ʼ����session");
		request.getSession().invalidate();
		logger.debug("�ɹ�����session");
		return "logout";
	}
	public LoginVo getLoginvo() {
		return loginvo;
	}
	public void setLoginvo(LoginVo loginvo) {
		this.loginvo = loginvo;
	}
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
