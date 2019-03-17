package org.dllwh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 机器监控信息存储
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月18日 下午3:03:06
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputerInfo {
	/** IP地址 */
	private String	ip;
	/** 机器名 */
	private String	hostname;
	/** 总内存 */
	private Long	totalMem;
	/** 剩余内存 */
	private Long	freeMem;
	/** 内存占比 */
	private Double	combinedMem;
	/** CPU占比 */
	private Double	combinedCpu;
	/** 磁盘总量 */
	private Long	totalDisk;
	/** 磁盘剩余量 */
	private Long	freeDisk;
	/** 磁盘占比 */
	private Double	combinedDisk;
	/** 交换区总量 */
	private Long	totalSwap;
	/** 空余交换区 */
	private Long	freeSwap;
	/** 交换区占比 */
	private Double	combinedSwap;
}