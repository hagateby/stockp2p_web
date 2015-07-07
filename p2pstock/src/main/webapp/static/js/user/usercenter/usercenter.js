//按钮事件
$(function($){
	//页面修改按钮
	$('#modiForm').click(function(){
		if($('#disdiv').css('display') == 'none'){
			document.getElementById('userInfoForm').reset();
			$('#modiForm').html('修改信息');
			$('#disdiv').css('display','block');
			$('#edtdiv').css('display','none');
		}else{
			$('#modiForm').html('取消修改');
			$('#disdiv').css('display','none');
			$('#edtdiv').css('display','block');
		}
	});
	//页面保存按钮
	$('#savebtn').click(function(){
		if(  chkcertno() && chkmobile() && chkmail()){
			$('#userInfoForm').submit();
		}
	});
	//添加银行 卡按钮
	$('#addBankBtn').click(function(){
		$('#disdiv').css('display','none');
		$('#adddiv').css('display','block');
	});
	//取消添加按钮
	$('#cancleAddBtn').click(function(){
		$('#disdiv').css('display','block');
		$('#adddiv').css('display','none');
	});
	//选择银行按钮
	$('#secBankBtn').click(function(e){
		$('#bankdiv').css('display','block');
		e.stopPropagation();
	});
	//点击银行列表层以外的位置关闭层
	$('#bankdiv').click(function(e){
		e.stopPropagation();
	});
    $(document).click(function() {
    	$('#bankdiv').css('display','none');
    });

	
	//选择银行按钮
	$('#editsecBankBtn').click(function(e){
		$('#editbankdiv').css('display','block');
		e.stopPropagation();
	});
	//点击银行列表层以外的位置关闭层
	$('#editbankdiv').click(function(e){
		e.stopPropagation();
	});
    $(document).click(function() {
    	$('#editbankdiv').css('display','none');
    });
	
	
	//发送收验证码
	$('#cptBtn').click(function(){
		ajaxCommonSubmit('bankAddForm', 'subok','/p2pstock/ajax/ajaxCpt_cptAction.action');
	});
	//发送收验证码
	$('#editcptBtn').click(function(){
		ajaxCommonSubmit('bankEditForm', 'subok2','/p2pstock/ajax/ajaxCpt_cptAction.action');
	});
	//添加按钮
	$('#addSubBtn').click(function(){
		if($('#acccode').val() == ''){
			$('#lerror').text('请填写银行账号');
			return;
		}
		if($('#bank_name').val() == ''){
			$('#lerror').text('请选择银行');
			return;
		}
		if($('#brachname').val() == ''){
			$('#lerror').text('请填写开户行名称');
			return;
		}
		if($('#cpt').val() == ''){
			$('#lerror').text('请输入手机验证码');
			return;
		}
		$('#bankAddForm').submit();
		
	});
	//添加按钮
	$('#editSubBtn').click(function(){
		if($('#editacccode').val() == ''){
			$('#lerror').text('请填写银行账号');
			return;
		}
		if($('#editbank_name').val() == ''){
			$('#lerror').text('请选择银行');
			return;
		}
		if($('#editbrachname').val() == ''){
			$('#lerror').text('请填写开户行名称');
			return;
		}
		if($('#editcpt').val() == ''){
			$('#lerror').text('请输入手机验证码');
			return;
		}
		$('#bankEditForm').submit();
		
	});
	$('#closeeditbtn').click(function(){
		$('#editdiv').css('display','none');
		$('#disdiv').css('display','block');
	});
	$('#closeaddbtn').click(function(){
		$('#adddiv').css('display','none');
		$('#disdiv').css('display','block');
	});
	


	
	

});
//发送手机验证码回调函数
function subok(msg){
	if($.trim(msg) == 0){
		time($('#cptBtn'),"120px","200px");
	}
}
//发送手机验证码回调函数
function subok2(msg){
	if($.trim(msg) == 0){
		time($('#editcptBtn'),"120px","200px");
	}
}
function secBank(obj){
	//alert(obj.innerHTML);
	$('#bankdiv').css('display','none');
	$('#secdis').html(obj.innerHTML);
	$('#bank_name').val(obj.innerHTML);
}
function secBankEdit(obj){
	//alert(obj.innerHTML);
	$('#editbankdiv').css('display','none');
	$('#editsecdis').html(obj.innerHTML);
	$('#editbank_name').val(obj.innerHTML);
}
function editinfo(bank_code,bank_acc_code,bank_branch_name,bank_name,user_bank_acc_id){
	$('#editdiv').css('display','block');
	$('#editacccode').val(bank_acc_code);
	$('#editsecdis').html(bank_name);
	$('#editbrachname').val(bank_branch_name);
	$('#bank_id').val(user_bank_acc_id);
	$('#editbank_name').val(bank_name);
}
function delinfo(user_bank_acc_id){
	if(confirm("确认删除？")){
		$('#delbankaccid').val(user_bank_acc_id);
		$('#bankDelForm').submit();
	}
}
function setinfo(user_bank_acc_id){
	if(confirm("确认设置该卡为默认银行卡？")){
		$('#setbankaccid').val(user_bank_acc_id);
		$('#bankSetForm').submit();
	}
}
function chkmail(){
	if($('#mail').val() == ''){
		return true;
	}
	if(!isEmail($('#mail').val())){
		$('#lerror').text('邮箱格式不正确');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
}

function chkcertno(){
	if($('#certif_no').val() == ''){
		return true;
	}
	if(!isIdCardNo($('#certif_no').val())){
		$('#lerror').text('身份证号格式不正确');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
}
function chkmobile(){
	if($('#recod_phone').val() == ''){
		return true;
	}
	if(!isMobile($('#recod_phone').val())){
		$('#lerror').text('推荐人手机号码格式不正确');
		return false;
	}else{
		$('#lerror').text('');
		return true;
	}
	
	
}


