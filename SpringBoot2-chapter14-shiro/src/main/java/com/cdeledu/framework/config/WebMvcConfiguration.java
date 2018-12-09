package com.cdeledu.framework.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月9日 下午2:31:53
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@SpringBootConfiguration
public class WebMvcConfiguration implements WebMvcConfigurer {
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String[] staticPath = new String[] { 
				"classpath:/webviews/",
				"classpath:/templates/",
				"classpath:/META-INF/resources/", 
				"classpath:/resources/", "classpath:/static/",
				"classpath:/public/" };
		registry.addResourceHandler("/static/**").addResourceLocations(staticPath);
	}
}
