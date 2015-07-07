function secInvestTyp(paramvalue,paramname){
	$('#basic_invest_type').val(paramvalue);
	$('#secinvesttypdis').html(paramname);
	$('#investtypdiv').css('display','none');
}
function secAccChargeTyp(paramvalue,paramname){
	if("0" == paramvalue){
		$('#investmgrlbl').text("投资人管理费计提比例:");
		$('#investmgrcell').text("%");
	}
	if("1" == paramvalue){
		$('#investmgrlbl').text("投资人管理费计提金额:");
		$('#investmgrcell').text("元");
	}
	$('#user_accruedcharges_type').val(paramvalue);
	$('#secaccchargetypdis').html(paramname);
	$('#accchargetypdiv').css('display','none');
}
function secLAccChargeTyp(paramvalue,paramname){
	$('#launcher_accruedcharges_type').val(paramvalue);
	$('#seclaccchargetypdis').html(paramname);
	$('#laccchargetypdiv').css('display','none');
}
function lovchk(){
	var nowdate = getCurrentDate();
	if(dateCompare($('#lot_date').val(),nowdate) == "3" || dateCompare($('#lot_date').val(),nowdate) == "2"){
		$('#lot_rate').removeAttr('readonly');
	}else{
		$('#lot_rate').attr('readonly','readonly');
	}
}
$(function($){
	//保存修改
	$('#saveProductInfoBtn').click(function(){
		if($('#basic_product_name').val() == ""){
			$('#lerror').text('请输入产品名称');
			return;
		}
		if($('#invest_product_id').val() == ""){
			$('#lerror').text('请输入股票代码');
			return;
		}
		var mno = judgemarket($('#invest_product_id').val());
		if(mno == '-1'){
			$('#lerror').text('请输入正确的股票代码');
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
		$('#productSaveForm').submit();
	});
	
});