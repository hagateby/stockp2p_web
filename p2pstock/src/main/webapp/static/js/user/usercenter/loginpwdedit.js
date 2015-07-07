//按钮事件
$(function($){
	//保存更改
	$('#savebtn').click(function(){
		if(checkpass1() && checkpass2() && checkpass3() && check2pass()){
			$('#userInfoForm').submit();
		}
	});
});

//原登录密码框
function checkpass1(){
	if($('#login_pwd').val() == ''){
		$('#lerror').text('登录密码不能为空');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
}
//新登录密码框
function checkpass2(){
	if($('#newlogin_pwd').val() == ''){
		$('#lerror').text('新登录密码不能为空');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
}
//新登录密码框2
function checkpass3(){
	if($('#newlogin_pwd2').val() == ''){
		$('#lerror').text('再次输入登录密码不能为空');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
}
function check2pass(){
	if($('#newlogin_pwd').val() != $('#newlogin_pwd2').val()){
		$('#lerror').text('两次输入密码不一致，请重新输入！');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
	
}

