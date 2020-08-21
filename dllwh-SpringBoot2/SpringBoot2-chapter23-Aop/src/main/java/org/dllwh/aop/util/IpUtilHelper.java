package org.dllwh.aop.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: IP地址工具类
 * 
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-08-20 01:11:16
 * @版本: V 1.0.1
 * @since: JDK 1.8
 *
 */
public final class IpUtilHelper {
	/**
	 * @方法描述: 获取客户端请求ip地址
	 *
	 * @param request
	 * @return
	 */
	public static String getClientIP(HttpServletRequest request) {
		String ip = null;
		/**
		 * Cdn-Src-Ip : 网宿cdn的真实ip HTTP_CLIENT_IP : 蓝讯cdn的真实ip X-Forwarded-For : 获取代理ip
		 * Proxy-Client-IP : 获取代理ip WL-Proxy-Client-IP : 获取代理ip
		 */
		if (null != request) {
			String proxs[] = { "Cdn-Src-Ip", "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
					"HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR", "x-real-ip" };
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

			// 如果通过多级反向代理检测的话,其值并不止一个，而是一串IP值,取第一个非unknown的有效IP字符串
			if (ip != null && ip.split(",").length > 1) {
				ip = ip.split(",")[0];
			}
		}
		return ip;
	}

	/**
	 * @方法描述: 本机IP
	 *
	 * @return
	 * @throws SocketException
	 */
	public static String getRealIp() throws SocketException {
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
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
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
