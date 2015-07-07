<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %> 
<table>
	<tr>
		<td class="tbl_td_20 tbl_tdtitleh">投资日期</td>
		<td class="tbl_td_20 tbl_tdtitleh">产品名称</td>
		<td class="tbl_td_20 tbl_tdtitleh">产品发起人</td>
		<td class="tbl_td_20 tbl_tdtitleh">投资金额</td>
		<td class="tbl_td_20 tbl_tdtitleh">投资状态</td>
	</tr>
	<s:iterator value="investlist" status="s">
		<tr>
			<td class="tbl_td_20"><s:property value="update_date"/></td>
			<td class="tbl_td_20"><s:property value="invest_prdouct_name"/></td>
			<td class="tbl_td_20"><s:property value="user_name"/></td>
			<td class="tbl_td_20"><s:property value="acount_prtfeeall"/></td>
			<td class="tbl_td_20"><s:property value="invest_statusname"/></td>
		</tr>
	</s:iterator>
	<tr>
		<td colspan="7">
			<!-- 分页标签 -->
			<tag:pages page="${page}" formid="myinvestform" subtype="ajax"></tag:pages>
		</td>
	</tr>
</table>