package org.dllwh.common.constant;

public interface CourseConstants {

	/**
	 * 把今天最好的表现当作明天最新的起点．．～
	 *
	 * Today the best performance as tomorrow newest starter!
	 *
	 * @类描述: 课程类型
	 * @创建者: 皇族灬战狼
	 * @联系方式: duleilewuhen@sina.com
	 * @创建时间: 2018年12月24日 上午11:39:50
	 * @版本: V 0.1
	 * @since: JDK 1.8
	 */
	static enum courseTypeEnum {
		//
		VIDEO_COURSE(1, "录播课程"),
		//
		LIVE_COURSE(2, "直播课程"),
		//
		OFFLINE_COURSE(3, "线下课程");
		private int		seq;
		private String	desc;

		private courseTypeEnum(int seq, String desc) {
			this.seq = seq;
			this.desc = desc;
		}

		public int getSeq() {
			return seq;
		}

		public String getDesc() {
			return desc;
		}
	}

	/**
	 * 把今天最好的表现当作明天最新的起点．．～
	 *
	 * Today the best performance as tomorrow newest starter!
	 *
	 * @类描述: 课程状态
	 * @创建者: 皇族灬战狼
	 * @联系方式: duleilewuhen@sina.com
	 * @创建时间: 2018年12月24日 上午11:40:05
	 * @版本: V 0.1
	 * @since: JDK 1.7
	 */
	static abstract class courseStateEnum {
		/** 新课程 */
		public static final int	NEW_COURSE			= 1;
		/** 未审核课程 */
		public static final int	UNAUTHED_COURSE		= 2;
		/** 审核通过 */
		public static final int	PASSED_COURSE		= 3;
		/** 审核未通过 */
		public static final int	NOT_PASSED_COURSE	= 4;
		/** 已删除 */
		public static final int	DELETEED_COURSE		= 5;
	}
}
