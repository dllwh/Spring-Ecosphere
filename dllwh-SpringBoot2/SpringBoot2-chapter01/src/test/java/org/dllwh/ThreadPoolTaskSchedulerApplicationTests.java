package org.dllwh;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 动态添加定时任务
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月17日 下午1:42:26
 * @版本: V1.0
 * @since: JDK 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ThreadPoolTaskSchedulerApplicationTests {
	final static Logger	logger	= LoggerFactory.getLogger(ThreadPoolTaskSchedulerApplicationTests.class);
	@Autowired
	TaskScheduler		taskScheduler;

	@Test
	public void threadPoolTaskScheduler() {
		taskScheduler.schedule(new Runnable() {
			public void run() {
				logger.info("ThreadPoolTaskScheduler定时任务：" + new Date());
			}
		}, new CronTrigger("0/3 * * * * ?"));// 每3秒执行一次
	}
}
