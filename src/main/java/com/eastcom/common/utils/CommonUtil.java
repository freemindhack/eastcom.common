package com.eastcom.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;

/**
 * 
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:34:57 <br>
 *
 */
public class CommonUtil {
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static byte[] getBytesFromIS(InputStream is) {
		try {
			byte[] ba = new byte[512];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int count = 0;
			while ((count = is.read(ba)) > -1) {
				baos.write(ba, 0, count);
			}
			return baos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt(String str) {
		String encrypt = "";
		encrypt = new String(Base64.encodeBase64(str.getBytes()));

		return encrypt;
	}

	public static String dencrypt(String str) {
		String dencrypt = "";
		dencrypt = new String(Base64.decodeBase64(str.getBytes()));

		return dencrypt;
	}

	public static String parseUnicode(String s) {
		if ((!s.startsWith("&#")) && (!s.endsWith(";"))) {
			return s;
		}
		StringBuffer sb = new StringBuffer();
		boolean flag1 = false;
		boolean flag2 = false;
		StringBuffer sb1 = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '&':
				flag1 = true;
				sb1 = new StringBuffer();
				break;
			case '#':
				if (flag1) {
					flag2 = true;
				}
				break;
			case ';':
				sb.append((char) Integer.parseInt(sb1.toString()));
				flag1 = false;
				flag2 = false;
				break;
			default:
				if (flag2) {
					sb1.append(c);
				}
				break;
			}
		}
		return sb.toString();
	}

	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
			return password;
		}
		md.reset();
		md.update(unencodedPassword);

		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xFF) < 16) {
				buf.append("0");
			}
			buf.append(Long.toString(encodedPassword[i] & 0xFF, 16));
		}
		return buf.toString();
	}

	public static String getWeekDay(Date now) {
		String nowStr = now.toString();
		if (nowStr.startsWith("Mon ")) {
			return "星期一";
		}
		if (nowStr.startsWith("Tue ")) {
			return "星期二";
		}
		if (nowStr.startsWith("Wed ")) {
			return "星期三";
		}
		if (nowStr.startsWith("Thu ")) {
			return "星期四";
		}
		if (nowStr.startsWith("Fri ")) {
			return "星期五";
		}
		if (nowStr.startsWith("Sat ")) {
			return "星期六";
		}
		if (nowStr.startsWith("Sun ")) {
			return "星期日";
		}
		return "";
	}

	public static String getDate(Date now) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		String date1 = df.format(now);
		return date1;
	}

	public static String getTime() {
		Date now = new Date();
		return getDate(now) + " " + getWeekDay(now);
	}

	public static String generateUuid() {
		long currentTime = System.currentTimeMillis();

		SecureRandom random = new SecureRandom();
		int pseudorandom = random.nextInt(100000);
		char[] value = { '0', '0', '0', '0', '0' };
		char[] real = String.valueOf(pseudorandom).toCharArray();
		for (int i = 0; i < real.length; i++) {
			value[(5 - real.length + i)] = real[i];
		}
		String suffix = String.valueOf(value);

		return "ID" + currentTime + suffix;
	}

	public static double subDouble(double f, int lenght) {
		String fStr = String.valueOf(f);

		int i = fStr.indexOf('.');
		double returnf;
		if ((i > 0) && (fStr.length() >= i + 1 + lenght)) {
			String returnStr = fStr.substring(0, i + 1 + lenght);
			returnf = Double.valueOf(returnStr).doubleValue();
		} else {
			returnf = Double.valueOf(fStr).doubleValue();
		}
		return returnf;
	}

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getDateTime(Date now) {
		return format.format(now);
	}

	public static boolean isContainChinese(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.substring(i, i + 1).matches("[\\u4e00-\\u9fa5]+")) {
				return true;
			}
		}
		return false;
	}
}
