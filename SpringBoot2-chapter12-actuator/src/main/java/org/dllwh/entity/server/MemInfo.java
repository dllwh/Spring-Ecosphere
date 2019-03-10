package org.dllwh.entity.server;

import org.dllwh.util.ArithHelper;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 內存相关信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年3月6日 下午2:18:24
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class MemInfo {
	/** 内存总量 */
	private double	total;
	/** 已用内存 */
	private double	used;
	/** 剩余内存 */
	private double	free;

	public double getTotal() {
		return ArithHelper.div(total, (1024 * 1024 * 1024), 2);
	}

	public double getUsed() {
		return ArithHelper.div(used, (1024 * 1024 * 1024), 2);
	}

	public double getFree() {
		return ArithHelper.div(free, (1024 * 1024 * 1024), 2);
	}

	public double getUsage() {
		return ArithHelper.mul(ArithHelper.div(used, total, 4), 100);
	}
}