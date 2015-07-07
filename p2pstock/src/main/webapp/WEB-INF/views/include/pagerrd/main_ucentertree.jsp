<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<!--lefttreee begin-->
				<!-- 我的投资 -->
				<form method="post" id="myinvestForm" action="/p2pstock/usercenter/queryInvest_investMgrAction.action?menusec=ainvestlist.investinfo">
					<input type="hidden" id="user_id" name="investMgrVo.user_id" value="${session.userid}"/>
				</form>
				<!-- 我的交易 -->
				<form method="post" id="mytranForm" action="/p2pstock/fundmgr/queryTranInfo_fundMgrAction.action?menusec=ainvestlist.traninfo">
					<input type="hidden" id="user_id" name="tranInfoVo.user_id" value="${session.userid}"/>
				</form>
				
		   		 <!--树形菜单层 -->
		    	<div class="grid_2">
		    		<div class="ui-side ui-side-withicon" id="sidebar">
		    			<s:hidden id="menusec" value="%{#session.menusec}"> </s:hidden>
		    			<ul class="ui-side-list nav nav-list bs-docs-sidenav affix">
		    			    <li class="ui-side-item active">
		      					<a class="ui-side-item-link" href="/p2pstock/usercenter/userInit_userCenterAction.action" style="cursor:pointer"><i class="fn-left ui-icon-mid index"></i>我的账户</a>
		    				</li>
						   <li class="ui-side-item ">
						      <a class="ui-side-item-link" id="ainvest"><i class="fn-left ui-icon-mid invest"></i>投资管理</a>
						      <ul class="ui-side-sub-list" id="ainvestlist">
						        <li class="ui-side-sub-item ">
						          <a class="ui-side-sub-item-link" onclick="myinvest();" id="investinfo">投资查询</a>
						        </li>
						        <li class="ui-side-sub-item ">
						          <a class="ui-side-sub-item-link" onclick="mytran();" id="traninfo">交易查询</a>
						        </li>
						      </ul>
						    </li>
						   <li class="ui-side-item ">
						      <a class="ui-side-item-link" id="aaccount"><i class="fn-left ui-icon-mid account"></i>账户管理</a>
						      <ul class="ui-side-sub-list" id="aaccountlist">
						        <li class="ui-side-sub-item" >
						          <a class="ui-side-sub-item-link" href="/p2pstock/usercenter/userEdit_userCenterAction.action?menusec=aaccountlist.baseinfo" id="baseinfo">基本信息维护</a>
						        </li>
						        <li class="ui-side-sub-item" >
						          <a class="ui-side-sub-item-link" href="/p2pstock/usercenter/bankEdit_userCenterAction.action?menusec=aaccountlist.bankinfo" id="bankinfo">银行信息维护</a>
						        </li>
						        <li class="ui-side-sub-item " >
						          <a class="ui-side-sub-item-link last" href="/p2pstock/usercenter/loginPwdEdit_userCenterAction.action?menusec=aaccountlist.loginpwd" id="loginpwd">登录密码修改</a>
						        </li>
						       	<li class="ui-side-sub-item " >
						          <a class="ui-side-sub-item-link last" href="/p2pstock/usercenter/tranPwdEdit_userCenterAction.action?menusec=aaccountlist.tranpwd" id="tranpwd">支付密码修改</a>
						        </li>
						      </ul>
						    </li>
		    			</ul>
		    		</div>
		    	</div>
<!--lefttreee end-->