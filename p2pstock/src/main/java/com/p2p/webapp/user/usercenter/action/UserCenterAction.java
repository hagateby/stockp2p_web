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
 * 用户中心
 * @author Administrator
 *
 */
public class UserCenterAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//写日志对象
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
	//我的投资
	private List<InvestVo> investlist;
	private Page page;
	//我的历史投资
	private List<UserSettleVo> settlelist;
	private String queryuserid;
	
	/*
	 * 我的账户按钮页面跳转 
	 * @return
	 */
	public String userInit(){
		logger.debug("用户查询开始");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		userInfoVo = userCenterService.queryUserInfo(userid);
		ParamMgrService ps= new ParamMgrServiceImpl();
		SystemParam sp = ps.querySystemByCode("SEC01");
		String secty_leveldis = sp.getPara_name();
		userInfoVo.setSecty_levelname(secty_leveldis);
		accountInfoVo = fundMgrService.queryAccountInfo(userid);
		logger.debug("secty_leveldis："+secty_leveldis);
		logger.debug("用户查询结束");
		return "userininit";
	}
	/*
	 * 用户基本信息维护
	 * @return
	 */
	public String userEdit(){
		logger.debug("用户查询开始");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		userInfoVo = userCenterService.queryUserInfo(userid);
		ParamMgrService ps= new ParamMgrServiceImpl();
		SystemParam sp = ps.querySystemByCode("SEC01");
		String secty_leveldis = sp.getPara_name();
		userInfoVo.setSecty_levelname(secty_leveldis);
		logger.debug("secty_leveldis："+secty_leveldis);
		logger.debug("用户查询结束");
		return "useredit";
	}
	/*
	 * 保存用户
	 */
	public String userSave(){
		logger.debug("用户保存开始");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		userInfoVo.setUser_id(userid);
		userCenterService.saveUserinfo(userInfoVo);
		//更新session中的用户名
		if(userInfoVo.getUser_nickname() != null && !"".equals(userInfoVo.getUser_nickname())){
			request.getSession().setAttribute("username", userInfoVo.getUser_nickname());
		}else if(userInfoVo.getUser_name() != null && !"".equals(userInfoVo.getUser_name())){
			request.getSession().setAttribute("username", userInfoVo.getUser_name());
		}
		msgCode = "MSG0008";
		logger.debug("用户保存结束");
		return "saveok";
	}
	/*
	 * 银行信息维护初始化
	 */
	public String bankEdit(){
		logger.debug("银行初始化开始");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		userInfoVo = userCenterService.queryUserInfo(userid);
		if(userInfoVo.getUser_name() == null || "".equals(userInfoVo.getUser_name())){
			msgCode = "MSG0004";
			return "bankiniterror";
		}
		//获取客户银行卡信息
		userBankList = new ArrayList<UserBankVo>();
		userBankList = userCenterService.queryBankInfo(userid);
		banklist = new ArrayList<SystemParam>();
		ParamMgrService ps= new ParamMgrServiceImpl();
		banklist = ps.querySystemByTyp("BANK");
		logger.debug("银行初始化结束");
		return "bankinit";
	}
	/*
	 * 银行信息查询
	 */
	public String bankQuery(){
		logger.debug("银行信息查询开始");
		//获取客户银行卡信息
		userBankList = new ArrayList<UserBankVo>();
		userBankList = userCenterService.queryBankInfo(queryuserid);
		logger.debug("银行信息查询结束");
		return "bankQueryOk";
	}
	
	/*
	 * 增加银行卡信息
	 */
	public String bankAdd(){
		logger.debug("增加银行卡信息开始");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		//验证手机验证码
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
		logger.debug("增加银行卡信息结束");
		return "addbankok";
	}
	/*
	 * 更新银行卡信息
	 */
	public String bankEditInfo(){
		logger.debug("更新银行卡信息开始");
		//验证手机验证码
		msgCode = AppUtil.jugeCpt(session, userbankvo.getCptno());
		if(msgCode != null && !"".equals(msgCode)){
			return "editbankok";
		}
		ParamMgrService ps= new ParamMgrServiceImpl();
		SystemParam sp = ps.querySystemByName(userbankvo.getBank_name());
		userbankvo.setBank_code(sp.getPara_code());
		userCenterService.editBankInfo(userbankvo);
		msgCode = "MSG0006";
		logger.debug("更新银行卡信息结束");
		return "editbankok";
	}
	/*
	 * 删除银行卡信息
	 */
	public String bankDelInfo(){
		logger.debug("删除银行卡信息开始");
		userCenterService.delBankInfo(userbankvo.getUser_bank_acc_id());
		msgCode = "MSG0007";
		logger.debug("删除银行卡信息结束");
		return "delbankok";
	}
	/*
	 * 设置默认银行卡
	 */
	public String setDefaultBank(){
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		userbankvo.setUser_id(userid);
		userCenterService.setDefaultBank(userbankvo);
		return "setDefaultBankok";
	}
	/*
	 *修改登录密码初始化
	 * @return
	 */
	public String loginPwdEdit(){
		logger.debug("修改登录密码初始化开始");
		
		logger.debug("修改登录密码初始化结束");
		return "editpwdok";
	}
	
	/*
	 *修改登录密码提交
	 * @return
	 */
	public String loginPwdEditSave(){
		logger.debug("修改登录密码开始");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		if(!userCenterService.checkUserLogin(userid,userInfoVo.getLogin_pwd())){
			msgCode = "ERR0001";
			return "editpwdok";
		}
		userInfoVo.setUser_id(userid);
		userCenterService.editUserLoginPwd(userInfoVo);
		msgCode = "MSG0001";
		logger.debug("修改登录密码结束");
		return "editpwdok";
	}
	
	/*
	 *修改交易密码初始化
	 * @return
	 */
	public String tranPwdEdit(){
		logger.debug("修改交易密码初始化开始");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		UserInfoVo  uvo = new UserInfoVo();
		uvo = userCenterService.queryUserInfo(userid);
		if(uvo.getTran_pwd() == null || "".equals(uvo.getTran_pwd())){
			//设置密码
			tranflag = "0";
		}else{
			//修改密码
			tranflag = "1";
		}
		logger.debug("修改交易密码初始化结束");
		return "edittranpwdok";
	}
	/*
	 * 设置交易密码
	 */
	public String tranPwdSetSub(){
		logger.debug("设置交易密码开始");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		userInfoVo.setUser_id(userid);
		userCenterService.editUserTranPwd(userInfoVo);
		msgCode = "MSG0002";
		logger.debug("设置交易密码结束");
		return "edittranpwdsubok";
	}
	/*
	 * 修改交易密码
	 */
	public String tranPwdEditSub(){
		logger.debug("修改交易密码开始");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		//获取用户原交易密码
		UserInfoVo  uvo = new UserInfoVo();
		uvo = userCenterService.queryUserInfo(userid);
		//比较是否一致
		if(!userInfoVo.getTran_pwd().equals(uvo.getTran_pwd())){
			msgCode = "ERR0002";
			return "edittranpwdok";
		}
		userInfoVo.setUser_id(userid);
		userCenterService.editUserTranPwd(userInfoVo);
		msgCode = "MSG0003";
		logger.debug("修改交易密码结束");
		return "edittranpwdsubok";
	}
	/*
	 * 我的投资
	 */
	public String queryMyInvest(){
		logger.debug("查询我的投资");
		//分页
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		String user_id = (String)request.getSession().getAttribute(Constant.getSession_userid());
		investMgrVo.setUser_id(user_id);
		investlist = investMgrService.queryInvestNow(page,investMgrVo);
		
		logger.debug("查询我的投资");
		return "myinvest";
	}
	/*
	 * 我的历史投资
	 */
	public String queryMyInvestHis(){
		logger.debug("查询我的历史投资");
		//分页
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		String user_id = (String)request.getSession().getAttribute(Constant.getSession_userid());
		settlelist = userCenterService.queryMyInvestHis(page,user_id);
		logger.debug("查询我的历史投资");
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
