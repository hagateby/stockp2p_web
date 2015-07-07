<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/invest/investnomgr/investnoquery.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>配号查询</title>
</head>
<body>
<!--nav begin-->
<div class="container_12 mt10">
  <ul class="ui-breadcrumb grid_12 fn-clear" id="navul">
    <li class="fn-left ui-breadcrumb-item ui-breadcrumb-text ui-breadcrumb-text-first">
      <a href="/p2pstock/init/index.action" id="mainlb">首页</a>
    </li>
    <li class="fn-left ui-breadcrumb-item ui-breadcrumb-separator"><br></li>
    <li class="fn-left ui-breadcrumb-item ui-breadcrumb-text ui-breadcrumb-text-current">
      <a href="#" id="sublb">配号查询</a>
    </li>
  </ul>
</div>
<!--nav end-->
 <div class="container_12">
  	<br/>
	<div style="padding-left:200px;color:red;">
		<label id="lerror" class="errmsg">
			<tag:msg msgcode="${msgCode}"></tag:msg>
		</label>
	</div>
	<s:form action="/ajax/moreIssueInfo_issueMgrAction.action" method="post" theme="simple" id="moreform">
		<s:hidden id="invt_product_id" name="invt_product_id"></s:hidden>
	</s:form>
	<s:form action="/invest/queryInvestNoInfo_investNoMgrAction.action" method="post" theme="simple" id="queryform">
	<input type="hidden"  id="qinvt_product_id" name="investNoInfoVo.invt_product_id" value="${investNoInfoVo.invt_product_id}"/>
	<div class="p20bs color-white-bg fn-clear" id="morediv" style="display:none">
     	<div class="fn-right">
     		<a id="cancleBtn" onclick="canclemore()" class="ui-button ui-button-green">关闭</a>
     	</div>
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">产品详细信息</legend>
			<br/>
			<div id="moreinvestdiv"></div>
		</fieldset>
	</div>
	<div class="p20bs color-white-bg fn-clear" id="nowdiv">
		<div class="fn-right">
     		<a id="cancleBtn" onclick="history.go(-1)" class="ui-button ui-button-green">取消</a>
     	</div>
		<table>
		<tr>
			<td class="tbl_td_15_b tbl_td_lbcolor">
				股票代码
			</td>
			<td class="tbl_td_15_b">
				<input type="text" id="invest_product_id" name="investNoInfoVo.invest_product_id" value="${investNoInfoVo.invest_product_id}"/>
			</td>
			<td style="padding-left:50px">
				<input type="button" id="secBtn" class="ui-button-rrd-blue-new2" style="width:100px;" value="查询">
			</td>
		</tr>
		</table>
	</div>
	<div class="p20bs color-white-bg fn-clear" id="hisdiv">
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">配号列表</legend>
			<br/>
			<table>
				<tr>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:100px">产品名称</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:80px">股票代码</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:100px">股票名称</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:80px">投资人</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:180px">起始签号</td>
					<td class="tbl_td_20 tbl_tdtitlecolor"  style="width:80px">购买签数</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:100px">中签尾号</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:80px">中签数</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:100px">投资额</td>
					<td class="tbl_td_20 tbl_tdtitlecolor" style="width:100px">投资利润</td>
				</tr>
				<s:iterator value="nolist" status="s" >
					<tr>
						<td class="tbl_td_20">
							<a href="javascript:moreinfo('${invt_product_id}');"><s:property value="invt_product_name"/></a> 
						</td>
						<td class="tbl_td_20"><s:property value="invest_product_id"/></td>
						<td class="tbl_td_20"><s:property value="basic_product_name"/></td>
						<td class="tbl_td_20"><s:property value="invest_user_phone"/></td>
						<td class="tbl_td_20"><s:property value="start_no"/></td>
						<td class="tbl_td_20"><s:property value="sub_code"/></td>
						<td class="tbl_td_20" style="width:100px;">
						<div style="width:100px; white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" onmouseover="display(<s:property value='#s.index'/>)" onmouseout="disappear(<s:property value='#s.index'/>)">${result_no}</div>
						<div class="hiddiv" id="msgdiv<s:property value='#s.index'/>" onmouseover="display(<s:property value='#s.index'/>)" onmouseout="disappear(<s:property value='#s.index'/>)">${result_no}</div>
						</td>
						<td class="tbl_td_20"><s:property value="praise_count"/></td>
						<td class="tbl_td_20"><s:property value="product_buycount_all"/>元</td>
						<td class="tbl_td_20"><s:property value="user_settle_profit"/>元</td>
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