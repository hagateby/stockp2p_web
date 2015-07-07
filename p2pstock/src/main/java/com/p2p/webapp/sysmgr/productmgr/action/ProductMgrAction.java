package com.p2p.webapp.sysmgr.productmgr.action;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.sysmgr.productmgr.service.ProductMgrService;
import com.p2p.webapp.sysmgr.productmgr.vo.BaseProductVo;
import com.p2p.webapp.system.parammgr.entity.SystemParam;
import com.p2p.webapp.system.parammgr.service.ParamMgrService;
import com.p2p.webapp.system.parammgr.service.impl.ParamMgrServiceImpl;

/**
 * ������Ĺ���ģ��
 * @author songcs
 *
 */
public class ProductMgrAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//д��־����
	public static Logger logger = LoggerFactory.getLogger(ProductMgrAction.class);
	//��Ʒ�����б�
	private List<SystemParam> protyplist;
	//�����б�
	private List<SystemParam> citylist;
	//Ͷ�������б�
	private List<SystemParam> invesettyolist;
	//Ͷ���˹���Ѽ��᷽ʽ�б�
	private List<SystemParam> accchargetyplist;
	private ProductMgrService productMgrService;
	//��������б�
	private List<BaseProductVo> productList;
	//vo
	private BaseProductVo baseProductVo;
	private Page page;

	
	
	/*
	 * �������ά��ҳ���ʼ��
	 * @return
	 */
	public String productmgrInit(){
		logger.debug("���������Ϣά��init��ʼִ��");
		
		
		logger.debug("��ҳ������pageNo" + pageNo );
		logger.debug("��ҳ������pageflag" + pageflag );
		if(baseProductVo == null){
			baseProductVo = new BaseProductVo();
		}
		
		ParamMgrService ps= new ParamMgrServiceImpl();
		//��ʼ����Ʒ�����б�request
		protyplist = new ArrayList<SystemParam>();
		protyplist = ps.querySystemByTyp(Constant.getProduct_typ());
		//��ʼ�������б�request
		citylist  = new ArrayList<SystemParam>();
		citylist = ps.querySystemByTyp(Constant.getProduct_city());
		//����Ĭ�ϲ�Ʒ����Ϊ�������
		SystemParam sptyp = ps.querySystemByCode(Constant.getProduct_typ_default());
		baseProductVo.setBasic_product_type(sptyp.getPara_values());
		baseProductVo.setBasic_product_typename(sptyp.getPara_name());
		//����Ĭ�ϳ���Ϊ����
		SystemParam spcity = ps.querySystemByCode(Constant.getProduct_city_default());
		//baseProductVo.setSales_city(spcity.getPara_values());
		//baseProductVo.setSales_cityname(spcity.getPara_name());
		
		
		//��ҳ
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		//��ѯ��Ʒ�б�
		productList = new ArrayList<BaseProductVo>();
		productList = productMgrService.queryBaseProductList(page,baseProductVo);
		//��ѯ��������session
		paramSave(page);
		
		logger.debug("���������Ϣά��init����ִ��");
		return "productmgrinit";
	}
	/*
	 * ��Ӳ�Ʒҳ��
	 */
	public String productaddInit(){
		logger.debug("��ӻ�����ĳ�ʼ��init��ʼִ��");
		ParamMgrService ps= new ParamMgrServiceImpl();
		//��ʼ�������б�request
		citylist  = new ArrayList<SystemParam>();
		citylist = ps.querySystemByTyp(Constant.getProduct_city());
		//��ʼ��Ͷ�������б�request
		invesettyolist = new ArrayList<SystemParam>();
		invesettyolist = ps.querySystemByTyp(Constant.getProduct_investtyp());
		//��ʼ��Ͷ���˹���Ѽ��᷽ʽ�б�request
		accchargetyplist = new ArrayList<SystemParam>();
		accchargetyplist = ps.querySystemByTyp(Constant.getProduct_accchargetyp());
		
		//��ҳ
		if(baseProductVo == null){
			baseProductVo = new BaseProductVo();
		}
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		//��ѯ��Ʒ�б�
		productList = new ArrayList<BaseProductVo>();
		productList = productMgrService.queryBaseProductList(page,baseProductVo);
		//��ѯ��������session
		paramSave(page);
		/*
		SystemParam sp = ps.querySystemByValue(baseProductVo.getBasic_product_type(),Constant.getProduct_typ());
		baseProductVo.setBasic_product_typename(sp.getPara_name());
		 */
		logger.debug("��ӻ�����ĳ�ʼ��init����ִ��");
		return "productaddinit";
	}
	/*
	 * ��ӻ������
	 */
	public String productAddSub(){
		
		productMgrService.addBasicStockProduct(baseProductVo);
		msgCode ="MSG0010";
		return "productaddok";
	}
	/*
	 * ��ѯ��ϸ��Ϣ
	 */
	public String productMoreInfo(){
		
		baseProductVo = productMgrService.queryMoreInfo(baseProductVo.getBasic_product_id());
		
		return "productmoreinfo";
	}
	/*
	 * �޸Ļ��������Ϣ
	 */
	public String productEditInfo(){
		
		logger.debug("�޸Ļ�����ĳ�ʼ��init��ʼִ��");
		ParamMgrService ps= new ParamMgrServiceImpl();
		//��ʼ�������б�request
		citylist  = new ArrayList<SystemParam>();
		citylist = ps.querySystemByTyp(Constant.getProduct_city());
		//��ʼ��Ͷ�������б�request
		invesettyolist = new ArrayList<SystemParam>();
		invesettyolist = ps.querySystemByTyp(Constant.getProduct_investtyp());
		//��ʼ��Ͷ���˹���Ѽ��᷽ʽ�б�request
		accchargetyplist = new ArrayList<SystemParam>();
		accchargetyplist = ps.querySystemByTyp(Constant.getProduct_accchargetyp());
		
		baseProductVo = productMgrService.queryMoreInfo(baseProductVo.getBasic_product_id());
		logger.debug(baseProductVo.getBasic_product_typename());
		logger.debug("�޸Ļ�����ĳ�ʼ��init����ִ��");
		return "producteditinfo";
	}
	/*
	 * �޸��ύ
	 */
	
	public String productEditSub(){
		logger.debug("�޸Ļ�����ĳ�ʼ��sub��ʼִ��");
		
		productMgrService.editBaseProductInfo(baseProductVo);
		
		msgCode = "MSG0011";
		logger.debug("�޸Ļ�����ĳ�ʼ��sub��ʼִ��");
		return "producteditok";
	}
	/*
	 * ɾ���ύ
	 */
	public String productDelSub(){
		logger.debug("ɾ��������ĳ�ʼ��sub��ʼִ��");
		productMgrService.delBaseProductInfo(baseProductVo.getBasic_product_id());
		msgCode = "MSG0012";
		logger.debug("ɾ��������ĳ�ʼ��sub��ʼִ��");
		return "productdelok";
	}

	public List<SystemParam> getProtyplist() {
		return protyplist;
	}
	public void setProtyplist(List<SystemParam> protyplist) {
		this.protyplist = protyplist;
	}
	public BaseProductVo getBaseProductVo() {
		return baseProductVo;
	}
	public void setBaseProductVo(BaseProductVo baseProductVo) {
		this.baseProductVo = baseProductVo;
	}
	public List<SystemParam> getCitylist() {
		return citylist;
	}
	public void setCitylist(List<SystemParam> citylist) {
		this.citylist = citylist;
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
	public ProductMgrService getProductMgrService() {
		return productMgrService;
	}
	public void setProductMgrService(ProductMgrService productMgrService) {
		this.productMgrService = productMgrService;
	}
	public List<BaseProductVo> getProductList() {
		return productList;
	}
	public void setProductList(List<BaseProductVo> productList) {
		this.productList = productList;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

}
