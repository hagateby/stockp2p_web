<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
      <div class="ui-tab-content ui-tab-content-current" data-name="exist">
     	<div class="errmsg0"><label id="lerror"   class="errmsg "><s:property value="errmsg"/></label></div>
        <div id="step1" class="step1">
          <p class="go-login">
              忘记密码？<a href="/p2pstock/login/loginInit_loginAction.action">找回密码</a>
          </p>
          <s:form action="/login/login_loginAction.action" method="post" theme="simple" id="loginform">
              <fieldset style="border:0px">
                  <div class="ui-form-item">
                      <label class="ui-label"><span class="ui-form-required">*</span>用户名</label>
                      <input class="ui-input input-icon code" type="text" id="userno" name="loginvo.userno" value="手机号"  autocomplete="off"  maxlength="11"/>
                  	  
                  </div>

                  		
                  <div class="ui-form-item">
                      <label class="ui-label"><span class="ui-form-required">*</span>登录密码</label>
                      <input id=passwd name="loginvo.passwd" class="ui-input input-icon code" type="password" data-is="isPassWord isPassNotAllNum isPassNotRepeat" autocomplete="off" onpaste="return false"/>
                  </div>
                  <div style="position: absolute;padding-left:130px">
                      <input type="button" id="loginBtn" class="ui-button-rrd-blue ui-button-rrd-blue-large" value='立即登录'>
                  </div>
              </fieldset>
          </s:form>
      </div>
      <div class="ui-tab-content" data-name="without">
      </div>
    </div>
  </div>
</div>

</body>
</html>