package com.cdeledu.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: web 工具类
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月24日 上午8:32:52
 * @版本: V1.1.3
 * @since: JDK 1.8
 */
public final class WebHelper {

	/**
	 * @方法描述 : 获取ServletRequestAttributes
	 * @return
	 */
	public static ServletRequestAttributes getRequestAttributes() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		return (ServletRequestAttributes) attributes;
	}

	/**
	 * @方法描述 : 获取request
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return getRequestAttributes().getRequest();
	}

	/**
	 * @方法描述 : 获取response
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return getRequestAttributes().getResponse();
	}

	/**
	 * @方法描述 : 获取session
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * @方法描述 : 获取IP地址
	 * 
	 *       <pre>
	 *  Cdn-Src-Ip : 网宿cdn的真实ip 
	 *  HTTP_CLIENT_IP : 蓝讯cdn的真实ip 
	 *  X-Forwarded-For :获取代理ip 
	 *  Proxy-Client-IP : 获取代理ip 
	 *  WL-Proxy-Client-IP : 获取代理ip
	 *       </pre>
	 * 
	 * @param request
	 * @return
	 */
	public static String getCliectIp(HttpServletRequest request) {
		String ip = "";
		String proxs[] = { "Cdn-Src-Ip", "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP",
				"HTTP_X_FORWARDED_FOR", "x-real-ip" };
		for (String prox : proxs) {
			ip = request.getHeader(prox);
			if (StringUtils.isBlank(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				continue;
			} else {
				break;
			}
		}

		if (StringUtils.isBlank(ip)) {
			ip = request.getRemoteAddr();// 获取真实ip
		}

		if (ip.equals("0:0:0:0:0:0:0:1")) {
			try {
				ip = getRealIp();
			} catch (Exception e) {
				ip = "127.0.0.1";
			}
		}

		// 多个路由时，取第一个非unknown的ip
		final String[] arr = ip.split(",");
		for (final String str : arr) {
			if (!"unknown".equalsIgnoreCase(str)) {
				ip = str;
				break;
			}
		}
		return ip;
	}

	/**
	 * @方法描述 : 获取错误状态码,可以根据异常对象返回对应的错误信息
	 * @param request
	 * @return
	 */
	public static HttpStatus getErrorHttpStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);
		try {
			return statusCode != null ? HttpStatus.valueOf(statusCode.intValue()) : HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (Exception ex) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

	/**
	 * @方法描述 : 获取堆栈轨迹
	 * @param error
	 * @param flag
	 * @return
	 */
	public static String getStackTraceInfo(Throwable error, boolean flag) {
		if (flag) {
			StringWriter stackTrace = new StringWriter();
			error.printStackTrace(new PrintWriter(stackTrace));
			stackTrace.flush();
			return stackTrace.toString();
		} else {
			return "omitted";
		}
	}

	/**
	 * 
	 * @方法描述 : 判断是否是AJAX请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String accept = request.getHeader("accept");
		if (accept != null && accept.indexOf("application/json") != -1) {
			return true;
		}

		String xRequestedWith = request.getHeader("X-Requested-With");
		if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
			return true;
		}

		String uri = request.getRequestURI();
		if (StringUtils.containsAny(uri, ".json", ".xml")) {
			return true;
		}

		String ajax = request.getParameter("__ajax");
		if (StringUtils.containsAny(ajax, "json", "xml")) {
			return true;
		}

		return false;
	}

	private static String getRealIp() throws SocketException {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();

		InetAddress ip;
		boolean finded = false;// 是否找到外网IP

		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				// 外网IP
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}

		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}
}
