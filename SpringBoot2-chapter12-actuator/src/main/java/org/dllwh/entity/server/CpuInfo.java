package org.dllwh.entity.server;

import org.dllwh.util.ArithHelper;

import lombok.Data;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: CPU相关信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年3月6日 下午1:57:08
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class CpuInfo {
	/** 核心数 */
	private int		cpuNum;
	/** CPU总的使用率 */
	private double	total;
	/** CPU系统使用率 */
	private double	sys;
	/** CPU用户使用率 */
	private double	used;
	/** CPU当前等待率 */
	private double	wait;
	/** CPU当前空闲率 */
	private double	free;

	public double getTotal() {
		return ArithHelper.round(ArithHelper.mul(total, 100), 2);
	}

	public double getSys() {
		return ArithHelper.round(ArithHelper.mul(sys / total, 100), 2);
	}

	public double getUsed() {
		return ArithHelper.round(ArithHelper.mul(used / total, 100), 2);
	}

	public double getWait() {
		return ArithHelper.round(ArithHelper.mul(wait / total, 100), 2);
	}

	public double getFree() {
		return ArithHelper.round(ArithHelper.mul(free / total, 100), 2);
	}
}