package com.newStdd.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
	public static String getDateString() {
		// 日期格式
		DateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dfmt.format(new Date());
	}
}
