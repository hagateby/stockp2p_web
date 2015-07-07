<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/sysmgr/issuemgr/investproductmore.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>发行产品管理</title>
</head>
<body>

<div class="grid_10">
	<div class="p20bs color-white-bg fn-clear" id="maindiv">
		<div style="padding-left:300px;color:red;">
			<label id="lerror" class="errmsg"><tag:msg msgcode="${msgCode}"></tag:msg></label>
		</div>
		<s:form action="/sysmgr/queryIssurInfo_issueMgrAction.action" method="post" theme="simple" id="cancleform">
		</s:form>
		<s:form action="/sysmgr/editIssurSub_issueMgrAction.action" method="post" theme="simple" id="saveform">
		<input type="hidden"  id="invt_product_id" name="investProductVo.invt_product_id" value="${investProductVo.invt_product_id}"/>
		<div class="fn-clear head">
	     	<div class="title fn-left">投资产品信息维护</div>
	     	<div class="fn-right">
	     		<a id="saveInfoBtn" class="ui-button ui-button-mid ui-button-green" onclick="saveinfo()">保存</a>
	     		<a id="cancleInfoBtn" href="javascript:history.go(-1);location.reload()" class="ui-button ui-button-mid ui-button-green">取消</a>
	     	</div>
   		</div>
		<div class="p20bs color-white-bg fn-clear">
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">产品定义</legend>
			<table>
				<tr><td height="20px"></td></tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">投资产品名称</td>
			 		<td class="tbl_td_15_b">
			 			<input type="text" id="invt_product_name" name="investProductVo.invt_product_name" value="${investProductVo.invt_product_name}" onblur="checkprdtname();"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">投资产品发起人</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="#session.username"/>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">发行上限</td>
			 		<td class="tbl_td_15_b">
			 			<input type="text" id="amount" name="investProductVo.amount"  value="${investProductVo.amount}"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">发起投资管理费</td>
			 		<td class="tbl_td_15_b">
			 			<label id="issue_fee" style="color:red;">${investProductVo.issue_fee}</label>元
			 			<input type="hidden" id="issue_feetxt" name="investProductVo.issue_fee" value="${investProductVo.issue_fee}">
			 		</td>
			 	</tr>
			 	<tr>
			 		<td  class="tbl_td_15_b tbl_td_lbcolor">开始时间</td>
			 		<td class="tbl_td_15_b">
			 			<label id="start_date_label">${investProductVo.start_date}</label>
			 			<input type="hidden" id="start_date" name="investProductVo.start_date" value="${investProductVo.start_date}">
			 		</td>				 	
			 		<td  class="tbl_td_15_b tbl_td_lbcolor">终止时间</td>
			 		<td class="tbl_td_15_b">
			 			<label id="end_date_label">${investProductVo.end_date}</label>
			 			<input type="hidden" id="end_date" name="investProductVo.end_date" value="${investProductVo.end_date}">
			 		</td>
			 	</tr>
			 	<tr>
			 		<td  class="tbl_td_15_b tbl_td_lbcolor">投资人管理费计提方式</td>
			 		<td class="tbl_td_15_b">
			 			
			 			<input type="hidden" id="user_accruedcharges_type" name="investProductVo.user_accruedcharges_type" value="${investProductVo.user_accruedcharges_type}"/>
			 			<div style="*z-index:21;">
		            		<div class="ui-selectdrop-new" style="padding-left:5px" >
			              		<div class="s_select_bg_new het35" style="left:195px;top:360px;width:150px;align:center" id="secAccChargeTypBtn">
					                <span class="txt_new fn-clear"><em id="secaccchargetypdis">${investProductVo.user_accruedcharges_typename}</em></span>
					                <span><em class="arrow_new2" style="left:320px"></em></span>
			             		</div>
			             		<div class="popCityBox_new2" style="width:150px;display:none" id="accchargetypdiv">
					                <ul class="has-icon-list-80 fn-clear">
					                	<s:iterator value="accchargetyplist" status="s">
						                    <li datavalue="4" datacode="105" class="select-lbl">
						                      <em><label onclick="secAccChargeTyp('${para_values}','${para_name}');"><s:property value="para_name"/></label></em>
						                    </li>
					                    </s:iterator>
					                </ul>
			              		</div>
		            		</div>
			      		</div>
			 		</td>				 	
			 		<td  class="tbl_td_15_b tbl_td_lbcolor"><label id="investmgrlbl">投资人管理费计提比例:</label></td>
			 		<td class="tbl_td_15_b">
			 			<input type="text" id="user_accruedcharges_amount"  name="investProductVo.user_accruedcharges_amount" value="${investProductVo.user_accruedcharges_amount}" style="width:100px" maxlength="12" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
			 			<label id="investmgrcell">%</label>
			 		</td>
			 	</tr>
			 	<tr>
		    		<s:hidden id="launcher_accruedcharges_type" name="investProductVo.launcher_accruedcharges_type" value="0"></s:hidden>
		    		<!-- 需求不明确，1期暂不支持按比例提取 -->
			 		<s:hidden id="starter_bail" name="investProductVo.starter_bail" value="0"></s:hidden>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" style="width:120px">投资人保证金比例:</td>
			 		<td class="tbl_td_15_b">
			 			<input type="text"  id="invester_bail" name="investProductVo.invester_bail" style="width:100px" value="${investProductVo.invester_bail}" maxlength="12" onkeyup="value=value.replace(/[^\d.]/g,'')"/>%
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" style="width:120px">发起人管理费计提比例:</td>
			 		<td class="tbl_td_15_b">
			 			<input type="text" id="launcher_accruedcharges_amount" name="investProductVo.launcher_accruedcharges_amount" value="${investProductVo.launcher_accruedcharges_amount}" style="width:100px" maxlength="12" readonly = true value="0.00"/>%
			 		</td>

			 	</tr>
			 	<tr height="20px"><td colspan="6" align="center"><br></td></tr>
			</table>
		</fieldset>
		<div id="binddiv" style="display:block">
			<fieldset style="border:0px">
				<legend class="fieldset-lgend">已绑定基础标的</legend>
				<div id="binddiv">
					<table id="bindtbl">
					<input type="hidden" id="base_product_idarray" name="investProductVo.base_product_idarray" value="${investProductVo.base_product_idarray}"/>
						<s:iterator value="lmvolist" status="s">
							<tr id="${basic_product_id}">
								<td class="tbl_td_15" ><s:property value="basic_product_typename"/></td>
								<td class="tbl_td_15"><s:property value="invest_product_id"/></td>
								<td class="tbl_td_15"><s:property value="basic_product_name"/></td>
								<td class='tbl_td_15'><a href="javascript:moreinfo('${basic_product_id}')">产品详情</a></td>
								<td class='tbl_td_15'></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</fieldset>
		</div>
		</s:form>
		<div>
			<table>
				<tr><td align="center" height="10px"></td></tr>
				<tr>
					<td align="center" style="padding-left:250px;">
						<input type="button" id="bindBtn" class="ui-button-rrd-blue ui-button-rrd-blue-large" value="绑定基础标的"/>
					</td>
				</tr>
				<tr><td align="center" height="10px"><br></td></tr>
			</table>
		</div>
		<s:form action="/ajax/productmgrInit_productMgrAction.action" method="post" theme="simple" id="bindform">
			<input type="hidden" id="startdatearray" name="investProductVo.startdatearray" value="${investProductVo.startdatearray}"/>
			<input type="hidden" id="enddatearray" name="investProductVo.enddatearray" value="${investProductVo.enddatearray}"/>
			<fieldset style="border:0px">
			<div id="basediv" style="display:none">
			<legend class="fieldset-lgend" >基础标的列表</legend>
			产品代码：<input type="text" id="invest_product_id" name="baseProductVo.invest_product_id" value="${baseProductVo.invest_product_id}"/>
			产品名称：<input type="text" id="basic_product_name" name="baseProductVo.basic_product_name" value="${baseProductVo.basic_product_name}"/>
			<a id="ajaxqueryBtn" class="ui-button ui-button-mid ui-button-green">检索</a>
			</div>
			<div id="baseprtdiv"></div>
			</fieldset>
		</s:form>
	</div>
	</div>
	<s:form action="/ajax/productMoreInfo_productMgrAction.action" method="post" theme="simple" id="moreform">
		<input type="hidden" id="more_basic_product_id" name="baseProductVo.basic_product_id"/>
		<div id="morediv">
		</div>
	</s:form>
	
</div>
</body>
</html>