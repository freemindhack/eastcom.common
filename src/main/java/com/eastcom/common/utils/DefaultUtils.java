package com.eastcom.common.utils;

import java.util.Date;

/**
 * 
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:35:16 <br>
 *
 */
public class DefaultUtils {

	public static String getThisOrDefault(String content) {
		if (content == null) {
			return "";
		}

		return content;
	}

	public static Date getThisOrDefault(Date date) {

		if (date == null) {
			return new Date();
		}
		return date;
	}

	public static Object getThisOrDefault(Object object) {
		if (object == null) {
			return "";
		}
		return object;
	}

	public static Boolean getThisOrDefault(Boolean booleanValue) {
		if (booleanValue == null) {
			return new Boolean(false);
		}
		return booleanValue;
	}

	public static Double getThisOrDefault(Double doubleValue) {
		if (doubleValue == null) {
			return 0.0;
		}
		return doubleValue;
	}

	public static Integer getThisOrDefault(Integer integer) {

		if (integer == null) {
			return 0;
		}
		return integer;
	}

}
