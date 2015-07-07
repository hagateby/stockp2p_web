<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/login/login.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/reg.css"/>
<title>登录初始化页面</title>
</head>
<body>
	
  <!-- TODO -->
 <div id="pg-reg">
 	<div class="container_12">
		<div class="ui-box-title fn-clear">
        	<h3 class="fn-left w600">用户登录</h3>
		</div>
		<div class="errmsg0"><label id="lerror"   class="errmsg "><tag:msg msgcode="${msgCode}"></tag:msg></label></div>
		<p class="go-login">忘记密码？<a href="/p2pstock/login/findpwdInit_loginAction.action">找回密码</a>
        </p>
        <s:form action="/login/login_loginAction.action" method="post" theme="simple" id="loginform">
        	<fieldset style="border:0px">
            	<div class="ui-form-item">
                	<label class="ui-label-login"><span class="ui-form-required">*</span>用户名</label>
                    <input class="ui-input input-icon code" type="text" id="userno" name="loginvo.userno" value="手机号"  autocomplete="off"  maxlength="11"/>
                 </div>
                 <div class="ui-form-item">
                 	<label class="ui-label-login"><span class="ui-form-required">*</span>登录密码</label>
                    <input id=passwd name="loginvo.passwd" class="ui-input input-icon code" type="password" data-is="isPassWord isPassNotAllNum isPassNotRepeat" autocomplete="off" onpaste="return false"/>
                 </div>
                 <div style="position: absolute;padding-left:130px">
                 	<input type="button" id="loginBtn" class="ui-button-rrd-blue ui-button-rrd-blue-large" value='立即登录'>
                 </div>
            </fieldset>
        </s:form>
		<div class="ui-tab-content" data-name="without">
		</div>
	</div>
</div>

</body>
</html>