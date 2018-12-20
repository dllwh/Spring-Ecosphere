package com.cdeledu.modules.monitor.job.service;

import java.util.List;

import com.cdeledu.modules.monitor.job.domain.Job;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 定时任务调度信息信息 服务层
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:19:50
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public interface JobService {
	List<Job> getJobList(Job scheduleJob);
	
	int getJobListCount(Job scheduleJob);
	
	Job getJobById(int jobId);
	
	/**
	 * @方法描述:保存定时任务
	 */
	int save(Job scheduleJob);
	
	/**
	 * @方法描述:更新定时任务
	 */
	int update(Job scheduleJob);
	
	/**
	 * @方法描述:批量删除定时任务
	 */
	int delete(Integer jobId);
	
	/**
	 * @方法描述:批量删除定时任务
	 */
	int deleteBatch(Integer[] jobId);
	
	/**
	 * @方法描述:批量更新定时任务状态
	 */
	int updateBatch(Integer[] jobIds, int status);
	
	/**
	 * @方法描述:立即执行
	 */
	int run(Integer jobId);
	
	/**
	 * @方法描述:暂停运行
	 */
	int pause(Integer jobId);
	
	/**
	 * @方法描述:恢复运行
	 */
	int resume(Integer jobId);
}