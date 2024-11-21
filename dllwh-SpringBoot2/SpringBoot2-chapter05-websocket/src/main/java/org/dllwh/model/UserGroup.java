package org.dllwh.model;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 用户组信息
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-03 3:42:23 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class UserGroup {
	/** 组的id，唯一 */
	private String userGroupId;
	/** 组的名字 */
	private String userGroupName;
	/** 组成员 */
	private int groupMemberNums;
}
