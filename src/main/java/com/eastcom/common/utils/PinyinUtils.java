/*
 * Created on 2013-7-10
 * PinyinUtils.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Eastcom Technologies Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package com.eastcom.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉语拼音工具类
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:36:47 <br>
 *
 */
public class PinyinUtils {

	/**
	 * 将中文转换成拼音
	 * 
	 * @param 拼音
	 *            -汉字
	 * @return
	 */
	private static String getPinYin(String cnString, boolean caseSensitive) {

		StringBuilder pinyinBuilder = new StringBuilder();
		char[] chars = cnString.toCharArray();
		try {
			for (int i = 0; i < chars.length; i++) {
				String[] pinYin;

				pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i], getDefaultOutputFormat());

				// 当转换不是中文字符时,返回null
				if (pinYin != null) {
					pinyinBuilder.append(pinYin[0]);
				} else {
					pinyinBuilder
							.append(caseSensitive ? chars[i] : Character.toLowerCase(chars[i]));
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return pinyinBuilder.toString();
	}

	/**
	 * 输出格式
	 * 
	 * @return
	 */
	private static HanyuPinyinOutputFormat getDefaultOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
		format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);// u显示

		return format;
	}

	public static int compareString(String s1, String s2, boolean caseSensitive) {
		int result;
		String py1 = getPinYin(s1, caseSensitive);
		String py2 = getPinYin(s2, caseSensitive);
		if (py1.equals(s1) && caseSensitive || py1.equalsIgnoreCase(s1)) {
			if (py2.equals(s2) && caseSensitive || py2.equalsIgnoreCase(s2)) {
				result = caseSensitive ? s1.compareTo(s2) : s1.compareToIgnoreCase(s2);
			} else {
				result = py1.charAt(0) - py2.charAt(0);
				result = result == 0 ? -1 : result;
			}
		} else if (py2.equals(s2) && caseSensitive || py2.equalsIgnoreCase(s2)) {
			result = py1.charAt(0) - py2.charAt(0);
			result = result == 0 ? 1 : result;
		} else {
			result = py1.compareTo(py2);
		}
		result = caseSensitive ? s1.compareTo(s2) : s1.compareToIgnoreCase(s2);
		return result;
	}
}
