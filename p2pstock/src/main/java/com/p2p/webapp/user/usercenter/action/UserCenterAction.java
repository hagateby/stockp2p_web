package com.p2p.webapp.user.usercenter.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.invest.investmgr.service.InvestMgrService;
import com.p2p.webapp.invest.investmgr.vo.InvestMgrVo;
import com.p2p.webapp.invest.investmgr.vo.InvestVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.UserSettleVo;
import com.p2p.webapp.system.parammgr.entity.SystemParam;
import com.p2p.webapp.system.parammgr.service.ParamMgrService;
import com.p2p.webapp.system.parammgr.service.impl.ParamMgrServiceImpl;
import com.p2p.webapp.user.fundmgr.service.FundMgrService;
import com.p2p.webapp.user.fundmgr.vo.AccountInfoVo;
import com.p2p.webapp.user.usercenter.service.UserCenterService;
import com.p2p.webapp.user.usercenter.vo.UserBankVo;
import com.p2p.webapp.user.usercenter.vo.UserInfoVo;
/**
 * �û�����
 * @author Administrator
 *
 */
public class UserCenterAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//д��־����
	public static Logger logger = LoggerFactory.getLogger(UserCenterAction.class);
	
	//service
	private UserCenterService userCenterService;
	private FundMgrService fundMgrService;
	private InvestMgrService investMgrService;
	//vo
	private UserInfoVo userInfoVo;
	private AccountInfoVo accountInfoVo;
	private InvestMgrVo  investMgrVo;
	//banklist
	private List<SystemParam> banklist;
	//bankinfolist
	private List<UserBankVo> userBankList;
	private UserBankVo userbankvo;
	private String secbankname;
	private String tranflag;
	private String chainflag;
	//�ҵ�Ͷ��
	private List<InvestVo> investlist;
	private Page page;
	//�ҵ���ʷͶ��
	private List<UserSettleVo> settlelist;
	private String queryuserid;
	
	/*
	 * �ҵ��˻���ťҳ����ת 
	 * @return
	 */
	public String userInit(){
		logger.debug("�û���ѯ��ʼ");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		userInfoVo = userCenterService.queryUserInfo(userid);
		ParamMgrService ps= new ParamMgrServiceImpl();
		SystemParam sp = ps.querySystemByCode("SEC01");
		String secty_leveldis = sp.getPara_name();
		userInfoVo.setSecty_levelname(secty_leveldis);
		accountInfoVo = fundMgrService.queryAccountInfo(userid);
		logger.debug("secty_leveldis��"+secty_leveldis);
		logger.debug("�û���ѯ����");
		return "userininit";
	}
	/*
	 * �û�������Ϣά��
	 * @return
	 */
	public String userEdit(){
		logger.debug("�û���ѯ��ʼ");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		userInfoVo = userCenterService.queryUserInfo(userid);
		ParamMgrService ps= new ParamMgrServiceImpl();
		SystemParam sp = ps.querySystemByCode("SEC01");
		String secty_leveldis = sp.getPara_name();
		userInfoVo.setSecty_levelname(secty_leveldis);
		logger.debug("secty_leveldis��"+secty_leveldis);
		logger.debug("�û���ѯ����");
		return "useredit";
	}
	/*
	 * �����û�
	 */
	public String userSave(){
		logger.debug("�û����濪ʼ");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		userInfoVo.setUser_id(userid);
		userCenterService.saveUserinfo(userInfoVo);
		//����session�е��û���
		if(userInfoVo.getUser_nickname() != null && !"".equals(userInfoVo.getUser_nickname())){
			request.getSession().setAttribute("username", userInfoVo.getUser_nickname());
		}else if(userInfoVo.getUser_name() != null && !"".equals(userInfoVo.getUser_name())){
			request.getSession().setAttribute("username", userInfoVo.getUser_name());
		}
		msgCode = "MSG0008";
		logger.debug("�û��������");
		return "saveok";
	}
	/*
	 * ������Ϣά����ʼ��
	 */
	public String bankEdit(){
		logger.debug("���г�ʼ����ʼ");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		userInfoVo = userCenterService.queryUserInfo(userid);
		if(userInfoVo.getUser_name() == null || "".equals(userInfoVo.getUser_name())){
			msgCode = "MSG0004";
			return "bankiniterror";
		}
		//��ȡ�ͻ����п���Ϣ
		userBankList = new ArrayList<UserBankVo>();
		userBankList = userCenterService.queryBankInfo(userid);
		banklist = new ArrayList<SystemParam>();
		ParamMgrService ps= new ParamMgrServiceImpl();
		banklist = ps.querySystemByTyp("BANK");
		logger.debug("���г�ʼ������");
		return "bankinit";
	}
	/*
	 * ������Ϣ��ѯ
	 */
	public String bankQuery(){
		logger.debug("������Ϣ��ѯ��ʼ");
		//��ȡ�ͻ����п���Ϣ
		userBankList = new ArrayList<UserBankVo>();
		userBankList = userCenterService.queryBankInfo(queryuserid);
		logger.debug("������Ϣ��ѯ����");
		return "bankQueryOk";
	}
	
	/*
	 * �������п���Ϣ
	 */
	public String bankAdd(){
		logger.debug("�������п���Ϣ��ʼ");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		//��֤�ֻ���֤��
		msgCode = AppUtil.jugeCpt(session, userbankvo.getCptno());
		if(msgCode != null && !"".equals(msgCode)){
			return "addbankok";
		}
		ParamMgrService ps= new ParamMgrServiceImpl();
		SystemParam sp = ps.querySystemByName(secbankname);
		userbankvo.setBank_code(sp.getPara_code());
		userbankvo.setBank_name(secbankname);
		userbankvo.setUser_id(userid);
		userCenterService.addBankInfo(userbankvo);
		
		msgCode = "MSG0005";
		logger.debug("�������п���Ϣ����");
		return "addbankok";
	}
	/*
	 * �������п���Ϣ
	 */
	public String bankEditInfo(){
		logger.debug("�������п���Ϣ��ʼ");
		//��֤�ֻ���֤��
		msgCode = AppUtil.jugeCpt(session, userbankvo.getCptno());
		if(msgCode != null && !"".equals(msgCode)){
			return "editbankok";
		}
		ParamMgrService ps= new ParamMgrServiceImpl();
		SystemParam sp = ps.querySystemByName(userbankvo.getBank_name());
		userbankvo.setBank_code(sp.getPara_code());
		userCenterService.editBankInfo(userbankvo);
		msgCode = "MSG0006";
		logger.debug("�������п���Ϣ����");
		return "editbankok";
	}
	/*
	 * ɾ�����п���Ϣ
	 */
	public String bankDelInfo(){
		logger.debug("ɾ�����п���Ϣ��ʼ");
		userCenterService.delBankInfo(userbankvo.getUser_bank_acc_id());
		msgCode = "MSG0007";
		logger.debug("ɾ�����п���Ϣ����");
		return "delbankok";
	}
	/*
	 * ����Ĭ�����п�
	 */
	public String setDefaultBank(){
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		userbankvo.setUser_id(userid);
		userCenterService.setDefaultBank(userbankvo);
		return "setDefaultBankok";
	}
	/*
	 *�޸ĵ�¼�����ʼ��
	 * @return
	 */
	public String loginPwdEdit(){
		logger.debug("�޸ĵ�¼�����ʼ����ʼ");
		
		logger.debug("�޸ĵ�¼�����ʼ������");
		return "editpwdok";
	}
	
	/*
	 *�޸ĵ�¼�����ύ
	 * @return
	 */
	public String loginPwdEditSave(){
		logger.debug("�޸ĵ�¼���뿪ʼ");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		if(!userCenterService.checkUserLogin(userid,userInfoVo.getLogin_pwd())){
			msgCode = "ERR0001";
			return "editpwdok";
		}
		userInfoVo.setUser_id(userid);
		userCenterService.editUserLoginPwd(userInfoVo);
		msgCode = "MSG0001";
		logger.debug("�޸ĵ�¼�������");
		return "editpwdok";
	}
	
	/*
	 *�޸Ľ��������ʼ��
	 * @return
	 */
	public String tranPwdEdit(){
		logger.debug("�޸Ľ��������ʼ����ʼ");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		UserInfoVo  uvo = new UserInfoVo();
		uvo = userCenterService.queryUserInfo(userid);
		if(uvo.getTran_pwd() == null || "".equals(uvo.getTran_pwd())){
			//��������
			tranflag = "0";
		}else{
			//�޸�����
			tranflag = "1";
		}
		logger.debug("�޸Ľ��������ʼ������");
		return "edittranpwdok";
	}
	/*
	 * ���ý�������
	 */
	public String tranPwdSetSub(){
		logger.debug("���ý������뿪ʼ");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		userInfoVo.setUser_id(userid);
		userCenterService.editUserTranPwd(userInfoVo);
		msgCode = "MSG0002";
		logger.debug("���ý����������");
		return "edittranpwdsubok";
	}
	/*
	 * �޸Ľ�������
	 */
	public String tranPwdEditSub(){
		logger.debug("�޸Ľ������뿪ʼ");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		//��ȡ�û�ԭ��������
		UserInfoVo  uvo = new UserInfoVo();
		uvo = userCenterService.queryUserInfo(userid);
		//�Ƚ��Ƿ�һ��
		if(!userInfoVo.getTran_pwd().equals(uvo.getTran_pwd())){
			msgCode = "ERR0002";
			return "edittranpwdok";
		}
		userInfoVo.setUser_id(userid);
		userCenterService.editUserTranPwd(userInfoVo);
		msgCode = "MSG0003";
		logger.debug("�޸Ľ����������");
		return "edittranpwdsubok";
	}
	/*
	 * �ҵ�Ͷ��
	 */
	public String queryMyInvest(){
		logger.debug("��ѯ�ҵ�Ͷ��");
		//��ҳ
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		String user_id = (String)request.getSession().getAttribute(Constant.getSession_userid());
		investMgrVo.setUser_id(user_id);
		investlist = investMgrService.queryInvestNow(page,investMgrVo);
		
		logger.debug("��ѯ�ҵ�Ͷ��");
		return "myinvest";
	}
	/*
	 * �ҵ���ʷͶ��
	 */
	public String queryMyInvestHis(){
		logger.debug("��ѯ�ҵ���ʷͶ��");
		//��ҳ
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		String user_id = (String)request.getSession().getAttribute(Constant.getSession_userid());
		settlelist = userCenterService.queryMyInvestHis(page,user_id);
		logger.debug("��ѯ�ҵ���ʷͶ��");
		return "myinvesthis";
	}
	
	
	public UserCenterService getUserCenterService() {
		return userCenterService;
	}
	public void setUserCenterService(UserCenterService userCenterService) {
		this.userCenterService = userCenterService;
	}
	public UserInfoVo getUserInfoVo() {
		return userInfoVo;
	}
	public void setUserInfoVo(UserInfoVo userInfoVo) {
		this.userInfoVo = userInfoVo;
	}
	public List<SystemParam> getBanklist() {
		return banklist;
	}
	public void setBanklist(List<SystemParam> banklist) {
		this.banklist = banklist;
	}
	public List<UserBankVo> getUserBankList() {
		return userBankList;
	}
	public void setUserBankList(List<UserBankVo> userBankList) {
		this.userBankList = userBankList;
	}
	public UserBankVo getUserbankvo() {
		return userbankvo;
	}
	public void setUserbankvo(UserBankVo userbankvo) {
		this.userbankvo = userbankvo;
	}
	public String getSecbankname() {
		return secbankname;
	}
	public void setSecbankname(String secbankname) {
		this.secbankname = secbankname;
	}
	public String getTranflag() {
		return tranflag;
	}
	public void setTranflag(String tranflag) {
		this.tranflag = tranflag;
	}
	public String getChainflag() {
		return chainflag;
	}
	public void setChainflag(String chainflag) {
		this.chainflag = chainflag;
	}
	public FundMgrService getFundMgrService() {
		return fundMgrService;
	}
	public void setFundMgrService(FundMgrService fundMgrService) {
		this.fundMgrService = fundMgrService;
	}
	public AccountInfoVo getAccountInfoVo() {
		return accountInfoVo;
	}
	public void setAccountInfoVo(AccountInfoVo accountInfoVo) {
		this.accountInfoVo = accountInfoVo;
	}
	public InvestMgrVo getInvestMgrVo() {
		return investMgrVo;
	}
	public void setInvestMgrVo(InvestMgrVo investMgrVo) {
		this.investMgrVo = investMgrVo;
	}
	public InvestMgrService getInvestMgrService() {
		return investMgrService;
	}
	public void setInvestMgrService(InvestMgrService investMgrService) {
		this.investMgrService = investMgrService;
	}
	public List<InvestVo> getInvestlist() {
		return investlist;
	}
	public void setInvestlist(List<InvestVo> investlist) {
		this.investlist = investlist;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<UserSettleVo> getSettlelist() {
		return settlelist;
	}
	public void setSettlelist(List<UserSettleVo> settlelist) {
		this.settlelist = settlelist;
	}
	public String getQueryuserid() {
		return queryuserid;
	}
	public void setQueryuserid(String queryuserid) {
		this.queryuserid = queryuserid;
	}

}
