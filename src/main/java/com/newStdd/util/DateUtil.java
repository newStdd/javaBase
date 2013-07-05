package com.newStdd.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
	public static String dateFormatYyyy_MM_dd_HH_mm_ss= "yyyy-MM-dd HH:mm:ss";
	public static String dateFormatYyyy_MM_dd_HH_mm_ss_ssssss= "yyyy-MM-dd HH:mm:ss.SSSSSS";
	public static String getSysDateString(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}
	public static String getDateString(Long dateLong, String format) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date(dateLong));
	}	
}
