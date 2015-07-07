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
	//д��־����
	public static Logger logger = LoggerFactory.getLogger(InvestMgrServiceImpl.class);
	/*
	 * �û�Ͷ��
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
		//��������
		investMgrDao.insertInvestInfo(paramap);
		String user_invest_id = investMgrDao.queryMaxLastSq();
		//��ϸ��
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
			//���ɺ����
			/*
			detaildto.setUser_invest_id(user_invest_id);
			produceInvestNo(detaildto);
			*/
		}
		
		return "0";
	}
	/*
	 * Ͷ�����
	 */
	public String auditInvest(InvestMgrVo investMgrVo){
		
		String auditflag = investMgrVo.getAuditflag();
		//��˲�ͨ�� ��Ϊ��ɾ��
		if("0".equals(auditflag)){
			Map<String,Object> paramap = new HashMap<String,Object>();
			paramap.put("invest_status", Constant.getInvest_status_del());
			paramap.put("user_invest_id", investMgrVo.getUser_invest_id());
			investMgrDao.updateInvestStatusByUser(paramap);
			investMgrDao.updateInvestDetailStatusByUser(paramap);
			
		}else if("1".equals(auditflag)){//���ͨ��
			//�۳���֤��
			Invest invest = investMgrDao.queryInvest(investMgrVo.getUser_invest_id());
			//��ʱ��֧�����½��ѣ���˴˴�ֱ�ӵ��ø��ѹ��ܿ۳���֤��
			Double trancount = Double.parseDouble(invest.getAcount_bailall());
			//�����˻�ת����֤��-ƽ̨�˻�ת�뱣֤��
			InvestPayVo payvo = new InvestPayVo();
			payvo.setUser_id(invest.getUser_id());
			payvo.setPay_count(trancount.toString());
			payvo.setTransion_seq(invest.getTransion_seq());
			payvo.setUser_opttyp(Constant.getUser_opttyp_buy());
			payvo.setTran_paytyp(Constant.getTran_paytyp_pay());
			investPay(payvo);
			//�޸�Ͷ��״̬ΪͶ�����
			/*
			Map<String,Object> investmap = new HashMap<String,Object>();
			investmap.put("invest_status", Constant.getInvest_status_feeok());
			investmap.put("user_invest_id", user_invest_id);	
			*/		
			//ƽ̨�˻�����
			PlatReceiveVo platReceiveVo = new PlatReceiveVo();
			platReceiveVo.setAmount(trancount.toString());
			platReceiveVo.setPayment_type(Constant.getTran_paytyp_rec());
			platReceiveVo.setTransion_seq(invest.getTransion_seq());
			platReceiveVo.setTransion_type(Constant.getUser_opttyp_buy());
			platReceive(platReceiveVo);
			//investMgrDao.updateInvestStatusByUser(investmap);
			//��ΪͶ�ʳɹ�
			Map<String,Object> paramap = new HashMap<String,Object>();
			paramap.put("invest_status", Constant.getInvest_status_feeok());
			paramap.put("user_invest_id", investMgrVo.getUser_invest_id());
			investMgrDao.updateInvestStatusByUser(paramap);
			investMgrDao.updateInvestDetailStatusByUser(paramap);
		}
		
		return "0";
	}
	
	
	/*
	 * �û�Ͷ���޸�
	 */
	public String editInvest(InvestMgrVo imvo){
		
		//ɾ��ԭͶ��
		investMgrDao.delInvestInfo(imvo.getUser_invest_id());
		investMgrDao.delInvestDetail(imvo.getUser_invest_id());
		userInvest(imvo);
		return "0";
	}
	
	/*
	 * �û�Ͷ���޸ĳ�ʼ��
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
			if("1".equals(im.getSales_city())){//����һ��ǩ��Ϊ1000��
				feecntarray = feecntarray + "1000" + ",";
			}else{//����һ��ǩ��Ϊ500��
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
		//��Ʒ��Ͷ�ʶ�
		String invest_countall = issueMgrDao.selectInvtCountAll(investMainVo.getInvt_product_id());
		investMainVo.setAmount(AppUtil.numFormat(invest_countall,2));
		
		rmap.put("investMainVo", investMainVo);
		rmap.put("rlist", rlist);
		return rmap;
	}
	
	/*
	 * Ͷ�ʷ��ü���
	 * basic_product_idarray ��ƷID(�����,�ָ�) start_noarray��ʼ����(�����,�ָ�)sub_codearray ��������(�����,�ָ�)
	 */
	public Map<String,Object> investCount(FeeCountDto feeCountDto){
		String basic_product_idarray = feeCountDto.getBasic_product_idarray();
		String start_noarray = feeCountDto.getStart_noarray();
		String sub_codearray = feeCountDto.getSub_codearray();
		String invest_prdouct_id = feeCountDto.getInvest_prdouct_id();
		
		Map<String,Object> rmap = new HashMap<String,Object>();
		InvestCountDto  indto = new InvestCountDto();
		List<InvestDetailDto> detailList = new ArrayList<InvestDetailDto>();
		//Ϊ��ֹ�ͻ���α�������֤��������������ȡ��ʽ���������ȡ��� �����ݿ����»�ȡ
		//��ȡ��֤�����
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
			//�˴���̨�ж�ļ�����������Ƿ����
			Date activedate = DateTimeFormatUtil.covertStringToDate(bp.getEnd_date(),DateTimeFormatUtil.YEAR_MONTH_DAY_HOUR_MINUTE_TEMPLATE);
			Date nowdate = DateTimeFormatUtil.getCurrentDate();
			if("2".equals(DateTimeFormatUtil.compareByDate(nowdate, activedate))){
				rmap.put("resultcode", "1");
				return rmap;
			}
			String sail_city = bp.getSales_city();
			//����
			Double fprice = Double.parseDouble(bp.getPrice());
			//����
			Double fsubcode =  Double.parseDouble(subcodearray[i]);
			//��Ʒ�����
			Double facount_prtfee = fprice*fsubcode ;
			//��֤�����
			Double finvestbail = Double.parseDouble(invester_bail);
			Double facountbail = (facount_prtfee*finvestbail)/100.00d;
			
			if("1".equals(sail_city)){//����һ��ǩ��Ϊ1000��
				facountbail = 1000.00f*facountbail;
				facount_prtfee = 1000.00f*facount_prtfee;
			}else{
				facountbail = 500.00f*facountbail;
				facount_prtfee = 500.00f*facount_prtfee;
			}
			acount_prtfeeall = acount_prtfeeall + facount_prtfee;
			acount_bailall = acount_bailall + facountbail;
			
			//��Ʒ�����
			/*
			Float chargecount = Float.parseFloat(bp.getUser_accruedcharges_amount());
			if(Constant.getProduct_accchargetyp_scale().equals(bp.getUser_accruedcharges_type())){//������
				chargecount = (facount_prtfee*chargecount)/100.00f;
				acount_chargeall = acount_chargeall + chargecount;
			}else{//�����
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
	 * Ͷ�ʸ���ת��
	 */
	public String investPay(InvestPayVo payvo){
		
		//��ѯ�˻����
		AccountInfo acountinfo  = fundMgrDao.queryUserAccount(payvo.getUser_id());
		if(acountinfo == null){//�˻���Ч
			return "1";
		}
		//��ȡ�˻����ö��
		Double avalcount =  Double.parseDouble(acountinfo.getAcc_enchash());
		//���ν��Ѷ��
		Double paycount = Double.parseDouble(payvo.getPay_count());
		//������Ϊ0��ֱ�ӷ��ء�������
		if(0.0d == paycount){
			return "0";
		}
		Double payfee;
		if(Constant.getTran_paytyp_pay().equals(payvo.getTran_paytyp())){
			//�жϽ��ѿ��� �Ƿ�֧�ֺ󸶷� 0��1��
			if(Constant.getPay_flag().equals("0")){//��֧�����ж��˻�����Ƿ����
				if(paycount > avalcount){//����
					return "2";
				}
			}
			payfee = -1d;
		}else{
			payfee = 1d;
		}
		//���˽��
		Double chargecount = payfee*paycount;
		//֧�ֻ����˻���������н���
		//���ӽ�����ˮ��Ϣ
		Map<String,Object> seqmap = new HashMap<String,Object>();
		seqmap.put("transion_seq", payvo.getTransion_seq());
		seqmap.put("user_id", payvo.getUser_id());
		seqmap.put("transion_type", payvo.getUser_opttyp());
		seqmap.put("payment_type", payvo.getTran_paytyp());
		seqmap.put("amount", chargecount);
		
		investMgrDao.insertTranSeq(seqmap);
		//���Ӹ����˻���ϸ
		Map<String,Object> accountdtmap = new HashMap<String,Object>();
		accountdtmap.put("accout_no", acountinfo.getAccout_no());
		accountdtmap.put("transion_seq", payvo.getTransion_seq());
		accountdtmap.put("payment_type", payvo.getTran_paytyp());
		accountdtmap.put("amount", chargecount);
		fundMgrDao.insertUserAccountDetail(accountdtmap);
		//�˻����䶯
		Map<String,Object> accountmap = new HashMap<String,Object>();
		accountmap.put("acount", chargecount);
		accountmap.put("user_id", payvo.getUser_id());
		fundMgrDao.updateUserAccount(accountmap);
		return "0";
	}
	/*
	 * ƽ̨��Ŀ�䶯
	 */
	public String platReceive(PlatReceiveVo platReceiveVo){
		System.out.println("ƽ̨��Ŀ�䶯��ʼ");
		//���ν��Ѷ��
		Double paycount = Double.parseDouble(platReceiveVo.getAmount());
		//������Ϊ0��ֱ�ӷ��ء�������
		if(0.0d == paycount){
			return "0";
		}
		Double payfee;
		if(Constant.getTran_paytyp_pay().equals(platReceiveVo.getPayment_type())){
			payfee = -1d;
		}else{
			payfee = 1d;
		}
		//���˽��
		Double chargecount = payfee*paycount;
		//���ӽ�����ˮ��Ϣ
		Map<String,Object> seqmap = new HashMap<String,Object>();
		seqmap.put("transion_seq", platReceiveVo.getTransion_seq());
		seqmap.put("user_id", Constant.getPlat_admin_userid());
		seqmap.put("transion_type", platReceiveVo.getTransion_type());
		seqmap.put("payment_type", platReceiveVo.getPayment_type());
		seqmap.put("amount", chargecount);
		investMgrDao.insertTranSeq(seqmap);
		//���Ӹ����˻���ϸ
		Map<String,Object> accountdtmap = new HashMap<String,Object>();
		accountdtmap.put("accout_no", Constant.getPlat_admin_acount());
		accountdtmap.put("transion_seq", platReceiveVo.getTransion_seq());
		accountdtmap.put("payment_type", platReceiveVo.getPayment_type());
		accountdtmap.put("amount", chargecount);
		fundMgrDao.insertUserAccountDetail(accountdtmap);
		//�˻����䶯
		Map<String,Object> accountmap = new HashMap<String,Object>();
		accountmap.put("acount", chargecount);
		accountmap.put("user_id", Constant.getPlat_admin_userid());
		fundMgrDao.updateUserAccount(accountmap);
		System.out.println("ƽ̨��Ŀ�䶯����");
		return "0";
	}
	
	/*
	 * ����Ͷ�ʺ����
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
	 * ��ѯ�û�Ͷ����Ϣ
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
			//�ж��Ƿ����¼����ǩ���
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
	 * ��ѯ�û�Ͷ����Ϣ
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
			//�ж��Ƿ����¼����ǩ���
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
	 * ��ѯ�û�Ͷ����ϸ��Ϣ
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
