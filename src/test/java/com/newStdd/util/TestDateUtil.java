package com.newStdd.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * 测试时间工具类
 * */
public class TestDateUtil {
	/**
	 * 测试getDateStringZh方法：将日期转成中文格式（年月日时分秒）的字符串
	 * */	
	@Test public void getDateStringZh() {
		assertEquals("2014年02月28日17点59分58秒", DateUtil.getDateStringZh(1393581598253L));
	}		
}
