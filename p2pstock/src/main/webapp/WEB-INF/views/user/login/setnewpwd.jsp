<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/login/setpwd.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/reg.css"/>
<title>登录初始化页面</title>
</head>
<body>
	
  <!-- TODO -->
 <div id="pg-reg">
 	<div class="container_12">
		<div class="ui-box-title fn-clear">
        	<h3 class="fn-left">找回密码</h3>
        	<h3 class="fn-left">-</h3>
        	<h3 class="fn-left">设置登录密码</h3>
		</div>
		<div class="errmsg0"><label id="lerror"   class="errmsg "><tag:msg msgcode="${msgCode}"></tag:msg></label></div>
		<p class="go-login">
        </p>
        <s:form action="/login/setnewpwd_loginAction.action" method="post" theme="simple" id="loginform">
        	<fieldset style="border:0px">
        	
                <input type="hidden" id="userno" name="loginvo.userno" value="${loginvo.userno}" />
	        	<div class="ui-form-item">
	               	<div style="float:left;display:inline">
	                     <label class="ui-label-login"><span class="ui-form-required">*</span>登录密码</label>
	                     <input id="passwd" name="loginvo.passwd" onblur="checkpass1();" maxlength="12" onKeyUp="CheckIntensity(this.value,'pwd_Weak','pwd_Medium','pwd_Strong')" class="ui-input input-icon code" type="password" name="password" data-is="isPassWord isPassNotAllNum isPassNotRepeat" autocomplete="off" onpaste="return false"/>
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
	                      <label class="ui-label-login"><span class="ui-form-required">*</span>重复密码</label>
	                      <input id="cusPass2" onblur="checkpass2();" maxlength="12" onKeyUp="CheckIntensity(this.value,'pwd_Weak2','pwd_Medium2','pwd_Strong2')" class="ui-input input-icon code" type="password" name="password" data-is="isPassWord isPassNotAllNum isPassNotRepeat" autocomplete="off" onpaste="return false"/>
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
                 <div style="position: absolute;padding-left:130px">
                 	<input type="button" id="loginBtn" class="ui-button-rrd-blue ui-button-rrd-blue-large" value='确定'>
                 </div>
            </fieldset>
        </s:form>
		<div class="ui-tab-content" data-name="without">
		</div>
	</div>
</div>

</body>
</html>