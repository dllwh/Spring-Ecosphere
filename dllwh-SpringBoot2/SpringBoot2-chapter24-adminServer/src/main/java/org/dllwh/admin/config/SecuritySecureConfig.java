package org.dllwh.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

/**
 * 配置security验证页面指向SpringBootAdmin提供的UI界面
 */
@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
	private final String contextPath;

	public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
		this.contextPath = adminServerProperties.getContextPath();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 跨域设置，SpringBootAdmin客户端通过instances注册，见InstancesController
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.ignoringAntMatchers(contextPath + "/instances");

		http.authorizeRequests().antMatchers(contextPath + "/assets/**").permitAll(); // 静态资源
		http.authorizeRequests().anyRequest().authenticated(); // 所有请求必须通过认证

		// 整合spring-boot-admin-server-ui
		http.formLogin().loginPage("/login").permitAll();
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");

		// 启用basic认证，SpringBootAdmin客户端使用的是basic认证
		http.httpBasic();
	}
}
