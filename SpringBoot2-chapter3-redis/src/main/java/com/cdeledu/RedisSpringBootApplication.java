package com.cdeledu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年11月21日 上午12:26:40
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
// 该注解指定项目为springboot，由此类当作程序入口自动装配 web 依赖的环境
@SpringBootApplication
public class RedisSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(RedisSpringBootApplication.class, args);
	}
}
