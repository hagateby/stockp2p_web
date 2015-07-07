<%@ page contentType="text/html; charset=utf-8" language="java"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><decorator:title /></title>
<decorator:head />
</head>
<body>
  
	<div class="container">
		<page:applyDecorator name="main_header" />
		
		<!--主内容区 begin-->
		<decorator:body />
		<!--主内容区 end-->
	</div>
	<page:applyDecorator name="main_footer" />
</body>
</html>