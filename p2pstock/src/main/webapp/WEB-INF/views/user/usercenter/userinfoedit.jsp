<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<%@ taglib prefix="tag" uri="/tags/mytags" %>
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/usercenter.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/usercentercommon.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<title>用户中心</title>
</head>
<body>


<div class="grid_10">
	<div class="p20bs color-white-bg fn-clear">
		 <div class="fn-clear head">
	     	<div class="title fn-left">个人基础信息</div>
	     	<div class="fn-right"><a id="modiForm" class="ui-button ui-button-mid ui-button-green">修改信息</a></div>
	     	<div class="errmsgdiv">
		     	<label id="lerror"   class="errmsg ">
		     		<tag:msg msgcode="${msgCode}"></tag:msg>
		     	</label>
	     	</div>
	     </div>
		<form data-name="userbasic" enctype="multipart/form-data" class="ui-form" method="post" id="userInfoForm" action="/p2pstock/usercenter/userSave_userCenterAction.action">
			 <div id="disdiv" style="display:block">

	     	 	 <div class="userphoto" id="userphoto">
	     	 	   <a id="modUserPhoto" class="photo" href="#"><img src="/p2pstock//static/images/rrd/ucenter-pp.png" /></a>
	     	 	    <input name="avatar" id="modUserPhotoInput" type="hidden" value="" />
	     	 	</div>
	     	 	<div class="inputs">
	     	 		  <div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>昵称</label>
	                    <label class="ui-label-p ">
	                   		<s:property value="userInfoVo.user_nickname"/>
	                    </label>
	                  </div>
	                  <div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>真实姓名</label>
	                    <label class="ui-label-p ">
	                    	<s:property value="userInfoVo.user_name"/>
	                    </label>
	                  </div>
	                  <div class="ui-form-item" >
	                    <label class="ui-label"><span class="ui-form-required"></span>身份证号</label>
	                    <label class="ui-label-p ">
	                    	<s:property value="userInfoVo.certif_no"/>
	                    </label>
	                  </div>
	                  <div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>手机号码</label>
	                    <label class="ui-label-p ">
		                    <s:property value="userInfoVo.phone"/>
		                    <span style="color:red">已绑定</span>
	                    </label>
	                  </div>
	                  <div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>推荐人手机号码</label>
	                    <label class="ui-label-p ">
	                    	<s:property value="userInfoVo.recod_phone"/>
	                    </label>
	                  </div>
	                  <div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>安全级别</label>
	                    <label class="ui-label-p ">
	                   		手机认证
	                    </label>
	                  </div>
	                  <div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>邮箱地址</label>
	                    <label class="ui-label-p ">
	                    	<s:property value="userInfoVo.mail"/>
	                    </label>
	                  </div>
	     	 	</div>
     	 	</div>
			<div id="edtdiv" style="display:none">
	     		<div class="userphoto" id="userphoto">
	     	 	   <a id="modUserPhoto" class="photo" href="/getMediaPicker.action"><img src="/p2pstock//static/images/rrd/ucenter-pp.png" /></a>
	     	 	   <input name="avatar" id="modUserPhotoInput" type="hidden" value="" />
	     	 	</div>
	     	 	<div class="inputs">
	     	 		<div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>昵称</label>
	                    <label class="ui-label-p ">
	                    	<input class="ui-input code" type="text" id="userno" name="userInfoVo.user_nickname" value="${userInfoVo.user_nickname}"  autocomplete="off"  maxlength="10"/>
	                	</label>
	                </div>
	                <div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>真实姓名</label>
	                    <label class="ui-label-p ">
	                    	<input class="ui-input code" type="text" id="userno" name="userInfoVo.user_name"  value="${userInfoVo.user_name}" autocomplete="off"  maxlength="10"/>
	                	</label>
	                </div>
	                <div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>身份证号</label>
	                    <label class="ui-label-p ">
	                    	<input class="ui-input code" type="text" id="certif_no" name="userInfoVo.certif_no"  value="${userInfoVo.certif_no}" onblur="chkcertno()" maxlength="18"/>
	               		</label>
	                </div>
	                <div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>手机号码</label>
	                    <label class="ui-label-p ">
		                    <s:property value="userInfoVo.phone"/>
		                    <span class="icon-status pass">已绑定</span>
	                    </label>
	                </div>
	                <div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>推荐人手机号码</label>
	                    <label class="ui-label-p ">
	                    	<input class="ui-input code" type="text" id="recod_phone" name="userInfoVo.recod_phone"  onblur="chkmobile()" value="${userInfoVo.recod_phone}"  autocomplete="off"  maxlength="11"/>
	                	</label>
	                </div>
	                <div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>安全级别</label>
	                    <label class="ui-label-p">手机认证</label>
	                </div>
	                <div class="ui-form-item">
	                    <label class="ui-label"><span class="ui-form-required"></span>邮箱地址</label>
	                    <label class="ui-label-p">
	                   		<input class="ui-input code" type="text" id="mail" name="userInfoVo.mail" value="${userInfoVo.mail}" onblur="chkmail()"  autocomplete="off"  maxlength="30"/>
	            		</label>
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