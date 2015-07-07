<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<fieldset style="border:0px">
	<legend class="fieldset-lgend">最新投资</legend>
	<br/>
	<table style="width:500px">
		<tr>
			<td class="tbl_td_lbcolor tbl_td_20" >投资日期</td>
			<td class="tbl_td_lbcolor tbl_td_20" >投资人</td>
			<td class="tbl_td_lbcolor tbl_td_20" >投资产品</td>
			<td class="tbl_td_lbcolor tbl_td_20" >投资额</td>
		</tr>
		<s:iterator value="investlist" status="s">
		<tr>
			<td class="tbl_td_20"><s:property value="create_date"/></td>
			<td class="tbl_td_20"><s:property value="user_name"/></td>
			<td class="tbl_td_20"><s:property value="invest_prdouct_name"/></td>
			<td class="tbl_td_20"><s:property value="acount_prtfeeall"/>元</td>
		</tr>
		</s:iterator>
	</table>
</fieldset>