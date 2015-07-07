package com.p2p.webapp.common.constant;

public class Constant {
	//系统根路径
	public static String webContontRoot = "/p2pstock";
	//默认公共菜单
	public static String menu_default = "INDEXPAGE,MYACOUNT,";
	//session 变量
	private static String session_secparam ="secParamStr";
	/*      平台类变量           */
	//是否支持后付费 0 否 1是
	private static String pay_flag = "1";
	//拦截器过滤，对以下命名空间的action不进行登录拦截
	private static String bizinterceptfilter = "demo,regist,login,common,ajax";
	//分页参数-每页条数
	private static String pagesize = "10";
	//手机验证码内容模板
	private static String cptModulStr = "验证码#x，您正在进行注册，请勿向任何人提供您收到的短信验证码 【摩尔立方】";
	//短信验证码有效时间
	private static String cptActiveTime = "60";
	//短信验证码开关 0 真实 1 模拟 111111
	private static String cptnoflag = "1";
	//短信发送开关 0 真实发送 1 模拟发送
	private static String cptflag = "1";
	//短信发送地址
	private static String cptsendadress = "http://dx.10659com.com:83/ApiService.asmx/Send";
	//短信额度查询地址
	private static String cptqueryadress = "http://dx.10659com.com:83/ApiService.asmx/GetYuE";
	//短信账号
	private static String cptaccount = "moerlifang";
	//密码
	private static String cptpwd = "789789";
	//产品编码
	private static String productid = "15";
	
	//session atrribute
	private static String session_userid = "userid";
	private static String session_username = "username";
	private static String session_phone = "phone";
	private static String session_usertyp = "usertyp";
	private static String session_mymenulist = "mymenu";
	private static String request_menutree = "menutree";
	/*      用户类变量           */
	//用户基本信息表 用户状态 A 有效 N无效 C冻结 
	private static String user_status_active = "A";
	private static String user_status_stop = "N";
	private static String user_status_cold = "C";
	private static String user_typ_custom = "G";
	private static String user_typ_manager = "A";
	//用户账户类型
	//现金
	private static String user_acctyp_cash = "C";
	//账户状态
	//正常
	private static String user_accstatus_act = "0";
	//冻结
	private static String user_accstatus_cold = "1";
	//注销
	private static String user_accstatus_off = "2";
	//登录锁定失败次数
	private static int user_loginerrr_count = 5;
	//用户操作状态 0成功 1失败
	private static String user_optstatus_succ = "0";
	private static String user_optstatus_err = "1";
	/*      交易类变量           */
	//用户操作类型 
	//登录
	private static String user_opttyp_login = "0";
	//购买
	private static String user_opttyp_buy = "1";
	//充值
	private static String user_opttyp_recharge = "2";
	//提现
	private static String user_opttyp_wdcash = "3";
	//结算保证金
	private static String user_opttyp_settlebail = "4";
	//结算管理费
	private static String user_opttyp_settlecharge = "5";
	//结算利润（结算前用户实得利润）
	private static String user_opttyp_settleprofitprv = "6";
	//结算利润（结算后用户应得利润）
	private static String user_opttyp_settleprofitaf = "7";	
	//冲正
	private static String user_opttyp_correct = "99";	
	//收付方式
	private static String tran_paytyp = "PAYTYP";
	//付
	private static String tran_paytyp_pay = "0";
	//收
	private static String tran_paytyp_rec = "1";
	//无金额变动
	private static String tran_paytyp_none = "2";
	//投资状态
	private static String invest_status = "INVESTSTATUS";
	//暂存
	private static String invest_status_tmp = "0";
	//录入完成待付款
	private static String invest_status_arr = "1";	
	//已付费
	private static String invest_status_feeok = "2";
	//已结算
	private static String invest_status_ok = "3";
	//利润分配完成
	private static String invest_status_proftok = "4";
	//已开签
	private static String invest_status_lovok = "5";
	//已删除
	private static String invest_status_del = "9";
	
	/*      是否完成利润分配           */
	private static String settle_status_no = "0";
	private static String settle_status_ok = "1";
	
	
	/*      产品类变量           */
	private static String product_typ = "PROTYP";
	private static String product_city = "CITY";
	private static String product_investtyp = "INVESTTYP";
	private static String product_accchargetyp = "ACCCHARGETYP";
	//按比例
	private static String product_accchargetyp_scale = "0";
	//按金额
	private static String product_accchargetyp_count = "1";
	/*      投资相关          */
	//投资产品状态
	private static String investprt_status = "INVESTPRTSTATUS";
	//可投资
	private static String investprt_status_avl = "0";
	//未交费
	private static String investprt_status_arr = "1";
	//已结算
	private static String investprt_status_setl = "2";
	//已开签
	private static String investprt_status_lov = "3";
	//利润分配中
	private static String investprt_status_seting = "4";
	//利润分配完成
	private static String investprt_status_setok = "5";
	//结算付款状态
	private static String settlepay_typ = "SETTLESTATUS";
	//未完成
	private static String settlepay_status_no = "0";
	//已完成
	private static String settlepay_status_ok = "1";
	//默认产品类型
	private static String product_typ_default = "PROTYP01";
	private static String product_city_default = "CITY001";
	//平台管理员账户
	private static String plat_admin_acount = "0";
	private static String plat_admin_userid = "0";
	
	
	
	public static String getCptModulStr() {
		return cptModulStr;
	}
	public static String getCptActiveTime() {
		return cptActiveTime;
	}
	public static String getCptsendadress() {
		return cptsendadress;
	}
	public static String getCptqueryadress() {
		return cptqueryadress;
	}
	public static String getCptaccount() {
		return cptaccount;
	}
	public static String getCptpwd() {
		return cptpwd;
	}
	public static String getProductid() {
		return productid;
	}
	public static String getCptflag() {
		return cptflag;
	}
	public static String getUser_status_active() {
		return user_status_active;
	}
	public static String getUser_status_stop() {
		return user_status_stop;
	}
	public static String getUser_status_cold() {
		return user_status_cold;
	}
	public static String getUser_typ_custom() {
		return user_typ_custom;
	}
	public static String getUser_typ_manager() {
		return user_typ_manager;
	}
	public static int getUser_loginerrr_count() {
		return user_loginerrr_count;
	}
	public static String getUser_optstatus_succ() {
		return user_optstatus_succ;
	}
	public static String getUser_opttyp_login() {
		return user_opttyp_login;
	}
	public static String getUser_optstatus_err() {
		return user_optstatus_err;
	}
	public static String getBizinterceptfilter() {
		return bizinterceptfilter;
	}
	public static String getSession_userid() {
		return session_userid;
	}
	public static String getSession_username() {
		return session_username;
	}
	public static String getSession_usertyp() {
		return session_usertyp;
	}
	public static String getSession_phone() {
		return session_phone;
	}
	public static String getProduct_typ() {
		return product_typ;
	}
	public static String getProduct_city() {
		return product_city;
	}
	public static String getProduct_typ_default() {
		return product_typ_default;
	}
	public static String getProduct_city_default() {
		return product_city_default;
	}
	public static String getProduct_investtyp() {
		return product_investtyp;
	}
	public static String getProduct_accchargetyp() {
		return product_accchargetyp;
	}
	public static String getPagesize() {
		return pagesize;
	}
	public static String getSession_secparam() {
		return session_secparam;
	}
	public static String getInvestprt_status() {
		return investprt_status;
	}
	public static void setInvestprt_status(String investprtStatus) {
		investprt_status = investprtStatus;
	}
	public static String getInvestprt_status_arr() {
		return investprt_status_arr;
	}
	public static String getInvestprt_status_avl() {
		return investprt_status_avl;
	}
	public static void setInvestprt_status_avl(String investprtStatusAvl) {
		investprt_status_avl = investprtStatusAvl;
	}
	public static String getProduct_accchargetyp_scale() {
		return product_accchargetyp_scale;
	}
	public static String getProduct_accchargetyp_count() {
		return product_accchargetyp_count;
	}
	public static String getInvest_status_arr() {
		return invest_status_arr;
	}
	public static String getUser_opttyp_buy() {
		return user_opttyp_buy;
	}
	public static String getUser_opttyp_recharge() {
		return user_opttyp_recharge;
	}
	public static String getUser_opttyp_wdcash() {
		return user_opttyp_wdcash;
	}
	public static String getTran_paytyp_pay() {
		return tran_paytyp_pay;
	}
	public static String getTran_paytyp_rec() {
		return tran_paytyp_rec;
	}
	public static String getTran_paytyp_none() {
		return tran_paytyp_none;
	}
	public static String getUser_acctyp_cash() {
		return user_acctyp_cash;
	}
	public static String getUser_accstatus_act() {
		return user_accstatus_act;
	}
	public static String getUser_accstatus_cold() {
		return user_accstatus_cold;
	}
	public static String getUser_accstatus_off() {
		return user_accstatus_off;
	}
	public static String getPay_flag() {
		return pay_flag;
	}
	public static String getInvest_status_tmp() {
		return invest_status_tmp;
	}
	public static String getInvest_status_feeok() {
		return invest_status_feeok;
	}
	public static String getInvest_status_ok() {
		return invest_status_ok;
	}
	public static String getInvest_status_del() {
		return invest_status_del;
	}
	public static String getPlat_admin_acount() {
		return plat_admin_acount;
	}
	public static String getPlat_admin_userid() {
		return plat_admin_userid;
	}
	public static String getUser_opttyp_settlebail() {
		return user_opttyp_settlebail;
	}
	public static String getUser_opttyp_settlecharge() {
		return user_opttyp_settlecharge;
	}
	public static String getInvestprt_status_setl() {
		return investprt_status_setl;
	}
	public static String getInvestprt_status_lov() {
		return investprt_status_lov;
	}

	public static String getCptnoflag() {
		return cptnoflag;
	}
	public static String getUser_opttyp_settleprofitprv() {
		return user_opttyp_settleprofitprv;
	}
	public static String getUser_opttyp_settleprofitaf() {
		return user_opttyp_settleprofitaf;
	}
	public static String getSettle_status_no() {
		return settle_status_no;
	}
	public static String getSettle_status_ok() {
		return settle_status_ok;
	}
	public static String getInvestprt_status_seting() {
		return investprt_status_seting;
	}
	public static String getInvestprt_status_setok() {
		return investprt_status_setok;
	}
	public static String getSettlepay_status_no() {
		return settlepay_status_no;
	}
	public static String getSettlepay_status_ok() {
		return settlepay_status_ok;
	}
	public static String getSettlepay_typ() {
		return settlepay_typ;
	}
	public static String getInvest_status_proftok() {
		return invest_status_proftok;
	}
	public static String getTran_paytyp() {
		return tran_paytyp;
	}
	public static String getUser_opttyp_correct() {
		return user_opttyp_correct;
	}
	public static String getInvest_status() {
		return invest_status;
	}
	public static String getInvest_status_lovok() {
		return invest_status_lovok;
	}
	public static String getSession_mymenulist() {
		return session_mymenulist;
	}
	public static String getRequest_menutree() {
		return request_menutree;
	}
	public static String getMenu_default() {
		return menu_default;
	}


}
