package org.dllwh.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @author : 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年1月16日 下午11:12:21
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public final class DateHelper {
	public static String getDateStr(long timeStmp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date(timeStmp));
	}
}