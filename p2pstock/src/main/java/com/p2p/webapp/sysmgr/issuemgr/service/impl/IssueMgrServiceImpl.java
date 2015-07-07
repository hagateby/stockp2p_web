package com.p2p.webapp.sysmgr.issuemgr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.common.util.DateTimeFormatUtil;
import com.p2p.webapp.sysmgr.issuemgr.dao.IssueMgrDao;
import com.p2p.webapp.sysmgr.issuemgr.entity.InvestMoreInfo;
import com.p2p.webapp.sysmgr.issuemgr.entity.InvestProduct;
import com.p2p.webapp.sysmgr.issuemgr.service.IssueMgrService;
import com.p2p.webapp.sysmgr.issuemgr.vo.InvestMoreInfoVo;
import com.p2p.webapp.sysmgr.issuemgr.vo.InvestProductVo;
import com.p2p.webapp.sysmgr.productmgr.dao.ProductMgrDao;

public class IssueMgrServiceImpl implements IssueMgrService{
	
	private IssueMgrDao issueMgrDao;
	private ProductMgrDao productMgrDao;
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(IssueMgrServiceImpl.class);
	/*
	 * 查询发行产品信息
	 */
	public List<InvestProductVo> queryIssurInfo(Page page,InvestProductVo ivo){
		List<InvestProductVo> rlist = new ArrayList<InvestProductVo>();
		List<InvestProduct> qlist = new ArrayList<InvestProduct>();
		Map<String,Object> parammap = new HashMap<String,Object>();
		if("1".equals(page.getPageflag())){
			parammap = page.getParamMap();
			parammap.put("page", page);
		}else{
			parammap.put("page", page);
			parammap.put("invt_product_name", AppUtil.nvlStr(ivo.getInvt_product_name()));
			parammap.put("invt_product_namelike", "%"+ AppUtil.nvlStr(ivo.getInvt_product_name()) +"%");
			parammap.put("user_id", ivo.getUser_id());
			parammap.put("start_date",  AppUtil.nvlStr(ivo.getStart_date()));
			parammap.put("end_date",  AppUtil.nvlStr(ivo.getEnd_date()));
			parammap.put("invt_product_status",  AppUtil.nvlStr(ivo.getInvt_product_status()));
			
			page.setParamMap(parammap);
		}
		qlist = issueMgrDao.selectInvestProductlist(parammap);
		for(InvestProduct ipo:qlist){
			InvestProductVo itmp = new InvestProductVo();
			itmp.setAmount(AppUtil.numFormat(ipo.getAmount(), 2));
			itmp.setInvestend_date(ipo.getInvestend_date());
			itmp.setInvt_product_id(ipo.getInvt_product_id());
			itmp.setInvt_product_name(ipo.getInvt_product_name());
			itmp.setInvt_product_status(ipo.getInvt_product_status());
			itmp.setInvt_product_statusname(ipo.getInvt_product_statusname());
			itmp.setProtol_id(ipo.getProtol_id());
			itmp.setRisk_lvl(ipo.getRisk_lvl());
			itmp.setInveststart_date(ipo.getInveststart_date());
			itmp.setUser_id(ipo.getUser_id());
			itmp.setIssue_fee(AppUtil.numFormat(ipo.getIssue_fee(),2));
			itmp.setUser_name(ipo.getUser_name());
			String invest_rate = issueMgrDao.selectInvestRate(ipo.getInvt_product_id());
			itmp.setInvest_rate(AppUtil.numFormat(invest_rate, 2));
			itmp.setFlag(ipo.getFlag());
			rlist.add(itmp);
		}
		return rlist;
	}
	/*
	 * 查询可投资产品信息
	 */
	public List<InvestProductVo> queryActiveIssurInfo(Page page){
		List<InvestProductVo> rlist = new ArrayList<InvestProductVo>();
		List<InvestProduct> qlist = new ArrayList<InvestProduct>();
		Map<String,Object> parammap = new HashMap<String,Object>();
		if("1".equals(page.getPageflag())){
			parammap.put("page", page);
		}else{
			parammap.put("page", page);
		}
		qlist = issueMgrDao.selectActiveProductlist();
		for(InvestProduct ipo:qlist){
			InvestProductVo itmp = new InvestProductVo();
			itmp.setAmount(AppUtil.numFormat(ipo.getAmount(), 2));
			itmp.setInvestend_date(ipo.getInvestend_date());
			itmp.setInvt_product_id(ipo.getInvt_product_id());
			itmp.setInvt_product_name(ipo.getInvt_product_name());
			itmp.setInvt_product_status(ipo.getInvt_product_status());
			itmp.setInvt_product_statusname(ipo.getInvt_product_statusname());
			itmp.setProtol_id(ipo.getProtol_id());
			itmp.setRisk_lvl(ipo.getRisk_lvl());
			itmp.setInveststart_date(ipo.getInveststart_date());
			itmp.setUser_id(ipo.getUser_id());
			itmp.setIssue_fee(AppUtil.numFormat(ipo.getIssue_fee(),2));
			itmp.setUser_name(ipo.getUser_name());
			rlist.add(itmp);
		}
		return rlist;
	}
	/*
	 * 查询历史投资产品信息
	 */
	public List<InvestProductVo> queryHisIssurInfo(Page page){
		List<InvestProductVo> rlist = new ArrayList<InvestProductVo>();
		List<InvestProduct> qlist = new ArrayList<InvestProduct>();
		Map<String,Object> parammap = new HashMap<String,Object>();
		if("1".equals(page.getPageflag())){
			parammap.put("page", page);
		}else{
			parammap.put("page", page);
		}
		qlist = issueMgrDao.selectHisProductlist();
		for(InvestProduct ipo:qlist){
			InvestProductVo itmp = new InvestProductVo();
			itmp.setAmount(AppUtil.numFormat(ipo.getAmount(), 2));
			itmp.setInvestend_date(ipo.getInvestend_date());
			itmp.setInvt_product_id(ipo.getInvt_product_id());
			itmp.setInvt_product_name(ipo.getInvt_product_name());
			itmp.setInvt_product_status(ipo.getInvt_product_status());
			itmp.setInvt_product_statusname(ipo.getInvt_product_statusname());
			itmp.setProtol_id(ipo.getProtol_id());
			itmp.setRisk_lvl(ipo.getRisk_lvl());
			itmp.setInveststart_date(ipo.getInveststart_date());
			itmp.setUser_id(ipo.getUser_id());
			itmp.setIssue_fee(AppUtil.numFormat(ipo.getIssue_fee(),2));
			itmp.setUser_name(ipo.getUser_name());
			String invest_rate = issueMgrDao.selectInvestRate(ipo.getInvt_product_id());
			itmp.setInvest_rate(AppUtil.numFormat(invest_rate, 2));
			rlist.add(itmp);
		}
		return rlist;
	}
	/*
	 * 增加投资产品
	 */
	public String addInvestInfo(InvestProductVo invo){
		
		//插入投资产品主表
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("user_id", invo.getUser_id());
		paramap.put("invt_product_name", invo.getInvt_product_name());
		paramap.put("risk_lvl", invo.getRisk_lvl());
		paramap.put("invt_product_status", invo.getInvt_product_status());
		paramap.put("start_date", invo.getStart_date());
		paramap.put("end_date", invo.getEnd_date());
		paramap.put("amount", invo.getAmount());
		paramap.put("protol_id", invo.getProtol_id());
		paramap.put("issue_fee", AppUtil.nvlNum(invo.getIssue_fee()));
		
		paramap.put("starter_bail", invo.getStarter_bail());
		paramap.put("invester_bail", invo.getInvester_bail());
		paramap.put("launcher_accruedcharges_type", invo.getLauncher_accruedcharges_type());
		paramap.put("launcher_accruedcharges_amount", invo.getLauncher_accruedcharges_amount());
		paramap.put("user_accruedcharges_type", invo.getUser_accruedcharges_type());
		paramap.put("user_accruedcharges_amount", invo.getUser_accruedcharges_amount());
		
		issueMgrDao.insertInvestProduct(paramap);
		//获取最近插入的序列
		String invt_product_id = productMgrDao.queryMaxLastSq();
		//插入invest-base关系表
		if(invo.getBase_product_idarray() != null && !"".equals(invo.getBase_product_idarray())){
			String basearray[] =  invo.getBase_product_idarray().split(",");
			for(String s:basearray){
				Map<String,Object> pmap = new HashMap<String,Object>();
				pmap.put("invt_product_id", invt_product_id);
				pmap.put("basic_product_id", s);
				issueMgrDao.insertInvestBase(pmap);
			}
		}
		return "0";
	}
	/*
	 * 查询详情
	 */
	public Map<String,Object> queryMoreinfo(String invest_product_id){
		Map<String,Object>  rmap = new HashMap<String,Object>();
		
		List<InvestMoreInfo> rlm = issueMgrDao.selectInvestMoreInfo(invest_product_id);
		InvestProductVo rvo = new InvestProductVo();
		List<InvestMoreInfoVo> rlist = new ArrayList<InvestMoreInfoVo>();
		String base_product_idarray ="";
		//产品总中签数
		int lot_count = issueMgrDao.selectInvtLovCount(invest_product_id);
		rvo.setLot_count(String.valueOf(lot_count));
		//产品总投资额
		String invest_countall = issueMgrDao.selectInvtCountAll(invest_product_id);
		rvo.setInvest_all(AppUtil.numFormat(invest_countall,2));
		
		for(InvestMoreInfo im:rlm){
			//投资产品信息
			
			rvo.setEnd_date(im.getInvestend_date());
			rvo.setInvt_product_id(im.getInvt_product_id());
			rvo.setInvt_product_name(im.getInvt_product_name());
			rvo.setInvt_product_status(im.getInvt_product_status());
			rvo.setIssue_fee(AppUtil.numFormat(im.getIssue_fee(),2));
			rvo.setStart_date(im.getInveststart_date());
			rvo.setUser_id(im.getUser_id());
			rvo.setUser_name(im.getUser_name());
			
			rvo.setInvester_bail(AppUtil.numFormat(im.getInvester_bail(),2));
			rvo.setLauncher_accruedcharges_amount(AppUtil.numFormat(im.getLauncher_accruedcharges_amount(),2));
			rvo.setLauncher_accruedcharges_type(im.getLauncher_accruedcharges_type());
			rvo.setLauncher_accruedcharges_typename(im.getLauncher_accruedcharges_typename());
			rvo.setStarter_bail(im.getStarter_bail());
			rvo.setUser_accruedcharges_amount(AppUtil.numFormat(im.getUser_accruedcharges_amount(),2));
			rvo.setUser_accruedcharges_type(im.getUser_accruedcharges_type());
			rvo.setUser_accruedcharges_typename(im.getUser_accruedcharges_typename());
			
			base_product_idarray = base_product_idarray + im.getBasic_product_id() + ",";
			//列表信息
			InvestMoreInfoVo imvo = new InvestMoreInfoVo();
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
			imvo.setUp_limit(AppUtil.numFormat(im.getUp_limit(),0));
			imvo.setUser_accruedcharges_amount(AppUtil.numFormat(im.getUser_accruedcharges_amount(),2));
			imvo.setUser_accruedcharges_type(im.getUser_accruedcharges_type());
			imvo.setUser_accruedcharges_typename(im.getUser_accruedcharges_typename());
			imvo.setUser_id(im.getUser_id());
			imvo.setUser_name(im.getUser_name());
			imvo.setLot_date(im.getLot_date());
			//判断是否可以录入中签结果
			Date activedate = DateTimeFormatUtil.covertStringToDate(im.getLot_date(),DateTimeFormatUtil.YEAR_MONTH_DAY_TEMPLATE);
			Date nowdate = DateTimeFormatUtil.getCurrentDate();
			if("0".equals(DateTimeFormatUtil.compareByDate(nowdate, activedate)) || "2".equals(DateTimeFormatUtil.compareByDate(nowdate, activedate))){
				imvo.setLot_flag("1");
				logger.debug("Lot_flag==="+imvo.getLot_flag());
			}
			rlist.add(imvo);
		}
		rvo.setBase_product_idarray(base_product_idarray);
		//判断是否可以录入中签结果
		Date activedate = DateTimeFormatUtil.covertStringToDate(rvo.getEnd_date(),DateTimeFormatUtil.YEAR_MONTH_DAY_TEMPLATE);
		Date nowdate = DateTimeFormatUtil.getCurrentDate();
		if("0".equals(DateTimeFormatUtil.compareByDate(nowdate, activedate)) || "2".equals(DateTimeFormatUtil.compareByDate(nowdate, activedate))){
			rvo.setDateflag("1");
		}else{
			rvo.setDateflag("0");
		}
		
		rmap.put("rvo", rvo);
		rmap.put("rlist", rlist);
		return rmap;
	}
	/*
	 * 删除投资产品
	 */
	public String delInvestProduct(String invest_product_id){
		
		issueMgrDao.delInvestProduct(invest_product_id);
		return "0";
	}
	/*
	 * 修改投资产品
	 */
	public String editInvestProduct(InvestProductVo invo){
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("user_id", invo.getUser_id());
		paramap.put("invt_product_name", invo.getInvt_product_name());
		paramap.put("risk_lvl", invo.getRisk_lvl());
		paramap.put("invt_product_status", invo.getInvt_product_status());
		paramap.put("start_date", invo.getStart_date());
		paramap.put("end_date", invo.getEnd_date());
		paramap.put("amount", invo.getAmount());
		paramap.put("protol_id", invo.getProtol_id());
		paramap.put("issue_fee", AppUtil.nvlNum(invo.getIssue_fee()));
		paramap.put("invt_product_id", invo.getInvt_product_id());
		paramap.put("starter_bail", invo.getStarter_bail());
		paramap.put("invester_bail", invo.getInvester_bail());
		paramap.put("launcher_accruedcharges_type", invo.getLauncher_accruedcharges_type());
		paramap.put("launcher_accruedcharges_amount", invo.getLauncher_accruedcharges_amount());
		paramap.put("user_accruedcharges_type", invo.getUser_accruedcharges_type());
		paramap.put("user_accruedcharges_amount", invo.getUser_accruedcharges_amount());
		
		issueMgrDao.updateInvestProduct(paramap);
		issueMgrDao.delInvestBase(invo.getInvt_product_id());
		//插入invest-base关系表
		if(invo.getBase_product_idarray() != null && !"".equals(invo.getBase_product_idarray())){
			String basearray[] =  invo.getBase_product_idarray().split(",");
			for(String s:basearray){
				Map<String,Object> pmap = new HashMap<String,Object>();
				pmap.put("invt_product_id", invo.getInvt_product_id());
				pmap.put("basic_product_id", s);
				issueMgrDao.insertInvestBase(pmap);
			}
		}
		return "0";
	}
	
	public IssueMgrDao getIssueMgrDao() {
		return issueMgrDao;
	}

	public void setIssueMgrDao(IssueMgrDao issueMgrDao) {
		this.issueMgrDao = issueMgrDao;
	}

	public ProductMgrDao getProductMgrDao() {
		return productMgrDao;
	}

	public void setProductMgrDao(ProductMgrDao productMgrDao) {
		this.productMgrDao = productMgrDao;
	}
	 
}
