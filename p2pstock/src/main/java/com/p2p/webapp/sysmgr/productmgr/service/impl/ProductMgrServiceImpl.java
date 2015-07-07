package com.p2p.webapp.sysmgr.productmgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.sysmgr.productmgr.dao.ProductMgrDao;
import com.p2p.webapp.sysmgr.productmgr.entity.BaseProduct;
import com.p2p.webapp.sysmgr.productmgr.service.ProductMgrService;
import com.p2p.webapp.sysmgr.productmgr.vo.BaseProductVo;
import com.p2p.webapp.system.parammgr.entity.SystemParam;
import com.p2p.webapp.system.parammgr.service.ParamMgrService;
import com.p2p.webapp.system.parammgr.service.impl.ParamMgrServiceImpl;

public class ProductMgrServiceImpl implements ProductMgrService{
	
	private ProductMgrDao productMgrDao;
	public static Logger logger = LoggerFactory.getLogger(ProductMgrServiceImpl.class);
	/*
	 * 增加基础标的
	 */
	public String addBasicStockProduct(BaseProductVo bvo){
		
		
		//插入基础标的信息表
		Map<String,String> parammap = new HashMap<String,String>();
		parammap.put("basic_product_name", bvo.getBasic_product_name());
		parammap.put("basic_product_type", bvo.getBasic_product_type());
		parammap.put("basic_invest_type", bvo.getBasic_invest_type());
		parammap.put("starter_bail", AppUtil.nvlNum(bvo.getStarter_bail()));
		parammap.put("invester_bail", AppUtil.nvlNum(bvo.getInvester_bail()));
		parammap.put("launcher_accruedcharges_type", bvo.getLauncher_accruedcharges_type());
		parammap.put("launcher_accruedcharges_amount", AppUtil.nvlNum(bvo.getLauncher_accruedcharges_amount()));
		parammap.put("user_accruedcharges_type", bvo.getUser_accruedcharges_type());
		parammap.put("user__accruedcharges_amount", AppUtil.nvlNum(bvo.getUser_accruedcharges_amount()));
		parammap.put("input_web", "");
		productMgrDao.insertBasicProduct(parammap);
		String nextid = productMgrDao.queryMaxLastSq();
		logger.debug("nextid:"+nextid);
		//插入基础标的彩票信息表
		Map<String,String> parammap2 = new HashMap<String,String>();
		parammap2.put("basic_product_id", nextid);
		parammap2.put("invest_product_id", bvo.getInvest_product_id());
		parammap2.put("sales_city", bvo.getSales_city());
		parammap2.put("price", AppUtil.nvlNum(bvo.getPrice()));
		parammap2.put("start_date", bvo.getStart_date());
		parammap2.put("end_date", bvo.getEnd_date());
		parammap2.put("up_limit", AppUtil.nvlNum(bvo.getUp_limit()));
		parammap2.put("low_limit", AppUtil.nvlNum(bvo.getLow_limit()));
		parammap2.put("lot_date", bvo.getLot_date());
		parammap2.put("fundfree_date", bvo.getFundfree_date());
		parammap2.put("lot_rate", AppUtil.nvlNum(bvo.getLot_rate()));
		parammap2.put("apply_code", bvo.getApply_code());
		productMgrDao.insertProductStock(parammap2);
		return "0";
	}
	/*
	 * 查询基础标的列表
	 */
	public List<BaseProductVo> queryBaseProductList(Page page,BaseProductVo paravo){
		ParamMgrService ps= new ParamMgrServiceImpl();
		List<BaseProductVo> rlist  = new ArrayList<BaseProductVo>();
		List<BaseProduct> dlist = new ArrayList<BaseProduct>();
		Map parammap = new HashMap();
		if("1".equals(page.getPageflag())){
			parammap = page.getParamMap();
			parammap.put("page", page);
		}else{
			parammap.put("page", page);
			parammap.put("basic_product_name", AppUtil.nvlStr(paravo.getBasic_product_name()));
			parammap.put("basic_product_namelike", "%"+AppUtil.nvlStr(paravo.getBasic_product_name())+"%");
			parammap.put("invest_product_id", AppUtil.nvlStr(paravo.getInvest_product_id()));
			parammap.put("lot_date", AppUtil.nvlStr(paravo.getLot_date()));
			parammap.put("sales_city", AppUtil.nvlStr(paravo.getSales_city()));
			parammap.put("start_date", AppUtil.nvlStr(paravo.getStart_date()));
			parammap.put("end_date", AppUtil.nvlStr(paravo.getEnd_date()));
			page.setParamMap(parammap);
		}
		
		dlist = productMgrDao.selectBasicProductlist(parammap);
		for(BaseProduct bp:dlist){
			BaseProductVo bvo =new BaseProductVo();
			bvo.setBasic_invest_type(bp.getBasic_invest_type());
			bvo.setBasic_invest_typename(bp.getBasic_invest_typename());
			bvo.setBasic_product_id(bp.getBasic_product_id());
			bvo.setBasic_product_name(bp.getBasic_product_name());
			bvo.setBasic_product_stock_id(bp.getBasic_product_stock_id());
			bvo.setBasic_product_type(bp.getBasic_product_type());
			bvo.setBasic_product_typename(bp.getBasic_product_typename());
			bvo.setCreate_date(bp.getCreate_date());
			bvo.setEnd_date(bp.getEnd_date());
			bvo.setInput_web(bp.getInput_web());
			bvo.setInvest_product_id(bp.getInvest_product_id());
			bvo.setInvester_bail(bp.getInvester_bail());
			bvo.setLauncher_accruedcharges_amount(bp.getLauncher_accruedcharges_amount());
			bvo.setLauncher_accruedcharges_type(bp.getLauncher_accruedcharges_type());
			bvo.setLot_date(bp.getLot_date());
			bvo.setLow_limit(bp.getLow_limit());
			bvo.setPrice(bp.getPrice());
			bvo.setSales_city(bp.getSales_city());
			SystemParam sp = ps.querySystemByValue(bp.getSales_city(),Constant.getProduct_city());
			bvo.setSales_cityname(sp.getPara_name());
			bvo.setStart_date(bp.getStart_date());
			bvo.setStarter_bail(bp.getStarter_bail());
			bvo.setUp_limit(bp.getUp_limit());
			bvo.setUpdate_date(bp.getUpdate_date());
			bvo.setUser_accruedcharges_amount(bp.getUser_accruedcharges_amount());
			bvo.setUser_accruedcharges_type(bp.getUser_accruedcharges_type());
			bvo.setFlag(bp.getFlag());
			rlist.add(bvo);
		}
		
		return rlist;
	}
	/*
	 * 查询详细信息
	 */
	public BaseProductVo queryMoreInfo(String basic_product_id){
		BaseProductVo bvo = new BaseProductVo();
		BaseProduct bp = productMgrDao.selectBasicProductById(basic_product_id);
		ParamMgrService ps= new ParamMgrServiceImpl();
		SystemParam  sp;
		bvo.setBasic_invest_type(bp.getBasic_invest_type());
		sp = ps.querySystemByValue(bp.getBasic_invest_type(), Constant.getProduct_investtyp());
		bvo.setBasic_invest_typename(sp.getPara_name());
		bvo.setBasic_product_id(bp.getBasic_product_id());
		bvo.setBasic_product_name(bp.getBasic_product_name());
		bvo.setBasic_product_stock_id(bp.getBasic_product_stock_id());
		bvo.setBasic_product_type(bp.getBasic_product_type());
		sp = ps.querySystemByValue(bp.getBasic_product_type(), Constant.getProduct_typ());
		bvo.setBasic_product_typename(sp.getPara_name());
		bvo.setCreate_date(bp.getCreate_date());
		bvo.setEnd_date(bp.getEnd_date());
		bvo.setInput_web(bp.getInput_web());
		bvo.setInvest_product_id(bp.getInvest_product_id());
		bvo.setInvester_bail(AppUtil.numFormat(bp.getInvester_bail(),2));
		bvo.setLauncher_accruedcharges_amount(AppUtil.numFormat(bp.getLauncher_accruedcharges_amount(),2));
		bvo.setLauncher_accruedcharges_type(bp.getLauncher_accruedcharges_type());
		sp = ps.querySystemByValue(bp.getLauncher_accruedcharges_type(), Constant.getProduct_accchargetyp());
		bvo.setLauncher_accruedcharges_typename(sp.getPara_name());
		bvo.setLot_date(bp.getLot_date());
		bvo.setLow_limit(AppUtil.numFormat(bp.getLow_limit(),2));
		bvo.setPrice(AppUtil.numFormat(bp.getPrice(),2));
		bvo.setSales_city(bp.getSales_city());
		sp = ps.querySystemByValue(bp.getSales_city(),Constant.getProduct_city());
		bvo.setSales_cityname(sp.getPara_name());
		bvo.setStart_date(bp.getStart_date());
		bvo.setStarter_bail(AppUtil.numFormat(bp.getStarter_bail(),2));
		bvo.setUp_limit(bp.getUp_limit());
		bvo.setUpdate_date(bp.getUpdate_date());
		bvo.setUser_accruedcharges_amount(AppUtil.numFormat(bp.getUser_accruedcharges_amount(),2));
		bvo.setUser_accruedcharges_type(bp.getUser_accruedcharges_type());
		sp = ps.querySystemByValue(bp.getUser_accruedcharges_type(), Constant.getProduct_accchargetyp());
		bvo.setUser_accruedcharges_typename(sp.getPara_name());
		bvo.setFundfree_date(bp.getFundfree_date());
		if("0.00".equals(AppUtil.numFormat(bp.getLot_rate(),2))){
			bvo.setLot_rate("");
		}else{
			bvo.setLot_rate(AppUtil.numFormat(bp.getLot_rate(),2));
		}
		bvo.setApply_code(bp.getApply_code());
		
		Map pmap = new HashMap();
		pmap.put("basic_product_id", basic_product_id);
		List<BaseProduct> lblist = productMgrDao.selectBIProductById(pmap);
		String editflag = "0";//可以修改
		for(BaseProduct bpt:lblist){
			if(bpt.getInvt_product_status() != null && !"".equals(bpt.getInvt_product_status())){
				if("0".equals(editflag)){
					editflag = "1";//仅可以修改中签率
				}
				//利润分配完成，中签率也不可以修改
				if(bpt.getInvt_product_status() != null && "5".equals(bpt.getInvt_product_status())){
					editflag = "2";
				}
			}
		}
		bvo.setEditflag(editflag);
		return bvo;
		
	}
	/*
	 * 修改基础标的信息
	 */
	public String editBaseProductInfo(BaseProductVo paravo){
		Map parammap = new HashMap();
		parammap.put("basic_product_name", paravo.getBasic_product_name());
		parammap.put("basic_invest_type", paravo.getBasic_invest_type());
		parammap.put("starter_bail", AppUtil.nvlNum(paravo.getStarter_bail()));
		parammap.put("invester_bail", AppUtil.nvlNum(paravo.getInvester_bail()));
		parammap.put("launcher_accruedcharges_type", paravo.getLauncher_accruedcharges_type());
		parammap.put("launcher_accruedcharges_amount", AppUtil.nvlNum(paravo.getLauncher_accruedcharges_amount()));
		parammap.put("user_accruedcharges_type", paravo.getUser_accruedcharges_type());
		parammap.put("user_accruedcharges_amount", AppUtil.nvlNum(paravo.getUser_accruedcharges_amount()));
		parammap.put("input_web", paravo.getInput_web());
		parammap.put("basic_product_id", paravo.getBasic_product_id());
		parammap.put("invest_product_id", paravo.getInvest_product_id());
		parammap.put("sales_city", paravo.getSales_city());
		parammap.put("price", AppUtil.nvlNum(paravo.getPrice()));
		parammap.put("start_date", paravo.getStart_date());
		parammap.put("end_date", paravo.getEnd_date());
		parammap.put("up_limit", AppUtil.nvlNum(paravo.getUp_limit()));
		parammap.put("low_limit", AppUtil.nvlNum(paravo.getLow_limit()));
		parammap.put("lot_date", paravo.getLot_date());
		parammap.put("apply_code", paravo.getApply_code());
		parammap.put("lot_rate", AppUtil.nvlNum(paravo.getLot_rate()));
		productMgrDao.updateBasicProductById(parammap);
		productMgrDao.updateBasicProductStockById(parammap);
		return "0";
	}
	/*
	 * 删除基础信息
	 */
	public String delBaseProductInfo(String basic_product_id){
		
		productMgrDao.delBasicProductById(basic_product_id);
		productMgrDao.delBasicProductStockById(basic_product_id);
		
		return "0";
	}
	
	public ProductMgrDao getProductMgrDao() {
		return productMgrDao;
	}
	public void setProductMgrDao(ProductMgrDao productMgrDao) {
		this.productMgrDao = productMgrDao;
	}



}
