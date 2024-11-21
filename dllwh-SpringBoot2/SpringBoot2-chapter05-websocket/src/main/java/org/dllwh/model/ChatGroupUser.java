package org.dllwh.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-03 12:09:31 AM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class ChatGroupUser implements Serializable {

	private static final long serialVersionUID = 3301184335582357621L;
	private String userId;
	private String chatGroupId;
	/** 群组成员显示的名字 */
	private String chatGroupUserRealName;
	/** 群组成员的角色 */
	private Byte chatGroupUserRole;
	private Date chatGroupUserUpdateTime;
	private boolean online;
}
