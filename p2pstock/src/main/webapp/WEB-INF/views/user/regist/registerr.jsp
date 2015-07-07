<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/regist/regist.js"></script>
<title>注册初始化页面</title>
</head>
<body>
	<div>
	<table>
		<tr>
			<td>
				<label id="perror"><s:property value="registRsMsg"/> </label>
			</td>
			<td>
				<a href="/p2pstock/regist/registInit_registAction.action"><span>重新注册</span></a>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>