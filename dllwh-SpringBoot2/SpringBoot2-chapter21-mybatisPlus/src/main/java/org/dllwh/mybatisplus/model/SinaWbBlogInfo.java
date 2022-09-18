package org.dllwh.mybatisplus.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author : 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-11-05 9:20:00 AM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
public class SinaWbBlogInfo implements Serializable {
	private static final long serialVersionUID = 4702983323168055712L;
	private int card_type;
	/** 微博地址 */
	private String scheme;
	private Mblog mblog;

	@Data
	public class Mblog {
		private String id;
		/** 内容 */
		private String text;
		/** 转发数量 */
		private int reposts_count;
		/** 评论数量 */
		private int comments_count;
		/** 赞数量 */
		private int attitudes_count;
		/** 博客ID */
		private String bid;
	}
}
