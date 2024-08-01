package org.dllwh.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-03 12:18:03 AM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseUser {

	private static final long serialVersionUID = 1953042400358699004L;
	/** 在线状态(online、offline) */
	private String status;
	/** 用户所属终端; */
	private String terminal;
	/** 聊天的名字 */
	private String userRealName;
	/** 属于哪个组织,用于获取好友列表，组织信息 */
	private UserGroup[] userGroupArr;
	/** 用户角色 1管理员，2普通成员 */
	private int userRole;

}
