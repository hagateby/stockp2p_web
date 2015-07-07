package com.p2p.webapp.sysmgr.usrmgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.sysmgr.usrmgr.dao.UsrMgrDao;
import com.p2p.webapp.sysmgr.usrmgr.service.UsrMgrService;
import com.p2p.webapp.sysmgr.usrmgr.vo.UserInfoVo;
import com.p2p.webapp.user.login.entity.UserInfo;

public class UsrMgrServiceImpl implements UsrMgrService{
	
	private UsrMgrDao usrMgrDao;
	
	/*
	 * 查询用户信息列表
	 */
	
	public List<UserInfoVo> queryUserInfoList(Page page,UserInfoVo uvo){
		List<UserInfoVo> rlist = new ArrayList<UserInfoVo>();
		Map<String,Object> paramap = new HashMap<String,Object>();

		if("1".equals(page.getPageflag())){
			paramap = page.getParamMap();
			paramap.put("page", page);
		}else{
			paramap.put("page", page);
			paramap.put("phone", uvo.getPhone());
			paramap.put("user_name", uvo.getUser_name());
			paramap.put("user_namelike", "%"+ AppUtil.nvlStr(uvo.getUser_name()) +"%");
			page.setParamMap(paramap);
		}
		List<UserInfo> datalist = usrMgrDao.queryUserInfo(paramap);
		for(UserInfo info:datalist){
			UserInfoVo uservo = new UserInfoVo();
			uservo.setAcc_balance(info.getAcc_balance());
			uservo.setAcc_enchash(info.getAcc_enchash());
			uservo.setAcc_freeze(info.getAcc_freeze());
			uservo.setAcc_status(info.getAcc_status());
			uservo.setAcc_statusname(info.getAcc_statusname());
			uservo.setBank_acc_code(info.getBank_acc_code());
			uservo.setBank_name(info.getBank_name());
			uservo.setCertif_no(info.getCertif_no());
			uservo.setCertif_type(info.getCertif_type());
			uservo.setCreate_date(info.getCreate_date());
			uservo.setErr_count(info.getErr_count());
			uservo.setMail(info.getMail());
			uservo.setPhone(info.getPhone());
			uservo.setRecod_phone(info.getRecod_phone());
			uservo.setSecty_level(info.getSecty_level());
			uservo.setSecty_levelname(info.getSecty_levelname());
			uservo.setUpdate_date(info.getUpdate_date());
			uservo.setUser_code(info.getUser_cde());
			uservo.setUser_credit(info.getUser_credit());
			uservo.setUser_id(info.getUser_id());
			uservo.setUser_name(info.getUser_name());
			uservo.setUser_nickname(info.getUser_nickname());
			uservo.setUser_status(info.getUser_status());
			uservo.setUser_statusname(info.getUser_statusname());
			uservo.setUser_type(info.getUser_type());
			uservo.setUser_typename(info.getUser_typename());
			rlist.add(uservo);
		}
		return rlist;
	}
	/*
	 * 查询用户基本信息
	 */
	public UserInfoVo queryUserBaseInfo(String userid){
		UserInfoVo uservo = new UserInfoVo();
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("user_id", userid);
		List<UserInfo> datalist = usrMgrDao.queryUserInfo(paramap);
		if(datalist != null){
			UserInfo info = (UserInfo)datalist.get(0);
			uservo.setAcc_balance(AppUtil.numFormat(info.getAcc_balance(), 2));
			uservo.setAcc_enchash(AppUtil.numFormat(info.getAcc_enchash(), 2));
			uservo.setAcc_freeze(AppUtil.numFormat(info.getAcc_freeze(), 2));
			uservo.setAcc_status(info.getAcc_status());
			uservo.setAcc_statusname(info.getAcc_statusname());
			uservo.setBank_acc_code(info.getBank_acc_code());
			uservo.setBank_name(info.getBank_name());
			uservo.setCertif_no(info.getCertif_no());
			uservo.setCertif_type(info.getCertif_type());
			uservo.setCreate_date(info.getCreate_date());
			uservo.setErr_count(info.getErr_count());
			uservo.setMail(info.getMail());
			uservo.setPhone(info.getPhone());
			uservo.setRecod_phone(info.getRecod_phone());
			uservo.setSecty_level(info.getSecty_level());
			uservo.setSecty_levelname(info.getSecty_levelname());
			uservo.setUpdate_date(info.getUpdate_date());
			uservo.setUser_code(info.getUser_cde());
			uservo.setUser_credit(info.getUser_credit());
			uservo.setUser_id(info.getUser_id());
			uservo.setUser_name(info.getUser_name());
			uservo.setUser_nickname(info.getUser_nickname());
			uservo.setUser_status(info.getUser_status());
			uservo.setUser_statusname(info.getUser_statusname());
			uservo.setUser_type(info.getUser_type());
			uservo.setUser_typename(info.getUser_typename());
		}
		return uservo;
	}
	public UsrMgrDao getUsrMgrDao() {
		return usrMgrDao;
	}

	public void setUsrMgrDao(UsrMgrDao usrMgrDao) {
		this.usrMgrDao = usrMgrDao;
	}
}
