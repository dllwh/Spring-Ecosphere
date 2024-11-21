package org.dllwh.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 获取用户消息请求结构
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-13
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserRecordRequest extends BaseRequest {

	private static final long serialVersionUID = -1690345914557088438L;
	/** 消息发送用户id(此字段必须与userId一起使用,获取双方聊天消息),非必填 */
	private int fromUserId;
	/** 当前用户id(必填字段),当只有此字段时,type必须为0，意思是获取当前用户所有离线消息(好友+群组) */
	private int userId;
	/** 群组id(此字段必须与userId一起使用,获取当前用户指定群组聊天消息),非必填 */
	private int groupId;
	/** 消息区间开始时间Date毫秒数double类型,非必填 */
	private double beginTime;
	/** 消息区间结束时间Date毫秒数double类型,非必填 */
	private double endTime;
	/** 分页偏移量int类型，类似Limit 0,10 中的0,非必填 */
	private int offset;
	/** 显示消息数量,类似Limit 0,10 中的10,非必填 */
	private int count;
	/** 消息类型(0:离线消息,1:历史消息) */
	private int type;
}
