package com.newStdd.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 * */
public class DateUtil {
	
	/**
	 * 日期格式：yyyy-MM-dd HH:mm:ss
	 * */	
	public static String dateFormatYyyy_MM_dd_HH_mm_ss= "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期格式：yyyy-MM-dd HH:mm:ss.SSSSSS
	 * */	
	public static String dateFormatYyyy_MM_dd_HH_mm_ss_SSSSSS= "yyyy-MM-dd HH:mm:ss.SSSSSS";
	
	/**
	 * 获取指定日期格式的系统时间字符串
	 * @param format 日期格式：取值从本类中日期格式静态变量中选取
	 * @return 指定日期格式的系统时间字符串
	 * */
	public static String getSysDateString(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}
	
	/**
	 * 将日期转成指定格式的字符串
	 * @param date 日期
	 * @param format 日期格式：取值从本类中日期格式静态变量中选取
	 * @return 指定日期格式的时间字符串
	 * */	
	public static String getDateString(Long date, String format) {
		DateFormat dateFormat= new SimpleDateFormat(format);
		return dateFormat.format(new Date(date));
	}	
	
	/**
	 * 将日期转成中文格式（年月日时分秒）的字符串
	 * @param date Long 日期
	 * @return 指定日期格式的时间字符串
	 * */	
	public static String getDateStringZh(Long date) {
		DateFormat dateFormat= new SimpleDateFormat(dateFormatYyyy_MM_dd_HH_mm_ss);
		String formatString= dateFormat.format(new Date(date));
		return formatString.substring(0, 4)+ "年"+ formatString.substring(5, 7)+ "月"+ formatString.substring(8, 10)+ "日"+ formatString.substring(11, 13)+ "点"+ formatString.substring(14, 16)+ "分"+ formatString.substring(17, 19)+ "秒";
	}		
}
