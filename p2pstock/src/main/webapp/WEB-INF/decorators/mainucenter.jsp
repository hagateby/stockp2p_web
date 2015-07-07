<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="UTF-8">
<head>
<link rel="shortcut icon" href="/p2pstock/static/images/my.ico" />
<meta http-equiv="Content-Type" content="大锅饭金服网专注于互联网金融产品创新及运营，大锅饭金服网首创互联网IPO众筹产品；大锅饭金服网开拓基于熟人圈信用网络模式的个人融资业务，为用户提供高效 便利的投融资方案" />
<meta name="keywords" content="大锅饭金服网" />
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/index.css" />
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/one.css" />
<title>大锅饭金服网-互联网金融产品创新及运营平台</title>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.min.js" ></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.blockUI.js" ></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.selectboxes.js"></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.bgiframe.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.multiSelect.js"></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/common/common.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/jquery/thickbox.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/quickTable/quickTable.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/quickTable/tableTest.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/jquery/jquery-powerFloat.js"></script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1255513383'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1255513383%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
<decorator:head /></head>
<body>
	<!-- 已登录布局 -->
	<s:if test="%{#session.username != null && #session.username!=''}">
	  	<div class="pg-container">
			<page:applyDecorator name="main_headerrrd" />
			<!--主内容区 begin-->
			<div class="pg-container-content">
				<div id="pg-account-index" class="pg-account" style="min-height:710px;" >
					<!-- 导航 -->
					<page:applyDecorator name="main_ucenternav" />
					<div class="container_12 mt10 fn-clear">
	        		<page:applyDecorator name="main_ucentertree" />
					<decorator:body/>
					</div>
					
				</div>
			</div>
			<!--主内容区 end-->
			<page:applyDecorator name="main_footerrrd" />
		</div>
	</s:if>
	<!-- 未登录布局 -->
	<s:else>
	  	<div class="pg-container">
			<page:applyDecorator name="main_headerrrd" />
			<!--主内容区 begin-->
			<div class="pg-container-content" style="min-height:710px">
				<decorator:body/>
			</div>
			<!--主内容区 end-->
			<page:applyDecorator name="main_footerrrd" />
		</div>	
	</s:else>
</body>
</html>