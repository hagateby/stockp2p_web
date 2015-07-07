<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<!--lefttreee begin-->
		   		 <!--树形菜单层 -->
		    	<div class="grid_2">
		    		<div class="ui-side ui-side-withicon" id="sidebar">
		    			<s:hidden id="menusec" value="%{#session.menusec}"> </s:hidden>
		    			<ul class="ui-side-list nav nav-list bs-docs-sidenav affix">
		    			    <li class="ui-side-item active">
		      					<a class="ui-side-item-link" href="#"><i class="fn-left ui-icon-mid index"></i>我的账户</a>
		    				</li>
						   <li class="ui-side-item ">
						      <a class="ui-side-item-link" id="amoney"><i class="fn-left ui-icon-mid capital"></i>资金管理</a>
						      <ul class="ui-side-sub-list" id="amoneylist">
						        <li class="ui-side-sub-item ">
						          <a class="ui-side-sub-item-link" href="/account/capital.action">交易记录</a>
						        </li>
						        <li class="ui-side-sub-item ">
						          <a class="ui-side-sub-item-link" href="/account/capital!recharge.action">充值</a>
						        </li>
						        <li class="ui-side-sub-item ">
						          <a class="ui-side-sub-item-link last" href="/account/capital!withdraw.action">提现</a>
						        </li>
						      </ul>
						    </li>
						   <li class="ui-side-item ">
						      <a class="ui-side-item-link" id="ainvest"><i class="fn-left ui-icon-mid invest"></i>投资管理</a>
						      <ul class="ui-side-sub-list" id="ainvestlist">
						        <li class="ui-side-sub-item ">
						          <a class="ui-side-sub-item-link" href="/account/capital.action">交易记录</a>
						        </li>
						        <li class="ui-side-sub-item ">
						          <a class="ui-side-sub-item-link" href="/account/capital!recharge.action">充值</a>
						        </li>
						        <li class="ui-side-sub-item ">
						          <a class="ui-side-sub-item-link last" href="/account/capital!withdraw.action">提现</a>
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
		    			<div class="ui-side-img mt20">
							<a  style="display: block; width:140px;overflow:hidden;"><img style="width:140px;" src="/p2pstock/static/images/rrd/ucenter001.jpg" alt="账户页免费延期至6月30用图"></a>
						</div>
		    		</div>
		    	</div>
<!--lefttreee end-->