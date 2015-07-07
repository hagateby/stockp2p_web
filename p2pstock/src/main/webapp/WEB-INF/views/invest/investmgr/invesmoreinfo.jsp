<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="tag" uri="/tags/mytags" %>  
<div>
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">产品定义</legend>
			<table>
				<tr><td height="20px"></td></tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor tbl_td_width">投资产品名称</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property  value="investProductVo.invt_product_name"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor tbl_td_width">投资产品发起人</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="investProductVo.user_name"/>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor tbl_td_width">发行上限</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:if test="investProductVo.amount == null">
			 				<label>无上限</label>	
			 			</s:if>
			 			<s:else>
				 			<s:property value="investProductVo.amount"/>
				 			<label>元</label>
			 			</s:else>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor tbl_td_width">发起投资管理费</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="investProductVo.issue_fee"/>
			 			<label>元</label>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="tbl_td_15_b tbl_td_lbcolor tbl_td_width">募集开始时间</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="investProductVo.start_date"/>
			 		</td>
			 		<td class="tbl_td_15_b tbl_td_lbcolor tbl_td_width">募集结束时间</td>
			 		<td class="tbl_td_15_b tbl_td_width">
			 			<s:property value="investProductVo.end_date"/>
			 		</td>
			 	</tr>
				 <tr>
			 		<td  class="tbl_td_15_b tbl_td_lbcolor">投资人管理费计提方式</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="investProductVo.user_accruedcharges_typename"/>
			 		</td>				 	
			 		<td  class="tbl_td_15_b tbl_td_lbcolor"><label id="investmgrlbl">投资人管理费计提比例:</label></td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="investProductVo.user_accruedcharges_amount"/>
			 			<label>%</label>
				 	</td>
				 </tr>
				 <tr>
			 		<td  class="tbl_td_15_b tbl_td_lbcolor">投资人保证金比例:</td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="investProductVo.invester_bail"/>
			 			<label>%</label>
			 		</td>				 	
			 		<td  class="tbl_td_15_b tbl_td_lbcolor"><label id="investmgrlbl">发起人管理费计提比例:</label></td>
			 		<td class="tbl_td_15_b">
			 			<s:property value="investProductVo.launcher_accruedcharges_amount"/>
			 			<label id="investmgrcell">%</label>
				 	</td>
				 </tr>
				 <tr>
			 		<td  class="tbl_td_15_b tbl_td_lbcolor">产品已募集总金额</td>
			 		<td class="tbl_td_15_b" style="color:red">
			 			<s:property value="investProductVo.invest_all"/>元
			 		</td>				 	
			 		<td  class="tbl_td_15_b tbl_td_lbcolor"><label id="investmgrlbl"></label></td>
			 		<td class="tbl_td_15_b">
				 	</td>
				 </tr>
			 	<tr height="20px"><td colspan="6" align="center"><br></td></tr>
			</table>
		</fieldset>
		<fieldset style="border:0px">
			<legend class="fieldset-lgend">已绑定基础标的</legend>
			<table>
					<tr>
						<td class="tbl_td_20 tbl_tdtitle160">标的名称</td>
						<td class="tbl_td_20 tbl_tdtitle140">所属市场</td>
						<td class="tbl_td_20 tbl_tdtitle140">股票代码</td>
						<td class="tbl_td_20 tbl_tdtitle140">开始日期</td>
						<td class="tbl_td_20 tbl_tdtitle140">结束日期</td>
						<td class="tbl_td_20 tbl_tdtitle140">中签公布日期</td>
					</tr>
					<s:iterator value="lmvolist" status="s">
					<tr>
						<td class="tbl_td_20"><s:property value="basic_product_name"/></td>
						<td class="tbl_td_20"><s:property value="sales_cityname"/></td>
						<td class="tbl_td_20"><s:property value="invest_product_id"/></td>
						<td class="tbl_td_20"><s:property value="start_date"/></td>
						<td class="tbl_td_20"><s:property value="end_date"/></td>
						<td class="tbl_td_20"><s:property value="lot_date"/></td>	
					</tr>
				</s:iterator>
			</table>
		</fieldset>
</div>
</body>
</html>