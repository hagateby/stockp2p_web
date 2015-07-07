<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/login/findpwd.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/reg.css"/>
<title>找回密码</title>
</head>
<body>
	
  <!-- TODO -->
 <div id="pg-reg">
 	<div class="container_12">
		<div class="ui-box-title fn-clear">
        	<h3 class="fn-left w600">找回密码</h3>
		</div>
		<div class="errmsg0"><label id="lerror"   class="errmsg "><tag:msg msgcode="${msgCode}"></tag:msg></label></div>
        <p class="go-login">
        </p>
        <s:form action="/login/findpwd_loginAction.action" method="post" theme="simple" id="findpwdform">
        	<s:hidden id="cptcusMobile" name="cptcusMobile"></s:hidden>
        	<fieldset style="border:0px">
            	<div class="ui-form-item">
                	<label class="ui-label-login"><span class="ui-form-required">*</span>手机号码</label>
                    <input class="ui-input input-icon code" type="text" id="userno" name="loginvo.userno" value="手机号"  autocomplete="off"  maxlength="11"/>
                 	<input type="button" class="ui-button-rrd-bluecpt ui-button-rrd-blue-long" id="regist_CptBtn" value="免费获取验证码" ></input>
                 </div>
                  <div class="ui-form-item code-item">
                      <label class="ui-label-login"><span class="ui-form-required">*</span>验证码</label>
                      <input class="ui-input input-icon code" type="text" id="cusCpt" name="loginvo.cusCpt" onblur="checkcpt();" />
                  </div>
                  <div style="position: absolute;padding-left:130px">
                 	<input type="button" id="findBtn" class="ui-button-rrd-blue ui-button-rrd-blue-large" value='确定'>
                 </div>
            </fieldset>
        </s:form>
		<div class="ui-tab-content" data-name="without">
		</div>
	</div>
</div>

</body>
</html>