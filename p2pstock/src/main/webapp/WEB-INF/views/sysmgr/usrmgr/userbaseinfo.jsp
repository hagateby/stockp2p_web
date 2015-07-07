<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<%@ taglib prefix="tag" uri="/tags/mytags" %>
<table>
	<tr><td height="20px"></td></tr>
 	<tr>
 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">手机号码</td>
 		<td class="tbl_td_15_b tbl_td_width">
 			<s:property  value="userInfoVo.phone"/>
 		</td>
 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">用户姓名</td>
 		<td class="tbl_td_15_b tbl_td_width">
 			<s:property value="userInfoVo.user_name"/>
 		</td>
 	</tr>
  	<tr>
 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">证件类型</td>
 		<td class="tbl_td_15_b tbl_td_width">
 			身份证
 		</td>
 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">证件号码</td>
 		<td class="tbl_td_15_b tbl_td_width">
 			<s:property value="userInfoVo.certif_no"/>
 		</td>
 	</tr>
 	<tr>
 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">用户类型</td>
 		<td class="tbl_td_15_b tbl_td_width">
 			<s:property value="userInfoVo.user_typename"/>
 		</td>
 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">用户状态</td>
 		<td class="tbl_td_15_b tbl_td_width">
 			<s:property value="userInfoVo.user_statusname"/>
 		</td>
 	</tr>
 	<tr>
 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">用户昵称</td>
 		<td class="tbl_td_15_b tbl_td_width">
 			<s:property value="userInfoVo.user_nickname"/>
 		</td>				 	
 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">用户邮箱</td>
 		<td class="tbl_td_15_b tbl_td_width">
 			<s:property value="userInfoVo.mail"/>
 		</td>
 	</tr>
 	<tr>
 		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">推荐人手机号码</td>
 		<td class="tbl_td_15_b tbl_td_width">
 			<s:property value="userInfoVo.recod_phone"/>
 		</td>
  		<td class="tbl_td_15_b tbl_td_width tbl_td_lbcolor">账户余额</td>
 		<td class="tbl_td_15_b tbl_td_width">
 			<s:property value="userInfoVo.acc_balance"/>元
 		</td>
 	</tr>
</table>