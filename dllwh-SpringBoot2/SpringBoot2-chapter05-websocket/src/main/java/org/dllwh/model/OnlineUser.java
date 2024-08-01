package org.dllwh.model;

import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～ß
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 在线用户信息ßßß
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-03 12:48:10 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OnlineUser extends BaseUser {

	private static final long serialVersionUID = 3895514525438708037L;
	private boolean isKick = false;
	private boolean isDisConnect = false;
}
