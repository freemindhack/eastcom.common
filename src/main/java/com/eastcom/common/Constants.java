package com.eastcom.common;

/**
 * 系统常量<br>
 * Title: eastcom.common <br>
 * Description: <br>
 * Copyright: eastcom Copyright (C) 2013 <br>
 * 
 * @author <a href="mailto:hujian@eastcom-sw.com">剑</a><br>
 * @e-mail: hujian@eastcom-sw.com <br>
 * @version 1.0 <br>
 * @creatdate 2014-5-21 下午3:11:36 <br>
 * 
 */
public interface Constants {
	public static final String ENCODING = "UTF-8";
	public static final String ACCOUNT_KEY = "account.name.";
	public static final String IP_ADDR = "ip_addr";
	public static final String LOGON_USER = "logon_user";

	public enum YesOrNo {
		YES("1"), NO("0");

		private String value;

		YesOrNo(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}
	}

	public enum ShowOrHide {
		SHOW("1"), HIDE("0");

		private String value;

		ShowOrHide(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}
	}

	public enum TrueOrFalse {
		TRUE("true"), FALSE("false");

		private String value;

		TrueOrFalse(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}
	}
}
