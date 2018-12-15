package com.cdeledu.common.constant;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 调度任务常量
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月14日 下午6:48:31
 * @版本: V 0.1
 * @since: JDK 1.8
 */
public interface ScheduleConstants {
	public enum TaskStatus {
		/** 未运行 */
		NORUN(0),
		/** 运行中 */
		RUNNING(1),
		/** 暂停 */
		PAUSE(2),
		/** 结束 */
		END(3),
		/** 停止 */
		STOP(4);
		;
		private int value;
		
		public int getValue() {
			return value;
		}
		
		private TaskStatus(int value) {
			this.value = value;
		}
		
	}
}
