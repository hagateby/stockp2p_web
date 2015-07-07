<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>公共首页</title>
	<link href="/p2pstock/static/js/Slider/css/slider.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>
	 <script src="/p2pstock/static/js/index/index.js" type="text/javascript"></script>
    <script src="/p2pstock/static/js/Slider/js/jQuery-easing.js" type="text/javascript"></script>
    <script src="/p2pstock/static/js/Slider/js/jQuery-jcSlider.js" type="text/javascript"></script>
   
</head>
<body>
  <div id="pg-index" style="min-height:850px">
    <!-- Slide -->
       <div id="pic_wrap" style="display:block">
			<s:form action="/regist/registInit_registAction.action" method="post" theme="simple" id="registform">
			</s:form>
			<s:form action="/ajax/queryNewProduct_indexAction.action" method="post" theme="simple" id="newproductform">
			</s:form>
			<s:form action="/ajax/queryNewInvest_indexAction.action" method="post" theme="simple" id="newinvestform">
			</s:form>
            <ul id="imgShow">
                <li>
                    <div class="middle-out">
                        <div class="middle-in">
                            <img src="/p2pstock/static/images/banners/bannernew01.jpg" width="800px" height="450px" />
                        </div>
                        <div align="center" style="margin-top: 5px; padding-top: 5px;">
                        </div>
                    </div>
                </li>
                <li>
                    <div class="middle-out">
                        <div class="middle-in">
                            <img src="/p2pstock/static/images/banners/bannernew02.jpg" width="800px" height="450px" />
                        </div>
                        <div align="center" style="margin-top: 5px; padding-top: 5px;">
                        </div>
                    </div>
                </li>
  <!--  
                 <li>
                    <div class="middle-out">
                        <div class="middle-in">
                            <img src="/p2pstock/static/images/banners/banner002.jpg" class="slider_style" />
                        </div>
                        <div align="center" style="margin-top: 5px; padding-top: 5px;">
                        </div>
                    </div>
                </li>
                 <li>
                    <div class="middle-out">
                        <div class="middle-in">
                            <img src="/p2pstock/static/images/banners/banner003.jpg" class="slider_style" />
                        </div>
                        <div align="center" style="margin-top: 5px; padding-top: 5px;">
                        </div>
                    </div>
                </li>
                 <li>
                    <div class="middle-out">
                        <div class="middle-in">
                            <img src="/p2pstock/static/images/banners/banner004.jpg" class="slider_style" />
                        </div>
                        <div align="center" style="margin-top: 5px; padding-top: 5px;">
                        </div>
                    </div>
                </li>
-->
            </ul>
        </div>
        <div style="padding-left:220px;padding-top:80px">
	        <div class="p20bs color-white-bg fn-clear" style="width:500px;float:left;display:inline">
	        	<div id="newproductdiv"></div>
			</div>
			<div style="width:60px;float:left;display:inline"><br/></div>
	        <div class="p20bs color-white-bg fn-clear" style="width:500px;float:left;display:inline">
	        	<div id="newinvestdiv"></div>
			</div>
        </div>
        
        <!-- 悬浮注册框 -->
    	<div class="register-box-outter">
		 <div class="register-box">	
		 </div> <!-- 悬浮注册框 end -->
		 <div class="register-box-inner">
				  		<p>已为用户赚取</p>
				  		<div class="num-unit">
				  			<span class="num"><s:property value="indexVo.user_invest_profit"/></span>
				  			<span class="unit">万元</span>
				  		</div>
				  		<p class="desc">累计成交额超过
				  		<s:property value="indexVo.product_buycount_all"/>万，累计交易 <s:property value="indexVo.trancount"/>人次</p>
				  		<input type="button" id="registbtn" onclick="regist()" class="ui-button-rrd-blue-new" value='立即注册'>
		 </div> 
	  </div>
</div>
</body>
</html>