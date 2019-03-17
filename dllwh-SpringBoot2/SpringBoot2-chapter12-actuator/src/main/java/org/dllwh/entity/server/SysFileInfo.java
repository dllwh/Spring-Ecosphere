package org.dllwh.entity.server;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 系统文件相关信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年3月6日 下午2:21:37
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class SysFileInfo {
	/** 盘符路径 */
	private String	dirName;
	/** 盘符类型 */
	private String	sysTypeName;
	/** 文件类型 */
	private String	typeName;
	/** 总大小 */
	private String	total;
	/** 剩余大小 */
	private String	free;
	/** 已经使用量 */
	private String	used;
	/** 资源的使用率 */
	private double	usage;
}