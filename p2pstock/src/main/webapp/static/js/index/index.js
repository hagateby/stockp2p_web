
//按钮事件
$(function($){
    $('#pic_wrap').jcSlider({
        loading: false,                 //预加载loading开关设置，提供true,false
        loadpic: '../Slider/img/loading.gif',     //预加载loading图片路径，相对定位,如../img/riddick.png
        play: true,                     //是否开起自动播放功能，提供true,false
        play_speed: 4000,               //自动播放速度设置，提供easing值 或 数值(mm)
        slider_btn: true,               //左右按钮开关，提供true,false
        slider_speed: 500,              //图片切换速度设置，提供easing值 或 数值(mm)
        slider_num: true,               //数字按钮开关，提供true,false
        offset: 0,                      //设置左右按钮偏移量(px)
        btn_event: 'mouseover',             //数字按钮事件设置，提供click,mouseover等
        btn_position: 'middle',         //数字按钮位置，提供left,middle,right
        num_offsetW: 0,                 //设置数字按钮的X偏移(px)
        num_offsetH: 400,               //设置数字按钮的Y偏移(px)
        scaling: false,                  //是否设置图片大小，提供true,false
        width: 1000,                     //设置图片宽度单位(px)
        height: 300,                    //设置图片高度单位(px)        
        sliderModle: 'xScroll'
    });
    var tagli = $("#imgShow li");
    if (tagli.length > 0) {
        $('#pic_wrap').css('display', 'block');
    }
    var _w1 = $('#pic_wrap').width();
    var _w2 = $('#sliderNum').width();
    $('#sliderNum').css('left', (_w1 - _w2) / 2);
   
	//ajax加载最新产品信息
	ajaxCommonSubmit('newproductform', 'querynewproductok','/p2pstock/ajax/queryNewProduct_indexAction.action');
	ajaxCommonSubmit('newinvestform', 'querynewinvestok','/p2pstock/ajax/queryNewInvest_indexAction.action');
	
	//隐藏首页链接
	$('#indexli').css('display','none');
});
function regist(){
	$('#registform').submit();
}
function querynewproductok(msg){
	$('#newproductdiv').html(msg);
}
function querynewinvestok(msg){
	$('#newinvestdiv').html(msg);
}