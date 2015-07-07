package com.p2p.webapp.sysmgr.issuemgr.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.issuemgr.service.IssueMgrService;
import com.p2p.webapp.sysmgr.issuemgr.vo.InvestMoreInfoVo;
import com.p2p.webapp.sysmgr.issuemgr.vo.InvestProductVo;
import com.p2p.webapp.sysmgr.productmgr.service.ProductMgrService;
import com.p2p.webapp.system.parammgr.entity.SystemParam;
import com.p2p.webapp.system.parammgr.service.ParamMgrService;
import com.p2p.webapp.system.parammgr.service.impl.ParamMgrServiceImpl;
/**
 * 用户注册模块
 * @author Administrator
 *
 */
public class IssueMgrAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(IssueMgrAction.class);
	private Page page;
	private InvestProductVo investProductVo;
	private IssueMgrService issueMgrService;
	private  List<InvestProductVo> investlist;
	private ProductMgrService productMgrService;
	private List<SystemParam>  investstatuslist;
	private List<InvestMoreInfoVo> lmvolist;
	private String invt_product_status;
	private String invt_product_id;
	//投资类型列表
	private List<SystemParam> invesettyolist;
	//投资人管理费计提方式列表
	private List<SystemParam> accchargetyplist;
	/*
	 * 发行产品信息查询
	 */
	public String queryIssurInfo(){
		logger.debug("发行产品查询开始");
		//产品状态列表初始化
		ParamMgrService ps= new ParamMgrServiceImpl();
		investstatuslist = new ArrayList<SystemParam>();
		investstatuslist = ps.querySystemByTyp(Constant.getInvestprt_status());
		if(investProductVo == null){
			investProductVo = new InvestProductVo();
		}
		//分页
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		investlist = issueMgrService.queryIssurInfo(page, investProductVo);
		//查询条件保存session
		paramSave(page);
		
		logger.debug("发行产品查询结束");
		return "issuequery";
	}
	/*
	 * 查询可投资产品
	 */
	public String queryActiveIssurInfo(){
		logger.debug("可投资产品查询开始");
		page = getPageInstance(page);
		investlist = issueMgrService.queryActiveIssurInfo(page);
		//查询条件保存session
		paramSave(page);
		
		logger.debug("可投资产品查询结束");
		return "queryActiveIssurInfoOK";
	}
	/*
	 * 查询历史投资产品
	 */
	public String queryHisIssurInfo(){
		logger.debug("历史产品查询开始");
		page = getPageInstance(page);
		investlist = issueMgrService.queryHisIssurInfo(page);
		//查询条件保存session
		paramSave(page);
		
		logger.debug("历史产品查询结束");
		return "queryHisIssurInfoOK";
	}
	/*
	 * 添加投资产品初始化
	 */
	public String addIssurInit(){
		logger.debug("添加投资产品初始化");
		//产品状态列表初始化
		ParamMgrService ps= new ParamMgrServiceImpl();
		//初始化投资类型列表到request
		invesettyolist = new ArrayList<SystemParam>();
		invesettyolist = ps.querySystemByTyp(Constant.getProduct_investtyp());
		//初始化投资人管理费计提方式列表到request
		accchargetyplist = new ArrayList<SystemParam>();
		accchargetyplist = ps.querySystemByTyp(Constant.getProduct_accchargetyp());
		//初始化产品类型列表到request
		investstatuslist = new ArrayList<SystemParam>();
		investstatuslist = ps.querySystemByTyp(Constant.getInvestprt_status());
		
		logger.debug("添加投资产品初始化结束");
		return "issueaddinit";
	}
	/*
	 * 添加投资产品提交
	 */
	public String addIssurSub(){
		logger.debug("添加投资产品提交开始");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		investProductVo.setUser_id(userid);
		investProductVo.setInvt_product_status(Constant.getInvestprt_status_avl());
		msgCode = "MSG0013";
		issueMgrService.addInvestInfo(investProductVo);
		logger.debug("添加投资产品提交结束");
		return "issueaddok";
	}
	/*
	 * 产品详情
	 */
	public String moreIssueInfo(){
		logger.debug("产品详情查询开始");
		if(investProductVo != null){
			invt_product_id = investProductVo.getInvt_product_id();
		}
		Map<String,Object> rmap = issueMgrService.queryMoreinfo(invt_product_id);
		investProductVo = (InvestProductVo)rmap.get("rvo");
		lmvolist = (ArrayList)rmap.get("rlist");
		
		logger.debug("产品详情查询结束");
		return "issuemore";
	}
	/*
	 * 删除
	 */
	public String delIssurSub(){
		issueMgrService.delInvestProduct(investProductVo.getInvt_product_id());
		msgCode ="MSG0014";
		return "issuedelok";
	}
	/*
	 * 修改初始化
	 */
	public String editIssueInfoInit(){
		ParamMgrService ps= new ParamMgrServiceImpl();
		//初始化投资人管理费计提方式列表到request
		accchargetyplist = new ArrayList<SystemParam>();
		accchargetyplist = ps.querySystemByTyp(Constant.getProduct_accchargetyp());
		//初始化产品类型列表到request
		investstatuslist = new ArrayList<SystemParam>();
		investstatuslist = ps.querySystemByTyp(Constant.getInvestprt_status());
		Map<String,Object> rmap = issueMgrService.queryMoreinfo(investProductVo.getInvt_product_id());
		investProductVo = (InvestProductVo)rmap.get("rvo");
		lmvolist = (ArrayList)rmap.get("rlist");
		//格式化起始日期数组、结束日期数组，用于页面计算
		String startdatearray = "";
		String enddatearray = "";
		for(InvestMoreInfoVo invo:lmvolist){
			startdatearray = startdatearray + invo.getStart_date() + ",";
			enddatearray = enddatearray + invo.getEnd_date() + ",";
		}
		investProductVo.setStartdatearray(startdatearray);
		investProductVo.setEnddatearray(enddatearray);
		return "issueeditinit";
	}
	/*
	 * 修改提交
	 */
	public String editIssurSub(){
		investProductVo.setInvt_product_status(Constant.getInvestprt_status_avl());
		msgCode = "MSG0015";
		issueMgrService.editInvestProduct(investProductVo);
		return "issueeditsub";
	}
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	public String queryIssueProduct(){
		logger.debug("发行产品查询开始");
		
		
		logger.debug("发行产品查询结束");
		return "issueinit";
	}


	public InvestProductVo getInvestProductVo() {
		return investProductVo;
	}


	public void setInvestProductVo(InvestProductVo investProductVo) {
		this.investProductVo = investProductVo;
	}


	public IssueMgrService getIssueMgrService() {
		return issueMgrService;
	}


	public void setIssueMgrService(IssueMgrService issueMgrService) {
		this.issueMgrService = issueMgrService;
	}





	public ProductMgrService getProductMgrService() {
		return productMgrService;
	}


	public void setProductMgrService(ProductMgrService productMgrService) {
		this.productMgrService = productMgrService;
	}


	public List<SystemParam> getInveststatuslist() {
		return investstatuslist;
	}


	public void setInveststatuslist(List<SystemParam> investstatuslist) {
		this.investstatuslist = investstatuslist;
	}
	public List<InvestProductVo> getInvestlist() {
		return investlist;
	}
	public void setInvestlist(List<InvestProductVo> investlist) {
		this.investlist = investlist;
	}

	public String getInvt_product_status() {
		return invt_product_status;
	}
	public void setInvt_product_status(String invtProductStatus) {
		invt_product_status = invtProductStatus;
	}
	public String getInvt_product_id() {
		return invt_product_id;
	}
	public void setInvt_product_id(String invtProductId) {
		invt_product_id = invtProductId;
	}
	public List<InvestMoreInfoVo> getLmvolist() {
		return lmvolist;
	}
	public void setLmvolist(List<InvestMoreInfoVo> lmvolist) {
		this.lmvolist = lmvolist;
	}
	public List<SystemParam> getInvesettyolist() {
		return invesettyolist;
	}
	public void setInvesettyolist(List<SystemParam> invesettyolist) {
		this.invesettyolist = invesettyolist;
	}
	public List<SystemParam> getAccchargetyplist() {
		return accchargetyplist;
	}
	public void setAccchargetyplist(List<SystemParam> accchargetyplist) {
		this.accchargetyplist = accchargetyplist;
	}
	


}
