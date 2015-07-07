package com.p2p.webapp.user.usercenter.service;

import java.util.List;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.UserSettleVo;
import com.p2p.webapp.user.usercenter.vo.UserBankVo;
import com.p2p.webapp.user.usercenter.vo.UserInfoVo;

public interface UserCenterService {
	/*
	 * 查询用户基本信息
	 * 
	 */
	public UserInfoVo queryUserInfo(String userid);
	/*
	 * 保存用户基本信息
	 */
	public String saveUserinfo(UserInfoVo userInfoVo);
	/*
	 * 查询用户银行卡信息
	 * 
	 */
	public List<UserBankVo> queryBankInfo(String userid);
	/*
	 * 增加银行卡信息
	 */
	public void addBankInfo(UserBankVo ubvo);
	/*
	 * 更新银行卡信息
	 */
	public void editBankInfo(UserBankVo ubvo);
	/*
	 * 删除银行卡信息
	 */
	public void delBankInfo(String user_bank_acc_id);
	/*
	 * 修改客户登录密码
	 */
	public void editUserLoginPwd(UserInfoVo uvo);
	/*
	 * 验证客户登录密码
	 */
	public boolean checkUserLogin(String user_id,String login_pwd);
	
	/*
	 * 修改客户交易密码
	 */
	public void editUserTranPwd(UserInfoVo uvo);
	/*
	 * 查询我的历史投资
	 */
	public List<UserSettleVo> queryMyInvestHis(Page page,String userid);
	/*
	 * 设置默认银行卡
	 */
	public void setDefaultBank(UserBankVo ubvo);
}
