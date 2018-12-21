package com.cdeledu.modules.monitor.syslog.domain;

import java.util.Date;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:11:40
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
public class SysLog {
	/** ID */
	private Integer	id;
	/** 用户账号 */
	private String	loginName;
	/** 登录状态 0成功 1失败 */
	private String	status;
	/** 登录IP地址 */
	private String	ipaddr;
	/** 浏览器类型 */
	private String	browser;
	/** 操作系统 */
	private String	os;
	/** 提示消息 */
	private String	msg;
	/** 访问时间 */
	private Date	loginTime;
}
