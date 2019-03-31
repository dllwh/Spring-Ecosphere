package org.dllwh.alarm;

import redis.clients.jedis.Jedis;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: redis服务连接报警接口
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年3月31日 下午11:50:39
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public interface RedisAlarm {
	/**
	 * @方法描述:连接断开报警
	 * @param jedis
	 */
	public void disconnectAlarm(Jedis jedis);

	/**
	 * @方法描述:连接恢复开报警
	 * @param jedis
	 */
	public void reconnectAlarm(Jedis jedis);
}
