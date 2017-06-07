package com.eastcom.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:36:10 <br>
 *
 */
public class IpUtil {

	/**
	 * 如果有代理取不到真实ip，请用getIpAddr(HttpServletRequest request)
	 * @return
	 */
	public static String getIpAddr() {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip = address.getHostAddress();
		return ip;
	}

	/**
	 * 获取登录用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (null == request) {
			return null;
		}
		String proxs[] = { "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP",
				"HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" };
		String ip = null;

		for (String prox : proxs) {
			ip = request.getHeader(prox);
			if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				continue;
			} else {
				break;
			}
		}

		if (StringUtils.isBlank(ip)) {
			return request.getRemoteAddr();
		}

		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "本地";
		}
		if (ip.split(",").length > 1) {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	public static void main(String[] args) {
		System.out.println(IpUtil.getIpAddr());
	}

}
