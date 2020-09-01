package org.dllwh.eureka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient // 表明这是一个EurekaClient（服务消费者）
public class EurekaConsumerClientApplication {
	@Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(EurekaConsumerClientApplication.class, args);
	}
}
