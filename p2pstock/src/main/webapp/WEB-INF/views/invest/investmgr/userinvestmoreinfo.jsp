<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/invest/investmgr/userinvestmoreinfo.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>

<title>投资详情</title>
</head>
<body>

<div class="grid_10">
	<div class="p20bs color-white-bg fn-clear" id="maindiv">
		<s:form action="/sysmgr/queryInvest_investMgrAction.action" method="post" theme="simple" id="cancleform">
		</s:form>
		<div class="fn-clear head">
	     	<div class="title fn-left">投资详细信息</div>
	     	<div class="fn-right">
	     		<a id="cancleInfoBtn" class="ui-button ui-button-mid ui-button-green" href="javascript:history.go(-1)">取消</a>
	     	</div>
	  	</div>
	  	<tag:flowstep flowstep="${investvo.invest_status}" flowtyp="typ_invest" ></tag:flowstep>


	  	
		<div id="binddiv" style="display:block">
			<br/><br/><br/>
			<fieldset style="border:0px">
				<div id="baseprtdiv">
					<table>
						<tr>
							<td class="tbl_td_20 tbl_tdtitle140" style="width:160px">投资日期</td>
							<td class="tbl_td_20 tbl_tdtitle140" style="width:100px">股票代码</td>
							<td class="tbl_td_20 tbl_tdtitle140" style="width:100px">起始签号</td>
							<td class="tbl_td_20 tbl_tdtitle140">购买签数</td>
							<td class="tbl_td_20 tbl_tdtitle140">投资额</td>
							<td class="tbl_td_20 tbl_tdtitle140">中签股数</td>
							<td class="tbl_td_20 tbl_tdtitle140">投资状态</td>
						</tr>
						<s:iterator value="investlist" status="s">
							<tr>
								<td class="tbl_td_20">
								<a href="javascript:detalinfo('${user_name}','${update_date}','${stock_code}','${start_no}','${sub_code}','${acount_prtfeeall}','${praise_count}','${acount_bailall}','${acount_chargeall}','${user_settle_profit}','${invest_statusname}');"><s:property value="update_date"/></a>
								</td>
								<td class="tbl_td_20"><s:property value="stock_code"/></td>
								<td class="tbl_td_20"><s:property value="start_no"/></td>
								<td class="tbl_td_20"><s:property value="sub_code"/></td>
								<td class="tbl_td_20"><s:property value="acount_prtfeeall"/>元</td>
								<td class="tbl_td_20"><s:property value="praise_count"/>股</td>
								<td class="tbl_td_20"><s:property value="invest_statusname"/></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</fieldset>
		</div>
	</div>
	<div id="detaildiv" style="display:none">
		<div class="p20bs color-white-bg fn-clear">
			<div class="fn-clear head">
		     	<div class="title fn-left">明细信息</div>
		     	<div class="fn-right">
		     		<a id="cancleInfoBtn" class="ui-button ui-button-mid ui-button-green" onclick="closedetail()">关闭</a>
		     	</div>
	  		</div>
	  		<fieldset style="border:0px">
			    <table>
			    	<tr>
				 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">投资人:</td>
				 		<td class="tbl_td_15_b tbl_td_width">
				 			<label id="dtuser_name"></label>
				 		</td>			    		
				 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">投资日期:</td>
				 		<td class="tbl_td_15_b tbl_td_width">
				 			<label id="dtupdate_date"></label>
				 		</td>
				 	</tr>
				 	<tr>
				 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">股票代码:</td>
				 		<td class="tbl_td_15_b tbl_td_width">
				 			<label id="dtstock_code"></label>
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">起始签号:</td>
				 		<td class="tbl_td_15_b tbl_td_width">
				 			<label id="dtstart_no"></label>
				 		</td>
			    	</tr>
			    	<tr>
				 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">购买签数:</td>
				 		<td class="tbl_td_15_b tbl_td_width">
				 			<label id="dtsub_code"></label>
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">投资额:</td>
				 		<td class="tbl_td_15_b tbl_td_width">
				 			<label id="dtacount_prtfeeall"></label>元
				 		</td>
			    	</tr>
			    	<tr>
				 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">中签股数:</td>
				 		<td class="tbl_td_15_b tbl_td_width">
				 			<label id="dtpraise_count"></label>股
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">保证金:</td>
				 		<td class="tbl_td_15_b tbl_td_width">
				 			<label id="dtacount_bailall"></label>元
				 		</td>
			    	</tr>
			    	<tr>
				 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">管理费:</td>
				 		<td class="tbl_td_15_b tbl_td_width">
				 			<label id="dtacount_chargeall"></label>元
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">结算利润:</td>
				 		<td class="tbl_td_15_b tbl_td_width">
				 			<label id="dtuser_settle_profit"></label>元
				 		</td>
			    	</tr>
			    	<tr>
				 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">投资状态:</td>
				 		<td class="tbl_td_15_b tbl_td_width">
				 			<label id="dtinvest_statusname"></label>
				 		</td>
				 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor"></td>
				 		<td class="tbl_td_15_b tbl_td_width">
				 			
				 		</td>
			    	</tr>
			    </table>
	   		</fieldset>
		</div>
	</div>
</div>
</body>
</html>