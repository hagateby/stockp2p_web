<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/invest/investmgr/investinfo.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>发行产品管理</title>
</head>
<body>
<!--nav begin-->
<div class="container_12 mt10">
  <ul class="ui-breadcrumb grid_12 fn-clear" id="navul">
    <li class="fn-left ui-breadcrumb-item ui-breadcrumb-text ui-breadcrumb-text-first">
      <a href="/p2pstock/init/index.action" id="mainlb">首页</a>
    </li>
    <li class="fn-left ui-breadcrumb-item ui-breadcrumb-separator"></li>
    <li class="fn-left ui-breadcrumb-item ui-breadcrumb-text ui-breadcrumb-text-current">
      <a href="#" id="sublb">我要投资</a>
    </li>
  </ul>
</div>
<!--nav end-->
 <div class="container_12">
	<div style="padding-left:300px;height:30px;color:red;">
		<label id="lerror" class="errmsg"><tag:msg msgcode="${msgCode}"></tag:msg></label>
	</div>
	<s:form action="/invest/queryInvestInfo_investMgrAction.action" method="post" theme="simple" id="cancleform">
	</s:form>
	<s:form action="/invest/invest_investMgrAction.action" method="post" theme="simple" id="investform">
	<input type="hidden" id="invt_product_id" name="investMgrVo.invt_product_id" value="${investProductVo.invt_product_id}"/>
	<div class="fn-clear head">
     	<div class="fn-right">
     		<a id="cancleInfoBtn" class="ui-button ui-button-green" onclick="cancleinfo()">取消</a>
     	</div>
  	</div>
  	
	<div class="p20bs color-white-bg fn-clear" style="width:925px">
	<fieldset style="border:0px">
		<legend class="fieldset-lgend">产品定义</legend>
		<br/>
		<table>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">投资产品名称</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property  value="investProductVo.invt_product_name"/>
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">投资产品发起人</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="investProductVo.user_name"/>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">产品已募集总金额</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="investProductVo.amount"/>
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">发起投资管理费</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="investProductVo.issue_fee"/>元
		 		</td>
		 	</tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">保证金</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="investProductVo.invester_bail"/>%
		 		</td>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">管理费</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:if test="investProductVo.user_accruedcharges_type == 0">
						<s:property value="investProductVo.user_accruedcharges_amount"/>%
					</s:if>
					<s:else>
						<s:property value="investProductVo.user_accruedcharges_amount"/>元
					</s:else>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">开始时间</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="investProductVo.start_date"/>
		 		</td>				 	
		 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">终止时间</td>
		 		<td class="tbl_td_15_b tbl_td_width">
		 			<s:property value="investProductVo.end_date"/>
		 		</td>
		 	</tr>
		 	<tr height="20px"><td colspan="6" align="center"><br></td></tr>
		</table>
	</fieldset>
	<div id="binddiv" style="display:block">
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">已绑定基础标的</legend>
			<input type="hidden" id="basic_product_idarray" name="investMgrVo.basic_product_idarray"/>
			<input type="hidden" id="start_noarray" name="investMgrVo.start_noarray"/>
			<input type="hidden" id="pricearray" name="investMgrVo.pricearray"/>
			<input type="hidden" id="sub_codearray" name="investMgrVo.sub_codearray"/>
			<input type="hidden" id="invester_bailarray" name="investMgrVo.invester_bailarray"/>
			<input type="hidden" id="user_accruedcharges_typearray" name="investMgrVo.user_accruedcharges_typearray"/>
			<input type="hidden" id="user_accruedcharges_amountarray" name="investMgrVo.user_accruedcharges_amountarray"/>
			<input type="hidden" id="feecntarray"/>
			<input type="hidden" id="okindexarray"/>
			<br/>
			<div id="baseprtdiv">
				<table id="sectbl">
						<tr>
							<td class="tbl_td_20 tbl_tdtitle160">股票市场</td>
							<td class="tbl_td_20 tbl_tdtitle160">标的名称</td>
							<td class="tbl_td_20 tbl_tdtitlecolor" style="width:100px">股票代码</td>
							<td class="tbl_td_20 tbl_tdtitle140">股票单价</td>
							<td class="tbl_td_20 tbl_tdtitlecolor" style="width:160px">募集结束时间</td>
							<td class="tbl_td_20 tbl_tdtitle140">起始号码</td>
							<td class="tbl_td_20 tbl_tdtitle140">购买签数</td>
							<td class="tbl_td_20 tbl_tdtitle140">合计额度</td>
							<td class="tbl_td_20 tbl_tdtitlecolor" style="width:100px">操作</td>
						</tr>
						<s:iterator value="lmvolist" status="s">
						<input type="hidden" id="user_accruedcharges_amount" name="user_accruedcharges_amount"/>
						<tr>
							<td class="tbl_td_20" >
								<input type="hidden">
								<a onclick="del(this)" href="#" ><img src="/p2pstock/static/images/rrd/del3.gif"/></a>
								<s:property value="sales_cityname"/>
							</td>
							<td class="tbl_td_20"><s:property value="basic_product_name"/></td>
							<td class="tbl_td_20"><s:property value="invest_product_id"/></td>
							<td class="tbl_td_20"><s:property value="price"/>元</td>
							<td class="tbl_td_20"><s:property value="end_date"/></td>
							<td class="tbl_td_20"><input type="text" maxlength="18" id="start_no" name="start_no" style="width:150px" onblur="value=value.replace(/[^\d]/g,'');startnoBlur(this,'${price}','${sales_city}')"/></td>
							<td class="tbl_td_20"><input type="text" maxlength="8" id="sub_code" name="sub_code" style="width:80px" onblur="value=value.replace(/[^\d]/g,'');subcodeBlur(this,'${price}','${sales_city}')"/></td>
							<td class="tbl_td_20"></td>
							<td class="tbl_td_20" id="tt"><a class="ui-button ui-button-green" onclick="ok(this,'${price}','${invester_bail}','${user_accruedcharges_amount}','${user_accruedcharges_type}','${basic_product_id}','${sales_city}','${up_limit}','${end_date}')">确定</a></td>
						</tr>
						<s:if test="%{#s.last == true}">
							<s:hidden id="prosize" value="%{#s.count}"></s:hidden>
						</s:if>
					</s:iterator>
					<tr><td><br></td></tr>
					<tr>
						<td>
							<div class="addbtn_001" id="addbtn"></div>
							<div id="productsecdiv" class="sec_new">
								<s:iterator value="lmvolist" status="s">
									<label class="seclbl_new" onclick="addproduct('${sales_cityname}','${basic_product_name}','${invest_product_id}','${price}','${invester_bail}','${user_accruedcharges_type}','${user_accruedcharges_amount}','${basic_product_id}','${sales_city}','${up_limit}','${end_date}')"><s:property value="basic_product_name"/></label><br/>
								</s:iterator>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</fieldset>
	</div>
	<br/>
	<div id="acountdiv" style="display:block">
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">缴费额度</legend>
			<br/>
			<div id="binddiv">
				<table id="acounttbl">
					<tr>
					<td style="font-size:20px">
						<label>产品总保证金:</label>
						<label id="acount_bail" style="color:red;">0</label>
						<label>元+</label>
						<label>产品总金额:</label>
						<label id="acount_prtfee" style="color:red;">0</label>
						<label>元=</label> 
						<label>缴费总金额:</label>
						<label id="acount_all" style="color:red;">0</label> 
						<label>元</label>
					</td>
					</tr>
				</table>
			</div>
		</fieldset>
	</div>
	<br/>
	<div style="padding-left:350px">
		<input type="button" id="investBtn"  onclick="invest();" class="ui-button-rrd-blue ui-button-rrd-blue-large" value="确认购买"/>
	</div>
	</s:form>
</div>
</div>
</body>
</html>