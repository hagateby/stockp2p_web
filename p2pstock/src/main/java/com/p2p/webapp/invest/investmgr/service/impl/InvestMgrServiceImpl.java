package com.p2p.webapp.invest.investmgr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.common.util.DateTimeFormatUtil;
import com.p2p.webapp.invest.investmgr.dao.InvestMgrDao;
import com.p2p.webapp.invest.investmgr.dto.FeeCountDto;
import com.p2p.webapp.invest.investmgr.dto.InvestCountDto;
import com.p2p.webapp.invest.investmgr.dto.InvestDetailDto;
import com.p2p.webapp.invest.investmgr.entity.Invest;
import com.p2p.webapp.invest.investmgr.service.InvestMgrService;
import com.p2p.webapp.invest.investmgr.vo.InvestMgrVo;
import com.p2p.webapp.invest.investmgr.vo.InvestPayVo;
import com.p2p.webapp.invest.investmgr.vo.InvestVo;
import com.p2p.webapp.invest.investmgr.vo.PlatReceiveVo;
import com.p2p.webapp.sysmgr.issuemgr.dao.IssueMgrDao;
import com.p2p.webapp.sysmgr.issuemgr.entity.InvestMoreInfo;
import com.p2p.webapp.sysmgr.issuemgr.vo.InvestMoreInfoVo;
import com.p2p.webapp.sysmgr.productmgr.dao.ProductMgrDao;
import com.p2p.webapp.sysmgr.productmgr.entity.BaseProduct;
import com.p2p.webapp.user.fundmgr.dao.FundMgrDao;
import com.p2p.webapp.user.fundmgr.entity.AccountInfo;

public class InvestMgrServiceImpl implements InvestMgrService{
	
	private InvestMgrDao investMgrDao;
	private ProductMgrDao productMgrDao;
	private IssueMgrDao issueMgrDao;
	private FundMgrDao fundMgrDao;
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(InvestMgrServiceImpl.class);
	/*
	 * 用户投资
	 */
	public String userInvest(InvestMgrVo imvo){
		Map<String,Object> paramap = new HashMap<String,Object>();
		String basic_product_idarray = imvo.getBasic_product_idarray();
		String start_noarray  = imvo.getStart_noarray();
		String sub_codearray = imvo.getSub_codearray();
		FeeCountDto feeCountDto = new FeeCountDto();
		feeCountDto.setBasic_product_idarray(basic_product_idarray);
		feeCountDto.setInvest_prdouct_id(imvo.getInvt_product_id());
		feeCountDto.setStart_noarray(start_noarray);
		feeCountDto.setSub_codearray(sub_codearray);
		Map<String,Object> rmap = investCount(feeCountDto);
		String resultcode = (String)rmap.get("resultcode");
		if("1".equals(resultcode)){
			return "1";
		}
		InvestCountDto indto = (InvestCountDto)rmap.get("investCount");
		paramap.put("user_id", imvo.getUser_id());
		paramap.put("invest_prdouct_id", imvo.getInvt_product_id());
		paramap.put("transion_seq", imvo.getTranseq());
		paramap.put("invest_status", Constant.getInvest_status_arr());
		paramap.put("invest_type", imvo.getInvest_type());
		paramap.put("acount_prtfeeall", indto.getAcount_prtfeeall());
		paramap.put("acount_bailall", indto.getAcount_bailall());
		paramap.put("acount_chargeall", indto.getAcount_chargefeeall());
		paramap.put("amount_all", indto.getAcount_all());
		//插入主表
		investMgrDao.insertInvestInfo(paramap);
		String user_invest_id = investMgrDao.queryMaxLastSq();
		//明细表
		List<InvestDetailDto> detailList = (ArrayList<InvestDetailDto>)rmap.get("detailList");
		for(InvestDetailDto detaildto:detailList){
			Map<String,Object> pmap = new HashMap<String,Object>();
			pmap.put("user_invest_id", user_invest_id);
			pmap.put("basic_product_id", detaildto.getBasic_product_id());
			pmap.put("acount_prtfee", detaildto.getAcount_prtfee());
			pmap.put("acount_bail", detaildto.getAcount_bail());
			pmap.put("acount_charge", detaildto.getAcount_chargefee());
			pmap.put("amount", detaildto.getAcount());
			pmap.put("start_no", detaildto.getStart_no());
			pmap.put("sub_code", detaildto.getSub_code());
			pmap.put("invest_prdouct_id", imvo.getInvt_product_id());
			pmap.put("invest_status", Constant.getInvest_status_arr());
			pmap.put("user_id", imvo.getUser_id());
			investMgrDao.insertInvestDetail(pmap);
			//生成号码库
			/*
			detaildto.setUser_invest_id(user_invest_id);
			produceInvestNo(detaildto);
			*/
		}
		
		return "0";
	}
	/*
	 * 投资审核
	 */
	public String auditInvest(InvestMgrVo investMgrVo){
		
		String auditflag = investMgrVo.getAuditflag();
		//审核不通过 置为已删除
		if("0".equals(auditflag)){
			Map<String,Object> paramap = new HashMap<String,Object>();
			paramap.put("invest_status", Constant.getInvest_status_del());
			paramap.put("user_invest_id", investMgrVo.getUser_invest_id());
			investMgrDao.updateInvestStatusByUser(paramap);
			investMgrDao.updateInvestDetailStatusByUser(paramap);
			
		}else if("1".equals(auditflag)){//审核通过
			//扣除保证金
			Invest invest = investMgrDao.queryInvest(investMgrVo.getUser_invest_id());
			//暂时仅支持线下交费，因此此处直接调用付费功能扣除保证金
			Double trancount = Double.parseDouble(invest.getAcount_bailall());
			//个人账户转出保证金-平台账户转入保证金
			InvestPayVo payvo = new InvestPayVo();
			payvo.setUser_id(invest.getUser_id());
			payvo.setPay_count(trancount.toString());
			payvo.setTransion_seq(invest.getTransion_seq());
			payvo.setUser_opttyp(Constant.getUser_opttyp_buy());
			payvo.setTran_paytyp(Constant.getTran_paytyp_pay());
			investPay(payvo);
			//修改投资状态为投资完成
			/*
			Map<String,Object> investmap = new HashMap<String,Object>();
			investmap.put("invest_status", Constant.getInvest_status_feeok());
			investmap.put("user_invest_id", user_invest_id);	
			*/		
			//平台账户入账
			PlatReceiveVo platReceiveVo = new PlatReceiveVo();
			platReceiveVo.setAmount(trancount.toString());
			platReceiveVo.setPayment_type(Constant.getTran_paytyp_rec());
			platReceiveVo.setTransion_seq(invest.getTransion_seq());
			platReceiveVo.setTransion_type(Constant.getUser_opttyp_buy());
			platReceive(platReceiveVo);
			//investMgrDao.updateInvestStatusByUser(investmap);
			//置为投资成功
			Map<String,Object> paramap = new HashMap<String,Object>();
			paramap.put("invest_status", Constant.getInvest_status_feeok());
			paramap.put("user_invest_id", investMgrVo.getUser_invest_id());
			investMgrDao.updateInvestStatusByUser(paramap);
			investMgrDao.updateInvestDetailStatusByUser(paramap);
		}
		
		return "0";
	}
	
	
	/*
	 * 用户投资修改
	 */
	public String editInvest(InvestMgrVo imvo){
		
		//删除原投资
		investMgrDao.delInvestInfo(imvo.getUser_invest_id());
		investMgrDao.delInvestDetail(imvo.getUser_invest_id());
		userInvest(imvo);
		return "0";
	}
	
	/*
	 * 用户投资修改初始化
	 */
	public Map<String,Object> editInit(String user_invest_id){
		Map<String,Object> rmap = new HashMap<String,Object>();
		List<InvestMoreInfo> investlist = investMgrDao.selectInvestMoreInfo(user_invest_id);
		InvestMoreInfoVo investMainVo = new InvestMoreInfoVo();
		String base_product_idarray ="";
		String start_noarray ="";
		String pricearray ="";
		String sub_codearray ="";
		String invester_bailarray ="";
		String user_accruedcharges_typearray ="";
		String user_accruedcharges_amountarray ="";
		String feecntarray ="";
		List<InvestMoreInfoVo> rlist = new ArrayList<InvestMoreInfoVo>();
		for(InvestMoreInfo im:investlist){
			InvestMoreInfoVo imvo = new InvestMoreInfoVo();
			base_product_idarray = base_product_idarray + im.getBasic_product_id() + ",";
			start_noarray = start_noarray + im.getStart_no() + ",";
			pricearray = pricearray + im.getPrice() + ",";
			sub_codearray = sub_codearray + im.getSub_code() + ",";
			invester_bailarray = invester_bailarray + im.getInvester_bail() + ",";
			user_accruedcharges_typearray = user_accruedcharges_typearray + im.getUser_accruedcharges_type() + ",";
			user_accruedcharges_amountarray = user_accruedcharges_amountarray + im.getUser_accruedcharges_amount() + ",";
			if("1".equals(im.getSales_city())){//沪市一个签号为1000股
				feecntarray = feecntarray + "1000" + ",";
			}else{//深市一个签号为500股
				feecntarray = feecntarray + "500" + ",";
			}
			
			imvo.setAmount(AppUtil.numFormat(im.getAmount(),2));
			imvo.setBasic_product_id(im.getBasic_product_id());
			imvo.setBasic_product_name(im.getBasic_product_name());
			imvo.setBasic_product_stock_id(im.getBasic_product_stock_id());
			imvo.setBasic_product_type(im.getBasic_product_type());
			imvo.setBasic_product_typename(im.getBasic_product_typename());
			imvo.setEnd_date(im.getEnd_date());
			imvo.setInvest_product_id(im.getInvest_product_id());
			imvo.setInvestend_date(im.getInvestend_date());
			imvo.setInvester_bail(AppUtil.numFormat(im.getInvester_bail(),2));
			imvo.setInveststart_date(im.getInveststart_date());
			imvo.setInvt_product_id(im.getInvt_product_id());
			imvo.setInvt_product_name(im.getInvt_product_name());
			imvo.setInvt_product_status(im.getInvt_product_status());
			imvo.setIssue_fee(AppUtil.numFormat(im.getIssue_fee(),2));
			imvo.setLauncher_accruedcharges_amount(AppUtil.numFormat(im.getLauncher_accruedcharges_amount(),2));
			imvo.setLauncher_accruedcharges_type(im.getLauncher_accruedcharges_type());
			imvo.setLauncher_accruedcharges_typename(im.getLauncher_accruedcharges_typename());
			imvo.setLow_limit(AppUtil.numFormat(im.getLow_limit(),2));
			imvo.setPrice(AppUtil.numFormat(im.getPrice(),2));
			imvo.setResult_flag(im.getResult_flag());
			imvo.setResult_no(im.getResult_no());
			imvo.setSales_city(im.getSales_city());
			imvo.setSales_cityname(im.getSales_cityname());
			imvo.setStart_date(im.getStart_date());
			imvo.setStarter_bail(im.getStarter_bail());
			imvo.setUp_limit(AppUtil.numFormat(im.getUp_limit(),2));
			imvo.setUser_accruedcharges_amount(AppUtil.numFormat(im.getUser_accruedcharges_amount(),2));
			imvo.setUser_accruedcharges_type(im.getUser_accruedcharges_type());
			imvo.setUser_accruedcharges_typename(im.getUser_accruedcharges_typename());
			imvo.setUser_id(im.getUser_id());
			imvo.setUser_name(im.getUser_name());
			imvo.setLot_date(im.getLot_date());
			imvo.setAcount_bail(AppUtil.numFormat(im.getAcount_bail(),2));
			imvo.setAcount_charge(AppUtil.numFormat(im.getAcount_charge(),2));
			imvo.setAcount_prtfee(AppUtil.numFormat(im.getAcount_prtfee(),2));
			imvo.setStart_no(im.getStart_no());
			imvo.setSub_code(im.getSub_code());
			imvo.setUser_invest_id(im.getUser_invest_id());
			rlist.add(imvo);
		}
		investMainVo = rlist.get(0);
		Invest invest = investMgrDao.queryInvest(user_invest_id);
		investMainVo.setAcount_bail_all(AppUtil.numFormat(invest.getAcount_bailall(),2));
		investMainVo.setAcount_prtfee_all(AppUtil.numFormat(invest.getAcount_prtfeeall(),2));
		investMainVo.setAmount_all(AppUtil.numFormat(invest.getAmount_all(),2));
		investMainVo.setBasic_product_idarray(base_product_idarray);
		investMainVo.setStart_noarray(start_noarray);
		investMainVo.setPricearray(pricearray);
		investMainVo.setSub_codearray(sub_codearray);
		investMainVo.setInvester_bailarray(invester_bailarray);
		investMainVo.setUser_accruedcharges_typearray(user_accruedcharges_typearray);
		investMainVo.setUser_accruedcharges_amountarray(user_accruedcharges_amountarray);
		investMainVo.setFeecntarray(feecntarray);
		//产品总投资额
		String invest_countall = issueMgrDao.selectInvtCountAll(investMainVo.getInvt_product_id());
		investMainVo.setAmount(AppUtil.numFormat(invest_countall,2));
		
		rmap.put("investMainVo", investMainVo);
		rmap.put("rlist", rlist);
		return rmap;
	}
	
	/*
	 * 投资费用计算
	 * basic_product_idarray 产品ID(多个以,分割) start_noarray起始号码(多个以,分割)sub_codearray 购买数量(多个以,分割)
	 */
	public Map<String,Object> investCount(FeeCountDto feeCountDto){
		String basic_product_idarray = feeCountDto.getBasic_product_idarray();
		String start_noarray = feeCountDto.getStart_noarray();
		String sub_codearray = feeCountDto.getSub_codearray();
		String invest_prdouct_id = feeCountDto.getInvest_prdouct_id();
		
		Map<String,Object> rmap = new HashMap<String,Object>();
		InvestCountDto  indto = new InvestCountDto();
		List<InvestDetailDto> detailList = new ArrayList<InvestDetailDto>();
		//为防止客户端伪造表单，保证金比例、管理费提取方式、管理费提取额度 从数据库重新获取
		//获取保证金比例
		InvestMoreInfo imfo = issueMgrDao.selectInvtMoreInfoById(feeCountDto.getInvest_prdouct_id());
		String invester_bail = imfo.getInvester_bail();
		String baseidarray[]  = basic_product_idarray.split(",");
		String subcodearray[] = sub_codearray.split(",");
		String startnoarray[] = start_noarray.split(",");
		Double acount_prtfeeall = 0d; 
		Double acount_bailall = 0d;
		//Float acount_chargeall = 0f;
		for(int i=0;i<baseidarray.length;i++){
			InvestDetailDto detaildto = new InvestDetailDto();
			BaseProduct bp =productMgrDao.selectBasicProductById(baseidarray[i]);
			//此处后台判断募集结束日期是否结束
			Date activedate = DateTimeFormatUtil.covertStringToDate(bp.getEnd_date(),DateTimeFormatUtil.YEAR_MONTH_DAY_HOUR_MINUTE_TEMPLATE);
			Date nowdate = DateTimeFormatUtil.getCurrentDate();
			if("2".equals(DateTimeFormatUtil.compareByDate(nowdate, activedate))){
				rmap.put("resultcode", "1");
				return rmap;
			}
			String sail_city = bp.getSales_city();
			//单价
			Double fprice = Double.parseDouble(bp.getPrice());
			//数量
			Double fsubcode =  Double.parseDouble(subcodearray[i]);
			//产品购买费
			Double facount_prtfee = fprice*fsubcode ;
			//保证金比例
			Double finvestbail = Double.parseDouble(invester_bail);
			Double facountbail = (facount_prtfee*finvestbail)/100.00d;
			
			if("1".equals(sail_city)){//沪市一个签号为1000股
				facountbail = 1000.00f*facountbail;
				facount_prtfee = 1000.00f*facount_prtfee;
			}else{
				facountbail = 500.00f*facountbail;
				facount_prtfee = 500.00f*facount_prtfee;
			}
			acount_prtfeeall = acount_prtfeeall + facount_prtfee;
			acount_bailall = acount_bailall + facountbail;
			
			//产品管理费
			/*
			Float chargecount = Float.parseFloat(bp.getUser_accruedcharges_amount());
			if(Constant.getProduct_accchargetyp_scale().equals(bp.getUser_accruedcharges_type())){//按比例
				chargecount = (facount_prtfee*chargecount)/100.00f;
				acount_chargeall = acount_chargeall + chargecount;
			}else{//按金额
				acount_chargeall = acount_chargeall + chargecount;
			}
			*/
			detaildto.setInvest_prdouct_id(invest_prdouct_id);
			detaildto.setBasic_product_id(baseidarray[i]);
			detaildto.setStart_no(startnoarray[i]);
			detaildto.setSub_code(subcodearray[i]);
			detaildto.setAcount_bail(facountbail.toString());
			//detaildto.setAcount_chargefee(chargecount.toString());
			detaildto.setAcount_prtfee(facount_prtfee.toString());
			Double count= facountbail + facount_prtfee;
			detaildto.setAcount(count.toString());
			detailList.add(detaildto);
		}

		Double count_all = acount_prtfeeall + acount_bailall;
		indto.setAcount_prtfeeall(acount_prtfeeall.toString());
		indto.setAcount_bailall(acount_bailall.toString());
		//indto.setAcount_chargefeeall(acount_chargeall.toString());
		indto.setAcount_all(count_all.toString());
		
		logger.debug("acount_prtfeeall" + acount_prtfeeall);
		logger.debug("acount_bailall" + acount_bailall);
		//logger.debug("acount_chargeall" + acount_chargeall);
		logger.debug("count_all" + count_all);
		rmap.put("investCount", indto);
		rmap.put("detailList", detailList);
		rmap.put("resultcode", "0");
		return rmap;
	}
	/*
	 * 投资付款转出
	 */
	public String investPay(InvestPayVo payvo){
		
		//查询账户额度
		AccountInfo acountinfo  = fundMgrDao.queryUserAccount(payvo.getUser_id());
		if(acountinfo == null){//账户无效
			return "1";
		}
		//获取账户可用额度
		Double avalcount =  Double.parseDouble(acountinfo.getAcc_enchash());
		//本次交费额度
		Double paycount = Double.parseDouble(payvo.getPay_count());
		//如果额度为0则直接返回、不记账
		if(0.0d == paycount){
			return "0";
		}
		Double payfee;
		if(Constant.getTran_paytyp_pay().equals(payvo.getTran_paytyp())){
			//判断交费开关 是否支持后付费 0否1是
			if(Constant.getPay_flag().equals("0")){//不支持则判断账户余额是否充足
				if(paycount > avalcount){//余额不足
					return "2";
				}
			}
			payfee = -1d;
		}else{
			payfee = 1d;
		}
		//记账金额
		Double chargecount = payfee*paycount;
		//支持或者账户余额充足进行交费
		//增加交易流水信息
		Map<String,Object> seqmap = new HashMap<String,Object>();
		seqmap.put("transion_seq", payvo.getTransion_seq());
		seqmap.put("user_id", payvo.getUser_id());
		seqmap.put("transion_type", payvo.getUser_opttyp());
		seqmap.put("payment_type", payvo.getTran_paytyp());
		seqmap.put("amount", chargecount);
		
		investMgrDao.insertTranSeq(seqmap);
		//增加个人账户明细
		Map<String,Object> accountdtmap = new HashMap<String,Object>();
		accountdtmap.put("accout_no", acountinfo.getAccout_no());
		accountdtmap.put("transion_seq", payvo.getTransion_seq());
		accountdtmap.put("payment_type", payvo.getTran_paytyp());
		accountdtmap.put("amount", chargecount);
		fundMgrDao.insertUserAccountDetail(accountdtmap);
		//账户余额变动
		Map<String,Object> accountmap = new HashMap<String,Object>();
		accountmap.put("acount", chargecount);
		accountmap.put("user_id", payvo.getUser_id());
		fundMgrDao.updateUserAccount(accountmap);
		return "0";
	}
	/*
	 * 平台账目变动
	 */
	public String platReceive(PlatReceiveVo platReceiveVo){
		System.out.println("平台账目变动开始");
		//本次交费额度
		Double paycount = Double.parseDouble(platReceiveVo.getAmount());
		//如果额度为0则直接返回、不记账
		if(0.0d == paycount){
			return "0";
		}
		Double payfee;
		if(Constant.getTran_paytyp_pay().equals(platReceiveVo.getPayment_type())){
			payfee = -1d;
		}else{
			payfee = 1d;
		}
		//记账金额
		Double chargecount = payfee*paycount;
		//增加交易流水信息
		Map<String,Object> seqmap = new HashMap<String,Object>();
		seqmap.put("transion_seq", platReceiveVo.getTransion_seq());
		seqmap.put("user_id", Constant.getPlat_admin_userid());
		seqmap.put("transion_type", platReceiveVo.getTransion_type());
		seqmap.put("payment_type", platReceiveVo.getPayment_type());
		seqmap.put("amount", chargecount);
		investMgrDao.insertTranSeq(seqmap);
		//增加个人账户明细
		Map<String,Object> accountdtmap = new HashMap<String,Object>();
		accountdtmap.put("accout_no", Constant.getPlat_admin_acount());
		accountdtmap.put("transion_seq", platReceiveVo.getTransion_seq());
		accountdtmap.put("payment_type", platReceiveVo.getPayment_type());
		accountdtmap.put("amount", chargecount);
		fundMgrDao.insertUserAccountDetail(accountdtmap);
		//账户余额变动
		Map<String,Object> accountmap = new HashMap<String,Object>();
		accountmap.put("acount", chargecount);
		accountmap.put("user_id", Constant.getPlat_admin_userid());
		fundMgrDao.updateUserAccount(accountmap);
		System.out.println("平台账目变动结束");
		return "0";
	}
	
	/*
	 * 生成投资号码库
	 */
	public void produceInvestNo(InvestDetailDto detailDto){
		String startno = detailDto.getStart_no();
		String subcode = detailDto.getSub_code();
		int sno = Integer.parseInt(startno);
		int scode = Integer.parseInt(subcode);
		for(int i=0;i<scode;i++){
			Map<String,Object> paramap = new HashMap<String,Object>();
			paramap.put("user_invest_id", detailDto.getUser_invest_id());
			paramap.put("invest_prdouct_id", detailDto.getInvest_prdouct_id());
			paramap.put("basic_product_id", detailDto.getBasic_product_id());
			paramap.put("invest_no", sno + i);
			investMgrDao.insertInvestNo(paramap);
		}
	}
	/*
	 * 查询用户投资信息
	 */
	public List<InvestVo> queryInvestInfo(Page page,InvestMgrVo  investMgrVo){
		List<InvestVo> investlist = new ArrayList<InvestVo>();
		Map<String,Object> paramap = new HashMap<String,Object>();
		if("1".equals(page.getPageflag())){
			paramap = page.getParamMap();
			paramap.put("page", page);
		}else{
			paramap.put("page", page);
			paramap.put("user_id", investMgrVo.getUser_id());
			paramap.put("invest_status", investMgrVo.getInvest_status());
			paramap.put("invest_statusqry", investMgrVo.getInvest_statusqry());
			paramap.put("invt_product_name", investMgrVo.getInvt_product_name());
			paramap.put("invt_product_namelike", "%"+ AppUtil.nvlStr(investMgrVo.getInvt_product_name()) +"%");
			paramap.put("start_date", investMgrVo.getStart_date());
			paramap.put("end_date", investMgrVo.getEnd_date());
			
			page.setParamMap(paramap);
		}

		List<Invest> lv = investMgrDao.selectUserInvestInfo(paramap);
		for(Invest iv:lv){
			InvestVo  ivo =new InvestVo();
			ivo.setAcount_bailall(AppUtil.numFormat(iv.getAcount_bailall(), 2));
			ivo.setAcount_chargeall(AppUtil.numFormat(iv.getAcount_chargeall(), 2));
			ivo.setAcount_prtfeeall(AppUtil.numFormat(iv.getAcount_prtfeeall(), 2));
			ivo.setAmount_all(AppUtil.numFormat(iv.getAmount_all(), 2));
			ivo.setCreate_date(iv.getCreate_date());
			ivo.setInvest_prdouct_id(iv.getInvest_prdouct_id());
			ivo.setInvest_prdouct_name(iv.getInvest_prdouct_name());
			ivo.setInvest_status(iv.getInvest_status());
			ivo.setInvest_statusname(iv.getInvest_statusname());
			ivo.setInvest_type(iv.getInvest_type());
			ivo.setTransion_seq(iv.getTransion_seq());
			ivo.setUpdate_date(iv.getUpdate_date());
			ivo.setUser_id(iv.getUser_id());
			ivo.setUser_name(iv.getUser_name());
			ivo.setUser_invest_id(iv.getUser_invest_id());
			ivo.setUser_invest_profit(AppUtil.numFormat(iv.getUser_invest_profit(), 2));
			ivo.setUser_settle_profit(AppUtil.numFormat(iv.getUser_settle_profit(), 2));
			//判断是否可以录入中签结果
			String activedate = iv.getEnd_date();
			String nowdate = DateTimeFormatUtil.covertDateToString(DateTimeFormatUtil.getCurrentDate(),"yyyy-MM-dd");
			String editflag = "0";
			if(Constant.getInvest_status_arr().equals(iv.getInvest_status())){
				if(!DateTimeFormatUtil.compareDateByString(nowdate, activedate,"yyyy-MM-dd")){
					editflag = "1";
				}
			}
			ivo.setEditflag(editflag);
			investlist.add(ivo);
		}
		return investlist;
	}
	/*
	 * 查询用户投资信息
	 */
	public List<InvestVo> queryInvestNow(Page page,InvestMgrVo  investMgrVo){
		List<InvestVo> investlist = new ArrayList<InvestVo>();
		Map<String,Object> paramap = new HashMap<String,Object>();
		if("1".equals(page.getPageflag())){
			paramap = page.getParamMap();
			paramap.put("page", page);
		}else{
			paramap.put("page", page);
			paramap.put("user_id", investMgrVo.getUser_id());
			paramap.put("invest_status", investMgrVo.getInvest_status());
			paramap.put("invest_statusqry", investMgrVo.getInvest_statusqry());
			paramap.put("invt_product_name", investMgrVo.getInvt_product_name());
			paramap.put("invt_product_namelike", "%"+ AppUtil.nvlStr(investMgrVo.getInvt_product_name()) +"%");
			paramap.put("start_date", investMgrVo.getStart_date());
			paramap.put("end_date", investMgrVo.getEnd_date());
			
			page.setParamMap(paramap);
		}

		List<Invest> lv = investMgrDao.selectUserInvestNow(paramap);
		for(Invest iv:lv){
			InvestVo  ivo =new InvestVo();
			ivo.setAcount_bailall(AppUtil.numFormat(iv.getAcount_bailall(), 2));
			ivo.setAcount_chargeall(AppUtil.numFormat(iv.getAcount_chargeall(), 2));
			ivo.setAcount_prtfeeall(AppUtil.numFormat(iv.getAcount_prtfeeall(), 2));
			ivo.setAmount_all(AppUtil.numFormat(iv.getAmount_all(), 2));
			ivo.setCreate_date(iv.getCreate_date());
			ivo.setInvest_prdouct_id(iv.getInvest_prdouct_id());
			ivo.setInvest_prdouct_name(iv.getInvest_prdouct_name());
			ivo.setInvest_status(iv.getInvest_status());
			ivo.setInvest_statusname(iv.getInvest_statusname());
			ivo.setInvest_type(iv.getInvest_type());
			ivo.setTransion_seq(iv.getTransion_seq());
			ivo.setUpdate_date(iv.getUpdate_date());
			ivo.setUser_id(iv.getUser_id());
			ivo.setUser_name(iv.getUser_name());
			ivo.setUser_invest_id(iv.getUser_invest_id());
			ivo.setUser_invest_profit(AppUtil.numFormat(iv.getUser_invest_profit(), 2));
			ivo.setUser_settle_profit(AppUtil.numFormat(iv.getUser_settle_profit(), 2));
			//判断是否可以录入中签结果
			String activedate = iv.getEnd_date();
			String nowdate = DateTimeFormatUtil.covertDateToString(DateTimeFormatUtil.getCurrentDate(),"yyyy-MM-dd");
			String editflag = "0";
			if(Constant.getInvest_status_arr().equals(iv.getInvest_status())){
				if(!DateTimeFormatUtil.compareDateByString(nowdate, activedate,"yyyy-MM-dd")){
					editflag = "1";
				}
			}
			ivo.setEditflag(editflag);
			investlist.add(ivo);
		}
		return investlist;
	}
	/*
	 * 查询用户投资详细信息
	 */
	public List<InvestVo> queryInvestMoreInfo(String  user_invest_id){
		List<InvestVo> investlist = new ArrayList<InvestVo>();
		List<Invest> lv = investMgrDao.selectUserInvestMoreInfo(user_invest_id);
		for(Invest iv:lv){
			InvestVo  ivo =new InvestVo();
			ivo.setAcount_bailall(AppUtil.numFormat(iv.getAcount_bailall(), 2));
			ivo.setAcount_chargeall(AppUtil.numFormat(iv.getAcount_chargeall(), 2));
			ivo.setAcount_prtfeeall(AppUtil.numFormat(iv.getAcount_prtfeeall(), 2));
			ivo.setAmount_all(AppUtil.numFormat(iv.getAmount_all(), 2));
			ivo.setCreate_date(iv.getCreate_date());
			ivo.setInvest_prdouct_id(iv.getInvest_prdouct_id());
			ivo.setInvest_prdouct_name(iv.getInvest_prdouct_name());
			ivo.setInvest_status(iv.getInvest_status());
			ivo.setInvest_statusname(iv.getInvest_statusname());
			ivo.setInvest_type(iv.getInvest_type());
			ivo.setTransion_seq(iv.getTransion_seq());
			ivo.setUpdate_date(iv.getUpdate_date());
			ivo.setUser_id(iv.getUser_id());
			ivo.setUser_name(iv.getUser_name());
			ivo.setUser_invest_id(iv.getUser_invest_id());
			ivo.setUser_invest_profit(AppUtil.numFormat(iv.getUser_invest_profit(), 2));
			ivo.setUser_settle_profit(AppUtil.numFormat(iv.getUser_settle_profit(), 2));
			ivo.setBasic_product_id(iv.getBasic_product_id());
			ivo.setBasic_product_name(iv.getBasic_product_name());
			ivo.setStock_code(iv.getStock_code());
			ivo.setResult_no(iv.getResult_no());
			ivo.setStart_no(iv.getStart_no());
			ivo.setSub_code(iv.getSub_code());
			ivo.setPraise_count(iv.getPraise_count());
			ivo.setInvt_product_status(iv.getInvt_product_status());
			investlist.add(ivo);
		}
		return investlist;
	}
	
	public InvestMgrDao getInvestMgrDao() {
		return investMgrDao;
	}
	public void setInvestMgrDao(InvestMgrDao investMgrDao) {
		this.investMgrDao = investMgrDao;
	}

	public ProductMgrDao getProductMgrDao() {
		return productMgrDao;
	}

	public void setProductMgrDao(ProductMgrDao productMgrDao) {
		this.productMgrDao = productMgrDao;
	}

	public FundMgrDao getFundMgrDao() {
		return fundMgrDao;
	}

	public void setFundMgrDao(FundMgrDao fundMgrDao) {
		this.fundMgrDao = fundMgrDao;
	}

	public IssueMgrDao getIssueMgrDao() {
		return issueMgrDao;
	}

	public void setIssueMgrDao(IssueMgrDao issueMgrDao) {
		this.issueMgrDao = issueMgrDao;
	}
}
