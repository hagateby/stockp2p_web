<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/sysmgr/pubresultmgr/settlepay.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>应收应付查询</title>
</head>
<body>

<div class="grid_10">
	<div style="position:absolute;padding-left:250px;color:red;"><label id="lerror" class="errmsg "><tag:msg msgcode="${msgCode}"></tag:msg></label></div>
	<s:form action="/settlepay/settlePay_pubResultMgrAction.action" method="post" theme="simple" id="settlepayform">
		<input type="hidden" id="ustl_id" name="settleVo.ustl_id"/>
		<input type="hidden" id="invt_product_id" name="invt_product_id"/>
	</s:form>
	<s:form action="querySettlePay_pubResultMgrAction.action" method="post" theme="simple" id="productQueryForm">
	<div class="p20bs color-white-bg fn-clear" style="width:900px;">
		<fieldset style="border:0px;>
			<legend class="fieldset-lgend">查询条件</legend>
			<table style="width:900px;">
				<tr><td height="20px"></td></tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">用户手机号</td>
			 		<td class="tbl_td_15_b">
			 			<input type="text" id="phone" name="settleVo.phone" value="${settleVo.phone}"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">产品名称</td>
			 		<td class="tbl_td_15_b">
			 			<input type="text" id="invt_product_name" name="settleVo.invt_product_name" value="${settleVo.invt_product_name}"/>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor">起始时间</td>
			 		<td class="tbl_td_15_b">
			 			<input id="lot_date" name="settleVo.start_date"  class="Wdate" type="text"  onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${settleVo.start_date}"/>
			 		</td>				 	
			 		<td class="tbl_td_15_b tbl_td_lbcolor">终止时间</td>
			 		<td class="tbl_td_15_b">
			 			<input id="start_date" name="settleVo.end_date"  class="Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn',isShowClear:true})" value="${settleVo.end_date}"/>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td  class="tbl_td_15_b tbl_td_lbcolor">结算收付状态</td>
			 		<td class="tbl_td_15_b">
			 			<s:hidden id="settle_flag" name="settleVo.settle_flag" ></s:hidden>
			 			<s:hidden id="settle_flagname" name="settleVo.settle_flagname" ></s:hidden>
			 			<div style="*z-index:21;">
		            		<div class="ui-selectdrop-new" style="padding-left:5px">
			              		<div class="s_select_bg_new het35" style="left:195px;top:360px;width:150px;align:center" id="secPrtStatusBtn">
					                <span class="txt_new fn-clear">
					                	<em id="secprtstatuspdis"><s:property value="settleVo.settle_flagname"/></em></span>
					                <span>
					                	<em class="arrow_new2" style="left:330px"></em>
					                </span>
			             		</div>
			             		<div class="popCityBox_new2" style="width:150px;display:none" id="prtstatusdiv">
					                <ul class="has-icon-list-new fn-clear" style="width:120px" >
					                	<li datavalue="4" datacode="105" class="select-lbl">
					                		<em><label onclick="secPrtStatus('','全部');">全部</label></em>
					                	</li>
					                	<s:iterator value="settlepaystatuslist" status="s">
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
	<div class="p20bs color-white-bg fn-clear" style="width:900px;">
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">结算应收应付信息</legend>
			<div id="baseprtdiv">
				<table style="width:900px;">
						<tr>
							<td class="tbl_td_20 tbl_tdtitle140">产品名称</td>
							<td class="tbl_td_20 tbl_tdtitlecolor">投资人</td>
							<td class="tbl_td_20 tbl_tdtitle140">平台应收利润</td>
							<td class="tbl_td_20 tbl_tdtitle140">平台应付利润</td>
							<td class="tbl_td_20 tbl_tdtitlecolor" style="width:160px">平台应收管理费</td>
							<td class="tbl_td_20 tbl_tdtitlecolor" style="width:160px">平台应付保证金</td>
							<td class="tbl_td_20 tbl_tdtitle140" >平台结账总额</td>
							<td class="tbl_td_20 tbl_tdtitlecolor" style="width:160px">收付款状态</td>
							<td class="tbl_td_20 tbl_tdtitle140">操作</td>
						</tr>
						<s:iterator value="listusvo" status="s">
							<tr>
								<td class="tbl_td_20"><s:property value="invt_product_name"/></td>
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
						<tr>
							<td colspan="9">
								<!-- 分页标签 -->
								<tag:pages page="${page}" formid="productQueryForm"></tag:pages>
							</td>
						</tr>
				</table>
			</div>
		</fieldset>
	</div>
	</s:form>
</div>
</body>
</html>