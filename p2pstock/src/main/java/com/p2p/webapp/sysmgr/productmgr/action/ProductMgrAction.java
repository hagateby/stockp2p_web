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
 * 基础标的管理模块
 * @author songcs
 *
 */
public class ProductMgrAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(ProductMgrAction.class);
	//产品类型列表
	private List<SystemParam> protyplist;
	//城市列表
	private List<SystemParam> citylist;
	//投资类型列表
	private List<SystemParam> invesettyolist;
	//投资人管理费计提方式列表
	private List<SystemParam> accchargetyplist;
	private ProductMgrService productMgrService;
	//基础标的列表
	private List<BaseProductVo> productList;
	//vo
	private BaseProductVo baseProductVo;
	private Page page;

	
	
	/*
	 * 基础标的维护页面初始化
	 * @return
	 */
	public String productmgrInit(){
		logger.debug("基础标的信息维护init开始执行");
		
		
		logger.debug("分页参数：pageNo" + pageNo );
		logger.debug("分页参数：pageflag" + pageflag );
		if(baseProductVo == null){
			baseProductVo = new BaseProductVo();
		}
		
		ParamMgrService ps= new ParamMgrServiceImpl();
		//初始化产品类型列表到request
		protyplist = new ArrayList<SystemParam>();
		protyplist = ps.querySystemByTyp(Constant.getProduct_typ());
		//初始化城市列表到request
		citylist  = new ArrayList<SystemParam>();
		citylist = ps.querySystemByTyp(Constant.getProduct_city());
		//设置默认产品类型为北京体彩
		SystemParam sptyp = ps.querySystemByCode(Constant.getProduct_typ_default());
		baseProductVo.setBasic_product_type(sptyp.getPara_values());
		baseProductVo.setBasic_product_typename(sptyp.getPara_name());
		//设置默认城市为北京
		SystemParam spcity = ps.querySystemByCode(Constant.getProduct_city_default());
		//baseProductVo.setSales_city(spcity.getPara_values());
		//baseProductVo.setSales_cityname(spcity.getPara_name());
		
		
		//分页
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		//查询产品列表
		productList = new ArrayList<BaseProductVo>();
		productList = productMgrService.queryBaseProductList(page,baseProductVo);
		//查询条件保存session
		paramSave(page);
		
		logger.debug("基础标的信息维护init结束执行");
		return "productmgrinit";
	}
	/*
	 * 添加产品页面
	 */
	public String productaddInit(){
		logger.debug("添加基础标的初始化init开始执行");
		ParamMgrService ps= new ParamMgrServiceImpl();
		//初始化城市列表到request
		citylist  = new ArrayList<SystemParam>();
		citylist = ps.querySystemByTyp(Constant.getProduct_city());
		//初始化投资类型列表到request
		invesettyolist = new ArrayList<SystemParam>();
		invesettyolist = ps.querySystemByTyp(Constant.getProduct_investtyp());
		//初始化投资人管理费计提方式列表到request
		accchargetyplist = new ArrayList<SystemParam>();
		accchargetyplist = ps.querySystemByTyp(Constant.getProduct_accchargetyp());
		
		//分页
		if(baseProductVo == null){
			baseProductVo = new BaseProductVo();
		}
		if(page == null){
			page = Page.newBuilder(pageNo,pageUrl); 
		}
		page = getPageInstance(page);
		//查询产品列表
		productList = new ArrayList<BaseProductVo>();
		productList = productMgrService.queryBaseProductList(page,baseProductVo);
		//查询条件保存session
		paramSave(page);
		/*
		SystemParam sp = ps.querySystemByValue(baseProductVo.getBasic_product_type(),Constant.getProduct_typ());
		baseProductVo.setBasic_product_typename(sp.getPara_name());
		 */
		logger.debug("添加基础标的初始化init结束执行");
		return "productaddinit";
	}
	/*
	 * 添加基础标的
	 */
	public String productAddSub(){
		
		productMgrService.addBasicStockProduct(baseProductVo);
		msgCode ="MSG0010";
		return "productaddok";
	}
	/*
	 * 查询详细信息
	 */
	public String productMoreInfo(){
		
		baseProductVo = productMgrService.queryMoreInfo(baseProductVo.getBasic_product_id());
		
		return "productmoreinfo";
	}
	/*
	 * 修改基础标的信息
	 */
	public String productEditInfo(){
		
		logger.debug("修改基础标的初始化init开始执行");
		ParamMgrService ps= new ParamMgrServiceImpl();
		//初始化城市列表到request
		citylist  = new ArrayList<SystemParam>();
		citylist = ps.querySystemByTyp(Constant.getProduct_city());
		//初始化投资类型列表到request
		invesettyolist = new ArrayList<SystemParam>();
		invesettyolist = ps.querySystemByTyp(Constant.getProduct_investtyp());
		//初始化投资人管理费计提方式列表到request
		accchargetyplist = new ArrayList<SystemParam>();
		accchargetyplist = ps.querySystemByTyp(Constant.getProduct_accchargetyp());
		
		baseProductVo = productMgrService.queryMoreInfo(baseProductVo.getBasic_product_id());
		logger.debug(baseProductVo.getBasic_product_typename());
		logger.debug("修改基础标的初始化init结束执行");
		return "producteditinfo";
	}
	/*
	 * 修改提交
	 */
	
	public String productEditSub(){
		logger.debug("修改基础标的初始化sub开始执行");
		
		productMgrService.editBaseProductInfo(baseProductVo);
		
		msgCode = "MSG0011";
		logger.debug("修改基础标的初始化sub开始执行");
		return "producteditok";
	}
	/*
	 * 删除提交
	 */
	public String productDelSub(){
		logger.debug("删除基础标的初始化sub开始执行");
		productMgrService.delBaseProductInfo(baseProductVo.getBasic_product_id());
		msgCode = "MSG0012";
		logger.debug("删除基础标的初始化sub开始执行");
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
