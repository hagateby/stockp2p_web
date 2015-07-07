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
	 * ����ֻ������Ƿ���ע�� 0�� 1��
	 */
	public String checkPhone(String phoneno){
		
		User user = registDao.queryUserByPhone(phoneno);
		
		if(user == null){
			//�û�δע��
			return "0";
		}else{
			return "1";
		}
	}
	
	/*
	 * ע���û�
	 */
	public String registUser(UserDto userdto){
		//�����û�ID
		String userid = registDao.queryUserId();
		//�û�������Ϣ
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
		//�����û��˻�
		Map paramap = new HashMap();
		paramap.put("user_id", userid);
		paramap.put("acc_type", Constant.getUser_acctyp_cash());
		paramap.put("acc_status", Constant.getUser_accstatus_act());
		paramap.put("acc_balance", "0");
		paramap.put("acc_freeze", "0");
		paramap.put("acc_enchash", "0");
		registDao.insertUserAccount(paramap);
		//�û���ȫ����
		UserCtl uctl = new UserCtl();
		uctl.setErr_count("0");
		uctl.setLogin_pwd(userdto.getLogin_pwd());
		//0��Ч 1���� 2ע��
		uctl.setUser_status("0");
		//0 �ֻ���֤ 1�����֤ 2���п���֤
		uctl.setSecty_level("0");
		uctl.setUser_id(userid);
		registDao.insertUserCtl(uctl);
		
		
		//�û�Ȩ��
		Map<String,Object> prvgmap = new HashMap<String,Object>();
		prvgmap.put("user_id", userid);
		prvgmap.put("role_code", "custom");
		registDao.insertUserPrvg(prvgmap);
		//ע��ɹ�
		return "0";
	}
	public RegistDao getRegistDao() {
		return registDao;
	}
	public void setRegistDao(RegistDao registDao) {
		this.registDao = registDao;
	}
}
