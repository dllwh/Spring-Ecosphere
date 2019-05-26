package org.dllwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 // 开启 swagger2 功能
public class FreeMarkerApplication {
	public static void main(String[] args) {
		SpringApplication.run(FreeMarkerApplication.class, args);
	}
}