<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<table>
		<tr>
			<td class="tbl_td_20 tbl_tdtitle160">产品名称</td>
			<td class="tbl_td_20 tbl_tdtitle140">所属市场</td>
			<td class="tbl_td_20 tbl_tdtitle140">股票代码</td>
			<td class="tbl_td_20 tbl_tdtitle140">开始日期</td>
			<td class="tbl_td_20 tbl_tdtitle140">结束日期</td>
			<td class="tbl_td_20 tbl_tdtitle140">开签日期</td>
			<td class="tbl_td_20 tbl_tdtitle160">操作</td>
		</tr>
		<s:iterator value="productList" status="s">
		<tr>
			<td class="tbl_td_20"><s:property value="basic_product_name"/></td>
			<td class="tbl_td_20"><s:property value="sales_cityname"/></td>
			<td class="tbl_td_20"><s:property value="invest_product_id"/></td>
			<td class="tbl_td_20"><s:property value="start_date"/></td>
			<td class="tbl_td_20"><s:property value="end_date"/></td>
			<td class="tbl_td_20"><s:property value="lot_date"/></td>
			<td class="tbl_td_20"><a href="javascript:moreinfo('${basic_product_id}');">详情</a> <a href="javascript:addinfo('${basic_product_id}','${basic_product_name}','${invest_product_id}','${basic_product_typename}','${launcher_accruedcharges_type}','${launcher_accruedcharges_amount}','${start_date}','${end_date}');">添加</a></td>		
		</tr>
	</s:iterator>
	<tr>
		<td colspan="7">
			<!-- 分页标签 -->
			<tag:pages page="${page}" formid="bindform" subtype="ajax"></tag:pages>
		</td>
	</tr>
</table>