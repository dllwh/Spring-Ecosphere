package org.dllwh.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Json 辅助工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年11月25日 上午10:19:45
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class JsonUtil {
	public static String toJson(Object object) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		return gson.toJson(object);
	}
}
