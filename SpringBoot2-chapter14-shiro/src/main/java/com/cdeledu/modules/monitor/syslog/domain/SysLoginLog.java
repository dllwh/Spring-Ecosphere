package com.cdeledu.modules.monitor.syslog.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 登录日志
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月29日 下午1:42:59
 * @版本: V 1.0.1
 * @since: JDK 1.7
 */
@Data
public class SysLoginLog {
	private Integer		id;
	/** 登录账号 */
	private String		loginName;
	/** 登录的IP地址 */
	private String		clientIp;
	/** 登录是否成功；1为成功，0为失败 */
	private Integer		loginStatus;
	/** 登录浏览器 */
	private String		browser;
	/** 操作系统 */
	private String		os;
	/** 日志内容 */
	private String		logContent;
	/** */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp	createTime;
}