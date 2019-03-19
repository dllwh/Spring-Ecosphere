package org.dllwh.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/demo").setViewName("demo/index.html");
		registry.addViewController("/demo/add").setViewName("demo/add.html");
		registry.addViewController("/demo/update").setViewName("demo/update.html");
	}
}