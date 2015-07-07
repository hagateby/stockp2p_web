<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/sysmgr/pubresultmgr/settlehistory.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>结算历史查询</title>
</head>
<body>

<div class="grid_10">
	<div style="position:absolute;padding-left:250px;color:red;"><label id="lerror" class="errmsg "><tag:msg msgcode="${msgCode}"></tag:msg></label></div>
	<s:form action="settleMoreInfo_pubResultMgrAction.action" method="post" theme="simple" id="moreinfoForm">
		<input type="hidden" id="invt_product_id" name ="invt_product_id"/>
	</s:form>
	<s:form action="settleHistory_pubResultMgrAction.action" method="post" theme="simple" id="productQueryForm">
	<div class="p20bs color-white-bg fn-clear">
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">查询条件</legend>
			<table>
				<tr><td height="20px"></td></tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">投资产品名称</td>
			 		<td class="tbl_td_15_b">
			 			<input type="text" id="invt_product_name" name="settleHisVo.invt_product_name" value="${settleHisVo.invt_product_name}"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">投资产品发起人</td>
			 		<td class="tbl_td_15_b">
			 			<input type="text" id="user_name" name="settleHisVo.user_name" value="${settleHisVo.user_name}"/>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">起始时间</td>
			 		<td class="tbl_td_15_b">
			 			<input id="lot_date" name="settleHisVo.start_date"  class="Wdate" type="text"  onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${settleHisVo.start_date}"/>
			 		</td>				 	
			 		<td class="tbl_td_15_b tbl_td_lbcolor">终止时间</td>
			 		<td class="tbl_td_15_b">
			 			<input id="start_date" name="settleHisVo.end_date"  class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${settleHisVo.end_date}"/>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td  class="tbl_td_15_b tbl_td_lbcolor">产品状态</td>
			 		<td class="tbl_td_15_b">
			 			<s:hidden id="invt_product_status" name="settleHisVo.invt_product_status" ></s:hidden>
		            	<s:hidden id="invt_product_statusname" name="settleHisVo.invt_product_statusname" ></s:hidden>
			 			<div style="*z-index:21;">
		            		<div class="ui-selectdrop-new" style="padding-left:5px">
			              		<div class="s_select_bg_new het35" style="left:195px;top:360px;width:150px;align:center" id="secPrtStatusBtn">
					                <span class="txt_new fn-clear">
					                	<em id="secprtstatuspdis"><s:property value="settleHisVo.invt_product_statusname"/></em></span>
					                <span>
					                	<em class="arrow_new2" style="left:265px"></em>
					                </span>
			             		</div>
			             		<div class="popCityBox_new2" style="width:150px;display:none" id="prtstatusdiv">
					                <ul class="has-icon-list-new fn-clear" style="width:120px" >
					                	<li datavalue="4" datacode="105" class="select-lbl">
					                		<em><label onclick="secPrtStatus('','全部');">全部</label></em>
					                	</li>
					                	<s:iterator value="investstatuslist" status="s">
						                    <li datavalue="4" datacode="105" class="select-lbl">
						                      <em><label onclick="secPrtStatus('${para_values}','${para_name}');"><s:property value="para_name"/></label></em>
						                    </li>
					                    </s:iterator>
					                </ul>
			              		</div>
		            		</div>
			      		</div>
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
					<td class="tbl_td_20 tbl_tdtitle160">产品名称</td>
					<td class="tbl_td_20 tbl_tdtitle140">产品发起人</td>
					<td class="tbl_td_20 tbl_tdtitle140">结算日期</td>
					<td class="tbl_td_20 tbl_tdtitle140">平台管理费</td>
					<td class="tbl_td_20 tbl_tdtitle140">产品状态</td>
					<td class="tbl_td_20 tbl_tdtitle160">操作</td>
				</tr>
				<s:iterator value="detaillist" status="s">
					<tr>
						<td class="tbl_td_20"><s:property value="invt_product_name"/></td>
						<td class="tbl_td_20"><s:property value="user_name"/></td>
						<td class="tbl_td_20"><s:property value="update_date"/></td>
						<td class="tbl_td_20"><s:property value="product_investcharge_all"/>元</td>
						<td class="tbl_td_20"><s:property value="invt_product_statusname"/></td>
						<td class="tbl_td_20"><a href="javascript:moreinfo('${invt_product_id}');">详情</a></td>		
					</tr>
				</s:iterator>
				<tr>
					<td colspan="7">
						<!-- 分页标签 -->
						<tag:pages page="${page}" formid="queryform"></tag:pages>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
	</s:form>
</div>
</body>
</html>