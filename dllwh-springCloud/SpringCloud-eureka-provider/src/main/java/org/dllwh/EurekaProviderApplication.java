package org.dllwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 基于spring-cloud-netflix，只能为eureka作用
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年2月24日 下午11:01:53
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@SpringBootApplication
@EnableEurekaClient // 表明这是一个EurekaClient（服务提供者）
public class EurekaProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaProviderApplication.class, args);
	}
}