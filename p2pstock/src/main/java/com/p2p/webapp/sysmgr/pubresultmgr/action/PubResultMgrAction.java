package com.p2p.webapp.sysmgr.pubresultmgr.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.pubresultmgr.service.PubResultMgrService;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.InvtProductSettleDetailVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.PubResultVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.SettleDetailVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.SettleHisVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.SettleMoreInfoVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.SettleVo;
import com.p2p.webapp.sysmgr.pubresultmgr.vo.UserSettleVo;
import com.p2p.webapp.system.parammgr.entity.SystemParam;
import com.p2p.webapp.system.parammgr.service.ParamMgrService;
import com.p2p.webapp.system.parammgr.service.impl.ParamMgrServiceImpl;
/**
 * 用户注册模块
 * @author Administrator
 *
 */
public class PubResultMgrAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(PubResultMgrAction.class);
	private PubResultVo pubResultVo;
	private PubResultMgrService pubResultMgrService;
	private String invt_product_id;
	private SettleVo settleVo;
	private List<SettleDetailVo> detailvolist;
	private SettleDetailVo settleDetailVo;
	private Page page;
	private List<SystemParam>  investstatuslist;
	private List<SystemParam>  settlepaystatuslist;
	//结算历史查询表单
	private SettleHisVo settleHisVo;
	private List<InvtProductSettleDetailVo> detaillist;
	//结算详情查询
	private SettleMoreInfoVo settleMoreInfoVo;
	private List<UserSettleVo> listusvo;
	/*
	 * 录入中签结果
	 */
	public String savePubResult(){
		pubResultVo.setInvt_product_id(invt_product_id);
		pubResultMgrService.pubResultNo(pubResultVo);
		return "saveresultok";
	}
	
	public void checkInvestAjax(){
		
		//结果
		
		String ajaxinvt_product_id = (String)request.getParameter("ajaxinvt_product_id");
		String result = pubResultMgrService.checkInvestAjax(ajaxinvt_product_id);
		response.setContentType("text/html;charset=utf-8"); 
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(result);  
			out.flush();  
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 产品结算
	 */
	public String settleProduct(){
		logger.debug("结算开始："+invt_product_id);
		settleVo.setInvt_product_id(invt_product_id);
		//UUID
		String uuid = UUID.randomUUID().toString();
		settleVo.setTranseq(uuid);
		Map<String,Object> rmap =pubResultMgrService.settleProduct(settleVo);
		settleVo= (SettleVo)rmap.get("settleVo");
		detailvolist=(ArrayList)rmap.get("detailvolist");
		logger.debug("结算完成"+invt_product_id);
		return "settleok";
	}
	/*
	 * 结算历史查询
	 */
	public String settleHistory(){
		//产品状态列表初始化
		ParamMgrService ps= new ParamMgrServiceImpl();
		investstatuslist = new ArrayList<SystemParam>();
		investstatuslist = ps.querySystemByTyp(Constant.getInvestprt_status());
		//分页
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		if(settleHisVo == null){
			settleHisVo = new SettleHisVo();
		}
		detaillist = pubResultMgrService.querySettleHistory(page, settleHisVo);
		//查询条件保存session
		paramSave(page);
		return "settlehisquery";
	}
	/*
	 * 结算详情查询
	 */
	public String settleMoreInfo(){
		
		Map<String,Object> rmap = pubResultMgrService.querySettleMoreInfo(invt_product_id);
		settleMoreInfoVo = (SettleMoreInfoVo)rmap.get("moreinfo");
		listusvo = (ArrayList)rmap.get("listusvo");
		return "settlemoreinfo";
	}
	/*
	 * 利润分配
	 */
	public String settlePay(){
		
		String ustl_id = settleVo.getUstl_id();
		pubResultMgrService.settlePay(ustl_id);
		return "settlePayok";
	}
	/*
	 * 应收应付查询
	 */
	public String querySettlePay(){
		//产品状态列表初始化
		ParamMgrService ps= new ParamMgrServiceImpl();
		settlepaystatuslist = new ArrayList<SystemParam>();
		settlepaystatuslist = ps.querySystemByTyp(Constant.getSettlepay_typ());
		if(settleVo == null){
			settleVo = new SettleVo();
		}
		//分页
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		listusvo = pubResultMgrService.querySettlePay(page, settleVo);
		//查询条件保存session
		paramSave(page);
		return "querySettlePayok";
	}
	





	public PubResultVo getPubResultVo() {
		return pubResultVo;
	}




	public void setPubResultVo(PubResultVo pubResultVo) {
		this.pubResultVo = pubResultVo;
	}




	public PubResultMgrService getPubResultMgrService() {
		return pubResultMgrService;
	}




	public void setPubResultMgrService(PubResultMgrService pubResultMgrService) {
		this.pubResultMgrService = pubResultMgrService;
	}




	public String getInvt_product_id() {
		return invt_product_id;
	}




	public void setInvt_product_id(String invtProductId) {
		invt_product_id = invtProductId;
	}

	public SettleDetailVo getSettleDetailVo() {
		return settleDetailVo;
	}

	public void setSettleDetailVo(SettleDetailVo settleDetailVo) {
		this.settleDetailVo = settleDetailVo;
	}

	public SettleVo getSettleVo() {
		return settleVo;
	}

	public void setSettleVo(SettleVo settleVo) {
		this.settleVo = settleVo;
	}

	public List<SettleDetailVo> getDetailvolist() {
		return detailvolist;
	}

	public void setDetailvolist(List<SettleDetailVo> detailvolist) {
		this.detailvolist = detailvolist;
	}
	public SettleHisVo getSettleHisVo() {
		return settleHisVo;
	}
	public void setSettleHisVo(SettleHisVo settleHisVo) {
		this.settleHisVo = settleHisVo;
	}

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public SettleMoreInfoVo getSettleMoreInfoVo() {
		return settleMoreInfoVo;
	}
	public void setSettleMoreInfoVo(SettleMoreInfoVo settleMoreInfoVo) {
		this.settleMoreInfoVo = settleMoreInfoVo;
	}
	public List<UserSettleVo> getListusvo() {
		return listusvo;
	}
	public void setListusvo(List<UserSettleVo> listusvo) {
		this.listusvo = listusvo;
	}
	public List<InvtProductSettleDetailVo> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<InvtProductSettleDetailVo> detaillist) {
		this.detaillist = detaillist;
	}
	public List<SystemParam> getInveststatuslist() {
		return investstatuslist;
	}
	public void setInveststatuslist(List<SystemParam> investstatuslist) {
		this.investstatuslist = investstatuslist;
	}
	public List<SystemParam> getSettlepaystatuslist() {
		return settlepaystatuslist;
	}
	public void setSettlepaystatuslist(List<SystemParam> settlepaystatuslist) {
		this.settlepaystatuslist = settlepaystatuslist;
	}

	
	


}
