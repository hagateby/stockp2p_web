function cancleinfo(){
	$('#cancleform').submit();
}
$(function($){
	//选择添加
	//$('#maincont').attr('style','width:1200px');
});
function detalinfo(user_name,update_date,stock_code,start_no,sub_code,acount_prtfeeall,praise_count,acount_bailall,acount_chargeall,user_settle_profit,invest_statusname){
	$('#dtuser_name').text(user_name);
	$('#dtupdate_date').text(update_date);
	$('#dtstock_code').text(stock_code);
	$('#dtstart_no').text(start_no);
	$('#dtsub_code').text(sub_code);
	$('#dtacount_prtfeeall').text(acount_prtfeeall);
	$('#dtpraise_count').text(praise_count);
	$('#dtacount_bailall').text(acount_bailall);
	$('#dtacount_chargeall').text(acount_chargeall);
	$('#dtuser_settle_profit').text(user_settle_profit);
	$('#dtinvest_statusname').text(invest_statusname);
	$('#maindiv').css('display','none');
	$('#detaildiv').css('display','block');
}
function closedetail(){
	$('#maindiv').css('display','block');
	$('#detaildiv').css('display','none');
}
