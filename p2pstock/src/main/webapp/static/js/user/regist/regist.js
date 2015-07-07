//按钮获取手机验证码
$(function($){
	$('#regist_CptBtn').click(function(){
		if(checkphone()){
			$('#cptcusMobile').val($('#cusMobile').val())
			ajaxCommonSubmit('registform', 'subok','/p2pstock/ajax/ajaxCpt_cptAction.action');
		}
		
	});
	$('#agreebtn').click(function(){
		if($('#agreebtn').attr('checked')){
			$('#registbtn').attr('disabled',false);
		}else{
			$('#registbtn').attr('disabled',true);
		}
	});	
});
//注册
function regist(){
	var r1 = checkphone();
	if(!checkphone()){
		return false;
	}
	if(!checkpass1()){
		return false;
	}
	if(!checkpass2()){
		return false;
	}
	if(!checkcpt()){
		return false;
	}
	if($('#cusPass1').val() != $('#cusPass2').val()){
		$('#p0error').text("两次密码输入不一致");
		$("#p0error").css("display", "block");
		return false;
	}
	if($('#agreeck').attr('checked') != 'checked'){
		$('#p0error').text("须阅读并同意网站协议才能注册");
		$("#p0error").css("display", "block");
		return false;
	}
	//提交表单
	$('#registform').submit();

}
//发送手机验证码回调函数
function subok(msg){
	if($.trim(msg) == 0){
		time($('#regist_CptBtn'),"120px","200px");
	}else if($.trim(msg) == 1){
		$('#p0error').text("发送失败请重试");
		$('#p0error').css("display", "block");
	}else if($.trim(msg) == 2){
		$('#p0error').text("该手机已经注册，不能重复注册");
		$('#p0error').css("display", "block");
	}
}
//验证手机号码合法性
function checkphone(){
	return checkContactNumber($.trim($("#cusMobile").val()))
}
//验证登录密码1是否为空
function checkpass1(){
	if($("#cusPass1").val() == ""){
		$("#p0error").text("登录密码不能为空");
		$("#p0error").css("display", "block");
		return false;
	}else{
		$("#p0error").css("display", "none");
		return true;
	}
}
//验证登录密码2是否为空
function checkpass2(){
	if($("#cusPass2").val() == ""){
		$("#p0error").text("重复登录密码不能为空");
		$("#p0error").css("display", "block");
		return false;
	}else{
		$("#p0error").css("display", "none");
		return true;
	}
}
//验证验证码是否为空
function checkcpt(){
	if($("#cusCpt").val() == ""){
		$("#p0error").text("验证码不能为空");
		$("#p0error").css("display", "block");
		return false;
	}else{
		$("#p0error").css("display", "none");
		return true;
	}
}
/*
 * 验证手机号码合法性
 */
function checkContactNumber(mobile) {
    if(mobile == ""){
    	$("#p0error").text("手机号码不能为空");
    	$("#p0error").css("display", "block");
    	return false;
    }
    //如果为1开头则验证手机号码   
    if (!checkPhoneRule(mobile)) {  
    	$("#p0error").text("请输入正确的手机号码");
    	$("#p0error").css("display", "block");
        return false;  
    }
    $("#p0error").css("display", "none");
    return true;  
}

function logininfo(){
	$('#loginform').submit();
}
function returnhome(){
	$('#homeform').submit();
}


