package org.dllwh.entity.server;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dllwh.util.ArithHelper;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: JVM相关信息
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年3月6日 下午2:01:10
 * @版本: V 1.0.1
 * @since: JDK 1.7
 */
@Data
public class JvmInfo {
	/** 当前JVM占用的内存总数(M) */
	private double	total;
	/** JVM最大可用内存总数(M) */
	private double	max;
	/** JVM空闲内存(M) */
	private double	free;
	/** JDK版本 */
	private String	version;
	/** JDK路径 */
	private String	home;

	public double getTotal() {
		return ArithHelper.div(total, (1024 * 1024), 2);
	}

	public double getMax() {
		return ArithHelper.div(max, (1024 * 1024), 2);
	}

	public double getFree() {
		return ArithHelper.div(free, (1024 * 1024), 2);
	}

	public double getUsed() {
		return ArithHelper.div(total - free, (1024 * 1024), 2);
	}

	public double getUsage() {
		return ArithHelper.mul(ArithHelper.div(total - free, total, 4), 100);
	}

	/**
	 * 获取JDK名称
	 */
	public String getName() {
		return ManagementFactory.getRuntimeMXBean().getVmName();
	}

	/**
	 * JDK启动时间
	 */
	public String getStartTime() {
		long time = ManagementFactory.getRuntimeMXBean().getStartTime();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));

	}

	/**
	 * JDK运行时间
	 */
	public String getRunTime() {
		Date currentDate = new Date();
		long time = ManagementFactory.getRuntimeMXBean().getStartTime();
		Date serverStartDate = new Date(time);
		return ArithHelper.getDatePoor(currentDate, serverStartDate);
	}
}