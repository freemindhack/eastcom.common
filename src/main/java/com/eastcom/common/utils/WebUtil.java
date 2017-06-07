package com.eastcom.common.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebUtil {

	public static String getValueFromSessionSenseNull(HttpSession session, String key) {
		if ((session != null) && (session.getAttribute(key) != null)) {
			return ((String) session.getAttribute(key));
		}

		throw new RuntimeException("Can not find key [" + key + "] in the current session.");
	}

	public static String getValueFromSessionNotSenseNull(HttpSession session, String key) {
		if ((session != null) && (session.getAttribute(key) != null)) {
			return ((String) session.getAttribute(key));
		}

		return "";
	}

	public static void removeValueInSession(HttpSession session, String key) {
		if (session != null) {
			session.removeAttribute(key);
		}
	}

	public static String removeCookie(HttpServletRequest request, HttpServletResponse response, String key) {
		
		String ret = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; ++i) {
				Cookie tmpCookie = cookies[i];
				if (key.equals(tmpCookie.getName())) {
					ret = tmpCookie.getValue();
					tmpCookie.setMaxAge(0);
					response.addCookie(tmpCookie);
					break;
				}
			}
		}
		return ret;
	}

	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String key, String value) {
		Cookie tmpCookie = new Cookie(key, value);
		tmpCookie.setMaxAge(-1);
		response.addCookie(tmpCookie);
	}

	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String key, String value,
			String path) {
		Cookie tmpCookie = new Cookie(key, value);
		tmpCookie.setMaxAge(-1);
		tmpCookie.setPath(path);
		response.addCookie(tmpCookie);
	}

	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String key, String value,
			int maxAge) {
		Cookie tmpCookie = new Cookie(key, value);
		tmpCookie.setMaxAge(maxAge);
		response.addCookie(tmpCookie);
	}

	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String key, String value,
			int maxAge, String domain) {
		Cookie tmpCookie = new Cookie(key, value);
		tmpCookie.setMaxAge(maxAge);
		tmpCookie.setDomain(domain);
		response.addCookie(tmpCookie);
	}

	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String key, String value,
			int maxAge, String domain, String path) {
		Cookie tmpCookie = new Cookie(key, value);
		tmpCookie.setMaxAge(maxAge);
		tmpCookie.setDomain(domain);
		tmpCookie.setPath(path);
		response.addCookie(tmpCookie);
	}

	public static String getValueFromCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		String ret = null;

		if (cookies != null) {
			for (int i = 0; i < cookies.length; ++i) {
				Cookie tmpCookie = cookies[i];

				if (key.equals(tmpCookie.getName())) {
					ret = tmpCookie.getValue();
					break;
				}
			}
		}

		return ret;
	}

	public static String buildUrlWithQueryString(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append(request.getRequestURL());

		String queryStr = request.getQueryString();

		if ((queryStr != null) && (queryStr.length() != 0)) {
			sb.append("?");
			sb.append(queryStr);
		}

		return sb.toString();
	}

	public static String getAbsoluteReturnUrl(HttpServletRequest request, String returnUrl) {
		if (returnUrl.startsWith("http")) {
			return returnUrl;
		} else if (returnUrl.startsWith("/")) {
			return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + returnUrl;
		} else {
			return request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/") + 1) + returnUrl;
		}
	}

	public static void downloadFile(String absoluteFileName, HttpServletResponse res) {
		try {
			File f = new File(absoluteFileName);
			if (f.exists()) {
				long filelength = f.length(); // 取得文件长度
				byte[] b = new byte[1024];
				// 设置文件输出流
				FileInputStream fin = new FileInputStream(f);
				DataInputStream in = new DataInputStream(fin);
				// 设置响应头信息，让下载的文件显示保存信息
				res.setHeader("Content-disposition",
						"attachment;filename=" + new String(f.getName().getBytes("gb2312"), "ISO8859-1"));
				// 设置输出流的MIME类型
				res.setContentType("application/octet-stream");
				// 确定长度
				String filesize = Long.toString(filelength);
				// 设置输出文件的长度
				res.setHeader("Content-Length", filesize);
				// 取得输出流
				ServletOutputStream servletout = res.getOutputStream();
				// 发送文件数据，每次1024字节，最后一次单独计算
				long totalsize = 0;
				while (totalsize < filelength) {
					totalsize += 1024;
					if (totalsize > filelength) {
						// 最后一次传送字节数
						byte[] leftpart = new byte[1024 - (int) (totalsize - filelength)];
						in.readFully(leftpart); // 读入字节数组
						servletout.write(leftpart); // 写入输出流
					} else {
						in.readFully(b); // 读入1024个字节到字节数组b
						servletout.write(b); // 写入输出流
					}
				}
				servletout.close();
				in.close();
				fin.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				res.getWriter().print("文件不存在.");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void downloadFile(String absoluteFileName, String dName, HttpServletResponse response) {
		try {
			File f = new File(absoluteFileName);
			if (f.exists()) {
				long filelength = f.length(); // 取得文件长度
				byte[] b = new byte[1024];
				// 设置文件输出流
				FileInputStream fin = new FileInputStream(f);
				DataInputStream in = new DataInputStream(fin);
				// 设置响应头信息，让下载的文件显示保存信息
				// response.setHeader("Content-disposition",
				// "attachment;filename=" + dName);
				response.setHeader("Content-disposition",
						"attachment;filename=" + new String(dName.getBytes("gb2312"), "ISO8859-1"));
				// 设置输出流的MIME类型
				response.setContentType("application/octet-stream");
				// 确定长度
				String filesize = Long.toString(filelength);
				// 设置输出文件的长度
				response.setHeader("Content-Length", filesize);
				// 取得输出流
				ServletOutputStream servletout = response.getOutputStream();
				// 发送文件数据，每次1024字节，最后一次单独计算
				long totalsize = 0;
				while (totalsize < filelength) {
					totalsize += 1024;
					if (totalsize > filelength) {
						// 最后一次传送字节数
						byte[] leftpart = new byte[1024 - (int) (totalsize - filelength)];
						in.readFully(leftpart); // 读入字节数组
						servletout.write(leftpart); // 写入输出流
					} else {
						in.readFully(b); // 读入1024个字节到字节数组b
						servletout.write(b); // 写入输出流
					}
				}
				servletout.close();
				in.close();
				fin.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				response.getWriter().print("文件不存在.");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static String getHighLightHtml(List<String> numArr, String text) {
		String[] strings = text.split("\\n");
		StringBuffer buffer = new StringBuffer();
		for (Integer i = 0; i < strings.length; i++) {
			if (numArr.contains(Integer.valueOf(i + 1).toString())) {
				buffer.append("<FONT style='BACKGROUND-COLOR: #ffff00'>");
				buffer.append(strings[i]);
				buffer.append("</FONT>\n\r");
			} else {
				buffer.append(strings[i]);
				buffer.append("<br />");
			}
		}
		return buffer.toString();
	}

	/**
	 * 生成链接的URL
	 * 
	 * @param href
	 * @param ctx
	 * @return
	 */
	public static String genUrl(String href, String ctx) {
		String url = href;
		if (!Pattern.matches("^(http://|https://|/).*", url))
			url = ctx + '/' + url;
		return url;
	}

	/**
	 * 验证权限点
	 * 
	 * @param content
	 * @param target
	 * @return
	 */
	public static boolean authenticate(String content, String target) {
		return content.contains("," + target + ",") ? true : false;
	}

	// public static void downloadFile(String path, String fname,
	// HttpServletResponse res) {
	// try {
	// File f = new File(path + fname);
	// System.out.println(f.getAbsolutePath());
	// if (f.exists()) {
	// long filelength = f.length(); // 取得文件长度
	// byte[] b = new byte[1024];
	// // 设置文件输出流
	// FileInputStream fin = new FileInputStream(f);
	// DataInputStream in = new DataInputStream(fin);
	// // 设置响应头信息，让下载的文件显示保存信息
	// res.setHeader("Content-disposition", "attachment;filename="
	// + new String(fname.getBytes(), "ISO-8859-1"));
	// // 设置输出流的MIME类型
	// res.setContentType("application/octet-stream");
	// // 确定长度
	// String filesize = Long.toString(filelength);
	// // 设置输出文件的长度
	// res.setHeader("Content-Length", filesize);
	// // 取得输出流
	// ServletOutputStream servletout = res.getOutputStream();
	// // 发送文件数据，每次1024字节，最后一次单独计算
	// long totalsize = 0;
	// while (totalsize < filelength) {
	// totalsize += 1024;
	// if (totalsize > filelength) {
	// // 最后一次传送字节数
	// byte[] leftpart = new byte[1024 - (int) (totalsize - filelength)];
	// in.readFully(leftpart); // 读入字节数组
	// servletout.write(leftpart); // 写入输出流
	// } else {
	// in.readFully(b); // 读入1024个字节到字节数组b
	// servletout.write(b); // 写入输出流
	// }
	// }
	// servletout.close();
	// in.close();
	// fin.close();
	// }
	// } catch (FileNotFoundException e) {
	//
	// e.printStackTrace();
	// } catch (IOException e) {
	//
	// e.printStackTrace();
	// }
	// }

	public static String removeCookie(HttpServletRequest request, HttpServletResponse response, String path,
			String key) {
		String ret = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie tmpCookie = cookies[i];
				if (key.equals(tmpCookie.getName())) {
					ret = tmpCookie.getValue();
					tmpCookie.setMaxAge(0);
					tmpCookie.setPath(path);
					response.addCookie(tmpCookie);
					break;
				}
			}
		}
		return ret;
	}

	public static String removeCookie(HttpServletRequest request, HttpServletResponse response, String domain,
			String path, String key) {
		String ret = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie tmpCookie = cookies[i];
				if (key.equals(tmpCookie.getName())) {
					ret = tmpCookie.getValue();
					tmpCookie.setDomain(domain);
					tmpCookie.setPath(path);
					tmpCookie.setMaxAge(0);
					response.addCookie(tmpCookie);
					break;
				}
			}
		}
		return ret;
	}
}