<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<table>
	<tr>
		<td class="tbl_td_20 tbl_tdtitle160">产品名称</td>
		<td class="tbl_td_20 tbl_tdtitle140">产品发起人</td>
		<td class="tbl_td_20 tbl_tdtitle140">开始日期</td>
		<td class="tbl_td_20 tbl_tdtitle140">结束日期</td>
		<td class="tbl_td_20 tbl_tdtitle140">产品状态</td>
		<td class="tbl_td_20 tbl_tdtitle160">操作</td>
	</tr>
	<s:iterator value="investlist" status="s">
		<tr>
			<td class="tbl_td_20"><a href="javascript:moreinfo('${invt_product_id}');"><s:property value="invt_product_name"/></a></td>
			<td class="tbl_td_20"><s:property value="user_name"/></td>
			<td class="tbl_td_20"><s:property value="investstart_date"/></td>
			<td class="tbl_td_20"><s:property value="investend_date"/></td>
			<td class="tbl_td_20"><s:property value="invt_product_statusname"/></td>
			<td class="tbl_td_20"><a href="javascript:queryinvestno('${invt_product_id}');">配号查询</a></td>		

		</tr>
	</s:iterator>
	<tr>
		<td colspan="7">
			<!-- 分页标签 -->
			<tag:pages page="${page}" formid="investform" subtype="ajax"></tag:pages>
		</td>
	</tr>
</table>