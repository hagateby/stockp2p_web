<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<%@ taglib prefix="tag" uri="/tags/mytags" %>
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/regist/regist.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/reg.css"/>
<title>注册初始化页面</title>
</head>
<body>
	
  <!-- TODO -->
  <div id="pg-reg">
  <div class="container_12"> 
  		
	  <div class="ui-box-title fn-clear">
        <h3 class="fn-left w600">用户注册</h3>
      </div>
      <div class="ui-tab-content ui-tab-content-current" data-name="exist">
     	<div class="errmsg0"><label id="p0error"   class="errmsg "><tag:msg msgcode="${msgCode}"></tag:msg></label></div>
        <div id="step1" class="step1">
          <p class="go-login">
              已有账号？<a href="/p2pstock/login/loginInit_loginAction.action">立即登录</a>
          </p>
          <s:form action="/regist/regist_registAction.action" method="post" theme="simple" id="registform">
          	  <s:hidden id="cptcusMobile" name="cptcusMobile"></s:hidden>
              <fieldset style="border:0px">
                  <div class="ui-form-item">
                      <label class="ui-label"><span class="ui-form-required">*</span>手机号码</label>
                      <input class="ui-input input-icon code" type="text" id="cusMobile" name="registvo.cusMobile" onblur="checkphone();" autocomplete="off"  maxlength="11"/>
                  	  <input type="button" class="ui-button-rrd-bluecpt ui-button-rrd-blue-long" id="regist_CptBtn" value="免费获取验证码" ></input>
                  </div>

                  <div class="ui-form-item">
	                  <div style="float:left;display:inline">
	                      <label class="ui-label"><span class="ui-form-required">*</span>登录密码</label>
	                      <input id="cusPass1" name="registvo.cusPass1" onblur="checkpass1();" maxlength="12" onKeyUp="CheckIntensity(this.value,'pwd_Weak','pwd_Medium','pwd_Strong')" class="ui-input input-icon code" type="password" name="password" data-is="isPassWord isPassNotAllNum isPassNotRepeat" autocomplete="off" onpaste="return false"/>
	                  </div>    
	                  <div style="padding-top:3px;padding-left:10px;float:left;display:inline">
	                      <table border="0" cellpadding="0" cellspacing="0"> 
							  <tr align="center"> 
							    <td id="pwd_Weak" class="pwd pwd_c">&nbsp;</td> 
							    <td id="pwd_Medium" class="pwd pwd_c pwd_f">无</td> 
							    <td id="pwd_Strong" class="pwd pwd_c pwd_c_r">&nbsp;</td> 
							  </tr> 
						  </table> 
                  	  </div>          
                  </div>
                  <div class="ui-form-item">
	                  <div style="float:left;display:inline">
	                      <label class="ui-label"><span class="ui-form-required">*</span>重复密码</label>
	                      <input id="cusPass2" name="registvo.cusPass2" onblur="checkpass2();" maxlength="12" onKeyUp="CheckIntensity(this.value,'pwd_Weak2','pwd_Medium2','pwd_Strong2')" class="ui-input input-icon code" type="password" name="password" data-is="isPassWord isPassNotAllNum isPassNotRepeat" autocomplete="off" onpaste="return false"/>
	                  </div>    
	                  <div style="padding-top:3px;padding-left:10px;float:left;display:inline">
	                      <table border="0" cellpadding="0" cellspacing="0"> 
							  <tr align="center"> 
							    <td id="pwd_Weak2" class="pwd pwd_c">&nbsp;</td> 
							    <td id="pwd_Medium2" class="pwd pwd_c pwd_f">无</td> 
							    <td id="pwd_Strong2" class="pwd pwd_c pwd_c_r">&nbsp;</td> 
							  </tr> 
						  </table> 
                  	  </div>          
                  </div>
                  <div class="ui-form-item code-item">
                      <label class="ui-label"><span class="ui-form-required">*</span>验证码</label>
                      <input class="ui-input input-icon code" type="text" id="cusCpt" name="registvo.cusCpt" onblur="checkcpt();" />
                  </div>

                  <div class="ui-form-item" style="height: 40px">
                  		<input id="agreeck" type="checkbox" name="checkbox" value="checkbox2">我已阅读并同意<a href="/p2pstock/static/html/WebProtolHtml.htm" target="_blank">《众筹平台网站服务协议》</a>
                  </div>

                  <div class="ui-form-item">
                      <div class="pl50">
                          <input type="button" id="registbtn"onclick="regist()" class="ui-button-rrd-blue ui-button-rrd-blue-large" value='立即注册'>
                      </div>
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