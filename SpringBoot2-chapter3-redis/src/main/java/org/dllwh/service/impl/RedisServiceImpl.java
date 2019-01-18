package org.dllwh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dllwh.entity.Operate;
import org.dllwh.entity.RedisInfoDetail;
import org.dllwh.service.RedisService;
import org.dllwh.utils.DateHelper;
import org.dllwh.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.util.Slowlog;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Redis 服务实现类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年1月16日 上午12:15:32
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 获取redis服务器信息
	 */
	@Override
	public List<RedisInfoDetail> getRedisInfo() {
		String info = redisUtil.getRedisInfo();
		List<RedisInfoDetail> ridList = new ArrayList<RedisInfoDetail>();
		String[] strs = info.split("\n");
		RedisInfoDetail rif = null;
		if (strs != null && strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				rif = new RedisInfoDetail();
				String s = strs[i];
				String[] str = s.split(":");
				if (str != null && str.length > 1) {
					String key = str[0];
					String value = str[1];
					rif.setKey(key);
					rif.setValue(value);
					ridList.add(rif);
				}
			}
		}
		return ridList;
	}

	/**
	 * 获取redis日志列表
	 */
	@Override
	public List<Operate> getLogs(long entries) {
		List<Slowlog> list = redisUtil.getLogs(entries);
		List<Operate> opList = null;
		Operate op = null;
		boolean flag = false;
		if (list != null && list.size() > 0) {
			opList = new LinkedList<Operate>();
			for (Slowlog sl : list) {
				String args = JSON.toJSONString(sl.getArgs());
				if (args.equals("[\"PING\"]") || args.equals("[\"SLOWLOG\",\"get\"]")
						|| args.equals("[\"DBSIZE\"]") || args.equals("[\"INFO\"]")) {
					continue;
				}
				op = new Operate();
				flag = true;
				op.setId(sl.getId());
				op.setExecuteTime(DateHelper.getDateStr(sl.getTimeStamp() * 1000));
				op.setUsedTime(sl.getExecutionTime() / 1000.0 + "ms");
				op.setArgs(args);
				opList.add(op);
			}
		}
		if (flag)
			return opList;
		else
			return null;
	}

	/**
	 * 获取日志总数
	 */
	@Override
	public Long getLogLen() {
		return redisUtil.getLogsLen();
	}

	@Override
	public String logEmpty() {
		return redisUtil.logEmpty();
	}

	/**
	 * 清空日志
	 */
	@Override
	public Map<String, Object> getKeysSize() {
		long dbSize = redisUtil.dbSize();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("create_time", new Date().getTime());
		map.put("dbSize", dbSize);
		return map;
	}

	/**
	 * 获取当前数据库中key的数量
	 */
	@Override
	public Map<String, Object> getMemeryInfo() {
		String[] strs = redisUtil.getRedisInfo().split("\n");
		Map<String, Object> map = null;
		for (int i = 0; i < strs.length; i++) {
			String s = strs[i];
			String[] detail = s.split(":");
			if (detail[0].equals("used_memory")) {
				map = new HashMap<String, Object>();
				map.put("used_memory", detail[1].substring(0, detail[1].length() - 1));
				map.put("create_time", new Date().getTime());
				break;
			}
		}
		return map;
	}

	/**
	 * 获取当前客户端连接的数量
	 */
	@Override
	public Map<String, Object> getConnectClient() {
		return null;
	}

	/**
	 * 获取当前redisCPU使用大小情况
	 */
	@Override
	public Map<String, Object> getCpuInfo() {
		return null;
	}
}