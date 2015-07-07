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
	 * 检查产品投资状态
	 */
	public String checkInvestAjax(String invest_prt_id){
		
		//判断是够存在有效投资
		List<Invest> listactive = pubResultMgrDao.selectActiveInvest(invest_prt_id);
		if(listactive == null || listactive.size() == 0){
			return "1";
		}
		//判断是否存在未审核投资
		List<Invest> listuncheck = pubResultMgrDao.selectUnCheckInvest(invest_prt_id);
		if(listuncheck != null && listuncheck.size() >0){
			return "2";
		}
		return "0";
	}
	
	
	/*
	 * 公布中签结果
	 */
	public String pubResultNo(PubResultVo pubResultVo){
		//获取基础标的数组
		String basic_product_idarray = pubResultVo.getBasic_product_idarray();
		String result_noarray = pubResultVo.getResult_noarray();
		String barray[] = basic_product_idarray.split(";");
		String rarray[] = result_noarray.split(";");
		for(int i=0; i<barray.length;i++){
			if(barray[i] == null || "".equals(barray[i])){
				break;
			}
			//更新基础标的状态
			Map<String,Object> paramap = new HashMap<String,Object>();
			paramap.put("result_no",rarray[i]);
			paramap.put("basic_product_id",barray[i]);
			pubResultMgrDao.updateResultNo(paramap);
		}
		//生成中签号码库
		createLovNo(pubResultVo.getInvt_product_id());
		//判断是否改产品所有关联基础标的都已开签
		List<InvestMoreInfo> listmore = issueMgrDao.selectInvestMoreInfo(pubResultVo.getInvt_product_id());
		boolean flag = true;
		for(InvestMoreInfo imf:listmore){
			if(!"1".equals(imf.getResult_flag())){
				flag = false;
				break;
			}
		}
		//更新投资产品 状态、投资状态为已开签
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
	 * 投资产品结算
	 */
	public Map<String,Object> settleProduct(SettleVo settleVo){
		
		
		//在利润分配之前可以重复结算
		String invt_product_id = settleVo.getInvt_product_id();
		pubResultMgrDao.delSettleDetail(invt_product_id);
		pubResultMgrDao.delSettle(invt_product_id);
		Map<String,Object> rmap = new HashMap<String,Object>();
		SettleVo setvo =new SettleVo();
		//基础标的ID数组
		String baeidarray[] = settleVo.getBasic_product_idarray().split(",");
		//基础标的卖出价数组
		String sailpricearry[] = settleVo.getPrice_sailarray().split(",");
		//统计每次投资利润、写入结算明细表
		for(int i=0;i<baeidarray.length;i++){
			String baseid = baeidarray[i];
			Map<String,Object> paramap = new HashMap<String,Object>();
			paramap.put("invest_product_id", settleVo.getInvt_product_id());
			paramap.put("basic_product_id", baseid);
			paramap.put("invest_status", Constant.getInvest_status_lovok());
			List<InvestMoreInfo>  involist = issueMgrDao.selectInvestMoreInfoByib(paramap);
			for(InvestMoreInfo invo:involist){
				setvo.setInvt_product_name(invo.getInvt_product_name());
				//投资产品id
				String invest_product_id = settleVo.getInvt_product_id();
				//基础标的id
				String basic_product_id = baseid;
				String detail_id = invo.getDetail_id();
				//产品买入价
				String buy_price = invo.getPrice();
				//产品卖出价
				String sail_price = sailpricearry[i];
				//中签数量
				int lov_count = countLov(detail_id);
				
				if("1".equals(invo.getSales_city())){//沪市1:1000配股
					lov_count = 1000*lov_count;
				}else{
					lov_count = 500*lov_count;
				}

				String product_investbail_all = invo.getAcount_bail();
				//产品购买总费用额
				String product_buycount_all = invo.getAcount_prtfee();
				//产品总投资额
				String product_investcount_all = invo.getAmount_all();
				//用户该笔基础标的投资利润
				Double userinvestprofit = (Double.parseDouble(sail_price) - Double.parseDouble(buy_price))*lov_count*(1d-0.0015d);
				//计算总收入
				Double product_recivecount_all = userinvestprofit + Double.parseDouble(product_buycount_all);
				//基础标的投资管理费总额 = 投资利润x投资管理费比例
				//String product_investcharge_all = pdc.getAcount_charge();
				//获取基础标的投资管理费收取比例
				Double user_accruedcharges_amount = Double.parseDouble(invo.getUser_accruedcharges_amount())/100;			
				Double product_investcharge_all =  (Double.parseDouble(sail_price) - Double.parseDouble(buy_price))*lov_count*user_accruedcharges_amount;

				//插入产品结算明细表 
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
		//查询该产品的总投资额
		InvtProductSumInfo allcount = pubResultMgrDao.selectInvtProductSum(settleVo.getInvt_product_id());
		//计算更新每次投资的利润分配、管理费
		List<InvtProductSettleDetail> uslist = pubResultMgrDao.selectSettleDetailByPrt(settleVo.getInvt_product_id());
		for(InvtProductSettleDetail us:uslist){
			//管理费收取比例
			Double user_accruedcharges_amount =  Double.parseDouble(us.getUser_accruedcharges_amount())/100;
			//本次投资购买额
			Double product_buycount_all =  Double.parseDouble(us.getProduct_buycount_all());
			//该产品总投资额
			Double all_buy_count = Double.parseDouble(allcount.getProduct_buycount_all());
			//该产品总利润额
			Double all_invest_profit = Double.parseDouble(allcount.getUser_invest_profit());
			//本次投资应分配利润
			Double invest_settle_profit = (product_buycount_all/all_buy_count)*all_invest_profit ;
			//本次投资应缴纳管理费
			Double invest_charge_count = invest_settle_profit*user_accruedcharges_amount;
			Map<String,Object> smap = new HashMap<String,Object>();
			
			smap.put("product_investcharge_all", invest_charge_count);
			smap.put("user_settle_profit", invest_settle_profit);
			smap.put("settle_id", us.getSettle_id());
			//更新数据库
			pubResultMgrDao.updateInvestSettle(smap);
		}
		//每个投资用户的购买总额、投资利润
		List<InvtProductSumInfo> suminfolist = pubResultMgrDao.selectUserProfitSum(settleVo.getInvt_product_id());
		for(InvtProductSumInfo suminfo:suminfolist){
			//重新计算客户应分配利润
			Double userprofit = (Double.parseDouble(suminfo.getProduct_buycount_all())/Double.parseDouble(allcount.getProduct_buycount_all()))*Double.parseDouble(allcount.getUser_invest_profit());
			//插入用户结算结果表
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
			//用户结账总额
			Double user_paycount_all = Double.parseDouble(suminfo.getUser_invest_profit()) - Double.parseDouble(userprofit.toString()) - Double.parseDouble(suminfo.getUser_investbail_all()) + Double.parseDouble(suminfo.getUser_investcharge_all());
			setlemap.put("user_paycount_all", String.valueOf(user_paycount_all));
			pubResultMgrDao.insertProductSettle(setlemap);
		}

		//更新投资产品状态为结算完成
		Map<String,Object> smap = new HashMap<String,Object>();
		smap.put("invt_product_status", Constant.getInvestprt_status_setl());
		smap.put("invt_product_id", settleVo.getInvt_product_id());
		issueMgrDao.updateInvestProductStatus(smap);
		//更新所有投资状态为结算完成
		Map<String,Object> investmap = new HashMap<String,Object>();
		investmap.put("invest_status", Constant.getInvest_status_ok());
		investmap.put("invest_prdouct_id", settleVo.getInvt_product_id());
		investmap.put("invest_statuspre", Constant.getInvest_status_lovok());
		investMgrDao.updateInvestStatusByInvt(investmap);
		investMgrDao.updateInvestDetailStatusByInvt(investmap);
		
		return rmap;
	}
	/*
	 * 利润分配
	 */
	public String settlePay(String ustl_id){
		//生成流水号
		String transeq = UUID.randomUUID().toString();
		UserSettle us = pubResultMgrDao.selectSettleById(ustl_id);
		logger.debug("流水：" + transeq + "利润分配开始");
		
		
		//用户充值管理费
		InvestPayVo  payvoI = new InvestPayVo();
		payvoI.setPay_count(us.getUser_investcharge_all());
		payvoI.setTran_paytyp(Constant.getTran_paytyp_rec());
		payvoI.setTransion_seq(transeq);
		payvoI.setUser_id(us.getInvest_user_id());
		payvoI.setUser_opttyp(Constant.getUser_opttyp_recharge());
		investMgrService.investPay(payvoI);
		logger.debug("用户充值管理费："+us.getUser_investcharge_all());		
		//用户管理费扣除
		InvestPayVo  payvoC = new InvestPayVo();
		payvoC.setPay_count(us.getUser_investcharge_all());
		payvoC.setTran_paytyp(Constant.getTran_paytyp_pay());
		payvoC.setTransion_seq(transeq);
		payvoC.setUser_id(us.getInvest_user_id());
		payvoC.setUser_opttyp(Constant.getUser_opttyp_settlecharge());
		investMgrService.investPay(payvoC);
		logger.debug("用户管理费扣除："+us.getUser_investcharge_all());
		//平台管理费入账
		PlatReceiveVo platReceiveVoC = new PlatReceiveVo();
		platReceiveVoC.setAmount(us.getUser_investcharge_all());
		platReceiveVoC.setPayment_type(Constant.getTran_paytyp_rec());
		platReceiveVoC.setTransion_seq(transeq);
		platReceiveVoC.setTransion_type(Constant.getUser_opttyp_settlecharge());
		investMgrService.platReceive(platReceiveVoC);
		logger.debug("平台管理费入账："+us.getUser_investcharge_all());
		//平台管理费提现
		PlatReceiveVo platReceiveVoW = new PlatReceiveVo();
		platReceiveVoW.setAmount(us.getUser_investcharge_all());
		platReceiveVoW.setPayment_type(Constant.getTran_paytyp_pay());
		platReceiveVoW.setTransion_seq(transeq);
		platReceiveVoW.setTransion_type(Constant.getUser_opttyp_wdcash());
		investMgrService.platReceive(platReceiveVoW);
		logger.debug("平台管理费提现："+us.getUser_investcharge_all());

		
		//平台保证金扣除
		PlatReceiveVo platReceiveVoB = new PlatReceiveVo();
		platReceiveVoB.setAmount(us.getUser_investbail_all());
		platReceiveVoB.setPayment_type(Constant.getTran_paytyp_pay());
		platReceiveVoB.setTransion_seq(transeq);
		platReceiveVoB.setTransion_type(Constant.getUser_opttyp_settlebail());
		investMgrService.platReceive(platReceiveVoB);
		logger.debug("平台保证金扣除："+us.getUser_investbail_all());
		//用户保证金返还
		InvestPayVo  payvo = new InvestPayVo();
		payvo.setPay_count(us.getUser_investbail_all());
		payvo.setTran_paytyp(Constant.getTran_paytyp_rec());
		payvo.setTransion_seq(transeq);
		payvo.setUser_id(us.getInvest_user_id());
		payvo.setUser_opttyp(Constant.getUser_opttyp_settlebail());
		investMgrService.investPay(payvo);
		logger.debug("用户保证金返还："+us.getUser_investbail_all());
		
		
		//用户利润充值（用户实得利润）
		InvestPayVo  payvoIP = new InvestPayVo();
		payvoIP.setPay_count(us.getUser_invest_profitpay());
		payvoIP.setTran_paytyp(Constant.getTran_paytyp_rec());
		payvoIP.setTransion_seq(transeq);
		payvoIP.setUser_id(us.getInvest_user_id());
		payvoIP.setUser_opttyp(Constant.getUser_opttyp_recharge());
		investMgrService.investPay(payvoIP);
		logger.debug("用户利润充值（用户实得利润）："+ us.getUser_invest_profitpay());
		//用户利润扣除（用户实得利润）
		InvestPayVo  payvo2 = new InvestPayVo();
		payvo2.setPay_count(us.getUser_invest_profitpay());
		payvo2.setTran_paytyp(Constant.getTran_paytyp_pay());
		payvo2.setTransion_seq(transeq);
		payvo2.setUser_id(us.getInvest_user_id());
		payvo2.setUser_opttyp(Constant.getUser_opttyp_settleprofitprv());
		investMgrService.investPay(payvo2);
		logger.debug("用户利润扣除（用户实得利润）："+ us.getUser_invest_profitpay());
		//平台利润入账（用户实得利润）
		PlatReceiveVo platReceiveVoP = new PlatReceiveVo();
		platReceiveVoP.setAmount(us.getUser_invest_profitpay());
		platReceiveVoP.setPayment_type(Constant.getTran_paytyp_rec());
		platReceiveVoP.setTransion_seq(transeq);
		platReceiveVoP.setTransion_type(Constant.getUser_opttyp_settleprofitprv());
		investMgrService.platReceive(platReceiveVoP);
		logger.debug("平台利润入账（用户实得利润）："+us.getUser_invest_profitpay());
		
		
		//平台利润扣除（用户应得利润）
		PlatReceiveVo platReceiveVoPA = new PlatReceiveVo();
		platReceiveVoPA.setAmount(us.getUser_invest_profit());
		platReceiveVoPA.setPayment_type(Constant.getTran_paytyp_pay());
		platReceiveVoPA.setTransion_seq(transeq);
		platReceiveVoPA.setTransion_type(Constant.getUser_opttyp_settleprofitaf());
		investMgrService.platReceive(platReceiveVoPA);
		logger.debug("平台利润扣除（用户应得利润）："+us.getUser_invest_profit());
		//用户利润入账（用户应得利润）
		InvestPayVo  payvopa = new InvestPayVo();
		payvopa.setPay_count(us.getUser_invest_profit());
		payvopa.setTran_paytyp(Constant.getTran_paytyp_rec());
		payvopa.setTransion_seq(transeq);
		payvopa.setUser_id(us.getInvest_user_id());
		payvopa.setUser_opttyp(Constant.getUser_opttyp_settleprofitaf());
		investMgrService.investPay(payvopa);
		logger.debug("用户利润入账（用户应得利润）："+ us.getUser_invest_profit());
		//用户利润提现（用户应得利润）
		InvestPayVo  payvoWP = new InvestPayVo();
		payvoWP.setPay_count(us.getUser_invest_profit());
		payvoWP.setTran_paytyp(Constant.getTran_paytyp_pay());
		payvoWP.setTransion_seq(transeq);
		payvoWP.setUser_id(us.getInvest_user_id());
		payvoWP.setUser_opttyp(Constant.getUser_opttyp_wdcash());
		investMgrService.investPay(payvoWP);
		logger.debug("用户利润提现（用户应得利润）："+ us.getUser_invest_profit());
		
		//更新结算状态、更新利润分配流水
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("profit_seq", transeq);
		paramap.put("ustl_id", ustl_id);
		pubResultMgrDao.updateSettleStatus(paramap);
		//判断是否该产品所有涉及用户都已利润分配完成
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
		//所有都已分配完成、则更新产品状态为利润分配完成
		if(flag){
			prtstatus = Constant.getInvestprt_status_setok();
		}else{
			prtstatus =  Constant.getInvestprt_status_seting();
		}
		Map<String,Object> pmap = new HashMap<String,Object>();
		pmap.put("invt_product_status", prtstatus);
		pmap.put("invt_product_id", us.getInvt_product_id());
		issueMgrDao.updateInvestProductStatus(pmap);
		//更新该用户该产品投资状态为利润分配完成
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
	 * 应收应付查询
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
	 * 生成中签号
	 */
	public String createLovNo(String invest_product_id){
		
		pubResultMgrDao.delLovNo(invest_product_id);
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("invest_product_id", invest_product_id);
		paramap.put("invest_status", Constant.getInvest_status_feeok());
		
		List<InvestDetail>  investdlist = investMgrDao.selectInvestDetail(paramap);
		//查询中签的签号
		for(InvestDetail idetail:investdlist){
			String lov_no = idetail.getResult_no();
			String basic_product_id = idetail.getBasic_product_id();
			if(lov_no != null && !"".equals(lov_no)){
				//中签尾号数组
				String lov_noarray[] = lov_no.split(",");
				Long start_no = Long.parseLong(idetail.getStart_no());
				String sub_code = idetail.getSub_code();
				for(int i=0;i<Integer.parseInt(sub_code);i++){
					//根据购签数量生成购签号码
					Long check_sno = start_no + i;
					for(int m=0;m<lov_noarray.length;m++){
						if(AppUtil.judgeLov(check_sno.toString(), lov_noarray[m])){//满足尾号中签规则
							//中签号存入数据库
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
	 * 结算历史信息查询
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
	 * 详情查询
	 */
	public Map<String,Object> querySettleMoreInfo(String invt_product_id){
		Map<String,Object> rmap = new HashMap<String,Object>();
		//查询汇总信息
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
		//查询用户投资信息
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
	 * 查询中签个数
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
