package com.cdeledu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 该注解指定项目为springboot，由此类当作程序入口自动装配 web 依赖的环境
@SpringBootApplication
public class MongoSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(MongoSpringBootApplication.class, args);
	}
}
