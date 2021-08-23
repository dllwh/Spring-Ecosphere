package org.dllwh.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.util.Slowlog;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Jedis操作redis的工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月3日 下午8:30:17
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取redis 服务器信息
     *
     * @return
     */
    public String getRedisInfo() {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Client client = jedis.getClient();
            client.info();
            String info = client.getBulkReply();
            return info;
        } catch (Exception e) {
            log.error(e.getMessage());
            return "";
        } finally {
            closeRedisClient(jedis);
        }
    }

    /**
     * 获取日志列表
     * @param entries
     * @return
     */
    public List<Slowlog> getLogs(long entries) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<Slowlog> logList = jedis.slowlogGet(entries);
            return logList;
        } finally {
            closeRedisClient(jedis);
        }
    }

    /**
     * 获取日志条数
     * @return
     */
    public Long getLogsLen() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long logLen = jedis.slowlogLen();
            return logLen;
        } finally {
            closeRedisClient(jedis);
        }
    }

    /**
     * 清空日志
     * @return
     */
    public String logEmpty() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.slowlogReset();
        } finally {
            closeRedisClient(jedis);
        }
    }

    /**
     * 获取占用内存大小
     * @return
     */
    public Long dbSize() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 配置redis服务信息
            Client client = jedis.getClient();
            client.dbSize();
            return client.getIntegerReply();
        } finally {
            closeRedisClient(jedis);
        }
    }

    private void closeRedisClient(Jedis jedisClient) {
        if (jedisClient != null) {
            jedisClient.close();
        }
    }
}