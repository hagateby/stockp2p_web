<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<%@ taglib prefix="tag" uri="/tags/mytags" %>
<table>
	<tr>
		<td class="tbl_td_20 tbl_tdtitle140">银行卡号</td>
		<td class="tbl_td_20 tbl_tdtitlecolor"  style="width:100px">所属银行</td>
		<td class="tbl_td_20 tbl_tdtitlecolor"  style="width:180px">开户行网点</td>
		<td class="tbl_td_20 tbl_tdtitlecolor"  style="width:180px">是否默认</td>
		<td class="tbl_td_20 tbl_tdtitle140">更新日期</td>
	</tr>
	<s:iterator value="userBankList" status="s">
		<tr>
			<td class="tbl_td_20"><s:property value="bank_acc_code"/></td>
			<td class="tbl_td_20"><s:property value="bank_name"/></td>
			<td class="tbl_td_20"><s:property value="bank_branch_name"/></td>
			<td class="tbl_td_20">
			<s:if test="default_flag == 0">
				否
			</s:if>
			<s:else>
				是
			</s:else>
			<td class="tbl_td_20"><s:property value="update_date"/></td>	
		</tr>
	</s:iterator>
</table>