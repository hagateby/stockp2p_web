function secPrtStatus(paramvalue,paramname){
	$('#invt_product_status').val(paramvalue);
	$('#invt_product_statusname').val(paramname);
	$('#secprtstatuspdis').html(paramname);
	$('#prtstatusdiv').css('display','none');
}
//按钮事件
$(function($){
	//点击新增
	$('#addBtn').click(function(){
		$('#addform').submit();
	});
	//点击查询
	$('#secBtn').click(function(){
		if($('#secprtstatuspdis').html() == ""){
			$('#invt_product_status').val("");
		}
		$('#queryform').submit();
	});
});
function moreinfo(invt_product_id){
	$('#invt_product_id').val(invt_product_id);
	$('#moreform').submit();
}
function delinfo(invt_product_id){
	if(confirm("确定删除？")){
		$('#dinvt_product_id').val(invt_product_id);
		$('#delform').submit();	
	}
}
function editinfo(invt_product_id){
	$('#einvt_product_id').val(invt_product_id);
	$('#editform').submit();
	
}