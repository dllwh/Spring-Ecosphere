package org.dllwh.protocol;

import org.dllwh.model.IMMessage;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 服务端消息 自定义IM协议的编码器
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-22
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class IMWebMessageEncoder {
	public String encode(IMMessage message) {
		if (null == message) {
			return "";
		}
		String prex = "[" + message.getCmd() + "]" + "[" + message.getTime() + "]";
		return prex;
	}
}
