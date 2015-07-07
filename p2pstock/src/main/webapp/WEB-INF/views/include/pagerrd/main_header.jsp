<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="tag" uri="/tags/mytags" %> 
<!--header begin-->
<div class="ui-header" id="header">
  <div class="ui-header-top ">
    <div class="container_12 fn-clear">
      <div class="grid_12 fn-clear">
        <div class="fn-left">
          
        </div>
        <div class="fn-right">
        	<s:if test='%{#session.username != null && #session.username!=""}'>
	        	您好，<s:property value="#session.username"/>
	        	<a class="ui-nav-item" href="/p2pstock/login/logout_loginAction.action" style="padding:0px 10px 0px 0px;" >[退出]</a>
        	</s:if>
        	<s:else>
            	<a rclass="ui-nav-item reg-link" href="/p2pstock/regist/registInit_registAction.action">快速注册</a>
            	<a class="ui-nav-item login-link" href="/p2pstock/login/loginInit_loginAction.action">立即登录</a>       	
        	</s:else>
          <a class="ui-nav-item" href="#" target="_blank">帮助</a>
          
        </div>
      </div>
    </div>
  </div>
   <div class="ui-header-main">
    <div class="container_12 fn-clear">
      <div class="ui-header-logo-grid fn-left">
         <a class="ui-header-slogan" href="/p2pstock/init/index.action">
         	<img src="/p2pstock/static/images/rrd/mylogo.bmp" style="width:200px;height:60px"/>
         </a>
      </div>
      <div class="ui-header-slogan-grid fn-left ml20">
        <a class="ui-header-slogan" href="#"></a>
      </div>
      <div class="ui-header-grid fn-right">
        <div class="fn-hide no-nav-text"></div>
        <tag:menu menuorder="1"></tag:menu>
      </div>
    </div>
  </div>
</div>
<!--header end-->