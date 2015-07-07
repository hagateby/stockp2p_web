//按钮事件
$(function($){
	//绑定传感器
	$('#bindBtn').click(function(){
		$('#basediv').css('display','block');
		ajaxCommonSubmit('bindform', 'pagesubok','/tprtmt/ajax/productmgrInit_productMgrAction.action');
	});
	$('#ajaxqueryBtn').click(function(){
		ajaxCommonSubmit('bindform', 'pagesubok','/tprtmt/ajax/productmgrInit_productMgrAction.action');
	});
});
function pagesubok(msg){
	$('#baseprtdiv').html(msg);
}
//添加传感器
function addinfo(basic_product_id,basic_product_name,invest_product_id,basic_product_typename,launcher_accruedcharges_type,launcher_accruedcharges_amount,start_date,end_date){
	var basicarry = $('#base_product_idarray').val();
	var startdatearray = $('#startdatearray').val();
	var enddatearray = $('#enddatearray').val();
	if(launcher_accruedcharges_amount == ''){
		launcher_accruedcharges_amount = 0;
	}
	if(basicarry.indexOf(basic_product_id) >= 0){
		alert("该基础标的已经添加过了");
	}else{
		var addtr="<tr id='"+basic_product_id + "'><td class='tbl_td_15' >" + basic_product_typename + "</td>" + 
		"<td  class='tbl_td_15'>" + invest_product_id + "</td>" + 
		"<td  class='tbl_td_15'>" + basic_product_name + "</td>" +
		"<td  class='tbl_td_15'><a href='javascript:moreinfo("+ basic_product_id  +")'>产品详情</a></td>" +
		"<td  class='tbl_td_15'><a href='javascript:delinfo("+ basic_product_id  +","+launcher_accruedcharges_amount +")'>删除</a></td>";
		$('#bindtbl').append(addtr);
		$('#base_product_idarray').val(basicarry+basic_product_id+",");
		if(launcher_accruedcharges_type == '1'){
			var issue_fee;
			if($('#issue_feetxt').val() == ''){
				issue_fee = 0;
			}else{
				issue_fee = parseFloat($('#issue_feetxt').val());
			}
			$('#issue_feetxt').val(parseFloat(launcher_accruedcharges_amount) + issue_fee);
			$('#issue_fee').text(parseFloat(launcher_accruedcharges_amount) + issue_fee);
			issue_feetxt
		}
		var sarray = startdatearray + start_date +",";
		var earray = enddatearray + end_date + ",";
		
		//取基础标的最小开始日期作为投资产品开始日期
		var investsdate = findmindate(sarray);
		//取基础标的最大结束日期作为投资产品结束日期
		var investedate = findmaxdate(earray);
		$('#startdatearray').val(sarray);
		$('#enddatearray').val(earray);
		$('#start_date_label').text(investsdate);
		$('#end_date_label').text(investedate);
		$('#start_date').val(investsdate);
		$('#end_date').val(investedate);
		
	}
}
function delinfo(basic_product_id,launcher_accruedcharges_amount){
	var basicarry = $('#base_product_idarray').val();
	basicarry = basicarry.replaceAll(basic_product_id+',','');
	$('#'+basic_product_id).remove();
	$('#base_product_idarray').val(basicarry);
	//var amount =  parseFloat($('#issue_feetxt').val()) - launcher_accruedcharges_amount;
	//$('#issue_feetxt').val(amount);
	//$('#issue_fee').text(amount);
}

//验证表单必填项
function checkform(){
	if($("#invt_product_name").val() == ""){
		$("#lerror").text("投资产品名称不能为空");
		$("#lerror").css("display", "block");
		return false;
	}
	if($('#user_accruedcharges_amount').val() == ""){
		if("0" == $('#user_accruedcharges_type').val()){
			$('#lerror').text('请输入投资人管理费计提比例');
		}
		if("1" == $('#user_accruedcharges_type').val()){
			$('#lerror').text('请输入投资人管理费计提金额');
		}
		return false;
	}
	if($('#invester_bail').val() == ""){
		$('#lerror').text('请输入投资保证金比例');
		return false;
	}
	return true;
}
//验证是否绑定
function checkbind(){
	if($("#base_product_idarray").val()==""){
		$("#lerror").text("请绑定基础标的");
		$("#lerror").css("display", "block");
		return false;
	}else{
		$("#lerror").css("display", "none");
		return true;
	}
}


function saveinfo(){
	if( checkform() && checkbind()){
		if(confirm("确认保存？")){
			$('#saveform').submit();
		}
	}
}
function cancleinfo(){
	$('#cancleform').submit();
}