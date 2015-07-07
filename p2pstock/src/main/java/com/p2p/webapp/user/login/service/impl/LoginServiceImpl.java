package com.p2p.webapp.user.login.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.util.DateTimeFormatUtil;
import com.p2p.webapp.system.menu.entity.MenuInfo;
import com.p2p.webapp.user.login.dao.LoginDao;
import com.p2p.webapp.user.login.entity.OptLog;
import com.p2p.webapp.user.login.entity.UserInfo;
import com.p2p.webapp.user.login.service.LoginService;
import com.p2p.webapp.user.login.vo.LoginVo;
import com.p2p.webapp.user.regist.dto.UserDto;
import com.p2p.webapp.user.usercenter.dao.UserCenterDao;

/**
 * ��¼����
 */
public class LoginServiceImpl implements LoginService{
	
	private LoginDao loginDao;
	private UserCenterDao userCenterDao;
	//д��־����
	public static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	/*
	 * �ж��û�״̬
	 */
	public String queryUserStatus(String userno){
		
		UserInfo user = loginDao.queryUser(userno);
		//�û���������
		if(user ==  null){
			logger.debug("�û���������");
			return "1";
		}
		//�û�״̬����
		if(Constant.getUser_status_cold().equals(user.getUser_status())){
			logger.debug("�û�״̬����");
			return "4";
		}
		//�û�״̬��Ч
		if(Constant.getUser_status_stop().equals(user.getUser_status())){
			logger.debug("�û�״̬��Ч");
			return "3";
		}
		return "0";
	}
	/*
	 * �����һ�
	 */
	public UserDto updatePwd(LoginVo loginvo,String ip){
		
		UserDto udto = new UserDto();
		UserInfo user = loginDao.queryUser(loginvo.getUserno());
		//���õ�¼����
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("login_pwd", loginvo.getPasswd());
		paramap.put("user_id", user.getUser_id());
		userCenterDao.updateLoginPwd(paramap);
		/*   ��¼�ɹ�����      */
		//����һ����¼��־�ɹ�
		OptLog llog = new OptLog();
		llog.setUser_id(user.getUser_id());
		llog.setOpt_type(Constant.getUser_opttyp_login());
		llog.setOpt_status(Constant.getUser_optstatus_succ());
		llog.setOpt_ip(ip);
		loginDao.insertUserOptLog(llog);
		logger.debug("���ӵ�¼��־�ɹ�");
		//��֤�ɹ���ȡ�û���Ϣ
		udto.setLog_result("0");
		udto.setCertif_no(user.getCertif_no());
		udto.setCertif_type(user.getCertif_type());
		udto.setCreate_date(user.getCreate_date());
		udto.setMail(user.getMail());
		udto.setPhone(user.getPhone());
		udto.setUpdate_date(user.getUpdate_date());
		udto.setUser_cde(user.getUser_cde());
		udto.setUser_id(user.getUser_id());
		udto.setUser_name(user.getUser_name());
		udto.setUser_status(user.getUser_status());
		udto.setUser_type(user.getUser_type());
		udto.setUser_nickname(user.getUser_nickname());
		
		return udto;
	} 
	/*
	 * ��ѯ�û��˵�����
	 */
	public List<MenuInfo>  queryUserMenu(String user_id){
		List<MenuInfo> menulist = loginDao.queryUserMenu(user_id);
		return menulist;
	}
	/*
	 * ��֤�Ƿ��ܹ���¼
	 */
	@SuppressWarnings("unchecked")
	public UserDto checkUser(String userno,String pwd,String ip){
		
		UserDto udto = new UserDto();
		UserInfo user = loginDao.queryUser(userno);
		//�û���������
		if(user ==  null){
			logger.debug("�û���������");
			udto.setLog_result("1");
			return udto;
		}
		//�û�״̬����
		if(Constant.getUser_status_cold().equals(user.getUser_status())){
			logger.debug("�û�״̬����");
			udto.setLog_result("4");
			return udto;
		}
		//�û�״̬��Ч
		if(Constant.getUser_status_stop().equals(user.getUser_status())){
			logger.debug("�û�״̬��Ч");
			udto.setLog_result("3");
			return udto;
		}
		//������¼��֤���� �һ����벻��֤����
		if(!pwd.equals(user.getLogin_pwd())){
			logger.debug("�����������");
			udto.setLog_result("2");
			//����һ����¼��־ʧ��
			OptLog llogerr = new OptLog();
			llogerr.setUser_id(user.getUser_id());
			llogerr.setOpt_type(Constant.getUser_opttyp_login());
			llogerr.setOpt_status(Constant.getUser_optstatus_err());
			llogerr.setOpt_ip(ip);
			loginDao.insertUserOptLog(llogerr);
			//��ѯ����ĵ�¼��־
			String nowdate = DateTimeFormatUtil.covertDateToString(DateTimeFormatUtil.getCurrentDate(),"yyyy-MM-dd");
			Map paramMap = new HashMap();
			paramMap.put("userid", user.getUser_id());
			paramMap.put("opttyp", Constant.getUser_opttyp_login());
			paramMap.put("sdate", nowdate);
			paramMap.put("edate", nowdate);
			List<OptLog> list  = loginDao.queryUserOptLog(paramMap);
			logger.debug("��ѯ�����¼����:"+list.size());
			boolean flag = false;
			if(list != null && list.size()>=Constant.getUser_loginerrr_count()){
				//���n�ʴ���1�ʳɹ���¼������Ϊδ����
				for(int i=0;i<Constant.getUser_loginerrr_count();i++){
					OptLog llog = (OptLog)list.get(i);
					if("0".equals(llog.getOpt_status())){
						flag = true;
						break;
					}
				}
			}else{
				flag = true;
			}
			if(!flag){
				udto.setLog_result("5");
				//�����û�Ϊ����״̬
				loginDao.updateUserStatus(userno, Constant.getUser_status_cold());
			}
			return udto;
		}
		logger.debug("��֤�ɹ�");
		/*   ��¼�ɹ�����      */
		//����һ����¼��־�ɹ�
		OptLog llog = new OptLog();
		llog.setUser_id(user.getUser_id());
		llog.setOpt_type(Constant.getUser_opttyp_login());
		llog.setOpt_status(Constant.getUser_optstatus_succ());
		llog.setOpt_ip(ip);
		loginDao.insertUserOptLog(llog);
		logger.debug("���ӵ�¼��־�ɹ�");
		//��֤�ɹ���ȡ�û���Ϣ
		udto.setLog_result("0");
		udto.setCertif_no(user.getCertif_no());
		udto.setCertif_type(user.getCertif_type());
		udto.setCreate_date(user.getCreate_date());
		udto.setMail(user.getMail());
		udto.setPhone(user.getPhone());
		udto.setUpdate_date(user.getUpdate_date());
		udto.setUser_cde(user.getUser_cde());
		udto.setUser_id(user.getUser_id());
		udto.setUser_name(user.getUser_name());
		udto.setUser_status(user.getUser_status());
		udto.setUser_type(user.getUser_type());
		udto.setUser_nickname(user.getUser_nickname());
		return udto;
		
	}
	public LoginDao getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	public UserCenterDao getUserCenterDao() {
		return userCenterDao;
	}
	public void setUserCenterDao(UserCenterDao userCenterDao) {
		this.userCenterDao = userCenterDao;
	}
	
	 

}
