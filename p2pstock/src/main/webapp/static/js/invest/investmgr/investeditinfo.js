function cancleinfo(){
	$('#cancleform').submit();
}
function resetinfo(){
	document.getElementById("investform").reset();
}
function startnoBlur(obj,price,sales_city){
	var sub_code = $(obj).parent().next('td').children(":first").val();
	var all = sub_code*price;
	if(sales_city == '1'){//沪市一个签号为1000股
		all = 1000.00*all;
	}else{//深市一个签号为500股
		all = 500.00*all;
	}
	if(sub_code != '' ){
		$(obj).parent().next('td').next('td').html(all+'元')
	}

}
function invest(){
	if($('#basic_product_idarray').val() == ""){
		$('#lerror').text('请选择购买产品');
		return;
	}
	if(confirm("确认购买？")){
		$('#investform').submit();
	}
}

function subcodeBlur(obj,price,sales_city){
	
	var start_no = $(obj).parent().prev('td').children(":first").val();
	var all = price*$(obj).val();
	if(sales_city == '1'){//沪市一个签号为1000股
		all = 1000.00*all;
	}else{//深市一个签号为500股
		all = 500.00*all;
	}
	if(start_no != ''){
		$(obj).parent().next('td').html(all+'元')
	}
}
function del(obj){
	var okindexarray = $('#okindexarray').val();
	var okindex = $(obj).parent().parent().find("td").eq(0).children(":first").val();
	if(okindex == ''){
		$(obj).parent().parent().remove();
	}else{
		var basic_product_idarray = $('#basic_product_idarray').val();
		var start_noarray = $('#start_noarray').val();
		var sub_codearray = $('#sub_codearray').val();
		var pricearray = $('#pricearray').val();
		var invester_bailarray = $('#invester_bailarray').val();
		var user_accruedcharges_typearray = $('#user_accruedcharges_typearray').val();
		var user_accruedcharges_amountarray = $('#user_accruedcharges_amountarray').val();
		var feecntarray = $('#feecntarray').val();
		var index = strindex(okindexarray,okindex);
		basic_product_idarray = strremovebyindex(basic_product_idarray,index,'');
		start_noarray = strremovebyindex(start_noarray,index,'');
		sub_codearray = strremovebyindex(sub_codearray,index,'');
		pricearray = strremovebyindex(pricearray,index,'');
		invester_bailarray = strremovebyindex(invester_bailarray,index,'');
		user_accruedcharges_typearray = strremovebyindex(user_accruedcharges_typearray,index,'');
		user_accruedcharges_amountarray = strremovebyindex(user_accruedcharges_amountarray,index,'');
		feecntarray = strremovebyindex(feecntarray,index,'');
		okindexarray = strremovebyindex(okindexarray,index,'');
		$('#basic_product_idarray').val(basic_product_idarray)
		$('#start_noarray').val(start_noarray)
		$('#sub_codearray').val(sub_codearray)
		$('#pricearray').val(pricearray)
		$('#invester_bailarray').val(invester_bailarray)
		$('#user_accruedcharges_typearray').val(user_accruedcharges_typearray)
		$('#user_accruedcharges_amountarray').val(user_accruedcharges_amountarray)
		$('#feecntarray').val(feecntarray)
		$('#okindexarray').val(okindexarray)
		count();
		$(obj).parent().parent().remove();
	}
	
}

var okindex = 0;

function ok(obj,price,invester_bail,user_accruedcharges_amount,user_accruedcharges_type,basic_product_id,sales_city){

	//起始号码
	var start_no = $(obj).parent().prev().prev().prev().children(":first").val();
	//购买数量
	var sub_code = $(obj).parent().prev().prev().children(":first").val();
	if(start_no != '' && sub_code != ''){
		$(obj).parent().prev().prev().prev().html(start_no);
		$(obj).parent().prev().prev().html(sub_code);
		var basic_product_idarray = $('#basic_product_idarray').val();
		var start_noarray = $('#start_noarray').val();
		var sub_codearray = $('#sub_codearray').val();
		var pricearray = $('#pricearray').val();
		var invester_bailarray = $('#invester_bailarray').val();
		var user_accruedcharges_typearray = $('#user_accruedcharges_typearray').val();
		var user_accruedcharges_amountarray = $('#user_accruedcharges_amountarray').val();
		var feecntarray = $('#feecntarray').val();
		//每签对应的股数
		var feecnt;
		if(sales_city == '1'){//沪市一个签号为1000股
			feecnt = 1000;
		}else{//深市一个签号为500股
			feecnt = 500;
		}
		
		if(start_noarray == ""){
			$('#basic_product_idarray').val(basic_product_id+',');
			$('#start_noarray').val(start_no+',');
			$('#sub_codearray').val(sub_code+',');
			$('#pricearray').val(price+',');
			$('#invester_bailarray').val(invester_bail+',');
			$('#user_accruedcharges_typearray').val(user_accruedcharges_type+',');
			$('#user_accruedcharges_amountarray').val(user_accruedcharges_amount+',');
			$('#feecntarray').val(feecnt+',');
		}else{
			$('#basic_product_idarray').val(basic_product_idarray+basic_product_id+',');
			$('#start_noarray').val(start_noarray+start_no+',');
			$('#sub_codearray').val(sub_codearray+sub_code+',');
			$('#pricearray').val(pricearray+price+',');
			$('#invester_bailarray').val(invester_bailarray+invester_bail+',');
			$('#user_accruedcharges_typearray').val(user_accruedcharges_typearray+user_accruedcharges_type+',');
			$('#user_accruedcharges_amountarray').val(user_accruedcharges_amountarray+user_accruedcharges_amount+',');
			$('#feecntarray').val(feecntarray+feecnt+',');
		}
		count();
		
		var okindexarray = $('#okindexarray').val();
		$(obj).parent().parent().find("td").eq(0).children(":first").val(okindex);
		okindexarray = okindexarray + okindex + ",";
		$('#okindexarray').val(okindexarray);
		okindex = okindex + 1;
		//投资管理费总额计算
		$(obj).parent().html('');
	}
}
function count(){
	//计算总保证金、管理费、产品购买费
	//单价
	var priceary = $('#pricearray').val().split(",");
	//数量
	var subcodeary = $('#sub_codearray').val().split(",");
	//保证金比例
	var bailary = $('#invester_bailarray').val().split(",");
	//管理费类型
	var chargtypary = $('#user_accruedcharges_typearray').val().split(",");
	//管理费额度
	var chargeary = $('#user_accruedcharges_amountarray').val().split(",");
	//每签股数
	var fary = $('#feecntarray').val().split(",");
	var acount_bail = 0;
	//var acount_mgrfee = 0;
	var acount_prtfee = 0;
	var acount_all = 0;

	for(i=0;i<priceary.length - 1;i++){
		
		var feetmp = fary[i];
		//保证金
		acount_bail = toDecimal2(acount_bail*1 + (priceary[i]*subcodeary[i]*bailary[i]*feetmp)/100);
		//管理费
		/*
		if(chargtypary[i] == '0'){//按比例收取管理费
			acount_mgrfee = toDecimal2(acount_mgrfee)*1 + toDecimal2((priceary[i]*subcodeary[i]*chargeary[i])/100)*1;
		}else{//固定金额收取
			acount_mgrfee = toDecimal2(acount_mgrfee*1 + chargeary[i]*1);
		}
		*/
		//产品购买费
		acount_prtfee = toDecimal2(acount_prtfee*1 + priceary[i]*subcodeary[i]*feetmp);
		//总交费额
		acount_all = toDecimal2(acount_bail*1 + acount_prtfee*1);
	}

	$('#acount_bail').html(acount_bail);
	//$('#acount_mgrfee').html(acount_mgrfee);
	$('#acount_prtfee').html(acount_prtfee);
	$('#acount_all').html(acount_all);
}
function reinvest(){
	$('#reinvestform').submit();
}

function addproduct(sales_cityname,basic_product_name,invest_product_id,price,invester_bail,user_accruedcharges_type,user_accruedcharges_amount,basic_product_id,sales_city){
	var uamountlbl;
	
	if(user_accruedcharges_type == "0"){
		uamountlbl = "%";
	}else{
		uamountlbl = "元";
	}
	//新行
	var rowstr = "<tr><td class='tbl_td_20'><input type='hidden'><a onclick='del(this)' href='#'><img src='/p2pstock/static/images/rrd/del3.gif'/></a> " + sales_cityname + "</td>" + "<td class='tbl_td_20'>" + basic_product_name + "</td>" + "<td class='tbl_td_20'>" + invest_product_id + "</td>" 
	+ "<td class='tbl_td_20'>" + price + "元</td>" 
	+ "<td class='tbl_td_20'><input type='text' id='start_no' name='start_no' style='width:150px' onblur='startnoBlur(this," + price +","+sales_city + ")'/></td>"
	+ "<td class='tbl_td_20'><input type='text' id='sub_code' name='sub_code' style='width:80px' onblur='subcodeBlur(this," + price +","+sales_city + ")'/></td>" + "<td class='tbl_td_20'></td>"
	+ "<td class='tbl_td_20' id='tt'><a class='ui-button ui-button-green' onclick='ok(this," + price + "," + invester_bail + "," + user_accruedcharges_amount + "," + user_accruedcharges_type
	+ "," + basic_product_id + ","+sales_city + ")'>确定</a></td></tr>";
	var teststr = "<tr><td><input type='button' onclick='ttt(this,3123)' value='哈哈哈哈'></td></tr>";
	var rowcount = $('#prosize').val();
	$("#sectbl").find("tr").eq(0).after(rowstr);
}

$(function($){
	//选择添加
	$('#addbtn').click(function(e){
		$('#productsecdiv').css('display','block');
		e.stopPropagation();
	});
	//点击以外位置关闭层
	$('#productsecdiv').click(function(e){
		e.stopPropagation();
	});
    $(document).click(function() {
    	$('#productsecdiv').css('display','none');
    });
    //调整宽度
    $('#pg-account-index').attr('style','width:1230px');
});


