<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/sysmgr/productmgr/producteditinfo.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>基础标的新增</title>
</head>
<body>


<div class="grid_10">
	
	<div class="p20bs color-white-bg fn-clear">
		<div style="padding-left:200px;color:red;">
			<label id="lerror" class="errmsg"><tag:msg msgcode="${msgCode}"></tag:msg></label>
		</div>
		<div class="fn-clear head">
	     	<div class="title fn-left">基础标的信息修改</div>
	     	<div class="fn-right">
	     		<s:if test="baseProductVo.editflag != 2">
	     		<a id="saveBtn" class="ui-button ui-button-mid ui-button-green">保存</a>
	     		</s:if>
	     		<a id="cancleBtn" class="ui-button ui-button-mid ui-button-green">取消</a>
	     	</div>
   		</div>
   		 <form method="post" id="productSaveForm" action="/p2pstock/sysmgr/productmgrInit_productMgrAction.action">
   		 </form>
	    <form  method="post" id="productEditForm" action="/p2pstock/sysmgr/productEditSub_productMgrAction.action">
	    <s:hidden id="basic_product_type" name="baseProductVo.basic_product_type" value="0"></s:hidden>
	    <input id="sales_city" type="hidden" name="baseProductVo.sales_city" value="${baseProductVo.sales_city}"/>
	    <s:hidden id="basic_invest_type" name="baseProductVo.basic_invest_type" value="2"></s:hidden>
	    <s:hidden id="low_limit" name="baseProductVo.low_limit" value="0"></s:hidden>
	     <s:hidden id="editflag" name="baseProductVo.editflag"></s:hidden>
	    <input id="basic_product_id" type="hidden" name="baseProductVo.basic_product_id" value="${baseProductVo.basic_product_id}"/>
	    <fieldset style="border:0px">
		    <table>
		    	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" >标的名称:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:if test="baseProductVo.editflag == 0">
			 				<input type="text"  id="basic_product_name" name="baseProductVo.basic_product_name" value="${baseProductVo.basic_product_name}"/>
			 			</s:if>
			 			<s:else>
			 				<input type="hidden"  id="basic_product_name" name="baseProductVo.basic_product_name" value="${baseProductVo.basic_product_name}"/>
			 				${baseProductVo.basic_product_name}
			 			</s:else>
			 			
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" >股票代码:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:if test="baseProductVo.editflag == 0">
			 				<input type="text"  id="invest_product_id" name="baseProductVo.invest_product_id" value="${baseProductVo.invest_product_id}"/>
			 			</s:if>
			 			<s:else>
			 				<input type="hidden"  id="invest_product_id" name="baseProductVo.invest_product_id" value="${baseProductVo.invest_product_id}"/>
			 				${baseProductVo.invest_product_id}
			 			</s:else>	
			 		</td>
		    	</tr>
		    	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" >股票单价:</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:if test="baseProductVo.editflag == 0">
			 				<input type="text"  id="price" onblur="value=value.replace(/[^\d.]/g,'')" name="baseProductVo.price" value="${baseProductVo.price}"/>元
			 			</s:if>
			 			<s:else>
			 				<input type="hidden"  id="price" name="baseProductVo.price" value="${baseProductVo.price}"/>
			 				${baseProductVo.price}元
			 			</s:else>	
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" >申购上限:</td>
			 		<td class="tbl_td_15_b">
			 			<s:if test="baseProductVo.editflag == 0">
			 				<input type="text"  id="up_limit" name="baseProductVo.up_limit" value="${baseProductVo.up_limit}"/>股
			 			</s:if>
			 			<s:else>
			 				<input type="hidden"  id="up_limit" name="baseProductVo.up_limit" value="${baseProductVo.up_limit}"/>
			 				${baseProductVo.up_limit}股
			 			</s:else>	
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">资金解冻日期:</td>
			 		<td class="tbl_td_15_b ">
			 			<s:if test="baseProductVo.editflag == 0">
			 				<input id="fundfree_date" name="baseProductVo.fundfree_date" class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${baseProductVo.fundfree_date}"/>
			 			</s:if>
			 			<s:else>
			 				<input type="hidden"  id="fundfree_date" name="baseProductVo.fundfree_date" value="${baseProductVo.fundfree_date}"/>
			 				${baseProductVo.fundfree_date}
			 			</s:else>
			 			
			 		</td>				 	
			 		<td class="tbl_td_15_b tbl_td_lbcolor">中签公布日期:</td>
			 		<td class="tbl_td_15_b ">
			 			<s:if test="baseProductVo.editflag == 0">
			 				<input id="lot_date" name="baseProductVo.lot_date" class="Wdate" type="text" onblur="lovchk()"  onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${baseProductVo.lot_date}"/>
			 			</s:if>
			 			<s:else>
			 				<input type="hidden"  id="lot_date" name="baseProductVo.lot_date" value="${baseProductVo.lot_date}"/>
			 				${baseProductVo.lot_date}
			 			</s:else>
			 		</td>
		    	</tr>
		    	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" >募集开始时间:</td>
			 		<td class="tbl_td_15_b">
			 			<s:if test="baseProductVo.editflag == 0">
			 				<input id="start_date" name="baseProductVo.start_date"  class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true,dateFmt: 'yyyy-MM-dd HH:mm'})" value="${baseProductVo.start_date}"/>
			 			</s:if>
			 			<s:else>
			 				<input type="hidden"  id="start_date" name="baseProductVo.start_date" value="${baseProductVo.start_date}"/>
			 				${baseProductVo.start_date}
			 			</s:else>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" >募集结束时间:</td>
			 		<td class="tbl_td_15_b">
			 			<s:if test="baseProductVo.editflag == 0">
			 				<input id="end_date" name="baseProductVo.end_date"  class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true,dateFmt: 'yyyy-MM-dd HH:mm'})" value="${baseProductVo.end_date}"/>
			 			</s:if>
			 			<s:else>
			 				<input type="hidden"  id="end_date" name="baseProductVo.end_date" value="${baseProductVo.end_date}"/>
			 				${baseProductVo.end_date}
			 			</s:else>
			 		</td>
		    	</tr>
		    	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">申购代码:</td>
			 		<td class="tbl_td_15_b">
			 			<s:if test="baseProductVo.editflag == 0">
			 				<input type="text" id="apply_code" onblur="value=value.replace(/[^\d.]/g,'')" name="baseProductVo.apply_code"  value="${baseProductVo.apply_code}"  maxlength="12"/>
			 			</s:if>
			 			<s:else>
			 				<input type="hidden"  id="apply_code" name="baseProductVo.apply_code" value="${baseProductVo.apply_code}"/>
			 				${baseProductVo.apply_code}
			 			</s:else>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" >中签率:</td>
			 		<td class="tbl_td_15_b">
			 			<s:if test="baseProductVo.editflag != 2">
			 				<input type="text" id="lot_rate" onblur="value=value.replace(/[^\d.]/g,'')" readonly name="baseProductVo.lot_rate" value="${baseProductVo.lot_rate}"  maxlength="12"/>%
			 			</s:if>
			 			<s:else>
			 				<input type="hidden"  id="lot_rate" name="baseProductVo.lot_rate" value="${baseProductVo.lot_rate}"/>
			 				${baseProductVo.lot_rate}
			 			</s:else>
			 		</td>
		    	</tr>
		    </table>
	    </fieldset>
	    </form>
	</div>
</div>
</body>
</html>