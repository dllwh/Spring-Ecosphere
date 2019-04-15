package org.dllwh.devTools.utils;

import org.springframework.util.ObjectUtils;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 工具包集合，只做简单的调用，不删除原有工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月8日 下午11:55:15
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class FuncHelper {

	/**
	 * @方法描述:对象组中是否存在 Empty Object
	 */
	public static boolean hasEmpty(Object... os) {
		for (Object object : os) {
			if (ObjectUtils.isEmpty(object)) {
				return true;
			}
		}
		return false;
	}
}