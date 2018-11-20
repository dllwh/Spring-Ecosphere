package com.cdeledu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication // 该注解指定项目为springboot，由此类当作程序入口自动装配 web 依赖的环境
@ServletComponentScan // 启动过滤器
@EnableScheduling // 启用定时任务的配置
@EnableTransactionManagement // 启动数据库事务管理
public class SpringBootApplicationWebInit {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationWebInit.class, args);
	}
}
