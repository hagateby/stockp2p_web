<%@ page contentType="text/html; charset=utf-8"  pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<html>
<head>
<title>大锅饭金服网-互联网金融产品创新及运营平台</title>
<link rel="shortcut icon" href="/p2pstock/static/images/my.ico" />
<meta http-equiv="Content-Type" content="大锅饭金服网专注于互联网金融产品创新及运营，大锅饭金服网首创互联网IPO众筹产品；大锅饭金服网开拓基于熟人圈信用网络模式的个人融资业务，为用户提供高效 便利的投融资方案" />
<meta name="keywords" content="大锅饭金服网" />
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/index.css" />
<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/one.css" />
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.blockUI.js" ></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.selectboxes.js"></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.bgiframe.min.js"	></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.multiSelect.js"></script>
<script type="text/javascript" charset="utf-8" src="/p2pstock/static/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/common/common.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/jquery/thickbox.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/quickTable/quickTable.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/quickTable/tableTest.js"></script>
<script type="text/javascript" src="/p2pstock/static/js/jquery/jquery-powerFloat.js"></script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1255513383'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1255513383%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>


	<link href="/p2pstock/static/js/Slider/css/slider.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/p2pstock/static/css/rrd/sysmgr.css"/>
	 <script src="/p2pstock/static/js/index/index.js" type="text/javascript"></script>
    <script src="/p2pstock/static/js/Slider/js/jQuery-easing.js" type="text/javascript"></script>
    <script src="/p2pstock/static/js/Slider/js/jQuery-jcSlider.js" type="text/javascript"></script>
</head>
<body>
<div class="pg-container">
<!--header begin-->
<div class="ui-header" id="header">
  <div class="ui-header-top ">
    <div class="container_12 fn-clear">
      <div class="grid_12 fn-clear">
        <div class="fn-left">
        </div>
        <div class="fn-right">
            	<a rclass="ui-nav-item reg-link" href="/p2pstock/regist/registInit_registAction.action">快速注册</a>
            	<a class="ui-nav-item login-link" href="/p2pstock/login/loginInit_loginAction.action">立即登录</a>    
          		<a class="ui-nav-item" href="#" target="_blank">帮助</a>
        </div>
      </div>
    </div>
  </div>
   <div class="ui-header-main">
    <div class="container_12 fn-clear">
      <div class="ui-header-logo-grid fn-left">
         <a class="ui-header-slogan" href="/p2pstock/init/index.action">
         	<img src="/p2pstock/static/images/rrd/mylogo.bmp" style="width:200px;height:60px"/>
         </a>
      </div>
      <div class="ui-header-slogan-grid fn-left ml20">
        <a class="ui-header-slogan" href="#"></a>
      </div>
      <div class="ui-header-grid fn-right">
        <div class="fn-hide no-nav-text"></div>
        <ul class='ui-nav'><li class='ui-nav-item ui-nav-item-x'id='li_INDEXPAGE'><a class='ui-nav-item-link rrd-dimgray'id='a_INDEXPAGE' href='/p2pstock/init/index.action?menucode=INDEXPAGE'><span id=span_INDEXPAGE'>首页</span></a></li><li class='ui-nav-item ui-nav-item-x'id='li_GOTOINVEST'><a class='ui-nav-item-link rrd-dimgray'id='a_GOTOINVEST' href='/p2pstock/invest/queryInvestInfo_investMgrAction.action?menucode=GOTOINVEST'><span id=span_GOTOINVEST'>我要投资</span></a></li><li class='ui-nav-item ui-nav-item-x'id='li_MYACOUNT'><a class='ui-nav-item-link rrd-dimgray'id='a_MYACOUNT' href='/p2pstock/usercenter/userInit_userCenterAction.action?menucode=MYACOUNT'><span id=span_MYACOUNT'>我的账户</span></a></li><li class='ui-nav-item ui-nav-item-x'id='li_INVESTNO'><a class='ui-nav-item-link rrd-dimgray'id='a_INVESTNO' href='/p2pstock/invest/queryInvestNoInit_investNoMgrAction.action?menucode=INVESTNO'><span id=span_INVESTNO'>配号查询</span></a></li><li class='ui-nav-item ui-nav-item-x'id='li_ABOUTME'><a class='ui-nav-item-link rrd-dimgray'id='a_ABOUTME' href='/p2pstock?menucode=ABOUTME'><span id=span_ABOUTME'>关于我们</span></a></li></ul>

      </div>
    </div>
  </div>
</div>
<!--header end-->
		<!--主内容区 begin-->
		<div class="pg-container-content" style="min-height:710px">
			
			  <div id="pg-index" style="min-height:850px">
			    <!-- Slide -->
			       <div id="pic_wrap" style="display:block">
						<form action="/p2pstock/regist/registInit_registAction.action" method="post" theme="simple" id="registform">
						</form>
						<form action="/ajax/queryNewProduct_indexAction.action" method="post" theme="simple" id="newproductform">
						</form>
						<form action="/ajax/queryNewInvest_indexAction.action" method="post" theme="simple" id="newinvestform">
						</form>
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
					 <div class="register-box-inner" id="indexdiv">
					 </div> 
				  </div>
			</div>
		</div>
		<!--主内容区 end-->
<!-- footer begin -->
<div class="ui-footer" id="footer">
  <div class="container_12">
    <div class="grid_12">
      <div class="ui-footer-section last">
      	<div class="ui-footer-copyright">
          <span class="ui-footer-contact-link color-gray-text has-separator">京ICP证 号</span>
          <span class="ui-footer-contact-link color-gray-text has-separator">京公网安备</span>
          <span class="ui-footer-contact-link color-gray-text has-separator last">京ICP备号</span>
      	</div>
      </div>
    </div>
  </div>
</div>
<!-- footer end -->
	</div>
</body>
</html>