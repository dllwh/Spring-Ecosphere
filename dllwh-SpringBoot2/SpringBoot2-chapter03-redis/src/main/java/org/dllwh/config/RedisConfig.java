package org.dllwh.config;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis 配置类
 * @author : 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月3日 下午8:28:29
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Configuration
@Slf4j
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String	host;

	@Value("${spring.redis.port}")
	private int		port;

	@Value("${spring.redis.timeout}")
	private int		timeout;

	@Value("${spring.redis.jedis.pool.max-idle}")
	private int		maxIdle;

	@Value("${spring.redis.jedis.pool.max-wait}")
	private long	maxWaitMillis;

	@Bean
	public JedisPool redisPoolFactory() {
		log.info("JedisPool注入成功！！");
		log.info("redis地址：" + host + ":" + port);
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
		return jedisPool;
	}

	/**
	 * 
	 * @方法描述 : 配置自定义redisTemplate
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);

		// 设置义key序列化的实例化对象
		RedisSerializer<String> stringSerializer = new StringRedisSerializer(StandardCharsets.UTF_8);
		// key采用String的序列化方式
		template.setKeySerializer(stringSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(stringSerializer);
		
		// 定义value的序列化方式
		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(
				Object.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		serializer.setObjectMapper(mapper);

		// value序列化方式采用jackson
		template.setValueSerializer(serializer);
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(serializer);

		// template.afterPropertiesSet();
		return template;
	}
}