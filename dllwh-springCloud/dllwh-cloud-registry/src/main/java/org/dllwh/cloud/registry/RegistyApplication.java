package org.dllwh.cloud.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistyApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegistyApplication.class, args);
	}
}