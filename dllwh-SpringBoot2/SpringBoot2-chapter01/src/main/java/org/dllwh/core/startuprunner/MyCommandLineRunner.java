package org.dllwh.core.startuprunner;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
	private final static Logger logger = LoggerFactory.getLogger(MyCommandLineRunner.class);
	
	@Override
	public void run(String... args) throws Exception {
		String strArgs = Arrays.stream(args).collect(Collectors.joining("|"));
		logger.info("服务器启动成功！<<<< 使用CommandLineRunner接口  with arguments:" + strArgs);
	}
}