package com.newStdd.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
	public static String getDateString() {
		// ���ڸ�ʽ
		DateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dfmt.format(new Date());
	}
}
