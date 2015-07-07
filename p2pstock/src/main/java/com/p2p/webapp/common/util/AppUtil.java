package com.p2p.webapp.common.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.p2p.webapp.common.constant.Constant;

public class AppUtil {
	
	/**
	 * 随机生成手机验证码
	 * @param flag 是否为纯数字 0是 1否
	 * @param length 生成验证码的位数
	 * @return
	 */
	public String createRandom(boolean flag,int length){
		if("1".equals(Constant.getCptnoflag())){
			return "111111";
		}
		String retStr = "";
		String strTable = flag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";   
		int len = strTable.length();   
		boolean bDone = true;   
		do
		{
			retStr = "";    
			int count = 0;    
			for (int i = 0; i < length; i++){
				double dblR = Math.random() * len;     
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} 
		while (bDone);
		return retStr;
	}
	/*
	 * 获取请求Ip
	 */
	public static String getIpAddr(HttpServletRequest request) {     
		String ip = request.getHeader("x-forwarded-for");     
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}     
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}     
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}   
		return ip;
	}
	/*
	 * 字符串nvl
	 */
	public static String nvlStr(String str){
		if(str == null){
			return "";
		}else{
			return str;
		}
	}
	/*
	 * 数值nvl
	 */
	public static String nvlNum(String str){
		if(str == null || "".equals(str)){
			return "0";
		}else{
			return str;
		}
	}
	public static String jugeCpt(Map session,String inputCpt){
		
		String msgCode = "";
		//验证手机验证码
		String cptno = (String)session.get("cptno");
		String activtime =  (String)session.get("activetime");
		if(cptno == null || "".equals(cptno)||!cptno.equals(inputCpt)){
			msgCode = "ERR0003";
			return msgCode;
		}
		if(activtime == null || "".equals(activtime)){
			msgCode = "ERR0003";
			return msgCode;
		}
		//验证手机验证码有效期
		Date activedate = DateTimeFormatUtil.covertStringToDate(activtime,DateTimeFormatUtil.YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		Date nowdate = DateTimeFormatUtil.getCurrentDate();
		if(DateTimeFormatUtil.compareDateByDate(nowdate, activedate)){
			msgCode = "ERR0004";
		}
		return msgCode;
	}
	//四舍五入小数点后保留num位小数
	public static String numFormat(String f,int num){
		
		if(f == null || "".equals(f)){
			if(num == 0){
				return "0";
			}else{
				String zerofmt = "0.";
				for(int i=0;i<num;i++){
					zerofmt = zerofmt +"0";
				}
				return zerofmt;
			}
		}
		if(Double.parseDouble(f) == 0.0){
			if(num == 0){
				return "0";
			}else{
				String zerofmt = "0.";
				for(int i=0;i<num;i++){
					zerofmt = zerofmt +"0";
				}
				return zerofmt;
			}
		}
		BigDecimal   b   =   new   BigDecimal(f);  
		BigDecimal   f1   =   b.setScale(num,BigDecimal.ROUND_HALF_EVEN);
		return String.valueOf(f1);
	}
	//判断是否中签
	public static boolean judgeLov(String startno,String lovno){
		//尾号长度
		int llength = lovno.length();
		//投资签号长度
		int slength = startno.length();
		
		if(slength < llength){
			return false;
		}
		//满足尾号与中签尾号相同
		if(lovno.equals(startno.substring(slength-llength))){
			return true;
		}
		return false;
	}
	/*
	 * 返回万为单位
	 */
	public static String numfor4(String num){
		
		if(num == null || "".equals(num)){
			return "0.00";
		}
		return numFormat(String.valueOf(Double.parseDouble(num)/10000.d), 2);
	}
	/*
	 * 使用*模糊处理敏感信息
	 * str 目标字符串
	 * flag 0代表前保留 1代表后保留
	 * num保留位数
	 */
	public static String fmtStr(String str,String flag,int num){
		if(str == null || "".equals(str)){
			return "*";
		}
		int l = str.length();
		if(l<num){
			return "*";
		}
		String returnstr = "";
		//保留前两位，其他使用*代替
		if("0".equals(flag)){
			returnstr = str.substring(0,num);
			for(int i=0;i<l-num;i++){
				returnstr = returnstr + "*";
			}
		}
		//保留后两位
		if("1".equals(flag)){
			returnstr = str.substring(l-num,l);
			for(int i=0;i<l-num;i++){
				returnstr = "*" + returnstr;
			}
		}
		return returnstr;
	}
	/*
	 * 金钱格式化 +
	 */
	public static String fmtAcount(String amount){
		String returnstr = "";
		if(Double.parseDouble(amount) > 0){
			returnstr = "+" +AppUtil.numFormat( amount,2);
		}else{
			returnstr = AppUtil.numFormat( amount,2);
		}
		return returnstr;
	}
	
	
}
