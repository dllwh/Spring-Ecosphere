package org.dllwh.protocol;

import org.apache.commons.lang3.StringUtils;
import org.dllwh.model.IMMessage;

import com.google.gson.Gson;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 服务端消息 自定义IM协议的解码器
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-22
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public class IMWebMessageDecoder {
	/**
	 * 
	 * @方法描述: 字符串解析成自定义即时通信协议
	 *
	 * @param msg
	 * @return
	 */
	public IMMessage decode(String message) {
		if (StringUtils.isBlank(message)) {
			return null;
		}
		
		return new Gson().fromJson(message, IMMessage.class);
	}
}
