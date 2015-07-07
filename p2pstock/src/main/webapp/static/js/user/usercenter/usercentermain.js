//按钮事件
$(function($){
	//ajax加载可投资产品列表
	ajaxCommonSubmit('myinvestform', 'querymyinvestok','/p2pstock/ajax/queryMyInvest_userCenterAction.action');
	ajaxCommonSubmit('hisinvestform', 'queryhisinvestok','/p2pstock/ajax/queryMyInvestHis_userCenterAction.action');
});
function querymyinvestok(msg){
	$('#myinvestdiv').html(msg);
}


function queryhisinvestok(msg){
	$('#myinvesthisdiv').html(msg);
}
function querymoreinfook(msg){
	$('#nowdiv').css('display','none');
	$('#hisdiv').css('display','none');
	$('#morediv').css('display','block');
	$('#moreinvestdiv').html(msg);
}