function secPrtStatus(paramvalue,paramname){
	$('#invt_product_status').val(paramvalue);
	$('#invt_product_statusname').val(paramname);
	$('#secprtstatuspdis').html(paramname);
	$('#prtstatusdiv').css('display','none');
}
function moreinfo(invt_product_id,invt_product_status){
	
	if(invt_product_status == '0' || invt_product_status == '3'){
		$('#invt_product_id').val(invt_product_id);
		$('#ajaxinvt_product_id').val(invt_product_id);
		ajaxCommonSubmit('checkform', 'checkinvestok','/p2pstock/ajax/checkInvestAjax_pubResultMgrAction.action');
		
	}else if(invt_product_status == '2' || invt_product_status == '4' || invt_product_status == '5'){
		$('#lerror').text('该产品已结算完成，不能修改录签结果');
	}
}

function checkinvestok(msg){
	if(msg == 0){
		$('#pubform').submit();
	}
	if(msg == 1){
		$('#lerror').text('该产品当前无任何有效投资');
		return;
	}
	if(msg == 2){
		$('#lerror').text('该产品存在未审核投资，请完成审核后再录签');
		return;
	}
	//$('#pubform').submit();
}
//保存
function saveresult(){
	//分隔符
	var fmt = ";";
	//遍历table
	for(x = 1;x<=$('#settlesize').val();x++){
		var tmpbaseid = $("#settletbl").find("tr").eq(x).find("td").eq(4).find("input").eq(0).val();
		var tmpresultno = $("#settletbl").find("tr").eq(x).find("td").eq(4).find("textarea").eq(0).val();
		tmpresultno = getallnumstr(tmpresultno).replaceAll(' ', '');
		if(tmpresultno == ""){
			$('#lerror').text('请录入中签号');
			return false;
		}else{
			var barry = $('#basic_product_idarray').val();
			var parry = $('#result_noarray').val();
			$('#basic_product_idarray').val(barry + tmpbaseid + fmt);
			$('#result_noarray').val(parry + tmpresultno + fmt);
		}
	}
	if(confirm("确认保存？")){
		$('#pubresultform').submit();
	}
}

function editresult(obj){
	$(obj).parent().prev('td').html('<input type="text" id="resultno"  maxlength="6" style="width:80px"/>');
	$(obj).prev('a').attr('disabled',false);
}

function cancleinfo(){
	$('#cancleform').submit();
}
function settle(invt_product_id,invt_product_status){
	
	if(invt_product_status == '4' || invt_product_status == '5'){
		$('#lerror').text('该产品开始利润分配，不能重复结算');
		return;
	}
	if(invt_product_status != '3' && invt_product_status != '2'){
		$('#lerror').text('产品录入中签号码之后才能结算');
		return;
	}else{
		$('#settleinvt_product_id').val(invt_product_id);
		$('#settleform').submit();
	}
}
//结算提交
function settlesub(){
	for(i = 1;i<=$('#settlesize').val();i++){
		var tmpbaseid = $("#settletbl").find("tr").eq(i).find("td").eq(4).find("input").eq(0).val();
		var tmppricesail = $("#settletbl").find("tr").eq(i).find("td").eq(4).find("input").eq(1).val();
		if(tmppricesail == ""){
			$('#lerror').text('请录入卖出价');
			return false;
		}else{
			var barry = $('#basic_product_idarray').val();
			var parry = $('#price_sailarray').val();
			$('#basic_product_idarray').val(barry + tmpbaseid + ',');
			$('#price_sailarray').val(parry + tmppricesail + ',');
		}
	}
	$('#settleform').submit();
}

//按钮事件
$(function($){
	//点击查询
	$('#secBtn').click(function(){
		$('#queryform').submit();
	});
});