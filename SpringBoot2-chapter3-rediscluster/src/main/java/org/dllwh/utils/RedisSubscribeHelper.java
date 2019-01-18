package org.dllwh.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 监控服务启动时，进行Redis队列中数据的统计初始化，并开启Redis消息订阅者的监听的
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月18日 下午8:57:01
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Slf4j
@Component
public class RedisSubscribeHelper {
	@Autowired
	private RedisSubscribeListener redisSubscribeListener;

	@PostConstruct
	public void doWork() {
		init();
		subscribe();
	}

	/**
	 * @方法描述 : 数据的初始化
	 */
	private void init() {
		long start = System.currentTimeMillis();
		log.info("开始从Redis中获取订单初始化数据....");
		log.info("初始化数据工作耗时:" + (System.currentTimeMillis() - start) + "ms");
	}

	private void subscribe() {
		log.info("启动Redis消息订阅");
	}
}