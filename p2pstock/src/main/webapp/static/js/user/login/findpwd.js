//按钮事件
$(function($){
	
	$('#regist_CptBtn').click(function(){
		if(checkphone()){
			$('#cptcusMobile').val($('#userno').val())
			ajaxCommonSubmit('findpwdform', 'subok','/p2pstock/ajax/ajaxCptOnly_cptAction.action');
		}
		
	});
	//光标进入
	$('#userno').focus(function(){
		if($("#userno").val() == "手机号"){
			$("#userno").val("");
		}
	});
	//光标离开
	$('#userno').blur(function(){
		if($("#userno").val() == ""){
			$("#userno").val("手机号");
			$("#lerror").text("用户名不能为空");
			$("#lerror").css("display","block");
		}else{
			$("#lerror").css("display","none");
		}
	});
	//光标进入
	$('#passwd').blur(function(){
		if($("#passwd").val() == ""){
			$("#lerror").text("密码不能为空");
			$("#lerror").css("display","block");
		}
	});
	
	//点击登录
	$('#findBtn').click(function(){
		if(formchk()){
			$("#findpwdform").submit();
		}
	});
});
//发送手机验证码回调函数
function subok(msg){
	if($.trim(msg) == 0){
		time($('#regist_CptBtn'),"120px","200px");
	}else if($.trim(msg) == 1){
		$('#lerror').text("发送失败请重试");
		$('#lerror').css("display", "block");
	}
}
//验证手机号码合法性
function checkphone(){
	return checkContactNumber($.trim($("#userno").val()))
}
/*
 * 验证手机号码合法性
 */
function checkContactNumber(mobile) {
    if(mobile == ""){
    	$("#lerror").text("手机号码不能为空");
    	$("#lerror").css("display", "block");
    	return false;
    }
    //如果为1开头则验证手机号码   
    if (!checkPhoneRule(mobile)) {  
    	$("#lerror").text("请输入正确的手机号码");
    	$("#lerror").css("display", "block");
        return false;  
    }
    $("#lerror").css("display", "none");
    return true;  
}

//验证验证码是否为空
function checkcpt(){
	if($("#cusCpt").val() == ""){
		$("#lerror").text("验证码不能为空");
		$("#lerror").css("display", "block");
		return false;
	}else{
		$("#lerror").css("display", "none");
		return true;
	}
}
function formchk(){
	//表单验证
	if($("#userno").val() == "手机号" || $("#userno").val() == ""){
		$("#lerror").text("用户名不能为空");
		$("#lerror").css("display","block");
		return false;
	}
	if($("#cusCpt").val() == ""){
		$("#lerror").text("验证码不能为空");
		$("#lerror").css("display","block");
		return false;
	}
	return true;
}
