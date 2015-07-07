<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/sysmgr/usrmgr/moreinfo.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>用户详情</title>
</head>
<body>

<div class="grid_10">
	<div class="p20bs color-white-bg fn-clear" id="maindiv" style="width:900px">
		<s:form action="usrmgr/queryUserInfo_usrMgrAction.action" method="post" theme="simple" id="cancleform">
		</s:form>
		<s:form action="/ajax/queryUserBaseInfo_usrMgrAction.action" method="post" theme="simple" id="userbaseinfoform">
			<input type="hidden" name="queryuserid" value="${queryuserid}"//>
		</s:form>
		<s:form action="/ajax/queryUserBaseInfo_usrMgrAction.action" method="post" theme="simple" id="userbankinfoform">
			<input type="hidden" name="queryuserid" value="${queryuserid}"//>
		</s:form>
		<div class="fn-clear head">
	     	<div class="title fn-left">用户详细信息</div>
	     	<div class="fn-right">
	     		<a id="cancleInfoBtn" class="ui-button ui-button-mid ui-button-green" onclick="cancleinfo()">取消</a>
	     	</div>
	  	</div>
	 </div>
	<div class="p20bs color-white-bg fn-clear" style="width:900px">
	<fieldset style="border:0px">
		<legend class="fieldset-lgend">用户基本信息</legend>
		<div id="baseinfodiv"></div>
	</fieldset>
	<br/>
	<fieldset style="border:0px">
		<legend class="fieldset-lgend">用户银行卡信息</legend>
		<div id="bankinfodiv"></div>
	</fieldset>
	</div>
</div>
</body>
</html>