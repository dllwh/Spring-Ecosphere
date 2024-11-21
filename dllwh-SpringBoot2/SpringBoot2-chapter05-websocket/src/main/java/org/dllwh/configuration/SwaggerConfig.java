package org.dllwh.configuration;

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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: SwaggerConfig
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-12
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private final String name = "独泪了无痕";
	private final String url = "https://github.com/dllwh/";
	private final String email = "duleilewuhen@sina.com";
	/** 是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置 */
	@Value(value = "${swagger.enabled}")
	private Boolean swaggerEnabled;

	@Bean
	public Docket createRestApiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				// 用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
				.apiInfo(apiInfo())
				// 是否开启
				.enable(swaggerEnabled).genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false)
				.forCodeGeneration(false)
				// 选择那些路径和api会生成document
				.pathMapping("/").select()
				// 指定扫描的包路径
				.apis(RequestHandlerSelectors.basePackage("org.dllwh"))
				// 指定路径处理PathSelectors.any()代表所有的路径
				.paths(PathSelectors.any())
				// 创建
				.build().pathMapping("/");
	}

	/**
	 * @方法描述:构建 api文档的详细信息函数
	 * @return
	 */
	private ApiInfo apiInfo() {
		// 联系人信息：联系人名字、联系人URL、联系人email
		Contact contact = new Contact(name, url, email);
		return new ApiInfoBuilder()
				// 标题
				.title("SpringBoot-Swagger2集成和使用-demo示例")
				// 描述
				.description("Spring Boot 学习")
				// 作者信息
				.contact(contact)
				// 版本
				.version("0.0.1")
				// 在basePath 基础上需要排除的url规则
				// .extensions(null)
				// 服务条款url
				// .termsOfServiceUrl("")
				// 许可证
				// .license("")
				// 许可证url
				// .licenseUrl("")
				.build();
	}
}