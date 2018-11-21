package com.cdeledu.core.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.cdeledu.core.interceptor.LoggerInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:
 * 
 * 		<pre>
 * Spring的WebMvcConfigurer接口提供了很多方法让我们来定制SpringMVC的配置。
 * 而且Spring还提供了WebMvcConfigurerAdapter让我们更加优化的去进行配置。
 * 我们的配置类可以直接继承WebMvcConfigurerAdapter来进行配置。
 *       </pre>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月27日 下午8:25:10
 * @版本: V1.2
 * @since: JDK 1.8
 */
@SpringBootConfiguration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {

	/**
	 * 添加类型转换器和格式化器
	 */
	public void addFormatters(FormatterRegistry registry) {

	}

	/**
	 * 异常处理
	 */
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
	}

	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
	}

	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
	}

	/**
	 * 配置视图解析器
	 */
	public void configureViewResolvers(ViewResolverRegistry registry) {
	}

	/**
	 * addViewControllers可以方便的实现一个请求直接映射成视图，而无需书写controller
	 * registry.addViewController("请求路径").setViewName("请求页面文件路径")
	 */
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	/**
	 * 注册拦截器
	 * 
	 * <pre>
	 * 1、addInterceptor 用于添加拦截规则
	 * 2、excludePathPatterns 用户排除拦截
	 * </pre>
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		// 添加拦截器 以及 拦截器规则
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");

		registry.addInterceptor(new HandlerInterceptor() {
			@Override
			public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
					Object handler, @Nullable Exception ex) throws Exception {
				if (response.getStatus() / 100 >= 4) {
					log.error(
							"访问URL:" + request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE));
				} else {
					log.info(("访问URL:" + request.getRequestURI()));
				}
			}
		});
	}

	/**
	 * 配置自定义静态资源路径
	 * 
	 * <pre>
	 * 1、addResourceLocations指的是文件放置的目录
	 * 2、addResoureHandler指的是对外暴露的访问路径
	 * 3、SpringBoot 默认静态访问：classpath:/META-INF/resources/,classpath:/resources/,classpath:/static,classpath:/public/
	 * </pre>
	 */
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/article/**")
				.addResourceLocations("file:D:/DevResource/uploads/article/");
	}

	/**
	 * 配置消息转换器--这里我用的是alibaba 开源的 fastjson
	 */
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 1.需要定义一个convert转换消息的对象;
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		// 2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteDateUseDateFormat);
		// 3处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		// 4.在convert中添加配置信息.
		fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		// 5.将convert添加到converters当中.
		converters.add(fastJsonHttpMessageConverter);
	}

	/**
	 * 重写父类提供的跨域请求处理的接口，目的是添加映射路径和具体的CORS配置信息
	 */
	public void addCorsMappings(CorsRegistry registry) {
		// 添加映射路径
		registry.addMapping("/**")
				// 放行哪些原始域
				.allowedOrigins("*")
				// 是否发送Cookie信息
				.allowCredentials(true)
				// 放行哪些原始域(请求方式)
				.allowedMethods("*")
				// 放行哪些原始域(头部信息)
				.allowedHeaders("*");
		// 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
		// .exposedHeaders("*");
	}
}
