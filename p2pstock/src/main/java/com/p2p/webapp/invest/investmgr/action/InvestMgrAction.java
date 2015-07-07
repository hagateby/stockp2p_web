package com.p2p.webapp.invest.investmgr.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.invest.investmgr.service.InvestMgrService;
import com.p2p.webapp.invest.investmgr.vo.InvestMgrVo;
import com.p2p.webapp.invest.investmgr.vo.InvestPayVo;
import com.p2p.webapp.invest.investmgr.vo.InvestVo;
import com.p2p.webapp.sysmgr.issuemgr.service.IssueMgrService;
import com.p2p.webapp.sysmgr.issuemgr.vo.InvestMoreInfoVo;
import com.p2p.webapp.system.parammgr.entity.SystemParam;
import com.p2p.webapp.system.parammgr.service.ParamMgrService;
import com.p2p.webapp.system.parammgr.service.impl.ParamMgrServiceImpl;

public class InvestMgrAction extends BaseAction{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(InvestMgrAction.class);
	private InvestMgrVo investMgrVo;
	private InvestMgrService investMgrService;
	private InvestPayVo investPayVo;
	List<InvestVo> investlist;
	private Page page;
	private String user_invest_id;
	private InvestMoreInfoVo investMainVo;
	private List<InvestMoreInfoVo> morelist;
	private List<InvestMoreInfoVo> lmvolist;
	private IssueMgrService issueMgrService;
	private List<SystemParam>  investstatuslist;
	private InvestVo investvo;
	/*
	 * 投资查询初始化
	 */
	public String queryInvestInfo(){
		return "investquery";
	}
	
	/*
	 * 用户投资
	 */
	public String invest(){
		logger.debug("投资交易开始");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		//UUID
		String uuid = UUID.randomUUID().toString();
		investMgrVo.setUser_id(userid);
		investMgrVo.setTranseq(uuid);
		logger.debug("交易流水号："+uuid);
		String result = investMgrService.userInvest(investMgrVo);
		if(!"0".equals(result)){
			logger.debug("投资失败，时间校验不合法");
			msgCode = "ERR0012";
			return "investerr";
		}
		logger.debug("投资交易结束");
		return "investok";
	}
	
	/*
	 * 用户投资修改
	 */
	public String editInvest(){
		logger.debug("投资修改交易开始");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		//UUID
		String uuid = UUID.randomUUID().toString();
		investMgrVo.setUser_id(userid);
		investMgrVo.setTranseq(uuid);
		logger.debug("交易流水号："+uuid);
		investMgrService.editInvest(investMgrVo);
		logger.debug("投资修改交易结束");
		return "editInvestOK";
	}
	/*
	 * 用户投资修改初始化
	 */
	public String editInit(){
		
		Map<String,Object> rmap = investMgrService.editInit(user_invest_id);
		investMainVo = (InvestMoreInfoVo)rmap.get("investMainVo");
		morelist = (ArrayList)rmap.get("rlist");
		Map<String,Object> rmap2 = issueMgrService.queryMoreinfo(investMainVo.getInvt_product_id());
		lmvolist = (ArrayList)rmap2.get("rlist");
		return "editInitOK";
	}
	/*
	 * 用户付费
	 */
	public String payinvest(){
		logger.debug("投资付款交易开始");
		investMgrService.investPay(investPayVo);
		logger.debug("投资付款交易结束");
		return "payok";
	}
	/*
	 * 投资信息查询
	 */
	public String queryInvest(){
		//产品状态列表初始化
		ParamMgrService ps= new ParamMgrServiceImpl();
		investstatuslist = new ArrayList<SystemParam>();
		investstatuslist = ps.querySystemByTyp(Constant.getInvest_status());
		//分页
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		if(investMgrVo == null){
			investMgrVo = new InvestMgrVo();
		}
		investlist = investMgrService.queryInvestInfo(page, investMgrVo);
		//查询条件保存session
		paramSave(page);
		return "queryInvestok";
	}
	/*
	 * 投资详细信息查询
	 */
	public String queryInvestMore(){
		investlist = investMgrService.queryInvestMoreInfo(user_invest_id);
		investvo = investlist.get(0);
		return "queryInvestMore";
	}
	
	/*
	 * 投资审核
	 */
	public String auditInvest(){
		String userId = (String)request.getSession().getAttribute(Constant.getSession_userid());
		investMgrVo.setUser_id(userId);
		investMgrService.auditInvest(investMgrVo);
		if("0".equals(investMgrVo.getAuditflag())){
			msgCode = "MSG0018";
		}else{
			msgCode = "MSG0017";
		}
		return "auditinvestok";
	}
	
	public InvestMgrVo getInvestMgrVo() {
		return investMgrVo;
	}

	public void setInvestMgrVo(InvestMgrVo investMgrVo) {
		this.investMgrVo = investMgrVo;
	}

	public InvestMgrService getInvestMgrService() {
		return investMgrService;
	}

	public void setInvestMgrService(InvestMgrService investMgrService) {
		this.investMgrService = investMgrService;
	}

	public InvestPayVo getInvestPayVo() {
		return investPayVo;
	}

	public void setInvestPayVo(InvestPayVo investPayVo) {
		this.investPayVo = investPayVo;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<InvestVo> getInvestlist() {
		return investlist;
	}

	public void setInvestlist(List<InvestVo> investlist) {
		this.investlist = investlist;
	}

	public String getUser_invest_id() {
		return user_invest_id;
	}

	public void setUser_invest_id(String userInvestId) {
		user_invest_id = userInvestId;
	}

	public InvestMoreInfoVo getInvestMainVo() {
		return investMainVo;
	}

	public void setInvestMainVo(InvestMoreInfoVo investMainVo) {
		this.investMainVo = investMainVo;
	}

	public List<InvestMoreInfoVo> getMorelist() {
		return morelist;
	}

	public void setMorelist(List<InvestMoreInfoVo> morelist) {
		this.morelist = morelist;
	}

	public IssueMgrService getIssueMgrService() {
		return issueMgrService;
	}

	public void setIssueMgrService(IssueMgrService issueMgrService) {
		this.issueMgrService = issueMgrService;
	}

	public List<InvestMoreInfoVo> getLmvolist() {
		return lmvolist;
	}

	public void setLmvolist(List<InvestMoreInfoVo> lmvolist) {
		this.lmvolist = lmvolist;
	}

	public List<SystemParam> getInveststatuslist() {
		return investstatuslist;
	}

	public void setInveststatuslist(List<SystemParam> investstatuslist) {
		this.investstatuslist = investstatuslist;
	}

	public InvestVo getInvestvo() {
		return investvo;
	}

	public void setInvestvo(InvestVo investvo) {
		this.investvo = investvo;
	}


}
