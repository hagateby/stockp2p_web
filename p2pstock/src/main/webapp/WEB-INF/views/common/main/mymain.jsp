<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 

<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/regist/regist.js"></script>
<title>我的首页</title>
</head>
<body>
	<div>
	<table>
		<tr>
			<td style="color:red">
				${userid}${errmsg} 登录后首页，页面内容待完善112<tag:msg msgcode="${msgCode}"></tag:msg>hhh<s:property value="#session.userid"/>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>