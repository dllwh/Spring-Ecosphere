package org.dllwh.core.configuration;

import org.springframework.context.annotation.Configuration;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: Shiro 配置
 * 		
 *       <pre>
 * 1、RequiresRoles
 * 	表示当前Subject需要角色
 * 2、RequiresPermissions
 * 表示当前subject 需要权限
 * 3、RequiresAuthentication
 * 	表示当前Subject已经通过login 进行了身份验证；即Subject.isAuthenticated()返回true
 * 4、RequiresUser
 * 表示当前Subject已经身份验证或者通过记住我登录的。
 * 5、RequiresGuest
 * 	表示当前Subject 没有身份验证或者通过记住我登录过，既是游客身份
 *       </pre>
 * 
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月27日 下午8:30:39
 * @版本: V1.0
 * @since: JDK 1.8
 */
@Configuration
public class ShiroConfig {

}