package org.dllwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication // 该注解指定项目为SpringBoot，由此类当作程序入口自动装配 web 依赖的环境
@EnableCaching // 注解配置启用缓存，自动配置配置文件的配置信息进行条件注入缓存所需实例
public class RedisSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(RedisSpringBootApplication.class, args);
	}
}