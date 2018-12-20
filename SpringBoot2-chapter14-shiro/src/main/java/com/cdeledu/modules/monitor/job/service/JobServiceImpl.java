package com.cdeledu.modules.monitor.job.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdeledu.modules.monitor.job.domain.Job;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午7:21:00
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Service
public class JobServiceImpl implements JobService {
	
	@Override
	public List<Job> getJobList(Job scheduleJob) {
		return null;
	}
	
	@Override
	public int getJobListCount(Job scheduleJob) {
		return 0;
	}
	
	@Override
	public Job getJobById(int jobId) {
		return null;
	}
	
	@Override
	public int save(Job scheduleJob) {
		return 0;
	}
	
	@Override
	public int update(Job scheduleJob) {
		return 0;
	}
	
	@Override
	public int delete(Integer jobId) {
		return 0;
	}
	
	@Override
	public int deleteBatch(Integer[] jobId) {
		return 0;
	}
	
	@Override
	public int updateBatch(Integer[] jobIds, int status) {
		return 0;
	}
	
	@Override
	public int run(Integer jobId) {
		return 0;
	}
	
	@Override
	public int pause(Integer jobId) {
		return 0;
	}
	
	@Override
	public int resume(Integer jobId) {
		return 0;
	}
}