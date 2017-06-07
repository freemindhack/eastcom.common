package com.eastcom.common.utils;

import java.io.UnsupportedEncodingException;

import com.eastcom.common.config.PropertyUtils;

/**
 * 
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:37:49 <br>
 *
 */
public class SystemUtils extends org.apache.commons.lang3.SystemUtils {

	private static String rootPath;

	private static String classPath;

	static {
		init();
	}

	public static String getClassPath() {
		if (StringUtils.isEmpty(classPath)) {
			String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
			String temp = path.replaceFirst("file:/", "");
			String separator = System.getProperty("file.separator");
			classPath = temp.replaceAll("/", separator + separator);
		}
		return classPath;
	}

	public static String getRootPath() {

		return rootPath;
	}

	public static String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

	public static String getRootPathAsWebApp() {

		StringBuffer sb = new StringBuffer(classPath);
		sb.delete(sb.lastIndexOf("WEB-INF"), sb.length());
		return sb.toString();
	}

	private static void init() {

		classPath = SystemUtils.class.getResource("/").toString();
		rootPath = SystemUtils.getUserDir().getAbsolutePath();
		classPath = classPath.substring(5);
		if (System.getProperty("os.name").startsWith("Window")) {
			classPath = classPath.substring(1);
		}
		try {
			classPath = java.net.URLDecoder.decode(classPath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}

		try {
			rootPath = java.net.URLDecoder.decode(rootPath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
	}

	/**
	 * 获取Key加载信息
	 */
	public static boolean printKeyLoadMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n    欢迎使用 " + PropertyUtils.getConfig("projectName") + "                     \r\n");
		sb.append("\r\n======================================================================\r\n");
		System.out.println(sb.toString());
		return true;
	}

	public static void main(String[] args) {
		System.out.println(getRootPath());
	}
}
