package com.cdeledu.framework.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.cdeledu.common.constant.ShiroConstants;
import com.cdeledu.framework.shiro.ShiroRealm;
import com.cdeledu.framework.shiro.filter.LoginFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: shiro配置项
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月5日 下午10:56:52
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Slf4j
@Configuration
public class ShiroConfiguration {
	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy() {
		FilterRegistrationBean<DelegatingFilterProxy> registrationBean = new FilterRegistrationBean<DelegatingFilterProxy>();
		DelegatingFilterProxy proxy = new DelegatingFilterProxy();
		proxy.setTargetFilterLifecycle(true);
		proxy.setTargetBeanName("shiroFilter");
		registrationBean.setFilter(proxy);
		return registrationBean;
	}

	/**
	 * @方法描述 : 身份认证realm
	 * @return
	 */
	@Bean(name = "shiroRealm")
	public ShiroRealm myShiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
		// 这个可以自定义的加密,当前版本暂不实现
		// 增加缓存
		// shiroRealm.setCacheManager(cacheManager);
		return shiroRealm;
	}

	/**
	 * @方法描述 : 配置核心安全事务管理器
	 */
	@Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(shiroRealm);
		// 记住我
		// securityManager.setRememberMeManager(rememberMeManager());
		// 缓存管理
		// securityManager.setCacheManager(cacheManager());
		// session 管理
		// securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// Shiro的核心安全接口,这个属性是必须的
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 身份认证失败，则跳转到登录页面的配置
		shiroFilterFactoryBean.setLoginUrl(ShiroConstants.LOGIN);
		// 权限认证失败，则跳转到指定页面
		shiroFilterFactoryBean.setUnauthorizedUrl(ShiroConstants.UNAUTHORIZED);

		/**
		 * 自定义拦截器
		 */
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();

		// 登录校验
		filtersMap.put("loginFilter", new LoginFilter());
		// 限制同一帐号同时在线的个数。
		// filtersMap.put("kickout", kickoutSessionFilterHandler());
		// 角色校验
		// filtersMap.put("roleFilter", roleFilterHandler());
		// 权限校验
		// filtersMap.put("permissionFilter", permissionFilterHandler());
		// 在线用户处理过滤器(当前版本暂时搁置)
		// filtersMap.put("onlineSession", onlineSessionFilter());
		// 在线用户同步过滤器(当前版本暂时搁置)
		// filtersMap.put("syncOnlineSession", syncOnlineSessionFilter());
		// 注销成功，则跳转到指定页面
		// filtersMap.put("logout", logoutFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);

		/**
		 * 权限拦截
		 */
		Map<String, String> filterChainMap = new LinkedHashMap<>();
		// 游客，开发权限
		filterChainMap.put("/guest/**", "anon");
		filterChainMap.put("/druid/**", "anon");

		/**
		 * 登录和主页不需要认证
		 */
		filterChainMap.put("/login/**", "anon");
		filterChainMap.put("/login", "anon");
		filterChainMap.put("/", "anon");
		filterChainMap.put("/favicon.ico**", "anon");
		filterChainMap.put("/csrf**", "anon");
		// 退出
		// filterChainMap.put("/logout", "logout");

		/**
		 * 静态资源匿名访问
		 */
		filterChainMap.put("/static/**", "anon");
		filterChainMap.put("/druid/**", "anon");

		/**
		 * Swagger
		 */
		filterChainMap.put("/swagger-ui.html", "anon");
		filterChainMap.put("/webjars/**", "anon");
		filterChainMap.put("/swagger-resources/**", "anon");
		filterChainMap.put("/v2/api-docs", "anon");

		// 退出 logout地址，shiro去清除session
		// filterChainMap.put("/logout", "logout");
		// 其余接口一律拦截，这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
		filterChainMap.put("/**", "loginFilter");
		// filterChainMap.put("/**", "anon");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

		if (log.isDebugEnabled()) {
			log.debug("Shiro拦截器工厂类注入成功");
		}
		return shiroFilterFactoryBean;
	}
}