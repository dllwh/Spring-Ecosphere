package com.cdeledu.core.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 监听Spring Boot的生命周期：启动、停止、重启、关闭
 * 		
 *       <pre>
 * 应用监听器事件执行先后顺序如下：
 * 1、ApplicationStartingEvent
 * 2、ApplicationEnvironmentPreparedEvent
 * 3、ApplicationPreparedEvent
 * 4、ApplicationStartedEvent
 * 5、ApplicationReadyEvent
 * 6、ApplicationFailedEvent
 *       </pre>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年11月1日 下午11:00:26
 * @版本: V1.0
 * @since: JDK 1.8
 */
@Slf4j
@Component // 这里注意我们直接把监听类注册成组件
public class StartApplicationListener implements ApplicationListener<ApplicationEvent> {
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ApplicationStartingEvent) {
			log.info("在运行开始时但任何处理之前发送，除了注册监测监听器和初始化器之外");
		} else if (event instanceof ApplicationEnvironmentPreparedEvent) {
			log.info("初始化环境变量");
		} else if (event instanceof ApplicationPreparedEvent) {
			log.info("初始化完成");
		} else if (event instanceof ContextRefreshedEvent) {
			log.info("应用刷新");
		} else if (event instanceof ApplicationReadyEvent) {
			log.info("应用已启动完成");
		} else if (event instanceof ContextStartedEvent) {
			log.info("应用启动，需要在代码动态添加监听器才可捕获");
		} else if (event instanceof ContextStoppedEvent) {
			log.info("应用停止");
		} else if (event instanceof ContextClosedEvent) {
			log.info("应用关闭");
		} else if (event instanceof ApplicationFailedEvent) {
			log.info("如果启动时发生异常");
		} else {
		
		}
	}
}