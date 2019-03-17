package org.dllwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 项目启动类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:36:44
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
public class ShiroApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(ShiroApplication.class, args);
	}
	
	/**
	 * 如此配置打包后可以用tomcat下使用
	 */
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ShiroApplication.class);
	}
}