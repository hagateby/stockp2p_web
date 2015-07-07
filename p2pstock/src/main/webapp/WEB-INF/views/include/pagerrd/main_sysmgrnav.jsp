<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--nav begin-->
		    <div class="container_12 mt10">
		      <ul class="ui-breadcrumb grid_12 fn-clear" id="navul">
		      	<s:property value="%{#session.menusecname}"/>
		      </ul>
		    </div>
<!--nav end-->