package org.dllwh.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Slowlog;

@Component
public class RedisUtil {

	@Autowired
	JedisPool jedisPool;

	// 获取redis 服务器信息
	public String getRedisInfo() {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Client client = jedis.getClient();
			client.info();
			String info = client.getBulkReply();
			return info;
		} finally {
			// 返还到连接池
			jedis.close();
		}
	}

	// 获取日志列表
	public List<Slowlog> getLogs(long entries) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			List<Slowlog> logList = jedis.slowlogGet(entries);
			return logList;
		} finally {
			// 返还到连接池
			jedis.close();
		}
	}

	// 获取日志条数
	public Long getLogsLen() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			long logLen = jedis.slowlogLen();
			return logLen;
		} finally {
			// 返还到连接池
			jedis.close();
		}
	}

	// 清空日志
	public String logEmpty() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.slowlogReset();
		} finally {
			// 返还到连接池
			jedis.close();
		}
	}

	// 获取占用内存大小
	public Long dbSize() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// 配置redis服务信息
			Client client = jedis.getClient();
			client.dbSize();
			return client.getIntegerReply();
		} finally {
			// 返还到连接池
			jedis.close();
		}
	}
}
