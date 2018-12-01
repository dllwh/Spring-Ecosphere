package com.cdeledu.core.startuprunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 初始化平台数据至缓存中
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月10日 下午2:08:41
 * @版本: V1.0
 * @since: JDK 1.8
 */
@Slf4j
@Component
public class InitBean implements ApplicationListener<ContextRefreshedEvent> {
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			initdata();
		}
		
	}
	
	public void initdata() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cacheDataDictionary();
		log.info(sdf.format(new Date()) + "--->开始初始化调度任务");
		cacheQuarzInit();
		log.info(sdf.format(new Date()) + "--->结束初始化调度任务");
	}
	
	/**
	 * @方法描述 : 加载调度器设置
	 */
	private void cacheQuarzInit() {
	
	}
	
	/**
	 * @方法描述 : 加载数据字典，平台常量及平台路径到缓存中
	 */
	private void cacheDataDictionary() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long millis1 = System.currentTimeMillis();
		long millis2 = System.currentTimeMillis();
		List<Object> DataDictionaryList = Lists.newArrayList();
		log.info(sdf.format(new Date()) + "--->读取数据字典耗时:" + (millis2 - millis1) + "毫秒");
		log.info(sdf.format(new Date()) + "--->加载缓存配置开始");
		millis2 = System.currentTimeMillis();
		log.info(sdf.format(new Date()) + "--->一共加载了:" + DataDictionaryList.size() + "条数据字典数据");
		log.info(sdf.format(new Date()) + "--->将数据存入缓存耗时:" + (millis2 - millis1) + "毫秒");
		log.info(sdf.format(new Date()) + "--->加载缓存配置结束");
	}
}