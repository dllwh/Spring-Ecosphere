package com.cdeledu.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis 基础配置
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月27日 下午8:29:23
 * @版本: V1.0
 * @since: JDK 1.8
 */
@Configuration
@PropertySource("classpath:config/redis.properties")
public class RedisConfig {
	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private Integer port;
	@Value("${redis.password}")
	private String password;
	@Value("${redis.database}")
	private Integer database;
	@Value("${redis.timeout}")
	private Integer timeout;
	@Value("${redis.pool.maxIdle}")
	private Integer maxIdle;
	@Value("${redis.pool.maxTotal}")
	private Integer maxTotal;
	@Value("${redis.pool.maxWaitMillis}")
	private Integer maxWaitMillis;
	@Value("${redis.pool.minEvictableIdleTimeMillis}")
	private Integer minEvictableIdleTimeMillis;
	@Value("${redis.pool.numTestsPerEvictionRun}")
	private Integer numTestsPerEvictionRun;
	@Value("${redis.pool.timeBetweenEvictionRunsMillis}")
	private long timeBetweenEvictionRunsMillis;
	@Value("${redis.pool.testOnBorrow}")
	private boolean testOnBorrow;
	@Value("${redis.pool.testWhileIdle}")
	private boolean testWhileIdle;

	/**
	 * @方法描述 :JedisPoolConfig 连接池
	 * @return
	 */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 最大空闲数
		jedisPoolConfig.setMaxIdle(maxIdle);
		// 连接池的最大数据库连接数
		jedisPoolConfig.setMaxTotal(maxTotal);
		// 最大建立连接等待时间
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		// 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		// 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
		jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		// 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		// 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		// 在空闲时检查有效性, 默认false
		jedisPoolConfig.setTestWhileIdle(testWhileIdle);
		return jedisPoolConfig;
	}

}
