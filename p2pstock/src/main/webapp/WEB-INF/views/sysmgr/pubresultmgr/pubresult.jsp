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
	<div style="position:absolute;padding-left:250px;color:red;"><label id="lerror" class="errmsg "><tag:msg msgcode="${msgCode}"></tag:msg></label></div>
	<div class="p20bs color-white-bg fn-clear" id="maindiv">
	<s:form action="/pubresult/queryIssurInfo_issueMgrAction.action" method="post" theme="simple" id="cancleform">
	</s:form>
	<s:form action="/pubresult/savePubResult_pubResultMgrAction.action" method="post" theme="simple" id="pubresultform">
		<input type="hidden" id="invt_product_id" name="invt_product_id" value="${investProductVo.invt_product_id}"/>
		<input type="hidden" id="result_noarray" name="pubResultVo.result_noarray"/>
		<input type="hidden" id="basic_product_idarray" name="pubResultVo.basic_product_idarray"/>
	</s:form>
	<div class="fn-clear head">
     	<div class="fn-right">
     		<a id="cancleInfoBtn" class="ui-button  ui-button-mid ui-button-green" onclick="cancleinfo()">取消</a>
     	</div>
  	</div>
  	
	<div class="p20bs color-white-bg fn-clear">
	<fieldset style="border:0px">
		<legend class="fieldset-lgend">产品定义</legend>
		<br/>
		<table>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width">投资产品名称</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property  value="investProductVo.invt_product_name"/>
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width">投资产品发起人</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="investProductVo.user_name"/>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width">产品募集总金额</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="investProductVo.amount"/>元
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width">发起投资管理费</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="investProductVo.issue_fee"/>元
		 		</td>
		 	</tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width">开始时间</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="investProductVo.start_date"/>
		 		</td>				 	
		 		<td class="tbl_td_15_b tbl_td_width">结束时间</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="investProductVo.end_date"/>
		 		</td>
		 	</tr>
		 	<tr height="20px"><td colspan="6" align="center"><br></td></tr>
		</table>
	</fieldset>
	<div id="binddiv" style="display:block">
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">已绑定基础标的</legend>
			<br/>
			<div id="baseprtdiv">
				<table id="settletbl">
						<tr>
							<td class="tbl_td_20 tbl_tdtitle160">产品类型</td>
							<td class="tbl_td_20 tbl_tdtitle160">基础标的</td>
							<td class="tbl_td_20 tbl_tdtitle140">股票代码</td>
							<td class="tbl_td_20 tbl_tdtitlecolor" style="width:80px">股票单价</td>
							<td class="tbl_td_20 tbl_tdtitlecolor" style="text-align:center;width:200px">中签尾号</td>
						</tr>
						<s:iterator value="lmvolist" status="s">
						<tr id="dd">
							<td class="tbl_td_20"><s:property value="basic_product_typename"/></td>
							<td class="tbl_td_20"><s:property value="basic_product_name"/></td>
							<td class="tbl_td_20"><s:property value="invest_product_id"/></td>
							<td class="tbl_td_20"><s:property value="price"/>元</td>
							<td class="tbl_td_20" id="tt2" style="width:200px">
								<s:if test="lot_flag != 1">
									即将在<s:property value="lot_date"/>公布
								</s:if>
								<s:else>
									<input type="hidden" value="${basic_product_id}"/>
									<textarea id="resultno" value="${result_no}" style="width:200px;height:200px">${result_no}</textarea>
								</s:else>
							</td>
						</tr>
						<s:if test="%{#s.last == true}">
							<s:hidden id="settlesize" value="%{#s.count}"></s:hidden>
						</s:if>
					</s:iterator>
					<tr height="20px"></tr>
					<tr>
						<td colspan="5" align="center">
							<input type="button" onclick="saveresult();" class="ui-button-rrd-blue ui-button-rrd-blue-large" style="width:130px" value='保存'">
						</td>
					</tr>
				</table>
			</div>
		</fieldset>
	</div>
	</div>
</div>
</div>
</body>
</html>