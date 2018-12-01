package com.cdeledu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

// 该注解指定项目为springboot，由此类当作程序入口自动装配 web 依赖的环境
@SpringBootApplication
@ServletComponentScan // 开启监听器
@EnableScheduling // 开启定时任务的配置
@EnableTransactionManagement // 开启数据库事务管理
@EnableSwagger2 // 开启swagger2
public class SpringBootApplicationWebInit {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationWebInit.class, args);
	}
}