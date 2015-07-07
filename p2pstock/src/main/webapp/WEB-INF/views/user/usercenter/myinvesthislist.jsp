<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %> 
<table>
	<tr>
		<td class="tbl_td_20 tbl_tdtitleh">结算日期</td>
		<td class="tbl_td_20 tbl_tdtitleh">产品名称</td>
		<td class="tbl_td_20 tbl_tdtitleh">产品发起人</td>
		<td class="tbl_td_20 tbl_tdtitleh">结算前收益</td>
		<td class="tbl_td_20 tbl_tdtitleh">结算后收益</td>
	</tr>
	<s:iterator value="settlelist" status="s">
		<tr>
			<td class="tbl_td_20"><s:property value="update_date"/></td>
			<td class="tbl_td_20"><s:property value="invt_product_name"/></td>
			<td class="tbl_td_20"><s:property value="create_username"/></td>
			<td class="tbl_td_20"><s:property value="user_invest_profitpay"/></td>
			<td class="tbl_td_20"><s:property value="user_invest_profit"/></td>
		</tr>
	</s:iterator>
	<tr>
		<td colspan="7">
			<!-- 分页标签 -->
			<tag:pages page="${page}" formid="myinvestform" subtype="ajax"></tag:pages>
		</td>
	</tr>
</table>