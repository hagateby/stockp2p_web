
//按钮事件
$(function($){
	//点击查询
	$('#secBtn').click(function(){
		$('#queryform').submit();
	});
});
function secPrtStatus(paramvalue,paramname){
	$('#invt_product_status').val(paramvalue);
	$('#invt_product_statusname').val(paramname);
	$('#secprtstatuspdis').html(paramname);
	$('#prtstatusdiv').css('display','none');
}
