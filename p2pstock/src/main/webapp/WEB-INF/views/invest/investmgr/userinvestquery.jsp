<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/invest/investmgr/userinvestquery.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>基础标的维护</title>
</head>
<body>

<div class="grid_10">
		<form method="post" id="auditForm" action="/p2pstock/sysmgr/auditInvest_investMgrAction.action">
			<input type="hidden" id="audit_user_invest_id" name="investMgrVo.user_invest_id" />
			<input type="hidden" id="audit_auditflag" name="investMgrVo.auditflag" />
		</form>
		<form method="post" id="moreForm" action="/p2pstock/sysmgr/queryInvestMore_investMgrAction.action">
			<input type="hidden" id="user_invest_id" name="user_invest_id" />
		</form>
		<form method="post" id="queryForm" action="/p2pstock/sysmgr/queryInvest_investMgrAction.action">
		<div class="p20bs color-white-bg fn-clear" style="width:850px;">
			<div style="padding-left:300px;color:red;">
				<label id="lerror" class="errmsg"><tag:msg msgcode="${msgCode}"></tag:msg></label>
			</div>
			<fieldset style="border:0px">
				<legend class="fieldset-lgend">查询条件</legend>
				<table>
					<tr><td height="20px"></td></tr>
				 	<tr>
				 		<td class="tbl_td_15_b tbl_td_lbcolor" style="width:150px">产品名称</td>
				 		<td class="tbl_td_15_b" >
				 			<input type="text" id="invt_product_name" style="width:150px"  name="investMgrVo.invt_product_name" value="${investMgrVo.invt_product_name}"/>
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_lbcolor" style="width:150px">投资状态</td>
				 		<td class="tbl_td_15_b" >
				 			<s:hidden id="invest_status" name="investMgrVo.invest_status"></s:hidden>
				 			<s:hidden id="invest_statusname" name="investMgrVo.invest_statusname"></s:hidden>
				 			<div style="*z-index:21;">
			            		<div class="ui-selectdrop-new" style="padding-left:5px">
				              		<div class="s_select_bg_new het35" style="left:195px;top:360px;width:150px;align:center" id="secBtn01">
						                <span class="txt_new fn-clear">
						                	<em id="secdis01">${investMgrVo.invest_statusname}</em></span>
						                <span>
						                	<em class="arrow_new2" style="left:696px"></em>
						                </span>
				             		</div>
				             		<div class="popCityBox_new2" style="width:150px;display:none" id="listdiv01">
						                <ul class="has-icon-list-new fn-clear" style="width:120px">
						                    <li datavalue="4" datacode="105" class="select-lbl" onclick="sec01('','全部');">
						                      <em><label>全部</label></em>
						                    </li>
						                	<s:iterator value="investstatuslist" status="s">
							                    <li datavalue="4" datacode="105" class="select-lbl"  onclick="sec01('<s:property value="para_values"/>','<s:property value="para_name"/>');">
							                      <em><label><s:property value="para_name"/></label></em>
							                    </li>
						                    </s:iterator>
						                </ul>
				              		</div>
				              	</div>
			            	</div>
				 		</td>
				 	</tr>
				 	<tr>
				 		<td class="tbl_td_15_b tbl_td_lbcolor" style="width:150px">开始日期</td>
				 		<td class="tbl_td_15_b">
				 			<input id="start_date" name="investMgrVo.start_date" style="width:150px"  class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${investMgrVo.start_date}"/>
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_lbcolor" style="width:150px">结束日期</td>
				 		<td class="tbl_td_15_b">
				 			<input id="end_date" name="investMgrVo.end_date"  style="width:150px"  class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${investMgrVo.end_date}"/>
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
		<div class="p20bs color-white-bg fn-clear" style="width:850px;">
			<fieldset style="border:0px">
				<legend class="fieldset-lgend">查询列表</legend>
				<table style="width:850px">
					<tr>
						<td class="tbl_td_20 tbl_tdtitle140">产品名称</td>
						<td class="tbl_td_20 tbl_tdtitlecolor" style="width:100px">投资用户</td>
						<td class="tbl_td_20 tbl_tdtitlecolor" style="width:140px">投资日期</td>
						<td class="tbl_td_20 tbl_tdtitlecolor"  style="width:120px">投资额</td>
						<td class="tbl_td_20 tbl_tdtitlecolor"  style="width:120px">保证金</td>
						<td class="tbl_td_20 tbl_tdtitle140">结算管理费</td>
						<td class="tbl_td_20 tbl_tdtitle140">结算利润</td>
						<td class="tbl_td_20 tbl_tdtitle140">投资状态</td>
						<td class="tbl_td_20 tbl_tdtitle160">操作</td>
					</tr>
					<s:iterator value="investlist" status="s">
						<tr>
							<td class="tbl_td_20 "><a href="javascript:moreinfo('${user_invest_id}');"><s:property value="invest_prdouct_name"/></a></td>
							<td class="tbl_td_20 "><s:property value="user_name"/></td>
							<td class="tbl_td_20 "><s:property value="update_date"/></td>
							<td class="tbl_td_20 "><s:property value="acount_prtfeeall"/>元</td>
							<td class="tbl_td_20 "><s:property value="acount_bailall"/>元</td>
							<td class="tbl_td_20 "><s:property value="acount_chargeall"/>元</td>
							<td class="tbl_td_20 "><s:property value="user_settle_profit"/>元</td>
							<td class="tbl_td_20 "><s:property value="invest_statusname"/></td>
							<td class="tbl_td_20 ">
								<s:if test="invest_status == 1 ">
									<a href="javascript:audit('${user_invest_id}','1');">通过</a>
									<a href="javascript:audit('${user_invest_id}','0');">不通过</a>
								</s:if>
								<s:else>
									已审核
								</s:else>
								
							</td>		
						</tr>
					</s:iterator>
					<tr>
						<td colspan="7">
							<!-- 分页标签 -->
							<tag:pages page="${page}" formid="queryForm"></tag:pages>
						</td>
					</tr>
				</table>
			</fieldset>
			</form>
		</div>
	</div>
</body>
</html>