//按钮事件
$(function($){
	//ajax加载用户基本信息
	ajaxCommonSubmit('userbaseinfoform', 'querybaseinfook','/p2pstock/ajax/queryUserBaseInfo_usrMgrAction.action');
	ajaxCommonSubmit('userbankinfoform', 'querybankinfook','/p2pstock/ajax/bankQuery_userCenterAction.action');
});
function querybaseinfook(msg){
	$('#baseinfodiv').html(msg);
}
function querybankinfook(msg){
	$('#bankinfodiv').html(msg);
}
function cancleinfo(){
	$('#cancleform').submit();
}