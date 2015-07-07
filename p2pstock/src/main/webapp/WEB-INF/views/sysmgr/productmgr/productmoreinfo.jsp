<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/sysmgr/productmgr/productmoreinfo.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>
</head>
<body>
<div class="grid_10">
	<div class="p20bs color-white-bg fn-clear">
		<div class="fn-clear head">
	     	<div class="title fn-left">基础标的详细信息</div>
	     	<div class="fn-right">
	     		<a id="cancleBtn" class="ui-button ui-button-mid ui-button-green">取消</a>
	     	</div>
   		</div>
	    <form data-name="addcard"  class="ui-form" method="post" id="productSaveForm" action="/p2pstock/sysmgr/productmgrInit_productMgrAction.action">
	    <fieldset style="border:0px">
		    <legend class="fieldset-lgend">基本属性</legend>
		    <table>
		    	<tr>
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">所属市场:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="baseProductVo.sales_cityname"/>
			 		</td>			    		
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">标的名称:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="baseProductVo.basic_product_name"/>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">股票代码:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="baseProductVo.invest_product_id"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">股票单价:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="baseProductVo.price"/>元
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
			 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">募集开始时间:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.start_date"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">募集结束时间:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.end_date"/>
			 		</td>
		    	</tr>
		    	<tr>
		    		<td class="tbl_td_15_b tbl_td_lbcolor">申购上限:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.up_limit"/>股
			 		</td>
		    		<td class="tbl_td_15_b tbl_td_lbcolor">申购代码:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.apply_code"/>
			 		</td>		 		
			 	</tr>
		    	<tr>
		    		<td class="tbl_td_15_b tbl_td_lbcolor">中签率:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="baseProductVo.lot_rate"/>%
			 		</td>				 		
			 	</tr>
		    </table>
	    </fieldset>
	    </form>
	</div>
</div>
</body>
</html>