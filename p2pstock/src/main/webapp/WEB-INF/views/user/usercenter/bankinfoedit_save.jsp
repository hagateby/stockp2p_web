<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<html>
<head>
<script type="text/javascript" src="/p2pstock/static/js/user/usercenter/usercenter.js"></script>
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/account.css"/>
<title>用户中心</title>
</head>
<body>


<div class="grid_10">
	<div class="p20bs color-white-bg fn-clear">
   		<div id="bankList" class="bankList">
       		<div class="title">已添加银行卡</div>
       		<div id="banklis" class="mt20"></div>
  		</div>
  		<div id="J_moreTenCards" class="card-dialog " >
		    <h4 class="text-center">添加银行卡</h4>
		    <p class="more-tips">您最多可添加10张银行卡！</p>
		    <div class="card-btn text-center fn-clear">
		      <a href="javascript:" class="J_close close-btn block-btn">关闭</a>
		    </div>
  		</div>
  		

  		
 	</div>
</div>
</body>
</html>