package org.dllwh.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取用户信息请求消息结构
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-13
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserInfoRequest extends BaseRequest {

	private static final long serialVersionUID = -1514940234753321294L;
	/** 用户id(必填项) */
	private int userId;
	/** 获取类型(0:所有在线用户,1:所有离线线用户,2:所有用户[在线+离线]) */
	private int type;
}
