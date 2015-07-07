function lovchk(){
	
	var nowdate = getCurrentDate();
	if(dateCompare($('#lot_date').val(),nowdate) == "3" || dateCompare($('#lot_date').val(),nowdate) == "2"){
		$('#lot_rate').removeAttr('readonly');
	}else{
		$('#lot_rate').attr('readonly','true');
	}
}
$(function($){
	if($('#editflag').val() != '2'){
		lovchk();
	}
	//取消
	$('#cancleBtn').click(function(){
		$('#productSaveForm').submit();
	});
	//保存修改
	$('#saveBtn').click(function(){
		if($('#editflag').val() == '1'){
			$('#productEditForm').submit();
			return;
		}
		if($('#basic_product_name').val() == ""){
			$('#lerror').text('请输入产品名称');
			return;
		}
		if($('#invest_product_id').val() == ""){
			$('#lerror').text('请输入股票代码');
			return;
		}
		if($('#price').val() == ""){
			$('#lerror').text('请输入股票单价');
			return;
		}
		if($('#lot_date').val() == ""){
			$('#lerror').text('请输入中签公布日期');
			return;
		}
		if($('#start_date').val() == ""){
			$('#lerror').text('请输入募集开始时间');
			return;
		}
		if($('#end_date').val() == ""){
			$('#lerror').text('请输入募集结束时间');
			return;
		}
		var nowdate = getCurrentDate();
		if(dateCompare(nowdate,$('#start_date').val().substring(0,10)) == "1"){
			$('#lerror').text('募集开始时间不能早于当前时间');
			return;
		}
		if(dateCompare(nowdate,$('#end_date').val().substring(0,10)) == "1"){
			$('#lerror').text('募集结束时间不能早于当前时间');
			return;
		}
		if(dateCompare($('#start_date').val().substring(0,10),$('#end_date').val()) == "1"){
			$('#lerror').text('募集结束时间不能早于募集开始时间');
			return;
		}
		if(dateCompare($('#end_date').val().substring(0,10),$('#lot_date').val()) == "1"){
			$('#lerror').text('募集结束时间不能晚于中签公布时间');
			return;
		}
		if(dateCompare($('#lot_date').val(),$('#fundfree_date').val()) == "1"){
			$('#lerror').text('中签公布时间不能晚于资金解冻时间');
			return;
		}
		if($('#user_accruedcharges_amount').val() == ""){
			if("0" == $('#user_accruedcharges_type').val()){
				$('#lerror').text('请输入投资人管理费计提比例');
			}
			if("1" == $('#user_accruedcharges_type').val()){
				$('#lerror').text('请输入投资人管理费计提金额');
			}
			return;
		}
		if($('#invester_bail').val() == ""){
			$('#lerror').text('请输入投资保证金比例');
			return;
		}
		$('#sales_city').val(judgemarket($('#invest_product_id').val()));
		$('#productEditForm').submit();
	});
});
function secCity(paramvalue,paramname){
	
	$('#sales_city').val(paramvalue);
	$('#seccitydis').html(paramname);
	$('#citydiv').css('display','none');
}
function secInvestTyp(paramvalue,paramname){
	$('#basic_invest_type').val(paramvalue);
	$('#secinvesttypdis').html(paramname);
	$('#investtypdiv').css('display','none');
}
function secAccChargeTyp(paramvalue,paramname){
	$('#user_accruedcharges_type').val(paramvalue);
	$('#secaccchargetypdis').html(paramname);
	$('#accchargetypdiv').css('display','none');
}
function secLAccChargeTyp(paramvalue,paramname){
	$('#launcher_accruedcharges_type').val(paramvalue);
	$('#seclaccchargetypdis').html(paramname);
	$('#laccchargetypdiv').css('display','none');
}