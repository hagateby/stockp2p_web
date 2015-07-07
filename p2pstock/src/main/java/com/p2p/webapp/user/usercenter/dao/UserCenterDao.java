package com.p2p.webapp.user.usercenter.dao;

import java.util.List;
import java.util.Map;
import com.p2p.webapp.user.login.entity.UserInfo;
import com.p2p.webapp.user.usercenter.entity.UserBank;

public interface UserCenterDao {
	
	/*
	 * 查询客户基本信息
	 */
	public UserInfo queryUserById(String userid);
	/*
	 * 更新客户基本信息
	 */
	public void updateUserById(Map<String,String> paramMap);
	/*
	 * 查询客户银行卡信息
	 */
	public List<UserBank> queryBankInfo(String userid);
	/*
	 * 插入客户银行信息
	 */
	public void insertBankInfo(Map<String,Object> map);
	
	/*
	 * 更新客户银行信息
	 */
	public void updateBankInfo(Map<String,Object> map);
	/*
	 * 删除客户银行信息
	 */
	public void delBankInfo(String user_bank_acc_id);
	/*
	 * 更新客户登录密码
	 */
	public void updateLoginPwd(Map<String,Object> paramap);
	/*
	 * 查询用户登录密码输入是否正确
	 */
	public String queryUserByIdPd(Map<String,Object> paramap);
	/*
	 * 更新客户交易密码
	 */
	public void updateTranPwd(Map<String,Object> paramap);
	/*
	 * 设置用户默认银行卡为否 
	 */
	public void updateUserBankDefaultNo(Map<String,Object> paramap);
	/*
	 * 设置用户默认银行卡
	 */
	public void updateUserBankDefault(Map<String,Object> paramap);
}
