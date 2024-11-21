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
 * @创建时间: 2019-12-22 3:51:34 PM
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
public class ChatGroup implements Serializable {
	
	private static final long serialVersionUID = 203913499216819952L;
	private String chatGroupId;
	private String chatGroupName;
	private Date chatGroupCreateTime;
	private String userGroupId;
}
