package org.dllwh.model;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:  群组成员
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-12
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class GroupUser {
	/** 用户id */
	private Integer userId;
	/** 群id */
	private Integer groupId;
	/** 入群时间 */
	private String joinTime;
	/** 群成员用户 */
	private UserInfo[] user;
}
