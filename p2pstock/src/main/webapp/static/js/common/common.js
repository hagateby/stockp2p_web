function getTranSerialNo(sysCode,tranCode){
		//系统号+交易号+ip+年月日时分秒毫秒
	var tranSerialNo = "";
	var tdate = getCurrentDate2();//yyyy-mm-dd hh:mm:ss:S

	var userip = $("#cc").val();
	tranSerialNo = sysCode + tranCode + userip + tdate;
	tranSerialNo = tranSerialNo.replaceAll("-","");
	tranSerialNo = tranSerialNo.replaceAll(":","");
	tranSerialNo = tranSerialNo.replaceAll(" ","");
	tranSerialNo = tranSerialNo.replaceAll("\\.","");
	return tranSerialNo;
}

String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}

/**
 * 功能： 去掉字符串的前后空格 参数： strVal 字符串 返回： 去掉前后空格的字符串
 */
function trimString(strVal) {
	var reVal;
	var strTmp;
	strTmp = strVal + "";
	if (strTmp.length == 0)
		return (strTmp);
	reVal = /^(\s|　)*/;
	strTmp = strTmp.replace(reVal, '');
	reVal = /(\s|　)*$/;
	return (strTmp.replace(reVal, ''));
}

/**
 * 功能： 打开窗口居中 参数： theURL 窗口地址 winName： 窗口名称(目标) features： 窗口的其他属性 返回： 新窗口
 */
function openCenterWindow(theURL, winName, features) {
	var nWidth;
	var nHeight;
	var nLeft;
	var nTop;
	var strVal;
	var nPos;
	var strComb;
	var i;
	var chVal;
	nLeft = 0;
	nTop = 0;
	if (features == null)
		return (window.open(theURL, winName, features));
	strVal = features.toUpperCase();
	if (strVal.indexOf("LEFT", 0) >= 0 || strVal.indexOf("TOP", 0) >= 0)
		return (window.open(theURL, winName, features));
	if ((nPos = strVal.indexOf("WIDTH", 0)) < 0)
		return (window.open(theURL, winName, features));
	if ((nPos = strVal.indexOf("=", nPos)) < 0)
		return (window.open(theURL, winName, features));
	strComb = "";
	for (i = nPos + 1; i < features.length; i++) {
		chVal = features.charAt(i);
		if (chVal == " " || (chVal >= "0" && chVal <= "9"))
			strComb += chVal;
		else
			break;
	}
	if ((nWidth = eval(trimString(strComb))) <= 0)
		return (window.open(theURL, winName, features));
	if ((nPos = strVal.indexOf("HEIGHT", 0)) < 0)
		return (window.open(theURL, winName, features));
	if ((nPos = strVal.indexOf("=", nPos)) < 0)
		return (window.open(theURL, winName, features));
	strComb = "";
	for (i = nPos + 1; i < features.length; i++) {
		chVal = features.charAt(i);
		if (chVal == " " || (chVal >= "0" && chVal <= "9"))
			strComb += chVal;
		else
			break;
	}
	if ((nHeight = eval(trimString(strComb))) <= 0)
		return (window.open(theURL, winName, features));
	nLeft = (window.screen.width - nWidth) / 2;
	nTop = (window.screen.height - nHeight) / 2;
	features += ",left=" + nLeft + ",top=" + nTop;
	return (window.open(theURL, winName, features));
}

/**
 * 功能： 创建一个显示HTML内容的模态对话框 参数： theURL 指定对话框要显示的文档的URL地址 vArguments： 用来向对话框传递参数。
 * 传递的参数类型不限，包括数组等。 对话框通过window.dialogArguments来取得传递进来的参数。 sFeatures：
 * 描述对话框的外观等信息 返回： 模态对话框返回值
 */
function openModalDialog(theURL, vArguments, sFeatures) {
	var vReturnValue = window.showModalDialog(theURL, vArguments, sFeatures);
	return vReturnValue;
}

/**
 * 功能： 创建一个显示HTML内容的非模态对话框 参数： theURL 窗口地址 vArguments： 用来向对话框传递参数。
 * 传递的参数类型不限，包括数组等。 对话框通过window.dialogArguments来取得传递进来的参数。
 * 
 * sFeatures： 描述对话框的外观等信息 返回： 对话框返回值
 */
function openModelessDialog(theURL, vArguments, sFeatures) {
	var vReturnValue = window.showModelessDialog(theURL, vArguments, sFeatures);
	return vReturnValue;
}

/**
 * 功能： 提示对话框 参数： msg： 提示信息 返回：
 */
function showalert(msg) {
	if (typeof (msg) != "undefined" && msg != "")
		alert(msg);
}

// 刷新父窗口
function refreshParent() {
	window.opener.location.href = window.opener.location.href;
	if (window.opener.progressWindow)
		window.opener.progressWindow.close();
	window.close();
}

// 简单跳转
function gotoPage(url) {
	window.location = url;
}

/**
 * 功能： 刷新校验码 参数： imgId： 校验码图片ID 返回：
 */
function changeValidateKey(imgId) {
	var changeURL = "";
	changeURL = eval(imgId).src + '&r=' + Math.random();
	eval(imgId).src = changeURL;
	// eval(imgId).setAttribute('src',changeURL);
	// setTimeout(function(){eval(imgId).src=changeURL; } ,20);
}

// 日期格式化
function getFormatDate(objDate, format) {
	var o = {
		"M+" : objDate.getMonth() + 1, // month
		"d+" : objDate.getDate(), // day
		"h+" : objDate.getHours(), // hour
		"m+" : objDate.getMinutes(), // minute
		"s+" : objDate.getSeconds(), // second
		"q+" : Math.floor((objDate.getMonth() + 3) / 3), // quarter
		"S" : objDate.getMilliseconds()
	// millisecond
	}

	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (objDate.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

// 获取当前日期
function getCurrentDate() {
	var date = new Date();
	return getFormatDate(date, 'yyyy-MM-dd');
}

// 获取当前日期 YYYY-MM-DD HH:MM:SS
function getCurrentDate1() {
	var date = new Date();
	return getFormatDate(date, 'yyyy-MM-dd hh:mm:ss');
}

// 获取当前日期 YYYY-MM-DD HH:MM:SS:S
function getCurrentDate2() {
	var date = new Date();
	return getFormatDate(date, 'yyyy-MM-dd hh:mm:ss:S');
}

// 日期加减
function dateAddDay(day) {
	var date = new Date();
	date = date.valueOf();
	date = date + parseInt(day) * 24 * 60 * 60 * 1000;
	date = new Date(date);
	return getFormatDate(date, 'yyyy-MM-dd');
}
// 日期加减
function dateAddMonth(month) {
	var date = new Date();
	date.setMonth(date.getMonth() + parseInt(month));
	date = new Date(date);
	return getFormatDate(date, 'yyyy-MM-dd');
}

function MacInfo(){    
 var mac = "";  
 var locator =new ActiveXObject ("WbemScripting.SWbemLocator");       
 var service = locator.ConnectServer(".");       
 var properties = service.ExecQuery("Select * from Win32_NetworkAdapterConfiguration Where IPEnabled =True");       
 var e =new Enumerator (properties);       
 { var p = e.item();            
   mac = p.MACAddress;            
   return mac;       
  } 
}

// function getMac(){  
// 	alert(11);     
//        var locator =new ActiveXObject ("WbemScripting.SWbemLocator");  
//        alert(12);      
//        var service = locator.ConnectServer(".");       
//        alert(22);     
//        var properties = service.ExecQuery("Select * from Win32_NetworkAdapterConfiguration Where IPEnabled =True");   
//        alert(33);         
//        var e =new Enumerator (properties);
//        alert(44);     
//        var p = e.item();      
//        alert(55);     
//        //获取mac地址      
//        var myMac = p.MACAddress;  
//        alert(66);        
//        return myMac;        
//       // alert("你的mac地址是： " + myMac)       
//    } 
    
/**
 * 用途：检查输入字符串是否为空或者全部都是空格 输入：str 返回： 如果全是空返回true,否则返回false
 */
function isNull(str) {
	if (str == "" || str == null || str.length == 0)
		return true;
	var regu = "^[ 　]+$";
	var re = new RegExp(regu);
	return re.test(str);
}

/**
 * 功能： 检查参数对象的值是否符合E-Mail格式 参数： str： 参数的字符串 返回： 如果通过验证返回true,否则返回false
 */
function isEmail(strEmail) {
	var myReg = /^[_a-zA-Z][_a-zA-Z0-9]*@[_a-z0-9]+\.[a-zA-Z]{2,5}(\.[a-zA-Z]{2,3})?$/;
	var emailReg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
	if (emailReg.test(strEmail))
		return true;
	return false;
}

/**
 * ^[1-9]d*$ //匹配正整数 ^-[1-9]d*$ //匹配负整数 ^-?[1-9]d*$ //匹配整数 ^[1-9]d*|0$
 * //匹配非负整数（正整数 + 0） ^-[1-9]d*|0$ //匹配非正整数（负整数 + 0） ^[1-9]d*.d*|0.d*[1-9]d*$
 * //匹配正浮点数 ^-([1-9]d*.d*|0.d*[1-9]d*)$ //匹配负浮点数
 * ^-?([1-9]d*.d*|0.d*[1-9]d*|0?.0+|0)$ //匹配浮点数 ^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$
 * //匹配非负浮点数（正浮点数 + 0） ^(-([1-9]d*.d*|0.d*[1-9]d*))|0?.0+|0$ //匹配非正浮点数（负浮点数 + 0）
 * 功能： 判断是否为数字 参数： strNumber： 数字字符串 flag： 数字字符串类型 返回： 如果通过验证则返回true,否则返回false
 */
function isNumeric(strNumber, flag) {
	if (isNaN(strNumber)) {
		return false;
	}
	// 正数去掉+号
	switch (flag) {
	case "r":// 实数
		return /^(-¦\+)?\d+(\.\d+)?$/.test(strNumber);
	case "+":// 正数 + 0
		return /^\d+(\.\d+)?$/.test(strNumber);
	case "-":// 负数
		return /^-\d*\.?\d+$/.test(strNumber);
	case "i":// 整数
		return /^-?\d+$/.test(strNumber);
	case "+i":// 正整数
		return /^[0-9]*[1-9][0-9]*$/.test(strNumber);
	case "i+":// 非负整数(正整数 + 0)
		return /^\d+$/.test(strNumber);
	case "-i":// 负整数
		return /^-[0-9]*[1-9][0-9]*$/.test(strNumber);
	case "i-":// 非正整数（负整数 + 0）
		return /^((-\d+)|(0+))$/.test(strNumber);
	case "f":// 浮点数
		return /^(-?\d+)(\.\d+)?$/.test(strNumber);
	case "+f":// 正浮点数
		return /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/
				.test(strNumber);
	case "+f%":// 正浮点数+%
		return /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))%$/
				.test(strNumber);
	case "f+":// 非负浮点数
		return /^\d+(\.\d+)?$/.test(strNumber);
	case "-f":// 负浮点数
		return /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/
				.test(strNumber);
	case "f-":// 非正浮点数
		return /^((-\d+(\.\d+)?)|(0+(\.0+)?))$/.test(strNumber);
	default:// 缺省 (数字)
		return /^[0-9]*$/.test(strNumber);
	}
}

/**
 * 规则： 移动: 前3位 134-139 或者 150-159 一共11位 联通: 前3位 130-133 或者 150-159 一共11位 新增18号段
 * 小灵通: 第一位为0 一共11位 这是最新规则 功能： 检查参数的电话号码格式是否正确（仅手机号） 参数： str： 字符串 返回：
 * 如果通过验证返回true,否则返回false
 */
function isMobile(str) {
	var regu = /(^[1][3][0-9]{9}$)|(^[1][5][0-9]{9}$)|(^[1][8][0-9]{9}$)|(^[0][1-9]{1}[0-9]{9}$)/;
	var reg = new RegExp(regu);
	if (reg.test(str)) {
		return true;
	} else {
		return false;
	}
}

/**
 * 要求： (1)电话号码由数字、"("、")"和"-"构成 (2)电话号码为3到8位 (3)如果电话号码中包含有区号，那么区号为三位或四位
 * (4)区号用"("、")"或"-"和其他部分隔开 (5)移动电话号码为11或12位，如果为12位,那么第一位为0
 * (6)11位移动电话号码的第一位和第二位为"13"或"15" (7)如果包含国际区号2-3位 [0\+]\d{2,3} (8)分机号3-4位
 * 0\d{2,3}
 * 
 * 根据这几条规则，可以与出以下正则表达式：
 * (^[0-9]{3,4}[0-9]{7,8}$)|(^\([0-9]{3,4}\)([0-9]{3,8})(-(\d{3,4}))?$)|(^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,4}))?$)
 * 功能： 检查参数的电话号码格式是否正确(包含手机) 参数： str： 字符串 返回： 如果通过验证返回true,否则返回false
 */
function isPhone(str) {
	var regu = /^\d{1,11}$/;

	var reg = new RegExp(regu);
	if (reg.test(str)) {
		return true;
	} else {
		return false;
	}
}
/**
 * 格式要求 (1)国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(2到5位)
 * /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{2,5}))?$/ (2)手机号
 * /(^0{0,1}13[0-9]{9}$)|(^0{0,1}15[0-9]{9}$)|(^0{0,1}18[0-9]{9}$)/ (2)小灵通
 * /^[0][1-9]{1}[0-9]{9}$/ 功能： 检查参数的电话号码格式是否正确(包含手机) 参数： str： 字符串 返回：
 * 如果通过验证返回true,否则返回false
 */
function isTel(str) {
	var regu = /(^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{2,5}))?$)|(^0{0,1}13[0-9]{9}$)|(^0{0,1}15[0-9]{9}$)|(^0{0,1}18[0-9]{9}$)|(^[0][1-9]{1}[0-9]{9}$)/;
	var reg = new RegExp(regu);
	if (reg.test(str)) {
		return true;
	} else {
		return false;
	}
}

/**
 * 格式化数字
 * 
 * @param number表示要格式化的数
 * @param pattern
 *            格式 alert(formatNumber(0,''));
 *            alert(formatNumber(12432.21,'#,###'));
 *            alert(formatNumber(12432.21,'#,###.000#'));
 *            alert(formatNumber(12432,'#,###.00'));
 *            alert(formatNumber('12432.415','#,###.0#'));
 */
function formatNumber(number, pattern) {
	var str = number.toString();
	var strInt;
	var strFloat;
	var formatInt;
	var formatFloat;
	if (/\./g.test(pattern)) {
		formatInt = pattern.split('.')[0];
		formatFloat = pattern.split('.')[1];
	} else {
		formatInt = pattern;
		formatFloat = null;
	}

	var fix = '0';
	if (formatFloat != null)
		fix = (0).toFixed(formatFloat.length);
	fix = fix.split('.')[1];

	if (/\./g.test(str) || /\./g.exec(str)) {
		if (formatFloat != null) {
			var tempFloat = Math.round(parseFloat('0.' + str.split('.')[1])
					* Math.pow(10, formatFloat.length))
					/ Math.pow(10, formatFloat.length);
			strInt = (Math.floor(number) + Math.floor(tempFloat)).toString();
			strFloat = /\./g.test(tempFloat.toString()) ? tempFloat.toString()
					.split('.')[1] : fix;
		} else {
			strInt = Math.round(number).toString();
			strFloat = '0';
		}
	} else {
		strInt = str;
		strFloat = fix;
	}

	if (formatInt != null) {
		var outputInt = '';
		var zero = formatInt.match(/0*$/)[0].length;
		var comma = null;
		if (/,/g.test(formatInt)) {
			comma = formatInt.match(/,[^,]*/)[0].length - 1;
		}
		var newReg = new RegExp('(\\d{' + comma + '})', 'g');

		if (strInt.length < zero) {
			outputInt = new Array(zero + 1).join('0') + strInt;
			outputInt = outputInt.substr(outputInt.length - zero, zero)
		} else {
			outputInt = strInt;
		}

		var outputInt = outputInt.substr(0, outputInt.length % comma)
				+ outputInt.substring(outputInt.length % comma).replace(newReg,
						(comma != null ? ',' : '') + '$1')
		outputInt = outputInt.replace(/^,/, '');

		strInt = outputInt;
	}

	if (formatFloat != null) {
		var outputFloat = '';
		var zero = formatFloat.match(/^0*/)[0].length;

		if (strFloat.length < zero) {
			outputFloat = strFloat + new Array(zero + 1).join('0');
			// outputFloat = outputFloat.substring(0,formatFloat.length);
			var outputFloat1 = outputFloat.substring(0, zero);
			var outputFloat2 = outputFloat.substring(zero, formatFloat.length);
			outputFloat = outputFloat1 + outputFloat2.replace(/0*$/, '');
		} else {
			outputFloat = strFloat.substring(0, formatFloat.length);
		}

		strFloat = outputFloat;
	} else {
		if (pattern != '' || (pattern == '' && strFloat == '0')) {
			strFloat = '';
		}
	}

	return strInt + (strFloat == '' ? '' : '.' + strFloat);
}

function formatNumber1(number, pattern) {
	var str = number.toString();
	var strInt;
	var strFloat;
	var formatInt;
	var formatFloat;
	if (/\./g.test(pattern)) {
		formatInt = pattern.split('.')[0];
		formatFloat = pattern.split('.')[1];
	} else {
		formatInt = pattern;
		formatFloat = null;
	}

	var fix = '0';
	if (formatFloat != null)
		fix = (0).toFixed(formatFloat.length);
	fix = fix.split('.')[1];

	if (/\./g.test(str) || /\./g.exec(str)) {
		if (formatFloat != null) {
			var tempFloat = Math.round(parseFloat('0.' + str.split('.')[1])
					* Math.pow(10, formatFloat.length))
					/ Math.pow(10, formatFloat.length);
			strInt = (Math.floor(number) + Math.floor(tempFloat)).toString();
			strFloat = /\./g.test(tempFloat.toString()) ? tempFloat.toString()
					.split('.')[1] : fix;
		} else {
			strInt = Math.round(number).toString();
			strFloat = '0';
		}
	} else {
		strInt = str;
		strFloat = fix;
	}

	if (formatInt != null) {
		var outputInt = '';
		var zero = formatInt.match(/0*$/)[0].length;
		var comma = null;
		if (/,/g.test(formatInt)) {
			comma = formatInt.match(/,[^,]*/)[0].length - 1;
		}
		var newReg = new RegExp('(\\d{' + comma + '})', 'g');

		if (strInt.length < zero) {
			outputInt = new Array(zero + 1).join('0') + strInt;
			outputInt = outputInt.substr(outputInt.length - zero, zero)
		} else {
			outputInt = strInt;
		}

		var outputInt = outputInt.substr(0, outputInt.length % comma)
				+ outputInt.substring(outputInt.length % comma).replace(newReg,
						(comma != null ? ',' : '') + '$1')
		outputInt = outputInt.replace(/^,/, '');

		strInt = outputInt;
	}

	if (formatFloat != null) {
		var outputFloat = '';
		var zero = formatFloat.match(/^0*/)[0].length;

		if (strFloat.length < zero) {
			outputFloat = strFloat + new Array(zero + 1).join('0');
			// outputFloat = outputFloat.substring(0,formatFloat.length);
			var outputFloat1 = outputFloat.substring(0, zero);
			var outputFloat2 = outputFloat.substring(zero, formatFloat.length);
			outputFloat = outputFloat1 + outputFloat2.replace(/0*$/, '');
		} else {
			outputFloat = strFloat.substring(0, formatFloat.length);
		}

		strFloat = outputFloat;
	} else {
		if (pattern != '' || (pattern == '' && strFloat == '0')) {
			strFloat = '';
		}
	}

	if (strFloat != '' && parseFloat(strFloat) <= 0)
		strFloat = '';

	return strInt + (strFloat == '' ? '' : '.' + strFloat);
}

function replaceAjaxUrl(strVal) {
	var reVal;
	var strTmp;
	strTmp = strVal + "";
	if (strTmp.length == 0)
		return (strTmp);

	reVal = /%/g;
	strTmp = strTmp.replace(reVal, '%25');
	reVal = /#/g;
	strTmp = strTmp.replace(reVal, '%23');
	reVal = /&/;
	strTmp = strTmp.replace(reVal, '%26');
	reVal = /\+/g;
	strTmp = strTmp.replace(reVal, '%2B');
	reVal = /\\/g;
	strTmp = strTmp.replace(reVal, '%2F');
	reVal = /=/g;
	strTmp = strTmp.replace(reVal, '%3D');
	reVal = /\?/g;
	strTmp = strTmp.replace(reVal, '%3F');

	return strTmp;
}

/**
 * 检查图片类型
 * 
 * @param fileURL
 * @return
 */
function checkImgType(fileURL) {
	var right_type = new Array(".gif", ".jpg", ".jpeg", ".png", ".bmp")
	var right_typeLen = right_type.length;
	var imgUrl = fileURL.toLowerCase();
	var postfixLen = imgUrl.length;
	var len4 = imgUrl.substring(postfixLen - 4, postfixLen);
	var len5 = imgUrl.substring(postfixLen - 5, postfixLen);
	for (i = 0; i < right_typeLen; i++) {
		if ((len4 == right_type[i]) || (len5 == right_type[i])) {
			return true;
		}
	}
	return false;
}

/**
 * 功能： 检查参数对象的值是否符邮政编码格式 参数： str： 参数的字符串 返回： 如果通过验证返回true,否则返回false
 */
function isZip(str) {
	var filter = /^[1-9]\d{5}$/;
	if (!filter.test(str))
		return false;
	return true;
}

/**
 * 功能： 获取字符串的长度 参数： strVal： 字符串 返回： 返回字符串的字节长度
 */
function getReaLength(strVal) {
	var tempStr;
	tempStr = strVal.replace(/(^\s*)|(\s*$)/g, '');

	return tempStr.replace(/[^\x00-\xff]/g, "**").length;
}

/**
 * 功能： 判断密码是否是字母 数字等组成并且6-20位 参数： str： 参数的字符串 返回： 如果通过验证返回true,否则返回false
 */
function isPassword(str) {
	var filter = /^([0-9a-zA-Z]){6,20}$/;
	if (!filter.exec(str))
		return false;
	return true;
}

/**
 * 功能： 判断用户名是否是字母 数字 . _等组成并且6-20位 参数： str： 参数的字符串 返回： 如果通过验证返回true,否则返回false
 */
function isUsername(str) {
	var filter = /^\s*[.A-Za-z0-9_-]{6,20}\s*$/;
	var filter = /^[a-zA-Z]+\s*[.A-Za-z0-9_-]{5,20}\s*$/;
	if (!filter.test(str))
		return false;
	return true;
}

/**
 * 功能： 检查参数字符串是否由字母和数字组成 参数： str： 字符串 返回： 如果通过验证返回true,否则返回false
 */
function isNumbAndLett(str) {
	// var reg=/^([a-z]+(?=[0-9])|[0-9]+(?=[a-z]))[a-z0-9]+$/ig;

	var reg = /^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*$/i;

	return reg.test(str);
}

/**
 * 功能： 判断字符串是否排列有序的字符 参数： str： 参数的字符串 返回： 如果通过验证返回true,否则返回false
 */
function isSequence(str) {
	var intcount = 0;
	var inttemp = 0;
	for (i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) - inttemp == 1) {
			intcount = intcount + 1;
		} else {
			intcount = 1;
		}
		inttemp = str.charCodeAt(i);
		if (intcount >= 3) {
			return true;
		}
	}
	return false;
}

/**
 * 功能： 判断字符串是否有3个以上的连续相同字符 参数： str： 参数的字符串 返回： 如果通过验证返回true,否则返回false
 */
function isSameChar(str) {
	// 检验是否有三个相同的字符
	for (i = 0; i < str.length - 2; i++) {
		if (str.substr(i, 1) == str.substr(i + 1, 1)
				&& str.substr(i, 1) == str.substr(i + 2, 1)) {
			return true;
		}
	}
	return false;
}

/**
 * 功能： 刷新校验码 参数： imgId： 校验码图片ID 返回：
 */
function changeValidateKey(imgId) {
	var changeURL = "";
	changeURL = eval(imgId).src + '&r=' + Math.random();
	eval(imgId).src = changeURL;
	// eval(imgId).setAttribute('src',changeURL);
	// setTimeout(function(){eval(imgId).src=changeURL; } ,20);
}

/** ********************添加遮罩层及loading页面**************** */
/**
 * 页面模态框效果层控制
 */
function showDiv() {
	var doc = document;
	var ddiv1 = doc.getElementById("div1");
	var scrollTop = doc.documentElement.scrollTop || doc.body.scrollTop;
	var scrollLeft = doc.documentElement.scrollLeft || doc.body.scrollLeft;
	if (ddiv1 != null && ddiv1 != undefined) {
		ddiv1.style.display = "inline";// 设置层1显示
		ddiv1.style.width = window.innerWidth
				|| doc.documentElement.clientWidth || doc.body.clientWidth;// 设置层1宽度
		ddiv1.style.height = window.innerHeight
				|| doc.documentElement.clientHeight || doc.body.clientHeight;// 设置层1高度
		ddiv1.style.top = scrollTop;
		ddiv1.style.left = scrollLeft;
	}
	var ddiv2 = doc.getElementById("div2");
	if (ddiv2 != null && ddiv2 != undefined) {
		ddiv2.style.display = "inline";// 设置层2的显示
		ddiv2.style.top = window.top.document.body.clientHeight / 2
				- ddiv2.clientHeight / 2
				- (window.screenTop - window.top.screenTop) + scrollTop;// 设置层2的距顶位置居中算法
		ddiv2.style.left = window.top.document.body.clientWidth / 2
				- ddiv2.clientWidth / 2
				- (window.screenLeft - window.top.screenLeft) + scrollLeft;// 设置层2的距左位置居中算法
	}
}

function resizeDiv(frames) {
	if (frames != null && frames != undefined) {
		for ( var i = 0; i < frames.length; i++) {
			var doc = frames[i].document;
			var scrollTop = doc.documentElement.scrollTop || doc.body.scrollTop;
			var scrollLeft = doc.documentElement.scrollLeft
					|| doc.body.scrollLeft;
			var ddiv1 = doc.getElementById("div1");
			if (ddiv1 != null && ddiv1 != undefined
					&& ddiv1.style.display != "none") {
				ddiv1.style.width = window.innerWidth
						|| doc.documentElement.clientWidth
						|| doc.body.clientWidth;// 设置层1宽度
				ddiv1.style.height = window.innerHeight
						|| doc.documentElement.clientHeight
						|| doc.body.clientHeight;// 设置层1高度
				ddiv1.style.top = scrollTop;
				ddiv1.style.left = scrollLeft;
			}
			resizeDiv(frames[i]);
		}
	}
}
/**
 * 关闭当前frame页面遮罩层
 */
function closeShow() {
	var doc = document;
	var ddiv1 = doc.getElementById("div1");
	if (ddiv1 != null && ddiv1 != undefined) {
		ddiv1.style.display = "none";
	}
	var ddiv2 = doc.getElementById("div2");
	if (ddiv2 != null && ddiv2 != undefined) {
		ddiv2.style.display = "none";
	}
}
/**
 * ajax模态层覆盖主页面方式提交表单
 * 
 * @param pform
 *            表单
 * @param callbackFuc
 *            回调函数
 */
function ajaxModuleSubmit(pform, callbackFunc) {
	// 默认调用ajaxCommonCallback函数
	if (callbackFunc == "") {
		callbackFunc = "ajaxCommonCallback";
	}
	$.ajaxSetup({
		async : true
	});
	// 默认调用show函数覆盖主页面
	showDiv();
	ajaxCommonSubmit(pform, callbackFunc);
}
/**
 * ajax普通方式提交表单
 * 
 * @param pform
 * @param callbackFunc
 */
function ajaxCommonSubmit(pform, callbackFunc, adr ) {
	if (callbackFunc == "") {
		alert("回调函数不能为空");
		return;
	}
	var params = $('#' + pform).serialize();
	$.ajax({
		type : "post",// 使用get方法访问后台
		url : adr,// 要访问的后台地址
		dataType : "text",
		data : params,
		success : function(msg) {
			window[callbackFunc](msg);
			$.ajaxSetup({
				async : false
			});
		}
	});
}
/**
 * ajax普通方式提交表单json格式
 * 
 * @param pform
 * @param callbackFunc
 */
function ajaxCommonSubmitJson(pform, callbackFunc) {
	if (callbackFunc == "") {
		alert("回调函数不能为空");
		return;
	}
	var params = $('#' + pform).serialize();
	$.ajax({
		type : "post",// 使用get方法访问后台
		url : "/bsve/proxy/proxy.action",// 要访问的后台地址
		dataType : "json",
		data : params,
		success : function(msg) {
			window[callbackFunc](msg);
			$.ajaxSetup({
				async : false
			});
		}
	});
}
/**
 * ajax默认回调函数
 * 
 * @param msg
 */
function ajaxCommonCallback(msg) {
	$('#disdiv').html(msg);
	closeShow();
}

/**
 * 页面遮罩层及弹窗层添加
 * 
 */
function divCheck() {
	var doc = document;
	if (doc.getElementById("div1") == null) {
		var div1 = doc.createElement("div");
		div1.id = "div1";
		div1.className = "div1";
		doc.body.appendChild(div1);
	}
	if (doc.getElementById("div2") == null) {
		var div2 = doc.createElement("div");
		div2.id = "div2";
		div2.className = "div2";
		div2.innerHTML = '<img alt="" src="/bsve/images/common/loading.gif" id="kaptchaImage" /><p><b>交易中...</b></p>';
		doc.body.appendChild(div2);
	}
	if (doc.getElementById("disdiv") == null) {
		var disdiv = doc.createElement("div");
		disdiv.id = "disdiv";
		disdiv.className = "disdiv";
		doc.body.appendChild(disdiv);
	}
	if (doc.getElementById("errButton") == null) {
		var errButton = doc.createElement("input");
		errButton.id = "errButton";
		errButton.className = "thickbox";
		errButton.alt = '#TB_inline?height=300&amp;width=700&amp;inlineId=disdiv';
		errButton.title = '错误信息';
		errButton.type = 'button';
		errButton.value = '显示错误信息';
		doc.body.appendChild(errButton);
		tb_init('input.thickbox');
	}
	doc.getElementById('errButton').style.display = "none";
}

/**
 * 显示全屏遮罩层
 * 
 * @param frames
 * @param cssRef
 * @param comjsRef
 */
function showBigOverLay(frames, cssRef, comjsRef) {
	if (frames != null && frames != undefined) {
		for ( var i = 0; i < frames.length; i++) {
			if (frames[i].document.getElementById("div1") == null) {
				var div1 = frames[i].document.createElement("div");
				div1.id = "div1";
				div1.className = "div1";
				frames[i].document.body.appendChild(div1);
			}
			if (frames[i].document.getElementById("overLayFun") == null) {
				var showFun = frames[i].document.createElement("script");
				showFun.id = 'overLayFun';
				showFun.text = 'function show(){showDiv();}function close(){closeShow();}';
				var linkRel = frames[i].document.createElement("link");
				linkRel.rel = "stylesheet";
				linkRel.type = "text/css";
				linkRel.href = cssRef;
				var scriptSrc = frames[i].document.createElement("script");
				scriptSrc.type = "text/javascript";
				scriptSrc.src = comjsRef;
				var head = frames[i].document.getElementsByTagName("head")[0];
				if (head != null && head != undefined) {
					head.appendChild(showFun);
					head.appendChild(linkRel);
					head.appendChild(scriptSrc);
				}
			}
			frames[i].show();
			showBigOverLay(frames[i], cssRef, comjsRef);
		}
	}
}

/**
 * 关闭全屏遮罩层
 * 
 * @param frames
 */
function closeBigOverLay(frames) {
	if (frames != null && frames != undefined) {
		for ( var i = 0; i < frames.length; i++) {
			frames[i].close();
			closeBigOverLay(frames[i]);
		}
	}
}

/**
 * 调整遮罩层大小
 */
function changeDivSize() {
	var doc = document;
	var scrollTop = doc.documentElement.scrollTop || doc.body.scrollTop;
	var scrollLeft = doc.documentElement.scrollLeft || doc.body.scrollLeft;
	var ddiv1 = doc.getElementById("div1");
	if (ddiv1 != null && ddiv1 != undefined && ddiv1.style.display != "none") {
		ddiv1.style.width = window.innerWidth
				|| doc.documentElement.clientWidth || doc.body.clientWidth;// 设置层1宽度
		ddiv1.style.height = window.innerHeight
				|| doc.documentElement.clientHeight || doc.body.clientHeight;// 设置层1高度
		ddiv1.style.top = scrollTop;
		ddiv1.style.left = scrollLeft;
	}
	var ddiv2 = doc.getElementById("div2");
	if (ddiv2 != null && ddiv2 != undefined && ddiv2.style.display != "none") {
		ddiv2.style.top = window.top.document.body.clientHeight / 2
				- ddiv2.clientHeight / 2
				- (window.screenTop - window.top.screenTop) + scrollTop;// 设置层2的距顶位置居中算法
		ddiv2.style.left = window.top.document.body.clientWidth / 2
				- ddiv2.clientWidth / 2
				- (window.screenLeft - window.top.screenLeft) + scrollLeft;// 设置层2的距左位置居中算法
	}
}

/**
 * js实现的Map实例
 * 
 */
function Map() {
	var struct = function(key, value) {
		this.key = key;
		this.value = value;
	};

	var put = function(key, value) {
		for ( var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				this.arr[i].value = value;
				return;
			}
		}
		this.arr[this.arr.length] = new struct(key, value);
	};

	var get = function(key) {
		for ( var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				return this.arr[i].value;
			}
		}
		return null;
	};

	var remove = function(key) {
		var v;
		for ( var i = 0; i < this.arr.length; i++) {
			v = this.arr.pop();
			if (v.key === key) {
				continue;
			}
			this.arr.unshift(v);
		}
	};

	var size = function() {
		return this.arr.length;
	};

	var isEmpty = function() {
		return this.arr.length <= 0;
	};
	this.arr = new Array();
	this.get = get;
	this.put = put;
	this.remove = remove;
	this.size = size;
	this.isEmpty = isEmpty;
}

/** ********************************一下为添加提示词下拉菜单功能*********************** */
var map = new Map();
$.ajaxSetup({
	async : false
});

/**
 * 添加提示词下拉列表
 * 
 * @param parameters
 *            输入参数
 */
function addPowerFloat(parameters) {
	if (!parameters.dataType) {
		return;
	}
	if (!parameters.inputId || !document.getElementById(parameters.inputId)) {
		return;
	}
	if (!parameters.formId || !document.getElementById(parameters.formId)) {
		return;
	}

	var sugType = "all", showType = "key", selectType = "key", dataType = parameters.dataType, inputId = parameters.inputId, formId = parameters.formId;
	if (parameters.sugType === "all" || parameters.sugType === "value"
			|| parameters.sugType === "key") {
		sugType = parameters.sugType;
	}
	if (parameters.showType === "value" || parameters.showType === "key") {
		showType = parameters.showType;
	}
	if (parameters.selectType === "value" || parameters.selectType === "key") {
		selectType = parameters.selectType;
	}

	var target = null;
	if (target = map.get(dataType)) {
		window["sugOptionCallBack"](target, inputId, sugType, showType,
				selectType);
	} else {
		var params = $('#' + formId).serialize() + '&sugOption=' + dataType;
		$.post("/bsve/proxy/proxy.action", params, function(msg) {
			target = getTarget(msg);
			map.put(dataType, target);
			window["sugOptionCallBack"](target, inputId, sugType, showType,
					selectType);
		});
	}
}

/**
 * ajax回调方法
 * 
 * @param target
 * @param inputId
 * @param sugType
 * @param showType
 * @param selectType
 */
function sugOptionCallBack(target, inputId, sugType, showType, selectType) {
	$("#" + inputId).powerFloat({
		targetField : inputId,
		eventType : "dblclick",
		width : "inherit",
		target : target,
		targetMode : "list_selectable",
		sugType : sugType,
		showType : showType,
		selectType : selectType
	});
}

/**
 * 将json字符串转换成object对象
 * 
 * @param targetStr
 * @returns
 */
function getTarget(targetStr) {
	if (targetStr == null || targetStr == undefined) {
		return null;
	}
	var target;
	try {
		target = eval('(' + targetStr + ')');
	} catch (e) {
		target = null;
	}
	return target;
}

// ***********帮助信息画面显示************//
/*
 * 入口函数，画面中调用使用
 */
function keydown4Help(formId) {
	var evt = window.event;
	if (evt.keyCode == 117) {// 监听的按键为：F6
		document.onhelp = function() {
			return (false);
		};
		window.onhelp = function() {
			return (false);
		};
		$('#PageId4Help').attr('value', strAbsPath);
		$('#InputId').attr('value', evt.srcElement.id);// 输入框的id必须有
		$('#KeyCode').attr('value', 'F6');
		helpFormSubmit(formId);
	}
}
/*
 * ajax方式调用后台，获取帮助画面URL
 */
function helpFormSubmit(formId) {
	ajaxCommonSubmit(formId, "helperCallBack");// 采用ajax方式调用后台处理
}
/*
 * 新窗口打开帮助画面
 */
function helperCallBack(msg) {
	window.open(msg);
}
/*
 * 增加cookie 
 * @param {Object} name
 * @param {Object} value
 * @param {Object} expiresHours
 */
function addCookie(name,value,expiresHours){ 
var cookieString=name+"="+escape(value); 
//判断是否设置过期时间 
if(expiresHours>0){
	var date=new Date(); 
	date.setTime(date.getTime+expiresHours*3600*1000); 
	cookieString=cookieString+"; expires="+date.toGMTString(); 
} 
document.cookie=cookieString;
}
/*
 * 获取cookie
 * @param {Object} name
 * @return {TypeName} 
 */
function getCookie(name){ 
var strCookie=document.cookie; 
var arrCookie=strCookie.split("; "); 
for(var i=0;i<arrCookie.length;i++){ 
var arr=arrCookie[i].split("="); 
if(arr[0]==name)return arr[1]; 
} 
return ""; 
} 

/*
 * 验证是否为手机号码
 */
function checkPhoneRule(mobile){
	var isMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;  
	if(isMobile.test(mobile)){
		return true;
	}else{
		return false;
	}
}

//----------------------------------------------------------------------
//<summary>
//限制只能输入数字
//</summary>
//----------------------------------------------------------------------
$.fn.onlyNum = function () {
 $(this).keypress(function (event) {
     var eventObj = event || e;
     var keyCode = eventObj.keyCode || eventObj.which;
     if ((keyCode >= 48 && keyCode <= 57))
         return true;
     else
         return false;
 }).focus(function () {
 //禁用输入法
     this.style.imeMode = 'disabled';
 }).bind("paste", function () {
 //获取剪切板的内容
     var clipboard = window.clipboardData.getData("Text");
     if (/^\d+$/.test(clipboard))
         return true;
     else
         return false;
 });
};

//----------------------------------------------------------------------
//<summary>
//限制只能输入字母
//</summary>
//----------------------------------------------------------------------
$.fn.onlyAlpha = function () {
 $(this).keypress(function (event) {
     var eventObj = event || e;
     var keyCode = eventObj.keyCode || eventObj.which;
     if ((keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122))
         return true;
     else
         return false;
 }).focus(function () {
     this.style.imeMode = 'disabled';
 }).bind("paste", function () {
     var clipboard = window.clipboardData.getData("Text");
     if (/^[a-zA-Z]+$/.test(clipboard))
         return true;
     else
         return false;
 });
};

//----------------------------------------------------------------------
//<summary>
//限制只能输入数字和字母
//</summary>
//----------------------------------------------------------------------
$.fn.onlyNumAlpha = function () {
 $(this).keypress(function (event) {
     var eventObj = event || e;
     var keyCode = eventObj.keyCode || eventObj.which;
     if ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122))
         return true;
     else
         return false;
 }).focus(function () {
     this.style.imeMode = 'disabled';
 }).bind("paste", function () {
     var clipboard = window.clipboardData.getData("Text");
     if (/^(\d|[a-zA-Z])+$/.test(clipboard))
         return true;
     else
         return false;
 });
};

$(function () {
    // 限制使用了onlyNum类样式的控件只能输入数字
    $(".onlyNum").onlyNum();
    //限制使用了onlyAlpha类样式的控件只能输入字母
    $(".onlyAlpha").onlyAlpha();
    // 限制使用了onlyNumAlpha类样式的控件只能输入数字和字母
    $(".onlyNumAlpha").onlyNumAlpha();
});

//发送手机验证码控制
var waitinit = 3;
var wait = waitinit;
function time(btn,w1,w2) {
    if (wait == 0) {
    	 btn.attr("disabled",false);
    	 btn.val("免费获取验证码");
    	 btn.css("width",w1);
    	 wait = waitinit;
    } else {
    	btn.attr("disabled",true);
    	btn.css("width",w2);
        btn.val("已发送，"+wait + "秒后重新获取验证码");
        wait--;
        setTimeout(function () { time(btn,w1,w2);
        },
        1000)
    }
}
//首页URL
var indexUrl1 = '/p2pstock/init/index.action';
//菜单处理导航链
function menuclick(to,action,menunav){
	$(to).attr('href',action);
}


function pageClick( pNo,formstr,subtype){
	if(subtype == "ajax"){
		$('#pageflag').val('1');
		$('#pageNo').val(pNo);
		ajaxCommonSubmit(formstr, 'pagesubok',$('#'+formstr).attr("action"));
	}else{
		$('#pageflag').val('1');
		$('#pageNo').val(pNo);
		$('#'+formstr).submit();
	}	
}

function nvlnum(str){
	if(str == ''){
		return 0;
	}
}

//制保留2位小数，如：2，会在2后面补上00.即2.00   
function toDecimal2(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return false;  
    }  
    var f = Math.round(x*100)/100;  
    var s = f.toString();  
    var rs = s.indexOf('.');  
    if (rs < 0) {  
        rs = s.length;  
        s += '.';  
    }  
    while (s.length <= rs + 2) {  
        s += '0';  
    }  
    return s;  
}  
//日期比较
function dateCompare(date1,date2){	
	date1 = date1.replace(/\-/gi,"/");	
	date2 = date2.replace(/\-/gi,"/");	
	var time1 = new Date(date1).getTime();	
	var time2 = new Date(date2).getTime();	
	if(time1 > time2){
		return 1;
	}else if(time1 == time2){		
		return 2;
	}else{
		return 3;
	}
}
//判断深市沪市
function judgemarket(marketno){
	//6或7开头的为沪市、3或0开头为深市
	if(marketno.indexOf('6') == 0 || marketno.indexOf('7') == 0){
		return '1';
	}else if(marketno.indexOf('3') == 0 || marketno.indexOf('0') == 0){
		return '0';
	}else{
		return '-1';
	}
}
//获取数组中最小的日期
function findmindate(datearry){
	var darray = datearry.split(",");
	var datemin = "9999-12-12";
	for(i=0;i<darray.length-1;i++){
		if(dateCompare(darray[i],datemin) == 3){
			datemin = darray[i];
		}
	}
	return datemin;
}
function findmaxdate(datearray){
	var darray = datearray.split(",");
	var datemax = "1000-12-12";
	for(i=0;i<darray.length-1;i++){
		if(dateCompare(darray[i],datemax) == 1){
			datemax = darray[i];
		}
	}
	return datemax;
}

//删除字符串中第一个指定串
function strremove(str1,str2){
	var al = str1.length;
	var l = str2.length;
	var ps = str1.indexOf(str2);
	var pe = ps +l;
	var prevstr =  str1.substring(0,ps);
	var endstr =   str1.substring(pe,al);
	return prevstr + endstr;
}
//删除指定位置的逗号之前的串
function strremovebyindex(str,index,fmt){
	if(fmt == ''){
		fmt = ',';
	}
	var strarray = str.split(fmt);
	var returnstr = "";
	for(i=0;i<strarray.length - 1;i++){
		if(i != index){
			returnstr = returnstr + strarray[i] + fmt;
		}
	}
	return returnstr;
}
//判断指定值是第几个
function strindex(str,indexstr,fmt){
	var strarray = str.split(",");
	for(i=0;i<strarray.length - 1;i++){
		if(strarray[i] == indexstr){
			return i;
		}
	}
}
//获取所有的数字串
function getallnumstr(str){
	var strarray = str.split(":");
	if(strarray.length == 1){
		return str.replaceAll("，", ",");
	}
	var rstr = "";
	for(i=0;i<strarray.length;i++){
		var reg1 = /[1-9]/g;
		var tmp = strarray[i].match(reg1);
		if(tmp != null){
			var array2 = strarray[i].split(";");
			for(m=0;m<array2.length - 1;m++){
				var reg2 = /[1-9]/g;
				var tmp2 = array2[m].match(reg2);
				if(tmp2!=null){
					rstr = rstr + array2[m] + ",";
				}
			}
		}
	}
	return rstr.replaceAll("，", ",");
	
}

function CheckIntensity(pwd,pwd_Weak,pwd_Medium,pwd_Strong){ 
	  var Mcolor,Wcolor,Scolor,Color_Html; 
	  var m=0; 
	  var Modes=0;
	  for(i=0; i<pwd.length; i++){ 
	    var charType=0; 
	    var t=pwd.charCodeAt(i); 
	    if(t>=48 && t <=57){charType=1;} //数字
	    else if(t>=65 && t <=90){charType=3;} //大写字母
	    else if(t>=97 && t <=122){charType=2;} //小写字母
	    else{charType=4;} 
	    Modes |= charType; 
	  } 
	  for(i=0;i<4;i++){ 
	  if(Modes & 1){m++;} 
	      Modes>>>=1; 
	  }
	  if(pwd.length<=4){m=1;} 
	  if(pwd.length<=0){m=0;} 
	  switch(m){ 
	    case 1 : 
	      Wcolor="pwd pwd_Weak_c"; 
	      Mcolor="pwd pwd_c"; 
	      Scolor="pwd pwd_c pwd_c_r"; 
	      Color_Html="弱"; 
	    break; 
	    case 2 : 
	      Wcolor="pwd pwd_Medium_c"; 
	      Mcolor="pwd pwd_Medium_c"; 
	      Scolor="pwd pwd_c pwd_c_r"; 
	      Color_Html="中"; 
	    break; 
	    case 3 : 
	      Wcolor="pwd pwd_Strong_c"; 
	      Mcolor="pwd pwd_Strong_c"; 
	      Scolor="pwd pwd_Strong_c pwd_Strong_c_r"; 
	      Color_Html="强"; 
	    break; 
	    default : 
	      Wcolor="pwd pwd_c"; 
	      Mcolor="pwd pwd_c pwd_f"; 
	      Scolor="pwd pwd_c pwd_c_r"; 
	      Color_Html="无"; 
	    break; 
	  } 
	  $('#'+pwd_Weak).attr('class',Wcolor);
	  $('#'+pwd_Medium).attr('class',Mcolor);
	  $('#'+pwd_Strong).attr('class',Scolor);
	  $('#'+pwd_Medium).html(Color_Html);
	} 

//增加身份证验证
function isIdCardNo(num) {
    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    // initialize
    if ((intStrLen != 15) && (intStrLen != 18)) {
        return false;
    }
    // check and set value
    for (i = 0; i < intStrLen; i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }
    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6, 14);
        if (isDate8(date8) == false) {
            return false;
        }
        // calculate the sum of the products
        for (i = 0; i < 17; i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = parityBit[lngProduct % 11];
        // check last digit
        if (varArray[17] != intCheckDigit) {
            return false;
        }
    }
    else {        //length is 15
        //check date
        var date6 = idNumber.substring(6, 12);
        if (isDate6(date6) == false) {
            return false;
        }
    }
    return true;
}

function isDate6(sDate) {
    if (!/^[0-9]{6}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    if (year < 1700 || year > 2500) return false
    if (month < 1 || month > 12) return false
    return true
}

function isDate8(sDate) {
    if (!/^[0-9]{8}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    day = sDate.substring(6, 8);
    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if (year < 1700 || year > 2500) return false
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
    if (month < 1 || month > 12) return false
    if (day < 1 || day > iaMonthDays[month - 1]) return false
    return true
}
function selectSubTreeAll(obj) {
	$(obj).next().next().find(':checkbox').each(function () {
		if($(obj).attr('checked') == 'checked'){
			$(this).attr('checked','checked');
		}else{
			$(this).removeAttr('checked');
		}
	 });
}

function selectSubMenu(obj,n){
	var sobj = obj;
	for(i = 1;i<n;i++){
		if($(sobj).attr('checked') == 'checked'){
			sobj = $(sobj).parent().parent().prev().prev();
			$(sobj).attr('checked','checked');
		}
	}
}
function getMenuTreeVal(objid){
	var menucodeary = '';
	$('#'+objid).find(':checkbox').each(function () {
		menucodeary = menucodeary + $(this).val() + ",";
	 });
	return menucodeary;
}
function getMenuTreeCheckedVal(objid){
	var menucodeary = '';
	$('#'+objid).find(':checkbox').each(function () {
		if($(this).attr('checked') == 'checked'){
			menucodeary = menucodeary + $(this).val() + ",";
		}
	 });
	return menucodeary;
}
function setMenuTreeCheckedDisb(objid){
	$('#'+objid).find(':checkbox').each(function () {
		$(this).attr('disabled','true')
	 });
}