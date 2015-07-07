<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/sysmgr/productmgr/productmt.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>基础标的维护</title>
</head>
<body>

<div class="grid_10">

		<s:form action="/sysmgr/productaddInit_productMgrAction.action" method="post" theme="simple" id="addform">
			<s:hidden id="addproducttyp" name="baseProductVo.basic_product_type"></s:hidden>
		</s:form>
		<s:form action="/sysmgr/productMoreInfo_productMgrAction.action" method="post" theme="simple" id="moreinfoform">
			<s:hidden id="basic_product_id" name="baseProductVo.basic_product_id"></s:hidden>
		</s:form>
		<s:form action="/sysmgr/productEditInfo_productMgrAction.action" method="post" theme="simple" id="editinfoform">
			<s:hidden id="ebasic_product_id" name="baseProductVo.basic_product_id"></s:hidden>
		</s:form>
		<s:form action="/sysmgr/productDelSub_productMgrAction.action" method="post" theme="simple" id="delinfoform">
			<s:hidden id="dbasic_product_id" name="baseProductVo.basic_product_id"></s:hidden>
		</s:form>
		<form method="post" id="productQueryForm" action="/p2pstock/sysmgr/productmgrInit_productMgrAction.action">
		<s:hidden id="basic_product_type" name="baseProductVo.basic_product_type" value="0"></s:hidden>
		<div class="p20bs color-white-bg fn-clear">
			<div style="padding-left:300px;color:red;">
				<label id="lerror" class="errmsg"><tag:msg msgcode="${msgCode}"></tag:msg></label>
			</div>
			<fieldset style="border:0px">
				<legend class="fieldset-lgend">查询条件</legend>
				<table>
					<tr><td height="20px"></td></tr>
				 	<tr>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">基础标的名称</td>
				 		<td class="tbl_td_15_b" >
				 			<input type="text" id="basic_product_name" style="width:100px"  name="baseProductVo.basic_product_name" value="${baseProductVo.basic_product_name}"/>
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">股票代码</td>
				 		<td class="tbl_td_15_b">
				 			<input type="text" style="width:100px" id="invest_product_id" name="baseProductVo.invest_product_id" value="${baseProductVo.invest_product_id}"/>
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">中签公布日期</td>
				 		<td class="tbl_td_15_b">
				 			<input id="lot_date" name="baseProductVo.lot_date" style="width:100px"  class="Wdate" type="text"  onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${baseProductVo.lot_date}"/>
				 		</td>
				 	</tr>
				 	<tr>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">开始日期</td>
				 		<td class="tbl_td_15_b">
				 			<input id="start_date" name="baseProductVo.start_date" style="width:100px"  class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${baseProductVo.start_date}"/>
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_lbcolor">结束日期</td>
				 		<td class="tbl_td_15_b">
				 			<input id="end_date" name="baseProductVo.end_date"  style="width:100px"  class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${baseProductVo.end_date}"/>
				 		</td>
				 		<td class="tbl_td_15_b"></td>
				 		<td >
				 		</td>
				 	</tr>
				 	<tr height="20px"><td colspan="6" align="center"></td></tr>
				 	<tr>
				 		<td colspan="6" align="center">
				 			<input type="button" id="secBtn" class="ui-button-rrd-blue ui-button-rrd-blue-large" value='查询'">
				 		</td>
				 	</tr>
				</table>
			</fieldset>
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
						<td class="tbl_td_20 tbl_tdtitle160">操作</td>
					</tr>
					<s:iterator value="productList" status="s">
						<tr>
							<td class="tbl_td_20 "><s:property value="basic_product_name"/></td>
							<td class="tbl_td_20 "><s:property value="sales_cityname"/></td>
							<td class="tbl_td_20 "><s:property value="invest_product_id"/></td>
							<td class="tbl_td_20 "><s:property value="start_date"/></td>
							<td class="tbl_td_20 "><s:property value="end_date"/></td>
							<td class="tbl_td_20 "><s:property value="lot_date"/></td>
							<td class="tbl_td_20 ">
								<a href="javascript:moreinfo('${basic_product_id}');">详情</a>
								<a href="javascript:editinfo('${basic_product_id}');">修改</a>
								<s:if test=" flag == null">
									<a href="javascript:delinfo('${basic_product_id}');">删除</a>
								</s:if>
							</td>		
						</tr>
					</s:iterator>
					<tr>
						<td colspan="7">
							<!-- 分页标签 -->
							<tag:pages page="${page}" formid="productQueryForm"></tag:pages>
						</td>
					</tr>
				</table>
			</fieldset>
		</div>
		</form>
	</div>
</body>
</html>