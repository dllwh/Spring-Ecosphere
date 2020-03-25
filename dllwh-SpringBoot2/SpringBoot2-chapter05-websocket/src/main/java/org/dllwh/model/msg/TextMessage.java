package org.dllwh.model.msg;

import org.dllwh.model.BaseMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 文本类型消息
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-24
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TextMessage extends BaseMessage {
	private static final long serialVersionUID = 1146480492744871139L;
	/** 内容 */
	private String content;
}
