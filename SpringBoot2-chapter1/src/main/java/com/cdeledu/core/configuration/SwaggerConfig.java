package com.cdeledu.core.configuration;

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
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Swagger2配置
 * 
 *       <pre>
 *       	1、通过@Configuration注解，让Spring来加载该类配置
 *       	2、通过@EnableSwagger2注解来启用Swagger2，在本示例项目中，为了统一管理，该注解放置于Application.java
 *       </pre>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月18日 上午12:47:32
 * @版本: V1.0.4
 * @since: JDK 1.8
 */
@Configuration
@ComponentScan(basePackages = { "com.cdeledu.controller" })
public class SwaggerConfig {
	/**
	 * @方法描述:swagger2的配置文件，这里可以配置swagger2的一些基本的内容
	 * @return
	 */
	@Bean
	public Docket customDocket() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("")// 设置栏目名
				.apiInfo(apiInfo()) // 用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
				.pathMapping("/").select() // 选择那些路径和api会生成document
				.build(); // 创建
	}

	@Bean
	public Docket alipayApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("支付宝API接口文档") // 设置栏目名
				.apiInfo(apiInfo()) // 构建 api文档的详细信息函数
				.pathMapping("/") // 设置api根路径
				.select() // 初始化并返回一个API选择构造器
				.apis(RequestHandlerSelectors.basePackage("com.cdeledu.project.alipay")) // 指定扫描的包路径
				.paths(PathSelectors.any()) // 指定路径处理PathSelectors.any()代表所有的路径
				.build(); // 创建
	}

	@Bean
	public Docket weixinpayApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("微信API接口文档") // 设置栏目名
				.apiInfo(apiInfo())// 构建 api文档的详细信息函数
				.pathMapping("/")// 设置api根路径
				.select() // 初始化并返回一个API选择构造器
				.apis(RequestHandlerSelectors.basePackage("com.cdeledu.project.weixinpay"))// 指定扫描的包路径
				.paths(PathSelectors.any()) // 指定路径处理PathSelectors.any()代表所有的路径
				.build(); // 创建
	}

	@Bean
	public Docket unionpayApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("银联API接口文档")// 设置栏目名
				.apiInfo(apiInfo())// 构建 api文档的详细信息函数
				.pathMapping("/")// 设置api根路径
				.select() // 初始化并返回一个API选择构造器
				.apis(RequestHandlerSelectors.basePackage("com.cdeledu.project.unionpay"))// 指定扫描的包路径
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
