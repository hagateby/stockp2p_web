function cancleinfo(){
	$('#cancleform').submit();
}
function settlepay(ustl_id,invt_product_id){
	if(confirm("确认该笔结算已经完成线下支付进行利润分配？")){
		$('#ustl_id').val(ustl_id);
		$('#invt_product_id').val(invt_product_id);
		$('#settlepayform').submit();
	}
}