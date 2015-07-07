//按钮事件
$(function($){
	//查询
	$('#secBtn').click(function(e){
		$('#queryform').submit();
	});
});
function moreinfo(invt_product_id){
	$('#invt_product_id').val(invt_product_id);
	ajaxCommonSubmit('moreform', 'moreok','/p2pstock/ajax/moreIssueInfo_issueMgrAction.action');
}
function moreok(msg){
	$('#nowdiv').css('display','none');
	$('#hisdiv').css('display','none');
	$('#morediv').css('display','block');
	$('#moreinvestdiv').html(msg);
}
function canclemore(){
	$('#nowdiv').css('display','block');
	$('#hisdiv').css('display','block');
	$('#morediv').css('display','none');
}
function display(index){
	document.getElementById("msgdiv"+index).style.display="block"; 
}
function disappear(index){
	document.getElementById("msgdiv"+index).style.display="none"; 
}