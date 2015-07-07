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
	<div style="padding-left:200px;color:red;">
		<label id="lerror" class="errmsg">
			<tag:msg msgcode="${msgCode}"></tag:msg>
		</label>
	</div>
	<div class="p20bs color-white-bg fn-clear">
		 
	     <div id="disdiv" style="display:block">
	     	<div class="fn-clear head">
		     	<div class="title fn-left">银行卡信息</div>
		     	<div class="fn-right">
		     		<a id="addBankBtn" class="ui-button ui-button-mid ui-button-green">添加银行卡</a>
		     	</div>
	   		</div>
	   		<form data-name="userbasic" enctype="multipart/form-data" class="ui-form" method="post" id="bankSetForm" action="/p2pstock/usercenter/setDefaultBank_userCenterAction.action">
	   			<s:hidden id="setbankaccid" name="userbankvo.user_bank_acc_id"></s:hidden>
	   		</form>
			<form data-name="userbasic" enctype="multipart/form-data" class="ui-form" method="post" id="bankDelForm" action="/p2pstock/usercenter/bankDelInfo_userCenterAction.action">
	     	 	<div class="inputs">
	     	 		<s:hidden id="delbankaccid" name="userbankvo.user_bank_acc_id"></s:hidden>
	     	 		<s:iterator value="userBankList" status="s">
	    	 		<div style="height:50px;vertical-align:middle;border:1px solid #A4D3EE">
	    	 			<label><img  src="/p2pstock/static/images/rrd/${bank_code}.jpg"/></label>
		                <label><span class="ui-form-required"></span><s:property value="bank_branch_name"/></label>
		                <label><span class="ui-form-required"></span><s:property value="bank_acc_code"/></label>
		                <label><span class="ui-form-required"></span><a href="javascript:editinfo('${bank_code}','${bank_acc_code}','${bank_branch_name}','${bank_name}','${user_bank_acc_id}');" class="link mod openLink">修改</a></label>
		                <label><span class="ui-form-required"></span><a href="javascript:delinfo('${user_bank_acc_id}');" id="delbtn"class="link del">删除</a></label>
		                <label>
		               	 	<span class="ui-form-required" ></span>
		               	 	<s:if test="default_flag == 0">
		               	 		<a href="javascript:setinfo('${user_bank_acc_id}');" id="setbtn"class="link del" >设为默认</a>
		               	 	</s:if>
		                	<s:else>
		                		<label style="color:red;font-size:20px;padding-left:80px">默认银行卡</label>
		                	</s:else>
		                	
		                </label>
	                </div>
	                </s:iterator>
	     	 	</div>
	    	</form>
    	</div>
	    <div id="adddiv"  class="card-dialog noBorder fn-hide" >
		    <form data-name="addcard"  class="ui-form" method="post" id="bankAddForm" action="/p2pstock/usercenter/bankAdd_userCenterAction.action">
		      <h4 class="text-center">添加银行卡</h4>
		      <div class="addCardInputs">
		        <div class="inputs">
		          <div class="ui-form-item lh-normal">
		            <label class="ui-label"><span class="ui-form-required">*</span>开户人姓名</label>
		            <label class="ui-label-p">
		            	<s:property value="userInfoVo.user_name"/>
		            </label>
		          </div>
		          <div class="ui-form-item">
		            <label class="ui-label">
		            	<span class="ui-form-required">*</span>银行卡号
					</label>
		          	<label class="ui-label-p">
		            	<input class="ui-input" style="width:270px;height:30px" type="text" onblur="value=value.replace(/[^\d]/g,'')"  name="userbankvo.bank_acc_code" id="acccode" maxlength="19" data-is="isBankCard" autocomplete="off" />
		            </label>
		          </div>
		          <div class="ui-form-item" style="*z-index:21;">
		            <label class="ui-label">
		            	<span class="ui-form-required">*</span>选择银行
		            </label>
		            <s:hidden id="bank_name" name="secbankname"></s:hidden>
		            <label class="ui-label-p">
			            <div class="ui-selectdrop  lh-normal">
			              <div class="s_select_bg wid280 het35 clearfix" id="secBankBtn">
			                	<span class="txt fn-clear"><em id="secdis">请选择银行</em></span>
			                	<span class="arrow"><em></em></span>
			              </div>
			              <div class="popBankBox J_popBankBox" id="bankdiv" style="display:none">
			                <ul class="has-icon-list fn-clear">
			                	<s:iterator value="banklist" status="s">
				                    <li datavalue="4" datacode="105">
				                      <em><label onclick="secBank(this);"><s:property value="para_name"/></label></em>
				                    </li>
			                    </s:iterator>
			                </ul>
			              </div>
			            </div>
		            </label>
		          </div>
		          <div class="ui-form-item" style="*z-index:20;">
		            <label class="ui-label">
		            	<span class="ui-form-required">*</span>开户行名称
		            </label>
		            <label class="ui-label-p">
		            	<input class="ui-input" style="width:270px;height:30px" type="text" name="userbankvo.bank_branch_name" id="brachname" maxlength="10" autocomplete="off" disableautocomplete/>
		          	</label>
		          </div>
		          <div class="ui-form-item wgetMobleCode fn-clear lh-normal">
		            <label class="ui-label lh40"><span class="ui-form-required">*</span>手机验证码</label>
		            <label class="ui-label-p">
			        	<input class="ui-input mt1 fn-left" style="width:150px;height:30px" type="text" name="userbankvo.cptno" id="cpt"/>
			        </label>
			        <label class="ui-label-p">
			            <input type="button" class="ui-button-rrd-bluecpt ui-button-rrd-blue-long"  id="cptBtn" value="免费获取验证码" ></input>
			        </label>
		          </div>
		          <div id="serverMsg" class="serverMsg" style="padding-left:0px; line-height: 22px; margin-bottom: 10px" ></div>
		        </div>
		        <div style="padding-left:170px">
			        <input type="button" class="block-btn" id="addSubBtn"value="添 加" />
			        <input type="button" id="closeaddbtn" class="block-btn" value="取 消" />
		        </div>
		      </div>
		    </form>
		</div>
	    <div id="editdiv"  class="card-dialog noBorder fn-hide" >
		    <form data-name="addcard"  class="ui-form" method="post" id="bankEditForm" action="/p2pstock/usercenter/bankEditInfo_userCenterAction.action">
		      <h4 class="text-center">修改银行卡</h4>
		      <div class="addCardInputs">
		        <div class="inputs">
		          <div class="ui-form-item lh-normal">
		            <label class="ui-label"><span class="ui-form-required">*</span>开户人姓名</label>
		            <em>
		    		<s:property value="userInfoVo.user_name"/></em>
		          </div>
		          <div class="ui-form-item">
		            <label class="ui-label"><span class="ui-form-required">*</span>银行卡号</label>
		            <input class="ui-input" style="width:270px;height:30px" type="text" name="userbankvo.bank_acc_code" id="editacccode" onblur="value=value.replace(/[^\d]/g,'')" maxlength="19" data-is="isBankCard" autocomplete="off" />
		          </div>
		          <div class="ui-form-item" style="*z-index:21;">
		            <label class="ui-label"><span class="ui-form-required">*</span>选择银行</label>
		            <s:hidden id="bank_id" name="userbankvo.user_bank_acc_id"></s:hidden>
		            <s:hidden id="editbank_name" name="userbankvo.bank_name"></s:hidden>
		            <div class="ui-selectdrop  lh-normal">
		              <div class="s_select_bg wid280 het35 clearfix" id="editsecBankBtn">
		                <span class="txt fn-clear"><em id="editsecdis">请选择银行</em></span>
		                <span class="arrow">
		                  <em></em>
		                </span>
		              </div>
		              <div class="popBankBox J_popBankBox" id="editbankdiv" style="display:none">
		                <ul class="has-icon-list fn-clear">
		                	<s:iterator value="banklist" status="s">
			                    <li datavalue="4" datacode="105">
			                      <em><label onclick="secBankEdit(this);"><s:property value="para_name"/></label></em>
			                    </li>
		                    </s:iterator>
		                </ul>
		              </div>
		            </div>
		          </div>
		          <div class="ui-form-item" style="*z-index:20;">
		            <label class="ui-label"><span class="ui-form-required">*</span>开户行名称</label>
		            <input class="ui-input" style="width:270px;height:30px" type="text" name="userbankvo.bank_branch_name" maxlength="10" id="editbrachname" autocomplete="off" disableautocomplete/>
		          </div>
		          <div class="ui-form-item wgetMobleCode fn-clear lh-normal">
		            <label class="ui-label lh40"><span class="ui-form-required">*</span>手机验证码</label>
		            <input class="ui-input mt1 fn-left" style="width:270px;height:30px" type="text" name="userbankvo.cptno" id="editcpt"/>
		             <input type="button" class="ui-button-rrd-bluecpt ui-button-rrd-blue-long"  id="editcptBtn" value="免费获取验证码" ></input>
		          </div>
		        </div>
		        <div style="padding-left:170px">
			        <input type="button" class="block-btn" id="editSubBtn"value="保存" />
			        <input type="button" id="closeeditbtn" class="block-btn" value="取 消" />
		        </div>
		      </div>
		    </form>
		</div>
	</div>
</div>
</body>
</html>