<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/regist/regist.js"></script>
<title>注册初始化页面</title>
</head>
<body>
	
	<div>
		<div class="p15p pt10p b1ed bcf">
			<div class="title_01 clearfix">
				<h2 class="fl">
					<span>新用户注册</span>
				</h2>
				<p class="fr fs12p">
					我已经注册过了，直接<a href="http://www.caidor.com/login"
						class="arrow_red ml5p cred">登录</a>
				</p>
			</div>
			<div class="div_center">
			<s:form action="/regist/regist_registAction.action" method="post" theme="simple" id="registform">
			<input type="hidden" name="test" value="11111111"></input>
			<table>
				<tr>
					<td width="200px" ><label id="perror" class="valerr">请正确填写手机号码 </label></td>
					<td><span class="cred">*</span>我的手机号：</td>
					<td>
						<input id="cusMobile" name="registvo.cusMobile" onblur="checkphone();" class="text_pub" tabindex="1" type="text" value="" size="27" maxlength="11"/>
					</td>
					<td><input type="button" id="regist_CptBtn" value="免费获取验证码" class="button button-orange"></input></td>
				</tr>
				<tr>
					<td ><label id="p1error" class="valerr">密码不能为空</label></td>
					<td><span class="cred">*</span>设置密码：</td>
					<td><input id="cusPass1" name="registvo.cusPass1" class="text_pub" onblur="checkpass1();" tabindex="1" type="password" value="" size="27" maxlength="11"/></td>
					<td></td>
				</tr>
				<tr>
					<td><label id="p2error" class="valerr">重复密码不能为空</td>
					<td><span class="cred">*</span>确认密码：</td>
					<td><input id="cusPass2" name="registvo.cusPass2" onblur="checkpass2();" class="text_pub" tabindex="1" type="password" value="" size="27" maxlength="11"/></td>
					<td></td>
				</tr>
				<tr>
					<td><label id="p3error" class="valerr">验证码不能为空</td>
					<td><span class="cred">*</span>验证码：</td>
					<td><input id="cusCpt" name="registvo.cusCpt" class="onlyNum text_pub" onblur="checkcpt();" tabindex="1" type="text" value="" size="27" maxlength="6"/></td>
					<td></td>
				</tr>
				<tr height="20px"></tr>
				<tr>
					<td ></td>
					<td COLSPAN ="2" align="center"><input type="button" onclick="regist()" value="提交注册" class="button button-orange"></input></td>
					<td ></td>
				</tr>
			</table>
			</s:form>
			</div>

		</div>
		<div class="div_protol" style="height: 40px">
            <input id="agree" name="agree" checked="checked" type="checkbox"> 我已阅读并同意<a href="/p2pstock/static/html/WebProtolHtml.htm" target="_blank">《众筹网站服务协议》</a>
            <label id="label-agree" for="agree" class="error" style="display: none"></label>
        </div>
	</div>
</body>
</html>