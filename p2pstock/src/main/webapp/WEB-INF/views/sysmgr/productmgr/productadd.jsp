<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/sysmgr/productmgr/productadd.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>基础标的新增</title>
</head>
<body>


<div class="grid_10">
	
	<div class="p20bs color-white-bg fn-clear">
		<div style="padding-left:300px;color:red;">
			<label id="lerror" class="errmsg"><tag:msg msgcode="${msgCode}"></tag:msg></label>
		</div>
		<div class="fn-clear head">
	     	<div class="title fn-left">基础标的信息维护</div>
	     	<div class="fn-right">
	     		<a id="saveProductInfoBtn" class="ui-button ui-button-mid ui-button-green">保存</a>
	     	</div>
   		</div>
	    <form data-name="addcard"  class="ui-form" method="post" id="productSaveForm" action="/p2pstock/sysmgr/productAddSub_productMgrAction.action">
		    <s:hidden id="basic_product_type" name="baseProductVo.basic_product_type" value="0"></s:hidden>
		    <s:hidden id="sales_city" name="baseProductVo.sales_city"></s:hidden>
		    <s:hidden id="basic_invest_type" name="baseProductVo.basic_invest_type" value="2"></s:hidden>
		    <s:hidden id="low_limit" name="baseProductVo.low_limit" value="0"></s:hidden>
		    <fieldset style="border:0px">
			    <table>
			    	<tr>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">标的名称:</td>
				 		<td class="tbl_td_15_b">
				 			<input type="text" id="basic_product_name" name="baseProductVo.basic_product_name" maxlength="12"/>
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">股票代码:</td>
				 		<td class="tbl_td_15_b">
				 			<input type="text" id="invest_product_id" onblur="value=value.replace(/[^\d]/g,'')" name="baseProductVo.invest_product_id" maxlength="6"/>
				 		</td>
			    	</tr>
			    	<tr>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">股票单价:</td>
				 		<td class="tbl_td_15_b">
				 			<input type="text" id="price" onblur="value=value.replace(/[^\d.]/g,'')" name="baseProductVo.price" maxlength="12"/>元
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">申购上限: </td>
				 		<td class="tbl_td_15_b">
				 			<input id="up_limit" name="baseProductVo.up_limit" onblur="value=value.replace(/[^\d.]/g,'')" type="text" maxlength="12"/>股
				 		</td>
				 	</tr>
				 	<tr>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">资金解冻日期:</td>
				 		<td class="tbl_td_15_b ">
				 			<input id="fundfree_date" name="baseProductVo.fundfree_date" class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})"/>
				 		</td>				 	
				 		<td class="tbl_td_15_b tbl_td_lbcolor">中签公布日期:</td>
				 		<td class="tbl_td_15_b ">
				 			<input id="lot_date" name="baseProductVo.lot_date" class="Wdate" type="text" onblur="lovchk()" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})"/>
				 		</td>
			    	</tr>
			    	<tr>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">募集开始时间:</td>
				 		<td class="tbl_td_15_b">
				 			<input id="start_date" name="baseProductVo.start_date" class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true,dateFmt: 'yyyy-MM-dd HH:mm'})"/>
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">募集结束时间:</td>
				 		<td class="tbl_td_15_b">
				 			<input id="end_date" name="baseProductVo.end_date" class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true,dateFmt: 'yyyy-MM-dd HH:mm'})"/>
				 		</td>
			    	</tr>
			    	<tr>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">申购代码:</td>
				 		<td class="tbl_td_15_b">
				 			<input type="text" id="price" onblur="value=value.replace(/[^\d.]/g,'')" name="baseProductVo.apply_code" maxlength="12"/>
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">中签率:</td>
				 		<td class="tbl_td_15_b">
				 			<input type="text" id="lot_rate" readonly onblur="value=value.replace(/[^\d.]/g,'')" name="baseProductVo.lot_rate" maxlength="12"/>%
				 		</td>
			    	</tr>
			    </table>
		    </fieldset>
	    </form>
	</div>
	<div class="p20bs color-white-bg fn-clear">
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">查询列表</legend>
			<table>
				<tr>
					<td class="tbl_td_20 tbl_tdtitle140">标的名称</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:100px">所属市场</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:100px">股票代码</td>
					<td class="tbl_td_20 tbl_tdtitlecolor"  style="width:180px">募集开始时间</td>
					<td class="tbl_td_20 tbl_tdtitlecolor"  style="width:180px">募集结束时间</td>
					<td class="tbl_td_20 tbl_tdtitle140">中签公布日期</td>
				</tr>
				<s:iterator value="productList" status="s">
					<tr>
						<td class="tbl_td_20 "><s:property value="basic_product_name"/></td>
						<td class="tbl_td_20 "><s:property value="sales_cityname"/></td>
						<td class="tbl_td_20 "><s:property value="invest_product_id"/></td>
						<td class="tbl_td_20 "><s:property value="start_date"/></td>
						<td class="tbl_td_20 "><s:property value="end_date"/></td>
						<td class="tbl_td_20 "><s:property value="lot_date"/></td>	
					</tr>
				</s:iterator>
			</table>
		</fieldset>
	</div>
</div>
</body>
</html>