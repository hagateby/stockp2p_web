package com.p2p.webapp.user.regist.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.user.regist.dao.RegistDao;
import com.p2p.webapp.user.regist.dto.UserDto;
import com.p2p.webapp.user.regist.entity.User;
import com.p2p.webapp.user.regist.entity.UserCtl;
import com.p2p.webapp.user.regist.service.RegistService;

public class RegistServiceImpl implements RegistService{

	private RegistDao registDao;

	/*
	 * 检查手机号码是否已注册 0否 1是
	 */
	public String checkPhone(String phoneno){
		
		User user = registDao.queryUserByPhone(phoneno);
		
		if(user == null){
			//用户未注册
			return "0";
		}else{
			return "1";
		}
	}
	
	/*
	 * 注册用户
	 */
	public String registUser(UserDto userdto){
		//生成用户ID
		String userid = registDao.queryUserId();
		//用户基本信息
		User user = new User();
		user.setUser_id(userid);
		user.setCertif_no(userdto.getCertif_no());
		user.setCertif_type(userdto.getCertif_type());
		user.setMail(userdto.getMail());
		user.setPhone(userdto.getPhone());
		user.setUser_code(userdto.getUser_cde());
		user.setUser_name(userdto.getUser_name());
		user.setUser_status(userdto.getUser_status());
		user.setUser_type(userdto.getUser_type());
		registDao.insertUser(user);
		//创建用户账户
		Map paramap = new HashMap();
		paramap.put("user_id", userid);
		paramap.put("acc_type", Constant.getUser_acctyp_cash());
		paramap.put("acc_status", Constant.getUser_accstatus_act());
		paramap.put("acc_balance", "0");
		paramap.put("acc_freeze", "0");
		paramap.put("acc_enchash", "0");
		registDao.insertUserAccount(paramap);
		//用户安全控制
		UserCtl uctl = new UserCtl();
		uctl.setErr_count("0");
		uctl.setLogin_pwd(userdto.getLogin_pwd());
		//0有效 1冻结 2注销
		uctl.setUser_status("0");
		//0 手机认证 1身份认证 2银行卡认证
		uctl.setSecty_level("0");
		uctl.setUser_id(userid);
		registDao.insertUserCtl(uctl);
		
		
		//用户权限
		Map<String,Object> prvgmap = new HashMap<String,Object>();
		prvgmap.put("user_id", userid);
		prvgmap.put("role_code", "custom");
		registDao.insertUserPrvg(prvgmap);
		//注册成功
		return "0";
	}
	public RegistDao getRegistDao() {
		return registDao;
	}
	public void setRegistDao(RegistDao registDao) {
		this.registDao = registDao;
	}
}
