package com.p2p.webapp.user.usercenter.service;

import java.util.List;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.UserSettleVo;
import com.p2p.webapp.user.usercenter.vo.UserBankVo;
import com.p2p.webapp.user.usercenter.vo.UserInfoVo;

public interface UserCenterService {
	/*
	 * ��ѯ�û�������Ϣ
	 * 
	 */
	public UserInfoVo queryUserInfo(String userid);
	/*
	 * �����û�������Ϣ
	 */
	public String saveUserinfo(UserInfoVo userInfoVo);
	/*
	 * ��ѯ�û����п���Ϣ
	 * 
	 */
	public List<UserBankVo> queryBankInfo(String userid);
	/*
	 * �������п���Ϣ
	 */
	public void addBankInfo(UserBankVo ubvo);
	/*
	 * �������п���Ϣ
	 */
	public void editBankInfo(UserBankVo ubvo);
	/*
	 * ɾ�����п���Ϣ
	 */
	public void delBankInfo(String user_bank_acc_id);
	/*
	 * �޸Ŀͻ���¼����
	 */
	public void editUserLoginPwd(UserInfoVo uvo);
	/*
	 * ��֤�ͻ���¼����
	 */
	public boolean checkUserLogin(String user_id,String login_pwd);
	
	/*
	 * �޸Ŀͻ���������
	 */
	public void editUserTranPwd(UserInfoVo uvo);
	/*
	 * ��ѯ�ҵ���ʷͶ��
	 */
	public List<UserSettleVo> queryMyInvestHis(Page page,String userid);
	/*
	 * ����Ĭ�����п�
	 */
	public void setDefaultBank(UserBankVo ubvo);
}
