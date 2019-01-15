package org.dllwh.service.impl;

import java.util.List;
import java.util.Map;

import org.dllwh.entity.Operate;
import org.dllwh.entity.RedisInfoDetail;
import org.dllwh.service.RedisService;
import org.springframework.stereotype.Service;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: TODO(这里用一句话描述这个类的作用)
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年1月16日 上午12:15:32
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Override
	public List<RedisInfoDetail> getRedisInfo() {
		return null;
	}

	@Override
	public List<Operate> getLogs(long entries) {
		return null;
	}

	@Override
	public Long getLogLen() {
		return null;
	}

	@Override
	public String logEmpty() {
		return null;
	}

	@Override
	public Map<String, Object> getKeysSize() {
		return null;
	}

	@Override
	public Map<String, Object> getMemeryInfo() {
		return null;
	}
}