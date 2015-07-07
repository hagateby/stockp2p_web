<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/sysmgr/pubresultmgr/settlemore.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>结算详情</title>
</head>
<body>

<div class="grid_10">
	<div class="p20bs color-white-bg fn-clear" id="maindiv" style="width:900px">
		<s:form action="/settle/settleHistory_pubResultMgrAction.action" method="post" theme="simple" id="cancleform">
		</s:form>
		<s:form action="/settle/settlePay_pubResultMgrAction.action" method="post" theme="simple" id="settlepayform">
			<input type="hidden" id="ustl_id" name="settleVo.ustl_id"/>
			<input type="hidden" id="invt_product_id" name="invt_product_id"/>
		</s:form>
		<div class="fn-clear head">
	     	<div class="title fn-left">产品结算详细信息</div>
	     	<div class="fn-right">
	     		<a id="cancleInfoBtn" class="ui-button ui-button-mid ui-button-green" onclick="cancleinfo()">取消</a>
	     	</div>
	  	</div>
	 </div>
	<div class="p20bs color-white-bg fn-clear" style="width:900px">
	<fieldset style="border:0px">
		<legend class="fieldset-lgend">结算汇总信息</legend>
		<table>
			<tr><td height="20px"></td></tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">投资产品名称</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property  value="settleMoreInfoVo.invt_product_name"/>
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">投资产品发起人</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleMoreInfoVo.user_name"/>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">产品总中签股数</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleMoreInfoVo.praise_count"/>股
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">产品总管理费</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleMoreInfoVo.product_investcharge_all"/>元
		 		</td>
		 	</tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">产品总投资费用</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleMoreInfoVo.product_buycount_all"/>元
		 		</td>				 	
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">产品总利润</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleMoreInfoVo.user_invest_profit"/>元
		 		</td>
		 	</tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">结算日期</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="settleMoreInfoVo.update_date"/>
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width"></td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 		</td>	
		 	</tr>
		 	<tr height="20px"><td colspan="6" align="center"><br></td></tr>
		</table>
	</fieldset>
	<div id="binddiv" style="display:block">
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">结算应收应付信息</legend>
			<div id="baseprtdiv">
				<table style="width:850px">
						<tr>
							<td class="tbl_td_20 tbl_tdtitle140">投资人</td>
							<td class="tbl_td_20 tbl_tdtitle140">应收利润</td>
							<td class="tbl_td_20 tbl_tdtitle140">应付利润</td>
							<td class="tbl_td_20 tbl_tdtitle140">应收管理费</td>
							<td class="tbl_td_20 tbl_tdtitle140">应付保证金</td>
							<td class="tbl_td_20 tbl_tdtitle140">结账总额</td>
							<td class="tbl_td_20 tbl_tdtitle140">收付款状态</td>
							<td class="tbl_td_20 tbl_tdtitle140">操作</td>
						</tr>
						<s:iterator value="listusvo" status="s">
							<tr>
								<td class="tbl_td_20"><s:property value="user_name"/></td>
								<td class="tbl_td_20"><s:property value="user_invest_profitpay"/>元</td>
								<td class="tbl_td_20"><s:property value="user_invest_profit"/>元</td>
								<td class="tbl_td_20"><s:property value="user_investcharge_all"/>元</td>
								<td class="tbl_td_20"><s:property value="user_investbail_all"/>元</td>
								<td class="tbl_td_20"><s:property value="user_paycount_all"/>元</td>
								<td class="tbl_td_20"><s:property value="settle_flagname"/></td>
								<td class="tbl_td_20">
									<s:if test="settle_flag == 0">
										<a href="javascript:settlepay('${ustl_id}','${invt_product_id}');">利润分配</a>
									</s:if>
									<s:else>
										分配结束
									</s:else>
								</td>
							</tr>
						</s:iterator>
				</table>
			</div>
		</fieldset>
	</div>
	</div>
</div>
</body>
</html>