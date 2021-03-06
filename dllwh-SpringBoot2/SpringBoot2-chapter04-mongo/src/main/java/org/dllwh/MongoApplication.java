package org.dllwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 该注解指定项目为springboot，由此类当作程序入口自动装配 web 依赖的环境
// @SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@SpringBootApplication
public class MongoApplication {
	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}
}