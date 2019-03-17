package org.dllwh.modules.monitor.online.domain;

import org.apache.shiro.session.mgt.SimpleSession;

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
 * @创建时间: 2019年1月4日 上午8:54:24
 * @版本: V 1.0.1
 * @since: JDK 1.7
 * @see <a href="">TODO(连接内容简介)</a>
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OnlineSession extends SimpleSession {
	private static final long	serialVersionUID	= 1L;
	private Integer				userID;
	private String				userName;
	private String				host;
	/** 浏览器 */
	private String				browser;
	/** 操作系统 */
	private String				os;
	/** 在线状态 */
	private Integer				onlineStatus;
}