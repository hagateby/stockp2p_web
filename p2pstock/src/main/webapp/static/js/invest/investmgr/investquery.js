//按钮事件
$(function($){
	//ajax加载可投资产品列表
	ajaxCommonSubmit('nowinvestform', 'queryinvestok','/p2pstock/ajax/queryActiveIssurInfo_issueMgrAction.action');
	ajaxCommonSubmit('hisinvestform', 'queryhisinvestok','/p2pstock/ajax/queryHisIssurInfo_issueMgrAction.action');
});
function queryinvestok(msg){
	$('#avlinvestdiv').html(msg);
}
function queryhisinvestok(msg){
	$('#hisinvestdiv').html(msg);
}
function querymoreinfook(msg){
	$('#nowdiv').css('display','none');
	$('#hisdiv').css('display','none');
	$('#morediv').css('display','block');
	$('#moreinvestdiv').html(msg);
}
function moreinfo(invt_product_id){
	$('#nowinvt_product_id').val(invt_product_id);
	ajaxCommonSubmit('moreform', 'querymoreinfook','/p2pstock/ajax/moreIssueInfo_issueMgrAction.action');
}
function editinfo(invt_product_id){
	$('#nowinvt_product_id').val(invt_product_id);
	ajaxCommonSubmit('moreform', 'querymoreinfook','/p2pstock/ajax/moreIssueInfo_issueMgrAction.action');
}
function canclemore(){
	$('#nowdiv').css('display','block');
	$('#hisdiv').css('display','block');
	$('#morediv').css('display','none');
}
function invest(invt_product_id){
	$('#invt_product_id').val(invt_product_id);
	$('#investform').submit();
}
