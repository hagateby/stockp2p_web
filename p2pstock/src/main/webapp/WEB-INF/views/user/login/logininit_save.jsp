<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/login/login.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/user/login/login.css" />
<title>登录初始化页面</title>
</head>
<body>
	
	<div>
		<div class="p15p pt10p b1ed bcf">
			<div class="title_01 clearfix">
				<h2 class="fl">
					<span>用户登录</span>
				</h2>
			</div>
			<s:form action="/login/login_loginAction.action" method="post" theme="simple" id="loginform">
			<s:hidden  id="loginflg" name="loginvo.loginflg">  </s:hidden>
			<s:hidden  id="errmsg" name="errmsg">  </s:hidden>
			<table >
				<tr height="80px" >
					<td></td>
					<td colspan="3">
					<label id="lerror" class="valloginerr"></label>
					</td>
				</tr>
				<tr>
					<td width="200px" ></td>
					<td><span class="cred">*</span>用户名：</td>
					<td>
						<input id="userno" name="loginvo.userno" class="text_login" tabindex="1" type="text" value="用户名/手机号" size="27" maxlength="30"/>
					</td>
					<td width="100px"></td>
				</tr>
				<tr>
					<td ></td>
					<td><span class="cred">*</span>登录密码：</td>
					<td><input id=passwd name="loginvo.passwd" class="text_pub"tabindex="1" type="password" value="" size="27"/></td>
					<td></td>
				</tr>

				<tr>
					<td ></td>
					<td COLSPAN ="2" align="center">
						<input type="button" id="loginBtn" value="立 即 登 录" class="button button-orange"></input>
					</td>
					<td></td>
				</tr>
			</table>
			</s:form>
		</div>
	</div>
</body>
</html>