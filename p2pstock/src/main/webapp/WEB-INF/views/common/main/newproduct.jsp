<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<fieldset style="border:0px">
	<legend class="fieldset-lgend">最新产品</legend>
	<br/>
	<table style="width:500px">
		<tr>
			<td class="tbl_td_lbcolor tbl_td_20" >产品名称</td>
			<td class="tbl_td_lbcolor tbl_td_20" >发行日期</td>
			<td class="tbl_td_lbcolor tbl_td_20" >开始日期</td>
			<td class="tbl_td_lbcolor tbl_td_20" >结束日期</td>
			<td class="tbl_td_lbcolor tbl_td_20" >产品类型</td>
		</tr>
		<s:iterator value="productlist" status="s">
		<tr>
			<td class="tbl_td_20"><s:property value="invt_product_name"/></td>
			<td class="tbl_td_20"><s:property value="create_date"/></td>
			<td class="tbl_td_20"><s:property value="start_date"/></td>
			<td class="tbl_td_20"><s:property value="end_date"/></td>
			<td class="tbl_td_20">新股申购</td>
		</tr>
		</s:iterator>
	</table>
</fieldset>