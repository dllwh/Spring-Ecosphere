package org.dllwh.social;

import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 社交登录配置主类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年11月25日 上午10:15:19
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class SocialConfig extends SocialConfigurerAdapter {
	/**
	 * 该回调方法用来允许应用添加需要支持的社交网络对应的连接工厂的实现
	 */
	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
			Environment environment) {
	}

	/**
	 * 该回调方法返回一个 org.springframework.social.UserIdSource 接口的实现对象，用来惟一标识当前用户。
	 */
	@Override
	public UserIdSource getUserIdSource() {
		return new UserIdSource() {
			@Override
			public String getUserId() {
				Authentication authentication = SecurityContextHolder.getContext()
						.getAuthentication();
				if (authentication == null) {
					throw new IllegalStateException(
							"Unable to get a ConnectionRepository: no user signed in");
				}
				return authentication.getName();
			}
		};
	}

	/**
	 * 该回调方法返回一个 org.springframework.social.connect.UsersConnectionRepository
	 * 接口的实现对象，用来管理用户与社交网络服务提供者之间的对应关系
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(
			ConnectionFactoryLocator connectionFactoryLocator) {
		return new InMemoryUsersConnectionRepository(connectionFactoryLocator);
	}
}
