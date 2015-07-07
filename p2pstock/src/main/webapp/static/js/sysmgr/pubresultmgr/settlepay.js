function secPrtStatus(paramvalue,paramname){
	$('#settle_flag').val(paramvalue);
	$('#settle_flagname').val(paramname);
	$('#secprtstatuspdis').html(paramname);
	$('#prtstatusdiv').css('display','none');
}
//按钮事件
$(function($){
	$('#maincont').css('width','1100px');
	//点击查询
	$('#secBtn').click(function(){
		$('#productQueryForm').submit();
	});
	
});
function settlepay(ustl_id,invt_product_id){
	if(confirm("确认该笔结算已经完成线下支付进行利润分配？")){
		$('#ustl_id').val(ustl_id);
		$('#invt_product_id').val(invt_product_id);
		$('#settlepayform').submit();
	}
}
