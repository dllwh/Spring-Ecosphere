package org.dllwh.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2019-12-19 5:43:22 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class BaseUser implements Serializable {

	private static final long serialVersionUID = -6763336771530186652L;
	/** 用户唯一id; */
	private Integer userId;
	/** 用户账号; */
	private String userCode;
	/** 用户名称 */
	private String username;
	/** 在线状态 online：在线、hide：隐身 */
	private String status;
	/** 用户头像 */
	private String avatar;
}
