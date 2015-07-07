function secProTyp(obj,paramcode,paramname){
	$('#basic_product_type').val(paramcode);
	$('#secdis').html(paramname);
	$('#typdiv').css('display','none');
}
function moreinfo(basic_product_id){
	$('#basic_product_id').val(basic_product_id);
	$('#moreinfoform').submit();
}
function editinfo(basic_product_id){
	$('#ebasic_product_id').val(basic_product_id);
	$('#editinfoform').submit();
}
function delinfo(basic_product_id){
	
	if(confirm("确认删除？")){
		$('#dbasic_product_id').val(basic_product_id);
		$('#delinfoform').submit();
	}
	
}
//按钮事件
$(function($){
	//点击新增
	$('#addBtn').click(function(){
		$('#addform').submit();
	});
	//点击查询
	$('#secBtn').click(function(){
		$('#productQueryForm').submit();
	});
	
});