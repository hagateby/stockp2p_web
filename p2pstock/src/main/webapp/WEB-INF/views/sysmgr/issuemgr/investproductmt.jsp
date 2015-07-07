<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/sysmgr/issuemgr/investproductmt.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>投资产品维护</title>
</head>
<body>

<div class="grid_10">
	<s:form action="/sysmgr/addIssurInit_issueMgrAction.action" method="post" theme="simple" id="addform">
	</s:form>
	<s:form action="/sysmgr/moreIssueInfo_issueMgrAction.action" method="post" theme="simple" id="moreform">
	<s:hidden id="invt_product_id" name="investProductVo.invt_product_id"></s:hidden>
	</s:form>
	<s:form action="/sysmgr/editIssueInfoInit_issueMgrAction.action" method="post" theme="simple" id="editform">
	<s:hidden id="einvt_product_id" name="investProductVo.invt_product_id"></s:hidden>
	</s:form>
	<s:form action="/sysmgr/delIssurSub_issueMgrAction.action" method="post" theme="simple" id="delform">
	<s:hidden id="dinvt_product_id" name="investProductVo.invt_product_id"></s:hidden>
	</s:form>
	<div style="padding-left:200px;color:red;">
		<label id="lerror" class="errmsg"><tag:msg msgcode="${msgCode}"></tag:msg></label>
	</div>
	<s:form action="/sysmgr/queryIssurInfo_issueMgrAction.action" method="post" theme="simple" id="queryform">
	<div class="p20bs color-white-bg fn-clear">
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">查询条件</legend>
			<table>
				<tr><td height="20px"></td></tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" >投资产品名称</td>
			 		<td class="tbl_td_15_b">
			 			<input type="text" id="invt_product_name" name="investProductVo.invt_product_name" value="${investProductVo.invt_product_name}"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" >投资产品发起人</td>
			 		<td class="tbl_td_15_b">
			 			<input type="text" id="user_name" name="investProductVo.user_name" value="${investProductVo.user_name}"/>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor" >开始时间</td>
			 		<td class="tbl_td_15_b">
			 			<input id="lot_date" name="investProductVo.start_date"  class="Wdate" type="text"  onFocus="WdatePicker({lang:'zh-cn'})" value="${investProductVo.start_date}"/>
			 		</td>				 	
			 		<td class="tbl_td_15_b tbl_td_lbcolor" >终止时间</td>
			 		<td class="tbl_td_15_b">
			 			<input id="start_date" name="investProductVo.end_date"  class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn'})" value="${investProductVo.end_date}"/>
			 		</td>
			 	</tr>
			 	<tr>
		    		<td class="tbl_td_15_b tbl_td_lbcolor" >产品状态:
		    		</td>
			 		<td class="tbl_td_15_b">
			 			<s:hidden id="invt_product_status" name="investProductVo.invt_product_status" ></s:hidden>
			 			<s:hidden id="invt_product_statusname" name="investProductVo.invt_product_statusname" ></s:hidden>
			 			<div style="*z-index:21;">
		            		<div class="ui-selectdrop-new" style="padding-left:5px">
			              		<div class="s_select_bg_new het35" style="left:195px;top:360px;width:150px;align:center" id="secPrtStatusBtn">
					                <span class="txt_new fn-clear"><em id="secprtstatuspdis"><s:property value="investProductVo.invt_product_statusname"/></em></span><span><em class="arrow_new2" style="left:267px"></em></span>
			             		</div>
			             		
			             		<div class="popCityBox_new" id="prtstatusdiv" style="display:none">
					                <ul class="has-icon-list-new fn-clear" style="width:120px" >
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
					<td class="tbl_td_20 tbl_tdtitle140">产品名称</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:100px">产品发起人</td>
					<td class="tbl_td_20 tbl_tdtitlecolor"  style="width:180px">开始日期</td>
					<td class="tbl_td_20 tbl_tdtitlecolor"  style="width:180px">结束日期</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:80px">产品状态</td>
					<td class="tbl_td_20 tbl_tdtitle160">操作</td>
				</tr>
				<s:iterator value="investlist" status="s">
					<tr>
						<td class="tbl_td_20"><s:property value="invt_product_name"/></td>
						<td class="tbl_td_20"><s:property value="user_name"/></td>
						<td class="tbl_td_20"><s:property value="investstart_date"/></td>
						<td class="tbl_td_20"><s:property value="investend_date"/></td>
						<td class="tbl_td_20"><s:property value="invt_product_statusname"/></td>
						<td class="tbl_td_20">
							<a href="javascript:editinfo('${invt_product_id}');">修改</a>
							<a href="javascript:moreinfo('${invt_product_id}');">详情</a>
							<s:if test=" flag == null">
								<a href="javascript:delinfo('${invt_product_id}');">删除</a>
							</s:if>
							
						</td>		
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