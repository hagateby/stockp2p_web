package com.p2p.webapp.sysmgr.pubresultmgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.invest.investmgr.dao.InvestMgrDao;
import com.p2p.webapp.invest.investmgr.entity.Invest;
import com.p2p.webapp.invest.investmgr.entity.InvestDetail;
import com.p2p.webapp.invest.investmgr.service.InvestMgrService;
import com.p2p.webapp.invest.investmgr.vo.InvestPayVo;
import com.p2p.webapp.invest.investmgr.vo.PlatReceiveVo;
import com.p2p.webapp.sysmgr.issuemgr.dao.IssueMgrDao;
import com.p2p.webapp.sysmgr.issuemgr.entity.InvestMoreInfo;
import com.p2p.webapp.sysmgr.pubresultmgr.dao.PubResultMgrDao;
import com.p2p.webapp.sysmgr.pubresultmgr.entity.InvtProductSettleDetail;
import com.p2p.webapp.sysmgr.pubresultmgr.entity.InvtProductSumInfo;
import com.p2p.webapp.sysmgr.pubresultmgr.entity.UserSettle;
import com.p2p.webapp.sysmgr.pubresultmgr.service.PubResultMgrService;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.InvtProductSettleDetailVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.PubResultVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.SettleHisVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.SettleMoreInfoVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.SettleVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.UserSettleVo;

public class PubResultMgrServiceImpl implements PubResultMgrService{

	private PubResultMgrDao pubResultMgrDao;
	private IssueMgrDao issueMgrDao;
	private InvestMgrDao investMgrDao;
	private InvestMgrService investMgrService;
	public static Logger logger = LoggerFactory.getLogger(PubResultMgrServiceImpl.class);
	
	/*
	 * ����ƷͶ��״̬
	 */
	public String checkInvestAjax(String invest_prt_id){
		
		//�ж��ǹ�������ЧͶ��
		List<Invest> listactive = pubResultMgrDao.selectActiveInvest(invest_prt_id);
		if(listactive == null || listactive.size() == 0){
			return "1";
		}
		//�ж��Ƿ����δ���Ͷ��
		List<Invest> listuncheck = pubResultMgrDao.selectUnCheckInvest(invest_prt_id);
		if(listuncheck != null && listuncheck.size() >0){
			return "2";
		}
		return "0";
	}
	
	
	/*
	 * ������ǩ���
	 */
	public String pubResultNo(PubResultVo pubResultVo){
		//��ȡ�����������
		String basic_product_idarray = pubResultVo.getBasic_product_idarray();
		String result_noarray = pubResultVo.getResult_noarray();
		String barray[] = basic_product_idarray.split(";");
		String rarray[] = result_noarray.split(";");
		for(int i=0; i<barray.length;i++){
			if(barray[i] == null || "".equals(barray[i])){
				break;
			}
			//���»������״̬
			Map<String,Object> paramap = new HashMap<String,Object>();
			paramap.put("result_no",rarray[i]);
			paramap.put("basic_product_id",barray[i]);
			pubResultMgrDao.updateResultNo(paramap);
		}
		//������ǩ�����
		createLovNo(pubResultVo.getInvt_product_id());
		//�ж��Ƿ�Ĳ�Ʒ���й���������Ķ��ѿ�ǩ
		List<InvestMoreInfo> listmore = issueMgrDao.selectInvestMoreInfo(pubResultVo.getInvt_product_id());
		boolean flag = true;
		for(InvestMoreInfo imf:listmore){
			if(!"1".equals(imf.getResult_flag())){
				flag = false;
				break;
			}
		}
		//����Ͷ�ʲ�Ʒ ״̬��Ͷ��״̬Ϊ�ѿ�ǩ
		if(flag){
			Map<String,Object> pmap = new HashMap<String,Object>();
			pmap.put("invt_product_status", Constant.getInvestprt_status_lov());
			pmap.put("invt_product_id", pubResultVo.getInvt_product_id());
			issueMgrDao.updateInvestProductStatus(pmap);
			Map<String,Object> paramap = new HashMap<String,Object>();
			paramap.put("invest_status", Constant.getInvest_status_lovok());
			paramap.put("invest_prdouct_id", pubResultVo.getInvt_product_id());
			paramap.put("invest_statuspre", Constant.getInvest_status_feeok());
			investMgrDao.updateInvestStatusByInvt(paramap);
			investMgrDao.updateInvestDetailStatusByInvt(paramap);
		}
		return "0";
	}
	/*
	 * Ͷ�ʲ�Ʒ����
	 */
	public Map<String,Object> settleProduct(SettleVo settleVo){
		
		
		//���������֮ǰ�����ظ�����
		String invt_product_id = settleVo.getInvt_product_id();
		pubResultMgrDao.delSettleDetail(invt_product_id);
		pubResultMgrDao.delSettle(invt_product_id);
		Map<String,Object> rmap = new HashMap<String,Object>();
		SettleVo setvo =new SettleVo();
		//�������ID����
		String baeidarray[] = settleVo.getBasic_product_idarray().split(",");
		//�����������������
		String sailpricearry[] = settleVo.getPrice_sailarray().split(",");
		//ͳ��ÿ��Ͷ������д�������ϸ��
		for(int i=0;i<baeidarray.length;i++){
			String baseid = baeidarray[i];
			Map<String,Object> paramap = new HashMap<String,Object>();
			paramap.put("invest_product_id", settleVo.getInvt_product_id());
			paramap.put("basic_product_id", baseid);
			paramap.put("invest_status", Constant.getInvest_status_lovok());
			List<InvestMoreInfo>  involist = issueMgrDao.selectInvestMoreInfoByib(paramap);
			for(InvestMoreInfo invo:involist){
				setvo.setInvt_product_name(invo.getInvt_product_name());
				//Ͷ�ʲ�Ʒid
				String invest_product_id = settleVo.getInvt_product_id();
				//�������id
				String basic_product_id = baseid;
				String detail_id = invo.getDetail_id();
				//��Ʒ�����
				String buy_price = invo.getPrice();
				//��Ʒ������
				String sail_price = sailpricearry[i];
				//��ǩ����
				int lov_count = countLov(detail_id);
				
				if("1".equals(invo.getSales_city())){//����1:1000���
					lov_count = 1000*lov_count;
				}else{
					lov_count = 500*lov_count;
				}

				String product_investbail_all = invo.getAcount_bail();
				//��Ʒ�����ܷ��ö�
				String product_buycount_all = invo.getAcount_prtfee();
				//��Ʒ��Ͷ�ʶ�
				String product_investcount_all = invo.getAmount_all();
				//�û��ñʻ������Ͷ������
				Double userinvestprofit = (Double.parseDouble(sail_price) - Double.parseDouble(buy_price))*lov_count*(1d-0.0015d);
				//����������
				Double product_recivecount_all = userinvestprofit + Double.parseDouble(product_buycount_all);
				//�������Ͷ�ʹ�����ܶ� = Ͷ������xͶ�ʹ���ѱ���
				//String product_investcharge_all = pdc.getAcount_charge();
				//��ȡ�������Ͷ�ʹ������ȡ����
				Double user_accruedcharges_amount = Double.parseDouble(invo.getUser_accruedcharges_amount())/100;			
				Double product_investcharge_all =  (Double.parseDouble(sail_price) - Double.parseDouble(buy_price))*lov_count*user_accruedcharges_amount;

				//�����Ʒ������ϸ�� 
				Map<String,Object> settlemapd = new HashMap<String,Object>();
				settlemapd.put("detail_id", invo.getDetail_id());
				settlemapd.put("invest_user_id", invo.getUser_id());
				settlemapd.put("user_invest_id", invo.getUser_invest_id());
				settlemapd.put("invt_product_id", invest_product_id);
				settlemapd.put("basic_product_id", basic_product_id);
				settlemapd.put("price_prv", buy_price);
				settlemapd.put("price_aft", sail_price);
				settlemapd.put("praise_count", lov_count);
				settlemapd.put("product_investbail_all", product_investbail_all);
				settlemapd.put("product_investcharge_all", product_investcharge_all);
				settlemapd.put("product_buycount_all", product_buycount_all);
				settlemapd.put("product_investcount_all", product_investcount_all);
				settlemapd.put("product_recivecount_all", product_recivecount_all);
				settlemapd.put("user_invest_profit", userinvestprofit);
				settlemapd.put("transion_seq", settleVo.getTranseq());
				pubResultMgrDao.insertSettleDetail(settlemapd);	
				

			}
		}
		//��ѯ�ò�Ʒ����Ͷ�ʶ�
		InvtProductSumInfo allcount = pubResultMgrDao.selectInvtProductSum(settleVo.getInvt_product_id());
		//�������ÿ��Ͷ�ʵ�������䡢�����
		List<InvtProductSettleDetail> uslist = pubResultMgrDao.selectSettleDetailByPrt(settleVo.getInvt_product_id());
		for(InvtProductSettleDetail us:uslist){
			//�������ȡ����
			Double user_accruedcharges_amount =  Double.parseDouble(us.getUser_accruedcharges_amount())/100;
			//����Ͷ�ʹ����
			Double product_buycount_all =  Double.parseDouble(us.getProduct_buycount_all());
			//�ò�Ʒ��Ͷ�ʶ�
			Double all_buy_count = Double.parseDouble(allcount.getProduct_buycount_all());
			//�ò�Ʒ�������
			Double all_invest_profit = Double.parseDouble(allcount.getUser_invest_profit());
			//����Ͷ��Ӧ��������
			Double invest_settle_profit = (product_buycount_all/all_buy_count)*all_invest_profit ;
			//����Ͷ��Ӧ���ɹ����
			Double invest_charge_count = invest_settle_profit*user_accruedcharges_amount;
			Map<String,Object> smap = new HashMap<String,Object>();
			
			smap.put("product_investcharge_all", invest_charge_count);
			smap.put("user_settle_profit", invest_settle_profit);
			smap.put("settle_id", us.getSettle_id());
			//�������ݿ�
			pubResultMgrDao.updateInvestSettle(smap);
		}
		//ÿ��Ͷ���û��Ĺ����ܶͶ������
		List<InvtProductSumInfo> suminfolist = pubResultMgrDao.selectUserProfitSum(settleVo.getInvt_product_id());
		for(InvtProductSumInfo suminfo:suminfolist){
			//���¼���ͻ�Ӧ��������
			Double userprofit = (Double.parseDouble(suminfo.getProduct_buycount_all())/Double.parseDouble(allcount.getProduct_buycount_all()))*Double.parseDouble(allcount.getUser_invest_profit());
			//�����û���������
			Map<String,Object> setlemap = new HashMap<String,Object>();
			setlemap.put("invest_user_id", suminfo.getInvest_user_id());
			setlemap.put("invt_product_id", settleVo.getInvt_product_id());
			setlemap.put("transion_seq", settleVo.getTranseq());
			setlemap.put("user_invest_profitpay", suminfo.getUser_invest_profit());
			setlemap.put("user_invest_profit", userprofit.toString());
			
			setlemap.put("user_count", suminfo.getUser_count());
			setlemap.put("user_investbail_all", suminfo.getUser_investbail_all());
			setlemap.put("user_investcharge_all", suminfo.getUser_investcharge_all());
			setlemap.put("user_buycount_all", suminfo.getUser_buycount_all());
			setlemap.put("user_investcount_all", suminfo.getUser_investcount_all());
			setlemap.put("user_recivecount_all", suminfo.getUser_recivecount_all());
			//�û������ܶ�
			Double user_paycount_all = Double.parseDouble(suminfo.getUser_invest_profit()) - Double.parseDouble(userprofit.toString()) - Double.parseDouble(suminfo.getUser_investbail_all()) + Double.parseDouble(suminfo.getUser_investcharge_all());
			setlemap.put("user_paycount_all", String.valueOf(user_paycount_all));
			pubResultMgrDao.insertProductSettle(setlemap);
		}

		//����Ͷ�ʲ�Ʒ״̬Ϊ�������
		Map<String,Object> smap = new HashMap<String,Object>();
		smap.put("invt_product_status", Constant.getInvestprt_status_setl());
		smap.put("invt_product_id", settleVo.getInvt_product_id());
		issueMgrDao.updateInvestProductStatus(smap);
		//��������Ͷ��״̬Ϊ�������
		Map<String,Object> investmap = new HashMap<String,Object>();
		investmap.put("invest_status", Constant.getInvest_status_ok());
		investmap.put("invest_prdouct_id", settleVo.getInvt_product_id());
		investmap.put("invest_statuspre", Constant.getInvest_status_lovok());
		investMgrDao.updateInvestStatusByInvt(investmap);
		investMgrDao.updateInvestDetailStatusByInvt(investmap);
		
		return rmap;
	}
	/*
	 * �������
	 */
	public String settlePay(String ustl_id){
		//������ˮ��
		String transeq = UUID.randomUUID().toString();
		UserSettle us = pubResultMgrDao.selectSettleById(ustl_id);
		logger.debug("��ˮ��" + transeq + "������俪ʼ");
		
		
		//�û���ֵ�����
		InvestPayVo  payvoI = new InvestPayVo();
		payvoI.setPay_count(us.getUser_investcharge_all());
		payvoI.setTran_paytyp(Constant.getTran_paytyp_rec());
		payvoI.setTransion_seq(transeq);
		payvoI.setUser_id(us.getInvest_user_id());
		payvoI.setUser_opttyp(Constant.getUser_opttyp_recharge());
		investMgrService.investPay(payvoI);
		logger.debug("�û���ֵ����ѣ�"+us.getUser_investcharge_all());		
		//�û�����ѿ۳�
		InvestPayVo  payvoC = new InvestPayVo();
		payvoC.setPay_count(us.getUser_investcharge_all());
		payvoC.setTran_paytyp(Constant.getTran_paytyp_pay());
		payvoC.setTransion_seq(transeq);
		payvoC.setUser_id(us.getInvest_user_id());
		payvoC.setUser_opttyp(Constant.getUser_opttyp_settlecharge());
		investMgrService.investPay(payvoC);
		logger.debug("�û�����ѿ۳���"+us.getUser_investcharge_all());
		//ƽ̨���������
		PlatReceiveVo platReceiveVoC = new PlatReceiveVo();
		platReceiveVoC.setAmount(us.getUser_investcharge_all());
		platReceiveVoC.setPayment_type(Constant.getTran_paytyp_rec());
		platReceiveVoC.setTransion_seq(transeq);
		platReceiveVoC.setTransion_type(Constant.getUser_opttyp_settlecharge());
		investMgrService.platReceive(platReceiveVoC);
		logger.debug("ƽ̨��������ˣ�"+us.getUser_investcharge_all());
		//ƽ̨���������
		PlatReceiveVo platReceiveVoW = new PlatReceiveVo();
		platReceiveVoW.setAmount(us.getUser_investcharge_all());
		platReceiveVoW.setPayment_type(Constant.getTran_paytyp_pay());
		platReceiveVoW.setTransion_seq(transeq);
		platReceiveVoW.setTransion_type(Constant.getUser_opttyp_wdcash());
		investMgrService.platReceive(platReceiveVoW);
		logger.debug("ƽ̨��������֣�"+us.getUser_investcharge_all());

		
		//ƽ̨��֤��۳�
		PlatReceiveVo platReceiveVoB = new PlatReceiveVo();
		platReceiveVoB.setAmount(us.getUser_investbail_all());
		platReceiveVoB.setPayment_type(Constant.getTran_paytyp_pay());
		platReceiveVoB.setTransion_seq(transeq);
		platReceiveVoB.setTransion_type(Constant.getUser_opttyp_settlebail());
		investMgrService.platReceive(platReceiveVoB);
		logger.debug("ƽ̨��֤��۳���"+us.getUser_investbail_all());
		//�û���֤�𷵻�
		InvestPayVo  payvo = new InvestPayVo();
		payvo.setPay_count(us.getUser_investbail_all());
		payvo.setTran_paytyp(Constant.getTran_paytyp_rec());
		payvo.setTransion_seq(transeq);
		payvo.setUser_id(us.getInvest_user_id());
		payvo.setUser_opttyp(Constant.getUser_opttyp_settlebail());
		investMgrService.investPay(payvo);
		logger.debug("�û���֤�𷵻���"+us.getUser_investbail_all());
		
		
		//�û������ֵ���û�ʵ������
		InvestPayVo  payvoIP = new InvestPayVo();
		payvoIP.setPay_count(us.getUser_invest_profitpay());
		payvoIP.setTran_paytyp(Constant.getTran_paytyp_rec());
		payvoIP.setTransion_seq(transeq);
		payvoIP.setUser_id(us.getInvest_user_id());
		payvoIP.setUser_opttyp(Constant.getUser_opttyp_recharge());
		investMgrService.investPay(payvoIP);
		logger.debug("�û������ֵ���û�ʵ�����󣩣�"+ us.getUser_invest_profitpay());
		//�û�����۳����û�ʵ������
		InvestPayVo  payvo2 = new InvestPayVo();
		payvo2.setPay_count(us.getUser_invest_profitpay());
		payvo2.setTran_paytyp(Constant.getTran_paytyp_pay());
		payvo2.setTransion_seq(transeq);
		payvo2.setUser_id(us.getInvest_user_id());
		payvo2.setUser_opttyp(Constant.getUser_opttyp_settleprofitprv());
		investMgrService.investPay(payvo2);
		logger.debug("�û�����۳����û�ʵ�����󣩣�"+ us.getUser_invest_profitpay());
		//ƽ̨�������ˣ��û�ʵ������
		PlatReceiveVo platReceiveVoP = new PlatReceiveVo();
		platReceiveVoP.setAmount(us.getUser_invest_profitpay());
		platReceiveVoP.setPayment_type(Constant.getTran_paytyp_rec());
		platReceiveVoP.setTransion_seq(transeq);
		platReceiveVoP.setTransion_type(Constant.getUser_opttyp_settleprofitprv());
		investMgrService.platReceive(platReceiveVoP);
		logger.debug("ƽ̨�������ˣ��û�ʵ�����󣩣�"+us.getUser_invest_profitpay());
		
		
		//ƽ̨����۳����û�Ӧ������
		PlatReceiveVo platReceiveVoPA = new PlatReceiveVo();
		platReceiveVoPA.setAmount(us.getUser_invest_profit());
		platReceiveVoPA.setPayment_type(Constant.getTran_paytyp_pay());
		platReceiveVoPA.setTransion_seq(transeq);
		platReceiveVoPA.setTransion_type(Constant.getUser_opttyp_settleprofitaf());
		investMgrService.platReceive(platReceiveVoPA);
		logger.debug("ƽ̨����۳����û�Ӧ�����󣩣�"+us.getUser_invest_profit());
		//�û��������ˣ��û�Ӧ������
		InvestPayVo  payvopa = new InvestPayVo();
		payvopa.setPay_count(us.getUser_invest_profit());
		payvopa.setTran_paytyp(Constant.getTran_paytyp_rec());
		payvopa.setTransion_seq(transeq);
		payvopa.setUser_id(us.getInvest_user_id());
		payvopa.setUser_opttyp(Constant.getUser_opttyp_settleprofitaf());
		investMgrService.investPay(payvopa);
		logger.debug("�û��������ˣ��û�Ӧ�����󣩣�"+ us.getUser_invest_profit());
		//�û��������֣��û�Ӧ������
		InvestPayVo  payvoWP = new InvestPayVo();
		payvoWP.setPay_count(us.getUser_invest_profit());
		payvoWP.setTran_paytyp(Constant.getTran_paytyp_pay());
		payvoWP.setTransion_seq(transeq);
		payvoWP.setUser_id(us.getInvest_user_id());
		payvoWP.setUser_opttyp(Constant.getUser_opttyp_wdcash());
		investMgrService.investPay(payvoWP);
		logger.debug("�û��������֣��û�Ӧ�����󣩣�"+ us.getUser_invest_profit());
		
		//���½���״̬���������������ˮ
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("profit_seq", transeq);
		paramap.put("ustl_id", ustl_id);
		pubResultMgrDao.updateSettleStatus(paramap);
		//�ж��Ƿ�ò�Ʒ�����漰�û���������������
		Map<String,Object> paramap2 = new HashMap<String,Object>();
		paramap2.put("invt_product_id", us.getInvt_product_id());
		List<UserSettle> ulist = pubResultMgrDao.selectSettleByPrt(paramap2);
		boolean flag = true;
		String prtstatus = "";
		for(UserSettle userSettle:ulist){
			if(Constant.getSettle_status_no().equals(userSettle.getSettle_flag())){
				flag = false;
				break;
			}
		}
		//���ж��ѷ�����ɡ�����²�Ʒ״̬Ϊ����������
		if(flag){
			prtstatus = Constant.getInvestprt_status_setok();
		}else{
			prtstatus =  Constant.getInvestprt_status_seting();
		}
		Map<String,Object> pmap = new HashMap<String,Object>();
		pmap.put("invt_product_status", prtstatus);
		pmap.put("invt_product_id", us.getInvt_product_id());
		issueMgrDao.updateInvestProductStatus(pmap);
		//���¸��û��ò�ƷͶ��״̬Ϊ����������
		Map<String,Object> investmap = new HashMap<String,Object>();
		investmap.put("invest_status", Constant.getInvest_status_proftok());
		investmap.put("invest_prdouct_id", us.getInvt_product_id());
		investmap.put("invest_statuspre", Constant.getInvest_status_ok());
		investmap.put("user_id", us.getInvest_user_id());
		investMgrDao.updateInvestStatusByInvt(investmap);
		investMgrDao.updateInvestDetailStatusByInvt(investmap);
		return "0";
	}
	/*
	 * Ӧ��Ӧ����ѯ
	 */
	public List<UserSettleVo> querySettlePay(Page page,SettleVo settleVo){
		
		Map<String,Object> paramap = new HashMap<String,Object>();
		if("1".equals(page.getPageflag())){
			paramap = page.getParamMap();
			paramap.put("page", page);
		}else{
			paramap.put("page", page);
			paramap.put("invt_product_name", AppUtil.nvlStr(settleVo.getInvt_product_name()));
			paramap.put("invt_product_namelike", "%"+ AppUtil.nvlStr(settleVo.getInvt_product_name()) +"%");
			paramap.put("phone", settleVo.getPhone());
			paramap.put("settle_flag", settleVo.getSettle_flag());
			paramap.put("start_date", settleVo.getStart_date());
			paramap.put("end_date", settleVo.getEnd_date());
			page.setParamMap(paramap);
		}
		List<UserSettle> uslist = pubResultMgrDao.selectSettleByPrt(paramap);
		List<UserSettleVo> listusvo = new ArrayList<UserSettleVo>();
		for(UserSettle us:uslist){
			UserSettleVo uvo = new UserSettleVo();
			uvo.setUstl_id(us.getUstl_id());
			uvo.setCreate_date(us.getCreate_date());
			uvo.setInvest_user_id(us.getInvest_user_id());
			uvo.setInvt_product_id(us.getInvt_product_id());
			uvo.setInvt_product_name(us.getInvt_product_name());
			uvo.setTransion_seq(us.getTransion_seq());
			uvo.setUpdate_date(us.getUpdate_date());
			uvo.setUser_invest_profit(AppUtil.numFormat(us.getUser_invest_profit(),2));
			uvo.setUser_invest_profitpay(AppUtil.numFormat(us.getUser_invest_profitpay(),2));
			uvo.setUser_name(us.getUser_name());
			uvo.setUser_count(us.getUser_count());
			uvo.setUser_investbail_all(AppUtil.numFormat(us.getUser_investbail_all(),2));
			uvo.setUser_investcharge_all(AppUtil.numFormat(us.getUser_investcharge_all(),2));
			uvo.setUser_buycount_all(AppUtil.numFormat(us.getUser_buycount_all(),2));
			uvo.setUser_investcount_all(AppUtil.numFormat(us.getUser_investcount_all(),2));
			uvo.setUser_recivecount_all(AppUtil.numFormat(us.getUser_recivecount_all(),2));
			uvo.setSettle_flag(us.getSettle_flag());
			uvo.setSettle_flagname(us.getSettle_flagname());
			String user_paycount_all = "";
			if(Double.parseDouble(us.getUser_paycount_all()) > 0){
				user_paycount_all = "+" +AppUtil.numFormat( us.getUser_paycount_all(),2);
			}else{
				user_paycount_all = AppUtil.numFormat( us.getUser_paycount_all(),2);
			}
			uvo.setUser_paycount_all(user_paycount_all);
			listusvo.add(uvo);
		}
		return listusvo;
	}
	
	/*
	 * ������ǩ��
	 */
	public String createLovNo(String invest_product_id){
		
		pubResultMgrDao.delLovNo(invest_product_id);
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("invest_product_id", invest_product_id);
		paramap.put("invest_status", Constant.getInvest_status_feeok());
		
		List<InvestDetail>  investdlist = investMgrDao.selectInvestDetail(paramap);
		//��ѯ��ǩ��ǩ��
		for(InvestDetail idetail:investdlist){
			String lov_no = idetail.getResult_no();
			String basic_product_id = idetail.getBasic_product_id();
			if(lov_no != null && !"".equals(lov_no)){
				//��ǩβ������
				String lov_noarray[] = lov_no.split(",");
				Long start_no = Long.parseLong(idetail.getStart_no());
				String sub_code = idetail.getSub_code();
				for(int i=0;i<Integer.parseInt(sub_code);i++){
					//���ݹ�ǩ�������ɹ�ǩ����
					Long check_sno = start_no + i;
					for(int m=0;m<lov_noarray.length;m++){
						if(AppUtil.judgeLov(check_sno.toString(), lov_noarray[m])){//����β����ǩ����
							//��ǩ�Ŵ������ݿ�
							Map<String,Object> pmap1 = new HashMap<String,Object>();
							pmap1.put("user_invest_id", idetail.getUser_invest_id());
							pmap1.put("invest_prdouct_id", invest_product_id);
							pmap1.put("basic_product_id", basic_product_id);
							pmap1.put("invest_no", check_sno.toString());
							pmap1.put("detail_id", idetail.getDetail_id());
							pubResultMgrDao.insertLovNo(pmap1);
						}
					}
				}				
			}
		}
		return "0";
	}
	
	/*
	 * ������ʷ��Ϣ��ѯ
	 */
	public List<InvtProductSettleDetailVo> querySettleHistory(Page page,SettleHisVo settleHisVo){
		
		Map<String,Object> paramap = new HashMap<String,Object>();
		if("1".equals(page.getPageflag())){
			paramap = page.getParamMap();
			paramap.put("page", page);
		}else{
			paramap.put("page", page);
			paramap.put("invt_product_namelike", "%"+ settleHisVo.getInvt_product_name() +"%");
			paramap.put("invt_product_name", settleHisVo.getInvt_product_name());
			paramap.put("user_namelike", "%"+ settleHisVo.getUser_name() +"%");
			paramap.put("user_name",settleHisVo.getUser_name());
			paramap.put("start_date", settleHisVo.getStart_date());
			paramap.put("end_date", settleHisVo.getEnd_date());
			paramap.put("invt_product_status", settleHisVo.getInvt_product_status());
			page.setParamMap(paramap);
		}
		List<InvtProductSettleDetail> detaillist = pubResultMgrDao.selectSettleDetail(paramap);
		List<InvtProductSettleDetailVo> rlist = new ArrayList<InvtProductSettleDetailVo>();
		for(InvtProductSettleDetail ipd:detaillist){
			InvtProductSettleDetailVo ivo = new InvtProductSettleDetailVo();
			ivo.setBasic_product_id(ipd.getBasic_product_id());
			ivo.setCreate_date(ipd.getCreate_date());
			ivo.setInvest_user_id(ipd.getInvest_user_id());
			ivo.setInvest_user_name(ipd.getInvest_user_name());
			ivo.setInvest_user_phone(ipd.getInvest_user_phone());
			ivo.setInvt_product_id(ipd.getInvt_product_id());
			ivo.setInvt_product_name(ipd.getInvt_product_name());
			ivo.setPraise_count(ipd.getPraise_count());
			ivo.setPrice_aft(AppUtil.numFormat(ipd.getPrice_aft(),2));
			ivo.setPrice_prv(AppUtil.numFormat(ipd.getPrice_prv(),2));
			ivo.setProduct_buycount_all(AppUtil.numFormat(ipd.getProduct_buycount_all(),2));
			ivo.setProduct_investbail_all(AppUtil.numFormat(ipd.getProduct_investbail_all(),2));
			ivo.setProduct_investcharge_all(AppUtil.numFormat(ipd.getProduct_investcharge_all(),2));
			ivo.setProduct_investcount_all(AppUtil.numFormat(ipd.getProduct_investcount_all(),2));
			ivo.setProduct_recivecount_all(AppUtil.numFormat(ipd.getProduct_recivecount_all(),2));
			ivo.setSettle_id(ipd.getSettle_id());
			ivo.setUpdate_date(ipd.getUpdate_date());
			ivo.setUser_accruedcharges_amount(AppUtil.numFormat(ipd.getUser_accruedcharges_amount(),2));
			ivo.setUser_accruedcharges_type(ipd.getUser_accruedcharges_type());
			ivo.setUser_id(ipd.getUser_id());
			ivo.setUser_invest_id(ipd.getUser_invest_id());
			ivo.setUser_invest_profit(AppUtil.numFormat(ipd.getUser_invest_profit(),2));
			ivo.setUser_name(ipd.getUser_name());
			ivo.setUser_settle_profit(AppUtil.numFormat(ipd.getUser_settle_profit(),2));
			ivo.setSettle_id(ipd.getSettle_id());
			ivo.setInvt_product_statusname(ipd.getInvt_product_statusname());
			rlist.add(ivo);
		}
		return rlist;
	}
	/*
	 * �����ѯ
	 */
	public Map<String,Object> querySettleMoreInfo(String invt_product_id){
		Map<String,Object> rmap = new HashMap<String,Object>();
		//��ѯ������Ϣ
		Map<String,Object> mmap = new HashMap<String,Object>();
		mmap.put("invt_product_id", invt_product_id);
		InvtProductSettleDetail invtProductSettleDetail = pubResultMgrDao.selectSettleSum(mmap);
		SettleMoreInfoVo  settleMoreInfoVo = new SettleMoreInfoVo();
		settleMoreInfoVo.setInvt_product_id(invtProductSettleDetail.getInvt_product_id());
		settleMoreInfoVo.setPraise_count(invtProductSettleDetail.getPraise_count());
		settleMoreInfoVo.setProduct_buycount_all(AppUtil.numFormat(invtProductSettleDetail.getProduct_buycount_all(), 2));
		settleMoreInfoVo.setProduct_investcharge_all(AppUtil.numFormat(invtProductSettleDetail.getProduct_investcharge_all(),2));
		settleMoreInfoVo.setUser_invest_profit(AppUtil.numFormat(invtProductSettleDetail.getUser_invest_profit(),2));
		settleMoreInfoVo.setInvt_product_name(invtProductSettleDetail.getInvt_product_name());
		settleMoreInfoVo.setUser_id(invtProductSettleDetail.getUser_id());
		settleMoreInfoVo.setUser_name(invtProductSettleDetail.getUser_name());
		settleMoreInfoVo.setUpdate_date(invtProductSettleDetail.getUpdate_date());
		rmap.put("moreinfo", settleMoreInfoVo);
		//��ѯ�û�Ͷ����Ϣ
		Map<String,Object> umap = new HashMap<String,Object>();
		umap.put("invt_product_id", invt_product_id);
		List<UserSettle> listu = pubResultMgrDao.selectSettleByPrt(umap);
		List<UserSettleVo> listusvo = new ArrayList<UserSettleVo>();
		for(UserSettle us:listu){
			UserSettleVo uvo = new UserSettleVo();
			uvo.setUstl_id(us.getUstl_id());
			uvo.setCreate_date(us.getCreate_date());
			uvo.setInvest_user_id(us.getInvest_user_id());
			uvo.setInvt_product_id(us.getInvt_product_id());
			uvo.setInvt_product_name(us.getInvt_product_name());
			uvo.setTransion_seq(us.getTransion_seq());
			uvo.setUpdate_date(us.getUpdate_date());
			uvo.setUser_invest_profit(AppUtil.numFormat(us.getUser_invest_profit(),2));
			uvo.setUser_invest_profitpay(AppUtil.numFormat(us.getUser_invest_profitpay(),2));
			uvo.setUser_name(us.getUser_name());
			uvo.setUser_count(us.getUser_count());
			uvo.setUser_investbail_all(AppUtil.numFormat(us.getUser_investbail_all(),2));
			uvo.setUser_investcharge_all(AppUtil.numFormat(us.getUser_investcharge_all(),2));
			uvo.setUser_buycount_all(AppUtil.numFormat(us.getUser_buycount_all(),2));
			uvo.setUser_investcount_all(AppUtil.numFormat(us.getUser_investcount_all(),2));
			uvo.setUser_recivecount_all(AppUtil.numFormat(us.getUser_recivecount_all(),2));
			uvo.setSettle_flag(us.getSettle_flag());
			uvo.setSettle_flagname(us.getSettle_flagname());
			String user_paycount_all = "";
			user_paycount_all = AppUtil.fmtAcount(us.getUser_paycount_all());
			uvo.setUser_paycount_all(user_paycount_all);
			listusvo.add(uvo);
		}
		rmap.put("listusvo", listusvo);
		
		return rmap;
	}
	
	
	/*
	 * ��ѯ��ǩ����
	 */
	public int countLov(String detail_id){
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("detail_id", detail_id);
		return pubResultMgrDao.selectLovCount(paramap);
	}
	
	
	public PubResultMgrDao getPubResultMgrDao() {
		return pubResultMgrDao;
	}
	public void setPubResultMgrDao(PubResultMgrDao pubResultMgrDao) {
		this.pubResultMgrDao = pubResultMgrDao;
	}

	public IssueMgrDao getIssueMgrDao() {
		return issueMgrDao;
	}

	public void setIssueMgrDao(IssueMgrDao issueMgrDao) {
		this.issueMgrDao = issueMgrDao;
	}
	public InvestMgrDao getInvestMgrDao() {
		return investMgrDao;
	}
	public void setInvestMgrDao(InvestMgrDao investMgrDao) {
		this.investMgrDao = investMgrDao;
	}
	public InvestMgrService getInvestMgrService() {
		return investMgrService;
	}
	public void setInvestMgrService(InvestMgrService investMgrService) {
		this.investMgrService = investMgrService;
	}

}
