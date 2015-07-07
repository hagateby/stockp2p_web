<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/loginpwdedit.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/usercentercommon.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<title>用户中心</title>
</head>
<body>


<div class="grid_10">
	<div class="p20bs color-white-bg fn-clear">
		 <div class="fn-clear head">
	     	<div class="title fn-left">登录密码修改</div>
	     	<div class="errmsgdiv"><label id="lerror"   class="errmsg "><tag:msg msgcode="${msgCode}"></tag:msg></label></div>
	     </div>
		<form data-name="userbasic" enctype="multipart/form-data" class="ui-form" method="post" id="userInfoForm" action="/p2pstock/usercenter/loginPwdEditSave_userCenterAction.action">
			<div id="edtdiv">
	     		<div class="userphoto" id="userphoto">
	     	 	   <a id="modUserPhoto" class="photo" href="#"><img src="/p2pstock//static/images/rrd/ucenter-pp.png" /></a>
	     	 	   <input name="avatar" id="modUserPhotoInput" type="hidden" value="" />
	     	 	</div>
	     	 	<div class="inputs">
	     	 		
	     	 		<div class="ui-form-item">
		            	<label class="ui-label"><span class="ui-form-required"></span>原登录密码</label>
		                <input class="ui-input code" type="password" id="login_pwd" onKeyUp="CheckIntensity(this.value,'pwd_Weak','pwd_Medium','pwd_Strong')" onblur="checkpass1();" name="userInfoVo.login_pwd" value="${userInfoVo.user_nickname}"  autocomplete="off"  maxlength="12"/>
	                 </div>

	                <div class="ui-form-item">
		     	 		<div  style="float:left;display:inline">
		                    <label class="ui-label"><span class="ui-form-required"></span>新登录密码</label>
		                    <input class="ui-input code" type="password" id="newlogin_pwd" onKeyUp="CheckIntensity(this.value,'pwd_Weak2','pwd_Medium2','pwd_Strong2')" onblur="checkpass2();" name="userInfoVo.newlogin_pwd"   maxlength="12"/>
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
	               
	               	 <div class="ui-form-item">
		     	 		<div  style="float:left;display:inline">
		                    <label class="ui-label"><span class="ui-form-required"></span>再次输入新登录密码</label>
		                    <input class="ui-input code" type="password" id="newlogin_pwd2" onKeyUp="CheckIntensity(this.value,'pwd_Weak3','pwd_Medium3','pwd_Strong3')" onblur="checkpass3();"  maxlength="12"/>
		                </div>
		                <div style="padding-top:3px;padding-left:10px;float:left;display:inline">
	                        <table border="0" cellpadding="0" cellspacing="0"> 
							  <tr align="center"> 
							    <td id="pwd_Weak3" class="pwd pwd_c">&nbsp;</td> 
							    <td id="pwd_Medium3" class="pwd pwd_c pwd_f">无</td> 
							    <td id="pwd_Strong3" class="pwd pwd_c pwd_c_r">&nbsp;</td> 
							  </tr> 
					 		</table> 
                  	     </div>   
	                 </div>
	            	<div class="ui-form-item">
	                	<input id="savebtn" type="button" class="ui-button ui-button-green ui-button-mid" value="保 存">
	            	</div>
	     		</div>
     		</div>
    	</form>
	</div>
</div>
</body>
</html>