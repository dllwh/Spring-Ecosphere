package com.cdeledu.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Swagger2配置
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年12月12日 下午6:45:15
 * @版本: V 0.1
 * @since: JDK 1.7
 */
@Configuration
@ComponentScan(basePackages = { "com.cdeledu" })
public class SwaggerConfig {
	@Bean
	public Docket monitorApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("系统监控接口文档") // 设置栏目名
				.apiInfo(apiInfo()) // 构建 api文档的详细信息函数
				.pathMapping("/") // 设置api根路径
				.select() // 初始化并返回一个API选择构造器
				.apis(RequestHandlerSelectors.basePackage("com.cdeledu.modules.monitor")) // 指定扫描的包路径
				.paths(PathSelectors.any()) // 指定路径处理PathSelectors.any()代表所有的路径
				.build(); // 创建
	}
	
	@Bean
	public Docket systemApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("系统相关接口文档") // 设置栏目名
				.apiInfo(apiInfo())// 构建 api文档的详细信息函数
				.pathMapping("/")// 设置api根路径
				.select() // 初始化并返回一个API选择构造器
				.apis(RequestHandlerSelectors.basePackage("com.cdeledu.modules.system"))// 指定扫描的包路径
				.paths(PathSelectors.any()) // 指定路径处理PathSelectors.any()代表所有的路径
				.build(); // 创建
	}
	
	/**
	 * @方法描述:构建 api文档的详细信息函数
	 * @return
	 */
	private ApiInfo apiInfo() {
		// 联系人信息：联系人名字、联系人URL、联系人email
		Contact contact = new Contact("独泪了无痕", "https://my.oschina.net/u/1030471",
				"duleilewuhen@sina.com");
		return new ApiInfoBuilder().title("Spring Boot 学习") // 标题
				.description("Spring Boot 学习") // 描述
				.contact(contact)// 作者信息
				.version("1.0.1")// 版本
				// .extensions(null) //在basePath 基础上需要排除的url规则
				// .termsOfServiceUrl("") // 服务条款url
				// .license("") //许可证
				// .licenseUrl("") //许可证url
				.build();
	}
}
