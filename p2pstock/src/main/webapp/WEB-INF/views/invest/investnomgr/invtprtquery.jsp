<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/invest/investnomgr/invtprtquery.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>我要投资</title>
</head>
<body>
<!--nav begin-->
<div class="container_12 mt10">
  <ul class="ui-breadcrumb grid_12 fn-clear" id="navul">
    <li class="fn-left ui-breadcrumb-item ui-breadcrumb-text ui-breadcrumb-text-first">
      <a href="/p2pstock/init/index.action" id="mainlb">首页</a>
    </li>
    <li class="fn-left ui-breadcrumb-item ui-breadcrumb-separator"></li>
    <li class="fn-left ui-breadcrumb-item ui-breadcrumb-text ui-breadcrumb-text-current">
      <a href="#" id="sublb">配号查询</a>
    </li>
  </ul>
</div>
<!--nav end-->
 <div class="container_12">

  	<br/>
	<s:form action="/invest/queryInvestNoInfo_investNoMgrAction.action" method="post" theme="simple" id="investform">
		<input type="hidden"  id="invt_product_id" name="investNoInfoVo.invt_product_id"/>
	</s:form>
	<s:form action="/invest/queryIssurInfo_issueMgrAction.action" method="post" theme="simple" id="nowinvestform">
		<input type="hidden"  id="ainvt_product_name" name="investProductVo.invt_product_name"/>
	</s:form>
	<s:form action="/ajax/moreIssueInfo_issueMgrAction.action" method="post" theme="simple" id="moreform">
	<s:hidden id="nowinvt_product_id" name="investProductVo.invt_product_id"></s:hidden>
	</s:form>
	<div style="padding-left:200px;color:red;">
		<label id="lerror" class="errmsg"><tag:msg msgcode="${msgCode}"></tag:msg></label>
	</div>
	<s:form action="/sysmgr/queryIssurInfo_issueMgrAction.action" method="post" theme="simple" >
	<div class="p20bs color-white-bg fn-clear" id="nowdiv">
		<table>
		<tr>
			<td class="tbl_td_15_b tbl_td_lbcolor">
				产品名称
			</td>
			<td class="tbl_td_15_b">
				<input type="text" id="invt_product_name" name="investProductVo.invt_product_name" value="${investProductVo.invt_product_name}"/>
			</td>
			<td style="padding-left:50px">
				<input type="button" onclick="queryprtinfo()" class="ui-button-rrd-blue-new2" style="width:100px;" value="查询">
			</td>
		</tr>
		</table>
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">投资产品列表</legend>
			<br/>
			<div id="avlinvestdiv"></div>
		</fieldset>
		<br/><br/><br/><br/>
	</div>
	</s:form>
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
</div>
</body>
</html>