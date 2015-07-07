package com.p2p.webapp.sysmgr.productmgr.dto;

public class BaseProductDto {
	//彩票产品ID
	private String basic_product_stock_id;
	//基础标的ID
	private String basic_product_id;
	//市场期数 
	private String invest_product_id;
	//销售城市
	private String sales_city;
	//销售城市名称
	private String sales_cityname;		
	//单价
	private String price = "0";
	//开始时间
	private String start_date;
	//结束时间
	private String end_date;
	//申购上限
	private String up_limit;
	//发行下限
	private String low_limit;
	//开奖日期
	private String lot_date;
	//创建时间
	private String create_date;
	//更新时间
	private String update_date;
	//基础标的名称
	private String basic_product_name;
	//基础标的类型
	private String basic_product_type;
	//基础标的类型名称
	private String basic_product_typename;

	
	//投资类型
	private String basic_invest_type;
	//投资类型名称
	private String basic_invest_typename;
	//投资发起人保证金比例
	private String starter_bail;
	//投资人保证金比例
	private String invester_bail;
	//发起人管理费计提方式
	private String launcher_accruedcharges_type;
	//发起人管理费计提金额
	private String launcher_accruedcharges_amount;
	//投资人管理费计提方式
	private String user_accruedcharges_type;
	//投资人管理费计提金额
	private String user_accruedcharges_amount;
	//基础投资录入页面
	private String input_web;
	public String getBasic_product_stock_id() {
		return basic_product_stock_id;
	}
	public void setBasic_product_stock_id(String basicProductStockId) {
		basic_product_stock_id = basicProductStockId;
	}
	public String getBasic_product_id() {
		return basic_product_id;
	}
	public void setBasic_product_id(String basicProductId) {
		basic_product_id = basicProductId;
	}
	public String getInvest_product_id() {
		return invest_product_id;
	}
	public void setInvest_product_id(String investPrdouctId) {
		invest_product_id = investPrdouctId;
	}
	public String getSales_city() {
		return sales_city;
	}
	public void setSales_city(String salesCity) {
		sales_city = salesCity;
	}
	public String getSales_cityname() {
		return sales_cityname;
	}
	public void setSales_cityname(String salesCityname) {
		sales_cityname = salesCityname;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String startDate) {
		start_date = startDate;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String endDate) {
		end_date = endDate;
	}
	public String getUp_limit() {
		return up_limit;
	}
	public void setUp_limit(String upLimit) {
		up_limit = upLimit;
	}
	public String getLow_limit() {
		return low_limit;
	}
	public void setLow_limit(String lowLimit) {
		low_limit = lowLimit;
	}
	public String getLot_date() {
		return lot_date;
	}
	public void setLot_date(String lotDate) {
		lot_date = lotDate;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String createDate) {
		create_date = createDate;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String updateDate) {
		update_date = updateDate;
	}
	public String getBasic_product_name() {
		return basic_product_name;
	}
	public void setBasic_product_name(String basicProductName) {
		basic_product_name = basicProductName;
	}
	public String getBasic_product_type() {
		return basic_product_type;
	}
	public void setBasic_product_type(String basicProductType) {
		basic_product_type = basicProductType;
	}
	public String getBasic_product_typename() {
		return basic_product_typename;
	}
	public void setBasic_product_typename(String basicProductTypename) {
		basic_product_typename = basicProductTypename;
	}
	public String getBasic_invest_type() {
		return basic_invest_type;
	}
	public void setBasic_invest_type(String basicInvestType) {
		basic_invest_type = basicInvestType;
	}
	public String getBasic_invest_typename() {
		return basic_invest_typename;
	}
	public void setBasic_invest_typename(String basicInvestTypename) {
		basic_invest_typename = basicInvestTypename;
	}
	public String getStarter_bail() {
		return starter_bail;
	}
	public void setStarter_bail(String starterBail) {
		starter_bail = starterBail;
	}
	public String getInvester_bail() {
		return invester_bail;
	}
	public void setInvester_bail(String investerBail) {
		invester_bail = investerBail;
	}
	public String getLauncher_accruedcharges_type() {
		return launcher_accruedcharges_type;
	}
	public void setLauncher_accruedcharges_type(String launcherAccruedchargesType) {
		launcher_accruedcharges_type = launcherAccruedchargesType;
	}
	public String getLauncher_accruedcharges_amount() {
		return launcher_accruedcharges_amount;
	}
	public void setLauncher_accruedcharges_amount(
			String launcherAccruedchargesAmount) {
		launcher_accruedcharges_amount = launcherAccruedchargesAmount;
	}
	public String getUser_accruedcharges_type() {
		return user_accruedcharges_type;
	}
	public void setUser_accruedcharges_type(String userAccruedchargesType) {
		user_accruedcharges_type = userAccruedchargesType;
	}
	public String getUser_accruedcharges_amount() {
		return user_accruedcharges_amount;
	}
	public void setUser_accruedcharges_amount(String userAccruedchargesAmount) {
		user_accruedcharges_amount = userAccruedchargesAmount;
	}
	public String getInput_web() {
		return input_web;
	}
	public void setInput_web(String inputWeb) {
		input_web = inputWeb;
	}
}
