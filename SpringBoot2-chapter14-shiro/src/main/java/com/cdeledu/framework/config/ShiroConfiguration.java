package com.cdeledu.framework.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cdeledu.common.constant.ShiroConstants;
import com.cdeledu.framework.shiro.ShiroRealm;
import com.cdeledu.framework.shiro.filter.KickoutSessionFilter;
import com.cdeledu.framework.shiro.filter.LoginFilter;
import com.cdeledu.framework.shiro.filter.OnlineSessionFilter;
import com.cdeledu.framework.shiro.filter.PermissionFilter;
import com.cdeledu.framework.shiro.filter.RoleFilter;
import com.cdeledu.framework.shiro.filter.SyncOnlineSessionFilter;
import com.cdeledu.framework.shiro.filter.UserLogoutFilter;

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
		securityManager.setRememberMeManager(rememberMeManager());
		// 缓存管理
		// securityManager.setCacheManager(cacheManager());
		// session 管理
		securityManager.setSessionManager(sessionManager());
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
		filtersMap.put("kickout", new KickoutSessionFilter());
		// 角色校验
		filtersMap.put("roleFilter", new RoleFilter());
		// 权限校验
		filtersMap.put("permissionFilter", new PermissionFilter());
		// 在线用户处理过滤器(当前版本暂时搁置)
		filtersMap.put("onlineSession", new OnlineSessionFilter());
		// 在线用户同步过滤器(当前版本暂时搁置)
		filtersMap.put("syncOnlineSession", new SyncOnlineSessionFilter());
		// 注销成功，则跳转到指定页面
		filtersMap.put("logout", new UserLogoutFilter());

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

		// 其余接口一律拦截，这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
		filterChainMap.put("/**", "loginFilter");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

		if (log.isDebugEnabled()) {
			log.debug("Shiro拦截器工厂类注入成功");
		}
		return shiroFilterFactoryBean;
	}

	/**
	 * @方法描述 : session管理器
	 * @return
	 */
	private DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		// session的失效时长，默认是30 分钟((1800000)),该值以毫秒为时间单位
		sessionManager.setGlobalSessionTimeout(ShiroConstants.TIMEOUT);
		// 是否在会话过期后会调用SessionDAO的delete方法删除会话,默认true
		sessionManager.setDeleteInvalidSessions(true);
		// 跳转页面删除sessionId
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		// 是否开启session验证检测
		sessionManager.setSessionValidationSchedulerEnabled(true);
		// 定时清理失效session , 清理用户直接关闭浏览器造成的孤立会话 :默认每小时检测一次
		sessionManager.setSessionValidationInterval(ShiroConstants.INTERVAL);
		// 是否启用/禁用，默认是启用的；如果禁用后将不会设置Session Id Cookie，即默认使用了Servlet容器的JSESSIONID
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdCookie(sessionIdCookie());
		return sessionManager;
	}

	/**
	 * @方法描述 : rememberMe管理器
	 * @return
	 */
	private CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		return cookieRememberMeManager;
	}

	/**
	 * @方法描述 : sessionIdCookie的实现,用于重写覆盖容器默认的JSESSIONID
	 * @return
	 */
	private SimpleCookie sessionIdCookie() {
		SimpleCookie cookie = new SimpleCookie();
		cookie.setName("shiro.sesssionCookie");
		// 设置Cookie的路径，默认空，即存储在域名根下
		cookie.setPath("/");
		// 设置Cookie的过期时间，秒为单位，默认-1表示关闭浏览器时过期Cookie
		cookie.setMaxAge(7200);
		// 如果设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击
		cookie.setHttpOnly(true);
		return cookie;
	}

	/**
	 * @方法描述 : 记住我
	 * @return
	 */
	private SimpleCookie rememberMeCookie() {
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// 记住我cookie生效时间30天 ,单位秒;
		simpleCookie.setMaxAge(2592000);
		return simpleCookie;
	}

	/**
	 * @方法描述 : Shiro生命周期处理器
	 * 
	 *       <pre>
	 *  配合 @RequiresRoles 、@RequiresPermissions 使用
	 *       </pre>
	 * 
	 * @return
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * @方法描述 : 授权所用配置
	 * 
	 *       <pre>
	 *  配合 @RequiresRoles 、@RequiresPermissions 使用
	 *       </pre>
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		proxyCreator.setProxyTargetClass(true);
		return proxyCreator;
	}

	/**
	 * @方法描述 : 开启Shiro注解通知器
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
}