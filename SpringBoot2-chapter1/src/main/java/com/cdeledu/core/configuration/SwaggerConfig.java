package com.cdeledu.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
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
 * @版本: V1.0.2
 * @since: JDK 1.8
 */
@Configuration
@ComponentScan(basePackages = { "com.cdeledu.controller" })
public class SwaggerConfig {
	/**
	 * @方法描述:swagger2的配置文件，这里可以配置swagger2的一些基本的内容
	 * @return
	 * 
	 * @Api：修饰整个类，描述Controller的作用
	 * @ApiOperation：描述一个类的一个方法，或者说一个接口
	 * @ApiParam：单个参数描述
	 * @ApiModel：用对象来接收参数
	 * @ApiProperty：用对象接收参数时，描述对象的一个字段
	 * @ApiResponse：HTTP响应其中1个描述
	 * @ApiResponses：HTTP响应整体描述
	 * @ApiIgnore：使用该注解忽略这个API
	 * @ApiError：发生错误返回的信息
	 * @ApiImplicitParam：一个请求参数
	 * @ApiImplicitParams：多个请求参数
	 */
	@Bean
	public Docket customDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()) // 用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
		// .select()
		// .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)
		// .apis(RequestHandlerSelectors.withMethodAnnotation(Api.class))
		// .apis(RequestHandlerSelectors.basePackage("com.cdeledu.controller"))
		// .paths(PathSelectors.any())
		// .build()
		;
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
