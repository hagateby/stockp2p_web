<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/regist/regist.js"></script>
<title>注册初始化页面</title>
</head>
<body>
<!--nav begin-->
<div class="container_12 mt10">
  <ul class="ui-breadcrumb grid_12 fn-clear" id="navul">
    <li class="fn-left ui-breadcrumb-item ui-breadcrumb-text ui-breadcrumb-text-first">
      <a href="/p2pstock/init/index.action" id="mainlb"></a>
    </li>
    <li class="fn-left ui-breadcrumb-item ui-breadcrumb-text ui-breadcrumb-text-current">
      <a href="#" id="sublb"></a>
    </li>
  </ul>
</div>
<s:form action="/login/loginInit_loginAction.action" method="post" theme="simple" id="loginform">
</s:form>
<s:form action="/init/index.action" method="post" theme="simple" id="homeform">
</s:form>
<!--nav end-->
 <div class="container_12">
 	<div style="position: absolute;padding-top:150px;padding-left:300px;font-size:20px;font-weight:600;"><tag:msg msgcode="${msgCode}"></tag:msg></div>
	<div class="p20bs color-white-bg fn-clear">
		<div style="padding-top:100px;padding-left:150px;">
			<img src="/p2pstock/static/images/rrd/sucess.jpg"/>
		</div>
		<div style="padding-top:30px;padding-left:350px">
			<input type="button" onclick = "history.go(-1)" class="ui-button-rrd-blue ui-button-rrd-blue-large" value="返回"/>
		</div>
	</div>
</div>
</body>
</html>