<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<%@ taglib prefix="tag" uri="/tags/mytags" %>
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/usercenter.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/usercentercommon.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<title>用户中心</title>
</head>
<body>


<div class="grid_10">
	<div style="padding-left:200px;color:red;">
		<label id="lerror" class="errmsg">
			<tag:msg msgcode="${msgCode}"></tag:msg>
		</label>
	</div>
</div>
</body>
</html>