package org.dllwh.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.dllwh.common.constant.ShiroConstants;

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
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	/**
	 * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
	 */
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(ShiroConstants.indexUrl);
		// registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/**
		 * 静态资源配置
		 */
		String[] staticPath = new String[6];
		staticPath[0] = "classpath:/webviews/";
		staticPath[1] = "classpath:/templates/";
		staticPath[2] = "classpath:/META-INF/resources/";
		staticPath[3] = "classpath:/resources/";
		staticPath[4] = "classpath:/static/";
		staticPath[5] = "classpath:/public/";
		registry.addResourceHandler("/static/**").addResourceLocations(staticPath);
	}
}