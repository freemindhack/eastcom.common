/**
 * UUIDHexGenerator.java 1.0
 * Description: 十六进制UUID生成器
 * Created by xxx
 * Created on 2011-07-28
 * Copyright: Eastcom Software Copyright (C) 2011
 */
package com.eastcom.common.utils;

import java.io.Serializable;

/**
 * 十六进制UUID生成器
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:38:22 <br>
 *
 */
public class UUIDHexGenerator extends UUIDGenerator {

	private String sep = "";

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	/**
	 * 生成UUID
	 * 
	 * @return Serializable UUID
	 */
	public Serializable generate() {
		return new StringBuffer(36).append(format(getIP())).append(sep).append(format(getJVM())).append(sep).append(format(getHiTime())).append(sep)
				.append(format(getLoTime())).append(sep).append(format(getCount())).toString();
	}
}