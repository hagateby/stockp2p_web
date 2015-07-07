/*
* DateTimeFormatUtil
* Version 1.0
* 2012/3/12
*/

package com.p2p.webapp.common.util;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;

/**
 * 时间相关操作工具类
 * @version  2012-03-12
 * @see      DateTimeFormatUtil
 * @author   songchengsong-ghq
 * @since    2012-03-12
 */
public class DateTimeFormatUtil {

	public static final String YEAR_MONTH_DAY_TEMPLATE = "yyyy-MM-dd";
	public static final String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_TEMPLATE = "yyyy-MM-dd hh:mm:ss";
	public static final String YEAR_MONTH_DAY_HOUR_MINUTE_TEMPLATE = "yyyy-MM-dd hh:mm";
	public static final String YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE = "yyyy-MM-dd HH:mm:ss";
	public static final String YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE_2 = "yyyyMMddHHmmss";
	private static final Logger logger = LoggerFactory
			.getLogger(DateTimeFormatUtil.class);

	/**
	 * 转换日期时间格式，将Date型转换为String型
	 * 
	 * @param date
	 *            要转换的日期时间 Date型
	 * @param format
	 *            使用的日期时间格式
	 * @return 转换后的日期时间格式 String类型
	 */
	public static String covertDateToString(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 将格林尼治时间转化为timezone时间字符串
	 * 
	 * @param date
	 *            格林尼治时间
	 * @param format
	 *            时间格式
	 * @param timeZoneId
	 *            时区信息
	 * @return
	 */
	public static String covertGMTDateToString(Date date, String format,
			String timeZoneId) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(toTimeZoneDate(date, timeZoneId));
	}

	/**
	 * 将格林尼治时间转化为timezone时间字符串
	 * 
	 * @param date
	 *            格林尼治时间
	 * @param format
	 *            时间格式
	 * @param timeZoneId
	 *            时区信息
	 * @return
	 */
	public static String defaultCovertGMTDateToString(Date date,
			String timeZoneId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(toTimeZoneDate(date, timeZoneId));
	}

	/**
	 * 转换日期时间格式，将String型转换为Date型
	 * 
	 * @param date
	 *            要转换的日期时间 String型
	 * @param format
	 *            使用的日期时间格式
	 * @return 转换后的日期时间格式 Date型
	 * @throws FrameworkException
	 */
	public static Date covertStringToDate(String date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date returnDate = new Date();
		try {
			returnDate = sdf.parse(date);
			return returnDate;
		} catch (ParseException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.toString());
			}
			return returnDate;
		}
	}

	/**
	 * 将时间字符串转化为GMT时间
	 * 
	 * @param date
	 *            时间字符串
	 * @param format
	 *            时间格式
	 * @param timeZoneId
	 *            时区信息
	 * @return 对应的格林尼治时间
	 * @throws FrameworkException
	 */
	public static Date covertStringToGMTDate(String date, String format,
			String timeZoneId) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date returnDate = new Date();
		try {
			returnDate = sdf.parse(date);
			return toGMTDate(returnDate, timeZoneId);
		} catch (ParseException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.toString());
			}
			return returnDate;
		}
	}

	/**
	 * 如果时间格式是yyyy-MM-dd hh:mm:ss，可以直接使用该方法，将时间字符串转化为GMT时间
	 * 
	 * @param date
	 *            时间字符串
	 * @param timeZoneId
	 *            时区信息
	 * @return 对应的格林尼治时间
	 * @throws FrameworkException
	 */
	public static Date defaultCovertStringToGMTDate(String date,
			String timeZoneId){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date returnDate = new Date();
		try {
			returnDate = sdf.parse(date);
			return toGMTDate(returnDate, timeZoneId);
		} catch (ParseException e) {
			return returnDate;
		}
	}

	/**
	 * 得到当前服务器的时间
	 * 
	 * @return 当前服务器时间
	 */
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * 设置Date型到Calendar里，通过Calendar得到所需要的日期格式
	 * 
	 * @param date
	 */
	public static void setDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	}

	/**
	 * 比较两个Date型时间的函数
	 * 
	 * @param firstDate
	 *            第一时间
	 * @param secondDate
	 *            第二时间
	 * @return 结果为true，则第一时间比第二时间大；反之为小
	 */
	public static String compareByDate(Date firstDate, Date secondDate) {
		if (firstDate.getTime() == secondDate.getTime()) {
			return "0";
		}else if(firstDate.getTime() < secondDate.getTime()){
			return "1";
		}else{
			return "2";
		}

	}
	/**
	 * 比较两个Date型时间的函数
	 * 
	 * @param firstDate
	 *            第一时间
	 * @param secondDate
	 *            第二时间
	 * @return 结果为true，则第一时间比第二时间大；反之为小
	 */
	public static boolean compareDateByDate(Date firstDate, Date secondDate) {
		if (firstDate.getTime() > secondDate.getTime()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 比较两个String型的时间函数
	 * 
	 * @param firstDate
	 *            第一时间
	 * @param secondDate
	 *            第二时间
	 * @param format
	 *            时间格式
	 * @return 结果为true，则第一时间比第二时间大；反之为小
	 * @throws FrameworkException
	 */
	public static boolean compareDateByString(String firstDate,
			String secondDate, String format){
		Date date1 = covertStringToDate(firstDate, format);
		Date date2 = covertStringToDate(secondDate, format);
		return compareDateByDate(date1, date2);
	}

	/**
	 * 验证时间格式是否正确
	 * 
	 * @param dateString
	 *            时间格式字符串
	 * @param splitChar
	 *            时间分隔符
	 * @return 时间格式是否正确
	 * @throws FrameworkException
	 */
	public static boolean validateDateFormate(String dateString,
			String splitChar){
		/*
		 * 检测字符串的长度，一定在允许的范围内
		 */
		if (dateString.length() < 1 || dateString.length() > 10) {
			return false;
		}
		/*
		 * 检验给定格式的时间字符串,是否含有标准字符
		 */
		if (!validateDateStr(dateString, splitChar)) {
			return false;
		}

		String[] datestr = dateString.split(splitChar);
		/*
		 * 只能含有两个时间分隔符
		 */
		if (datestr.length > 3) {
			return false;
		}

		int year = Integer.parseInt(datestr[0]);
		int month = Integer.parseInt(datestr[1]);
		int day = Integer.parseInt(datestr[2]);

		if (year < 1900)
			return false;
		if (month < 0 || month > 12)
			return false;
		if (day < 0
				|| day > 31
				&& (month == 1 || month == 3 || month == 5 || month == 7
						|| month == 8 || month == 10 || month == 12))
			return false;
		if (day < 0 || day > 30
				&& (month == 4 || month == 6 || month == 9 || month == 11))
			return false;

		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			if (month == 2 && (day < 0 || day > 29))
				return false;
			// System.out.println("是闰年");
		} else {
			if (month == 2 && (day < 0 || day > 28))
				return false;
			// System.out.println("不是闰年");
		}
		return true;
	}

	/**
	 * 检验给定格式的时间字符串,是否含有标准字符
	 * 
	 * @param dateString
	 *            时间或日期字符串
	 * @param splitChar
	 *            分隔符
	 * @return 判断结果
	 * @throws FrameworkException
	 */
	public static boolean validateDateStr(String dateString, String splitChar)
			{

		List<String> formateChar = new LinkedList<String>();
		for (int i = 0; i < 10; i++) {
			formateChar.add(String.valueOf(i));
		}
		formateChar.add(splitChar);
		for (int i = 0; i < dateString.length(); i++) {
			if (!formateChar.contains(String.valueOf(dateString.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查日期时间格式是否符合规定格式
	 * 
	 * @param dateTimeString
	 *            日期时间格式字符串
	 * @param timeDateChar
	 *            日期时间分隔符
	 * @param dateSplitChar
	 *            日期分隔符
	 * @param timeSplitChar
	 *            时间分隔符
	 * @return 判断结果
	 * @throws FrameworkException
	 */
	public static boolean validateDateTimeFormate(String dateTimeString,
			String timeDateChar, String dateSplitChar, String timeSplitChar)
			{

		dateTimeString = makeupDateTimeStr(dateTimeString, dateSplitChar);

		if (dateTimeString.length() > 16) {
			return false;
		}

		String[] datetimeStr = dateTimeString.split(timeDateChar);

		if (datetimeStr.length > 2) {
			return false;
		}
		if ((validateDateFormate(datetimeStr[0], dateSplitChar))
				&& (validateTimeFormate(datetimeStr[1], timeSplitChar))) {
			return true;
		} else {
			return false;
		}
	}

	public static String makeupDateTimeStr(String dateTimeString,
			String dateSplitChar){
		/*
		 * 如果dateTime的格式不包含时间,如2005-12-23
		 */
		if (dateTimeString.length() > 0 && dateTimeString.length() < 11
				&& validateDateStr(dateTimeString, dateSplitChar)) {
			dateTimeString = dateTimeString.trim() + " 00:00";
		}
		return dateTimeString;
	}

	/**
	 * 默认日期时间分隔符
	 * 
	 * @param dateTimeString
	 *            日期时间格式字符串
	 * @return 判断结果
	 * @throws FrameworkException
	 */
	public static boolean defaultValidateDateTimeFormate(String dateTimeString)
			{
		return validateDateTimeFormate(dateTimeString, " ", "-", ":");
	}

	/**
	 * 检查时间格式是否符合要求
	 * 
	 * @param timeString
	 *            时间格式字符串
	 * @param splitChar
	 *            分隔符
	 * @return 判断结果
	 * @throws FrameworkException
	 */
	public static boolean validateTimeFormate(String timeString,
			String splitChar){
		/*
		 * 检测字符串的长度，一定在允许的范围内
		 */
		if (timeString.length() < 1 || timeString.length() > 5) {
			return false;
		}

		if (!validateDateStr(timeString, splitChar)) {
			return false;
		}

		String[] timestr = timeString.split(splitChar);

		if (timestr.length > 2) {
			return false;
		}

		int hourStr = Integer.parseInt(timestr[0]);
		int secondStr = Integer.parseInt(timestr[1]);

		if (hourStr > 23 || hourStr < 0) {
			return false;
		}

		if (secondStr > 59 || secondStr < 0) {
			return false;
		}

		return true;
	}

	/**
	 * 为当前的时间增加的秒数
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param addSeconds
	 *            增加的秒数
	 * @return 增加秒后的时间
	 */
	public static Date addTimeBySeconds(Date currentDate, int addSeconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentDate.getTime());
		calendar.add(Calendar.SECOND, addSeconds);
		return calendar.getTime();
	}

	/**
	 * 为当前的时间增加天数
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param day
	 *            增加的天数
	 * @return 增加天数后的时间
	 */
	public static Date addTimeByDays(Date currentDate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentDate.getTime());
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 为当前的时间增加小时
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param Hours
	 *            增加的小时
	 * @return 增加小时后的时间
	 */
	public static Date addTimeByHours(Date currentDate, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentDate.getTime());
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTime();
	}

	/**
	 * 为当前时间增加月数
	 * 
	 * @param currentDate
	 * @param months
	 * @return
	 */
	public static Date addTimeByMonths(Date currentDate, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentDate.getTime());
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * 为当前时间增加年
	 * 
	 * @param currentDate
	 * @param months
	 * @return
	 */
	public static Date addTimeByYears(Date currentDate, int years) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentDate.getTime());
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}

	/**
	 * 为当前时间增加月数
	 * 
	 * @param currentDate
	 * @param months
	 * @return
	 */
	public static Date addTimeByMonths(Date currentDate, int months, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentDate.getTime());
		calendar.add(Calendar.MONTH, months);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 为当前的时间减少的秒数
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param seconds
	 *            减少的秒数
	 * @return 减少秒后的时间
	 */
	public static Date subtractTimeBySeconds(Date currentDate, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentDate.getTime());
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) - seconds);
		return calendar.getTime();
	}

	/**
	 * 为当前的时间减少月数
	 * 
	 * @param currentDate
	 * @param months
	 * @return
	 */
	public static Date subtractTimeByMonth(Date currentDate, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentDate.getTime());
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - months);
		return calendar.getTime();
	}

	/**
	 * 为当前的时间减少天数
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param days
	 *            减少的天数
	 * @return 减少秒后的时间
	 */
	public static Date subtractTimeByDays(Date currentDate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentDate.getTime());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - days);
		return calendar.getTime();
	}

	/**
	 * 为当前的时间减少小时
	 * 
	 * @param currentDate
	 *            当前时间
	 * @param Hours
	 *            减少的小时
	 * @return 减少小时后的时间
	 */
	public static Date subtractTimeByHours(Date currentDate, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentDate.getTime());
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - hours);
		return calendar.getTime();
	}

	/**
	 * 获取当前的格林威治时间（GMT时间）
	 * 
	 * @return 返回当前主机的格林威治时间
	 */
	public static Date getCurrentGMTDate() {
		TimeZone defaultTimeZone = TimeZone.getDefault();
		Date date = Calendar.getInstance().getTime();
		date
				.setTime(date.getTime()
						- defaultTimeZone.getOffset(date.getTime()));
		return date;

	}

	/**
	 * 把一个时间转换成为格林威治时间
	 * 
	 * @param date
	 *            时间
	 * @param timeZoneId
	 *            时间所在时区信息
	 * @return 返回传入时间对应格林威治的时间
	 */
	public static Date toGMTDate(Date date, String timeZoneId) {
		if (date == null) {
			return null;
		}
		TimeZone targetTimeZone = TimeZone.getTimeZone(timeZoneId);
		Date tempDate = new Date(date.getTime());
		tempDate.setTime(tempDate.getTime()
				- targetTimeZone.getOffset(tempDate.getTime()));
		return tempDate;
	}

	/**
	 * 将格林威治时间转换成为时区时间
	 * 
	 * @param date
	 *            格林威治时间
	 * @param timeZoneId
	 *            时区信息
	 * @return 返回该时区中的时间
	 */
	public static Date toTimeZoneDate(Date date, String timeZoneId) {
		TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
		Date tempDate = new Date(date.getTime());
		tempDate.setTime(tempDate.getTime()
				+ timeZone.getOffset(tempDate.getTime()));
		return tempDate;
	}

	/**
	 * 取得离TT自动关闭时间的值
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static String getRemainTime(Date date, int hours) {
		String result = null;
		Date currentdate = DateTimeFormatUtil.getCurrentDate();
		Date adddate = DateTimeFormatUtil.addTimeByHours(date, hours);
		if (DateTimeFormatUtil.compareDateByDate(adddate, currentdate)) {
			long cha = adddate.getTime() - currentdate.getTime();
			long hour = cha / (1000 * 60 * 60);
			long minute = cha / (1000 * 60) - hour * 60;
			result = String.valueOf(hour) + ":" + String.valueOf(minute);
		}
		return result;
	}

	/**
	 * 根据给定的日期取得年份
	 * 
	 * @param date
	 * @return
	 */
	public static int getYearByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 根据给定的日期取得月份
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 根据给定的日期取得日份
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 根据给定的日期取得星期
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 根据给定的日期取得小时
	 * 
	 * @param date
	 * @return
	 */
	public static int getHourByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 根据给定的日期取得分钟
	 * 
	 * @param date
	 * @return
	 */
	public static int getMinuteByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 根据给定的日期取得秒
	 * 
	 * @param date
	 * @return
	 */
	public static int getSecondByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 取得比当前年份大num个年的列表
	 * 
	 * @param num
	 * @return
	 */
	public static String[] getYearList(int num) {
		Date currentDate = DateTimeFormatUtil.getCurrentGMTDate();
		int year = DateTimeFormatUtil.getYearByDate(currentDate);
		String[] years = new String[num];
		for (int i = 0; i < num; i++) {
			years[i] = String.valueOf(year);
			year++;
		}
		return years;
	}

	/**
	 * 将带有AM PM字符串转换为GMT时间
	 * 
	 * @param format
	 * @param date
	 * @return
	 * @throws FrameworkException
	 */
	public static Date convertStringToGMTDateByAMPM(String format, String date)
			{
		SimpleDateFormat df = new SimpleDateFormat(format);
		DateFormatSymbols dfs = new DateFormatSymbols();
		Date returnDate = new Date();
		dfs.setAmPmStrings(new String[]{"AM", "PM"});
		df.setDateFormatSymbols(dfs);
		try {
			returnDate = df.parse(date);
			return returnDate;
		} catch (ParseException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.toString());
			}
			return returnDate;
		}
	}

	/**
	 * 得到当前0时的时间
	 * 
	 * @return
	 */
	public static Date getCurrentDateByZeroTime() {
		Date date = new Date();
		String sd = DateTimeFormatUtil.covertDateToString(date,
				YEAR_MONTH_DAY_TEMPLATE);
		sd += " 00:00:00";
		try {
			date = DateTimeFormatUtil.covertStringToDate(sd,
					YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 根据给定的时间,取得当前月的最后一天
	 * 
	 * @param currentDate
	 * @return
	 */
	public static int getLastDayByCurrentMonth(Date currentDate) {
		Date adate = DateTimeFormatUtil.addTimeByMonths(currentDate, 1);
		Calendar c = Calendar.getInstance();
		c.setTime(adate);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		String crtDate = year + "-" + month + "-" + "01";
		Date cdate = null;
		try {
			cdate = covertStringToDate(crtDate, YEAR_MONTH_DAY_TEMPLATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date ddate = DateTimeFormatUtil.subtractTimeByDays(cdate, 1);
		c.setTime(ddate);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static void main(String[] args) {
		String tt = DateTimeFormatUtil.covertDateToString(DateTimeFormatUtil.getCurrentDate(), DateTimeFormatUtil.YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		System.out.println(tt);
	}

}
