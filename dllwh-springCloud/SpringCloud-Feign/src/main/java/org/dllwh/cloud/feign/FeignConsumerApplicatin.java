package org.dllwh.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // 指定为feign角色
@EnableEurekaClient
public class FeignConsumerApplicatin {
	public static void main(String[] args) {
		SpringApplication.run(FeignConsumerApplicatin.class, args);
	}
}