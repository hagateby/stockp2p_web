<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/tranquery.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/usercentercommon.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>交易查询</title>
</head>
<body>

<div class="grid_10">

	<div style="padding-left:200px;color:red;">
		<label id="lerror" class="errmsg"><tag:msg msgcode="${msgCode}"></tag:msg></label>
	</div>
	<s:form action="/fundmgr/queryTranInfo_fundMgrAction.action" method="post" theme="simple" id="queryform">
	<input type="hidden" id="user_id" name="tranInfoVo.user_id" value="${session.userid}"/>
	<div class="p20bs color-white-bg fn-clear">
		<fieldset style="border:0px">
		<legend class="fieldset-lgend">查询条件</legend>
		<table>
			<tr>
				<td class="tbl_td_15_b tbl_td_lbcolor">
					产品名称
				</td>
				<td class="tbl_td_15_b">
					<input type="text" id="invt_product_name" style="width:140px" name="tranInfoVo.invt_product_name" value="${tranInfoVo.invt_product_name}"/>
				</td>
	    		<td class="tbl_td_15_b tbl_td_lbcolor">收付状态:
	    		</td>
		 		<td class="tbl_td_15_b">
		 			<s:hidden id="invt_product_status" name="tranInfoVo.payment_type" ></s:hidden>
		 			<s:hidden id="invt_product_statusname" name="tranInfoVo.payment_typename" ></s:hidden>
		 			<div style="*z-index:21;">
	            		<div class="ui-selectdrop-new" style="padding-left:5px">
		              		<div class="s_select_bg_new het35" style="left:195px;top:360px;width:150px;align:center" id="secPrtStatusBtn"">
				                <span class="txt_new fn-clear"><em id="secprtstatuspdis"><s:property value="tranInfoVo.payment_typename"/></em></span><span><em class="arrow_new2" style="left:585px"></em></span>
		             		</div>
		             		
		             		<div class="popCityBox_new" id="prtstatusdiv" style="display:none">
				                <ul class="has-icon-list-new fn-clear" style="width:80px" >
						            <li datavalue="4" datacode="105" class="select-lbl">
					                	<em><label onclick="secPrtStatus('','全部');">全部</label></em>
					                </li>
				                	<s:iterator value="investstatuslist" status="s" >
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
		 	<tr>
 				<td class="tbl_td_15_b tbl_td_lbcolor" style="width:100px">开始日期</td>
		 		<td class="tbl_td_15_b">
		 			<input id="start_date" name="tranInfoVo.start_date" style="width:140px"  class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${tranInfoVo.start_date}"/>
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_lbcolor" style="width:100px">结束日期</td>
		 		<td class="tbl_td_15_b">
		 			<input id="end_date" name="tranInfoVo.end_date"  style="width:140px"  class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${tranInfoVo.end_date}"/>
		 		</td>
			</tr>
			<tr height="20px"><td colspan="4" align="center"></td></tr>
		 	<tr>
		 		<td colspan="4" align="center">
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
					<td class="tbl_td_20 tbl_tdtitle140">交易日期</td>
					<td class="tbl_td_20 tbl_tdtitle140">投资人</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:100px">投资产品</td>
					<td class="tbl_td_20 tbl_tdtitlecolor"  style="width:180px">交易类型</td>
					<td class="tbl_td_20 tbl_tdtitlecolor"  style="width:80px">收付类型</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:140px">交易额</td>
				</tr>
				<s:iterator value="traninfolist" status="s">
					<tr>
						<td class="tbl_td_20"><s:property value="update_date"/></td>
						<td class="tbl_td_20"><s:property value="user_name"/></td>
						<td class="tbl_td_20"><s:property value="invt_product_name"/></td>
						<td class="tbl_td_20"><s:property value="transion_typename"/></td>
						<td class="tbl_td_20"><s:property value="payment_typename"/></td>
						<td class="tbl_td_20_mount"><s:property value="amount"/>元</td>
					</tr>
				</s:iterator>
				<tr height="20px"></tr>
				<tr>
					<td colspan="10">
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