package org.dllwh.model;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 群组基本信息
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-12-19 5:42:55 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class BaseFriend {
	private Integer id;
	/** 群号 */
	private String groupNum;
	/** 群名称 */
	private String groupName;
	/** 创建时间 */
	private String buildTime;
	/** 是否为默认分组：1为默认，0为不是默认分组 */
	private Integer ifDefault;
	/** 分组下好友总数 */
	private int friendCount;
	/** 在线好友数 */
	private int onlineCount;
}
