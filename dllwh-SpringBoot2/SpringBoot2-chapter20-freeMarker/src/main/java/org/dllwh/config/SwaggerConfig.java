package org.dllwh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	private final String	name	= "独泪了无痕";
	private final String	url		= "https://github.com/dllwh/";
	private final String	email	= "duleilewuhen@sina.com";
	// 是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
	@Value(value = "${swagger.enabled}")
	private Boolean			swaggerEnabled;

	@Bean
	public Docket createRestApiDocket() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.apiInfo(apiInfo()) // 用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
				.enable(swaggerEnabled) // 是否开启
				.genericModelSubstitutes(DeferredResult.class) //
				.useDefaultResponseMessages(false) //
				.forCodeGeneration(false) //
				.pathMapping("/").select() // 选择那些路径和api会生成document
				.apis(RequestHandlerSelectors.basePackage("org.dllwh")) // 指定扫描的包路径
				.paths(PathSelectors.any())// 指定路径处理PathSelectors.any()代表所有的路径
				.build().pathMapping("/");// 创建
	}

	/**
	 * @方法描述:构建 api文档的详细信息函数
	 * @return
	 */
	private ApiInfo apiInfo() {
		// 联系人信息：联系人名字、联系人URL、联系人email
		Contact contact = new Contact(name, url, email);
		return new ApiInfoBuilder().title("SpringBoot-FreeMark集成代码生成器") // 标题
				.description("Spring Boot 学习") // 描述
				.contact(contact)// 作者信息
				.version("0.0.1")// 版本
				// .extensions(null) //在basePath 基础上需要排除的url规则
				// .termsOfServiceUrl("") // 服务条款url
				// .license("") //许可证
				// .licenseUrl("") //许可证url
				.build();
	}
}