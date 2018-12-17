package com.cdeledu.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

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
 * @版本: V1.0.2
 * @since: JDK 1.8
 */

@Configuration
public class RedisConfig {
	@Value("${spring.redis.host}")
	private String	host;
	@Value("${spring.redis.port}")
	private Integer	port;
	@Value("${spring.redis.password}")
	private String	password;
	@Value("${spring.redis.database}")
	private Integer	database;
	@Value("${spring.redis.timeout}")
	private Integer	timeout;
	@Value("${spring.redis.jedis.pool.max-idle}")
	private Integer	maxIdle;
	@Value("${spring.redis.jedis.pool.max-active}")
	private Integer	maxTotal;
	@Value("${spring.redis.jedis.pool.max-wait}")
	private Integer	maxWaitMillis;


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
		return jedisPoolConfig;
	}

	/**
	 * 
	 * @方法描述 : RedisTemplate配置
	 * @param factory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(factory);

		// 使用fastjson序列化
		FastJsonRedisSerializer redisSerializer = new FastJsonRedisSerializer<>(Object.class);
		// value值的序列化采用fastJsonRedisSerializer
		redisTemplate.setValueSerializer(redisSerializer);
		redisTemplate.setHashValueSerializer(redisSerializer);
		// key的序列化采用StringRedisSerializer
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());

		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}
