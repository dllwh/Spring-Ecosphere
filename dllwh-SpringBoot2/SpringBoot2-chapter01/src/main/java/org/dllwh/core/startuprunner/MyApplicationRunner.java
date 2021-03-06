package org.dllwh.core.startuprunner;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {
	private final static Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);
	
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		logger.info("服务器启动成功！ <<<< 使用ApplicationRunner接口，now=" + new Date());
		logger.info("获取到的参数： " + applicationArguments.getOptionNames());
		logger.info("获取到的参数： " + applicationArguments.getNonOptionArgs());
		logger.info("获取到的参数： " + applicationArguments.getSourceArgs());
	}
}