 //去掉字符串头尾空格   
function trim(str) {   
    return str.replace(/(^\s*)|(\s*$)/g, "");   
}  
//身份证验证   
function checkIdcard(idcard1,showMsg){   
   var idcard=this.trim(idcard1);// 对身份证号码做处理。去除头尾空格。
      
   var Errors=new Array(   
   "验证通过!",   
   "身份证号码位数不对!",   
   "身份证号码出生日期超出范围或含有非法字符!",   
   "身份证号码校验错误!",   
   "身份证地区非法!"  
   );   
   var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}    
      
   var idcard,Y,JYM;   
   var S,M;   
   var idcard_array = new Array();   
   idcard_array = idcard.split(""); 
   /*基本校验*/  
   if(idcard == "" || idcard == null || idcard.length == 0)    
   {   
    if(showMsg==null||showMsg=="") alert("身份证号为空，请输入您的身份证号！");     
       
    return false;   
   }   
   /*地区检验*/  
   if(area[parseInt(idcard.substr(0,2))]==null)    
   {   
    if(showMsg==null||showMsg=="") alert(Errors[4]);    
       
    return false;   
   }   
   /*身份号码位数及格式检验*/  
   switch(idcard.length){   
    case 15:   
    if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){   
     ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性   
    } else {   
     ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性   
    }   
    if(ereg.test(idcard)){   
      //alert(Errors[0]+"15");    
      return true; //15位验证通过   
     }   
    else {   
      if(showMsg==null||showMsg=="") alert(Errors[2]);   
       return false;   
      }   
    break;   
       
    case 18:   
    //18位身份号码检测   
    //出生日期的合法性检查    
    //闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))   
    //平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))   
    if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){   
    ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式   
    } else {   
    ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式   
    }   
    if(ereg.test(idcard)){//测试出生日期的合法性   
     //计算校验位   
     S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7   
     + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9   
     + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10   
     + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5   
     + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8   
     + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4   
     + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2   
     + parseInt(idcard_array[7]) * 1    
     + parseInt(idcard_array[8]) * 6   
     + parseInt(idcard_array[9]) * 3 ;   
     Y = S % 11;   
     M = "F";   
     JYM = "10X98765432";   
     M = JYM.substr(Y,1);/*判断校验位*/  
     if(M == idcard_array[17]){   
      //alert(Errors[0]+"18");    
      return true; /*检测ID的校验位false;*/  
     }   
     else {   
      if(showMsg==null||showMsg=="") alert(Errors[3]);    
      return false;   
     }   
    }   
    else {   
     if(showMsg==null||showMsg=="") alert(Errors[2]);    
     return false;   
    }   
    break;   
       
    default:   
     if(showMsg==null||showMsg=="") alert(Errors[1]);    
     return false;   
   }   
 }  
 /**  
 * 通过身份证判断是男是女  
 * @param idCard 15/18位身份证号码   
 * @return '1'-女、'2'-男  
 */  
function maleOrFemalByIdCard(idCard){   
    var IdCard = this.trim(idCard);// 对身份证号码做处理。去除头尾空格。
    /*
    //验证身份证号
    if(!this.checkIdcard(IdCard)){
      return false;
    } 
    */  
    if(IdCard.length==15){   
        if(IdCard.substring(14,15)%2==0){   
            return '1';   
        }else{   
            return '2';   
        }   
    }else if(IdCard.length ==18){   
        if(IdCard.substring(14,17)%2==0){   
            return '1';   
        }else{   
            return '2';   
        }   
    }
}
/* 验证手机号 */
function checkMobile(mobileNum,showMsg){
  var ReDetection = false;
  var mobileNumber = this.trim(mobileNum);
  ReDetection = !(mobileNumber == "" || mobileNumber == null || mobileNumber.length == 0);
  if(!ReDetection) {
    if(showMsg==null||showMsg=="") alert("手机号为空，请输入您的手机号！"); 
    return false; // 基础验证
  }
  //ReDetection = (/^0(([1-9]d)|([3-9]d{2}))d{8}$/.test(m));if(ReDetection) return true; // 电话/小灵通验证
  var regu =/(^[1][3][0-9]{9}$)|(^[1][5][0-9]{9}$)|(^[1][8][0-9]{9}$)|(^[0][1-9]{1}[0-9]{9}$)/;	
	var reg = new RegExp(regu);
  ReDetection = reg.test(mobileNumber);  // 手机验证 13x 15x 18x 以此类推
  if(!ReDetection){
    if(showMsg==null||showMsg=="") alert("手机号不合法，请重新输入手机号！");
    return false;
  } 
    return true;
}
/* 验证电子邮箱的格式 */
function isEmail(email,showMsg) {
   var strEmail=this.trim(email);
   if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
  {
     return true;
  }else{
     if(showMsg==null||showMsg=="") alert("Email格式不正确！");
     return false;
  }
}
/* 身份证号确定出生日期 */
function birthdate(idCard,showMsg){
   var bir = null;
   var IdCard = this.trim(idCard);   
   /*
   //验证身份证号
   if(!this.checkIdcard(IdCard)){
     return false;
  }
  */
   if(IdCard.length==18){
     bir=IdCard.substr(6,4)+"-"+IdCard.substr(10,2)+"-"+IdCard.substr(12,2);
     }
   else if(IdCard.length==15){
     bir="19"+IdCard.substr(6,2)+"-"+IdCard.substr(8,2)+"-"+IdCard.substr(10,2);
     }else{
      if(showMsg==null||showMsg=="") alert("您的身份证号不正确，请输入正确的身份证号!");
      return false;
     }
     return bir;
}
/* 汉字验证，只能输入汉字！ */
function checkchinese(strChinese,showMsg)
{
   var reg=/^[\u4e00-\u9fa5]+$/i;
   var str=this.trim(strChinese);
   if(!reg.test(str)){
       if(showMsg==null||showMsg=="") alert("请输入汉字!");
       return false;
   }   
   return true;
}
/* 获取当前格式化后的时间 */
function getNowFormatDate()
{
   var day = new Date(); 

   var Year = 0;
   var Month = 0;
   var Day = 0;
   var CurrentDate = "";
   //初始化时间   
   Year       = day.getFullYear();
   Month      = day.getMonth()+1;
   Day        = day.getDate();
   
   CurrentDate += Year + "-";
   
   if (Month >= 10 )
   {
    CurrentDate += Month + "-";
   }
   else
   {
    CurrentDate += "0" + Month + "-";
   }
   if (Day >= 10 )
   {
    CurrentDate += Day ;
   }
   else
   {
    CurrentDate += "0" + Day ;
   } 
   return CurrentDate;
} 
/* 身份证号和出生日期验证 */
function checkBirByIdCard(idCard,birthdate,showMsg)
{
   var bir = null;
   if(idCard.length==18){
     bir=idCard.substr(6,4)+"-"+idCard.substr(10,2)+"-"+idCard.substr(12,2);
   }
   if(idCard.length==15){
     bir="19"+idCard.substr(6,2)+"-"+idCard.substr(8,2)+"-"+idCard.substr(10,2);
   } 
   if(bir==birthdate){
     return true;
  }else{
     if(showMsg==null||showMsg=="") alert("身份证号和出生日期不匹配！");
     return false;
  }
}
/* 身份证号返回年龄 */
function ageByIdCard(idCard)
{

   var birYear = null;
   var birMonth = null;
   var birDay = null;
   var day = new Date();
   //获取当前的日期
   Year       = day.getYear();
   Month      = day.getMonth()+1;
   Day        = day.getDate();
   if(idCard.length==18){
     birYear=idCard.substr(6,4);
     birMonth=idCard.substr(10,2);
     birDay=idCard.substr(12,2);
   }
   if(idCard.length==15){
     birYear="19"+idCard.substr(6,2);
     birMonth=idCard.substr(8,2);
     birDay=idCard.substr(10,2);
   } 
   
   var age = Year - birYear;
   
   if (Month >= 10 ){
    Month = Month ;
   }else{
    Month = "0" + Month ;
   }
   
   if (Day >= 10 ){
    Day = Day ;
   }else{
    Day = "0" + Day ;
   }
   //当前月、日大于出生月、日，年龄加1
   var m = Month - birMonth ;
   var d = Day - birDay ;  
   if (m > 0){
      age = age + 1 ;
  }else if (m == 0){
      if (d >= 0){
         age = age + 1 ;
      }
  }
  
   return age;
}
/* 身份证号和性别验证 */
function checkSexByIdCard(idCard,sex,showMsg)
{
   var s = null;
   if(idCard.length==15){   
        if(idCard.substring(14,15)%2==0){   
            s = '1';   
        }else{   
            s = '2';   
        }   
    }else if(idCard.length ==18){   
        if(idCard.substring(14,17)%2==0){   
            s = '1';   
        }else{   
            s ='2';   
        }   
    }
   if(s==sex){
     return true;
  }else{
     if(showMsg==null||showMsg=="") alert("身份证号和性别不匹配！");
     return false;
  }
}
/** 
*功能：	判断密码是否是字母 数字等组成并且6-20位
*参数：	str：		参数的字符串 
*返回：	如果通过验证返回true,否则返回false 
*/ 
function isPassword(str){
	var filter = /^([0-9a-zA-Z]){6,20}$/;
	if (!filter.exec(str)) return false;
		return true;
}

/** 
*功能：	判断用户名是否是字母 数字 . _等组成并且6-20位
*参数：	str：		参数的字符串 
*返回：	如果通过验证返回true,否则返回false 
*/ 
function isUsername(str){
	var filter=/^\s*[.A-Za-z0-9_-]{6,20}\s*$/;
	var filter=  /^[a-zA-Z]+\s*[.A-Za-z0-9_-]{5,20}\s*$/; 
	if(!filter.test(str)) return false;
	return true;
}
/* 生成校验码 */
function createCode(){ 
var code = "";
var codeLength = 4;//验证码的长度

var selectChar = new Array(2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');

for(var i=0;i<codeLength;i++) {
   var charIndex = Math.floor(Math.random()*32);
   code +=selectChar[charIndex];
}
if(code.length != codeLength){
   createCode();
}
return code;
}
/* 验证只能为数字 */
function   isNum(str,showMsg){  
  var   reg=/^[0-9]*[1-9][0-9]*$/;   
  if(reg.test(str)   ==   false)  
  {  
  	if(showMsg==null||showMsg=="") alert('请输入一个大于0的整数！');    
  return   false;  
  }  
  return true;
} 
/* 验证固定电话或传真 */
function isPhone(tele,flag){
	var tel = this.trim(tele);  
  switch(flag) 
	{ 
		case 1://国家代码  
		  if(tel.length==2||tel.length==3){
		     return true; 
		  }			   
			else{
			  return false;
			} 
		case 2://固话区号
			if(tel.length==3||tel.length==4)
			   return true; 
			else return false; 
		case 3://电话号码
			if(tel.length==7||tel.length==8)
			   return true; 
			else return false; 
		case 4://分机号
			if(tel.length==2||tel.length==3||tel.length==4||tel.length==5)
			   return true; 
			else return false; 
	} 
} 

/* 验证键盘输入的是否是数字 */
function checkNumFromKeyboard(){
	var key = window.event ? event.keyCode : event.which;
	var keychar = String.fromCharCode(key);
	var reg = /\d/;
	if(!reg.test(keychar)) return false;
	return true; 
}

/* 验证剪贴板里的是不是数字 */
function checkNumFromClipboardText(){
	var str=window.clipboardData.getData("Text");
	var   reg=/^[0-9]*[1-9][0-9]*$/;   
	if(reg.test(str)   ==   false)  
	{
		return   false;  
	}  
	return true;
}