<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>DEMO测试页面</title>
<script type="text/javascript" src="/p2pstock/static/js/demo/demo.js"></script>
</head>

<body id="body">
    <!--导航 begin-->
    <div>
    <input type="button" onclick="hhhh();" value="同意以下协议，提交注册"></input>
    	<h1>测试框架成功11</h1>
    	<s:form action="/demo/testDemo2_demoAction.action" method="post" theme="simple" id="registform">
    	<table>
    	<tr><td>
    	<input type="hidden" id="messageStruts" name="demoVo.messageStruts" value="11111111"></input>
    	
    	</td>
    	</tr>
    	</table>
    	</s:form>
    </div>
</body>
</html>