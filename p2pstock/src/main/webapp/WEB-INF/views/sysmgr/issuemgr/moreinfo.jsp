<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
	<div class="p20bs color-white-bg fn-clear">
		<div class="fn-clear head">
	     	<div class="title fn-left">基础标的详细信息</div>
	     	<div class="fn-right">
	     		<a id="canclemoreBtn" class="ui-button ui-button-mid ui-button-green" onclick="canclemore()">取消</a>
	     	</div>
   		</div>
	    <form data-name="addcard"  class="ui-form" method="post" id="productSaveForm" action="/p2pstock/sysmgr/productmgrInit_productMgrAction.action">
	    <fieldset style="border:0px">
		    <legend class="fieldset-lgend">产品基本属性</legend>
		    <table>
		    	<tr>
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">所属市场:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="baseProductVo.sales_cityname"/>
			 		</td>			    		
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">产品名称:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="baseProductVo.basic_product_name"/>
			 		</td>
			 	</tr>
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">股票代码:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="baseProductVo.invest_product_id"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">市场单价:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="baseProductVo.price"/>
			 		</td>
		    	</tr>
		    	<tr>
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">中签公布日期:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="baseProductVo.lot_date"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">资金解冻日期:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="baseProductVo.fundfree_date"/>
			 		</td>
		    	</tr>
		    	<tr>
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">开始时间:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.start_date"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">结束时间:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.end_date"/>
			 		</td>
		    	</tr>
		    	<tr>
		    		<td class="tbl_td_15_b tbl_td_lbcolor">申购上限:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.up_limit"/>股
			 		</td>
		    		<td class="tbl_td_15_b tbl_td_lbcolor">中签率:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.lot_rate"/>
			 		</td>				 		
			 	</tr>
		    </table>
	    </fieldset>
	    <fieldset style="border:0px">
		    <legend class="fieldset-lgend">产品管理属性</legend>
		    <table>
		    	<tr>
		    		
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">
			 		投资类型:
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_width" >
			 			<s:property value="baseProductVo.basic_invest_typename"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">投资人保证金比例:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="baseProductVo.invester_bail"/>
			 		</td>
		    	</tr>
		    	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">
			 			投资人管理费计提方式:
			 		</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.user_accruedcharges_typename"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">投资人管理费计提金额:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.user_accruedcharges_amount"/>
			 		</td>
		    	</tr>
		    	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" >
			 		发起人管理费计提方式:
			 		</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.launcher_accruedcharges_typename"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">发起人管理费计提金额:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.launcher_accruedcharges_amount"/>
			 		</td>
		    	</tr>
		    	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">发起人保证金比例:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.starter_bail"/>
			 		</td>
			 		<td class="tbl_td_15_b"></td>
			 		<td class="tbl_td_15_b">
			 		</td>
		    	</tr>
		    </table>
	    </fieldset>
	    </form>
	</div>