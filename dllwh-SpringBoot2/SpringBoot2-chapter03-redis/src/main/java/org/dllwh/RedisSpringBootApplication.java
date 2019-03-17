package org.dllwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//该注解指定项目为springboot，由此类当作程序入口自动装配 web 依赖的环境
@SpringBootApplication
@EnableSwagger2
public class RedisSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(RedisSpringBootApplication.class, args);
	}
}