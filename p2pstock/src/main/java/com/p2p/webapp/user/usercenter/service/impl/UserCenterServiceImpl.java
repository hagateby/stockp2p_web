package com.p2p.webapp.user.usercenter.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.sysmgr.pubresultmgr.dao.PubResultMgrDao;
import com.p2p.webapp.sysmgr.pubresultmgr.entity.UserSettle;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.UserSettleVo;
import com.p2p.webapp.user.login.entity.UserInfo;
import com.p2p.webapp.user.usercenter.dao.UserCenterDao;
import com.p2p.webapp.user.usercenter.entity.UserBank;
import com.p2p.webapp.user.usercenter.service.UserCenterService;
import com.p2p.webapp.user.usercenter.vo.UserBankVo;
import com.p2p.webapp.user.usercenter.vo.UserInfoVo;

public class UserCenterServiceImpl implements UserCenterService{
	
	private UserCenterDao userCenterDao;
	private PubResultMgrDao pubResultMgrDao;
	/*
	 * 查询用户基本信息
	 * 
	 */
	public UserInfoVo queryUserInfo(String userid) {
		UserInfoVo udto = new UserInfoVo();
		// TODO Auto-generated method stub
		UserInfo uinfo = userCenterDao.queryUserById(userid);
		udto.setCertif_no(uinfo.getCertif_no());
		udto.setCertif_type(uinfo.getCertif_type());
		udto.setCreate_date(uinfo.getCreate_date());
		udto.setErr_count(uinfo.getErr_count());
		udto.setLogin_pwd(uinfo.getLogin_pwd());
		udto.setMail(uinfo.getMail());
		udto.setPhone(uinfo.getPhone());
		udto.setRecod_phone(uinfo.getRecod_phone());
		udto.setSecty_level(uinfo.getSecty_level());
		udto.setTran_pwd(uinfo.getTran_pwd());
		udto.setUpdate_date(uinfo.getUpdate_date());
		udto.setUser_code(uinfo.getUser_cde());
		udto.setUser_id(uinfo.getUser_id());
		udto.setUser_name(uinfo.getUser_name());
		udto.setUser_nickname(uinfo.getUser_nickname());
		udto.setUser_status(uinfo.getUser_status());
		udto.setUser_type(uinfo.getUser_type());
		udto.setUser_credit(uinfo.getUser_credit());
		return udto;
	}

	/*
	 * 保存用户基本信息
	 */
	@SuppressWarnings("unchecked")
	public String saveUserinfo(UserInfoVo userInfoVo) {
		// TODO Auto-generated method stub
		Map paramMap = new HashMap();
		paramMap.put("user_cde", userInfoVo.getUser_code());
		paramMap.put("user_name", userInfoVo.getUser_name());
		paramMap.put("user_nickname", userInfoVo.getUser_nickname());
		paramMap.put("mail", userInfoVo.getMail());
		paramMap.put("certif_type", userInfoVo.getCertif_type());
		paramMap.put("certif_no", userInfoVo.getCertif_no());
		paramMap.put("recod_phone", userInfoVo.getRecod_phone());
		paramMap.put("userid", userInfoVo.getUser_id());
		userCenterDao.updateUserById(paramMap);
		return "0";
	}
	/*
	 * 查询用户银行卡信息
	 * 
	 */
	public List<UserBankVo> queryBankInfo(String userid){
		List<UserBankVo> returnlist =  new ArrayList<UserBankVo>();
		List<UserBank> lu = userCenterDao.queryBankInfo(userid);
		if(lu != null && lu.size() > 0){
			for(int i=0;i<lu.size();i++){
				UserBankVo ubvo = new UserBankVo();
				UserBank ub = lu.get(i);
				ubvo.setBank_acc_code(ub.getBank_acc_code());
				ubvo.setBank_branch_name(ub.getBank_branch_name());
				ubvo.setBank_code(ub.getBank_code());
				ubvo.setBank_name(ub.getBank_name());
				ubvo.setCreate_date(ub.getCreate_date());
				ubvo.setDefault_flag(ub.getDefault_flag());
				ubvo.setUpdate_date(ub.getUpdate_date());
				ubvo.setUser_bank_acc_id(ub.getUser_bank_acc_id());
				ubvo.setUser_id(ub.getUser_id());
				returnlist.add(ubvo);
			}
		}
		return returnlist;
	}
	/*
	 * 增加银行卡信息
	 */
	public void addBankInfo(UserBankVo ubvo){
		String default_flag = "0";
		//判断是否维护过银行卡
		List<UserBank> lu = userCenterDao.queryBankInfo(ubvo.getUser_id());
		if(lu==null || lu.size() == 0){
			//设为默认
			default_flag = "1";
		}else{
			default_flag = "0";
		}
		Map<String,Object> parammap = new HashMap<String,Object>();
		parammap.put("user_id", ubvo.getUser_id());
		parammap.put("bank_code", ubvo.getBank_code());
		parammap.put("bank_name", ubvo.getBank_name());
		parammap.put("bank_branch_name", ubvo.getBank_branch_name());
		parammap.put("bank_acc_code", ubvo.getBank_acc_code());
		parammap.put("default_flag", default_flag);
		userCenterDao.insertBankInfo(parammap);
	}
	/*
	 * 设置默认银行卡
	 */
	public void setDefaultBank(UserBankVo ubvo){
		//更新用户所有卡为否
		Map<String,Object> parammap = new HashMap<String,Object>();
		parammap.put("user_id", ubvo.getUser_id());
		userCenterDao.updateUserBankDefaultNo(parammap);
		//设置默认
		parammap.put("user_bank_acc_id", ubvo.getUser_bank_acc_id());
		userCenterDao.updateUserBankDefault(parammap);
	}
	
	/*
	 * 更新银行卡信息
	 */
	@SuppressWarnings("unchecked")
	public void editBankInfo(UserBankVo ubvo){
		Map parammap = new HashMap();
		parammap.put("user_bank_acc_id", ubvo.getUser_bank_acc_id());
		parammap.put("user_id", ubvo.getUser_id());
		parammap.put("bank_code", ubvo.getBank_code());
		parammap.put("bank_name", ubvo.getBank_name());
		parammap.put("bank_branch_name", ubvo.getBank_branch_name());
		parammap.put("bank_acc_code", ubvo.getBank_acc_code());
		userCenterDao.updateBankInfo(parammap);
	}
	/*
	 * 删除银行卡信息
	 */
	public void delBankInfo(String user_bank_acc_id){
		userCenterDao.delBankInfo(user_bank_acc_id);
	}
	/*
	 * 修改客户登录密码
	 */
	public void editUserLoginPwd(UserInfoVo uvo){
		String user_id = uvo.getUser_id();
		String login_pwd = uvo.getNewlogin_pwd();
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("user_id", user_id);
		paramap.put("login_pwd", login_pwd);
		userCenterDao.updateLoginPwd(paramap);
	}
	/*
	 * 验证客户登录密码
	 */
	public boolean checkUserLogin(String user_id,String login_pwd){
		
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("user_id", user_id);
		paramap.put("login_pwd", login_pwd);
		String userid = userCenterDao.queryUserByIdPd(paramap);
		if(userid != null && !"".equals(userid)){
			return true;
		}else{
			return false;
		}
	}
	/*
	 * 修改客户交易密码
	 */
	public void editUserTranPwd(UserInfoVo uvo){
		String user_id = uvo.getUser_id();
		String tran_pwd = uvo.getNewtran_pwd();
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("user_id", user_id);
		paramap.put("tran_pwd", tran_pwd);
		userCenterDao.updateTranPwd(paramap);
	}
	/*
	 * 查询我的历史投资
	 */
	@SuppressWarnings("unchecked")
	public List<UserSettleVo> queryMyInvestHis(Page page,String userid){
		//查询用户投资信息
		Map umap = new HashMap();
		
		if("1".equals(page.getPageflag())){
			umap = page.getParamMap();
			umap.put("page", page);
		}else{
			umap.put("page", page);
			umap.put("invest_user_id", userid);
			page.setParamMap(umap);
		}
		List<UserSettle> listu = pubResultMgrDao.selectSettleByUserHis(umap);
		List<UserSettleVo> listusvo = new ArrayList<UserSettleVo>();
		for(UserSettle us:listu){
			UserSettleVo uvo = new UserSettleVo();
			uvo.setCreate_date(us.getCreate_date());
			uvo.setInvest_user_id(us.getInvest_user_id());
			uvo.setInvt_product_id(us.getInvt_product_id());
			uvo.setInvt_product_name(us.getInvt_product_name());
			uvo.setTransion_seq(us.getTransion_seq());
			uvo.setUpdate_date(us.getUpdate_date());
			uvo.setUser_invest_profit(AppUtil.numFormat(us.getUser_invest_profit(),2));
			uvo.setUser_invest_profitpay(AppUtil.numFormat(us.getUser_invest_profitpay(),2));
			uvo.setUser_name(us.getUser_name());
			uvo.setCreate_username(us.getCreate_username());
			listusvo.add(uvo);
		}
		return listusvo;
	}
	
	public UserCenterDao getUserCenterDao() {
		return userCenterDao;
	}
	public void setUserCenterDao(UserCenterDao userCenterDao) {
		this.userCenterDao = userCenterDao;
	}

	public PubResultMgrDao getPubResultMgrDao() {
		return pubResultMgrDao;
	}

	public void setPubResultMgrDao(PubResultMgrDao pubResultMgrDao) {
		this.pubResultMgrDao = pubResultMgrDao;
	}



}
