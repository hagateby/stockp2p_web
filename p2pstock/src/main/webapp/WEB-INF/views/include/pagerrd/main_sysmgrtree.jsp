<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="tag" uri="/tags/mytags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<script type="text/javascript" src="${webContontRoot}/static/js/sysmgr/sysmgrcommon.js"></script>
<!--lefttreee begin-->
<!-- 我的投资 -->
<form method="post" id="myinvestForm" action="/p2pstock/usercenter/queryInvest_investMgrAction.action?menucode=MYINVESTQRY">
	<input type="hidden" id="user_id" name="investMgrVo.user_id" value="${session.userid}"/>
</form>
<!-- 我的交易 -->
<form method="post" id="mytranForm" action="/p2pstock/fundmgr/queryTranInfo_fundMgrAction.action?menucode=MYTRANQRY">
	<input type="hidden" id="user_id" name="tranInfoVo.user_id" value="${session.userid}"/>
</form>
<!--树形菜单层 -->
<div class="grid_2">
	<div class="ui-side ui-side-withicon" id="sidebar">
		 <s:hidden id="menusec" value="%{#session.menusec}"> </s:hidden>
		 <tag:menu menuorder="2"></tag:menu>
	</div>
</div>
<!--lefttreee end-->