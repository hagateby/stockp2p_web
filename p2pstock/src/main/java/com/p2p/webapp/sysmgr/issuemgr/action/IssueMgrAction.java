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
 * �û�ע��ģ��
 * @author Administrator
 *
 */
public class IssueMgrAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//д��־����
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
	//Ͷ�������б�
	private List<SystemParam> invesettyolist;
	//Ͷ���˹���Ѽ��᷽ʽ�б�
	private List<SystemParam> accchargetyplist;
	/*
	 * ���в�Ʒ��Ϣ��ѯ
	 */
	public String queryIssurInfo(){
		logger.debug("���в�Ʒ��ѯ��ʼ");
		//��Ʒ״̬�б��ʼ��
		ParamMgrService ps= new ParamMgrServiceImpl();
		investstatuslist = new ArrayList<SystemParam>();
		investstatuslist = ps.querySystemByTyp(Constant.getInvestprt_status());
		if(investProductVo == null){
			investProductVo = new InvestProductVo();
		}
		//��ҳ
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		investlist = issueMgrService.queryIssurInfo(page, investProductVo);
		//��ѯ��������session
		paramSave(page);
		
		logger.debug("���в�Ʒ��ѯ����");
		return "issuequery";
	}
	/*
	 * ��ѯ��Ͷ�ʲ�Ʒ
	 */
	public String queryActiveIssurInfo(){
		logger.debug("��Ͷ�ʲ�Ʒ��ѯ��ʼ");
		page = getPageInstance(page);
		investlist = issueMgrService.queryActiveIssurInfo(page);
		//��ѯ��������session
		paramSave(page);
		
		logger.debug("��Ͷ�ʲ�Ʒ��ѯ����");
		return "queryActiveIssurInfoOK";
	}
	/*
	 * ��ѯ��ʷͶ�ʲ�Ʒ
	 */
	public String queryHisIssurInfo(){
		logger.debug("��ʷ��Ʒ��ѯ��ʼ");
		page = getPageInstance(page);
		investlist = issueMgrService.queryHisIssurInfo(page);
		//��ѯ��������session
		paramSave(page);
		
		logger.debug("��ʷ��Ʒ��ѯ����");
		return "queryHisIssurInfoOK";
	}
	/*
	 * ���Ͷ�ʲ�Ʒ��ʼ��
	 */
	public String addIssurInit(){
		logger.debug("���Ͷ�ʲ�Ʒ��ʼ��");
		//��Ʒ״̬�б��ʼ��
		ParamMgrService ps= new ParamMgrServiceImpl();
		//��ʼ��Ͷ�������б�request
		invesettyolist = new ArrayList<SystemParam>();
		invesettyolist = ps.querySystemByTyp(Constant.getProduct_investtyp());
		//��ʼ��Ͷ���˹���Ѽ��᷽ʽ�б�request
		accchargetyplist = new ArrayList<SystemParam>();
		accchargetyplist = ps.querySystemByTyp(Constant.getProduct_accchargetyp());
		//��ʼ����Ʒ�����б�request
		investstatuslist = new ArrayList<SystemParam>();
		investstatuslist = ps.querySystemByTyp(Constant.getInvestprt_status());
		
		logger.debug("���Ͷ�ʲ�Ʒ��ʼ������");
		return "issueaddinit";
	}
	/*
	 * ���Ͷ�ʲ�Ʒ�ύ
	 */
	public String addIssurSub(){
		logger.debug("���Ͷ�ʲ�Ʒ�ύ��ʼ");
		String userid = (String)request.getSession().getAttribute(Constant.getSession_userid());
		investProductVo.setUser_id(userid);
		investProductVo.setInvt_product_status(Constant.getInvestprt_status_avl());
		msgCode = "MSG0013";
		issueMgrService.addInvestInfo(investProductVo);
		logger.debug("���Ͷ�ʲ�Ʒ�ύ����");
		return "issueaddok";
	}
	/*
	 * ��Ʒ����
	 */
	public String moreIssueInfo(){
		logger.debug("��Ʒ�����ѯ��ʼ");
		if(investProductVo != null){
			invt_product_id = investProductVo.getInvt_product_id();
		}
		Map<String,Object> rmap = issueMgrService.queryMoreinfo(invt_product_id);
		investProductVo = (InvestProductVo)rmap.get("rvo");
		lmvolist = (ArrayList)rmap.get("rlist");
		
		logger.debug("��Ʒ�����ѯ����");
		return "issuemore";
	}
	/*
	 * ɾ��
	 */
	public String delIssurSub(){
		issueMgrService.delInvestProduct(investProductVo.getInvt_product_id());
		msgCode ="MSG0014";
		return "issuedelok";
	}
	/*
	 * �޸ĳ�ʼ��
	 */
	public String editIssueInfoInit(){
		ParamMgrService ps= new ParamMgrServiceImpl();
		//��ʼ��Ͷ���˹���Ѽ��᷽ʽ�б�request
		accchargetyplist = new ArrayList<SystemParam>();
		accchargetyplist = ps.querySystemByTyp(Constant.getProduct_accchargetyp());
		//��ʼ����Ʒ�����б�request
		investstatuslist = new ArrayList<SystemParam>();
		investstatuslist = ps.querySystemByTyp(Constant.getInvestprt_status());
		Map<String,Object> rmap = issueMgrService.queryMoreinfo(investProductVo.getInvt_product_id());
		investProductVo = (InvestProductVo)rmap.get("rvo");
		lmvolist = (ArrayList)rmap.get("rlist");
		//��ʽ����ʼ�������顢�����������飬����ҳ�����
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
	 * �޸��ύ
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
		logger.debug("���в�Ʒ��ѯ��ʼ");
		
		
		logger.debug("���в�Ʒ��ѯ����");
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
