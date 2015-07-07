<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/usercentercommon.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/usercentermain.js"></script>
<title>用户中心</title>
</head>
<body>
	

	<div class="grid_10">
		<s:form action="/ajax/queryMyInvest_userCenterAction.action" method="post" theme="simple" id="myinvestform">
			<input type="hidden" name="investMgrVo.invest_status" value="2"/>
		</s:form>
		<s:form action="/ajax/queryMyInvestHis_userCenterAction.action" method="post" theme="simple" id="myinvesthisform">
		</s:form>
		<div class="ui-poptip ui-poptip-orange fn-hide" id="tipCon_3">
	    	<div class="">
	        	<div class="ui-poptip-container">
	            	<div class="ui-poptip-arrow ui-poptip-arrow-7">
		                <em></em>
		                <span></span>
	              	</div>
	            	<div class="ui-poptip-content" data-role="content">
	             	</div>
	         	</div>
	    	</div>
	  	</div>
	  	<div class="img-section pr20">
	 	</div>
	    <div class="box box-user-info">
	    <div class="user-avatar-container">
	    	<a href="#"><img width="96" height="96" src="/p2pstock/static/images/rrd/ucenter-man.png" /></a>
	    </div>
	    <div class="user-info-container">
	    	<h3 title="我的小浩浩"><s:property value="#session.username"/></h3>
	        <div class="fn-clear">
	        	<div class="fn-left user-security-container mr10" id="info-box">
	    			<div class="safe fn-clear">
	                	<span class="fn-left color-gray-text">安全等级</span>
	                <div id="safe-progressbar" class="safe-progressbar fn-left">
	                	<div class="bar"></div>
	                </div>
	             </div>
	             <div class="icons fn-clear mt5">
	             </div>
	           </div>
	           <div class="fn-left last">
	             <div class="surplus fn-clear">
	               <span class="fn-left text-l mr10">账户余额</span>
	               <span class="fn-left num-xl color-orange-text">
	                 <em>${accountInfoVo.acc_balance}</em>元
	               </span>
	               <a class="fn-left ui-button ui-button-green ui-button-mid mr4" href="#">充值</a>
	               <a class="fn-left ui-button ui-button-blue ui-button-mid last" href="#">提现</a>
	             </div>
	             <div class="surplus-detail fn-clear">
	               <div class="fn-left mr40">
	                 <span>冻结金额</span>
	                 <span >
	                   <em>${accountInfoVo.acc_freeze}</em>元
	                 </span>
	            	  <span style="padding-left:60px">可用金额</span>
	                 <span>
	                   <em>${accountInfoVo.acc_enchash}</em>元
	                 </span>
	               </div>
	             </div>
	           </div>
	       	   <div>
		         	<div>
		         		<span style="padding-left:175px">累计收益</span>
		         		<span >
		                   <em>${accountInfoVo.user_profit}</em>元
		                 </span>
		                 <span style="padding-left:60px">投资收益率</span>
		                 <span>
		                   <em>${accountInfoVo.user_profitrate}</em>%
		                 </span>	
		         	</div>
	           </div>
	         </div>
	       </div>
	       <div style="width:700px;padding-top:70px;padding-left:50px;">
	           	<fieldset style="border:0px">
	           		<legend class="fieldset-lgend">我的投资</legend>
	           		<div id="myinvestdiv">
	           		</div>
	           	</fieldset>
	        </div>
	       <div style="width:700px;padding-top:70px;padding-left:50px;">
	           	<fieldset style="border:0px">
	           		<legend class="fieldset-lgend">我的历史投资</legend>
	           		<div id="myinvesthisdiv">
	           		</div>
	           	</fieldset>
	        </div>
		</div>
	</div>
</body>
</html>