//按钮事件
$(function($){
	//设置密码保存更改
	$('#setsavebtn').click(function(){
		if(setcheckpass2() && setcheckpass3() && checkset2pass()){
			$('#pwdSetForm').submit();
		}
	});
	//修改保存更改
	$('#editsavebtn').click(function(){
		if(edtcheckpass1() && edtcheckpass2() && edtcheckpass3()&&checkedit2pass){
			$('#pwdEditForm').submit();
		}
	});
});

//设置交易密码
function setcheckpass2(){
	if($('#setnewtran_pwd').val() == ''){
		$('#lerror').text('交易密码不能为空');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
}
//再次设置交易密码
function setcheckpass3(){
	if($('#setnewtran_pwd2').val() == ''){
		$('#lerror').text('重复交易密码不能为空');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
}

function checkset2pass(){
	if($('#setnewtran_pwd').val() != $('#setnewtran_pwd2').val()){
		$('#lerror').text('两次输入密码不一致，请重新输入！');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
	
}
//修改-原交易密码
function edtcheckpass1(){
	if($('#edittran_pwd').val() == ''){
		$('#lerror').text('原交易密码不能为空');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
}
//修改新交易密码
function edtcheckpass2(){
	if($('#editnewtran_pwd').val() == ''){
		$('#lerror').text('新交易密码不能为空');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
}
//修改重复新交易密码
function edtcheckpass3(){
	if($('#editnewtran_pwd2').val() == ''){
		$('#lerror').text('重复新交易密码不能为空');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
}
//设置交易密码
function checkedit2pass(){
	if($('#edittran_pwd').val() != $('#edittran_pwd2').val()){
		$('#lerror').text('两次输入密码不一致，请重新输入！');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
}

