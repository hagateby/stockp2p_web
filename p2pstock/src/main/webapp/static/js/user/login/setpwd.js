//按钮事件
$(function($){
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
	$('#loginBtn').click(function(){
		if(formchk()){
			$("#loginform").submit();
		}
	});
});
function formchk(){
	//表单验证
	if($("#passwd").val() == ""){
		$("#lerror").text("密码不能为空");
		$("#lerror").css("display","block");
		return false;
	}
	if($("#cusPass2").val() == ""){
		$("#lerror").text("重复密码不能为空");
		$("#lerror").css("display","block");
		return false;
	}
	if($('#passwd').val() != $('#cusPass2').val()){
		$('#lerror').text("两次密码输入不一致");
		$("#lerror").css("display", "block");
		return false;
	}
	return true;
}