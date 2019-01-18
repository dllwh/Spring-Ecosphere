package org.dllwh.service;

import java.util.List;
import java.util.Map;

import org.dllwh.entity.Operate;
import org.dllwh.entity.RedisInfoDetail;

public interface RedisService {
	/**
	 * @方法描述:获取redis服务器信息
	 */
	public List<RedisInfoDetail> getRedisInfo();

	/**
	 * @方法描述:获取redis日志列表
	 */
	public List<Operate> getLogs(long entries);

	/**
	 * @方法描述:获取日志总数
	 */
	public Long getLogLen();

	/**
	 * @方法描述:清空日志
	 */
	public String logEmpty();

	/**
	 * @方法描述:获取当前数据库中key的数量
	 */
	public Map<String, Object> getKeysSize();

	/**
	 * @方法描述:获取当前redis使用内存大小情况
	 */
	public Map<String, Object> getMemeryInfo();
	
	/**
	 * @方法描述:获取当前客户端连接的情况
	 */
	public Map<String, Object> getConnectClient();

	/**
	 * @方法描述:获取当前redisCPU使用大小情况
	 */
	public Map<String, Object> getCpuInfo();
}