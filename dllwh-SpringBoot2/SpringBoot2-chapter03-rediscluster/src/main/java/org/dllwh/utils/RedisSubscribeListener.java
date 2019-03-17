package org.dllwh.utils;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPubSub;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis消息监听者
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月18日 下午8:59:20
 * @版本: V 1.0.1
 * @since: JDK 1.7
 */
@Slf4j
@Component("redisSubscribeListener")
public class RedisSubscribeListener extends JedisPubSub {
	@Override
	public void onMessage(String channel, String message) {
		log.info("Redis received...");
	}
}