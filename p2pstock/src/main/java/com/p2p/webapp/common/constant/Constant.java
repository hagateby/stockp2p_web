package com.p2p.webapp.common.constant;

public class Constant {
	//ϵͳ��·��
	public static String webContontRoot = "/p2pstock";
	//Ĭ�Ϲ����˵�
	public static String menu_default = "INDEXPAGE,MYACOUNT,";
	//session ����
	private static String session_secparam ="secParamStr";
	/*      ƽ̨�����           */
	//�Ƿ�֧�ֺ󸶷� 0 �� 1��
	private static String pay_flag = "1";
	//���������ˣ������������ռ��action�����е�¼����
	private static String bizinterceptfilter = "demo,regist,login,common,ajax";
	//��ҳ����-ÿҳ����
	private static String pagesize = "10";
	//�ֻ���֤������ģ��
	private static String cptModulStr = "��֤��#x�������ڽ���ע�ᣬ�������κ����ṩ���յ��Ķ�����֤�� ��Ħ��������";
	//������֤����Чʱ��
	private static String cptActiveTime = "60";
	//������֤�뿪�� 0 ��ʵ 1 ģ�� 111111
	private static String cptnoflag = "1";
	//���ŷ��Ϳ��� 0 ��ʵ���� 1 ģ�ⷢ��
	private static String cptflag = "1";
	//���ŷ��͵�ַ
	private static String cptsendadress = "http://dx.10659com.com:83/ApiService.asmx/Send";
	//���Ŷ�Ȳ�ѯ��ַ
	private static String cptqueryadress = "http://dx.10659com.com:83/ApiService.asmx/GetYuE";
	//�����˺�
	private static String cptaccount = "moerlifang";
	//����
	private static String cptpwd = "789789";
	//��Ʒ����
	private static String productid = "15";
	
	//session atrribute
	private static String session_userid = "userid";
	private static String session_username = "username";
	private static String session_phone = "phone";
	private static String session_usertyp = "usertyp";
	private static String session_mymenulist = "mymenu";
	private static String request_menutree = "menutree";
	/*      �û������           */
	//�û�������Ϣ�� �û�״̬ A ��Ч N��Ч C���� 
	private static String user_status_active = "A";
	private static String user_status_stop = "N";
	private static String user_status_cold = "C";
	private static String user_typ_custom = "G";
	private static String user_typ_manager = "A";
	//�û��˻�����
	//�ֽ�
	private static String user_acctyp_cash = "C";
	//�˻�״̬
	//����
	private static String user_accstatus_act = "0";
	//����
	private static String user_accstatus_cold = "1";
	//ע��
	private static String user_accstatus_off = "2";
	//��¼����ʧ�ܴ���
	private static int user_loginerrr_count = 5;
	//�û�����״̬ 0�ɹ� 1ʧ��
	private static String user_optstatus_succ = "0";
	private static String user_optstatus_err = "1";
	/*      ���������           */
	//�û��������� 
	//��¼
	private static String user_opttyp_login = "0";
	//����
	private static String user_opttyp_buy = "1";
	//��ֵ
	private static String user_opttyp_recharge = "2";
	//����
	private static String user_opttyp_wdcash = "3";
	//���㱣֤��
	private static String user_opttyp_settlebail = "4";
	//��������
	private static String user_opttyp_settlecharge = "5";
	//�������󣨽���ǰ�û�ʵ������
	private static String user_opttyp_settleprofitprv = "6";
	//�������󣨽�����û�Ӧ������
	private static String user_opttyp_settleprofitaf = "7";	
	//����
	private static String user_opttyp_correct = "99";	
	//�ո���ʽ
	private static String tran_paytyp = "PAYTYP";
	//��
	private static String tran_paytyp_pay = "0";
	//��
	private static String tran_paytyp_rec = "1";
	//�޽��䶯
	private static String tran_paytyp_none = "2";
	//Ͷ��״̬
	private static String invest_status = "INVESTSTATUS";
	//�ݴ�
	private static String invest_status_tmp = "0";
	//¼����ɴ�����
	private static String invest_status_arr = "1";	
	//�Ѹ���
	private static String invest_status_feeok = "2";
	//�ѽ���
	private static String invest_status_ok = "3";
	//����������
	private static String invest_status_proftok = "4";
	//�ѿ�ǩ
	private static String invest_status_lovok = "5";
	//��ɾ��
	private static String invest_status_del = "9";
	
	/*      �Ƿ�����������           */
	private static String settle_status_no = "0";
	private static String settle_status_ok = "1";
	
	
	/*      ��Ʒ�����           */
	private static String product_typ = "PROTYP";
	private static String product_city = "CITY";
	private static String product_investtyp = "INVESTTYP";
	private static String product_accchargetyp = "ACCCHARGETYP";
	//������
	private static String product_accchargetyp_scale = "0";
	//�����
	private static String product_accchargetyp_count = "1";
	/*      Ͷ�����          */
	//Ͷ�ʲ�Ʒ״̬
	private static String investprt_status = "INVESTPRTSTATUS";
	//��Ͷ��
	private static String investprt_status_avl = "0";
	//δ����
	private static String investprt_status_arr = "1";
	//�ѽ���
	private static String investprt_status_setl = "2";
	//�ѿ�ǩ
	private static String investprt_status_lov = "3";
	//���������
	private static String investprt_status_seting = "4";
	//����������
	private static String investprt_status_setok = "5";
	//���㸶��״̬
	private static String settlepay_typ = "SETTLESTATUS";
	//δ���
	private static String settlepay_status_no = "0";
	//�����
	private static String settlepay_status_ok = "1";
	//Ĭ�ϲ�Ʒ����
	private static String product_typ_default = "PROTYP01";
	private static String product_city_default = "CITY001";
	//ƽ̨����Ա�˻�
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
