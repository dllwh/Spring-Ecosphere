package org.dllwh.aop.model;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Controller层的日志封装类
 * 
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-08-20 00:49:41
 * @版本: V 1.0.1
 * @since: JDK 1.8
 *
 */
@Data
public class WebLog {
	/** 操作描述 */
	private String description;
	/** 操作用户 */
	private String userName;
	/** IP地址 */
	private String ipAddress;
	/** 目标类名 */
	private String className;
	/** 目标方法 */
	private String methodName;
	/** 操作时间 */
	private Long startTime;
	/** 消耗时间(单位：毫秒) */
	private Long spendTime;
	/** 请求方式(请求类型) */
	private String method;
	/** 浏览器信息 */
	private String browser;
	/** 浏览器版本 */
	private String browserVersion;
	/** 根路径 */
	private String basePath;
	/** URI */
	private String uri;
	/** URL */
	private String url;
	/** 请求参数 */
	private Object parameter;
	/** 请求返回的结果 */
	private Object result;
	/** 日志类型 */
	private int logType;
	/** 异常原因 */
	private String exceptionDetail = "";
}
