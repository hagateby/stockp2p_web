<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/sysmgr/pubresultmgr/pubresultmt.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>结算</title>
</head>
<body>
<div class="grid_10">
	<div style="padding-left:300px;color:red;">
		<label id="lerror" class="errmsg"><tag:msg msgcode="${msgCode}"></tag:msg></label>
	</div>
	<div class="p20bs color-white-bg fn-clear" id="maindiv">
	<s:form action="/pubresult/queryIssurInfo_issueMgrAction.action" method="post" theme="simple" id="cancleform">
	</s:form>
	<s:form action="/pubresult/savePubResult_pubResultMgrAction.action" method="post" theme="simple" id="pubresultform">
	<input type="hidden" id="basic_product_id" name="pubResultVo.basic_product_id"/>
	<input type="hidden" id="result_no" name="pubResultVo.result_no"/>
	<input type="hidden" id="invt_product_id" name="invt_product_id" value="${investProductVo.invt_product_id}"/>
	</s:form>
	<div class="fn-clear head">
     	<div class="fn-right">
     		<a id="cancleInfoBtn" class="ui-button  ui-button-mid ui-button-green" onclick="cancleinfo()">取消</a>
     	</div>
  	</div>
  	
	<div class="p20bs color-white-bg fn-clear">
	<fieldset style="border:0px">
		<legend class="fieldset-lgend">投资产品结算信息</legend>
		<br/>
		<table>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width">投资产品名称</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property  value="settleVo.invt_product_name"/>
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width">投资总保证金</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleVo.product_investbail_all"/>元
		 		</td>
		 	</tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width">投资总管理费</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleVo.product_investcharge_all"/>元
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width">投资购买产品总金额</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleVo.product_buycount_all"/>元
		 		</td>
		 	</tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width">总投资额</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleVo.product_investcount_all"/>元
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width">总投资收入</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleVo.product_recivecount_all"/>元
		 		</td>
		 	</tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width">应付用户总额</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleVo.pay_user_countall"/>元
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width">用户投资总收益额</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleVo.user_invest_profit"/>元
		 		</td>
		 	</tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width">平台总收益额</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleVo.platform_profit"/>元
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width">产品投资收益率</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleVo.product_rate"/>
		 		</td>
		 	</tr>
		 	<tr height="20px"><td colspan="6" align="center"><br></td></tr>
		</table>
	</fieldset>
	</div>
</div>
</div>
</body>
</html>