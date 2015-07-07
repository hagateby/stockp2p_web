<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/invest/investmgr/investinfo.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>发行产品管理</title>
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
      <a href="#" id="sublb">我要投资</a>
    </li>
  </ul>
</div>
<s:form action="/invest/queryInvestInfo_investMgrAction.action" method="post" theme="simple" id="reinvestform">
</s:form>
<!--nav end-->
 <div class="container_12">
 	<div style="position: absolute;padding-top:150px;padding-left:300px;font-size:20px;font-weight:600;">恭喜您购买成功，请尽快完成线下付款</div>
	<div class="p20bs color-white-bg fn-clear">
		<div style="padding-top:100px;padding-left:150px;">
			<img src="/p2pstock/static/images/rrd/sucess.jpg"/>
		</div>
		<div style="padding-top:30px;padding-left:350px">
			<input type="button" onclick ="reinvest()" class="ui-button-rrd-blue ui-button-rrd-blue-large" value="继续购买"/>
		</div>
	</div>
</div>
</body>
</html>