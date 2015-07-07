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
 * 登录服务
 */
public class LoginServiceImpl implements LoginService{
	
	private LoginDao loginDao;
	private UserCenterDao userCenterDao;
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	/*
	 * 判断用户状态
	 */
	public String queryUserStatus(String userno){
		
		UserInfo user = loginDao.queryUser(userno);
		//用户名不存在
		if(user ==  null){
			logger.debug("用户名不存在");
			return "1";
		}
		//用户状态冻结
		if(Constant.getUser_status_cold().equals(user.getUser_status())){
			logger.debug("用户状态冻结");
			return "4";
		}
		//用户状态无效
		if(Constant.getUser_status_stop().equals(user.getUser_status())){
			logger.debug("用户状态无效");
			return "3";
		}
		return "0";
	}
	/*
	 * 密码找回
	 */
	public UserDto updatePwd(LoginVo loginvo,String ip){
		
		UserDto udto = new UserDto();
		UserInfo user = loginDao.queryUser(loginvo.getUserno());
		//设置登录密码
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("login_pwd", loginvo.getPasswd());
		paramap.put("user_id", user.getUser_id());
		userCenterDao.updateLoginPwd(paramap);
		/*   登录成功处理      */
		//增加一条登录日志成功
		OptLog llog = new OptLog();
		llog.setUser_id(user.getUser_id());
		llog.setOpt_type(Constant.getUser_opttyp_login());
		llog.setOpt_status(Constant.getUser_optstatus_succ());
		llog.setOpt_ip(ip);
		loginDao.insertUserOptLog(llog);
		logger.debug("增加登录日志成功");
		//验证成功获取用户信息
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
	 * 查询用户菜单集合
	 */
	public List<MenuInfo>  queryUserMenu(String user_id){
		List<MenuInfo> menulist = loginDao.queryUserMenu(user_id);
		return menulist;
	}
	/*
	 * 验证是否能够登录
	 */
	@SuppressWarnings("unchecked")
	public UserDto checkUser(String userno,String pwd,String ip){
		
		UserDto udto = new UserDto();
		UserInfo user = loginDao.queryUser(userno);
		//用户名不存在
		if(user ==  null){
			logger.debug("用户名不存在");
			udto.setLog_result("1");
			return udto;
		}
		//用户状态冻结
		if(Constant.getUser_status_cold().equals(user.getUser_status())){
			logger.debug("用户状态冻结");
			udto.setLog_result("4");
			return udto;
		}
		//用户状态无效
		if(Constant.getUser_status_stop().equals(user.getUser_status())){
			logger.debug("用户状态无效");
			udto.setLog_result("3");
			return udto;
		}
		//正常登录验证密码 找回密码不验证密码
		if(!pwd.equals(user.getLogin_pwd())){
			logger.debug("密码输入错误");
			udto.setLog_result("2");
			//增加一条登录日志失败
			OptLog llogerr = new OptLog();
			llogerr.setUser_id(user.getUser_id());
			llogerr.setOpt_type(Constant.getUser_opttyp_login());
			llogerr.setOpt_status(Constant.getUser_optstatus_err());
			llogerr.setOpt_ip(ip);
			loginDao.insertUserOptLog(llogerr);
			//查询当天的登录日志
			String nowdate = DateTimeFormatUtil.covertDateToString(DateTimeFormatUtil.getCurrentDate(),"yyyy-MM-dd");
			Map paramMap = new HashMap();
			paramMap.put("userid", user.getUser_id());
			paramMap.put("opttyp", Constant.getUser_opttyp_login());
			paramMap.put("sdate", nowdate);
			paramMap.put("edate", nowdate);
			List<OptLog> list  = loginDao.queryUserOptLog(paramMap);
			logger.debug("查询当天登录次数:"+list.size());
			boolean flag = false;
			if(list != null && list.size()>=Constant.getUser_loginerrr_count()){
				//最近n笔存在1笔成功登录，则视为未锁定
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
				//锁定用户为冻结状态
				loginDao.updateUserStatus(userno, Constant.getUser_status_cold());
			}
			return udto;
		}
		logger.debug("验证成功");
		/*   登录成功处理      */
		//增加一条登录日志成功
		OptLog llog = new OptLog();
		llog.setUser_id(user.getUser_id());
		llog.setOpt_type(Constant.getUser_opttyp_login());
		llog.setOpt_status(Constant.getUser_optstatus_succ());
		llog.setOpt_ip(ip);
		loginDao.insertUserOptLog(llog);
		logger.debug("增加登录日志成功");
		//验证成功获取用户信息
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
