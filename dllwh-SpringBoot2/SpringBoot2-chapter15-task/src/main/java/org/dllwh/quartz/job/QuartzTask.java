package org.dllwh.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow the newest starter!
 *
 * @类描述: 继承QuartzJobBean实现executeInternal即可，该方法即定时执行任务逻辑
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-05-20
 * @版本: V 1.0.1
 * @since: JDK 1.8
 * @see <a href="">TODO(连接内容简介)</a>
*/
@Slf4j
public class QuartzTask extends QuartzJobBean {
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * 执行定时任务
	 *
	 * @param jobExecutionContext
	 */
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) {
		log.info("quartz task: " + LocalDateTime.now().format(formatter));
	}
}
