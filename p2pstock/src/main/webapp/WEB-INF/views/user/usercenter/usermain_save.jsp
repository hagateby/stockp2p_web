<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/usercenter.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<title>用户中心</title>
</head>
<body>
	<div class="pg-container-content">
    	<div id="pg-account-index" class="pg-account">
		    <div class="container_12 mt10">
		      <ul class="ui-breadcrumb grid_12 fn-clear">
		        <li class="fn-left ui-breadcrumb-item ui-breadcrumb-text ui-breadcrumb-text-first">
		          <a href="/">首页</a>
		        </li>
		        <li class="fn-left ui-breadcrumb-item ui-breadcrumb-separator"></li>
		        <li class="fn-left ui-breadcrumb-item ui-breadcrumb-text ui-breadcrumb-text-current">
		          <a href="/account/index.action">我的账户</a>
		        </li>
		      </ul>
		    </div>
		    <div class="container_12 mt10 fn-clear">
		   		 <!--树形菜单层 -->
		    	<div class="grid_2">
		    		<div class="ui-side ui-side-withicon" id="sidebar">
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
						        <li class="ui-side-sub-item ">
						          <a class="ui-side-sub-item-link" href="/account/capital.action">基本信息维护</a>
						        </li>
						        <li class="ui-side-sub-item ">
						          <a class="ui-side-sub-item-link" href="/account/capital!recharge.action">银行信息维护</a>
						        </li>
						        <li class="ui-side-sub-item ">
						          <a class="ui-side-sub-item-link last" href="/account/capital!withdraw.action">登录密码修改</a>
						        </li>
						       	<li class="ui-side-sub-item ">
						          <a class="ui-side-sub-item-link last" href="/account/capital!withdraw.action">支付密码修改</a>
						        </li>
						      </ul>
						    </li>
		    			</ul>
		    			<div class="ui-side-img mt20">
							<a  style="display: block; width:140px;overflow:hidden;"><img style="width:140px;" src="/p2pstock/static/images/rrd/ucenter001.jpg" alt="账户页免费延期至6月30用图"></a>
						</div>
		    		</div>
		    	</div>
		 		<div class="grid_10">
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
		            <a href="/account/info!basicInfo.action"><img width="96" height="96" src="/p2pstock/static/images/rrd/ucenter-man.png" /></a>
		          </div>
		          <div class="user-info-container">
		            <h3 title="我的小浩浩">我的小浩浩</h3>
		            <div class="fn-clear">
		              <div class="fn-left user-security-container mr10" id="info-box">
		                <div class="safe fn-clear">
		                  <span class="fn-left color-gray-text">安全等级</span>
		                  <div id="safe-progressbar" class="safe-progressbar fn-left"><div class="bar"></div></div>
		                </div>
		                <div class="icons fn-clear mt5">
		                  <div class="fn-left icon-box light">
		                    <a data-txt="为了您的账户更安全，请您绑定手机号。||立即绑定" title="绑定手机，已绑定" href="/account/info!security.action" class="fn-left safe-rank cellphone light"></a>
		                  </div>
		                  <div class="fn-left icon-box ">
		                    <a data-txt="进行实名认证后，您才可以进行投资、借款。||立即认证" title="实名认证，未设置" href="/account/info!security.action" class="fn-left safe-rank man "></a>
		                  </div>
		                  <div class="fn-left icon-box ">
		                    <a data-txt="为了您的资金更加安全，请您设置交易密码。||立即设置" title="交易密码，未设置" href="/account/info!security.action" class="fn-left safe-rank lock "></a>
		                  </div>
		                  <div class="fn-left icon-box ">
		                    <a data-txt="为了使您的账户有更多的安全保障，请您绑定邮箱。||立即绑定" title="绑定邮箱，未绑定" href="/account/info!security.action" class="fn-left safe-rank mail "></a>
		                  </div>
		                </div>
		              </div>
		              <div class="fn-left last">
		                <div class="surplus fn-clear">
		                  <span class="fn-left text-l mr10">账户余额</span>
		                  <span class="fn-left num-xl color-orange-text">
		                    <em>0.00</em>元
		                  </span>
		                  <a class="fn-left ui-button ui-button-green ui-button-mid mr4" href="/account/capital!recharge.action">充值</a>
		                  <a class="fn-left ui-button ui-button-blue ui-button-mid last" href="/account/capital!withdraw.action">提现</a>
		                </div>
		                <div class="surplus-detail fn-clear">
		                  <div class="fn-left mr40">
		                    <span class="fn-left text mr10">冻结金额</span>
		                    <span class="fn-left num last">
		                      <em>0.00</em>元
		                    </span>
		                  </div>
		                  <div class="fn-left last">
		                    <span class="fn-left text mr10">可用金额</span>
		                    <span class="fn-left num last">
		                      <em>0.00</em>元
		                    </span>
		                  </div>
		                </div>
		              </div>
		            </div>
		          </div>
		        </div>
		    </div>
		    </div>
    	</div>
    </div>
</body>
</html>