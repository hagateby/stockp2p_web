
//按钮事件
$(function($){
	//点击查询
	$('#secBtn').click(function(){
		$('#userQueryForm').submit();
	});
	
});
function moreinfo(userid){
	$('#queryuserid').val(userid);
	$('#moreinfoform').submit();
}
