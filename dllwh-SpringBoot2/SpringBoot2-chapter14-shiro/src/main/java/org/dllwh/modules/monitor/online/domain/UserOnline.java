package org.dllwh.modules.monitor.online.domain;

import java.util.Date;

import org.dllwh.common.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月4日 上午9:04:47
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserOnline extends BaseEntity {
	/** 用户会话id */
	private String	sessionId;
	/** 登录名称 */
	private String	loginName;
	/** 登录IP地址 */
	private String	ipaddr;
	/** 浏览器类型 */
	private String	browser;
	/** 操作系统 */
	private String	os;
	/** session创建时间 */
	private Date	startTimestamp;
	/** session最后访问时间 */
	private Date	lastAccessTime;
	/** 超时时间，单位为分钟 */
	private Long	expireTime;
}