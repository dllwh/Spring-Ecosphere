package org.dllwh.entity.server;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统相关信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年3月6日 下午2:20:34
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class SysInfo {
	/** 服务器名称 */
	private String	systemName;
	/** 服务器Ip */
	private String	computerIp;
	/** 项目路径 */
	private String	userDir;
	/** 操作系统 */
	private String	osName;
	/** 系统架构(位数) */
	private String	osArch;
	/** 操作系统版本 */
	private String	osVersion;
	/** */
	private String availableProcessors;
}