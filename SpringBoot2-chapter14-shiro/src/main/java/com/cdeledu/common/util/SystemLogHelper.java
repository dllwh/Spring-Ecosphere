package com.cdeledu.common.util;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cdeledu.common.constant.SysLogConstant;
import com.cdeledu.framework.factory.TaskFactory;
import com.cdeledu.framework.model.LoggerEntity;
import com.cdeledu.framework.scheduled.ScheduledManager;
import com.cdeledu.modules.monitor.syslog.domain.SysLoginLog;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 记录用户日志信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月19日 上午8:45:13
 * @版本: V 1.0.2
 * @since: JDK 1.8
 */
public final class SystemLogHelper {

	/**
	 * @方法描述 : 登录日志
	 * @param userNmae
	 *            用户名
	 * @param state
	 *            操作
	 * 
	 *            <pre>
	 *           loginError 登录失败 
	 *           loginSuccess 登录成功
	 *           passwordError 密码错误
	 *            </pre>
	 * 
	 * @param msg
	 *            错误消息
	 * @param args
	 */
	public static void loginLog(String userNmae, Integer state, String msg) {
		// HttpServletRequest request = WebHelper.getRequest();
		UserAgent userAgent = UserAgent.parseUserAgentString(WebHelper.getRequest().getHeader("User-Agent"));
		// 获取客户端操作系统
		String os = userAgent.getOperatingSystem().getName();
		// 获取客户端浏览器
		String browser = userAgent.getBrowser().getName();
		// requestLog.setSessionId(request.getRequestedSessionId());
		SysLoginLog loginInfor = new SysLoginLog();
		loginInfor.setLoginName(userNmae);
		loginInfor.setLoginStatus(state);
		loginInfor.setClientIp(WebHelper.getCliectIp(WebHelper.getRequest()));
		loginInfor.setOs(os);
		loginInfor.setBrowser(browser);
		loginInfor.setLogContent(msg);

		ScheduledManager.getInstance().executeLog(TaskFactory.loginLog(loginInfor));
	}

	/**
	 * @方法描述 : 异常日志
	 * @param throwable
	 */
	public static void expLog(Throwable throwable) {
		HttpServletRequest request = WebHelper.getRequest();
		LoggerEntity requestLog = new LoggerEntity();
		requestLog.setStartTime(new Timestamp(System.currentTimeMillis()));
		requestLog.setSessionId(request.getRequestedSessionId());
		requestLog.setRequestMethod(request.getMethod());
		if (WebHelper.isAjaxRequest(request)) {
			requestLog.setRequestType(1);
		} else {
			requestLog.setRequestType(0);
		}
		requestLog.setClientIp(WebHelper.getCliectIp(request));
		requestLog.setRequestUrl(request.getRequestURI());
		requestLog.setReturnData(JSON.toJSONString(throwable,
				SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue));
		requestLog.setExceptionDetail(throwable.getMessage());
		requestLog.setOperateStatus(WebHelper.getErrorHttpStatus(request).value());
		requestLog.setLogType(SysLogConstant.type.exception);
		ScheduledManager.getInstance().executeLog(TaskFactory.exceptionLog(requestLog));
	}

	public static void opLog(JoinPoint joinPoint, Object returnValue, Throwable throwable) {

	}
}
