<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<p>已为用户赚取</p>
<div class="num-unit">
	<span class="num"><s:property value="indexVo.user_invest_profit"/></span>
	<span class="unit">万元</span>
</div>
<p class="desc">累计成交额超过
<s:property value="indexVo.product_buycount_all"/>万，累计交易 <s:property value="indexVo.trancount"/>人次</p>
<input type="button" id="registbtn" onclick="regist()" class="ui-button-rrd-blue-new" value='立即注册'>